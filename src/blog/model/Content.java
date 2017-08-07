package blog.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mongodb.morphia.annotations.Entity;

@Entity("content")
public class Content extends BaseModel {

	private String topic;//标识属于哪个topic, topic的bmid
	
	private String title;
	private String intro;
	private String content;
	private String cover;
	
	private Auth need =new Auth();
	
	private User user;//作者
	
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
	 * UserPayment 付款信息.
	 */
	private Map<String,Payment> payer = new HashMap<>();
	/**
	 * 打赏内容
	 */
	private Map<String, Payment> reward = new HashMap<>();
	/**
	 * ISODATE
	 */
	private Date date = new Date();
	/**
	 * 在评论中需要过滤掉的字符串
	 */
	private List<String> wipeStr = null;
	

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

	public Map<String,Payment> getPayer() {
		return payer;
	}

	public void setPayer(Map<String,Payment> payer) {
		this.payer = payer;
	}

	public Map<String, Payment> getReward() {
		return reward;
	}

	public void setReward(Map<String, Payment> reward) {
		this.reward = reward;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> getWipeStr() {
		return wipeStr;
	}

	public void setWipeStr(List<String> wipeStr) {
		this.wipeStr = wipeStr;
	}
	
}
