package adminControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import adminDAO.AdminDAO;
import adminVO.CheckDetailVO;
import adminVO.CheckVO;
import adminView.AdminCheckDetailView;

public class AdminCheckDetailViewEvt implements ActionListener {
	
	private AdminCheckDetailView acdv;
	
	public AdminCheckDetailViewEvt(AdminCheckDetailView acdv) {
		this.acdv=acdv;
	}

	
	private void grant() {
		
		String productCode= acdv.getCode();
		switch (JOptionPane.showConfirmDialog(acdv, "판매 승인을 할까요?")) {
		case JOptionPane.OK_OPTION:
			
			AdminDAO aDAO=AdminDAO.getInstance();
			try {
				String msg = "변경안됨";
				if (aDAO.updateApproval(productCode)) {
					msg="변경됨";
				};
				JOptionPane.showMessageDialog(acdv, msg);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
private void refuse() {
		
		String productCode= acdv.getCode();
		String refuseMsg=JOptionPane.showInputDialog("거부 사유 입력");
		
		if(!refuseMsg.isEmpty()) {
			switch (JOptionPane.showConfirmDialog(acdv, "판매 거부를 할까요?")) {
			case JOptionPane.OK_OPTION:
			
			AdminDAO aDAO=AdminDAO.getInstance();
			try {
				String msg = "변경안됨";
				if (aDAO.updateRefuse(productCode, refuseMsg)) {
					msg="변경됨";
				};
				JOptionPane.showMessageDialog(acdv, msg);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}else {
			JOptionPane.showMessageDialog(acdv, "사유를 입력해 주세요");
		}
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==acdv.getJbtGrant()) {
			grant();
		}
		if(e.getSource()==acdv.getJbtReject()) {
			refuse();
		}
	}

}
