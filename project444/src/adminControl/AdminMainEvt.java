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
import javax.swing.table.DefaultTableModel;

import adminDAO.AdminDAO;
import adminVO.CheckListVO;
import adminVO.CheckVO;
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
			JOptionPane.showMessageDialog(amv, "�����ڴ�, ���񽺰� ��Ȱ���� ���� �� �˼��մϴ�.");
			e.printStackTrace();
		}//end catch
		
	}//setLunchList

	private void openDetail() {
		
	}
	
	/////////////�ι�° ��//////////////////////////////////////////////////////

	private void setProductList() {
//		DefaultTableModel dtm = amv.getDtmProductList();
//		
//		//JTable�� ���ڵ� �ʱ�ȭ
//		dtm.setRowCount(0);
//		Object[] rowData = null;//JTable�� ���� ������
//		
//		//DBMS���� ��ȸ
//		AdminDAO aDAO = AdminDAO.getInstance();
//		try {
//			List<CheckListVO> list = aDAO.selectAllProductList();
//			
//			if(list.isEmpty()) {//��ǰ�� ���� ���
//				JOptionPane.showMessageDialog(amv, "��ϵ� ��ǰ�� �����ϴ�.");
//			}//end if
//			
//			CheckListVO cv = null;
//			
//			for(int i = 0 ; i < list.size() ; i++) {
//				cv = list.get(i);
//				
//				//��ȸ����� JTable ���ڵ忡 �� �����͸� �����ϰ�, dtm�� �߰�
//				rowData = new Object[8];
//				
//				//�迭�� ���� �Ҵ�
//				rowData[0] = cv.getProduct_code();//i + 1�� ����
//				rowData[1] = cv.getImg_file();
//				rowData[2] = cv.getUser_id();
//				rowData[3] = cv.getCategory();
//				rowData[4] = cv.getProduct_name();
//				rowData[5] = new Integer(cv.getPrice());
//				rowData[6] = cv.getUpload_date();
//				rowData[7] = cv.getAll_flag();
//				
//				//dtm�� �߰�
//				dtm.addRow(rowData);
//				
//			}//end for
//			
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(amv, "�����ڴ�, ���񽺰� ��Ȱ���� ���� �� �˼��մϴ�.");
//			e.printStackTrace();
//		}//end catch
//		
	}//setProductList
	
	private void openDetail2() {
		
	}
	
	private void setRefreshList2() {
		
	}
	
	private void setCategoryList2() {
		
	}
	
	private void setRecentList2() {
		
	}
	
	private void productNameSelect2() {
		
	}
	
	private void userIdSelect2() {
		
	}
	
	//////////////////////////////����° ��//////////////////////////////
	
	private void setUserIdList() {
//		DefaultTableModel dtm = amv.getDtmUserList();
//		
//		//JTable�� ���ڵ� �ʱ�ȭ
//		dtm.setRowCount(0);
//		Object[] rowData = null;//JTable�� ���� ������
//		
//		//DBMS���� ��ȸ
//		AdminDAO aDAO = AdminDAO.getInstance();
//		try {
//			List<UserIdListVO> list = aDAO.selectAllUserList();
//			
//			if(list.isEmpty()) {//��ϵ� ����id ���� ���
//				JOptionPane.showMessageDialog(amv, "��ϵ� ID�� �����ϴ�.");
//			}//end if
//			
//			UserIdListVO uilv = null;
//			
//			for(int i = 0 ; i < list.size() ; i++) {
//				uilv = list.get(i);
//				
//				//��ȸ����� JTable ���ڵ忡 �� �����͸� �����ϰ�, dtm�� �߰�
//				rowData = new Object[7];
//				
//				//�迭�� ���� �Ҵ�
//				rowData[0] = uilv.getUser_id();//i + 1�� ����
//				rowData[1] = uilv.getUser_name();
//				rowData[2] = uilv.getGender();
//				rowData[3] = uilv.getPhone();
//				rowData[4] = uilv.getLoc();
//				rowData[5] = uilv.getJoin_date();
//				rowData[6] = uilv.getSuspend_flag();
//				
//				//dtm�� �߰�
//				dtm.addRow(rowData);
//				
//			}//end for
//			
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(amv, "�����ڴ�, ���񽺰� ��Ȱ���� ���� �� �˼��մϴ�.");
//			e.printStackTrace();
//		}//end catch
		
	}//setUserIdList
	
	
	
	
	
	
	
	
	
	
	
	private void setCategoryList(String category) {
//		DefaultTableModel dtm = amv.getDtmCheckList();
//		JComboBox<String> jcb = amv.getJcbCategory1();
//		
//		//JTable�� ���ڵ� �ʱ�ȭ
//		dtm.setRowCount(0);
//		Object[] rowData = null;//JTable�� ���� ������
//		
//		//DBMS���� ��ȸ
//		AdminDAO aDAO = AdminDAO.getInstance();
//		try {
//			List<CheckListVO> list = aDAO.selectAllCheckList();
//			
//			if(list.isEmpty()) {//��ϵ� ��ǰ�� ���� ���
//				JOptionPane.showMessageDialog(amv, "�˼��� ��ǰ�� �����ϴ�.");
//				setCheckList();
//				jcb.setSelectedIndex(0);
//			}//end if
//			
//			CheckListVO clv = null;
//			int cnt = 0;
//			for(int i = 0 ; i < list.size() ; i++) {
//				clv = list.get(i);
//				
//				//��ȸ����� JTable ���ڵ忡 �� �����͸� �����ϰ�, dtm�� �߰�
//				rowData = new Object[7];
//				if (clv.getCategory().equals(category)) {
//					
//					//�迭�� ���� �Ҵ�
//					rowData[0] = clv.getProduct_code();
//					rowData[1] = clv.getImg_file();
//					rowData[2] = clv.getUser_id();
//					rowData[3] = clv.getCategory();
//					rowData[4] = clv.getProduct_name();
//					rowData[5] = new Integer(clv.getPrice());
//					rowData[6] = clv.getUpload_date();
//					cnt++;
//					
//				}//end if
//				
//				//dtm�� �߰�
//				dtm.addRow(rowData);
//				
//			}//end for
//			dtm.setRowCount(cnt);
//			
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(amv, "�����ڴ�, ���񽺰� ��Ȱ���� ���� �� �˼��մϴ�.");
//			e.printStackTrace();
//		}//end catch
//		
	}//setCategoryList
	
	private void setProductNameList(String productName) {
//		
//		DefaultTableModel dtm = amv.getDtmCheckList();
////		JComboBox<String> jcb = amv.getJcbCategory1();
//		
//		//JTable�� ���ڵ� �ʱ�ȭ
//		dtm.setRowCount(0);
//		Object[] rowData = null;//JTable�� ���� ������
//		
//		//DBMS���� ��ȸ
//		AdminDAO aDAO = AdminDAO.getInstance();
//		try {
//			List<CheckListVO> list = aDAO.selectAllCheckList();
//			
////			if(list.isEmpty()) {//��ϵ� ��ǰ�� ���� ���
////				JOptionPane.showMessageDialog(amv, "�˼��� ��ǰ�� �����ϴ�.");
////				setCheckList();
////				jcb.setSelectedIndex(0);
////			}//end if
//			
//			CheckListVO clv = null;
//			int cnt = 0;
//			for(int i = 0 ; i < list.size() ; i++) {
//				clv = list.get(i);
//				
//				//��ȸ����� JTable ���ڵ忡 �� �����͸� �����ϰ�, dtm�� �߰�
//				rowData = new Object[7];
//				
//				//�迭�� ���� �Ҵ�
//				
//				if (clv.getProduct_name().contains(productName)) {
//					rowData[0] = clv.getProduct_code();
//					rowData[1] = clv.getImg_file();
//					rowData[2] = clv.getUser_id();
//					rowData[3] = clv.getCategory();
//					rowData[4] = clv.getProduct_name();
//					rowData[5] = new Integer(clv.getPrice());
//					rowData[6] = clv.getUpload_date();
//					cnt++;
//				}//end if
//				
//				
//				//dtm�� �߰�
//				dtm.addRow(rowData);
//				
//			}//end for
//			dtm.setRowCount(cnt);
//			
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(amv, "�����ڴ�, ���񽺰� ��Ȱ���� ���� �� �˼��մϴ�.");
//			e.printStackTrace();
//		}//end catch
//		
//		
	}//selectProductNameList
	
	
