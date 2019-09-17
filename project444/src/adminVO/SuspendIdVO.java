package adminVO;

public class SuspendIdVO {

	private String userId, suspendReason, suspendDate;

	public SuspendIdVO(String userId, String suspendReason, String suspendDate) {
		this.userId = userId;
		this.suspendReason = suspendReason;
		this.suspendDate = suspendDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSuspendReason() {
		return suspendReason;
	}

	public void setSuspendReason(String suspendReason) {
		this.suspendReason = suspendReason;
	}

	public String getSuspendDate() {
		return suspendDate;
	}

	public void setSuspendDate(String suspendDate) {
		this.suspendDate = suspendDate;
	}

	@Override
	public String toString() {
		return "SuspendIdVO [userId=" + userId + ", suspendReason=" + suspendReason + ", suspendDate=" + suspendDate
				+ "]";
	}

}//SuspendIdVO
