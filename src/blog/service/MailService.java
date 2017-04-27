package blog.service;

import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	//public 
	
	public static void main(String[] args) throws Exception {
		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.163.com"); // 发送服务器
		email.setAuthentication("otakulacom@163.com", "epcwwikcxgvvb123"); // 发送邮件的用户名和密码
		email.addTo("sasgsc@qq.com"); // 接收邮箱
		email.setFrom("otakulacom@163.com"); // 发送邮箱
		email.setSubject("测试主题");// 主题
		email.setMsg("这里是邮件内容"); // 内容
		email.setSmtpPort(465); // 端口
		email.setSSLOnConnect(true); // gmail需要设置SSL安全设置
		email.setCharset("UTF-8"); // 编码
		email.send();
	}
}
