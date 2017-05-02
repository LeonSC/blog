package blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.model.Content;
import blog.service.ContentService;
import blog.service.PayService;

@Controller
@RequestMapping("/pay")
public class PayController {

	@Autowired
	private ContentService contentService;
	@Autowired
	private PayService payService;
	
	@RequestMapping("/art/{bmid}")
	public String payForArt(HttpServletRequest request,@PathVariable String bmid)
	{
		Content c = this.contentService.getContentByBMID(bmid);
		if(c==null)
		{
			return "redirect:/error/noart";
		}
		request.getSession().setAttribute("surepay",c.getBM_ID());
		request.setAttribute("c", c);
		return "pay/art";
	}
	
	@RequestMapping("/sure")
	public String payForSure(HttpServletRequest request)
	{
		String bmid = request.getSession().getAttribute("surepay").toString();
		if(bmid==null)
		{
			return "redirect:/error/noart";
		}
		bmid = this.payService.art(bmid);
		return "redirect:/topic/art/"+bmid;
	}
}
