package blog.component;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import blog.dao.SettingDao;
import blog.model.Setting;
import blog.startup.Checker;

@Component
public class QiniuComponent {

	@Autowired
	private SettingDao settingDao;

	public int saveSetting(String accesskey, String secretkey, String bucket, String link, Integer onoff) {
		Setting setting = new Setting();
		setting.setQiniuAccessKey(accesskey);
		setting.setQiniuSecretKey(secretkey);
		setting.setQiniuBucket(bucket);
		setting.setQiniuLink(link);
		setting.setQiniuOnOff(onoff);
		this.settingDao.save(setting);
		Checker.setting = this.settingDao.getSetting();
		this.test();
		return 0;
	}

	public Setting getSetting() {
		return this.settingDao.getSetting();
	}

	private String qiniu(byte[] uploadBytes) throws QiniuException {
		if (Checker.setting == null || Checker.setting.getQiniuOnOff() == 0) {
			Checker.qiniuChecker = 0;
			return "";
		}
		Auth auth = Auth.create(Checker.setting.getQiniuAccessKey(), Checker.setting.getQiniuSecretKey());
		String upToken = auth.uploadToken(Checker.setting.getQiniuBucket());
		// 默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = null;
		// 构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone0());
		// ...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);
		Response response = uploadManager.put(byteInputStream, key, upToken, null, null);
		// 解析上传成功的结果
		DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		if (putRet.hash.startsWith("{\"error\":")) {
			return putRet.hash;
		}
		return putRet.key;
	}

	/**
	 * 测试上传接口
	 * 
	 * @return
	 */
	public String test() {
		try {
			byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
			this.qiniu(uploadBytes);
			Checker.qiniuChecker = 1;
		} catch (QiniuException e) {
			Checker.qiniuChecker = 0;
		} catch (UnsupportedEncodingException e) {
			Checker.qiniuChecker = 0;
		}
		return "";
	}

	/**
	 * 对外上传接口
	 * 
	 * @param uploadBytes
	 * @return
	 */
	public String upload(byte[] uploadBytes) {
		try {
			return this.qiniu(uploadBytes);
		} catch (QiniuException ex) {
			Checker.qiniuChecker = 0;
			Response r = ex.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException ex2) {
				// ignore
			}
		}
		return "";
	}
}
