package blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.model.Content;
import blog.model.Page;
import blog.service.ContentService;

@Controller
@RequestMapping("/admin/content")
public class AdminContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("")
	public String topic(HttpServletRequest request, 
			@RequestParam(value = "p", required = false) Integer p,
			@RequestParam(value = "i", required = false) Integer i) {
		Page<Content> page = this.contentService.getContentTrashCanList(p, i);
		request.setAttribute("page", page);
		return "admin/content";
	}
}
