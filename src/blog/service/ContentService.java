package blog.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import blog.dao.ContentDao;
import blog.dao.DraftDao;
import blog.dao.ReplyDao;
import blog.dao.UserDao;
import blog.model.Content;
import blog.model.Draft;
import blog.model.Manager;
import blog.model.Reply;
import blog.model.User;
import blog.startup.Config;
import blog.startup.TCache;
import blog.startup.Tools;
import net.coobird.thumbnailator.Thumbnails;

@Service
public class ContentService {
	@Autowired
	private DraftDao draftDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ContentDao contentDao;
	@Autowired
	private ReplyDao replyDao;
	/**
	 * 草稿
	 * @param title
	 * @param content
	 * @return
	 */
	public String saveDraft(String user, String title,String content)
	{
		Map<String,String> map = new HashMap<>();
		map.put("status", "0");
		map.put("info", "succ");
		if(title==null||title.isEmpty())
		{
			map.put("status", "-1");
			map.put("info", "title is empty");
		}
		title= this.titleFix(title);
		if(title.length()>50)
		{
			map.put("status", "-2");
			map.put("info", "title is too long");
		}
		if(content==null||content.isEmpty())
		{
			map.put("status", "-3");
			map.put("info", "content is empty");
		}
		content = this.contentFilter(content);
		if(content.length()<10)
		{
			map.put("status", "-4");
			map.put("info", "content is too short");
		}
		//解析第一张图片
		String cover = this.getFirstImg(content);
		//解析intro
		String intro = this.getIntroFromContent(content);
		Draft draft = new Draft();
		draft.setWrite(user);
		draft.setTitle(title);
		draft.setCover(cover);
		draft.setIntro(intro);
		draft.setContent(content);
		this.draftDao.saveOrUpdate(draft);
		return JSON.toJSONString(map);
	}
	
