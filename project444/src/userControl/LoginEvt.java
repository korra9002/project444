package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.LoginVO;
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
	
	public void loginRun() {
		String id = lg.getJtfId().getText();
		String pw = "";
		char[] cPw = lg.getJpfPw().getPassword(); 
		for(int i=0; i<cPw.length;i++) {
			pw = String.valueOf(cPw);
		}//end for
	if(!id.isEmpty()&&!pw.isEmpty()) {
		try {
			
			LoginVO lvo = new LoginVO(id, pw);
			UserDAO uDAO = UserDAO.getInstance();
			
			String Login_name = uDAO.loginRun(lvo)[0];
			String suspend_flag = uDAO.loginRun(lvo)[1];
			if(suspend_flag.equals("N")) {
		if(!Login_name.isEmpty()) {
			JOptionPane.showMessageDialog(lg, Login_name+"님 안녕하세요!");
			
			new RunMarketMain();
			lg.dispose();
		
		}//end else
			}else {
				JOptionPane.showMessageDialog(lg, "정지된 계정입니다.");
			}
		} catch (SQLException e) {
			
		}catch(NullPointerException npe) {
			lg.getJlLoginFail().setText("아이디 또는 비밀번호를 확인해주세요.");
		}
	
	}//end if
		
	}//loginRun
	
	
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
		if(ae.getSource()==lg.getJbtLogin()||ae.getSource()==lg.getJtfId()||ae.getSource()==lg.getJpfPw()) {
			loginRun();
		}//end if
		
	}//actionPerformed

}//class
