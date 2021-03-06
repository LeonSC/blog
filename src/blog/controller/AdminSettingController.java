package blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.component.QiniuComponent;

@Controller
@RequestMapping("/admin/setting")
public class AdminSettingController {

	@Autowired
	private QiniuComponent qiniuComponent;

	@RequestMapping("/remotepic")
	public String remotePic(HttpServletRequest request) {
		return "admin/remotepic";
	}

	@RequestMapping("/qiniusubmit")
	public String qiniuSubmit(@RequestParam(value = "accesskey", required = false) String accesskey,
			@RequestParam(value = "secretkey", required = false) String secretkey,
			@RequestParam(value = "bucket", required = false) String bucket,
			@RequestParam(value = "link", required = false) String link,
			@RequestParam(value = "onoff", required = false) Integer onoff) {
		this.qiniuComponent.saveSetting(accesskey, secretkey, bucket, link, onoff);
		return "redirect:/admin/setting/remotepic";
	}
	
	@RequestMapping("/qiniusubmittest")
	@ResponseBody
	public String qiniuSubmitTest()
	{
		return this.qiniuComponent.test();
	}
}
