package userVO;

public class FlagVO {
	private String dealFLag, allFlag, id;

	public FlagVO(String dealFLag, String allFlag, String id) {
		super();
		this.dealFLag = dealFLag;
		this.allFlag = allFlag;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getDealFLag() {
		return dealFLag;
	}

	public String getAllFlag() {
		return allFlag;
	}

}
