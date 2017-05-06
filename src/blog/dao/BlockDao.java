package blog.dao;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import blog.model.Block;
import blog.startup.MongoDBConnector;

@Repository
public class BlockDao {

	/**
	 * 保存
	 * @param c
	 * @return
	 */
	public Block save(Block b)
	{
		MongoDBConnector.datastore.save(b);
		ObjectId id = b.getId();
		return MongoDBConnector.datastore.get(Block.class,id);
	}
}
