package adminControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import adminDAO.AdminDAO;
import adminVO.CheckListVO;
import adminVO.CheckVO;
import adminVO.UserIdDetailVO;
import adminVO.UserIdVO;
import adminVO.CheckDetailVO;
import adminView.AdminCheckDetailView;
import adminView.AdminIdDetailView;
import adminView.AdminMainView;

public class AdminMainEvt extends MouseAdapter implements ActionListener{

	private AdminMainView amv;
	private int index;
	private String[] categoryTemp ;
//	private OrderThread ot;
	
	public AdminMainEvt(AdminMainView amv) {
		this.amv = amv;
		setCheckList();
	}//LunchMainEvt

	
	/////////////////////////////////////////////////////////////////////ù��° ��//////////////////////////////////////////////////////////////////////////
	/**
	 * DBMS���̺��� ��ȸ�� �˼����� ����Ʈ�� JTable�� ���� 
	 */
	private void setCheckList() {
		DefaultTableModel dtm = amv.getDtmCheckList();
		String value = null;
		String col_name = null;
		String category = null;
		
		category = (String) amv.getJcbCategory1().getSelectedItem();
		value = amv.getJtfSearch1().getText().trim();
		
		if (amv.getJrbSubject1().isSelected()) {
			col_name = amv.getJrbSubject1().getText();
		}else {
			col_name = amv.getJrbID1().getText();
		}//end else
		
		CheckVO cv = new CheckVO(category, col_name, value);
		//JTable�� ���ڵ� �ʱ�ȭ
		dtm.setRowCount(0);
		Object[] rowData = null;//JTable�� ���� ������
		
		//DBMS���� ��ȸ
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			List<CheckListVO> list = aDAO.selectAllCheckList(cv);
			
			if(list.isEmpty()) {//��ǰ�� ���� ���
				JOptionPane.showMessageDialog(amv, "�˼��� ��ǰ�� �����ϴ�.");
				resetCheckList();
			}//end if
			
			CheckListVO clv = null;
			
			for(int i = 0 ; i < list.size() ; i++) {
				clv = list.get(i);
				
				//��ȸ����� JTable ���ڵ忡 �� �����͸� �����ϰ�, dtm�� �߰�
				rowData = new Object[7];
				
				//�迭�� ���� �Ҵ�
				rowData[0] = clv.getProduct_code();
				rowData[1] = clv.getImg_file();
				rowData[2] = clv.getUser_id();
				rowData[3] = clv.getCategory();
				rowData[4] = clv.getProduct_name();
				rowData[5] = new Integer(clv.getPrice());
				rowData[6] = clv.getUpload_date();
				
				//dtm�� �߰�
				dtm.addRow(rowData);
				
			}//end for
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(amv, "�����ڴ�, db���񽺰� ��Ȱ���� ���� �� �˼��մϴ�.");
			e.printStackTrace();
		}//end catch
		
	}//setLunchList

	private void setRecentList1() {
		DefaultTableModel dtm = amv.getDtmCheckList();
		String value = null;
		String col_name = null;
		String category = null;
		
		category = (String) amv.getJcbCategory1().getSelectedItem();
		value = amv.getJtfSearch1().getText().trim();
		
		if (amv.getJrbSubject1().isSelected()) {
			col_name = amv.getJrbSubject1().getText();
		}else {
			col_name = amv.getJrbID1().getText();
		}//end else
		
		CheckVO cv = new CheckVO(category, col_name, value);
		//JTable�� ���ڵ� �ʱ�ȭ
		dtm.setRowCount(0);
		Object[] rowData = null;//JTable�� ���� ������
		
		//DBMS���� ��ȸ
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			List<CheckListVO> list = aDAO.selectOrderbyList(cv);
			
			if(list.isEmpty()) {//��ǰ�� ���� ���
				JOptionPane.showMessageDialog(amv, "������ ��ǰ�� �����ϴ�.");
			}//end if
			
			CheckListVO clv = null;
			
			for(int i = 0 ; i < list.size() ; i++) {
				clv = list.get(i);
				
				//��ȸ����� JTable ���ڵ忡 �� �����͸� �����ϰ�, dtm�� �߰�
				rowData = new Object[7];
				
				//�迭�� ���� �Ҵ�
				rowData[0] = clv.getProduct_code();
				rowData[1] = clv.getImg_file();
				rowData[2] = clv.getUser_id();
				rowData[3] = clv.getCategory();
				rowData[4] = clv.getProduct_name();
				rowData[5] = new Integer(clv.getPrice());
				rowData[6] = clv.getUpload_date();
				
				//dtm�� �߰�
				dtm.addRow(rowData);
				
			}//end for
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(amv, "�����ڴ�, �ֽż� ���� ����! ���񽺰� ��Ȱ���� ���� �� �˼��մϴ�.");
			e.printStackTrace();
		}//end catch
		
	}//setRecentList1
	
	private void resetCheckList() {
		DefaultTableModel dtm = amv.getDtmCheckList();
		//JTable�� ���ڵ� �ʱ�ȭ
		dtm.setRowCount(0);
		Object[] rowData = null;//JTable�� ���� ������
		
		//DBMS���� ��ȸ
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			List<CheckListVO> list = aDAO.reselectAllCheckList();
			
			if(list.isEmpty()) {//��ǰ�� ���� ���
				JOptionPane.showMessageDialog(amv, "�˼��� ��ǰ�� �����ϴ�.");
			}//end if
			
			CheckListVO clv = null;
			
			for(int i = 0 ; i < list.size() ; i++) {
				clv = list.get(i);
				
				//��ȸ����� JTable ���ڵ忡 �� �����͸� �����ϰ�, dtm�� �߰�
				rowData = new Object[7];
				
				//�迭�� ���� �Ҵ�
				rowData[0] = clv.getProduct_code();
				rowData[1] = clv.getImg_file();
				rowData[2] = clv.getUser_id();
				rowData[3] = clv.getCategory();
				rowData[4] = clv.getProduct_name();
				rowData[5] = new Integer(clv.getPrice());
				rowData[6] = clv.getUpload_date();
				
				//dtm�� �߰�
				dtm.addRow(rowData);
				
			}//end for
			
			amv.getJtfSearch1().setText("");
			amv.getJcbCategory1().setSelectedIndex(0);
			amv.getJrbSubject1().setSelected(true);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(amv, "�����ڴ�, reset����! ���񽺰� ��Ȱ���� ���� �� �˼��մϴ�.");
			e.printStackTrace();
		}//end catch
		
	}//end resetCheckList
	
	private void openCheckDetail(JTable temp){
		CheckDetailVO dv = new CheckDetailVO();
		
		//���ö� ����Ʈ���� ���� �� �ִ� ��
		//������ ���� ���ö� �ڵ带 ���
		String product_code = (String)temp.getValueAt(temp.getSelectedRow(), 0);
		
		dv.setProduct_code(product_code);
		
//		System.out.println(dv);
		
		//DBTable���� ���� �� �ִ� ��
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			aDAO.checkDetail(dv);
			
			//���� ���� VO�� �Ҵ��Ͽ� ��ȭ���� ����ش�.
			new AdminCheckDetailView(amv, this, dv);
			
//			System.out.println(dv);
		} catch (SQLException e) {
			e.printStackTrace();
		}//���ö� �ڵ忡 ���� �����̹����� Ư���� ��ȸ
		
	}//openCheckDetail
	
	/////////////////////////////////////////////////////////////////////////�ι�° ��/////////////////////////////////////////////////////////////////

	private void setProductList() {
	}//setProductList
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////����° ��////////////.//////////////////////////////////////////////
	private void setUserIdList() {
		DefaultTableModel dtm = amv.getDtmUserList();
		String userId = null;
		
		userId = amv.getJtfSearch3().getText().trim();
		
		//JTable�� ���ڵ� �ʱ�ȭ
		dtm.setRowCount(0);
		Object[] rowData = null;//JTable�� ���� ������
		
		//DBMS���� ��ȸ
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			List<UserIdVO> list = aDAO.selectAllUserIdList(userId);
			
			if(list.isEmpty()) {//��ǰ�� ���� ���
				JOptionPane.showMessageDialog(amv, "��ϵ� ������ �����ϴ�.");
//				resetUserIdList();
			}//end if
			
			UserIdVO uiVO = null;
			
			for(int i = 0 ; i < list.size() ; i++) {
				uiVO = list.get(i);
				
				//��ȸ����� JTable ���ڵ忡 �� �����͸� �����ϰ�, dtm�� �߰�
				rowData = new Object[7];
				
				//�迭�� ���� �Ҵ�
				rowData[0] = uiVO.getUser_id();
				rowData[1] = uiVO.getUser_name();
				if (uiVO.getGender().equals("F")) {
					rowData[2] = "����";
				}else {
					rowData[2] = "����";
				}
//				rowData[2] = uiVO.getGender();
				rowData[3] = uiVO.getPhone();
				rowData[4] = uiVO.getLoc();
				rowData[5] = uiVO.getJoin_date();
				rowData[6] = uiVO.getSuspend_flag();
				
				//dtm�� �߰�
				dtm.addRow(rowData);
				
			}//end for
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(amv, "�����ڴ�, UserID db���񽺰� ��Ȱ���� ���� �� �˼��մϴ�.");
			e.printStackTrace();
		}//end catch
		
	}//setUserIdList
	
	private void resetUserIdList() {
		
		DefaultTableModel dtm = amv.getDtmUserList();
		//JTable�� ���ڵ� �ʱ�ȭ
		dtm.setRowCount(0);
		Object[] rowData = null;//JTable�� ���� ������
		
		//DBMS���� ��ȸ
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			List<UserIdVO> list = aDAO.reselectAllUserIdList();
			
			if(list.isEmpty()) {//��ǰ�� ���� ���
				JOptionPane.showMessageDialog(amv, "��ϵ� ������ �����ϴ�.");
			}//end if
			
			UserIdVO uiVO = null;
			
			for(int i = 0 ; i < list.size() ; i++) {
				uiVO = list.get(i);
				
				//��ȸ����� JTable ���ڵ忡 �� �����͸� �����ϰ�, dtm�� �߰�
				rowData = new Object[7];
				
				//�迭�� ���� �Ҵ�
				rowData[0] = uiVO.getUser_id();
				rowData[1] = uiVO.getUser_name();
				if (uiVO.getGender().equals("F")) {
					rowData[2] = "����";
				}else {
					rowData[2] = "����";
				}
//				rowData[2] = uiVO.getGender();
				rowData[3] = uiVO.getPhone();
				rowData[4] = uiVO.getLoc();
				rowData[5] = uiVO.getJoin_date();
				rowData[6] = uiVO.getSuspend_flag();
				
				//dtm�� �߰�
				dtm.addRow(rowData);
				
			}//end for
			
			amv.getJtfSearch3().setText("");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(amv, "�����ڴ�, reset����! ���񽺰� ��Ȱ���� ���� �� �˼��մϴ�.");
			e.printStackTrace();
		}//end catch
		
	}//resetUserIdList
	
	private void openUserIdDetail(JTable temp){
		UserIdDetailVO uidVO = new UserIdDetailVO();
		
		//���ö� ����Ʈ���� ���� �� �ִ� ��
		//������ ���� ���ö� �ڵ带 ���
		String user_id = (String)temp.getValueAt(temp.getSelectedRow(), 0);
		
		uidVO.setUser_id(user_id);
		
//		System.out.println(dv);
		
		//DBTable���� ���� �� �ִ� ��
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			aDAO.UserIdDetail(uidVO);
			
			//���� ���� VO�� �Ҵ��Ͽ� ��ȭ���� ����ش�.
			new AdminIdDetailView(amv, this, uidVO);
			
//			System.out.println(dv);
		} catch (SQLException e) {
			e.printStackTrace();
		}//���ö� �ڵ忡 ���� �����̹����� Ư���� ��ȸ
		
	}//openCheckDetail
	
	
	/////////////////////////////////////////////////////////////actionPerformed////////////////////////////////////////////////////////////////////////////////
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == amv.getJcbCategory1()) {//ī�װ�1 ���� ��
			JComboBox jcb = (JComboBox) ae.getSource();
			
			index = jcb.getSelectedIndex();
			
			if (index != 0) {
				setCheckList();
			}//end if
			
		}//end if
		
		if(ae.getSource() == amv.getJcbCategory2()) {//ī�װ�2 ���� ��
			JComboBox jcb = (JComboBox) ae.getSource();
			
			index = jcb.getSelectedIndex();
			
			if (index != 0) {
//				setCheckList();
			}//end if
			
		}//end if
		
		if(ae.getSource() == amv.getJbtSearch1()) {//�˻�1��ư Ŭ��
			
			if (amv.getJtfSearch1().getText().isEmpty()) {
				JOptionPane.showMessageDialog(amv, "�˻�� �Է����ּ���.");
				amv.getJtfSearch1().getCursor();
				
			}else {
				setCheckList();
			}//end else
			
		}//end if
		
		if(ae.getSource() == amv.getJbtSearch2()) {//�˻�2��ư Ŭ��
			
			if (amv.getJtfSearch2().getText().isEmpty()) {
				JOptionPane.showMessageDialog(amv, "�˻�� �Է����ּ���.");
				amv.getJtfSearch2().getCursor();
				
			}else {
//				setCheckList();
			}//end else
			
		}//end if
		
		if(ae.getSource() == amv.getJbtSearch3()) {//�˻�3��ư Ŭ��
			
			if (amv.getJtfSearch3().getText().isEmpty()) {
				JOptionPane.showMessageDialog(amv, "�˻�� �Է����ּ���.");
				amv.getJtfSearch3().getCursor();
				
			}else {
				setUserIdList();
			}//end else
			
		}//end if
		
		
		if(ae.getSource() == amv.getJbtRecent1()) {//�ֽż� ��ư1 Ŭ��
			setRecentList1();
		}//end if
		
		if(ae.getSource() == amv.getJbtRecent2()) {//�ֽż� ��ư2 Ŭ��
//			setRecentList1();
		}//end if
		
		if(ae.getSource() == amv.getJbtRefresh1()) {//���ΰ�ħ ��ư1 Ŭ��
			setCheckList();
		}//end if
		
		if(ae.getSource() == amv.getJbtRefresh2()) {//���ΰ�ħ ��ư2 Ŭ��
//			setCheckList();
		}//end if
		
		if(ae.getSource() == amv.getJbtRefresh3()) {//���ΰ�ħ ��ư3 Ŭ��
			setUserIdList();;
		}//end if
		
		if(ae.getSource() == amv.getJbtReset1()) {//�ʱ�ȭ ��ư1 Ŭ��
			resetCheckList();
		}//end if
		
		if(ae.getSource() == amv.getJbtReset2()) {//�ʱ�ȭ ��ư2 Ŭ��
//			resetCheckList();
		}//end if
		
		if(ae.getSource() == amv.getJbtReset3()) {//�ʱ�ȭ ��ư3 Ŭ��
			resetUserIdList();
		}//end if
		
	}//actionPerformed
	
	
