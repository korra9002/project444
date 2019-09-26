package adminControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import adminDAO.AdminDAO;
import adminView.AdminCheckDetailView;

public class AdminCheckDetailViewEvt implements ActionListener {
	
	private AdminCheckDetailView acdv;
	private AdminMainEvt ame;
	
	public AdminCheckDetailViewEvt(AdminCheckDetailView acdv) {
		this.acdv = acdv;
	}//AdminCheckDetailViewEvt
	
	private void grant() {
		
		String productCode= acdv.getCode();
		switch (JOptionPane.showConfirmDialog(acdv, "판매 승인을 할까요?")) {
		case JOptionPane.OK_OPTION:
			
			AdminDAO aDAO=AdminDAO.getInstance();
			try {
				String msg = "변경안됨";
				
				if (aDAO.updateApproval(productCode)) {
					msg="변경됨";
				}//end if
				
				JOptionPane.showMessageDialog(acdv, msg);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException npe) {
				
			}//end catch
			
			close();
			ame.setCheckList();
			
		}//end switch
		
	}//grant
	
	private void refuse() {
		
		String productCode= acdv.getCode();
		String refuseMsg=JOptionPane.showInputDialog("거부 사유 입력");
		
		try {
			if(!refuseMsg.isEmpty()) {
				switch (JOptionPane.showConfirmDialog(acdv, "판매 거부를 할까요?")) {
				case JOptionPane.OK_OPTION:
				
					AdminDAO aDAO=AdminDAO.getInstance();
					String msg = "변경안됨";
					if (aDAO.updateRefuse(productCode, refuseMsg)) {
						msg="변경됨";
					}//end if
					JOptionPane.showMessageDialog(acdv, msg);
					
					close();
					ame.setCheckList();
					
				}//end switch
				
			}else {
				JOptionPane.showMessageDialog(acdv, "사유를 입력해 주세요");
			}//end else
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
			
		}//end catch
		
	}//refuse
	
	private void close() {
		acdv.dispose();
	}//close
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==acdv.getJbtGrant()) {
			grant();
		}//end if
		
		if(ae.getSource()==acdv.getJbtReject()) {
			refuse();
		}//end if
		
		if(ae.getSource()==acdv.getJbtOk()) {
			close();
		}//end if
		
	}//actionPerformed

}//class
