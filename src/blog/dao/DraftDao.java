package blog.dao;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import blog.model.Draft;
import blog.startup.MongoDBConnector;


@Repository
public class DraftDao {

	/**
	 * 
	 * @param draft
	 * @return
	 */
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
	
	/**
	 * 
	 * @param bmid
	 * @return
	 */
	public Draft getDraftByUser(String bmid)
	{
		if(bmid==null)
		{
			return null;
		}
		Draft d = MongoDBConnector.datastore.createQuery(Draft.class).field("write").equal(bmid).get();
		return d;
	}
}
