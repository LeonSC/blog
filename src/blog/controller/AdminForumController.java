package blog.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.service.ForumService;
import blog.service.ImgService;

@Controller
@RequestMapping("/admin/forum")
public class AdminForumController {

	@Autowired
	private ForumService forumService;
	@Autowired
	private ImgService imgService;
	
	@RequestMapping("")
	public String forum() {
		return "admin/forum";
	}
	
	@RequestMapping("/forumsetting")
	public String forumSetting(HttpServletRequest request,
			@RequestParam(value = "bmid", required = false) String bmid,
			@RequestParam(value = "okey", required = false) String okey) {
		request.setAttribute("bmid", bmid);
		request.setAttribute("okey", okey);
		return "admin/forumsetting";
	}
	
	@RequestMapping("/submit")
	public String forumSave(@RequestParam(value = "bmid", required = false) String bmid,
			@RequestParam(value = "okey", required = false) String okey,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "intro", required = false) String intro,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "order", required = false) Integer order,
			@RequestParam(value = "loginvisible", required = false) Integer loginvisible) {
		String[] array = { "","","" };
		// 判断文件是否为空
		if (file != null && !file.isEmpty()) {
			try {
				array = this.imgService.getImgPhysicalPathForForumBlock(file.getOriginalFilename());
				// 转存文件
				file.transferTo(new File(array[1]));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.forumService.saveOrUpdateBlock(bmid, okey, name, intro, array[2], order, loginvisible);
		return "redirect:/admin/forum";
	}
}
