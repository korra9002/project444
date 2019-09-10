package userVO;

public class LoginVO {
	private String id, pass;

	public LoginVO(String id, String pass) {
		super();
		this.id = id;
		this.pass = pass;
	}//LoginVO

	public String getId() {
		return id;
	}//getId

	public String getPass() {
		return pass;
	}//getPass

	@Override
	public String toString() {
		return "LoginVO [id=" + id + ", pass=" + pass + "]";
	}//toString
		
}//class
