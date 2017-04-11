package blog.dao;

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
}
