package blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import blog.model.Draft;

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
	public String write(HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		Draft draft = this.contentService.getDraftByUser(u.getBM_ID());
		request.setAttribute("draft", draft);
		return "write";
	}

	/**
	 * 图片上传
	 * 
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("/imgUpload")
	@ResponseBody
	public String fileUpload(HttpServletRequest request,
			@RequestParam(value = "files[]", required = false) MultipartFile file) {
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		if (u == null) {
			return "";
		}
		String array = this.imgService.getImgPhysicalPath(u.getBM_ID(), file);
		// 
		return array;
	}

	/**
	 * 图片删除
	 * 
	 * @param path
	 * @return
	 */
	@RequestMapping("/imgDelete")
	@ResponseBody
	public String fileDelete(@RequestParam(value = "file", required = false) String path) {
		String re = this.imgService.delImgPhysicalPath(path);
		return re;
	}

	/**
	 * 保存草稿
	 * 
	 * @param request
	 * @param title
	 * @param content
	 * @return
	 */
	@RequestMapping("/savedraft")
	@ResponseBody
	public String saveDraft(HttpServletRequest request, @RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "content", required = false) String content) {
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		String re = this.contentService.saveDraft(u.getBM_ID(), title, content);
		return re;
	}

	/**
	 * 获取预览
	 * 
	 * @return
	 */
	@RequestMapping("/preview")
	public String preView(HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		Draft draft = this.contentService.getDraftByUser(u.getBM_ID());
		if(draft==null)
		{
			return "redirect:/write";
		}
		request.setAttribute("draft", draft);
		return "preview";
	}

	/**
	 * 草稿发布
	 * 
	 * @param request
	 * @param topic
	 * @param price
	 * @param bmid
	 * @return
	 */
	@RequestMapping("/publish")
	public String publish(HttpServletRequest request, @RequestParam(value = "topic", required = false) String topic,
			@RequestParam(value = "price", required = false) Integer price,
			@RequestParam(value = "draft", required = false) String bmid) {
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		this.contentService.publishContent(u.getBM_ID(), topic, price);
		return "redirect:/topic/" + topic;
	}

	/**
	 * 用户回复
	 * 
	 * @param request
	 * @param topic
	 * @return
	 */
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request,
			@RequestParam(value = "contentid", required = false) String contentid,
			@RequestParam(value = "reply", required = false) String reply) {
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		this.contentService.setReplyForContent(u.getBM_ID(), contentid, reply);
		return "redirect:/topic/art/" + contentid;
	}
}
