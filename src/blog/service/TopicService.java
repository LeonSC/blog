package blog.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.dao.ContentDao;
import blog.dao.TopicDao;
import blog.dao.UserDao;
import blog.model.Auth;
import blog.model.Manager;
import blog.model.Topic;
import blog.model.User;
import blog.startup.TCache;

@Service
public class TopicService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private ContentDao contentDao;
	
	/**
	 * 添加管理员
	 * @param topicid
	 * @param username
	 * @return
	 */
	public int updateTopicManager(String topicid, String username)
	{
		User u = this.userDao.findByUserName(username);
		if(u==null)
		{
			return -1;
		}
		Topic t = this.topicDao.findTopicByBMID(topicid);
		Manager m = new Manager();
		m.setBM_ID(u.getBM_ID());
		m.setNickname(u.getNickname());
		m.setUsername(u.getUsername());
		m.setAuth(new Auth());
		if(t.getManager()==null)
		{
			t.setManager(new HashMap<>());
		}
		t.getManager().put(u.getBM_ID(), m);
		this.topicDao.updateTopicManagerByBMID(t);
		//重新设置缓存
		TCache.getCache().initTitleCache();
		return 0;
	}
	
	/**
	 * 修改主题的权限
	 * @param topicid
	 * @param u
	 * @param loginvisible
	 * @param lv
	 * @param visible
	 * @return
	 */
	public int updateTopicAuth(String topicid, User u, Integer loginvisible, Integer lv, Integer visible)
	{
		if(loginvisible==null||loginvisible!=1)
		{
			loginvisible=0;
		}
		else
		{
			loginvisible=1;
		}
		if(lv==null||lv<0)
		{
			lv=0;
		}
		if(lv>=u.getAdmin().getLv())
		{
			lv=u.getAdmin().getLv();
		}
		if(visible==null||visible<0)
		{
			visible=0;
		}
		if(visible>=u.getAdmin().getVisible())
		{
			visible=u.getAdmin().getVisible();
		}
		Topic topic =new Topic();
		topic.setBM_ID(topicid);
		Auth auth=new Auth();
		auth.setLoginVisible(loginvisible);
		auth.setLv(lv);
		auth.setVisible(visible);
		auth.setCreate(null);
		auth.setDelete(null);
		auth.setModify(null);
		auth.setFind(null);
		topic.setAuth(auth);
		this.topicDao.updateTopicAuthByBMID(topic);
		//重新设置缓存
		TCache.getCache().initTitleCache();
		return 0;
	}
	
	/**
	 * 保存一个topic
	 * @param name
	 * @param icon
	 * @param order
	 * @param intro
	 * @return
	 */
	public int saveTopic(String name, String icon, Integer order, String intro)
	{
		if(name==null)
		{
			return -1;
		}
		if(order==null)
		{
			order = this.topicDao.getLastOrder();
		}
		Topic topic = new Topic();
		topic.setName(name);
		topic.setIcon(icon);
		topic.setOrder(order);
		topic.setIntro(intro);
		topic.getAuth().setVisible(0);
		this.topicDao.save(topic);
		//重新设置缓存
		TCache.getCache().initTitleCache();
		return 0;
	}
	
	/**
	 * 删除一个节点/主题
	 * @param bmid
	 * @return
	 */
	public int removeTopic(String bmid)
	{
		int re = this.topicDao.remove(bmid);
		TCache.getCache().initTitleCache();
		return re;
	}
	
	/**
	 * 根据主题查找主题下面有多少帖子
	 * @param bmid
	 * @return long
	 */
	public long countArtInTopic(String bmid)
	{
		return this.contentDao.countArtInTopic(bmid);
	}
	
	/**
	 * 把一个主题下的文章全部移动到另外一个主题中
	 * @param old
	 * @param yo
	 * @return
	 */
	public int moveArtToOtherTopic(String old, String yo)
	{
		return this.contentDao.moveArtTopicByBMID(old, yo);
	}
}
