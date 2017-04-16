package blog.model;

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
	
}