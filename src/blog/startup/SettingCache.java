package blog.startup;

import blog.model.Setting;

public class SettingCache {

	private static SettingCache settingCache;

	private SettingCache() {
	}

	public static SettingCache getSettingCache() {
		if(SettingCache.settingCache == null)
		{
			SettingCache.settingCache = new SettingCache();
			SettingCache.setting = MongoDBConnector.datastore.createQuery(Setting.class).get();
		}
		return SettingCache.settingCache;
	}

	public static int qiniuChecker = 0;// 0不能使用,1能使用

	public int getQiniuChecker() {
		return qiniuChecker;
	}

	public static Setting setting = null;

	public Setting getSetting() {
		return setting;
	}
}
