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
		String refuseMsg=JOptionPane.showInputDialog("���� ���� �Է�");
		
		if(!refuseMsg.isEmpty()) {
			switch (JOptionPane.showConfirmDialog(aidv, "���̵� ���� �ұ��?")) {
			case JOptionPane.OK_OPTION:
			
			AdminDAO aDAO=AdminDAO.getInstance();
			try {
				String msg = "�����ȵ�";
				if (aDAO.updateSuspend(userId, refuseMsg)) {
					msg="������";
				};
				JOptionPane.showMessageDialog(aidv, msg);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}else {
			JOptionPane.showMessageDialog(aidv, "������ �Է��� �ּ���");
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
