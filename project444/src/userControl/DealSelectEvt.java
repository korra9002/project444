package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import userDAO.UserDAO;
import userVO.ChatListVO;
import userVO.DealListVO;
import userView.DealSelect;

public class DealSelectEvt extends MouseAdapter implements ActionListener{
private DealSelect ds;
private String productName,productCode;


	public DealSelectEvt(DealSelect ds, String nameAndCode) {
		System.out.println("dealselectEvt ������");
	this.ds = ds;
	this.productName = nameAndCode.substring(0,nameAndCode.lastIndexOf('('));
	this.productCode = nameAndCode.substring(nameAndCode.lastIndexOf('(')+1,nameAndCode.lastIndexOf(')'));
	System.out.println(productCode);
	ds.getjlb().setText(productName);
	setList();
}

	public void setList() {
		System.out.println("setlist");
		UserDAO uDAO = UserDAO.getInstance();
		DefaultTableModel dtm = ds.getDtmAreaList();
		dtm.setRowCount(0);

		Object[] rowData = null;
		try {
			List<DealListVO> list = uDAO.setDealList(productCode);

			DealListVO dlVO = null;
			for (int i = 0; i < list.size(); i++) {
				dlVO = list.get(i);
				rowData = new Object[3];

				rowData[0] = dlVO.getId();
				rowData[1] = dlVO.getLoc();
				rowData[2] = dlVO.getDealCode();
			System.out.println("for��");
				dtm.addRow(rowData);
			} // end for

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendDealMsg() {
		JTable jtpAreaList=ds.getJtpAreaList();
		String id=(String) jtpAreaList.getValueAt(jtpAreaList.getSelectedRow(), 0);
		String dealCode=(String) jtpAreaList.getValueAt(jtpAreaList.getSelectedRow(), 2);
		if(JOptionPane.showConfirmDialog(ds, id+"�Կ��� "+productName+"�� �Ǹ��Ͻðڽ��ϱ�?","�Ǹ�Ȯ��",JOptionPane.YES_NO_OPTION,JOptionPane.DEFAULT_OPTION)==0) {
			UserDAO uDAO = UserDAO.getInstance(); 
			try {
				if(uDAO.changeFlag(dealCode)==1) {
					JOptionPane.showMessageDialog(ds,id+"�Կ��� �Ǹ� �޼����� ���½��ϴ�.");
					
					
				}else{
					JOptionPane.showMessageDialog(ds, "������ �߻��߽��ϴ�.");
				};
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			return;
		}

	}
	
	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getClickCount() == 2) {//����Ŭ��
			if(me.getSource() == ds.getJtpAreaList()) {
				
						sendDealMsg();
				
			}//end if			
		}//end if
		
	}//mouseClicked

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() ==ds.getJbtcancell()) {
			ds.dispose();
		}
		
		if(e.getSource() == ds.getJbtsell()) {
			if(ds.getJtpAreaList().getSelectedRow() !=-1) {
				sendDealMsg();
			}else {
				JOptionPane.showMessageDialog(ds, "ID�� �����ϼ���");
			}
		}
	}//actionPerformed

}
