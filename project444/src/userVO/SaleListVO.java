package userVO;

public class SaleListVO {

String productCode, image, productName, loc_code ,upload_date, category, sellerID, check;
int price;
public SaleListVO(String productCode, String image, String productName, String loc_code, String upload_date,
		String category, String sellerID, String check, int price) {
	super();
	this.productCode = productCode;
	this.image = image;
	this.productName = productName;
	this.loc_code = loc_code;
	this.upload_date = upload_date;
	this.category = category;
	this.sellerID = sellerID;
	this.check = check;
	this.price = price;
}
public String getProductCode() {
	return productCode;
}
public String getImage() {
	return image;
}
public String getProductName() {
	return productName;
}
public String getLoc_code() {
	return loc_code;
}
public String getUpload_date() {
	return upload_date;
}
public String getCategory() {
	return category;
}
public String getSellerID() {
	return sellerID;
}
public String getCheck() {
	return check;
}
public int getPrice() {
	return price;
}


}