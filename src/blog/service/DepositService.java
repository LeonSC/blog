package blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.dao.DepositDao;
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
		return list;
	}
}
