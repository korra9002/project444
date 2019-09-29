package adminControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

import adminFileServer.AdminFileRecieve;
import adminFileServer.AdminFileSend;
import adminView.AdminLoginView;
import adminView.AdminMainView;
import kr.co.sist.util.cipher.DataEncrypt;

public class AdminLoginEvt implements ActionListener {

	private AdminLoginView alv;
	
	public AdminLoginEvt(AdminLoginView alv) {
		this.alv = alv;
		
	}//AdminLoginEvt
	
	private void login() { 
		String id = alv.getJtfId().getText();
		String pw = "";
		char[] cPw = alv.getJpfPw().getPassword();
		
		for (int i = 0; i < cPw.length; i++) {
			pw = String.valueOf(cPw);
		}//end for
		
		if (id.equals("admin") && pw.equals("1234")) {
			new AdminMainView();
			try {
				new AdminFileRecieve().start();
				new AdminFileSend().start();
			} catch (IOException e) {
				e.printStackTrace();
			}//end catch 
			alv.dispose();
		}else {
			JOptionPane.showMessageDialog(alv, "아이디 또는 비밀번호를 확인해주세요.");
			alv.getJtfId().setText("");
			alv.getJpfPw().setText("");
			alv.getJpfPw().requestFocus();
		}//end else
		
	}//login
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == alv.getJbtLogin() || ae.getSource() == alv.getJpfPw()) {
			login();
			
		}//end if
		
	}//actionPerformed

}//class
