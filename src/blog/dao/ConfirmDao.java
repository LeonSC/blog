package blog.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Repository;

import blog.model.Confirm;
import blog.startup.MongoDBConnector;

@Repository
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
	
	/**
	 * 使用EMAIL进行查找
	 * @param email
	 * @return
	 */
	public Confirm findConfirmByEmail(String email)
	{
		return MongoDBConnector.datastore.createQuery(Confirm.class).field("email").equal(email).get();
	}
	
	/**
	 * 使用UserId进行查找
	 * @param email
	 * @return
	 */
	public Confirm findConfirmByCode(String code)
	{
		return MongoDBConnector.datastore.createQuery(Confirm.class).field("code").equal(code).get();
	}
	
	/**
	 * 
	 * @param user
	 * @param email
	 * @param code
	 * @return 0, 没有查到, 1, 删除
	 */
	public int deleteConfirmByCode(String bmid)
	{
		Query<Confirm> query=MongoDBConnector.datastore.createQuery(Confirm.class).field("BM_ID").equal(bmid);
		int re = MongoDBConnector.datastore.delete(query).getN();
		return re;
	}
}
