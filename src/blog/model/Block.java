package blog.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.mongodb.morphia.annotations.Entity;

/**
 * 用于存放论坛结构
 * @author sasgsc
 */
@Entity("block")
public class Block extends BaseModel{

	private String okey="0";//从属预哪个节点, 节点0为顶级节点
	
	private String name;
	private String icon="";
	private Integer order=0;
	private String intro="";
	
	private Background background;
	
	private Map<String, Manager> manager=new HashMap<>();
	//人, 可用权限, 如果此人被查找在本列表中, 此人将使用本列表的权限
	//key, BMID, value, 管理员实体
	
	private Auth auth = new Auth();//这个节点的必须要的权限, 只有大于这个权限才能操作, 不可视一定不可操作
	
	//子域
	private LinkedHashMap<String,Block> block;

	public String getOkey() {
		return okey;
	}

	public void setOkey(String okey) {
		this.okey = okey;
	}

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

	public Background getBackground() {
		return background;
	}

	public void setBackground(Background background) {
		this.background = background;
	}

	public Map<String, Manager> getManager() {
		return manager;
	}

	public void setManager(Map<String, Manager> manager) {
		this.manager = manager;
	}

	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}

	public LinkedHashMap<String,Block> getBlock() {
		return block;
	}

	public void setBlock(LinkedHashMap<String,Block> block) {
		this.block = block;
	}
}
