package adminControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import adminView.AdminLoginView;
import adminView.AdminMainView;

public class AdminLoginEvt implements ActionListener {

	private AdminLoginView alv;
	
	public AdminLoginEvt(AdminLoginView alv) {
		this.alv = alv;
	}//AdminLoginEvt
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == alv.getJbtLogin()) {
			new AdminMainView();
				alv.dispose();
//			if (alv.getJtfId().getText().equals("admin")&& alv.getJpfPw().getText().equals("1234")) {
//				new AdminMainView();
//				alv.dispose();
//			} else {
//				alv.getJlLoginFail().setText("���̵� �Ǵ� ��й�ȣ�� Ȯ�����ּ���.");
//				alv.getJtfId().setText("");
//				alv.getJpfPw().setText("");
//			}//end else
			
		}//end if
		
	}//actionPerformed

}//class
