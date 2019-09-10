package userVO;

public class SignUpVO {
	private String  id , pw , name, gender,phone , loc, pwHint ,pwAnswer;

	public SignUpVO(String id, String pw, String name, String gender, String phone, String loc, String pwHint,
			String pwAnswer) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.loc = loc;
		this.pwHint = pwHint;
		this.pwAnswer = pwAnswer;
	}//SignUpVO

	public String getId() {
		return id;
	}//getId

	public String getPw() {
		return pw;
	}//getPw

	public String getName() {
		return name;
	}//getName

	public String getGender() {
		return gender;
	}//getGender

	public String getPhone() {
		return phone;
	}//getPhone

	public String getLoc() {
		return loc;
	}//getLoc

	public String getPwHint() {
		return pwHint;
	}//getPwHint

	public String getPwAnswer() {
		return pwAnswer;
	}//getPwAnswer

	@Override
	public String toString() {
		return "SignUpVO [id=" + id + ", pw=" + pw + ", name=" + name + ", gender=" + gender + ", phone=" + phone
				+ ", loc=" + loc + ", pwHint=" + pwHint + ", pwAnswer=" + pwAnswer + "]";
	}//toString
	
	
}//class
