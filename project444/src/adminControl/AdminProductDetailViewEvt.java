package adminControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import adminDAO.AdminDAO;
import adminVO.CheckDetailVO;
import adminVO.CheckVO;
import adminView.AdminCheckDetailView;
import adminView.AdminProductDetailView;

public class AdminProductDetailViewEvt implements ActionListener {
	
	AdminProductDetailView apdv;
	private AdminMainEvt ame;
	
	public AdminProductDetailViewEvt(AdminProductDetailView apdv, AdminMainEvt ame) {
		this.apdv = apdv;
		this.ame = ame;
	}//AdminCheckDetailViewEvt

	private void close() {
		apdv.dispose();
	}//close
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==apdv.getJbtOk()) {
			close();
		}//end if
		
	}//actionPerformed

}//class
