package blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.model.Block;
import blog.model.Content;
import blog.model.Page;
import blog.model.Reply;
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
	public String block(HttpServletRequest request, @PathVariable String point, 
			@RequestParam(value = "p", required = false) Integer p,
			@RequestParam(value = "i", required = false) Integer i)
	{
		Block node = FCache.getCache().getBlockmap().get(point);
		request.setAttribute("node", node);
		Page<Content> page = this.contentService.getContentListByTopic(point,p,i);
		request.setAttribute("page", page);
		return "forum/block";
	}
	
	@RequestMapping("/point/{bmid}")
	public String point(HttpServletRequest request, @PathVariable String bmid, 
			@RequestParam(value = "p", required = false) Integer p,
			@RequestParam(value = "i", required = false) Integer i)
	{
		Content c=this.contentService.getContentByBMID(bmid);
		request.setAttribute("point", c);
		Page<Reply> page = this.contentService.getReplyList(bmid,p,i);
		request.setAttribute("page", page);
		return "forum/point";
	}
}
