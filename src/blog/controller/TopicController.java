package blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.model.Content;
import blog.model.Page;
import blog.model.Reply;
import blog.model.Topic;
import blog.model.User;
import blog.service.ContentService;
import blog.startup.Config;
import blog.startup.TCache;

@Controller
@RequestMapping("/topic")
public class TopicController {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/{topic}")
	public String topic(HttpServletRequest request, @PathVariable String topic, 
			@RequestParam(value = "p", required = false) Integer p,
			@RequestParam(value = "i", required = false) Integer i) {
		// 未登录不允许浏览判定
		Topic t = TCache.getCache().titleCache.get(topic);
		if (t.getAuth().getLoginVisible() != null && t.getAuth().getLoginVisible() == 1) {
			User u = (User) request.getSession().getAttribute(Config.memAuth);
			if (u == null) {
				return "redirect:/error/noart";
			}
		}
		List<Content> toplist = this.contentService.getContentTopListByTopic(topic);
		request.setAttribute("toplist", toplist);
		Page<Content> page = this.contentService.getContentListByTopic(topic,p,i);
		request.setAttribute("page", page);
		request.setAttribute("topic", topic);
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
	public String art(HttpServletRequest request, @PathVariable String bmid, 
			@RequestParam(value = "p", required = false) Integer p,
			@RequestParam(value = "i", required = false) Integer i) {
		Content c = this.contentService.getContentByBMID(bmid);
		if (c == null) {
			return "redirect:/error/noart";
		}
		// 未登录不允许浏览判定
		Topic t = TCache.getCache().titleCache.get(c.getTopic());
		if (t.getAuth().getLoginVisible() != null && t.getAuth().getLoginVisible() == 1) {
			User u = (User) request.getSession().getAttribute(Config.memAuth);
			if (u == null) {
				return "redirect:/error/noart";
			}
		}
		// 当文章附带价格时候, 未登录不允许浏览, 未付费不允许浏览
		if (c.getPrice() != null && c.getPrice() != 0) {
			// 判断未登录
			User u = (User) request.getSession().getAttribute(Config.memAuth);
			if (u == null) {
				return "redirect:/error/nologin";
			}
			// 不是作者, 未购买, 不允许查看
			if (!u.getBM_ID().equals(c.getUser().getBM_ID()) && c.getPayer().get(u.getBM_ID()) == null) {
				return "redirect:/pay/art/" + c.getBM_ID();
			}
		}
		request.setAttribute("c", c);
		Page<Reply> page = this.contentService.getReplyList(bmid,p,i);
		request.setAttribute("replyPage", page);
		return "show";
	}

	/**
	 * 文章置顶
	 * @param request
	 * @param bmid
	 * @return
	 */
	@RequestMapping("/switchtop/{bmid}")
	public String switchArtTop(HttpServletRequest request, @PathVariable String bmid) {
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		this.contentService.changeContentToTop(u, bmid);
		return "redirect:/topic/art/" + bmid;
	}
	
	/**
	 * 删除一篇文章
	 * @param request
	 * @param bmid
	 * @return
	 */
	@RequestMapping("/delete/{bmid}")
	public String deleteArt(HttpServletRequest request, @PathVariable String bmid)
	{
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		String topic = this.contentService.deleteContent(u, bmid);
		return "redirect:/topic/" + topic;
	}
}
