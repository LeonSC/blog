package blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.dao.CarouselDao;
import blog.dao.UserDao;
import blog.model.Carousel;
import blog.model.User;
import blog.startup.SuperAdminConfig;
import blog.startup.TCache;
import blog.startup.Tools;

@Service
public class AdminService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private CarouselDao carouselDao;
	
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
	
	/**
	 * 更新轮转图
	 * @param bmid
	 * @param visitPath
	 * @param link
	 * @return
	 */
	public int saveCarousel(String bmid, String visitPath, String link)
	{
		Carousel c = new Carousel();
		c.setBM_ID(bmid);
		c.setImg(visitPath);
		c.setLink(link);
		c = this.carouselDao.update(c);
		
		for(Carousel cc :TCache.getCache().indexPageCarouselList)
		{
			if(cc.getBM_ID().equals(bmid))
			{
				cc.setImg(c.getImg());
				cc.setLink(c.getLink());
			}
		}
		return 0;
	}
}
