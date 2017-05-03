package blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.model.User;
import blog.service.TopicService;
import blog.startup.Config;

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
}
