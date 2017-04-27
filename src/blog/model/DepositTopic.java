package blog.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.NotSaved;

@Entity("deposittopic")
public class DepositTopic extends BaseModel {

	private String title = "";//比如充100送20
	private Integer count = 10;//发卡张数
	private Integer price = 1;//基于元
	private Integer frequency=0;//生成次数
	@NotSaved
	private long rest = 0;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	public long getRest() {
		return rest;
	}
	public void setRest(long rest) {
		this.rest = rest;
	}
}
