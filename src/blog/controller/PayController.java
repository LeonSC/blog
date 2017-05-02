package blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.model.Content;
import blog.model.User;
import blog.service.ContentService;
import blog.service.PayService;
import blog.startup.Config;

@Controller
@RequestMapping("/pay")
public class PayController {

	@Autowired
	private ContentService contentService;
	@Autowired
	private PayService payService;

	@RequestMapping("/art/{bmid}")
	public String payForArt(HttpServletRequest request, @PathVariable String bmid) {
		Content c = this.contentService.getContentByBMID(bmid);
		if (c == null) {
			return "redirect:/error/noart";
		}
		request.getSession().setAttribute("surepay", c.getBM_ID());
		request.setAttribute("c", c);
		return "pay/art";
	}

	@RequestMapping("/sure")
	public String payForSure(HttpServletRequest request,
			@RequestParam(value = "secret", required = false) Integer secret) {
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		String bmid = request.getSession().getAttribute("surepay").toString();
		bmid = this.payService.art(bmid, u, secret);
		if (bmid == null) {
			return "redirect:/error/noart";
		}
		if (bmid.equals("-1")) {
			return "redirect:/error/noenough";
		}
		return "redirect:/topic/art/" + bmid;
	}
}
