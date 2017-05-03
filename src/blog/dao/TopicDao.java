package blog.dao;

import java.util.HashMap;
import java.util.List;

import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
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
	
	/**
	 * 更新TOPIC的权限
	 * @param topic
	 * @return
	 */
	public int updateTopicAuthByBMID(Topic topic)
	{
		if(topic==null||topic.getBM_ID()==null||topic.getAuth()==null)
		{
			return -1;
		}
		Query<Topic> updateQuery = MongoDBConnector.datastore.createQuery(Topic.class).field("BM_ID").equal(topic.getBM_ID());
		UpdateOperations<Topic> ops=MongoDBConnector.datastore.createUpdateOperations(Topic.class);
		if(topic.getAuth().getLoginVisible()!=null)
		{
			ops.set("auth.loginVisible", topic.getAuth().getLoginVisible());
		}
		if(topic.getAuth().getLv()!=null)
		{
			ops.set("auth.lv", topic.getAuth().getLv());
		}
		if(topic.getAuth().getVisible()!=null)
		{
			ops.set("auth.visible", topic.getAuth().getVisible());
		}
		if(topic.getAuth().getCreate()!=null)
		{
			ops.set("auth.create", topic.getAuth().getCreate());
		}
		if(topic.getAuth().getDelete()!=null)
		{
			ops.set("auth.delete", topic.getAuth().getDelete());
		}
		if(topic.getAuth().getModify()!=null)
		{
			ops.set("auth.modify", topic.getAuth().getModify());
		}
		if(topic.getAuth().getFind()!=null)
		{
			ops.set("auth.find", topic.getAuth().getFind());
		}
		UpdateResults re =MongoDBConnector.datastore.update(updateQuery, ops);
		return re.getUpdatedCount();
	}
}
