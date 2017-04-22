package blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/personal")
public class PersonalController {

	
	@RequestMapping("/index")
	public String index() {
		return "personal/index";
	}
	
}
