package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
			try {
				new PersonalInform(id,rmm);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end if
		
	}//actionPerformed

}//class
