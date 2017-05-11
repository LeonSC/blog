package blog.dao;

import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Repository;

import blog.model.Content;
import blog.startup.MongoDBConnector;
@Repository
public class TallyDao {

	/**
	 * 用户BMID, 开始时间, 截至时间, 计数
	 * @param user
	 * @param start
	 * @param end
	 * @return
	 */
	public Long tally(String user, Long start, Long end)
	{
		if(start>end)
		{
			return null;
		}
		Query<Content> query = MongoDBConnector.datastore.createQuery(Content.class);
		if(user!=null)
		{
			query.field("user.BM_ID").equal(user);
		}
		Long re = query.order("-BM_TIME").field("BM_TIME").greaterThanOrEq(start).field("BM_TIME").lessThan(end).count();
		return re;
	}
}
