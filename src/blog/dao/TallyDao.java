package blog.dao;

import org.mongodb.morphia.query.Query;

import static org.mongodb.morphia.aggregation.Group.grouping;
import static org.mongodb.morphia.aggregation.Accumulator.accumulator;
import static org.mongodb.morphia.aggregation.Projection.projection;
import static org.mongodb.morphia.query.Sort.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.mongodb.morphia.aggregation.Group.*;
import org.springframework.stereotype.Repository;

import blog.model.Content;
import blog.model.Tally;
import blog.startup.Config;
import blog.startup.MongoDBConnector;
import blog.startup.Tools;

@Repository
public class TallyDao {

	/**
	 * 用户BMID, 开始时间, 截至时间, 计数
	 * 
	 * @param user
	 * @param start
	 * @param end
	 * @return
	 */
	public Long tally(String user, Long start, Long end) {
		if (start > end) {
			return null;
		}
		Query<Content> query = MongoDBConnector.datastore.createQuery(Content.class);
		if (user != null) {
			query.field("user.BM_ID").equal(user);
		}
		Long re = query.order("-BM_TIME").field("BM_TIME").greaterThanOrEq(start).field("BM_TIME").lessThan(end)
				.count();
		return re;
	}
	
	/**
	 * 
	 * @param timeStr 格式必须为20170515
	 * @return
	 */
	public Map<String,Tally> lastNDays(String timeStr)
	{
		List<Tally> list = MongoDBConnector.datastore.createQuery(Tally.class).field("userid").equal("").field("dateString").greaterThanOrEq(timeStr).order("dateString").asList();
		Map<String,Tally> map = new HashMap<>();
		for(Tally t : list)
		{
			map.put(t.getDateString(), t);
		}
		return map;
	}
	
	/**
	 * 
	 * @param timeStr 格式必须为20170515
	 * @param userid 用户BMID
	 * @return
	 */
	public Map<String,Tally> lastNDays(String timeStr, String userid)
	{
		List<Tally> list = MongoDBConnector.datastore.createQuery(Tally.class).field("userid").equal(userid).field("dateString").greaterThanOrEq(timeStr).order("dateString").asList();
		Map<String,Tally> map = new HashMap<>();
		for(Tally t : list)
		{
			map.put(t.getDateString(), t);
		}
		return map;
	}
	
	public static void main(String[] args) {
		Config.getConfig();
		MongoDBConnector.getMongoDBConnector();

		Calendar start = Calendar.getInstance();
		start.add(Calendar.DATE, -1);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		System.out.println(start.getTime());

		Calendar end = Calendar.getInstance();

		end.set(Calendar.HOUR_OF_DAY, 0);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		System.out.println(end.getTime());

		Query<Content> m = MongoDBConnector.datastore.createQuery(Content.class)
				.field("BM_TIME").lessThan(end.getTimeInMillis());

		Iterator<Tally> aggregate = MongoDBConnector.datastore.createAggregation(Content.class)
				.sort(descending("BM_TIME")).match(m)
				.project(projection("bmid", "user.BM_ID"), projection("date", "date"))
				.group(id(grouping("bmid"), grouping("year", accumulator("$year", "date")),
						grouping("month", accumulator("$month", "date")),
						grouping("day", accumulator("$dayOfMonth", "date"))), grouping("count", accumulator("$sum", 1)),
						grouping("userid", first("bmid")), grouping("date", first("date")))
				.project(projection("_id").suppress()).aggregate(Tally.class);

		while (aggregate.hasNext()) {
			Tally t = aggregate.next();
			t.setDateString(Tools.dateTransDateyyyyMMddWithoutMinus(t.getDate()));
			MongoDBConnector.datastore.save(t);
		}
		
		aggregate = MongoDBConnector.datastore.createAggregation(Content.class)
				.sort(descending("BM_TIME")).match(m)
				.project(projection("date", "date"))
				.group(id(grouping("year", accumulator("$year", "date")),
						grouping("month", accumulator("$month", "date")),
						grouping("day", accumulator("$dayOfMonth", "date"))), grouping("count", accumulator("$sum", 1)),
						grouping("userid", first("bmid")), grouping("date", first("date")))
				.project(projection("_id").suppress()).aggregate(Tally.class);

		while (aggregate.hasNext()) {
			Tally t = aggregate.next();
			t.setUserid("");
			t.setDateString(Tools.dateTransDateyyyyMMddWithoutMinus(t.getDate()));
			MongoDBConnector.datastore.save(t);
		}
	}
}
