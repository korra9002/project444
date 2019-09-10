package chatTest;

import java.util.Date;

public class ChatVO2 {
	private String sender,reciever,chat,chatFlag;
	private Date inputDate;
	public ChatVO2(String sender, String reciever, String chat, String chatFlag, Date inputDate) {
		super();
		this.sender = sender;
		this.reciever = reciever;
		this.chat = chat;
		this.chatFlag = chatFlag;
		this.inputDate = inputDate;
	}
	public String getSender() {
		return sender;
	}
	public String getReciever() {
		return reciever;
	}
	public String getChat() {
		return chat;
	}
	public String getChatFlag() {
		return chatFlag;
	}
	public Date getInputDate() {
		return inputDate;
	}
	
	
	
}
