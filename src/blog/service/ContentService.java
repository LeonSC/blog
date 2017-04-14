package blog.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import blog.dao.DraftDao;
import blog.model.Draft;
import blog.startup.Config;
import net.coobird.thumbnailator.Thumbnails;

@Service
public class ContentService {
	@Autowired
	private DraftDao draftDao;
	
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
		title= title.trim();
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
}
