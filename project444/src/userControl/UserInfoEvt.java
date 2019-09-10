package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import userRun.RunMarketMain;
import userView.PersonalInform;
import userView.UserInfo;

public class UserInfoEvt implements ActionListener {
	private UserInfo uif ;
	private RunMarketMain rmm;
	public UserInfoEvt(UserInfo uif) {
		this.uif = uif;
		this.rmm= rmm;
	}//UserInfoEvt
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==uif.getJbtPersonalData()) {
			String id = uif.getjlaId().getText();
			new PersonalInform(id,rmm);
		}//end if
		
	}//actionPerformed

}//class
