package blog.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.service.AdminService;
import blog.service.ImgService;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ImgService imgService;
	@Autowired
	private AdminService adminService;

	@RequestMapping("/index")
	public String index() {
		return "admin/index";
	}

	@RequestMapping("/index/setting")
	public String indexSetting() {
		return "admin/indexsetting";
	}

	/**
	 * 保存首页轮播图
	 * @param file
	 * @param bmid
	 * @param link
	 * @return
	 */
	@RequestMapping("/index/carousel/submit")
	public String carouselSetting(@RequestParam(value = "img", required = false) MultipartFile file,
			@RequestParam(value = "bmid", required = false) String bmid,
			@RequestParam(value = "link", required = false) String link) {
		String name = file.getOriginalFilename();
		String[] tmp = this.imgService.getAdminImgPhysicalPath(name);
		try {
			Thumbnails.of(file.getInputStream()).sourceRegion(Positions.CENTER, 825, 160).size(825, 160)
					.keepAspectRatio(false).toFile(tmp[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.adminService.saveCarousel(bmid, tmp[1], link);
		return "redirect:/admin/index/setting";
	}
	
	/**
	 * 保存首页的重要通知
	 * @param bar
	 * @param title
	 * @param notice
	 * @param link
	 * @return
	 */
	@RequestMapping("/index/notice/submit")
	public String noticeSubmit(@RequestParam(value = "bar", required = false) String bar,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "notice", required = false) String notice,
			@RequestParam(value = "link", required = false) String link)
	{
		this.adminService.saveNotice(bar, title, notice, link);
		return "redirect:/admin/index/setting";
	}
	
	
	/**
	 * 删除
	 * @param bmid
	 * @return
	 */
	@RequestMapping("/index/notice/delete")
	public String noticeDelete(@RequestParam(value = "bmid", required = false) String bmid)
	{
		this.adminService.deleteNoticeByBMID(bmid);
		return "redirect:/admin/index/setting";
	}
}
