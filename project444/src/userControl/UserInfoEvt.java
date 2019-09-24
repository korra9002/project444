package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import userRun.RunLogin;
import userRun.RunMarketMain;
import userView.InterestList;
import userView.Login;
import userView.PersonalInform;
import userView.PurchaseHistory;
import userView.SaleList;
import userView.UserInfo;

public class UserInfoEvt implements ActionListener {
	private UserInfo uif ;
	private RunMarketMain rmm;
	
	public UserInfoEvt(UserInfo uif, RunMarketMain rmm) {
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
		if(ae.getSource()==uif.getJbtSell()) {
			try {
				new SaleList(rmm);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end if
		if(ae.getSource()==uif.getJbtBuy()) {
			try {
				new PurchaseHistory(rmm);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end if
		if(ae.getSource()==uif.getJbtLike()) {
			new InterestList(rmm);
		}//end if
		if(ae.getSource()==uif.getjbtLogout()) {
			new Login();
			rmm.dispose();
		}
	}//actionPerformed

}//class