/////////////////////////////////////////////////////////////mouseClicked////////////////////////////////////////////////////////////////////////////////
	@Override
	public void mouseClicked(MouseEvent me) {
		
		if(me.getSource() == amv.getJtp()) {
			//�ֹ� ���� ������ �� ó��
			JTabbedPane jtpTemp = (JTabbedPane)me.getSource();
			
			if(jtpTemp.getSelectedIndex() == 1) {
				
				setProductList();
//				if (ot == null) {//��ȸ thread�� �����Ǿ����� ����. �ֹ���ȸx
//					ot = new OrderThread(lm.getJtOrderList(), lm.getDtmOrderList());
//					ot.start();
//					
//				}//end if
				
			} else if (jtpTemp.getSelectedIndex() == 2) {
				setUserIdList();
//				if (ot == null) {//��ȸ thread�� �����Ǿ����� ����. �ֹ���ȸx
//					ot = new OrderThread(lm.getJtOrderList(), lm.getDtmOrderList());
//					ot.start();
//				
//				}//end if
				
			} //end else if
			
		}//end if
		
		if(me.getClickCount() == 2) {
			//�̺�Ʈ�� �߻��� JTable�� �޴´�.
			JTable temp1 = amv.getJtCheckList();
			JTable temp2 = amv.getJtProductList();
			JTable temp3 = amv.getJtUserList();
			
			if(me.getSource() == temp1) {//���ö� ����Ʈ���� �̺�Ʈ �߻�
				openCheckDetail(temp1);
			}//end if
			
			if(me.getSource() == temp2) {//���ö� ����Ʈ���� �̺�Ʈ �߻�
//				openProductDetail(temp2);
			}//end if
			
			if(me.getSource() == temp3) {//���ö� ����Ʈ���� �̺�Ʈ �߻�
				openUserIdDetail(temp3);
			}//end if
			
		}//end if
		
	}//mouseClicked
	
}//class







