package adminVO;

public class UserIdListVO {
											

	private String user_id, user_name, gender, phone, loc, join_date, suspend_flag ;

	public UserIdListVO(String user_id, String user_name, String gender, String phone, String loc, String join_date,
			String suspend_flag) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.gender = gender;
		this.phone = phone;
		this.loc = loc;
		this.join_date = join_date;
		this.suspend_flag = suspend_flag;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getGender() {
		return gender;
	}

	public String getPhone() {
		return phone;
	}

	public String getLoc() {
		return loc;
	}

	public String getJoin_date() {
		return join_date;
	}

	public String getSuspend_flag() {
		return suspend_flag;
	}

	@Override
	public String toString() {
		return "UserIdListVO [user_id=" + user_id + ", user_name=" + user_name + ", gender=" + gender + ", phone="
				+ phone + ", loc=" + loc + ", join_date=" + join_date + ", suspend_flag=" + suspend_flag + "]";
	}
	
}//class
