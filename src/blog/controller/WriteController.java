package blog.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.model.User;
import blog.service.ImgService;
import blog.startup.Config;

/**
 * 控制文章相关的东西
 * 
 * @author sasgsc
 *
 */
@Controller
@RequestMapping("write")
public class WriteController {

	@Autowired
	private ImgService imgService;

	@RequestMapping("")
	public String write()
	{
		return "write";
	}
	
	@RequestMapping("imgUpload")
	public String fileUpload(HttpServletRequest request,@RequestParam("imgfile") MultipartFile file) 
	{
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 转存文件
				file.transferTo(new File(this.imgService.getImgPhysicalPath(u.getBM_ID(), file.getOriginalFilename())));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 重定向
		return "redirect:/list.html";
	}

}
