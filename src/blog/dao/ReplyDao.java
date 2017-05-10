package blog.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Repository;

import blog.model.Page;
import blog.model.Reply;
import blog.startup.MongoDBConnector;

@Repository
public class ReplyDao {

	public Reply save(Reply reply)
	{
		if(reply==null||reply.getUser()==null||reply.getContent()==null)
		{
			return null;
		}
		MongoDBConnector.datastore.save(reply);
		ObjectId id = reply.getId();
		return MongoDBConnector.datastore.get(Reply.class,id);
	}
	
	/**
	 * 获取评论列表
	 * @param okey
	 * @return
	 */
	public Page<Reply> getReplyList(String okey, int nowPage, int numInPage)
	{
		Query<Reply> query = MongoDBConnector.datastore.createQuery(Reply.class).field("okey").equal(okey);
		Page<Reply> page = new Page<>();
		page.setTotal(query.count());
		page.setNowPage(nowPage);
		page.setTotalInPage(numInPage);
		page.getPage();
		List<Reply> list = query.order("BM_TIME").asList(new FindOptions().skip(page.getSkip()).limit(page.getTotalInPage()));
		page.setList(list);
		return page;
	}
}
