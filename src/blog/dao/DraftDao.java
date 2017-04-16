package blog.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.stereotype.Repository;

import blog.model.Draft;
import blog.startup.MongoDBConnector;
import blog.startup.Tools;


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
		draft.setLastUpdate(Tools.getServerTime());
		MongoDBConnector.datastore.save(draft);
		ObjectId id = draft.getId();
		return MongoDBConnector.datastore.get(Draft.class,id);
	}
	
	/**
	 * 更新或者保存
	 * @param draft
	 * @return
	 */
	public Draft saveOrUpdate(Draft draft)
	{
		if(draft==null||draft.getWrite()==null||draft.getTitle()==null||draft.getContent()==null)
		{
			return null;
		}
		
		Query<Draft> updateQuery = MongoDBConnector.datastore.createQuery(Draft.class).field("write").equal(draft.getWrite());
		
		UpdateOperations<Draft> ops=MongoDBConnector.datastore.createUpdateOperations(Draft.class);
		
		ops.set("title", draft.getTitle());
		ops.set("intro", draft.getIntro());
		ops.set("content", draft.getContent());
		ops.set("cover", draft.getCover());
		ops.set("lastUpdate", Tools.getServerTime());
		
		MongoDBConnector.datastore.update(updateQuery, ops,true);
		
		return MongoDBConnector.datastore.createQuery(Draft.class).field("write").equal(draft.getWrite()).get();
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
	
	/**
	 * 根据一个用户删除一个草稿
	 * @param bmid
	 * @return
	 */
	public int deleteDraftByUser(String bmid)
	{
		if(bmid==null)
		{
			return 0;
		}
		Query<Draft> query=MongoDBConnector.datastore.createQuery(Draft.class).field("write").equal(bmid);
		MongoDBConnector.datastore.delete(query);
		return 0;
	}
}
