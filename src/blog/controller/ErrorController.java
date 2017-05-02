package blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

	@RequestMapping("/noart")
	public String noArt()
	{
		return "error/noart";
	}
	
	@RequestMapping("/noauth")
	public String noAuth()
	{
		return "error/noauth";
	}
	
	@RequestMapping("")
	public String error()
	{
		return "error/error";
	}
}
