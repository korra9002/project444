package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import userRun.RunMarketMain;
import userView.InterestList;
import userView.PersonalInform;
import userView.PurchaseHistory;
import userView.SaleList;
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
		if(ae.getSource()==uif.getJbtSell()) {
			new SaleList(rmm);
		}//end if
		if(ae.getSource()==uif.getJbtBuy()) {
			new PurchaseHistory(rmm);
		}//end if
		if(ae.getSource()==uif.getJbtLike()) {
			new InterestList(rmm);
		}//end if
		
	}//actionPerformed

}//class
