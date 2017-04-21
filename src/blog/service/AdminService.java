package blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.dao.CarouselDao;
import blog.dao.NoticeDao;
import blog.dao.UserDao;
import blog.model.Auth;
import blog.model.Carousel;
import blog.model.Notice;
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
	@Autowired
	private NoticeDao noticeDao;
	
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
	
	/**
	 * 替换首页提示
	 * @param bar
	 * @param title
	 * @param notice
	 * @param link
	 * @return
	 */
	public int saveNotice(String bar, String title, String notice, String link)
	{
		Notice n = new Notice();
		n.setBar(bar);
		n.setTitle(title);
		n.setNotice(notice);
		n.setLink(link);
		n = this.noticeDao.save(n);
		if(n==null)
		{
			return -1;
		}
		TCache.getCache().indexPageNoticeList.add(0,n);
		if(TCache.getCache().indexPageNoticeList.size()>4)
		{
			TCache.getCache().indexPageNoticeList.remove(4);
		}
		return 0;
	}
	
	public int deleteNoticeByBMID(String bmid)
	{
		this.noticeDao.deleteByBMID(bmid);
		TCache.getCache().indexPageNoticeList = this.noticeDao.findNoticeList(4);
		return 0;
	}
	
	/**
	 * 修改用户的权限, 以及把普通用户设置成管理员
	 * @param bmid
	 * @param lv
	 * @param auth
	 * @param adminlv
	 * @param adminvisible
	 * @param admincreate
	 * @param admindelete
	 * @param adminmodify
	 * @param adminfind
	 * @return
	 */
	public int updateAdmin(String bmid, Integer lv, Integer auth, Integer adminlv, Integer adminvisible, Integer admincreate, Integer admindelete, Integer adminmodify, Integer adminfind)
	{
		User u = this.userDao.findUserByBMID(bmid);
		if(u==null)
		{
			return -1;
		}
		u.setLv(lv);
		if(u.getAdmin()==null&&auth==1)
		{
			u.setAdmin(new Auth());
		}
		if(u.getAdmin()!=null&&auth==0)
		{
			u.setAdmin(null);
		}
		if(u.getAdmin()!=null)
		{
			u.getAdmin().setLv(adminlv);
			u.getAdmin().setVisible(adminvisible);
			u.getAdmin().setCreate(admincreate);
			u.getAdmin().setDelete(admindelete);
			u.getAdmin().setModify(adminmodify);
			u.getAdmin().setFind(adminfind);
		}
		this.userDao.editAuth(u);
		return 0;
	}
}
