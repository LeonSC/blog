package blog.model;

import org.mongodb.morphia.annotations.Entity;

@Entity("depositcard")
public class DepositCard extends BaseModel {

	private String okey = "";//对应depositTopic的BMID
	private String uuid = "";
	private Integer price = 1; //金额, 基于元
	public String getOkey() {
		return okey;
	}
	public void setOkey(String okey) {
		this.okey = okey;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
}
