package userVO;

public class MarketDetailVO {
	private String productCode, image, productName, loc_code ,upload_date, category, sellerID, pDetail;
	int price;
	public MarketDetailVO(String productCode, String image, String productName, String loc_code, String upload_date,
			String category, String sellerID, String pDetail, int price) {
		super();
		this.productCode = productCode;
		this.image = image;
		this.productName = productName;
		this.loc_code = loc_code;
		this.upload_date = upload_date;
		this.category = category;
		this.sellerID = sellerID;
		this.pDetail = pDetail;
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
	public String getpDetail() {
		return pDetail;
	}
	public int getPrice() {
		return price;
	}
	@Override
	public String toString() {
		return "MarketDetailVO [productCode=" + productCode + ", image=" + image + ", productName=" + productName
				+ ", loc_code=" + loc_code + ", upload_date=" + upload_date + ", category=" + category + ", sellerID="
				+ sellerID + ", pDetail=" + pDetail + ", price=" + price + "]";
	}
	
	

}
