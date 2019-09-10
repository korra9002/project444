package userVO;

public class ForgotIdVO {
	private String phone,name ;

	public ForgotIdVO(String phone, String name) {
		super();
		this.phone = phone;
		this.name = name;
	}//ForgotIdVO

	public String getPhone() {
		return phone;
	}//getPhone

	public String getName() {
		return name;
	}//getName

	@Override
	public String toString() {
		return "ForgotIdVO [phone=" + phone + ", name=" + name + "]";
	}//toString
	
}//class
