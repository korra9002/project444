package userVO;

public class ForgotPwVO {
	private String id,pwHint,pwAnswer;

	public ForgotPwVO(String id, String pwHint, String pwAnswer) {
		super();
		this.id = id;
		this.pwHint = pwHint;
		this.pwAnswer = pwAnswer;
	}//ForgotPwVO

	public String getId() {
		return id;
	}//getId

	public String getPwHint() {
		return pwHint;
	}//getPwHint

	public String getPwAnswer() {
		return pwAnswer;
	}//getPwAnswer

	@Override
	public String toString() {
		return "ForgotPwVO [id=" + id + ", pwHint=" + pwHint + ", pwAnswer=" + pwAnswer + "]";
	}//toString
	
	
	
}//class
