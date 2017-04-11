package blog.model;

import org.mongodb.morphia.annotations.NotSaved;

/**
 * Admin is up on the user. Admin is a role mark it has this right. User is the bean.
 * @author sasgsc
 */
@NotSaved
public class Auth {

	private Integer lv=0;
	
	private String password;

	private Integer visible=0;//0为通用
	
	private Integer create=10;
	private Integer delete=100;
	private Integer modify=100;
	private Integer find=10;
	
	public Integer getVisible() {
		return visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	public Integer getCreate() {
		return create;
	}

	public void setCreate(Integer create) {
		this.create = create;
	}

	public Integer getDelete() {
		return delete;
	}

	public void setDelete(Integer delete) {
		this.delete = delete;
	}

	public Integer getModify() {
		return modify;
	}

	public void setModify(Integer modify) {
		this.modify = modify;
	}

	public Integer getFind() {
		return find;
	}

	public void setFind(Integer find) {
		this.find = find;
	}

	public Integer getLv() {
		return lv;
	}

	public void setLv(Integer lv) {
		this.lv = lv;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
