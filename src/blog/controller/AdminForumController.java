package blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/forum")
public class AdminForumController {

	@RequestMapping("")
	public String forum() {
		return "admin/forum";
	}
	
	@RequestMapping("/add")
	public String forumAdd(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "loginvisible", required = false) Integer loginvisible) {
		return "admin/forum";
	}
}