//	
//
//	/**
//	 * ������ â�� �ݴ� ��
//	 */
//	private void lunchClose() {
//		switch (JOptionPane.showConfirmDialog(lm, "'�� ���ö�' ������ ���α׷��� �����Ͻðڽ��ϱ�?")) {
//		case JOptionPane.OK_OPTION:
//			lm.dispose();
//
//		}//switch
//	
//	}//lunchClose
//	
//	private void addForm() {
//		new LunchAddFrm(lm, this);
//	}//addForm
//	
//	
//	/**
//	 * ��ȣȭ�� ip�� ��ȣȭ �Ͽ� �����ϴ� ��
//	 */
//	private void ipView() {
//		JTable temp = lm.getJtOrderList();
//		
//		int selectRow = temp.getSelectedRow();
//		
//		if (selectRow == -1) {//���� �Ϸ� �� ���ö��� ���� ���õ��� �ʾ��� ��
//			JOptionPane.showMessageDialog(lm, "ip�ּҸ� Ȯ���� ���� �������ּ���");
//			return;
//		}//end if
//		
//		try {
//			DataDecrypt dd = new DataDecrypt("1111111111111111");
//			//��ȣȭ�� ������ ���
//			String cipherText = (String) temp.getValueAt(selectRow, 6);
//			String name = (String) temp.getValueAt(selectRow, 3);
//			
//			String plainText = dd.decryption(cipherText);//��ȣȭ
//			
//			JOptionPane.showMessageDialog(lm, name + " �ֹ����� ip Address��\n'" + plainText + "'\n�Դϴ�.");
//			
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (GeneralSecurityException e) {
//			e.printStackTrace();
//		}//end catch
//		
//	}//ipView
//	
//	
//	/**
//	 * ���ۻ��¸� ����
//	 */
//	private void orderStatus() {
//		JTable temp = lm.getJtOrderList();
//		int selectRow = temp.getSelectedRow();
//		
//		if (selectRow == -1) {//���� �Ϸ� �� ���ö��� ���� ���õ��� �ʾ��� ��
//			JOptionPane.showMessageDialog(lm, "���� �Ϸ��� ���ö��� ���� �������ּ���");
//			return;
//		}//end if
//		
//		String status = (String) temp.getValueAt(selectRow, 8);
//		Integer orderNum = (Integer) temp.getValueAt(selectRow, 0);
//		String lunchName = (String) temp.getValueAt(selectRow, 1);
//		
//		StringBuilder msg = new StringBuilder();
//		msg.append(lunchName).append("(").append(orderNum).append(") ���ö��� ");
//		
//		if(status.equals("N")) {
//			msg.append("�����Ϸ� ���·� �����Ͻðڽ��ϱ�?");
//		} else {
//			msg.append("�����Ϸ� ���¸� ����Ͻðڽ��ϱ�?");
//		}//end else
//		
//		switch (JOptionPane.showConfirmDialog(lm, msg.toString())) {
//		case JOptionPane.OK_OPTION:
//			if(status.equals("N")) {
//				status = "Y";
//			} else {
//				status = "N";
//			}//end if
//			
//			//ȭ���� ����
//			temp.setValueAt(status, selectRow, 8);
//			
//			//DBMS�� ����
//			AdminDAO aDAO = AdminDAO.getInstance();
//			try {
//				String resultMsg = "���ö� ���°� ������� �ʾҽ��ϴ�.";
//				
//				if(aDAO.updateStatus(orderNum, status)) {
//					resultMsg = "���ö� ���°� ����Ǿ����ϴ�.";
//				}//end if
//				
//				JOptionPane.showMessageDialog(lm, resultMsg);
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}//end catch
//			
//		}//end switch
//		
//	}//orderStatus
//	
//	
//	private void viewCalc() {
//		AdminDAO aDAO = AdminDAO.getInstance();
//		try {
//			List<CalcVO> list = aDAO.selectCalc();
//			
//			JTextArea jta = new JTextArea(20,50);
//			JScrollPane jsp = new JScrollPane(jta);
//			
//			if (list.isEmpty()) {
//				jta.append("������ �� �ִ� �ŷ��� �̷������ �ʾҽ��ϴ�.");
//			}//end if
//			
//			jta.append("-----------------------------------------------------------------------------------------------------------\n");
//			jta.append("��ȣ\t���ö���(�ڵ�)\t\t�� �Ǹż���\t�� ����\t\n");
//			jta.append("-----------------------------------------------------------------------------------------------------------\n");
//			CalcVO cv = null;
//			int totalCnt = 0;
//			int totalPrice = 0;
//			
//			for (int i = 0; i < list.size(); i++) {
//				cv = list.get(i);
//				jta.append((i+1)+"\t" + cv.getName() + "(" + cv.getLunchCode() + ")\t" + cv.getCnt() + "\t" + cv.getTotalPrice() + "\n");
//				totalCnt += cv.getCnt();
//				totalPrice += cv.getTotalPrice();
//			}//end for
//			jta.append("-----------------------------------------------------------------------------------------------------------\n");
//			jta.append("�� ���� : " + totalCnt + "��,	�� ���� : " + totalPrice + "��");
//			
//			
//			JOptionPane.showMessageDialog(lm, jsp);
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(lm, "DBMS�� ������ �߻��Ͽ����ϴ�.");
//			e.printStackTrace();
//		}//end catch
//		
//	}//viewCalc
//	
//	private void removeOrder() {
//		int deleteCnt = 0;
//		int orderNum = (int) lm.getJtOrderList().getValueAt(lm.getJtOrderList().getSelectedRow(), 0);
//		AdminDAO aDAO = AdminDAO.getInstance();
//		
//		switch (JOptionPane.showConfirmDialog(lm, "�ֹ��� �����Ͻðڽ��ϱ�?")) {
//		case JOptionPane.OK_OPTION:
//			try {
//				deleteCnt = aDAO.deleteOrder(orderNum);
//				
//				if (deleteCnt == 0) {
//					JOptionPane.showMessageDialog(lm, "������ �����Ͱ� �����ϴ�.");
//					
//				} else {
//					JOptionPane.showMessageDialog(lm, orderNum + "�� �ֹ��� �����Ǿ����ϴ�.");
//					
//				}//end else
//				
////				ot.start();
//				
//			} catch (SQLException e) {
//				JOptionPane.showMessageDialog(lm, "���� �۾� �� ������ �߻��Ͽ����ϴ�.");
//				e.printStackTrace();
//			}//end catch
//			
//		}//end switch
//		
//	}//removeOrder
//	
//	
//	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == amv.getJcbCategory1()) {//�����ư
			JComboBox jcb = (JComboBox) ae.getSource();
			
			index = jcb.getSelectedIndex();
