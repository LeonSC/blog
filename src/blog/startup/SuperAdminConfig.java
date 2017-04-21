package blog.startup;

import java.util.HashMap;
import java.util.Map;

import blog.model.Auth;
import blog.model.User;


public class SuperAdminConfig {

	public final static Map<String, User> map= new HashMap<String, User>();

	/**
	 * 定义几个超级用户
	 * @return
	 */
	public static Map<String, User> getMap() {
		
		if(!SuperAdminConfig.map.isEmpty())
		{
			return SuperAdminConfig.map;
		}
		
		User user=new User();
		Auth ad=new Auth();
		ad.setPassword("10E6C182032B3DBADBD2A49C438D9D35A8B567B4");
		ad.setLv(999);
		ad.setVisible(999);
		ad.setCreate(999);
		ad.setDelete(999);
		ad.setModify(999);
		ad.setFind(999);
		user.setAdmin(ad);
		user.setUsername("sasgsc@gmail.com");
		user.setNickname("admin");
		SuperAdminConfig.map.put("sasgsc@gmail.com", user);
		
		User az=new User();
		Auth adaz=new Auth();
		adaz.setPassword("20C7D854C8E24573B3C4426842BC7D4B4FA63F73");
		adaz.setLv(999);
		adaz.setVisible(999);
		adaz.setCreate(999);
		adaz.setDelete(999);
		adaz.setModify(999);
		adaz.setFind(999);
		az.setAdmin(adaz);
		az.setUsername("imnotorc@163.com");
		az.setNickname("xiusir");
		SuperAdminConfig.map.put("imnotorc@163.com", az);
		
		return SuperAdminConfig.map;
	}
}
