package userControl;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sun.awt.RequestFocusController;
import userDAO.UserDAO;
import userFileRecieve.UserFileRecieve;
import userRun.RunMarketMain;
import userVO.AllListVO;
import userVO.MarketDetailVO;
import userVO.searchValueVO;
import userView.MarketDetailBuyer;
import userView.MarketDetailSeller;
import userView.MarketMain;

public class MarketMainEvt extends MouseAdapter implements ActionListener {

	private MarketMain mm;
	private String id;
	////// ���� ���� 09-19 19:48///////////////////
	private InterestListEvt ile;

	private String sortFlag = "UD";
	private boolean recentFlag, priceFlag;

	public MarketMainEvt(MarketMain mm, String id, InterestListEvt ile) throws SQLException {
		this.mm = mm;
		this.id = id;
		this.ile = ile;
//		setAllList();
		setList(sortFlag);
	}// MarketMainEvt

//	public void setAllList() throws SQLException {
//
//		DefaultTableModel dtm = mm.getDtmProductList();
//
//		// JTable�� ���ڵ� �ʱ�ȭ
//		dtm.setRowCount(0);
//
//		Object[] rowData = null;
//		// JTable ���� ������ // object�� �ڹ��� ��� ���� �� ���� �� �ִ�.
//
//		// DBMS���� ��ȸ
//		UserDAO uDAO = UserDAO.getInstance();
//		List<AllListVO> list = uDAO.selectAllList(mm.getJcbArea().getSelectedIndex(),
//				mm.getJcbCategory().getSelectedIndex(), mm.getJtfSearch().getText().trim());
//
//		if (list.isEmpty()) {
//			JOptionPane.showMessageDialog(mm, "�˻����ǿ� �´� ��ǰ�� �����ϴ�. ");
//			mm.getJtfSearch().setText("");
//			mm.getJtfSearch().requestFocus();
//
//		}
//		AllListVO alv = null;
//		for (int i = 0; i < list.size(); i++) {
//			alv = list.get(i);
//			// ��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
//			rowData = new Object[7];
//			// �迭�� �� �Ҵ�
//			rowData[0] = alv.getImage();
//			rowData[1] = alv.getProductName() + "(" + alv.getProductCode() + ")";
//			rowData[2] = alv.getLoc_code();
//			rowData[3] = alv.getPrice();
//			rowData[4] = alv.getUpload_date();
//			rowData[5] = alv.getCategory();
//			rowData[6] = alv.getSellerID();
//			// dtm�� �߰�
//			dtm.addRow(rowData);
//		} // end for
//	}// setAllList
//
//	public void setListByID() throws SQLException {
//
//		DefaultTableModel dtm = mm.getDtmProductList();
//
//		// JTable�� ���ڵ� �ʱ�ȭ
//		dtm.setRowCount(0);
//
//		Object[] rowData = null;
//		// JTable ���� ������ // object�� �ڹ��� ��� ���� �� ���� �� �ִ�.
//
//		// DBMS���� ��ȸ
//		UserDAO uDAO = UserDAO.getInstance();
//		List<AllListVO> list = uDAO.selectListByID(mm.getJcbArea().getSelectedIndex(),
//				mm.getJcbCategory().getSelectedIndex(), mm.getJtfSearch().getText().trim());
//		if (list.isEmpty()) {
//			JOptionPane.showMessageDialog(mm, "�˻����ǿ� �´� ��ǰ�� �����ϴ�.");
//			mm.getJtfSearch().setText("");
//			mm.getJtfSearch().requestFocus();
//		}
//		AllListVO alv = null;
//		for (int i = 0; i < list.size(); i++) {
//			alv = list.get(i);
//			// ��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
//			rowData = new Object[7];
//			// �迭�� �� �Ҵ�
//			rowData[0] = alv.getImage();
//			rowData[1] = alv.getProductName() + "(" + alv.getProductCode() + ")";
//			rowData[2] = alv.getLoc_code();
//			rowData[3] = alv.getPrice();
//			rowData[4] = alv.getUpload_date();
//			rowData[5] = alv.getCategory();
//			rowData[6] = alv.getSellerID();
//			// dtm�� �߰�
//			dtm.addRow(rowData);
//		} // end for
//	}// setListByID
//
//	public void setRefresh() throws SQLException {
//
//		DefaultTableModel dtm = mm.getDtmProductList();
//
//		// JTable�� ���ڵ� �ʱ�ȭ
//		dtm.setRowCount(0);
//
//		Object[] rowData = null;
//		// JTable ���� ������ // object�� �ڹ��� ��� ���� �� ���� �� �ִ�.
//
//		// DBMS���� ��ȸ
//		UserDAO uDAO = UserDAO.getInstance();
//		List<AllListVO> list = uDAO.selectRefresh();
//		if (list.isEmpty()) {
//			JOptionPane.showMessageDialog(mm, "�˻����ǿ� �´� ��ǰ�� �����ϴ�.");
//			mm.getJtfSearch().setText("");
//			mm.getJtfSearch().requestFocus();
//		}
//		AllListVO alv = null;
//		for (int i = 0; i < list.size(); i++) {
//			alv = list.get(i);
//			// ��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
//			rowData = new Object[7];
//			// �迭�� �� �Ҵ�
//			rowData[0] = alv.getImage();
//			rowData[1] = alv.getProductName() + "(" + alv.getProductCode() + ")";
//			rowData[2] = alv.getLoc_code();
//			rowData[3] = alv.getPrice();
//			rowData[4] = alv.getUpload_date();
//			rowData[5] = alv.getCategory();
//			rowData[6] = alv.getSellerID();
//			// dtm�� �߰�
//			dtm.addRow(rowData);
//		} // end for
//	}// setRefresh
//
//	public void setListRecent() throws SQLException {
//
//		DefaultTableModel dtm = mm.getDtmProductList();
//
//		// JTable�� ���ڵ� �ʱ�ȭ
//		dtm.setRowCount(0);
//
//		Object[] rowData = null;
//		// JTable ���� ������ // object�� �ڹ��� ��� ���� �� ���� �� �ִ�.
//
//		// DBMS���� ��ȸ
//		UserDAO uDAO = UserDAO.getInstance();
//		List<AllListVO> list = uDAO.selectListRecent(mm.getJcbArea().getSelectedIndex(),
//				mm.getJcbCategory().getSelectedIndex(), mm.getJtfSearch().getText().trim());
//		if (list.isEmpty()) {
//			JOptionPane.showMessageDialog(mm, "�˻����ǿ� �´� ��ǰ�� �����ϴ�.");
//			mm.getJtfSearch().setText("");
//			mm.getJtfSearch().requestFocus();
//		}
//		AllListVO alv = null;
//		for (int i = 0; i < list.size(); i++) {
//			alv = list.get(i);
//			// ��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
//			rowData = new Object[7];
//			// �迭�� �� �Ҵ�
//			rowData[0] = alv.getImage();
//			rowData[1] = alv.getProductName() + "(" + alv.getProductCode() + ")";
//			rowData[2] = alv.getLoc_code();
//			rowData[3] = alv.getPrice();
//			rowData[4] = alv.getUpload_date();
//			rowData[5] = alv.getCategory();
//			rowData[6] = alv.getSellerID();
//			// dtm�� �߰�
//			dtm.addRow(rowData);
//		} // end for
//	}// setListRecent
//
//	public void setListPrice() throws SQLException {
//
//		DefaultTableModel dtm = mm.getDtmProductList();
//
//		// JTable�� ���ڵ� �ʱ�ȭ
//		dtm.setRowCount(0);
//
//		Object[] rowData = null;
//		// JTable ���� ������ // object�� �ڹ��� ��� ���� �� ���� �� �ִ�.
//
//		// DBMS���� ��ȸ
//		UserDAO uDAO = UserDAO.getInstance();
//		List<AllListVO> list = uDAO.selectListPrice(mm.getJcbArea().getSelectedIndex(),
//				mm.getJcbCategory().getSelectedIndex(), mm.getJtfSearch().getText().trim());
//		if (list.isEmpty()) {
//			JOptionPane.showMessageDialog(mm, "�˻����ǿ� �´� ��ǰ�� �����ϴ�.");
//			mm.getJtfSearch().setText("");
//			mm.getJtfSearch().requestFocus();
//		}
//		AllListVO alv = null;
//		for (int i = 0; i < list.size(); i++) {
//			alv = list.get(i);
//			// ��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
//			rowData = new Object[7];
//			// �迭�� �� �Ҵ�
//			rowData[0] = alv.getImage();
//			rowData[1] = alv.getProductName() + "(" + alv.getProductCode() + ")";
//			rowData[2] = alv.getLoc_code();
//			rowData[3] = alv.getPrice();
//			rowData[4] = alv.getUpload_date();
//			rowData[5] = alv.getCategory();
//			rowData[6] = alv.getSellerID();
//			// dtm�� �߰�
//			dtm.addRow(rowData);
//		} // end for
//	}// setListPrice

