package userVO;

import java.util.Comparator;

public class ChatListVO {
	private String proName, id, loc, time, lastchat, dealCode,allFlag,saleFlag,readFlag;

	public ChatListVO(String proName, String id, String loc, String time, String lastchat, String dealCode,
			String allFlag, String saleFlag,String readFlag) {
		super();
		this.proName = proName;
		this.id = id;
		this.loc = loc;
		this.time = time;
		this.lastchat = lastchat;
		this.dealCode = dealCode;
		this.allFlag = allFlag;
		this.saleFlag = saleFlag;
		this.readFlag = readFlag;
	}

	public String getReadFlag() {
		return readFlag;
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

	public String getDealCode() {
		return dealCode;
	}

	public String getAllFlag() {
		return allFlag;
	}

	public String getSaleFlag() {
		return saleFlag;
	}


//	@Override
//	public int compareTo(ChatListVO clVO) {
//		String thisVal = this.time;
//		String anotherVal = clVO.getTime();
//		
//		return thisVal.compareTo(anotherVal)*-1;
//	}

	

}
