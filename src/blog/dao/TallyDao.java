package blog.dao;

import org.mongodb.morphia.query.Query;
import static org.mongodb.morphia.aggregation.Group.grouping;
import static org.mongodb.morphia.aggregation.Accumulator.accumulator;
import static org.mongodb.morphia.aggregation.Projection.projection;

import java.util.Iterator;

import static org.mongodb.morphia.aggregation.Group.*;
import org.springframework.stereotype.Repository;

import blog.model.Content;
import blog.model.Tally;
import blog.startup.Config;
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
	
	public static void main(String[] args) {
		Config.getConfig();
		MongoDBConnector.getMongoDBConnector();
		Iterator<Tally> aggregate = MongoDBConnector.datastore.createAggregation(Content.class)
			      .group("user.BM_ID", grouping("count", accumulator("$sum", 1)), grouping("userid", first("user.BM_ID")))
			      .project(projection("_id").suppress())
			      .out(Tally.class);
		
		while(aggregate.hasNext())
		{
			Tally t = aggregate.next();
			System.out.println(t.getCount());
		}
	}
}
