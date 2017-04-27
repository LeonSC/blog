package blog.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.dao.DepositDao;
import blog.model.DepositCard;
import blog.model.DepositTopic;

@Service
public class DepositService {

	@Autowired
	private DepositDao depositDao;
	
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
			dc.setUuid(UUID.randomUUID().toString());
			dc.setPrice(dt.getPrice());
			this.depositDao.save(dc);
		}
		return 0;
	}
}
