package blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.service.DepositService;

@Controller
@RequestMapping("/admin/deposit")
public class AdminDepositController {
	@Autowired
	private DepositService depositService;
	
	@RequestMapping("")
	public String index(HttpServletRequest request) {
		request.setAttribute("dtlist", this.depositService.getDepositTopicList());
		return "admin/deposit";
	}

	@RequestMapping("/topicsubmit")
	public String depositTopicSubmit(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "count", required = false) Integer count,
			@RequestParam(value = "price", required = false) Integer price) {
		this.depositService.saveDepositTopic(title, count, price);
		return "redirect:/admin/deposit";
	}
	
	/**
	 * 生成卡
	 * @param bmid
	 * @return
	 */
	@RequestMapping("/depositcreate")
	public String depositCreate(@RequestParam(value = "bmid", required = false) String bmid)
	{
		this.depositService.createDepositCard(bmid);
		return "redirect:/admin/deposit";
	}
}
