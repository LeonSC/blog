package blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.dao.SettingDao;
import blog.model.Setting;

@Service
public class QiniuService {

	@Autowired
	private SettingDao settingDao;

	public int saveSetting(String accesskey, String secretkey, String bucket, Integer onoff) {
		Setting setting = new Setting();
		setting.setQiniuAccessKey(accesskey);
		setting.setQiniuSecretKey(secretkey);
		setting.setQiniuBucket(bucket);
		setting.setQiniuOnOff(onoff);
		this.settingDao.save(setting);
		return 0;
	}

	public Setting getSetting() {
		return this.settingDao.getSetting();
	}
}
