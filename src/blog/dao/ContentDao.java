package blog.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.FindOptions;
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
		List<Content> list = MongoDBConnector.datastore.createQuery(Content.class).field("topic").equal(topic).order("-BM_TIME").asList(new FindOptions().limit(15));
		return list;
	}
	
	/**
	 * 更具ID找文章
	 * @param bmid
	 * @return
	 */
	public Content getContentByBMID(String bmid)
	{
		Content c = MongoDBConnector.datastore.createQuery(Content.class).field("BM_ID").equal(bmid).get();
		return c;
	}
}
