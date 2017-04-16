package blog.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import blog.model.Content;
import blog.startup.MongoDBConnector;

@Repository
public class ContentDao {

	public Content save(Content c)
	{
		MongoDBConnector.datastore.save(c);
		ObjectId id = c.getId();
		return MongoDBConnector.datastore.get(Content.class,id);
	}
	
	/**
	 * 通过topic查找列表
	 * @param topic
	 * @return
	 */
	public List<Content> getContentListByTopic(String topic)
	{
		List<Content> list = MongoDBConnector.datastore.createQuery(Content.class).field("topic").equal(topic).asList();
		return list;
	}
}
