package blog.model;

import java.util.HashMap;
import java.util.Map;

import org.mongodb.morphia.annotations.Entity;

@Entity("title")
public class Topic extends BaseModel{
	
	private String name;
	private String icon="";
	private Integer order=0;
	private String intro="";
	
	private Background background;
	
	private Map<String, Manager> manager=new HashMap<>();
	//人, 可用权限, 如果此人被查找在本列表中, 此人将使用本列表的权限
	//key, BMID, value, 管理员实体
	
	private Auth auth = new Auth();//这个节点的必须要的权限, 只有大于这个权限才能操作, 不可视一定不可操作
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Map<String, Manager> getManager() {
		return manager;
	}
	public void setManager(Map<String, Manager> manager) {
		this.manager = manager;
	}
	public Background getBackground() {
		return background;
	}
	public void setBackground(Background background) {
		this.background = background;
	}
	public Auth getAuth() {
		return auth;
	}
	public void setAuth(Auth auth) {
		this.auth = auth;
	}
}
