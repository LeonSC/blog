package blog.model;

import org.mongodb.morphia.annotations.Entity;

@Entity("carousel")
public class Carousel extends BaseModel {

	private String img = "";
	private String link = "";
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
