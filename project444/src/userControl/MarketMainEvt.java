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
import userVO.AllListVO;
import userVO.MarketDetailVO;
import userView.MarketDetailBuyer;
import userView.MarketDetailSeller;
import userView.MarketMain;

public class MarketMainEvt extends MouseAdapter implements ActionListener{
	public static final int DOUBLE_CLICK=2;
	
	private MarketMain mm;
	private String id;
	
	
	
	public MarketMainEvt(MarketMain mm, String id) throws SQLException {
		this.mm=mm;	
		this.id=id;
		setAllList();
	}//MarketMainEvt
	
//	public void setAllList() throws SQLException {
//		DefaultTableModel dtm=mm.getDtmProductList();
//		
//		//JTable�� ���ڵ� �ʱ�ȭ
//		dtm.setRowCount(0);
//		
//		Object[] rowData= null;
//		//JTable ���� ������ // object�� �ڹ��� ��� ���� �� ���� �� �ִ�.
//		
//		//DBMS���� ��ȸ
//		UserDAO uDAO =UserDAO.getInstance();
//			List<AllListVO> list=uDAO.selectAllList();
//				if(list.isEmpty()) { 
//					JOptionPane.showMessageDialog(mm, "�˻����ǿ� �´� ��ǰ�� �����ϴ�.");
//				}
//			AllListVO alv=null;
//			for(int i=0; i<list.size(); i++) {
//				alv=list.get(i);
//				//��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
//				rowData=new Object[7];
//				//�迭�� �� �Ҵ� 		String[] productColumn= {"�̹���", "��ǰ��", "����", "����", "�ð�", "ī�װ�", "�Ǹ��� ID" };
//				rowData[0]=alv.getImage();
//				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
//				rowData[2]=alv.getLoc_code();
//				rowData[3]=alv.getPrice();
//				rowData[4]=alv.getUpload_date();
//				rowData[5]=alv.getCategory();
//				rowData[6]=alv.getSellerID();
//			//dtm�� �߰�
//			dtm.addRow(rowData);		
//		}//end for		
//	}//setAllList
	
		
		
