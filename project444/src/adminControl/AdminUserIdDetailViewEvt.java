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
	
	public AdminUserIdDetailViewEvt(AdminIdDetailView aidv) {
		this.aidv=aidv;
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
		String[] tempData = {"",""};
		boolean whileFlag = false;
		int period = 0;
		
		try {
			if (!suspendFlag.equals("Y")) {
				do {
					suspendMsg = JOptionPane.showInputDialog("<HTML>[���� ����, ���� �Ⱓ] �Է�<br/>���� �������� �Է����ּ���.");
					tempData = suspendMsg.split(",");
					if (tempData.length != 2) {
						JOptionPane.showMessageDialog(aidv, "���Ŀ� �°� �Է����ּ���.");
						
					}//end if
					
				}while(!whileFlag);
				
				suspendMsg = (String) tempData[0];
				period = Integer.parseInt(tempData[1],10);
				
			}//end if
			
		} catch (NullPointerException npe) {
			
		}
//		System.out.println(suspendMsg + 11);
//		System.out.println(tempData.length);
		try {
			if(suspendMsg.equals("Y")){
				switch (JOptionPane.showConfirmDialog(aidv, "������ ���̵� �����ұ��?")) {
				case JOptionPane.OK_OPTION:
					
					AdminDAO aDAO=AdminDAO.getInstance();
					UserIdControlVO uicVO = new UserIdControlVO(userId, suspendFlag, suspendMsg, period);
					String msg = "���̵� ������ �� �����ϴ�.";
					
					if (aDAO.suspendControl(uicVO)) {
						msg="���̵� �����Ǿ����ϴ�.";
						
					}//end if
					
					JOptionPane.showMessageDialog(aidv, msg);
					exit();
					ame.setUserIdList();
				}//switch
				
			}//end else
			
			if(tempData.length == 2) {
				
				UserIdControlVO uicVO = new UserIdControlVO(userId, suspendFlag, suspendMsg, period);
				
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
						ame.setUserIdList();
					}//switch
					
				} 
				
			} else {
				JOptionPane.showMessageDialog(aidv, "���� ���� �� �Ⱓ�� �ٽ� �Է����ּ���.");
			}//end else
			
			
				
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
//			npe.printStackTrace();
		}//end catch
		
	}//suspend
	
	private void exit() {
		aidv.dispose();
		
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
