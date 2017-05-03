package blog.model;

import org.mongodb.morphia.annotations.NotSaved;

/**
 * Admin is up on the user. Admin is a role mark it has this right. User is the bean.
 * @author sasgsc
 */
@NotSaved
public class Auth {

	private Integer lv=10;
	//任意人可以浏览, TOPIC CONTENT中大于这个值的会员都可以操作, 这个值为操作的最低值. USER中, 这个值大于设置值就可以操作.
	
	private String password;

	private Integer visible=999;//可视等级, 小于文档等级不现实. 这个数大于文档的阅读权限才显示
	
	private Integer create=10;
	private Integer delete=100;
	private Integer modify=100;
	private Integer find=10;
	
	private Integer loginVisible = 0;//0代表任意何健, 1代表登录可见
	
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

	public Integer getLoginVisible() {
		return loginVisible;
	}

	public void setLoginVisible(Integer loginVisible) {
		this.loginVisible = loginVisible;
	}
}
