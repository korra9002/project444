package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import userView.PersonalInform;
import userView.UserInfo;

public class UserInfoEvt implements ActionListener {
	private UserInfo uif ;
	
	public UserInfoEvt(UserInfo uif) {
		this.uif = uif;
	}//UserInfoEvt
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==uif.getJbtPersonalData()) {
			String id = uif.getjlaId().getText();
			new PersonalInform(id);
		}//end if
		
	}//actionPerformed

}//class
