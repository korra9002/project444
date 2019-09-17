package userVO;

public class ChatListVO {
	private String proName, id, loc, time, lastchat, dealCode;

	public ChatListVO(String proName, String id, String loc, String time, String lastchat, String dealCode) {
		super();
		this.proName = proName;
		this.id = id;
		this.loc = loc;
		this.time = time;
		this.lastchat = lastchat;
		this.dealCode = dealCode;
	}

	public String getDealCode() {
		return dealCode;
	}

	public String getProName() {
		return proName;
	}

	public String getId() {
		return id;
	}

	public String getLoc() {
		return loc;
	}

	public String getTime() {
		return time;
	}

	public String getLastchat() {
		return lastchat;
	}

}
