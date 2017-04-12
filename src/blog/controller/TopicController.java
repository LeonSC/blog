package blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/topic")
public class TopicController {

	@RequestMapping("/{topic}")
	public String topic()
	{
		return "topic";
	}
}
