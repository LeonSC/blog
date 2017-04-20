package blog.model;

import org.mongodb.morphia.annotations.Entity;

@Entity("notice")
public class Notice extends BaseModel {

	private String bar = "";//标签字
	private String title="";
	private String notice="";
	private String link="";
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getBar() {
		return bar;
	}
	public void setBar(String bar) {
		this.bar = bar;
	}
}