		public void setAllList() throws SQLException {
			
//			if (jcbAreaIndex==0 && jcbCateIndex==0 && jtfText==null) {
//				setAllList();
//			}//end if
			DefaultTableModel dtm=mm.getDtmProductList();
			
			//JTable�� ���ڵ� �ʱ�ȭ
			dtm.setRowCount(0);
			
			Object[] rowData= null;
			//JTable ���� ������ // object�� �ڹ��� ��� ���� �� ���� �� �ִ�.
		
//			int area =0;
//			try {
//			area =  mm.getJcbArea().getSelectedIndex();
//			}catch (Exception e) {
//           e.printStackTrace();
// 	 	
//			}	
//			int category =0;
//			try {
//				category =  mm.getJcbCategory().getSelectedIndex();
//			}catch (Exception e) {
//				e.printStackTrace();
//				
//			}	
//			
//			String proName ="";
//			try {
//				proName =  mm.getJtfSearch().getText().trim();
//			}catch (Exception e) {
//				e.printStackTrace();
//				
//			}	
			
			//DBMS���� ��ȸ
			UserDAO uDAO =UserDAO.getInstance();
		List<AllListVO> list=uDAO.selectAllList( mm.getJcbArea().getSelectedIndex(), mm.getJcbCategory().getSelectedIndex(),mm.getJtfSearch().getText().trim());
		//�ФФФФФФФФФФ�getselectedText�ƴϰ� getText()�Դϴ٤ФФФФФФиӸ��� �����ô٤�
	//		List<AllListVO> list=uDAO.selectAllList(0, 0,"");
			if(list.isEmpty()) { 
				JOptionPane.showMessageDialog(mm, "�˻����ǿ� �´� ��ǰ�� �����ϴ�. ");
			}
			AllListVO alv=null;
			for(int i=0; i<list.size(); i++) {
				alv=list.get(i);
				//��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
				rowData=new Object[7];
				//�迭�� �� �Ҵ�
				rowData[0]=alv.getImage();
				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
				rowData[2]=alv.getLoc_code();
				rowData[3]=alv.getPrice();
				rowData[4]=alv.getUpload_date();
				rowData[5]=alv.getCategory();
				rowData[6]=alv.getSellerID();
				//dtm�� �߰�
				dtm.addRow(rowData);		
			}//end for		
		}//setAllList
		
		
		public void setListByID() throws SQLException {
			
			DefaultTableModel dtm=mm.getDtmProductList();
			
			//JTable�� ���ڵ� �ʱ�ȭ
			dtm.setRowCount(0);
			
			Object[] rowData= null;
			//JTable ���� ������ // object�� �ڹ��� ��� ���� �� ���� �� �ִ�.
			
			
			//DBMS���� ��ȸ
			UserDAO uDAO =UserDAO.getInstance();
			List<AllListVO> list=uDAO.selectListByID( mm.getJcbArea().getSelectedIndex(), mm.getJcbCategory().getSelectedIndex(),mm.getJtfSearch().getText().trim());
			if(list.isEmpty()) { 
				JOptionPane.showMessageDialog(mm, "�˻����ǿ� �´� ��ǰ�� �����ϴ�.");
			}
			AllListVO alv=null;
			for(int i=0; i<list.size(); i++) {
				alv=list.get(i);
				//��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
				rowData=new Object[7];
				//�迭�� �� �Ҵ�
				rowData[0]=alv.getImage();
				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
				rowData[2]=alv.getLoc_code();
				rowData[3]=alv.getPrice();
				rowData[4]=alv.getUpload_date();
				rowData[5]=alv.getCategory();
				rowData[6]=alv.getSellerID();
				//dtm�� �߰�
				dtm.addRow(rowData);		
			}//end for		
		}//setListByID 
		
		
		public void setRefresh() throws SQLException {
			

			DefaultTableModel dtm=mm.getDtmProductList();
			
			//JTable�� ���ڵ� �ʱ�ȭ
			dtm.setRowCount(0);
			
			Object[] rowData= null;
			//JTable ���� ������ // object�� �ڹ��� ��� ���� �� ���� �� �ִ�.
	
			
			//DBMS���� ��ȸ
			UserDAO uDAO =UserDAO.getInstance();
			List<AllListVO> list=uDAO.selectRefresh();
			if(list.isEmpty()) { 
				JOptionPane.showMessageDialog(mm, "�˻����ǿ� �´� ��ǰ�� �����ϴ�.");
			}
			AllListVO alv=null;
			for(int i=0; i<list.size(); i++) {
				alv=list.get(i);
				//��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
				rowData=new Object[7];
				//�迭�� �� �Ҵ�
				rowData[0]=alv.getImage();
				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
				rowData[2]=alv.getLoc_code();
				rowData[3]=alv.getPrice();
				rowData[4]=alv.getUpload_date();
				rowData[5]=alv.getCategory();
				rowData[6]=alv.getSellerID();
				//dtm�� �߰�
				dtm.addRow(rowData);		
			}//end for		
		}//setRefresh 
		
		
		public void setListRecent() throws SQLException {
			
			
			DefaultTableModel dtm=mm.getDtmProductList();
			
			//JTable�� ���ڵ� �ʱ�ȭ
			dtm.setRowCount(0);
			
			Object[] rowData= null;
			//JTable ���� ������ // object�� �ڹ��� ��� ���� �� ���� �� �ִ�.
			
			
			//DBMS���� ��ȸ
			UserDAO uDAO =UserDAO.getInstance();
			List<AllListVO> list=uDAO.selectListRecent( mm.getJcbArea().getSelectedIndex(), mm.getJcbCategory().getSelectedIndex(),mm.getJtfSearch().getText().trim());
			if(list.isEmpty()) { 
				JOptionPane.showMessageDialog(mm, "�˻����ǿ� �´� ��ǰ�� �����ϴ�.");
			}
			AllListVO alv=null;
			for(int i=0; i<list.size(); i++) {
				alv=list.get(i);
				//��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
				rowData=new Object[7];
				//�迭�� �� �Ҵ�
				rowData[0]=alv.getImage();
				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
				rowData[2]=alv.getLoc_code();
				rowData[3]=alv.getPrice();
				rowData[4]=alv.getUpload_date();
				rowData[5]=alv.getCategory();
				rowData[6]=alv.getSellerID();
				//dtm�� �߰�
				dtm.addRow(rowData);		
			}//end for		
		}//setListRecent 
		
		
		public void setListPrice() throws SQLException {
			
			
			DefaultTableModel dtm=mm.getDtmProductList();
			
			//JTable�� ���ڵ� �ʱ�ȭ
			dtm.setRowCount(0);
			
			Object[] rowData= null;
			//JTable ���� ������ // object�� �ڹ��� ��� ���� �� ���� �� �ִ�.
			
			
			//DBMS���� ��ȸ
			UserDAO uDAO =UserDAO.getInstance();
			List<AllListVO> list=uDAO.selectListPrice( mm.getJcbArea().getSelectedIndex(), mm.getJcbCategory().getSelectedIndex(),mm.getJtfSearch().getText().trim());
			if(list.isEmpty()) { 
				JOptionPane.showMessageDialog(mm, "�˻����ǿ� �´� ��ǰ�� �����ϴ�.");
			}
			AllListVO alv=null;
			for(int i=0; i<list.size(); i++) {
				alv=list.get(i);
				//��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
				rowData=new Object[7];
				//�迭�� �� �Ҵ�
				rowData[0]=alv.getImage();
				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
				rowData[2]=alv.getLoc_code();
				rowData[3]=alv.getPrice();
				rowData[4]=alv.getUpload_date();
				rowData[5]=alv.getCategory();
				rowData[6]=alv.getSellerID();
				//dtm�� �߰�
				dtm.addRow(rowData);		
			}//end for		
		}//setListPrice 
		
		
		
		
		public void productDetail() {		
			
			//������ ���� �ڵ带 �����ͼ� �� ������ ��ȸ
			  
			 
			JTable jtProductList=mm.getJtProductList();
			String temp=(String) jtProductList.getValueAt(jtProductList.getSelectedRow(), 1);
			String loc_code=(String) jtProductList.getValueAt(jtProductList.getSelectedRow(), 2);
			String productCode=temp.substring(temp.lastIndexOf("(")+1, temp.lastIndexOf(")"));
			
			
			
			//DBMS���� ��ȸ
			UserDAO uDAO =UserDAO.getInstance();

			try {
				MarketDetailVO mdVO=uDAO.selectProDetail(productCode, loc_code);
				
				
				//���� ������ ���̵�� ������ �Ǹ��� ���̵�� ������ MarketDetailBuyer
				//�ٸ��ٸ� MarketDetailSeller
				if (mdVO.getSellerID()==id) {
					new MarketDetailSeller(mm, mdVO, id);
				} else {
					new MarketDetailBuyer(mm, mdVO, id);
				}//end else
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
	
		}//productDetail
		
	

	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mm.getJcbArea() || ae.getSource() == mm.getJcbCategory() ) {
			try {
				setAllList();
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
		}//end if				

		if (mm.getJrbId().isSelected() && ae.getSource() == mm.getJbSearch()) {
			try {
				setListByID();
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
		} else if(mm.getJrbSubject().isSelected() && ae.getSource() == mm.getJbSearch()) {
			try { 
				setAllList();
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
		}//end if
		
		if (ae.getSource() == mm.getJbRecent()) {
				try {
					setListRecent();
				} catch (SQLException e) {
					e.printStackTrace();
				}//end catch
		}//end if
		if (ae.getSource() == mm.getJbPrice()) {
				try {
					setListPrice();
				} catch (SQLException e) {
					e.printStackTrace();
				}//end catch
		}//end if
		if (ae.getSource() == mm.getJbRefresh()) {			
			try {
				setRefresh();
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
		}//end if
		
	}//actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getClickCount() == 2) {//����Ŭ��
			if(me.getSource() == mm.getJtProductList()) {
				productDetail();
			}//end if			
		}//end if
		

	
	}

}//class
