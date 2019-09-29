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
	
	public AdminUserIdDetailViewEvt(AdminIdDetailView aidv, AdminMainEvt ame) {
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
		String[] tempData = {"",""};
		int period = 0;
		
		boolean whileFlag = false;
		AdminDAO aDAO=AdminDAO.getInstance();
		
		try {
			if (suspendFlag.equals("N")) {
				do {
					suspendMsg = JOptionPane.showInputDialog("<HTML>[���� ����, ���� �Ⱓ] �Է�<br/>���� �������� �Է����ּ���.");
					tempData = suspendMsg.split(",");
//					System.out.println("[" + tempData[0] + "]/[" + tempData[1] + "]");
					if (tempData.length != 2 ) {
						JOptionPane.showMessageDialog(aidv, "','�� �����Ͽ� ���Ŀ� �°� �Է����ּ���.", "�޽���", JOptionPane.ERROR_MESSAGE);
						continue;
					}//end if
					
					if (tempData[0].trim().equals("") || tempData[1].equals("")) {
						JOptionPane.showMessageDialog(aidv, "������ �Է� �Ұ��մϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
						continue;
					}//end if
//					System.out.println(aDAO.isNumber(tempData[1]));
					
					if (aDAO.isNumber(tempData[1])==false) {
						JOptionPane.showMessageDialog(aidv, "�Ⱓ�� ���ڸ� �Է� �����մϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
						continue;
					}//end if
					
					if(tempData.length == 2) {
						suspendMsg = (String) tempData[0];
						period = Integer.parseInt(tempData[1],10);
						
						UserIdControlVO uicVO = new UserIdControlVO(userId, suspendFlag, suspendMsg, period);
						
						switch (JOptionPane.showConfirmDialog(aidv, "������ ���̵� �����ұ��?", "���̵� ���� Ȯ��", JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE)) {
						case JOptionPane.OK_OPTION:
							
							String msg = "���̵� ������ �� �����ϴ�.";
							
							if (aDAO.suspendControl(uicVO)) {
								msg="���̵� �����Ǿ����ϴ�.";
								
							}//end if
							
							JOptionPane.showMessageDialog(aidv, msg);
							exit();
							ame.setUserIdList();
							whileFlag = true;
						}//switch
							
					}//end else
					
				}while(!whileFlag);//true �϶� �ݺ�
				
			}else if(suspendFlag.equals("Y")) {
				switch (JOptionPane.showConfirmDialog(aidv, "������ ���̵� �����ұ��?", "���̵� ���� Ȯ��", JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE)) {
				case JOptionPane.OK_OPTION:
					
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
			
		} catch (NullPointerException npe) {
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
//		System.out.println(suspendMsg + 11);
//		System.out.println(tempData.length);
		
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
