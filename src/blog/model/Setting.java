package blog.model;

import org.mongodb.morphia.annotations.Entity;

@Entity("setting")
public class Setting extends BaseModel {

	private String qiniuAccessKey = "";
	private String qiniuSecretKey = "";
	private String qiniuBucket = "";
	private Integer qiniuOnOff = 0; //0关闭, 1开启, 默认关闭
	
	public String getQiniuAccessKey() {
		return qiniuAccessKey;
	}
	public void setQiniuAccessKey(String qiniuAccessKey) {
		this.qiniuAccessKey = qiniuAccessKey;
	}
	public String getQiniuSecretKey() {
		return qiniuSecretKey;
	}
	public void setQiniuSecretKey(String qiniuSecretKey) {
		this.qiniuSecretKey = qiniuSecretKey;
	}
	public String getQiniuBucket() {
		return qiniuBucket;
	}
	public void setQiniuBucket(String qiniuBucket) {
		this.qiniuBucket = qiniuBucket;
	}
	public Integer getQiniuOnOff() {
		return qiniuOnOff;
	}
	public void setQiniuOnOff(Integer qiniuOnOff) {
		this.qiniuOnOff = qiniuOnOff;
	}
}
