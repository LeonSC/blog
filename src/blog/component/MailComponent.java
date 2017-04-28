package blog.component;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;

import blog.startup.Config;

@Component
public class MailComponent {

	public SimpleEmail getSimpleEmail() throws EmailException
	{
		SimpleEmail email = new SimpleEmail();
		email.setHostName(Config.esmtp); // 发送服务器
		email.setAuthentication(Config.efrom, Config.epw); // 发送邮件的用户名和密码
		email.setFrom(Config.efrom); // 发送邮箱
		email.setSmtpPort(465); // 端口
		email.setSSLOnConnect(true); // gmail需要设置SSL安全设置
		email.setCharset("UTF-8"); // 编码
		return email;
	}
	
	private SimpleEmail getHotmailSimpleEmail() throws EmailException
	{
		SimpleEmail client = new SimpleEmail();
		client.setHostName("smtp-mail.outlook.com"); // 发送服务器
		client.setSmtpPort(587); // 端口
		client.setAuthentication(Config.efrom, Config.epw); // 发送邮件的用户名和密码
		client.setStartTLSEnabled(true);
		client.setStartTLSRequired(true);
		client.setFrom(Config.efrom); // 发送邮箱
		client.setCharset("UTF-8"); // 编码
		return client;
	}
	
	public int sendComfirmEmail(String email, String content)
	{
		try {
			SimpleEmail client = this.getHotmailSimpleEmail();
			client.addTo(email);
			client.setSubject("邮箱验证");// 主题
			client.setMsg(content); // 内容
			client.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int sendDepositCardEmail(String email, String code)
	{
		try {
			SimpleEmail client = this.getHotmailSimpleEmail();
			client.addTo(email);
			client.setSubject("宅次元");// 主题
			client.setMsg("请登录后在个人面板中兑换."+code); // 内容
			client.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void main(String[] args) throws EmailException {
		
		SimpleEmail client = new SimpleEmail();
		client.setHostName("smtp-mail.outlook.com"); // 发送服务器
		client.setSmtpPort(587); // 端口
		client.setAuthentication("otakulacom@hotmail.com", "akinaclub13832840620"); // 发送邮件的用户名和密码
		client.setStartTLSEnabled(true);
		client.setStartTLSRequired(true);
		//client.setSSLOnConnect(true); // gmail需要设置SSL安全设置
		client.setFrom("otakulacom@hotmail.com"); // 发送邮箱
		
		//client.set
		client.setCharset("UTF-8"); // 编码
		client.addTo("sasgsc@qq.com");
		client.setSubject("邮箱验证");// 主题
		client.setMsg("test"); // 内容
		client.send();
	}
}
