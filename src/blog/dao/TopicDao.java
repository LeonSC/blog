package blog.dao;

import java.util.List;

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
}
