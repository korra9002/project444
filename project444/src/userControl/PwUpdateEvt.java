package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import userView.PwUpdate;

public class PwUpdateEvt implements ActionListener {
	PwUpdate pu;
	public PwUpdateEvt(PwUpdate pu) {
		this.pu=pu;
	}//PwUpdateEvt
	
	public void modifyPw() {
		char[] curPw = pu.getJpfCurrentPw().getPassword();
		System.out.println(curPw);
	}
	
	
	public void PwUpdateClose() {
		pu.dispose();
	}//PwUpdateClose
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==pu.getJbtCancle()) {
			PwUpdateClose();
		}//end if
		if(ae.getSource()==pu.getJbtOk()) {
			modifyPw();
		}//end if
	}//actionPerformed

}//class
