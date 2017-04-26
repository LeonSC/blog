package blog.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
