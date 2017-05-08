package blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.model.Block;
import blog.startup.FCache;

@Controller
@RequestMapping("/forum")
public class ForumController {

	@RequestMapping("")
	public String index() {
		return "forum/index";
	}
	
	@RequestMapping("/{bmid}")
	public String block(HttpServletRequest request, @PathVariable String bmid)
	{
		Block node = FCache.getCache().getBlockmap().get(bmid);
		request.setAttribute("node", node);
		return "forum/block";
	}
}
