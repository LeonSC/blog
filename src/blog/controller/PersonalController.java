package blog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.model.User;
import blog.service.ImgService;
import blog.service.UserService;
import blog.startup.Config;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@Controller
@RequestMapping("/personal")
public class PersonalController {

	@Autowired
	private ImgService imgService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String index() {
		return "personal/index";
	}
	
	@RequestMapping("/headericon/update")
	public String carouselSetting(HttpServletRequest request,@RequestParam(value = "img", required = false) MultipartFile file) {
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		String name = file.getOriginalFilename();
		String[] tmp = this.imgService.getUserHeaderIconPhysicalPath(u.getBM_ID(),name);
		try {
			Thumbnails.of(file.getInputStream()).sourceRegion(Positions.CENTER, 825, 160).size(200, 200)
					.keepAspectRatio(false).toFile(tmp[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		u = this.userService.updateHeaderIcon(u.getBM_ID(), tmp[1]);
		request.getSession().setAttribute(Config.memAuth, u);
		return "redirect:/personal/index";
	}
	
}
