package blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.service.TopicService;

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
	public String updateTopicAuth(@RequestParam(value = "bmid", required = false) String bmid,
			@RequestParam(value = "loginvisible", required = false) String loginvisible,
			@RequestParam(value = "lv", required = false) String lv,
			@RequestParam(value = "visible", required = false) String visible)
	{
		return "redirect:/admin/topic?bmid="+bmid;
	}
}
