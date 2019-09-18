package userVO;

public class InterestListVO {
	private  String product_code,user_id;

	public InterestListVO(String product_code, String user_id) {
		super();
		this.product_code = product_code;
		this.user_id = user_id;
	}//InterestListVO

	public String getProduct_code() {
		return product_code;
	}//getProduct_code

	public String getUser_id() {
		return user_id;
	}//getUser_id

	@Override
	public String toString() {
		return "InterestListVO [product_code=" + product_code + ", user_id=" + user_id + "]";
	}//toString
	
}//class
