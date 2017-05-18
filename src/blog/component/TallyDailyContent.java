package blog.component;

import static org.mongodb.morphia.aggregation.Accumulator.accumulator;
import static org.mongodb.morphia.aggregation.Group.first;
import static org.mongodb.morphia.aggregation.Group.grouping;
import static org.mongodb.morphia.aggregation.Group.id;
import static org.mongodb.morphia.aggregation.Projection.projection;
import static org.mongodb.morphia.query.Sort.descending;

import java.util.Calendar;
import java.util.Iterator;

import org.mongodb.morphia.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import blog.model.Content;
import blog.model.Tally;
import blog.startup.Config;
import blog.startup.MongoDBConnector;
import blog.startup.Tools;


@Component
public class TallyDailyContent {

	@Scheduled(cron = "0 0 3 * * ?")
	public void refreshTallyDailyContent() {
		Calendar start = Calendar.getInstance();
		start.add(Calendar.DATE, -1);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		Calendar end = Calendar.getInstance();
		end.set(Calendar.HOUR_OF_DAY, 0);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		System.out.println("统计从 "+start.getTime()+" 到 "+end.getTime() +" 数据");
		Query<Content> m = MongoDBConnector.datastore.createQuery(Content.class).field("BM_TIME").greaterThanOrEq(start.getTimeInMillis())
				.field("BM_TIME").lessThan(end.getTimeInMillis());
		//统计当日以用户角度统计
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
		//统计当日总量
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
	
	public static void main(String[] args) {
		Config.getConfig();
		MongoDBConnector.getMongoDBConnector();
		
		TallyDailyContent tdc = new TallyDailyContent();
		tdc.refreshTallyDailyContent();
	}
}
