package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import userRun.RunMarketMain;
import userView.PersonalInform;
import userView.PwUpdate;

public class PersonalInformEvt implements ActionListener{
	private PersonalInform psi;
	private RunMarketMain rmm;
	public PersonalInformEvt(PersonalInform psi, RunMarketMain rmm) {
		this.psi=psi;
		this.rmm = rmm;
	}//PersonalInformEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==psi.getJbtPwUpdate()) {
			JOptionPane.showInputDialog(psi, "비밀번호 입력");
			new PwUpdate(rmm);
		}//end if
		
	}//actionPerformed
	
	
}//class
