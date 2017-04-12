package blog.dao;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import blog.model.Draft;
import blog.startup.MongoDBConnector;


@Repository
public class DraftDao {

	public Draft save(Draft draft)
	{
		if(draft==null||draft.getWrite()==null||draft.getTitle()==null||draft.getContent()==null)
		{
			return null;
		}
		
		MongoDBConnector.datastore.save(draft);
		ObjectId id = draft.getId();
		return MongoDBConnector.datastore.get(Draft.class,id);
	}
}
