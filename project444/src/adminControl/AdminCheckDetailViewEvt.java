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
	private AdminMainEvt ame;
	
	public AdminCheckDetailViewEvt(AdminCheckDetailView acdv, AdminMainEvt ame) {
		this.acdv = acdv;
		this.ame = ame;
	}//AdminCheckDetailViewEvt
	
	private void grant() {
		
		String productCode= acdv.getCode();
		switch (JOptionPane.showConfirmDialog(acdv, "�Ǹ� ������ �ұ��?")) {
		case JOptionPane.OK_OPTION:
			
			AdminDAO aDAO=AdminDAO.getInstance();
			try {
				String msg = "����ȵ�";
				
				if (aDAO.updateApproval(productCode)) {
					msg="�����";
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
		String refuseMsg=JOptionPane.showInputDialog("�ź� ���� �Է�");
		
		try {
			if(!refuseMsg.isEmpty()) {
				switch (JOptionPane.showConfirmDialog(acdv, "�Ǹ� �źθ� �ұ��?")) {
				case JOptionPane.OK_OPTION:
				
					AdminDAO aDAO=AdminDAO.getInstance();
					String msg = "����ȵ�";
					if (aDAO.updateRefuse(productCode, refuseMsg)) {
						msg="�����";
					}//end if
					JOptionPane.showMessageDialog(acdv, msg);
					
					close();
					ame.setCheckList();
					
				}//end switch
				
			}else {
				JOptionPane.showMessageDialog(acdv, "������ �Է��� �ּ���");
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