	/**
	 * 把title多于的字符全部去掉
	 * @param title
	 * @return
	 */
	private String titleFix(String title)
	{
		return title.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "").replaceAll("&.{2,6}?;", "").replaceAll("\r|\n|\t| ", "").trim();
	}
	
	/**
	 * 过滤非法字符
	 * @param content
	 * @return
	 */
	private String contentFilter(String content)
	{
		return content.trim();
	}
	
	/**
	 * 从正文中拿去第一张图片
	 * @param content
	 * @return
	 */
	private String getFirstImg(String content)
	{
		String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
		Pattern p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		Matcher m_image = p_image.matcher(content);
		if(!m_image.find()){
			return "";
		}
		String img = m_image.group();
		Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
		if(!m.find())
		{
			return "";
		}
		String cover = m.group(1).trim();
		if(!cover.equals(""))
		{
			String coverpath = cover.replace(Config.getImgWebPath(), Config.getImgPhysicalPath());
			File file = new File(coverpath);
			if (file.exists()) {
				try {
					int last = coverpath.lastIndexOf(".");
					String tmp = coverpath.substring(0, last)+"-200x200"+coverpath.substring(last,coverpath.length());
					coverpath = tmp;
					Thumbnails.of(file).size(200, 200).toFile(coverpath);
					cover = coverpath.replace(Config.getImgPhysicalPath(),Config.getImgWebPath());
				} catch (IOException e) {
					e.getStackTrace();
				}
			}
		}
		return cover;
	}
	
	/**
	 * 通过正文获取intro
	 * @param content
	 * @return
	 */
	private String getIntroFromContent(String content)
	{
		String intro = content.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "").replaceAll("&.{2,6}?;", "").trim();
		if(intro.length()>100)
		{
			intro=intro.substring(0,100);
		}
		return intro;
	}
	
	/**
	 * 通过用户ID查找
	 * @param bmid
	 * @return
	 */
	public Draft getDraftByUser(String bmid)
	{
		if(bmid==null)
		{
			return null;
		}
		Draft d = this.draftDao.getDraftByUser(bmid);
		return d;
	}
	
	/**
	 * 将一个草稿发布
	 * @param userid
	 * @param topic
	 * @return
	 */
	public Content publishContent(String userid, String topic, Integer price)
	{
		if(topic==null||topic.isEmpty())
		{
			return null;
		}
		if(price == null||price < 0)
		{
			price=0;
		}
		Draft d = this.draftDao.getDraftByUser(userid);
		User u = this.userDao.findUserByBMID(userid);
		u.setAdmin(null);
		u.setPw(null);
		Content c= new Content();
		c.setTopic(topic);
		c.setUser(u);
		c.setTitle(d.getTitle());
		c.setCover(d.getCover());
		c.setIntro(d.getIntro());
		c.setContent(d.getContent());
		c.setPrice(price);
		c.getNeed().setLv(0);
		c.getNeed().setVisible(0);
		c.getNeed().setLoginVisible(0);
		c = this.contentDao.save(c);
		//删除草稿
		this.draftDao.deleteDraftByUser(userid);
		return c;
	}
	
	/**
	 * 直接发布
	 * @param userid
	 * @param topic
	 * @param price
	 * @param title
	 * @param content
	 * @return
	 */
	public String layuiPublishContent(String userid, String topic, Integer price, String title, String content)
	{
		Map<String,String> map = new HashMap<>();
		map.put("status", "0");
		map.put("info", "succ");
		if(topic==null||topic.isEmpty())
		{
			map.put("status", "-6");
			map.put("info", "topic is empty");
			return JSON.toJSONString(map);
		}
		if(price == null||price < 0)
		{
			price=0;
		}
		if(title==null||title.isEmpty())
		{
			map.put("status", "-1");
			map.put("info", "title is empty");
			return JSON.toJSONString(map);
		}
		title= this.titleFix(title);
		if(title.length()>50)
		{
			map.put("status", "-2");
			map.put("info", "title is too long");
			return JSON.toJSONString(map);
		}
		if(content==null||content.isEmpty())
		{
			map.put("status", "-3");
			map.put("info", "content is empty");
			return JSON.toJSONString(map);
		}
		content = this.contentFilter(content);
		if(content.length()<10)
		{
			map.put("status", "-4");
			map.put("info", "content is too short");
			return JSON.toJSONString(map);
		}
		//解析第一张图片
		String cover = this.getFirstImg(content);
		//解析intro
		String intro = this.getIntroFromContent(content);
		User u = this.userDao.findUserByBMID(userid);
		u.setAdmin(null);
		u.setPw(null);
		Content c= new Content();
		c.setTopic(topic);
		c.setUser(u);
		c.setTitle(title);
		c.setCover(cover);
		c.setIntro(intro);
		c.setContent(content);
		c.setPrice(price);
		c.getNeed().setLv(0);
		c.getNeed().setVisible(0);
		c.getNeed().setLoginVisible(0);
		c = this.contentDao.save(c);
		return JSON.toJSONString(map);
	}
	
	
	/**
	 * 通过topic查找列表
	 * @param topic
	 * @return
	 */
	public List<Content> getContentListByTopic(String topic)
	{
		List<Content> list = this.contentDao.getContentListByTopic(topic);
		return list;
	}
	
	public Content getContentByBMID(String bmid)
	{
		Content c = this.contentDao.getContentByBMID(bmid);
		return c;
	}
	
	/**
	 * 
	 * @param user
	 * @param contentid
	 * @param reply
	 * @return
	 */
	public int setReplyForContent(User user, String contentid, String reply)
	{
		reply= reply.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "").replaceAll("&.{2,6}?;", "").replaceAll("\r|\n|\t| ", "").trim();
		Reply r = new Reply();
		r.setOkey(contentid);
		r.setUsername(user.getUsername());
		r.setNickname(user.getNickname());
		r.setWrite(user.getBM_ID());
		r.setHeadericon(user.getHeaderIcon());
		r.setContent(reply);
		this.replyDao.save(r);
		this.contentDao.incContentReplyOne(contentid);
		return 0;
	}
	
	/**
	 * 获取留言列表
	 * @param okey
	 * @return
	 */
	public List<Reply> getReplyList(String okey)
	{
		List<Reply> list = this.replyDao.getReplyList(okey);
		return list;
	}
	
	///////////////////////////置顶相关//////////////////////////////////////////////
	/**
	 * 把改变帖子置顶状态
	 * @param bmid
	 * @return
	 */
	public int changeContentToTop(User user, String topic, String bmid)
	{
		//验证是否有权限去操作
		Manager manager = TCache.getCache().getTitleCache().get(topic).getManager().get(user.getBM_ID());
		if(manager == null)
		{
			return -1;
		}
		Content c = this.contentDao.getContentByBMID(bmid);
		if(c==null)
		{
			return -2;
		}
		long top = 0L;
		if(c.getTop()==null||c.getTop()==0L)
		{
			top = Tools.getServerTime();
		}
		this.contentDao.updateContentTop(bmid, top);
		return 0;
	}
	
	/**
	 * 查找置顶贴
	 * @param topic
	 * @return
	 */
	public List<Content> getContentTopListByTopic(String topic)
	{
		List<Content> list = this.contentDao.getContentTopListByTopic(topic);
		return list;
	}
}
