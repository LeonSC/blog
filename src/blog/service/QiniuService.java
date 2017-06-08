package blog.service;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class QiniuService {

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
		return 0;
	}

	public Setting getSetting() {
		return this.settingDao.getSetting();
	}

	public int upload(byte[] uploadBytes) {
		Setting s = this.getSetting();
		if (s == null || s.getQiniuOnOff() == 0) {
			return -1;
		}
		Auth auth = Auth.create(s.getQiniuAccessKey(), s.getQiniuSecretKey());
		String upToken = auth.uploadToken(s.getQiniuBucket());
		// 默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = null;
		// 构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone0());
		// ...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);
		try {
			Response response = uploadManager.put(byteInputStream, key, upToken, null, null);
			// 解析上传成功的结果
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			System.out.println(putRet.key);
			System.out.println(putRet.hash);
		} catch (QiniuException ex) {
			Response r = ex.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException ex2) {
				// ignore
			}
		}
		return 0;
	}

	public String test() {
		Setting s = this.getSetting();
		Auth auth = Auth.create(s.getQiniuAccessKey(), s.getQiniuSecretKey());
		String upToken = auth.uploadToken(s.getQiniuBucket());
		// 默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = null;
		// 构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone0());
		// ...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		try {
			byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
			ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);
			try {
				Response response = uploadManager.put(byteInputStream, key, upToken, null, null);
				// 解析上传成功的结果
				DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
				if(putRet.hash.startsWith("{\"error\":"))
				{
					return putRet.hash;
				}
				return "{\"success\":\"0\"}";
			} catch (QiniuException ex) {
				Response r = ex.response;
				System.err.println(r.toString());
				try {
					System.err.println(r.bodyString());
				} catch (QiniuException ex2) {
					// ignore
				}
			}
		} catch (UnsupportedEncodingException ex) {
			// ignore
		}
		return "0";
	}
}
