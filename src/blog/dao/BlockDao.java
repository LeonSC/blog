package blog.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.stereotype.Repository;

import blog.model.Block;
import blog.startup.MongoDBConnector;
import blog.startup.Tools;

@Repository
public class BlockDao {

	/**
	 * 保存
	 * @param b
	 * @return
	 */
	public Block save(Block b) {
		b.setBM_ID(Tools.getID("f"));
		MongoDBConnector.datastore.save(b);
		ObjectId id = b.getId();
		return MongoDBConnector.datastore.get(Block.class, id);
	}
	
	/**
	 * 修改
	 * @param block
	 * @return
	 */
	public int update(Block block) {
		Query<Block> updateQuery = MongoDBConnector.datastore.createQuery(Block.class).field("BM_ID").equal(block.getBM_ID());
		UpdateOperations<Block> ops = MongoDBConnector.datastore.createUpdateOperations(Block.class);
		if (block.getName() != null && !block.getName().equals("")) {
			ops.set("name", block.getName());
		}
		if (block.getIcon() != null && !block.getIcon().equals("")) {
			ops.set("icon", block.getIcon());
		}
		if (block.getOrder() != null && !block.getOrder().equals("")) {
			ops.set("order", block.getOrder());
		}
		if (block.getIntro() != null && !block.getIntro().equals("")) {
			ops.set("intro", block.getIntro());
		}
		UpdateResults re = MongoDBConnector.datastore.updateFirst(updateQuery, ops);
		return re.getUpdatedCount();
	}
}
