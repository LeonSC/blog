package blog.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import blog.model.User;
import blog.service.ContentService;
import blog.service.ImgService;
import blog.startup.Config;

/**
 * 控制文章相关的东西
 * 
 * @author sasgsc
 *
 */
@Controller
@RequestMapping("/write")
public class WriteController {

	@Autowired
	private ImgService imgService;
	@Autowired
	private ContentService contentService; 

	@RequestMapping("")
	public String write()
	{
		return "write";
	}
	
	/**
	 * 图片上传
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("/imgUpload")
	@ResponseBody
	public String fileUpload(HttpServletRequest request,@RequestParam(value = "files[]", required = false) MultipartFile file) 
	{
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		if(u==null)
		{
			return "";
		}
		String[] array = {""};
		// 判断文件是否为空
		if (file!=null&&!file.isEmpty()) {
			try {
				array = this.imgService.getImgPhysicalPath(u.getBM_ID(), file.getOriginalFilename());
				// 转存文件
				file.transferTo(new File(array[1]));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 重定向
		return array[0];
	}

	/**
	 * 图片删除
	 * @param path
	 * @return
	 */
	@RequestMapping("/imgDelete")
	@ResponseBody
	public String fileDelete(@RequestParam(value = "file", required = false) String path)
	{
		String re = this.imgService.delImgPhysicalPath(path);
		
		return re;
	}
	
	@RequestMapping("/savedraft")
	@ResponseBody
	public String saveDraft(HttpServletRequest request,@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "content", required = false) String content) {
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		this.contentService.saveDraft(u.getBM_ID(), title, content);
		return "";
	}
}
