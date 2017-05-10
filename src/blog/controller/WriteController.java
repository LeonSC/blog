package blog.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import blog.model.Block;
import blog.model.Draft;
import blog.model.Topic;
import blog.model.User;
import blog.service.ContentService;
import blog.service.ImgService;
import blog.startup.Config;
import blog.startup.FCache;
import blog.startup.TCache;

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
		String[] array = { "" };
		// 判断文件是否为空
		if (file != null && !file.isEmpty()) {
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
			return "redirect:/write/layui";
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

	///////////////////////////// layui editor///////////////////////////////////////
	@RequestMapping("/layui")
	public String layuiWrite(HttpServletRequest request, @RequestParam(value = "topic", required = false) String topic) {
		if(topic==null)
		{
			return "redirect:/index";
		}
		Topic t = TCache.getCache().titleCache.get(topic);
		request.setAttribute("topic", t);
		return "editor/layui";
	}
	
	@RequestMapping("/imguploadforlayui")
	@ResponseBody
	public String imgUploadForLayUI(HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		if (u == null) {
			return "";
		}
		String[] array = { "" };
		// 判断文件是否为空
		if (file != null && !file.isEmpty()) {
			try {
				array = this.imgService.getImgPhysicalPathForLayUI(u.getBM_ID(), file.getOriginalFilename());
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
	 * layui保存
	 * 
	 * @param request
	 * @param title
	 * @param content
	 * @return
	 */
	@RequestMapping("/publishlayui")
	@ResponseBody
	public String publishLayUI(HttpServletRequest request,
			@RequestParam(value = "topic", required = false) String topic,
			@RequestParam(value = "price", required = false) Integer price,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "content", required = false) String content) {
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		String re = this.contentService.layuiPublishContent(u.getBM_ID(), topic, price, title, content);
		return re;
	}
	
	/**
	 * 进入写入论坛BLOCK
	 * @param request
	 * @param block
	 * @return
	 */
	@RequestMapping("/layuiforum")
	public String writeForum(HttpServletRequest request, @RequestParam(value = "block", required = false) String block)
	{
		Block node = FCache.getCache().getBlockmap().get(block);
		request.setAttribute("node", node);
		return "forum/layui";
	}
	
	@RequestMapping("/forumsubmitlayui")
	@ResponseBody
	public String publishForumLayUI(HttpServletRequest request,
			@RequestParam(value = "topic", required = false) String topic,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "content", required = false) String content) {
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		String re = this.contentService.layuiPublishContent(u.getBM_ID(), topic, 0, title, content);
		return re;
	}
	
	/**
	 * 用户回复
	 * 
	 * @param request
	 * @param topic
	 * @return
	 */
	@RequestMapping("/forumreply")
	public String forumReply(HttpServletRequest request,
			@RequestParam(value = "contentid", required = false) String contentid,
			@RequestParam(value = "reply", required = false) String reply) {
		User u = (User) request.getSession().getAttribute(Config.memAuth);
		this.contentService.setReplyForContent(u.getBM_ID(), contentid, reply);
		return "redirect:/forum/point/" + contentid;
	}
}
