package blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.model.Topic;
import blog.model.User;
import blog.service.TopicService;
import blog.startup.Config;
import blog.startup.TCache;

@Controller
@RequestMapping("/admin/topic")
public class AdminTopicController {

	@Autowired
	private TopicService topicService;

	@RequestMapping("")
	public String topic() {
		return "admin/topic";
	}

	@RequestMapping("/addmanager")
	public String topicAddManager(@RequestParam(value = "bmid", required = false) String bmid,
			@RequestParam(value = "email", required = false) String email)
	{
		this.topicService.updateTopicManager(bmid, email);
		return "redirect:/admin/topic?bmid="+bmid;
	}

	@RequestMapping("/auth/update")
	public String updateTopicAuth(HttpServletRequest request,
			@RequestParam(value = "bmid", required = false) String bmid,
			@RequestParam(value = "loginvisible", required = false) Integer loginvisible,
			@RequestParam(value = "lv", required = false) Integer lv,
			@RequestParam(value = "visible", required = false) Integer visible)
	{
		User u = (User) request.getSession().getAttribute(Config.adminAuth);
		this.topicService.updateTopicAuth(bmid, u, loginvisible, lv, visible);
		return "redirect:/admin/topic?bmid="+bmid;
	}
	
	/**
	 * 增加一个节点
	 * @param name
	 * @param icon
	 * @param order
	 * @param intro
	 * @return
	 */
	@RequestMapping("/add")
	public String addTopic(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "icon", required = false) String icon,
			@RequestParam(value = "order", required = false) Integer order,
			@RequestParam(value = "intro", required = false) String intro)
	{
		this.topicService.saveTopic(name, icon, order, intro);
		return "redirect:/admin/topic";
	}
	
	/**
	 * 管理一个节点
	 * @param request
	 * @param bmid
	 * @return
	 */
	@RequestMapping("/remove")
	public String goRemoveTopic(HttpServletRequest request,@RequestParam(value = "bmid", required = false) String bmid)
	{
		Topic topic = TCache.getCache().titleCache.get(bmid);
		request.setAttribute("topic", topic);
		long restArt = this.topicService.countArtInTopic(bmid);
		request.setAttribute("restArt", restArt);
		request.getSession().setAttribute("admin_topic_remove_moveart", bmid);
		return "admin/removetopic";
	}
	
	/**
	 * 挪动主题下的内容
	 * @param request
	 * @param to
	 * @return
	 */
	@RequestMapping("/remove/moveart")
	public String moveArt(HttpServletRequest request,@RequestParam(value = "to", required = false) String to)
	{
		Object oldobj = request.getSession().getAttribute("admin_topic_remove_moveart");
		if(oldobj==null)
		{
			return "redirect:/admin/topic";
		}
		String old = oldobj.toString();
		this.topicService.moveArtToOtherTopic(old, to);
		return "redirect:/admin/topic/remove?bmid="+old;
	}
	
	/**
	 * 删除一个主题
	 * @param request
	 * @return
	 */
	@RequestMapping("/remove/action")
	public String remove(HttpServletRequest request)
	{
		Object oldobj = request.getSession().getAttribute("admin_topic_remove_moveart");
		if(oldobj!=null)
		{
			this.topicService.removeTopic(oldobj.toString());
		}
		return "redirect:/admin/topic";
	}
}
