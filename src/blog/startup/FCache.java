package blog.startup;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import blog.model.Block;

public class FCache {

	private static FCache fcache;

	private FCache() {
	}

	public static FCache getCache() {
		if (FCache.fcache == null) {
			FCache.fcache = new FCache();
			FCache.fcache.initBlockCache();
		}
		return FCache.fcache;
	}

	/**
	 * 顶级BLOCK列表, 底下包含子分类
	 */
	public LinkedHashMap<String, Block> block = null;
	public HashMap<String, Block> blockmap = null;

	/**
	 * 初始化列表,只支持两级
	 * @return
	 */
	public int initBlockCache() {
		if (FCache.fcache.block == null) {
			FCache.fcache.block = new LinkedHashMap<>();
		}
		FCache.fcache.block.clear();
		if (FCache.fcache.blockmap == null) {
			FCache.fcache.blockmap = new HashMap<>();
		}
		FCache.fcache.blockmap.clear();
		// 查询数据
		List<Block> blocklist = MongoDBConnector.datastore.find(Block.class).field("okey").equal("0").order("order").asList();
		for (Block block : blocklist) {
			LinkedHashMap<String, Block> tmp = new LinkedHashMap<>();
			List<Block> tmplist = MongoDBConnector.datastore.find(Block.class).field("okey").equal(block.getBM_ID()).order("order").asList();
			for (Block sub : tmplist) {
				tmp.put(sub.getBM_ID(), sub);
				FCache.fcache.blockmap.put(sub.getBM_ID(), sub);
			}
			block.setBlock(tmp);
			FCache.fcache.block.put(block.getBM_ID(), block);
			FCache.fcache.blockmap.put(block.getBM_ID(), block);
		}
		return 0;
	}

}
