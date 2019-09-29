package adminControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import adminDAO.AdminDAO;
import adminView.AdminCheckDetailView;

public class AdminCheckDetailViewEvt implements ActionListener {
	
	private AdminCheckDetailView acdv;
	private AdminMainEvt ame;
	
	public AdminCheckDetailViewEvt(AdminCheckDetailView acdv, AdminMainEvt ame) {
		this.acdv = acdv;
		this.ame = ame;
	}//AdminCheckDetailViewEvt
	
	private void grant() {
		String productCode=  acdv.getCode();
		String msg = "";
		
		switch (JOptionPane.showConfirmDialog(acdv,"판매 승인처리 하시겠습니까?","창닫기",JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE)) {
		case JOptionPane.OK_OPTION:
			AdminDAO aDAO=AdminDAO.getInstance();
			
			try { 
				 msg = "판매 여부가 변경되지 않았습니다.";
				
				if (aDAO.updateApproval(productCode)) {
					msg="해당 제품은 판매 승인 처리되었습니다.";
					
				}//end if
				
				JOptionPane.showMessageDialog(acdv, msg);
				close();
				ame.setCheckList();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException npe) {
				
			}//end catch
			
		}//end switch
		
	}//grant
	
	private void refuse() {
		boolean flag = false;
		String productCode= acdv.getCode();
		String refuseMsg= "";
		
		try {
			do {
				refuseMsg = JOptionPane.showInputDialog("판매 거부 사유를 입력해주세요.");
				
				if(!refuseMsg.trim().isEmpty()) {
					switch (JOptionPane.showConfirmDialog(acdv,"판매 거부처리 하시겠습니까?","창닫기",JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE)) {
					case JOptionPane.OK_OPTION:
					
						AdminDAO aDAO=AdminDAO.getInstance();
						String msg = "판매 여부가 변경되지 않았습니다.";
						
						if (aDAO.updateRefuse(productCode, refuseMsg)) {
							msg="해당 제품은 판매 거부 처리되었습니다.";
							
						}//end if
						JOptionPane.showMessageDialog(acdv, msg);
						flag = true;
						
					}//end switch
					
				}else {
					JOptionPane.showMessageDialog(acdv, "거부 사유 입력은 필수입니다.", "메시지", JOptionPane.ERROR_MESSAGE);
					
				}//end else
				
			}while(!flag);//true일 때 반복
			
			close();
			ame.setCheckList();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException npe) {
//			npe.printStackTrace();
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
