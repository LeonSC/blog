package blog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.service.ImgService;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ImgService imgService;
	
	@RequestMapping("/index")
	public String index() {
		return "admin/index";
	}

	@RequestMapping("/index/setting")
	public String indexSetting() {
		return "admin/indexsetting";
	}

	@RequestMapping("/index/carousel/submit")
	public String carouselSetting(HttpServletRequest request,
			@RequestParam(value = "img", required = false) MultipartFile file,
			@RequestParam(value = "bmid", required = false) String bmid,
			@RequestParam(value = "link", required = false) String link) {
		String name = file.getOriginalFilename();
		String[] tmp = this.imgService.getAdminImgPhysicalPath(name);
		try {
			Thumbnails.of(file.getInputStream()).size(825, 160).toFile(tmp[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/indexsetting";
	}
}
