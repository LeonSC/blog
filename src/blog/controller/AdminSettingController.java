package blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/setting")
public class AdminSettingController {

	@RequestMapping("/remotepic")
	public String remotePic()
	{
		return "admin/remotepic";
	}
	
	@RequestMapping("/qiniusubmit")
	public String qiniuSubmit(@RequestParam(value = "accesskey", required = false) String accesskey,
			@RequestParam(value = "secretkey", required = false) String secretkey,
			@RequestParam(value = "bucket", required = false) String bucket)
	{
		return "redirect:/admin/remotepic";
	}
}
