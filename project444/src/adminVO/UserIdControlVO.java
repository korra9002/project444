package adminVO;

public class UserIdControlVO {

	private String userId, suspendFlag, suspendMsg;
	private int period;
	
	public UserIdControlVO(String userId, String suspendFlag, String suspendMsg, int period) {
		super();
		this.userId = userId;
		this.suspendFlag = suspendFlag;
		this.suspendMsg = suspendMsg;
		this.period = period;
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

	public int getPeriod() {
		return period;
	}

	@Override
	public String toString() {
		return "UserIdControlVO [userId=" + userId + ", suspendFlag=" + suspendFlag + ", suspendMsg=" + suspendMsg
				+ ", period=" + period + "]";
	}
	
}//UserIdVO
