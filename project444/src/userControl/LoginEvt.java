package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import userRun.RunMarketMain;
import userView.ForgotId;
import userView.ForgotPw;
import userView.Login;
import userView.MarketMain;
import userView.SignUp;



public class LoginEvt implements ActionListener {
	private Login lg;
	public LoginEvt(Login lg) {
		this.lg=lg;
		
	}//LoginEvt
	
	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == lg.getJbtSignUp()) {
			new SignUp();
		} // end if
		if (ae.getSource() == lg.getJbtForgotId()) {
			new ForgotId();
		} // end if
		if (ae.getSource() == lg.getJbtForgotPw()) {
			new ForgotPw();
		} // end if
		if(ae.getSource()==lg.getJbtLogin()) {
			String id = lg.getJtfId().getText();
			String pw = "";
			char[] cPw = lg.getJpfPw().getPassword(); 
			for(int i=0; i<cPw.length;i++) {
				pw = String.valueOf(cPw);
			}//end for
			System.out.println("id : "+id+ "pw : "+pw);
		if(!id.isEmpty()&&!pw.isEmpty()) {
			try {
				new MarketMain();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}else {
			lg.getJlLoginFail().setText("아이디 또는 비밀번호를 확인해주세요.");
		}
		}//end if
		
	}//actionPerformed

}//class
