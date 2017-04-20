package blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.dao.UserDao;
import blog.model.User;
import blog.startup.SuperAdminConfig;
import blog.startup.Tools;

@Service
public class AdminService {

	@Autowired
	private UserDao userDao;
	
	public User checkAdmin(String email,String pw)
	{
		pw=Tools.digestSha1(pw);
		User u = SuperAdminConfig.map.get(email);
		if(u!=null)
		{
			if(u.getAdmin().getPassword().equals(pw))
			{			
				return u;
			}
		}
		u=this.userDao.findByUserName(email);
		if(u==null)
		{
			return null;
		}
		if(u.getPw()==null)
		{
			return null;
		}
		if(u.getPw().equals(pw))
		{			
			return u;
		}
		return null;
	}
	
	public int saveCarousel(String bmid, String visitPath, String link)
	{
		return 0;
	}
}
