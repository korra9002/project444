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
		switch (JOptionPane.showConfirmDialog(acdv, "�Ǹ� ������ �ұ��?")) {
		case JOptionPane.OK_OPTION:
			
			AdminDAO aDAO=AdminDAO.getInstance();
			try {
				String msg = "����ȵ�";
				if (aDAO.updateApproval(productCode)) {
					msg="�����";
				};
				JOptionPane.showMessageDialog(acdv, msg);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
private void refuse() {
		
		String productCode= acdv.getCode();
		String refuseMsg=JOptionPane.showInputDialog("�ź� ���� �Է�");
		
		if(!refuseMsg.isEmpty()) {
			switch (JOptionPane.showConfirmDialog(acdv, "�Ǹ� �źθ� �ұ��?")) {
			case JOptionPane.OK_OPTION:
			
			AdminDAO aDAO=AdminDAO.getInstance();
			try {
				String msg = "����ȵ�";
				if (aDAO.updateRefuse(productCode, refuseMsg)) {
					msg="�����";
				};
				JOptionPane.showMessageDialog(acdv, msg);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}else {
			JOptionPane.showMessageDialog(acdv, "������ �Է��� �ּ���");
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
