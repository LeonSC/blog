package blog.model;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Payment {

	private String payer;//支付人的ID
	private String payername;//支付人用于显示的名字
	private String payerheadericon;
	private Integer payment;//支付了多少
	private Integer secret;//0标识显示支付, 1标识秘密支付
	private Long paytime;
	public String getPayer() {
		return payer;
	}
	public void setPayer(String payer) {
		this.payer = payer;
	}
	public Integer getPayment() {
		return payment;
	}
	public void setPayment(Integer payment) {
		this.payment = payment;
	}
	public Integer getSecret() {
		return secret;
	}
	public void setSecret(Integer secret) {
		this.secret = secret;
	}
	public Long getPaytime() {
		return paytime;
	}
	public void setPaytime(Long paytime) {
		this.paytime = paytime;
	}
	public String getPayername() {
		return payername;
	}
	public void setPayername(String payername) {
		this.payername = payername;
	}
	public String getPayerheadericon() {
		return payerheadericon;
	}
	public void setPayerheadericon(String payerheadericon) {
		this.payerheadericon = payerheadericon;
	}
}
