package adminVO;

import java.util.Date;

public class SuspendIdVO {

	private String userId, suspendReason;
	private Date suspendDate;
	private int period;
	
	public SuspendIdVO(String userId, String suspendReason, Date suspendDate, int period) {
		this.userId = userId;
		this.suspendReason = suspendReason;
		this.suspendDate = suspendDate;
		this.period = period;
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

	public Date getSuspendDate() {
		return suspendDate;
	}

	public void setSuspendDate(Date suspendDate) {
		this.suspendDate = suspendDate;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

}//SuspendIdVO
