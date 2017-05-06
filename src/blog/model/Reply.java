package blog.model;

import org.mongodb.morphia.annotations.Entity;

@Entity("reply")
public class Reply extends BaseModel{

	private String okey;//对应content的ID
	private User user;
	private String content;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOkey() {
		return okey;
	}
	public void setOkey(String okey) {
		this.okey = okey;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
