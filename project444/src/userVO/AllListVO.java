package userVO;

public class AllListVO {
	private String productCode, image, productName, loc_code ,upload_date;
	int price;
	public AllListVO(String productCode, String image, String productName, String loc_code, String upload_date,
			int price) {
		super();
		this.productCode = productCode;
		this.image = image;
		this.productName = productName;
		this.loc_code = loc_code;
		this.upload_date = upload_date;
		this.price = price;
	}
	
	

	
}
