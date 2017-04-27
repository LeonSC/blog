package blog.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import blog.model.DepositTopic;
import blog.startup.MongoDBConnector;

@Repository
public class DepositDao {

	/**
	 * 查找主题
	 * @param dt
	 * @return
	 */
	public DepositTopic save(DepositTopic dt)
	{
		if(dt==null)
		{
			return null;
		}
		MongoDBConnector.datastore.save(dt);
		ObjectId id = dt.getId();
		return MongoDBConnector.datastore.get(DepositTopic.class,id);
	}
	
	/**
	 * 获取主题列表
	 * @return
	 */
	public List<DepositTopic> getDepositTopicList()
	{
		List<DepositTopic> list = MongoDBConnector.datastore.createQuery(DepositTopic.class).asList();
		return list;
	}
	
	
}
