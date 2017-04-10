package blog.startup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Config {

	private static Config config;
	
	private Config(){}
	
	public static Config getConfig()
	{
		if(Config.config==null)
		{
			Config.config=new Config();
			Config.config.propertyReader();
		}
		
		return Config.config;
	}
	
	private final static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	public static String updateTime=sdf.format(new Date(Tools.getServerTime()));

	///////////////常用路径参数////////////////////////
	public static String rootPath="";
	
	private static String imgPhysicalPath=null;//物理绝对路径
	private static String imgWebPath=null;//文件夹的名字, 使用rootPath拼接可以访问图片
	
	/**
	 * 数据库设置
	 */
	public static String dbUser;
	public static String dbBase;
	public static String dbPassword;
	public static String dbIP;
	public static Integer dbPort;
	//
	
	public String getUpdateTime() {
		return updateTime;
	}
	public String getRootPath() {
		return rootPath;
	}
	
	/**
	 * 读取配置文件
	 */
	public void propertyReader()
	{
		Properties prop = this.getProperties();
		
		Config.rootPath = prop.getProperty("rootPath");
		
		Config.dbUser = prop.getProperty("dbUser");
		Config.dbBase = prop.getProperty("dbBase");
		Config.dbPassword = prop.getProperty("dbPassword");
		Config.dbIP = prop.getProperty("dbIP");
		Config.dbPort = 27017;
		
		if(prop.getProperty("dbPort")!=null)
		{
			try {
				Config.dbPort=Integer.parseInt(prop.getProperty("dbPort"));
			} catch (NumberFormatException e) {}
		}
		
		//解析图片路径
	}
	
	/**
	 * 读取
	 * @return
	 */
	private Properties getProperties()
	{
		Properties prop = new Properties();
		InputStream in = null;
		
		File[] roots = File.listRoots();
		
		String root="";
		
		if(roots.length>0)
		{
			root=roots[0].toString();
		}
		
		String[] pathList={root+"blog.property",root+"home"+File.separator+"blog.property",root+"home"+File.separator+"ubuntu"+File.separator+"blog.property",root+"work"+File.separator+"blog.property"};
		
		try {
			for(String base:pathList)
			{
				System.out.println("读取配置文件=>"+base);
				File file = new File(base);
				if(file.exists()&&!file.isDirectory())
				{
					in = new FileInputStream(file);
					prop.load(in);
					in.close();
					return prop;
				}
			}
		} catch (FileNotFoundException e) {
			return prop;
		} catch (IOException e) {
			return prop;
		} finally{
			try {if(in!=null){in.close();}} catch (IOException e) {}
		}
		
		return prop;
	}

	/**
	 * 结尾已经加/
	 * @return
	 */
	public static String getImgWebPath() {
		return imgWebPath;
	}

	/**
	 * 获取图片的物理路径, 后面已经加/
	 * @return
	 */
	public static String getImgPhysicalPath() {
		return imgPhysicalPath;
	}
	
	
	//存放图片的路径
	public static void setImgPath(String total,String work)
	{
		if(Config.imgPhysicalPath!=null)
		{
			return;
		}
		
		if(work.length()>0)
		{
			work = work.substring(1)+File.separator;
			Config.imgPhysicalPath =total.substring(0, total.lastIndexOf(work))+"imgBlogFolder"+File.separator;
			Config.imgWebPath =Config.rootPath.substring(0, Config.rootPath.lastIndexOf(work.replace("\\", "")))+"imgBlogFolder"+"/";
		}
	}
	
	//用于标识用户session
	public static String memAuth="memAuth";
}
