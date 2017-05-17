package blog.dao;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.stereotype.Repository;

import blog.model.Content;
import blog.model.Page;
import blog.model.Payment;
import blog.startup.MongoDBConnector;

@Repository
public class ContentDao {

	/**
	 * 保存
	 * @param c
	 * @return
	 */
	public Content save(Content c)
	{
		MongoDBConnector.datastore.save(c);
		ObjectId id = c.getId();
		return MongoDBConnector.datastore.get(Content.class,id);
	}
	
	/**
	 * 通过topic查找列表
	 * @param topic
	 * @return
	 */
	public Page<Content> getContentListByTopic(String topic, int nowPage, int numInPage)
	{
		Query<Content> query = MongoDBConnector.datastore.createQuery(Content.class).field("BM_DEL").equal(0).field("topic").equal(topic);
		Page<Content> page = new Page<>();
		page.setTotal(query.count());
		page.setNowPage(nowPage);
		page.setTotalInPage(numInPage);
		page.getPage();
		List<Content> list = query.order("-BM_TIME").asList(new FindOptions().skip(page.getSkip()).limit(page.getTotalInPage()));
		page.setList(list);
		return page;
	}
	
	/**
	 * 根据ID找文章
	 * @param bmid
	 * @return
	 */
	public Content getContentByBMID(String bmid)
	{
		Content c = MongoDBConnector.datastore.createQuery(Content.class).field("BM_DEL").equal(0).field("BM_ID").equal(bmid).get();
		return c;
	}
	
	/**
	 * 内容回复+1
	 * @param bmid
	 * @return
	 */
	public int incContentReplyOne(String bmid)
	{
		Query<Content> updateQuery = MongoDBConnector.datastore.createQuery(Content.class).field("BM_DEL").equal(0).field("BM_ID").equal(bmid);
		UpdateOperations<Content> ops=MongoDBConnector.datastore.createUpdateOperations(Content.class);
		ops.inc("replyCount");
		MongoDBConnector.datastore.update(updateQuery, ops);
		return 0;
	}
	
	/**
	 * 设置置顶
	 * @param bmid
	 * @param top
	 * @return
	 */
	public int updateContentTop(String bmid, long top)
	{
		Query<Content> updateQuery = MongoDBConnector.datastore.createQuery(Content.class).field("BM_DEL").equal(0).field("BM_ID").equal(bmid);
		UpdateOperations<Content> ops=MongoDBConnector.datastore.createUpdateOperations(Content.class);
		ops.set("top",top);
		MongoDBConnector.datastore.update(updateQuery, ops);
		return 0;
	}
	
	/**
	 * 更新内容
	 * @param bmid
	 * @param content
	 * @param intro
	 * @param cover
	 * @return
	 */
	public int updateContentByBMID(Content c)
	{
		if(c==null)
		{
			return -1;
		}
		Query<Content> updateQuery = MongoDBConnector.datastore.createQuery(Content.class).field("BM_DEL").equal(0).field("BM_ID").equal(c.getBM_ID());
		UpdateOperations<Content> ops=MongoDBConnector.datastore.createUpdateOperations(Content.class);
		ops.set("intro",c.getIntro());
		ops.set("cover",c.getCover());
		ops.set("content",c.getContent());
		MongoDBConnector.datastore.update(updateQuery, ops);
		return 0;
	}
	
	/**
	 * 查询置顶帖子
	 * @param topic
	 * @return
	 */
	public List<Content> getContentTopListByTopic(String topic)
	{
		List<Content> list = MongoDBConnector.datastore.createQuery(Content.class).field("BM_DEL").equal(0).field("topic").equal(topic).field("top").exists().field("top").notEqual(0L).order("-top").asList(new FindOptions().limit(3));
		return list;
	}
	
	/**
	 * 将支付情况存入到内容中
	 * @param bmid
	 * @param payer
	 * @param payment
	 * @return
	 */
	public int addPayerToContent(String bmid, Map<String, Payment> payment)
	{
		Query<Content> updateQuery = MongoDBConnector.datastore.createQuery(Content.class).field("BM_ID").equal(bmid);
		UpdateOperations<Content> ops=MongoDBConnector.datastore.createUpdateOperations(Content.class);
		ops.set("payer",payment);
		UpdateResults re = MongoDBConnector.datastore.update(updateQuery, ops);
		return re.getUpdatedCount();
	}
	
	/**
	 * 伪删除
	 * @param bmid
	 * @return
	 */
	public int deleteContent(String bmid)
	{
		Query<Content> updateQuery = MongoDBConnector.datastore.createQuery(Content.class).field("BM_ID").equal(bmid);
		UpdateOperations<Content> ops=MongoDBConnector.datastore.createUpdateOperations(Content.class);
		ops.set("BM_DEL",1);
		UpdateResults re = MongoDBConnector.datastore.update(updateQuery, ops);
		return re.getUpdatedCount();
	}
	
	/**
	 * 删除恢复
	 * @param bmid
	 * @return
	 */
	public int redoDeleteContent(String bmid)
	{
		Query<Content> updateQuery = MongoDBConnector.datastore.createQuery(Content.class).field("BM_ID").equal(bmid);
		UpdateOperations<Content> ops=MongoDBConnector.datastore.createUpdateOperations(Content.class);
		ops.set("BM_DEL",0);
		UpdateResults re = MongoDBConnector.datastore.update(updateQuery, ops);
		return re.getUpdatedCount();
	}
}
