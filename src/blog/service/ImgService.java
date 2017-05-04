package blog.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

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

	/**
	 * 
	 * @param bmid
	 * @param suffix
	 * @param result 用于返回给上传图片的JS客户端
	 * @return 1, 用于返回给JS的字符串, 2, 用于存放图片的路径
	 */
	public String[] getImgPhysicalPath(String bmid, String name) 
	{
		String[] tmp = name.split("\\.");
		String suffix = tmp[tmp.length - 1];
		String newPicName = new StringBuffer(Tools.getID()).append(".").append(suffix).toString();
		String timePath = Tools.longTransDateyyyyMMdd(Tools.getServerTime());
		// 拼接需要返回的字符串信息
		StringBuffer rePath = new StringBuffer(Config.getImgPhysicalPath()).append(bmid).append(File.separator).append(timePath);
		//目录不存在就创建
		File file = new File(rePath.toString());
		if(!file.exists())
		{
			file.mkdirs();
		}
		rePath.append(File.separator).append(newPicName);
		//访问路径
		String visitPath = new StringBuffer(Config.getImgWebPath()).append(bmid).append("/").append(timePath).append("/").append(newPicName).toString();
		//拼装需要返回给JS的字符串
		Map<String,String> map = new HashMap<>();
		map.put("name", newPicName);
		map.put("url", visitPath);
		map.put("delete_url", new StringBuffer(Config.rootPath+"/write/imgDelete?file=").append(File.separator).append(timePath).append(File.separator).append(newPicName).toString());
		map.put("delete_type", "DELETE");
		List<Map<String,String>> list = new ArrayList<>();
		list.add(map);
		Map<String,List<Map<String,String>>> re = new HashMap<>();
		re.put("files", list);
		String[] reArray = {JSON.toJSONString(re),rePath.toString()};
		return reArray;
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public String delImgPhysicalPath(String path)
	{
		if (path.startsWith("data")) {
			return "";
		}
		// 拼装需要返回给JS的字符串
		Map<String, Boolean> map = new HashMap<>();
		map.put(path, true);
		List<Map<String, Boolean>> list = new ArrayList<>();
		list.add(map);
		Map<String, List<Map<String, Boolean>>> re = new HashMap<>();
		re.put("files", list);
		String tmp = JSON.toJSONString(re);
		String del = path.replace(Config.getImgWebPath(), Config.getImgPhysicalPath());
		del = del.replaceAll("/", File.separator);
		File file = new File(del);
		if(file.exists())
		{
			file.delete();
		}
		
		return tmp;
	}
	
	/**
	 * 获取管理员上传图片的地址和访问地址
	 * @param name
	 * @return 0, 文件地址, 1, 访问地址
	 */
	public String[] getAdminImgPhysicalPath(String name) 
	{
		String[] re={"",""};
		String[] tmp = name.split("\\.");
		String suffix = tmp[tmp.length - 1];
		String newPicName = new StringBuffer(Tools.getID()).append(".").append(suffix).toString();
		// 拼接需要返回的字符串信息
		StringBuffer rePath = new StringBuffer(Config.getImgPhysicalPath()).append("admin");
		//目录不存在就创建
		File file = new File(rePath.toString());
		if(!file.exists())
		{
			file.mkdirs();
		}
		rePath.append(File.separator).append(newPicName);
		//访问路径
		String visitPath = new StringBuffer(Config.getImgWebPath()).append("admin").append("/").append(newPicName).toString();
		re[0] = rePath.toString();
		re[1] = visitPath;
		return re;
	}
	
	
	/**
	 * 获取用户修改头像的路径
	 * @param name
	 * @return 0, 文件地址, 1, 访问地址
	 */
	public String[] getUserHeaderIconPhysicalPath(String bmid, String name) 
	{
		String[] re={"",""};
		String[] tmp = name.split("\\.");
		String suffix = tmp[tmp.length - 1];
		String newPicName = new StringBuffer(Tools.getID()).append(".").append(suffix).toString();
		// 拼接需要返回的字符串信息
		StringBuffer rePath = new StringBuffer(Config.getImgPhysicalPath()).append(bmid);
		//目录不存在就创建
		File file = new File(rePath.toString());
		if(!file.exists())
		{
			file.mkdirs();
		}
		rePath.append(File.separator).append(newPicName);
		//访问路径
		String visitPath = new StringBuffer(Config.getImgWebPath()).append(bmid).append("/").append(newPicName).toString();
		re[0] = rePath.toString();
		re[1] = visitPath;
		return re;
	}
	
	/**
	 * layui返回格式
	 * @param bmid
	 * @param name
	 * @return
	 */
	public String[] getImgPhysicalPathForLayUI(String bmid, String name) 
	{
		String[] tmp = name.split("\\.");
		String suffix = tmp[tmp.length - 1];
		String newPicName = new StringBuffer(Tools.getID()).append(".").append(suffix).toString();
		String timePath = Tools.longTransDateyyyyMMdd(Tools.getServerTime());
		// 拼接需要返回的字符串信息
		StringBuffer rePath = new StringBuffer(Config.getImgPhysicalPath()).append(bmid).append(File.separator).append(timePath);
		//目录不存在就创建
		File file = new File(rePath.toString());
		if(!file.exists())
		{
			file.mkdirs();
		}
		rePath.append(File.separator).append(newPicName);
		//访问路径
		String visitPath = new StringBuffer(Config.getImgWebPath()).append(bmid).append("/").append(timePath).append("/").append(newPicName).toString();
		//拼装需要返回给JS的字符串
		Map<String,Object> map = new HashMap<>();
		map.put("code", "0");
		map.put("msg", "上传成功");
		Map<String,String> data = new HashMap<>();
		data.put("src", visitPath);
		map.put("data", data);
		String[] reArray = {JSON.toJSONString(map),rePath.toString()};
		return reArray;
	}
}
