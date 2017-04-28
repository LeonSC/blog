package blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.component.MailComponent;
import blog.dao.DepositDao;
import blog.dao.UserDao;
import blog.model.DepositCard;
import blog.model.DepositTopic;
import blog.model.User;
import blog.startup.Tools;

@Service
public class DepositService {

	@Autowired
	private DepositDao depositDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private MailComponent mailComponent;
	/**
	 * 
	 * @param title 名字
	 * @param count 发卡数量
	 * @param price 价格 基于元
	 * @return
	 */
	public DepositTopic saveDepositTopic(String title, Integer count,Integer price)
	{
		DepositTopic dt = new DepositTopic();
		dt.setTitle(title);
		dt.setCount(count);
		dt.setPrice(price);//基于元
		dt = this.depositDao.save(dt);
		return dt;
	}
	
	/**
	 * 获取发卡列表
	 * @return
	 */
	public List<DepositTopic> getDepositTopicList()
	{
		List<DepositTopic> list = this.depositDao.getDepositTopicList();
		for(DepositTopic dt:list)
		{
			dt.setRest(this.depositDao.getDepositCardCount(dt.getBM_ID()));
			dt.setSent(this.depositDao.getDepositCardSent(dt.getBM_ID()));
		}
		return list;
	}
	
	/**
	 * 
	 * @param depoisttopicid
	 * @return
	 */
	public int createDepositCard(String depoisttopicid)
	{
		DepositTopic dt = this.depositDao.findDepositTopicByBMID(depoisttopicid);
		long rest = this.depositDao.getDepositCardCount(dt.getBM_ID());
		if(rest!=0L)
		{
			return -1;
		}
		for(int i=0;i<dt.getCount();i++)
		{
			DepositCard dc = new DepositCard();
			dc.setOkey(dt.getBM_ID());
			dc.setUuid(Tools.getUUIDUpperCase());
			dc.setPrice(dt.getPrice());
			this.depositDao.save(dc);
		}
		this.depositDao.updateDepositTopicFrequencyByBMID(dt.getBM_ID());
		return 0;
	}
	
	/**
	 * 发送一张卡给用户
	 * @param email
	 * @param depoisttopicid
	 * @return
	 */
	public int sendDepositCard(String email, String depoisttopicid)
	{
		//查找用户
		User u = this.userDao.findByUserName(email);
		if(u==null)
		{
			return -1;
		}
		//是否绑定邮箱
		if(u.getEmail()==null||u.getEmail().equals(""))
		{
			return -2;
		}
		DepositCard card = this.depositDao.getOneDepositCard(depoisttopicid, u.getBM_ID(), u.getUsername());
		this.mailComponent.sendDepositCardEmail(u.getEmail(), card.getUuid());
		return 0;
	}
}
