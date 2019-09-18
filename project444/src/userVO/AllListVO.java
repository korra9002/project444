package userVO;

public class AllListVO {
	private String productCode, image, productName, loc_code ,upload_date, category, sellerID;
	int price;
	public AllListVO(String productCode, String image, String productName, String loc_code, String upload_date,
			String category, String sellerID, int price) {
		super();
		this.productCode = productCode;
		this.image = image;
		this.productName = productName;
		this.loc_code = loc_code;
		this.upload_date = upload_date;
		this.category = category;
		this.sellerID = sellerID;
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
	public int getPrice() {
		return price;
	}

}
