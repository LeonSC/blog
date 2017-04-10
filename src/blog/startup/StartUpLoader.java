package blog.startup;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

public class StartUpLoader extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6633712045797179941L;
	
	public void init(ServletConfig conf)
	{
		System.out.println("===============================启动项目参数=================================");
		ServletContext application=conf.getServletContext();
		application.setAttribute("config", Config.getConfig());
		System.out.println("访问根路径=>"+Config.rootPath);
		System.out.println("数据库用户=>"+Config.dbUser);
		System.out.println("数据库=>"+Config.dbBase);
		System.out.println("数据库地址=>"+Config.dbIP+":"+Config.dbPort);
		System.out.println("==========================项目参数启动======================================");
		
		System.out.println("===============================启动mongodb=================================");
		MongoDBConnector.getMongoDBConnector();
		System.out.println("===============================启动mongodb结束=================================");
		
		System.out.println("===============================装载超级用户=================================");
		SuperAdminConfig.getMap();
		System.out.println("==========================装载超级用户结束======================================");
		
		System.out.println("===============================获取地址图片存放地址=================================");
		System.out.println("工程真实路径=>"+application.getRealPath("/"));
		System.out.println("工程名=>"+application.getContextPath());
		Config.setImgPath(application.getRealPath("/"), application.getContextPath());
		System.out.println("图片根路径=>"+Config.getImgPhysicalPath());
		System.out.println("图片访问路径=>"+Config.getImgWebPath());
		System.out.println("===============================获取地址图片存放地址结束=================================");
	}

}
