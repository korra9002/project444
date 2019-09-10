package adminVO;

public class CheckVO {
											

	private String product_code, img_file, product_name, upload_date, all_flag, user_id, category;
	private int price;
	
	public CheckVO(String product_code, String img_file, String product_name, String upload_date, String all_flag,
			String user_id, String category, int price) {
		super();
		this.product_code = product_code;
		this.img_file = img_file;
		this.product_name = product_name;
		this.upload_date = upload_date;
		this.all_flag = all_flag;
		this.user_id = user_id;
		this.category = category;
		this.price = price;
	}

	public String getProduct_code() {
		return product_code;
	}

	public String getImg_file() {
		return img_file;
	}

	public String getProduct_name() {
		return product_name;
	}

	public String getUpload_date() {
		return upload_date;
	}

	public String getAll_flag() {
		return all_flag;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getCategory() {
		return category;
	}

	public int getPrice() {
		return price;
	}
	
}//class
