package blog.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import blog.model.Draft;
import blog.startup.Config;
import net.coobird.thumbnailator.Thumbnails;

@Service
public class ContentService {

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
		cover = "";
		String coverpath = cover.replace(Config.getImgWebPath(), Config.getImgPhysicalPath());
		coverpath = coverpath.replaceAll("/", File.separator);
		File file = new File(coverpath);
		if (file.exists()) {
			try {
				String[] tmp = coverpath.split("\\.");
				coverpath = tmp[0]+"200x200."+tmp[1];
				Thumbnails.of(file).size(200, 200).toFile(coverpath);
				cover = coverpath.replace(Config.getImgPhysicalPath(),Config.getImgWebPath());;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//解析intro
		String intro = content.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "").replaceAll("[(/>)<]", "").trim().substring(100);
		Draft c = new Draft();
		c.setWrite(user);
		c.setTitle(title);
		c.setCover(cover);
		c.setIntro(intro);
		c.setContent(content);
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
}
