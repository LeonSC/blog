package blog.dao;

import org.bson.types.ObjectId;

import blog.model.Confirm;
import blog.startup.MongoDBConnector;

public class ConfirmDao {

	/**
	 * 
	 * @param confirm
	 * @return
	 */
	public Confirm save(Confirm confirm)
	{
		MongoDBConnector.datastore.save(confirm);
		ObjectId id = confirm.getId();
		return MongoDBConnector.datastore.get(Confirm.class,id);
	}
	
	public Confirm findConfirmByEmail(String email)
	{
		return MongoDBConnector.datastore.createQuery(Confirm.class).field("email").equal(email).get();
	}
	
}
