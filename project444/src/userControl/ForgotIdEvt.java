package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import userView.ForgotId;

public class ForgotIdEvt implements ActionListener {
	private ForgotId fi ;
	private String name="";
	public ForgotIdEvt(ForgotId fi) {
		this.fi=fi;
	}//ForgotIdEvt
	
	public void ForgotIdClose() {
		fi.dispose();
	}//ForgotIdClose
	public void searchId() {
		if(fi.getJtfName().getText().isEmpty()) {
			JOptionPane.showMessageDialog(fi, "이름을 입력해주세요.");
		}else {
		 name = fi.getJtfName().getText().trim();
		}
		
	}//searchId
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==fi.getJbtCancle()) {
			ForgotIdClose();
		}//end if
		if(ae.getSource()==fi.getJbtSearch()) {
			searchId();			
		}//end if
	}//actionPerformed

}//class
