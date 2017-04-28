package blog.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.component.MailComponent;
import blog.dao.ConfirmDao;
import blog.dao.UserDao;
import blog.model.Confirm;
import blog.model.Page;
import blog.model.User;
import blog.startup.Config;
import blog.startup.Tools;


/**
 * 用于登录相关
 * @author sasgsc
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ConfirmDao confirmDao;
	@Autowired
	private MailComponent mailComponent;
	
	/**
	 * 同时还要查找这个用户的管理员属性
	 * @param email
	 * @param pw
	 * @return
	 */
	public User checkUser(String email,String pw)
	{
		User u=this.userDao.findByUserName(email);
		if(u==null)
		{
			return null;
		}
		
		if(u.getPw()==null)
		{
			return null;
		}
		pw=Tools.digestSha1(pw);
		if(pw.equals(u.getPw()))
		{			
			return u;
		}
		return null;
	}
	
	
	/**
	 * 首先查找这个用户是否存在, 如果存在, 查找这个用户输入的密码是否是正确的密码, 
	 * 如果是, 返回直接登录, 返回这个用户, 如果不是, 返回用户名已被占用
	 * 不存在, 正常注册.
	 * @param email 需要验证结尾
	 * @param pw
	 * @return
	 */
	public User registerUser(String email,String pw)
	{
		//验证邮件的正确性
		if(email==null||email.isEmpty())
		{
			return new User();
		}
		
		if(email.contains(" "))
		{
			return new User();
		}
		
		ArrayList<String> suffixsList=new ArrayList<String>();
		suffixsList.add("com");
		suffixsList.add("cn");
		ArrayList<String> addressList=new ArrayList<String>();
		addressList.add("gmail");
		addressList.add("hotmail");
		addressList.add("qq");
		addressList.add("163");
		addressList.add("yahoo");
		addressList.add("sina");
		addressList.add("126");
		addressList.add("outlook");
		
		String[] emailAt=email.split("@");
		
		if(emailAt.length!=2)
		{
			return new User();
		}
		
		String[] emailAtDot=emailAt[1].split("\\.");
		
		if(emailAtDot.length<2)
		{
			return new User();
		}
		if(!suffixsList.contains(emailAtDot[emailAtDot.length-1]))
		{
			return new User();
		}
		if(!addressList.contains(emailAtDot[emailAtDot.length-2]))
		{
			return new User();
		}
		//---验证邮件的正确性---
		
		User u=this.userDao.findByUserName(email);
		
		if(u==null)
		{
			User us=new User();
			
			us.setUsername(email);
			us.setNickname(email);
			us.setPw(pw);
			us.setLv(1);
			
			us=this.userDao.save(us);
			
			return us;
		}
		
		pw=Tools.digestSha1(pw);
		
		if(pw.equals(u.getPw()))
		{
			return u;
		}
		
		u.setBM_DEL(-1);//user account has been taken
		return u;
	}
	
	
	public Page<User> findTenUsersByAccountWithPage(String name,Integer nowPage)
	{
		if(name==null)
		{
			return null; 
		}
		
		if(nowPage==null)
		{
			nowPage=1;
		}
		
		if(nowPage<0)
		{
			nowPage=1;
		}
		
		return this.userDao.findUsersByAccountWithPage(name, nowPage, 10);
	}
	
	public User findUserByBMID(String bmid)
	{
		if(bmid==null)
		{
			return null;
		}
		return this.userDao.findUserByBMID(bmid);
	}
	
	/**
	 * 通过EMAIL查找用户
	 * @param email
	 * @return
	 */
	public User findUserByEmail(String email)
	{
		if(email==null)
		{
			return null;
		}
		return this.userDao.findByUserName(email);
	}
	
	
	///////////////////////////update//////////////////////////////////////////
	public User updateHeaderIcon(String bmid, String visitPath)
	{
		User u = new User();
		u.setBM_ID(bmid);
		u.setHeaderIcon(visitPath);
		u = this.userDao.editUser(u);
		return u;
	}
	
	/**
	 * 设置会员信息
	 * @param bmid
	 * @param nickname
	 * @param gender
	 * @param sign
	 * @return
	 */
	public User updateUserInfo(String bmid,String nickname, Integer gender, String sign)
	{
		if(gender==null)
		{
			gender=0;
		}
		User u = new User();
		u.setBM_ID(bmid);
		u.setNickname(nickname);
		u.setGender(gender);
		if(sign!=null&&sign.length()>50)
		{
			sign = sign.substring(0, 50)+"..";
		}
		u.setSign(sign);
		u = this.userDao.editUser(u);
		return u;
	}
	
	
	///////////////////////////////email confirm/////////////////////////////////////////////////
	public int sendConfirmEmail(String user, String email)
	{
		Confirm c = this.confirmDao.findConfirmByEmail(email);
		if(c==null)
		{
			c = new Confirm();
			c.setUser(user);
			c.setEmail(email);
			c.setCode(Tools.getUUIDUpperCase());
			c.setUntil(Tools.getServerTime()+1000*60*60*24);
			c = this.confirmDao.save(c);
		}
		this.mailComponent.sendComfirmEmail(c.getEmail(),"请在浏览器中使用以下链接进行验证 "+Config.rootPath+"/email/check/"+c.getCode());
		return 0;
	}
	
	/**
	 * 验证码
	 * @param user
	 * @param code
	 * @return
	 */
	public int checkConfirmEmail(String code)
	{
		Confirm c = this.confirmDao.findConfirmByCode(code);
		if(!c.getCode().equals(code))
		{
			return -1;
		}
		User u = new User();
		u.setBM_ID(c.getUser());
		u.setEmail(c.getEmail());
		this.userDao.editUser(u);
		this.confirmDao.deleteConfirmByCode(c.getBM_ID());
		return 0;
	}
}
