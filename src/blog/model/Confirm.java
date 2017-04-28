package blog.model;

import org.mongodb.morphia.annotations.Entity;

@Entity("confirm")
public class Confirm extends BaseModel {

	private String user;//user bmid
	private String email;
	private String code;
	private Long until;
	
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getUntil() {
		return until;
	}
	public void setUntil(Long until) {
		this.until = until;
	}
}
