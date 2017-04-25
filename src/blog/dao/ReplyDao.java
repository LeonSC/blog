package blog.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.FindOptions;
import org.springframework.stereotype.Repository;

import blog.model.Reply;
import blog.startup.MongoDBConnector;

@Repository
public class ReplyDao {

	public Reply save(Reply reply)
	{
		if(reply==null||reply.getWrite()==null||reply.getContent()==null)
		{
			return null;
		}
		MongoDBConnector.datastore.save(reply);
		ObjectId id = reply.getId();
		return MongoDBConnector.datastore.get(Reply.class,id);
	}
	
	/**
	 * 
	 * @param okey
	 * @return
	 */
	public List<Reply> getReplyList(String okey)
	{
		List<Reply> list = MongoDBConnector.datastore.createQuery(Reply.class).field("okey").equal(okey).order("-BM_TIME").asList(new FindOptions().limit(15));
		return list;
	}
}
