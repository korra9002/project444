package chatTest;

import java.util.Date;

public class ChatVO {
	private String chat, you;
	private Date inputDate;

	public ChatVO(String chat, String you, Date inputDate) {
		super();
		this.chat = chat;
		this.you = you;
		this.inputDate = inputDate;
	}

	public String getChat() {
		return chat;
	}

	public String getYou() {
		return you;
	}

	public Date getInputDate() {
		return inputDate;
	}

}
