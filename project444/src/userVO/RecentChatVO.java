package userVO;

public class RecentChatVO {
	String time, chat;

	public RecentChatVO(String time, String chat) {
		super();
		this.time = time;
		this.chat = chat;
	}

	public String getTime() {
		return time;
	}

	public String getChat() {
		return chat;
	}

}
