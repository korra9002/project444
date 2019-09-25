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
		String[] tempData = {"",""};
		int period = 0;
		
		try {
			if (!suspendFlag.equals("Y")) {
				suspendMsg = JOptionPane.showInputDialog("<HTML>[정지 사유, 정지 기간] 입력<br/>위의 형식으로 입력해주세요.");
				tempData = suspendMsg.split(",");
				suspendMsg = (String) tempData[0];
				period = Integer.parseInt(tempData[1],10);
			}//end if
		} catch (NullPointerException npe) {
			JOptionPane.showMessageDialog(aidv, "형식에 맞게 입력해주세요.");
		}
//		System.out.println(suspendMsg + 11);
//		System.out.println(tempData.length);
		try {
			if(suspendMsg.equals("Y")){
				switch (JOptionPane.showConfirmDialog(aidv, "정말로 아이디를 복구할까요?")) {
				case JOptionPane.OK_OPTION:
					
					AdminDAO aDAO=AdminDAO.getInstance();
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
			
			if(tempData.length == 2) {
				
				UserIdControlVO uicVO = new UserIdControlVO(userId, suspendFlag, suspendMsg, period);
				
				if(!suspendMsg.equals("Y")) {
					switch (JOptionPane.showConfirmDialog(aidv, "정말로 아이디를 정지할까요?")) {
					case JOptionPane.OK_OPTION:
						
						AdminDAO aDAO=AdminDAO.getInstance();
						
						String msg = "아이디를 정지할 수 없습니다.";
						
						if (aDAO.suspendControl(uicVO)) {
							msg="아이디가 정지되었습니다.";
							
						}//end if
						
						JOptionPane.showMessageDialog(aidv, msg);
						exit();
						ame.setUserIdList();
					}//switch
					
				} 
				
			} else {
				JOptionPane.showMessageDialog(aidv, "정지 사유 및 기간을 다시 입력해주세요.");
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
