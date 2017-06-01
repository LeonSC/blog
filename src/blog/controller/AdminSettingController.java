package blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.service.QiniuService;

@Controller
@RequestMapping("/admin/setting")
public class AdminSettingController {

	@Autowired
	private QiniuService qiniuService;

	@RequestMapping("/remotepic")
	public String remotePic() {
		return "admin/remotepic";
	}

	@RequestMapping("/qiniusubmit")
	public String qiniuSubmit(@RequestParam(value = "accesskey", required = false) String accesskey,
			@RequestParam(value = "secretkey", required = false) String secretkey,
			@RequestParam(value = "bucket", required = false) String bucket,
			@RequestParam(value = "onoff", required = false) Integer onoff) {
		this.qiniuService.saveSetting(accesskey, secretkey, bucket, onoff);
		return "redirect:/admin/remotepic";
	}
}