//			categoryTemp = amv.getCategoryList1();
			
//			System.out.println(index);
//			System.out.println(temp[index]);
			if (index != 0) {
				setCheckList();
			}//end if
			
		}//end if
		
		if(ae.getSource() == amv.getJbtSearch1()) {//�߰���ư
			
			if (amv.getJtfSearch1().getText().isEmpty()) {
				JOptionPane.showMessageDialog(amv, "�˻�� �Է����ּ���.");
				amv.getJtfSearch1().getCursor();
				
			}else {
				setCheckList();
			}
			
			
		}//end if
		
//		
//		if(ae.getSource() == lm.getJmStatus()) {
//			orderStatus();
//		}//end if
//		
//		if(ae.getSource() == lm.getJmIpView()) {
//			ipView();
//		}//end if
//		
//		if(ae.getSource() == lm.getJmDelete()) {
//			removeOrder();
//		}//end if
//		
//		if (ae.getSource() == lm.getJbtCalc()) {
//			viewCalc();
//		}//end if
//		
	}//actionPerformed
//	
//	/**
//	 * ������ ���ö��� ������â�� ����ִ� ��
//	 */
//	private void lunchDetail(JTable temp) {
//		//��â���� ����� ���� VO(DTO�� ����)�� ����
//		LunchDetailVO ldVO = new LunchDetailVO();
//		
//		//���ö� ����Ʈ���� ���� �� �ִ� ��
//		//������ ���� ���ö� �ڵ带 ���
//		String lunchName = (String)temp.getValueAt(temp.getSelectedRow(), 2);
//		String lunchCode = (String)temp.getValueAt(temp.getSelectedRow(), 3);
//		Integer lunchPrice = (Integer)temp.getValueAt(temp.getSelectedRow(), 4);
//		String lunchInputDate = (String)temp.getValueAt(temp.getSelectedRow(), 5);
//		
//		ldVO.setLunchCode(lunchCode);
//		ldVO.setName(lunchName);
//		ldVO.setPrice(lunchPrice.intValue());
//		ldVO.setInputDate(lunchInputDate);
//		
////		System.out.println(ldVO);
//		
//		//DBTable���� ���� �� �ִ� ��
//		AdminDAO aDAO = AdminDAO.getInstance();
//		try {
//			aDAO.selectDetailLunch(ldVO);
//			
//			//���� ���� VO�� �Ҵ��Ͽ� ��ȭ���� ����ش�.
//			new LunchDetail(lm, this, ldVO);
//			
////			System.out.println(ldVO);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//���ö� �ڵ忡 ���� �����̹����� Ư���� ��ȸ
//		
//	}//lunchDetail
//
//	
//	
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
//				ot = new OrderThread(lm.getJtOrderList(), lm.getDtmOrderList());
//				ot.start();
//				
//				}//end if
				
			} //end else if
			
		}//end if
		
//		if(me.getClickCount() == 2) {
//			//�̺�Ʈ�� �߻��� JTable�� �޴´�.
//			JTable temp = lm.getJtLunchList();
//			
//			if(me.getSource() == temp) {//���ö� ����Ʈ���� �̺�Ʈ �߻�
//				lunchDetail(temp);
//								
//			}//end if
//			
//		}//end if
//		
	}//mouseClicked
//
	
	
}//class







