package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import userDAO.UserDAO;
import userRun.RunMarketMain;
import userView.InterestList;
import userView.Login;
import userView.PersonalInform;
import userView.PurchaseHistory;
import userView.SaleList;
import userView.UserInfo;

public class UserInfoEvt implements ActionListener {
	private UserInfo uif;
	private RunMarketMain rmm;

	public UserInfoEvt(UserInfo uif, RunMarketMain rmm) {
		this.uif = uif;
		this.rmm = rmm;
		setGrade();
	}// UserInfoEvt

	public void setGrade() {
		UserDAO uDAO = UserDAO.getInstance();
		int cnt = 0;
		try {
			cnt = uDAO.grade();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JLabel jlLevelImg = uif.getJlLevelImg();
		
		int level = 0;
		
		if(cnt ==0) {
			
			level = 1;
		}else if(cnt>0 && cnt <3) {
			level = 2;
		}else if(cnt>=3 && cnt <5) {
			
			level = 3;
		}else if(cnt>=5 && cnt <10) {
			
			level = 4;
		}else if(cnt >=10) {
			
			level = 5;
		}
		System.out.println("플래그 총 횟수:"+cnt);
		System.out.println("레벨:"+level);
		 
		jlLevelImg.setIcon(new ImageIcon("C:/Users/seoyy/git/project444/project444/src/image/바나나레벨"+level+".png"));
//		System.out.println(jlLevelImg.getIcon().toString());
//	jlLevelImg = new JLabel(new ImageIcon("C:/Users/owner/git/project444/project444/src/image/2016-03-11_16;56;13.png"));

	}// setGrade

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == uif.getJbtPersonalData()) {
			PwUpdateEvt.uFlag = false;
			String id = uif.getjlaId().getText();
			try {
				new PersonalInform(id, rmm);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end if
		if (ae.getSource() == uif.getJbtSell()) {
			try {
				new SaleList(rmm);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		} // end if
		if (ae.getSource() == uif.getJbtBuy()) {
			try {
				new PurchaseHistory(rmm);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end if
		if (ae.getSource() == uif.getJbtLike()) {
			new InterestList(rmm);
		} // end if
		if (ae.getSource() == uif.getjbtLogout()) {
			new Login();
			rmm.dispose();
		}
	}// actionPerformed

}// class
