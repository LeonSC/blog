package blog.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.stereotype.Repository;

import blog.model.DepositCard;
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
	
	/**
	 * 通过主键查找
	 * @param bmid
	 * @return
	 */
	public DepositTopic findDepositTopicByBMID(String bmid)
	{
		DepositTopic dt = MongoDBConnector.datastore.createQuery(DepositTopic.class).field("BM_ID").equal(bmid).get();
		return dt;
	}
	
	public int updateDepositTopicFrequencyByBMID(String bmid)
	{
		if(bmid==null)
		{
			return -1;
		}
		Query<DepositTopic> updateQuery = MongoDBConnector.datastore.createQuery(DepositTopic.class).field("BM_ID").equal(bmid);
		UpdateOperations<DepositTopic> ops=MongoDBConnector.datastore.createUpdateOperations(DepositTopic.class);
		ops.inc("frequency");
		MongoDBConnector.datastore.update(updateQuery, ops);
		return 0;
	}
	
	/**
	 * 查找余卡数量
	 * @param bmid
	 * @return
	 */
	public long getDepositCardCount(String bmid)
	{
		long count = MongoDBConnector.datastore.createQuery(DepositCard.class).field("okey").equal(bmid).field("BM_DEL").notEqual(1).count();
		return count;
	}
	
	/**
	 * 发行卡
	 * @param dc
	 * @return
	 */
	public DepositCard save(DepositCard dc)
	{
		if(dc==null)
		{
			return null;
		}
		MongoDBConnector.datastore.save(dc);
		ObjectId id = dc.getId();
		return MongoDBConnector.datastore.get(DepositCard.class,id);
	}
	
	/**
	 * 获取一张卡并把他设置成删除状态
	 * @param okey
	 * @return
	 */
	public DepositCard getOneDepositCard(String okey)
	{
		DepositCard dc = MongoDBConnector.datastore.createQuery(DepositCard.class).field("okey").equal(okey).field("BM_DEL").notEqual(1).get();
		return dc;
	}
}
