package userVO;

public class PersonalInformVO {
	private String name, gender,phone , loc;

	public PersonalInformVO(String name, String gender, String phone, String loc) {
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.loc = loc;
	}//SignUpVO


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


	@Override
	public String toString() {
		return "PersonalInformVO [name=" + name + ", gender=" + gender + ", phone=" + phone + ", loc=" + loc + "]";
	}//toString


	
}//class
