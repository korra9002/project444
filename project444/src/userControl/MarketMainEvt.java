package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import userDAO.UserDAO;
import userVO.AllListVO;
import userView.MarketMain;
import userView.SignUp;

public class MarketMainEvt implements ActionListener{
	private MarketMain mm;
	
	public MarketMainEvt(MarketMain mm) throws SQLException {
		this.mm=mm;		
		setAllList();
	}//MarketMainEvt
	
	public void setAllList() throws SQLException {
		DefaultTableModel dtm=mm.getDtmProductList();
		
		//JTable�� ���ڵ� �ʱ�ȭ
		dtm.setRowCount(0);
		
		Object[] rowData= null;
		//JTable ���� ������ // object�� �ڹ��� ��� ���� �� ���� �� �ִ�.
		
		//DBMS���� ��ȸ
		UserDAO uDAO =UserDAO.getInstance();
			List<AllListVO> list=uDAO.selectAllList();
				if(list.isEmpty()) { 
					JOptionPane.showMessageDialog(mm, "�˻����ǿ� �´� ��ǰ�� �����ϴ�.");
				}
			AllListVO alv=null;
			for(int i=0; i<list.size(); i++) {
				alv=list.get(i);
				//��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
				rowData=new Object[5];
				//�迭�� �� �Ҵ�
				rowData[0]=alv.getImage();
				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
				rowData[2]=alv.getLoc_code();
				rowData[3]=alv.getPrice();
				rowData[4]=alv.getUpload_date();
			//dtm�� �߰�
			dtm.addRow(rowData);		
		}//end for		
	}//setAllList
	
		
		
		public void setArea(int jcbindex) throws SQLException {
			DefaultTableModel dtm=mm.getDtmProductList();
			
			//JTable�� ���ڵ� �ʱ�ȭ
			dtm.setRowCount(0);
			
			Object[] rowData= null;
			//JTable ���� ������ // object�� �ڹ��� ��� ���� �� ���� �� �ִ�.
			
			//DBMS���� ��ȸ
			UserDAO uDAO =UserDAO.getInstance();
			List<AllListVO> list=uDAO.selectAreaList(jcbindex);
			if(list.isEmpty()) { 
				JOptionPane.showMessageDialog(mm, "�˻����ǿ� �´� ��ǰ�� �����ϴ�.");
			}
			AllListVO alv=null;
			for(int i=0; i<list.size(); i++) {
				alv=list.get(i);
				//��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
				rowData=new Object[5];
				//�迭�� �� �Ҵ�
				rowData[0]=alv.getImage();
				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
				rowData[2]=alv.getLoc_code();
				rowData[3]=alv.getPrice();
				rowData[4]=alv.getUpload_date();
				//dtm�� �߰�
				dtm.addRow(rowData);		
			}//end for		
		}//setArea
		
		
		public void setCategory(int jcbindex) throws SQLException {
			DefaultTableModel dtm=mm.getDtmProductList();
			
			//JTable�� ���ڵ� �ʱ�ȭ
			dtm.setRowCount(0);
			
			Object[] rowData= null;
			//JTable ���� ������ // object�� �ڹ��� ��� ���� �� ���� �� �ִ�.
			
			//DBMS���� ��ȸ
			UserDAO uDAO =UserDAO.getInstance();
			List<AllListVO> list=uDAO.selectCategoryList(jcbindex);
			if(list.isEmpty()) { 
				JOptionPane.showMessageDialog(mm, "�˻����ǿ� �´� ��ǰ�� �����ϴ�.");
			}
			AllListVO alv=null;
			for(int i=0; i<list.size(); i++) {
				alv=list.get(i);
				//��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
				rowData=new Object[5];
				//�迭�� �� �Ҵ�
				rowData[0]=alv.getImage();
				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
				rowData[2]=alv.getLoc_code();
				rowData[3]=alv.getPrice();
				rowData[4]=alv.getUpload_date();
				//dtm�� �߰�
				dtm.addRow(rowData);		
			}//end for		
		}//setArea
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mm.getJcbArea()) {
			try {
				setArea(mm.getJcbArea().getSelectedIndex());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//end if
		if (ae.getSource() == mm.getJbSearch()) {
			new SignUp();
		}//end if
		if (ae.getSource() == mm.getJcbCategory()) {
			try {
				setCategory(mm.getJcbCategory().getSelectedIndex());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end if
		if (ae.getSource() == mm.getJbRecent()) {
			new SignUp();
		}//end if
//		if (ae.getSource() == mm.getJbPrice()) {
//			new SignUp();
//		}//end if
		if (ae.getSource() == mm.getJbRefresh()) {
			try {
				setAllList();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end if
		
	}//actionPerformed

}//class