	public void productDetail() {

		// ������ ���� �ڵ带 �����ͼ� �� ������ ��ȸ

		JTable jtProductList = mm.getJtProductList();
		String temp = (String) jtProductList.getValueAt(jtProductList.getSelectedRow(), 1);
		String loc_code = (String) jtProductList.getValueAt(jtProductList.getSelectedRow(), 2);
		String productCode = temp.substring(temp.lastIndexOf("(") + 1, temp.lastIndexOf(")"));

		// DBMS���� ��ȸ
		UserDAO uDAO = UserDAO.getInstance();

		String classFlag = "M"; // userDAO���� selectProDetail method ����� �� ���п� �÷���

		try {
			MarketDetailVO mdVO = uDAO.selectProDetail(productCode, classFlag);
			if (mdVO != null) {//// ������ â�� ���� ���� ��ȸ�ÿ� �ش� ������ �÷��װ�
				// �ǸŵǴ� ���� ������ ����Ǿ� ��ȸ�� ���� ������ �޼����� �������� if��
				// ���� ������ ���̵�� ������ �Ǹ��� ���̵�� ������ MarketDetailBuyer
				// �ٸ��ٸ� MarketDetailSeller
				System.out.println(id + "/" + mdVO.getSellerID());
				if (mdVO.getSellerID().equals(id)) {
					new MarketDetailSeller(mm, mdVO, id);
				} else {
					new MarketDetailBuyer(mm, mdVO, id, ile);
				} // end else

			} else {
				JOptionPane.showMessageDialog(mm, "�Ǹ����� ��ǰ�� �ƴմϴ�.");
				setList(sortFlag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// productDetail

	public void setList(String sortFlag) {
		UserFileRecieve uFR = UserFileRecieve.getInstance();
		try {
			uFR.getImgFile();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		searchValueVO svVO = new searchValueVO(mm.getJcbArea().getSelectedIndex(),
				mm.getJcbCategory().getSelectedIndex(), mm.getJtfSearch().getText().trim(),
				mm.getJrbId().isSelected() ? "ID" : "SU", sortFlag);
		// UP DP UD DD NN
		DefaultTableModel dtm = mm.getDtmProductList();

		// JTable�� ���ڵ� �ʱ�ȭ
		dtm.setRowCount(0);

		Object[] rowData = null;
		// JTable ���� ������ // object�� �ڹ��� ��� ���� �� ���� �� �ִ�.

		// DBMS���� ��ȸ
		UserDAO uDAO = UserDAO.getInstance();
		List<AllListVO> list = null;
		try {
			list = uDAO.setList(svVO);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(mm, "���� �߻�");
			e.printStackTrace();
		}
		if (list.isEmpty()) {
			JOptionPane.showMessageDialog(mm, "�˻����ǿ� �´� ��ǰ�� �����ϴ�.");
			mm.getJtfSearch().setText("");
			mm.getJtfSearch().requestFocus();
		}
		AllListVO alv = null;
		for (int i = 0; i < list.size(); i++) {
			alv = list.get(i);
			// ��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
			rowData = new Object[7];
			// �迭�� �� �Ҵ�
			if(new File(RunMarketMain.imgPath+"/"+alv.getImage()).exists()) {
				
				rowData[0] = (new ImageIcon(new ImageIcon(RunMarketMain.imgPath+"/"+alv.getImage()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			}else {
				rowData[0] = alv.getImage();
			}
			rowData[1] = alv.getProductName() + "(" + alv.getProductCode() + ")";
			rowData[2] = alv.getLoc_code();
			rowData[3] = alv.getPrice();
			rowData[4] = alv.getUpload_date();
			rowData[5] = alv.getCategory();
			rowData[6] = alv.getSellerID();
			// dtm�� �߰�
			dtm.addRow(rowData);
		} // end for

	}// setList

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mm.getJbRecent()) {
			if(!recentFlag) {
				sortFlag = "UD";
				recentFlag = true;
			}else {
				sortFlag = "DD";
				recentFlag =false;
			}

		}
		if (ae.getSource() == mm.getJbPrice()) {
			if(!priceFlag) {
				sortFlag = "UP";
				priceFlag =true;
			}else {
				sortFlag = "DP";
				priceFlag =false;
				
			}

		}
		setList(sortFlag);
//
//		if (ae.getSource() == mm.getJcbArea() || ae.getSource() == mm.getJcbCategory()) {
//			try {
//				setAllList();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} // end catch
//		} // end if
//
//		if (mm.getJrbId().isSelected() && ae.getSource() == mm.getJbSearch()) {
//			try {
//				setListByID();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} // end catch
//		} else if (mm.getJrbSubject().isSelected() && ae.getSource() == mm.getJbSearch()) {
//			try {
//				setAllList();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} // end catch
//		} // end if
//
//		if (ae.getSource() == mm.getJtfSearch() && !mm.getJtfSearch().getText().trim().isEmpty()) {
//			mm.getJbSearch().doClick();
//		} // end if
//
//		if (ae.getSource() == mm.getJbRecent()) {
//			try {
//				setListRecent();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} // end catch
//		} // end if
//		if (ae.getSource() == mm.getJbPrice()) {
//			try {
//				setListPrice();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} // end catch
//		} // end if
//		if (ae.getSource() == mm.getJbRefresh()) {
//			try {
//				setRefresh();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} // end catch
//		} // end if

	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getClickCount() == 2) {// ����Ŭ��
			if (me.getSource() == mm.getJtProductList()) {
				productDetail();
			} // end if
		} // end if

	} 

}// class
