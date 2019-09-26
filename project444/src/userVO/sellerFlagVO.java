package userVO;

public class sellerFlagVO {
	private int pFlag;
	private int yFlag;
	private String allFLag;
	public sellerFlagVO(String allFLag,int pFlag, int yFlag) {
		super();
		this.allFLag = allFLag;
		this.pFlag = pFlag;
		this.yFlag = yFlag;
	}
	public int getpFlag() {
		return pFlag;
	}
	public String getAllFLag() {
		return allFLag;
	}
	public int getyFlag() {
		return yFlag;
	}
	@Override
	public String toString() {
		return "sellerFlagVO [pFlag=" + pFlag + ", yFlag=" + yFlag + ", allFLag=" + allFLag + "]";
	}
	
	
}
