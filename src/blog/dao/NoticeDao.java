package blog.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Repository;

import blog.model.Notice;
import blog.startup.MongoDBConnector;

@Repository
public class NoticeDao {

	public Notice save(Notice notice)
	{
		if(notice==null||notice.getNotice()==null||notice.getNotice().isEmpty()||notice.getNotice().length()>300)
		{
			return null;
		}
		if(notice.getTitle()!=null&&notice.getTitle().length()>15)
		{
			notice.setTitle(notice.getTitle().substring(0,15));
		}
		if(notice.getNotice().length()>150)
		{
			notice.setNotice(notice.getNotice().substring(0,150));
		}
		if(notice.getBar()==null||notice.getBar().isEmpty())
		{
			String bar = "";
			if(notice.getTitle()==null||notice.getTitle().isEmpty())
			{
				bar = notice.getNotice().substring(0,3)+"..";
			}
			else
			{
				if(notice.getTitle().length()>4)
				{
					bar = notice.getTitle().substring(0,3)+"..";
				}
				else
				{
					bar = notice.getTitle();
				}
			}
			notice.setBar(bar);
		}
		MongoDBConnector.datastore.save(notice);
		ObjectId id = notice.getId();
		return MongoDBConnector.datastore.get(Notice.class,id);
	}
	
	
	public int deleteByBMID(String bmid)
	{
		if(bmid==null)
		{
			return 0;
		}
		Query<Notice> query=MongoDBConnector.datastore.createQuery(Notice.class).field("BM_ID").equal(bmid);
		MongoDBConnector.datastore.delete(query);
		return 0;
	}
	
	public List<Notice> findNoticeList(int size)
	{
		return MongoDBConnector.datastore.find(Notice.class).order("-BM_TIME").asList(new FindOptions().limit(size));
	}
}
