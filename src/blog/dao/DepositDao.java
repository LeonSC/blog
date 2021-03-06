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
	
	/**
	 * 发行+1
	 * @param bmid
	 * @return
	 */
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
	 * 查找已发送待兑换的
	 * @param bmid
	 * @return
	 */
	public List<DepositCard> getDepositCardSent(String bmid)
	{
		return MongoDBConnector.datastore.createQuery(DepositCard.class).field("okey").equal(bmid).field("BM_DEL").equal(1).asList();
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
	 * @param account
	 * @return
	 */
	public DepositCard getOneDepositCard(String okey, String bmid, String account)
	{
		DepositCard dc = MongoDBConnector.datastore.createQuery(DepositCard.class).field("okey").equal(okey).field("BM_DEL").notEqual(1).get();
		Query<DepositCard> updateQuery = MongoDBConnector.datastore.createQuery(DepositCard.class).field("BM_ID").equal(dc.getBM_ID());
		UpdateOperations<DepositCard> ops=MongoDBConnector.datastore.createUpdateOperations(DepositCard.class);
		ops.set("BM_DEL",1);
		ops.set("user", bmid);
		ops.set("account",account);
		MongoDBConnector.datastore.update(updateQuery, ops);
		return dc;
	}
	
	/**
	 * 通过三个获得一个卡
	 * @param bmid
	 * @param account
	 * @param code
	 * @return
	 */
	public DepositCard findDepositCard(String bmid, String account, String code)
	{
		return MongoDBConnector.datastore.createQuery(DepositCard.class).field("user").equal(bmid).field("account").equal(account).field("uuid").equal(code).get();
	}
	
	/**
	 * 删除一个用过的卡
	 * @param bmid
	 * @return
	 */
	public int deleteDepositCard(String bmid)
	{
		Query<DepositCard> query=MongoDBConnector.datastore.createQuery(DepositCard.class).field("BM_ID").equal(bmid).field("BM_DEL").equal(1);
		MongoDBConnector.datastore.delete(query);
		return 0;
	}
	
}
