package adminControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import adminView.AdminLoginView;
import adminView.AdminMainView;

public class AdminLoginEvt implements ActionListener {

	private AdminLoginView al;
	
	public AdminLoginEvt(AdminLoginView al) {
		this.al = al;
	}//AdminLoginEvt
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == al.getJbtLogin()) {
			
			if (al.getJtfId().getText().equals("admin")&& al.getJpfPw().getPassword().equals("1234")) {
				new AdminMainView();
				al.dispose();
			} else {
				al.getJlLoginFail().setText("아이디 또는 비밀번호를 확인해주세요.");
				al.getJtfId().setText("");
				al.getJpfPw().setText("");
			}//end else
			
		}//end if
		
	}//actionPerformed

}//class
