package adminControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import adminDAO.AdminDAO;
import adminVO.CheckDetailVO;
import adminVO.CheckVO;
import adminView.AdminCheckDetailView;
import adminView.AdminIdDetailView;

public class AdminUserIdDetailViewEvt implements ActionListener {
	
	private AdminIdDetailView aidv;
	
	public AdminUserIdDetailViewEvt(AdminIdDetailView aidv) {
		this.aidv=aidv;
	}
	
private void suspend() {
		
		String userId= aidv.getJtfId().getText();
		String refuseMsg=JOptionPane.showInputDialog("정지 사유 입력");
		
		if(!refuseMsg.isEmpty()) {
			switch (JOptionPane.showConfirmDialog(aidv, "아이디를 정지 할까요?")) {
			case JOptionPane.OK_OPTION:
			
			AdminDAO aDAO=AdminDAO.getInstance();
			try {
				String msg = "정지안됨";
				if (aDAO.updateSuspend(userId, refuseMsg)) {
					msg="정지됨";
				};
				JOptionPane.showMessageDialog(aidv, msg);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}else {
			JOptionPane.showMessageDialog(aidv, "사유를 입력해 주세요");
		}
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==aidv.getJbtOkey()) {
			aidv.dispose();
		}
		if(e.getSource()==aidv.getJbtStop()) {
			suspend();
		}
	}

}
