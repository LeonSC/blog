package blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/index")
	public String index() {
		return "admin/index";
	}
	
	@RequestMapping("/index/setting")
	public String indexsetting() {
		return "admin/indexsetting";
	}
}
