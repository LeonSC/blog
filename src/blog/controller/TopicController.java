package blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.model.Content;
import blog.model.Reply;
import blog.model.User;
import blog.service.ContentService;
import blog.startup.Config;

@Controller
@RequestMapping("/topic")
public class TopicController {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/{topic}")
	public String topic(HttpServletRequest request, @PathVariable String topic) {
		List<Content> toplist = this.contentService.getContentTopListByTopic(topic);
		request.setAttribute("toplist", toplist);
		List<Content> list = this.contentService.getContentListByTopic(topic);
		request.setAttribute("list", list);
		return "topic";
	}

	/**
	 * 浏览文章
	 * 
	 * @param request
	 * @param bmid
	 * @return
	 */
	@RequestMapping("/art/{bmid}")
	public String art(HttpServletRequest request, @PathVariable String bmid) {
		Content c = this.contentService.getContentByBMID(bmid);
		if (c == null) {
			return "redirect:/error/noart";
		}
		if (c.getPrice() != null || c.getPrice() != 0) {
			User u = (User) request.getSession().getAttribute(Config.memAuth);
			if (u == null) {
				return "redirect:/error/nologin";
			}
			if (!u.getBM_ID().equals(c.getUser().getBM_ID()) && c.getPayer().get(u.getBM_ID()) == null) {
				return "redirect:/pay/art/" + c.getBM_ID();
			}
		}
		request.setAttribute("c", c);
		List<Reply> list = this.contentService.getReplyList(bmid);
		request.setAttribute("replylist", list);
		return "show";
	}

	@RequestMapping("/switchtop/{topic}/{bmid}")
	public String switchArtTop(HttpServletRequest request, @PathVariable String topic, @PathVariable String bmid) {
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		this.contentService.changeContentToTop(u, topic, bmid);
		return "redirect:/topic/art/" + bmid;
	}
}
