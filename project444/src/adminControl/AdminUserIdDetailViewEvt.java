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
					suspendMsg = JOptionPane.showInputDialog("<HTML>[정지 사유, 정지 기간] 입력<br/>위의 형식으로 입력해주세요.");
					tempData = suspendMsg.split(",");
//					System.out.println("[" + tempData[0] + "]/[" + tempData[1] + "]");
					if (tempData.length != 2 ) {
						JOptionPane.showMessageDialog(aidv, "','로 구분하여 형식에 맞게 입력해주세요.", "메시지", JOptionPane.ERROR_MESSAGE);
						continue;
					}//end if
					
					if (tempData[0].trim().equals("") || tempData[1].equals("")) {
						JOptionPane.showMessageDialog(aidv, "공백은 입력 불가합니다.", "메시지", JOptionPane.ERROR_MESSAGE);
						continue;
					}//end if
//					System.out.println(aDAO.isNumber(tempData[1]));
					
					if (aDAO.isNumber(tempData[1])==false) {
						JOptionPane.showMessageDialog(aidv, "기간은 숫자만 입력 가능합니다.", "메시지", JOptionPane.ERROR_MESSAGE);
						continue;
					}//end if
					
					if(tempData.length == 2) {
						suspendMsg = (String) tempData[0];
						period = Integer.parseInt(tempData[1],10);
						
						UserIdControlVO uicVO = new UserIdControlVO(userId, suspendFlag, suspendMsg, period);
						
						switch (JOptionPane.showConfirmDialog(aidv, "정말로 아이디를 정지할까요?", "아이디 정지 확인", JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE)) {
						case JOptionPane.OK_OPTION:
							
							String msg = "아이디를 정지할 수 없습니다.";
							
							if (aDAO.suspendControl(uicVO)) {
								msg="아이디가 정지되었습니다.";
								
							}//end if
							
							JOptionPane.showMessageDialog(aidv, msg);
							exit();
							ame.setUserIdList();
							whileFlag = true;
						}//switch
							
					}//end else
					
				}while(!whileFlag);//true 일때 반복
				
			}else if(suspendFlag.equals("Y")) {
				switch (JOptionPane.showConfirmDialog(aidv, "정말로 아이디를 복구할까요?", "아이디 복구 확인", JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE)) {
				case JOptionPane.OK_OPTION:
					
					UserIdControlVO uicVO = new UserIdControlVO(userId, suspendFlag, suspendMsg, period);
					String msg = "아이디를 복구할 수 없습니다.";
					
					if (aDAO.suspendControl(uicVO)) {
						msg="아이디가 복구되었습니다.";
						
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
