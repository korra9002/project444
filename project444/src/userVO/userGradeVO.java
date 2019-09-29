package userVO;

public class userGradeVO {
	int sellCount, buyCount;

	public userGradeVO(int sellCount, int buyCount) {
		super();
		this.sellCount = sellCount;
		this.buyCount = buyCount;
	}

	public int getSellCount() {
		return sellCount;
	}

	public int getBuyCount() {
		return buyCount;
	}

}
