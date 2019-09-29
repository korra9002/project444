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
		
		switch (JOptionPane.showConfirmDialog(acdv,"�Ǹ� ����ó�� �Ͻðڽ��ϱ�?","â�ݱ�",JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE)) {
		case JOptionPane.OK_OPTION:
			AdminDAO aDAO=AdminDAO.getInstance();
			
			try { 
				 msg = "�Ǹ� ���ΰ� ������� �ʾҽ��ϴ�.";
				
				if (aDAO.updateApproval(productCode)) {
					msg="�ش� ��ǰ�� �Ǹ� ���� ó���Ǿ����ϴ�.";
					
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
				refuseMsg = JOptionPane.showInputDialog("�Ǹ� �ź� ������ �Է����ּ���.");
				
				if(!refuseMsg.trim().isEmpty()) {
					switch (JOptionPane.showConfirmDialog(acdv,"�Ǹ� �ź�ó�� �Ͻðڽ��ϱ�?","â�ݱ�",JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE)) {
					case JOptionPane.OK_OPTION:
					
						AdminDAO aDAO=AdminDAO.getInstance();
						String msg = "�Ǹ� ���ΰ� ������� �ʾҽ��ϴ�.";
						
						if (aDAO.updateRefuse(productCode, refuseMsg)) {
							msg="�ش� ��ǰ�� �Ǹ� �ź� ó���Ǿ����ϴ�.";
							
						}//end if
						JOptionPane.showMessageDialog(acdv, msg);
						flag = true;
						
					}//end switch
					
				}else {
					JOptionPane.showMessageDialog(acdv, "�ź� ���� �Է��� �ʼ��Դϴ�.", "�޽���", JOptionPane.ERROR_MESSAGE);
					
				}//end else
				
			}while(!flag);//true�� �� �ݺ�
			
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
