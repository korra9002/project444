package adminView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginEvt implements ActionListener {

	private AdminLogin al;
	
	public AdminLoginEvt(AdminLogin al) {
		this.al = al;
	}//AdminLoginEvt
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == al.getJbtLogin()) {
			
			if (al.getJtfId().getText().equals("admin")&& al.getJpfPw().getText().equals("1234")) {
				new adminView();
				al.dispose();
			} else {
				al.getJlLoginFail().setText("���̵� �Ǵ� ��й�ȣ�� Ȯ�����ּ���.");
				al.getJtfId().setText("");
				al.getJpfPw().setText("");
			}//end else
			
		}//end if
		
	}//actionPerformed

}//class
