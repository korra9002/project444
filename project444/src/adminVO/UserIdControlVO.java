package adminVO;

public class UserIdControlVO {

	private String userId, suspendFlag, suspendMsg;

	public UserIdControlVO(String userId, String suspendFlag, String suspendMsg) {
		this.userId = userId;
		this.suspendFlag = suspendFlag;
		this.suspendMsg = suspendMsg;
	}

	public String getUserId() {
		return userId;
	}

	public String getSuspendFlag() {
		return suspendFlag;
	}

	public String getSuspendMsg() {
		return suspendMsg;
	}

	@Override
	public String toString() {
		return "UserIdControlVO [userId=" + userId + ", suspendFlag=" + suspendFlag + ", suspendMsg=" + suspendMsg
				+ "]";
	}
	
}//UserIdVO
