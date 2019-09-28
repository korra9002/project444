package userVO;

public class RecentChatVO {
	String time, chat, readFlag;

	public RecentChatVO(String time, String chat, String readFlag) {
		super();
		this.time = time;
		this.chat = chat;
		this.readFlag = readFlag;
	}

	public String getTime() {
		return time;
	}

	public String getChat() {
		return chat;
	}

	public String getReadFlag() {
		return readFlag;
	}


}
