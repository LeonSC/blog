package blog.component;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;

import blog.startup.Config;

@Component
public class MailComponent {

	private SimpleEmail getSimpleEmail() throws EmailException
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
	
	public int sendComfirmEmail(String email, String content)
	{
		try {
			SimpleEmail client = this.getSimpleEmail();
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
			SimpleEmail client = this.getSimpleEmail();
			client.addTo(email);
			client.setSubject("兑换卷");// 主题
			client.setMsg("请登录后在个人面板中兑换. 兑换卷为 "+code); // 内容
			client.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
