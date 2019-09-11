package userVO;

public class PersonalInformVO {
	private String id,pw,name,gender,phone,pwAnswer,loc,pwHint;

	public PersonalInformVO(String id, String pw, String name, String gender, String phone, String pwAnswer, String loc,
			String pwHint) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.pwAnswer = pwAnswer;
		this.loc = loc;
		this.pwHint = pwHint;
	}//PersonalInformVO

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

	public String getPwAnswer() {
		return pwAnswer;
	}//getPwAnswer

	public String getLoc() {
		return loc;
	}//getLoc

	public String getPwHint() {
		return pwHint;
	}//getPwHint
	
	
	
}//class
