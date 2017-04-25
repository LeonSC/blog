package blog.model;

import org.mongodb.morphia.annotations.Entity;

@Entity("reply")
public class Reply extends BaseModel{

	private String okey;//对应content的ID
	private String write;
	private String username;
	private String nickname;
	private String headericon;
	private String content;
	
	
	public String getWrite() {
		return write;
	}
	public void setWrite(String write) {
		this.write = write;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadericon() {
		return headericon;
	}
	public void setHeadericon(String headericon) {
		this.headericon = headericon;
	}
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
}
