package blog.service;

import java.io.File;

import org.springframework.stereotype.Service;

import blog.startup.Config;
import blog.startup.Tools;

/**
 * 用于处理图片相关
 * 
 * @author sasgs
 *
 */
@Service
public class ImgService {

	public String getImgPhysicalPath(String bmid, String suffix) 
	{
		StringBuffer sb = new StringBuffer(Config.getImgPhysicalPath()).append(bmid).append(File.separator)
				.append(Tools.longTransDateyyyyMMdd(Tools.getServerTime())).append(File.separator).append(Tools.getID())
				.append(suffix);
		return sb.toString();
	}
}
