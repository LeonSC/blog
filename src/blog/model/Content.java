package blog.model;

import java.util.HashMap;
import java.util.Map;

import org.mongodb.morphia.annotations.Entity;

@Entity("content")
public class Content extends BaseModel {

	private String topic;//标识属于哪个topic
	
	private String title;
	private String intro;
	private String content;
	private String cover;
	
	private Auth need =new Auth();
	
	private User user;
	
	private Integer replyCount = 0;

	/**
	 * 0 正常
	 * long 置顶, 置顶时间, 用于排序
	 */
	private Long top = 0L;
	
	/**
	 * 售卖价格, 0, 表示免费
	 */
	private Integer price = 0;
	/**
	 * String 付款人BMID
	 * INTEGER 付款多少.
	 * 支持打赏
	 */
	private Map<String,Integer> payer = new HashMap<>();
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Auth getNeed() {
		return need;
	}

	public void setNeed(Auth need) {
		this.need = need;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}
	public Long getTop() {
		return top;
	}

	public void setTop(Long top) {
		this.top = top;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Map<String,Integer> getPayer() {
		return payer;
	}

	public void setPayer(Map<String,Integer> payer) {
		this.payer = payer;
	}
	
}
