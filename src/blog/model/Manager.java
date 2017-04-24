package blog.model;

import org.mongodb.morphia.annotations.NotSaved;

@NotSaved
public class Manager {

	private String username = "";
	private String BM_ID = "";
	private Auth auth = new Auth();
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBM_ID() {
		return BM_ID;
	}
	public void setBM_ID(String bM_ID) {
		BM_ID = bM_ID;
	}
	public Auth getAuth() {
		return auth;
	}
	public void setAuth(Auth auth) {
		this.auth = auth;
	}
}
