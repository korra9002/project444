package adminControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import adminDAO.AdminDAO;
import adminVO.UserIdControlVO;
import adminView.AdminIdDetailView;

public class AdminUserIdDetailViewEvt implements ActionListener {
	
	private AdminIdDetailView aidv;
	private AdminMainEvt ame;
	
	public AdminUserIdDetailViewEvt(AdminIdDetailView aidv, AdminMainEvt ame ) {
		this.aidv=aidv;
		this.ame=ame;
		jbtActive();
	}//AdminUserIdDetailViewEvt
	
	private void jbtActive() {
		
		if (aidv.getJtfSuspendFlag().getText().equals("Y")) {
			aidv.getJbtRelief().setEnabled(true);
			aidv.getJbtSuspend().setEnabled(false);
		}else {
			aidv.getJbtRelief().setEnabled(false);
			aidv.getJbtSuspend().setEnabled(true);
		}//end if
		
	}//jbtActive
	
	private void controlSuspend() {
		
		String userId = aidv.getJtfId().getText();
		String suspendFlag = aidv.getJtfSuspendFlag().getText();
		String suspendMsg = "Y";
		
		if (!suspendFlag.equals("Y")) {
			suspendMsg = JOptionPane.showInputDialog("���� ���� �Է�");
		}//end if
//		System.out.println(suspendFlag);
		try {
				
			if(!suspendMsg.isEmpty()) {
				UserIdControlVO uicVO = new UserIdControlVO(userId, suspendFlag, suspendMsg);
				
				if(!suspendMsg.equals("Y")) {
					switch (JOptionPane.showConfirmDialog(aidv, "������ ���̵� �����ұ��?")) {
					case JOptionPane.OK_OPTION:
						
						AdminDAO aDAO=AdminDAO.getInstance();
						
						String msg = "���̵� ������ �� �����ϴ�.";
						
						if (aDAO.suspendControl(uicVO)) {
							msg="���̵� �����Ǿ����ϴ�.";
							
						}//end if
						
						JOptionPane.showMessageDialog(aidv, msg);
						exit();
					}//switch
					
				} else if(suspendMsg.equals("Y")){
					switch (JOptionPane.showConfirmDialog(aidv, "������ ���̵� �����ұ��?")) {
					case JOptionPane.OK_OPTION:
						
						AdminDAO aDAO=AdminDAO.getInstance();
						
						String msg = "���̵� ������ �� �����ϴ�.";
						
						if (aDAO.suspendControl(uicVO)) {
							msg="���̵� �����Ǿ����ϴ�.";
							
						}//end if
						
						JOptionPane.showMessageDialog(aidv, msg);
						exit();
					}//switch
					
				}//end else
				
			} else {
				JOptionPane.showMessageDialog(aidv, "���� ������ �Է����ּ���.");
			}//end else
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
//			npe.printStackTrace();
		}//end catch
		
	}//suspend
	
	private void exit() {
		aidv.dispose();
		ame.resetUserIdList();
	}//exit
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==aidv.getJbtOkey()) {
			exit();
			
		}//end if
		
		if(ae.getSource()==aidv.getJbtSuspend() || ae.getSource()==aidv.getJbtRelief()) {
			controlSuspend();
		}//end if
		
	}//actionPerformed

}//class
