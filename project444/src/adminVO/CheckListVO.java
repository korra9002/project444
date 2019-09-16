package adminVO;

public class CheckListVO {
											

	private String product_code, img_file, product_name, upload_date, user_id, category, price;
	
	public CheckListVO(String product_code, String img_file, String product_name, String upload_date, String user_id,
			String category, String price) {
		this.product_code = product_code;
		this.img_file = img_file;
		this.product_name = product_name;
		this.upload_date = upload_date;
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

	public String getUser_id() {
		return user_id;
	}

	public String getCategory() {
		return category;
	}


	public String getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "CheckVO [product_code=" + product_code + ", img_file=" + img_file + ", product_name=" + product_name
				+ ", upload_date=" + upload_date + ", user_id=" + user_id + ", category=" + category + ", price=" + price + "]";
	}
	
}//class
