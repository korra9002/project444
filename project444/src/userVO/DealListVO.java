package userVO;

public class DealListVO {
private String id,loc,dealCode;

public DealListVO(String id, String loc, String dealCode) {
	super();
	this.id = id;
	this.loc = loc;
	this.dealCode = dealCode;
}

public String getId() {
	return id;
}

public String getLoc() {
	return loc;
}

public String getDealCode() {
	return dealCode;
}


}
