package blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.model.Block;
import blog.model.Content;
import blog.service.ContentService;
import blog.startup.FCache;

@Controller
@RequestMapping("/forum")
public class ForumController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("")
	public String index() {
		return "forum/index";
	}
	
	@RequestMapping("/node/{point}")
	public String block(HttpServletRequest request, @PathVariable String point)
	{
		Block node = FCache.getCache().getBlockmap().get(point);
		request.setAttribute("node", node);
		List<Content> list = this.contentService.getContentListByTopic(point);
		request.setAttribute("list", list);
		return "forum/block";
	}
}
