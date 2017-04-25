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
import blog.model.Reply;
import blog.model.User;
import blog.startup.Config;
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
		title= title.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "").replaceAll("&.{2,6}?;", "").replaceAll("\r|\n|\t| ", "").trim();
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
		//解析intro
		String intro = content.replaceAll("<(S*?)[^>]*>.*?|<.*? />", "").replaceAll("&.{2,6}?;", "").trim();
		if(intro.length()>100)
		{
			intro=intro.substring(0,100);
		}
		Draft draft = new Draft();
		draft.setWrite(user);
		draft.setTitle(title);
		draft.setCover(cover);
		draft.setIntro(intro);
		draft.setContent(content);
		this.draftDao.saveOrUpdate(draft);
		return JSON.toJSONString(map);
	}
	
	private String contentFilter(String content)
	{
		return content;
	}
	
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
		return m.group(1);
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
	public Content publishContent(String userid, String topic)
	{
		if(topic==null||topic.isEmpty())
		{
			return null;
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
		c = this.contentDao.save(c);
		//删除草稿
		this.draftDao.deleteDraftByUser(userid);
		return c;
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
	 * 
	 * @param okey
	 * @return
	 */
	public List<Reply> getReplyList(String okey)
	{
		List<Reply> list = this.replyDao.getReplyList(okey);
		return list;
	}
}
