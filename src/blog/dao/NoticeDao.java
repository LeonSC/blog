package blog.dao;

import org.bson.types.ObjectId;
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
			notice.setBar(notice.getTitle()==null?notice.getNotice().substring(0,3)+"..":(notice.getTitle().length()>4?notice.getTitle().substring(0,3)+"..":notice.getTitle()));
		}
		MongoDBConnector.datastore.save(notice);
		ObjectId id = notice.getId();
		return MongoDBConnector.datastore.get(Notice.class,id);
	}
}
