package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.util.cipher.DataEncrypt;
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
		String dePw = "";
		char[] cPw = lg.getJpfPw().getPassword(); 
		for(int i=0; i<cPw.length;i++) {
			pw = String.valueOf(cPw);
		}//end for
		//비밀번호 암호화
		try {
			dePw = DataEncrypt.messageDigest("MD5", pw);
		} catch (NoSuchAlgorithmException nae) {
			nae.printStackTrace();
		} // end catch
	if(!id.isEmpty()&&!pw.isEmpty()) {
		try {
			
			LoginVO lvo = new LoginVO(id, dePw);
			UserDAO uDAO = UserDAO.getInstance();
			
			String Login_name = uDAO.loginRun(lvo)[0];
			String suspend_flag = uDAO.loginRun(lvo)[1];
			if(suspend_flag.equals("N")) {
		if(!Login_name.isEmpty()) {
			
			new RunMarketMain(id);
			lg.dispose();
		
		}//end else
			}else {
				JOptionPane.showMessageDialog(lg, "정지된 계정입니다.");
			}
		} catch (SQLException e) {
			
		}catch(NullPointerException npe) {
			lg.getJlLoginFail().setBounds(180, 140, 240, 25);
			lg.getJlLoginFail().setText("아이디 또는 비밀번호를 확인해주세요.");
			lg.getJtfId().setText("");
			lg.getJpfPw().setText("");
			lg.getJtfId().requestFocus();
		}
	
	}else if(id.isEmpty()){
		lg.getJlLoginFail().setBounds(270, 140, 200, 25);
		lg.getJlLoginFail().setText("아이디를 입력해주세요.");
		lg.getJtfId().requestFocus();
	}else if(pw.isEmpty()){
		lg.getJlLoginFail().setBounds(270, 140, 200, 25);
		lg.getJlLoginFail().setText("비밀번호를 입력해주세요.");
		lg.getJpfPw().requestFocus();
	}
		
	}//loginRun
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == lg.getJbtSignUp()) {
			new SignUp(lg);
		} // end if
		if (ae.getSource() == lg.getJbtForgotId()) {
			new ForgotId(lg);
		} // end if
		if (ae.getSource() == lg.getJbtForgotPw()) {
			new ForgotPw(lg);
		} // end if
		if(ae.getSource()==lg.getJbtLogin()||ae.getSource()==lg.getJtfId()||ae.getSource()==lg.getJpfPw()) {
			loginRun();
		}//end if
		
	}//actionPerformed

}//class
