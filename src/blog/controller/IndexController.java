package blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import blog.model.User;
import blog.service.AdminService;
import blog.service.UserService;
import blog.startup.Config;

@Controller
public class IndexController {

	@Autowired
	private UserService userService;
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	/////////////////////////// 普通会员登录////////////////////////////////////////
	@RequestMapping("/memloginsubmit")
	public String memLoginSubmit(HttpServletRequest request,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "pw", required = false) String pw) {
		User u = this.userService.checkUser(email, pw);
		if (u == null) {
			request.getSession().setAttribute("error_wrongpw", "wrongpw");
			return "redirect:/index";
		}
		request.getSession().setAttribute(Config.memAuth, u);
		// 如果为非首页需求登录
		Object path = request.getSession().getAttribute("recentView");
		request.getSession().setAttribute("recentView", "");// 用完后清理
		if (path != null && !path.toString().isEmpty()) {
			return "redirect:" + Config.getConfig().getRootPath() + path.toString();
		}
		return "redirect:/index";
	}
	
	/**
	 * 登出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/memlogoutsubmit")
	public String memLogoutSubmit(HttpServletRequest request,
			@RequestParam(value = "goback", required = false) String goback) {
		request.getSession().setAttribute(Config.memAuth, null);

		// 优先级, goback优先
		if (goback != null && !goback.isEmpty()) {
			return "redirect:" + Config.getConfig().getRootPath() + goback;
		}

		return "redirect:/index";
	}
	
	/////////////////////////// 普通会员注册////////////////////////////////////////
	/**
	 * 用户注册
	 * @param request
	 * @return
	 */
	@RequestMapping("/memregister")
	public String memRegister(HttpServletRequest request) {
		request.getSession().setAttribute("register", "on");
		return "memregister";
	}
	
	/**
	 * 用户注册提交
	 * @param email
	 * @param pw
	 * @return
	 */
	@RequestMapping("/memregistersubmit")
	public String memRegisterSubmit(HttpServletRequest request, 
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "pw", required = false) String pw) {
		if(request.getSession().getAttribute("register")==null)
		{
			return "redirect:/index";
		}
		request.getSession().setAttribute("register", null);
		this.userService.registerUser(email, pw, nickname);
		return "redirect:/index";
	}
	
	@RequestMapping("/email/check/{code}")
	public String checkConfirmEmail(HttpServletRequest request,@PathVariable String code)
	{
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		String email = this.userService.checkConfirmEmail(code);
		if(u!=null)
		{
			u.setEmail(email);
		}
		return "redirect:/index";
	}
	
	///////////////////////////管理员登录页////////////////////////////////////////
	@RequestMapping("/adminindex")
	public String adminLoginPage() {
		return "admin/adminLogin";
	}
	
	@RequestMapping("/adminlogoutsubmit")
	public String memLogoutSubmit(HttpServletRequest request) {
		request.getSession().setAttribute(Config.adminAuth, null);
		return "redirect:/adminloginsubmit";
	}
	
	@RequestMapping("/adminloginsubmit")
	public String adminLoginSubmit(HttpServletRequest request, @RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "pw", required = false) String pw) {
		User u = this.adminService.checkAdmin(email, pw);
		if (u == null) {
			request.getSession().setAttribute("error_wrongpw", "wrongpw");
			return "redirect:/adminindex";
		}
		request.getSession().setAttribute(Config.adminAuth, u);
		return "redirect:/admin/index";
	}
}
