package blog.dao;

import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import blog.model.Setting;
import blog.startup.MongoDBConnector;

public class SettingDao {

	/**
	 * 保存setting
	 * @param setting
	 * @return
	 */
	public int save(Setting setting)
	{
		if(setting==null)
		{
			return -1;
		}
		Query<Setting> updateQuery = MongoDBConnector.datastore.createQuery(Setting.class);
		UpdateOperations<Setting> ops=MongoDBConnector.datastore.createUpdateOperations(Setting.class);
		if(setting.getQiniuAccessKey()!=null)
		{
			ops.set("qiniuAccessKey",setting.getQiniuAccessKey());
		}
		if(setting.getQiniuSecretKey()!=null)
		{
			ops.set("qiniuSecretKey",setting.getQiniuSecretKey());
		}
		if(setting.getQiniuBucket()!=null)
		{
			ops.set("qiniuBucket",setting.getQiniuBucket());
		}
		ops.set("qiniuOnOff",setting.getQiniuOnOff()==null?0:setting.getQiniuOnOff());
		MongoDBConnector.datastore.updateFirst(updateQuery, ops, true);
		return 0;
	}
}
