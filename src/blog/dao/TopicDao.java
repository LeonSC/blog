package blog.dao;

import java.util.HashMap;
import java.util.List;

import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.stereotype.Repository;

import blog.model.Topic;
import blog.startup.MongoDBConnector;

@Repository
public class TopicDao {

	public List<Topic> findTopicList()
	{
		return MongoDBConnector.datastore.find(Topic.class).order("order").asList();
	}
	
	public Topic findTopicByBMID(String bmid)
	{
		return MongoDBConnector.datastore.find(Topic.class).field("BM_ID").equal(bmid).get();
	}
	
	/**
	 * 更新管理员
	 * 必要参数BMID, manager map
	 * @param topic
	 * @return
	 */
	public Topic updateTopicManagerByBMID(Topic topic)
	{
		if(topic==null||topic.getBM_ID()==null)
		{
			return null;
		}
		
		Query<Topic> updateQuery = MongoDBConnector.datastore.createQuery(Topic.class).field("BM_ID").equal(topic.getBM_ID());
		
		UpdateOperations<Topic> ops=MongoDBConnector.datastore.createUpdateOperations(Topic.class);
		
		if(topic.getManager()==null)
		{
			topic.setManager(new HashMap<>());
		}
		ops.set("manager", topic.getManager());
		
		MongoDBConnector.datastore.update(updateQuery, ops);
		
		return MongoDBConnector.datastore.find(Topic.class).field("BM_ID").equal(topic.getBM_ID()).get();
	}
}
