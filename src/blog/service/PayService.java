package blog.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.dao.ContentDao;
import blog.dao.UserDao;
import blog.model.Content;
import blog.model.Payment;
import blog.model.User;
import blog.startup.Tools;

@Service
public class PayService {

	@Autowired
	private ContentDao contentDao;
	@Autowired
	private UserDao userDao;
	
	/**
	 * 对文章进行支付
	 * @param artBmid
	 * @param payer
	 * @param secret 0标识显示支付, 1标识秘密支付
	 * @return
	 */
	public String art(String artBmid, User user, Integer secret)
	{
		if(artBmid==null)
		{
			return null;
		}
		Content c = this.contentDao.getContentByBMID(artBmid);
		if(c==null)
		{
			return null;
		}
		if(c.getPrice()==0)
		{
			return null;
		}
		if(c.getPayer()==null)
		{
			c.setPayer(new HashMap<>());
		}
		if(user==null)
		{
			return null;
		}
		if(user.getDeposit()<c.getPrice())
		{
			return "-1";
		}
		if(secret==null||secret!=1)
		{
			secret=0;
		}
		Payment p = c.getPayer().get(user.getBM_ID());
		if(p==null)
		{
			p = new Payment();
			p.setPayer(user.getBM_ID());
			p.setPayername(user.getNickname());
			p.setPayerheadericon(user.getHeaderIcon());
			p.setPayment(c.getPrice());
			p.setSecret(secret);
			p.setPaytime(Tools.getServerTime());
			c.getPayer().put(p.getPayer(), p);
		}
		else
		{
			p.setPayername(user.getNickname());
			p.setPayerheadericon(user.getHeaderIcon());
			p.setPayment(p.getPayment()+c.getPrice());
		}
		int re = this.contentDao.addPayerToContent(c.getBM_ID(), c.getPayer());
		if(re>0)
		{
			//对用户进行减款
			re = this.userDao.decUserDeposit(user.getBM_ID(), c.getPrice());
			if(re>0)
			{
				user.setDeposit(user.getDeposit()-c.getPrice());
			}
		}
		return c.getBM_ID();
	}
}
