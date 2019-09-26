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
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import userDAO.UserDAO;
import userFileRecieve.UserFileRecieve;
import userRun.RunMarketMain;
import userVO.AllListVO;
import userVO.MarketDetailVO;
import userVO.SaleListVO;
import userView.MarketDetailBuyer;
import userView.MarketDetailSeller;
import userView.ModifyProduct;
import userView.SaleList;

public class SaleListEvt extends MouseAdapter implements ActionListener {
	private SaleList sl;
	private RunMarketMain rmm;
	String id;
	String classFlag = "";
	String productCode = "";
	DecimalFormat df=new DecimalFormat ("#,###,###");
	
	public SaleListEvt(SaleList sl, RunMarketMain rmm) throws SQLException {
		this.sl = sl;
		this.rmm = rmm;
		id = RunMarketMain.userId;
		setAllList();

	}// SaleListEvt

	public void loadFile() {
		/////////////////// ������ �����ؼ� ���� �ޱ� /////////////////
		UserFileRecieve uFR = UserFileRecieve.getInstance();
		try {
			uFR.getImgFile();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(sl, "���ϼ����� ���� ����");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(sl, "���� �ε� ����");
			e.printStackTrace();
		}
		///////////////////////////////////////////////////
	}//loadFile
	
	
	/**
	 * �ǸŸ�� ����
	 * 
	 * @param sl
	 * @throws SQLException
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
	public void setAllList() throws SQLException {
		loadFile();
		

		DefaultTableModel dtm = sl.getDtmSell();

		// JTable�� ���ڵ� �ʱ�ȭ
		dtm.setRowCount(0);

		Object[] rowData = null;
		String temp_flag = "S";
		// DBMS���� ��ȸ
		UserDAO uDAO = UserDAO.getInstance();
		List<SaleListVO> list = uDAO.selectSaleList(id, temp_flag);
//		if (list.isEmpty()) {
//			JOptionPane.showMessageDialog(sl, "���� �Ǹ��Ͻô� ��ǰ�� �����ϴ�. ");
//
//		} /// �޼��� �ȶ� �������� ����
		SaleListVO slv = null;
		for (int i = 0; i < list.size(); i++) {
			slv = list.get(i);
			// ��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
			rowData = new Object[7];
			// �迭�� �� �Ҵ�
			if (new File(RunMarketMain.imgPath + "/" + slv.getImage()).exists()) {

				rowData[0] = (new ImageIcon(new ImageIcon(RunMarketMain.imgPath + "/" + slv.getImage()).getImage()
						.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			} else {
				rowData[0] = (new ImageIcon(new ImageIcon(RunMarketMain.imgPath+"/"+"default.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			}
			rowData[1] = slv.getProductName() + "(" + slv.getProductCode() + ")";
			rowData[2] = df.format(slv.getPrice());
			rowData[3] = slv.getCategory();
			rowData[4] = slv.getLoc_code();
			rowData[5] = slv.getUpload_date();
			rowData[6] = slv.getCheck();
			// dtm�� �߰�
			dtm.addRow(rowData);
		} // end for
	}// setAllList

	/**
	 * �ǸſϷ�� ��ǰ��� ����
	 * 
	 * @param sl
	 * @throws SQLException
	 */
	public void setCompList() throws SQLException {
		loadFile();
		
		DefaultTableModel dtm = sl.getDtmComp();

		// JTable�� ���ڵ� �ʱ�ȭ
		dtm.setRowCount(0);

		Object[] rowData = null;

		// DBMS���� ��ȸ
		UserDAO uDAO = UserDAO.getInstance();
		List<SaleListVO> list = uDAO.selectCompList(RunMarketMain.userId);
//		if (list.isEmpty()) {
//			JOptionPane.showMessageDialog(sl, "���� �ǸſϷ�� ��ǰ�� �����ϴ�. ");
//
//		}
		SaleListVO slv = null;
		for (int i = 0; i < list.size(); i++) {
			slv = list.get(i);
			// ��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
			rowData = new Object[7];
			// �迭�� �� �Ҵ�
			if (new File(RunMarketMain.imgPath + "/" + slv.getImage()).exists()) {

				rowData[0] = (new ImageIcon(new ImageIcon(RunMarketMain.imgPath + "/" + slv.getImage()).getImage()
						.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			} else {
				rowData[0] = (new ImageIcon(new ImageIcon(RunMarketMain.imgPath+"/"+"default.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			}
			rowData[1] = slv.getProductName() + "(" + slv.getProductCode() + ")";
			rowData[2] = df.format(slv.getPrice());
			rowData[3] = slv.getCategory();
			rowData[4] = slv.getLoc_code();
			rowData[5] = slv.getSellerID();
			rowData[6] = slv.getUpload_date();

			// dtm�� �߰�
			dtm.addRow(rowData);
		} // end for
	}// setAllList
	
	public void modifySaleList() throws SQLException {
		
		MarketDetailVO mdVO=null;
		//VO�� ���� ����
		String temp=(String)sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 1);	//��ǰ��+�ڵ�
		String product_Code = temp.substring(temp.lastIndexOf("(") + 1, temp.lastIndexOf(")"));
		
//		ImageIcon image=(ImageIcon)sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 0); //�̹���
//		String productName= temp.substring(0, temp.lastIndexOf( "(" ) - 1);
//		
//		int price=(int)sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 2);		//����
//		String category=(String)sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 3); //ī�װ�
//
//		String seller=sl.getJtSell().get
//		
//		String pDetail=mds.getJtaStrongPoint().getText().trim();
//		String sellerID=mds.getJtfId().getText().trim();
		
		UserDAO uDAO=UserDAO.getInstance();
		uDAO.selectProDetail(product_Code, "M");

		
		
		mdVO=uDAO.selectProDetail(product_Code, "M");
		
		new ModifyProduct(mdVO ,this, rmm);
		

		
		
	}//modifySaleList

	public void deleteSaleList() {

		switch (JOptionPane.showConfirmDialog(sl, "�Ǹű��� �����Ͻðڽ��ϱ�?")) {
		case JOptionPane.OK_OPTION:

			// DBMS���� ���ڵ带 ����
			UserDAO uDAO = UserDAO.getInstance();

			String msg = "�����Ͻ� �Ǹű��� �������� ���߽��ϴ�.";
			try {

//			StringBuilder removeFileName = new StringBuilder(ld.getJlImg().getIcon().toString());
				String temp = (String) sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 1);
				String product_code = temp.substring(temp.lastIndexOf("(") + 1, temp.lastIndexOf(")"));
				System.out.println(product_code);
				if (uDAO.deletePost(product_code)) {
					// ������ ���� ���ε�� ������ �����Ѵ�.
//					File originFile=new File(removeFileName.toString());
//					File thumbFile=new File(removeFileName.insert(removeFileName.lastIndexOf("/")+1,"rs_").toString());
//					originFile.delete();
//					thumbFile.delete();

					msg = "�����Ͻ� �Ǹű��� �����Ͽ����ϴ�.";

					// �θ�â�� ���ö� ����Ʈ�� ����
					setAllList();
					// ����â �ݱ�
					// close();
				} // end if
			} catch (SQLException e) {
				msg = " ���� �۾� �� ������ �߻��Ͽ����ϴ�.";
				e.printStackTrace();
			} // end catch
			JOptionPane.showMessageDialog(sl, msg);
		}// end switch
	}// deleteSaleList

	public void openDetail() throws SQLException {

		if (classFlag == "S") {
			JTable jtProductList = sl.getJtSell();

			String temp = (String) jtProductList.getValueAt(jtProductList.getSelectedRow(), 1);
			productCode = temp.substring(temp.lastIndexOf("(") + 1, temp.lastIndexOf(")"));

		} else if (classFlag == "C") {
			JTable jtComList = sl.getJtComplete();

			String temp = (String) jtComList.getValueAt(jtComList.getSelectedRow(), 1);
			productCode = temp.substring(temp.lastIndexOf("(") + 1, temp.lastIndexOf(")"));

		}

		// DBMS���� ��ȸ
		UserDAO uDAO = UserDAO.getInstance();

		MarketDetailVO mdVO = uDAO.selectProDetail(productCode, classFlag);

		if (mdVO != null) {

			// ���� ������ ���̵�� ������ �Ǹ��� ���̵�� ������ MarketDetailBuyer
			// �ٸ��ٸ� MarketDetailSeller
			new MarketDetailSeller(null, mdVO, id);
		} else {
			JOptionPane.showMessageDialog(sl, "�Ǹ����� ��ǰ�� �ƴմϴ�.");
		}

	}// openDetail

	/**
	 * �Ǹų��� â �ݱ�
	 */
	public void close() {
		sl.dispose();
	}// close

	@Override
	public void mouseClicked(MouseEvent me) {
		// �ǸſϷ� ��ǰ���� ������ �� ó��

		if (me.getSource() == sl.getJtp()) {
			JTabbedPane jtpTemp = (JTabbedPane) me.getSource();

			if (jtpTemp.getSelectedIndex() == 0) {
				try {
					setAllList();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (jtpTemp.getSelectedIndex() == 1) {

				try {
					setCompList();
				} catch (SQLException e) {
					e.printStackTrace();
				} // end catch

			} // end if

		} // end if

		if (me.getClickCount() == 2) {// ����Ŭ��			
			if (me.getSource() == sl.getJtSell()) {
				if(sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(),6).equals("P")) {						
					try {
						classFlag = "S"; // userDAO���� selectProDetail method ����� �� ���п� �÷��� ---->�Ǹ����θ��
	
						openDetail();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}//end catch
					
				} else {
					JOptionPane.showMessageDialog(sl, "�ش� ��ǰ�� �˼����Դϴ�");
				}//end if
			} else if (me.getSource() == sl.getJtComplete()) {
//				try {
////					classFlag = "C"; // userDAO���� selectProDetail method ����� �� ���п� �÷��� ---->�ǸſϷ�ȸ��
////					openDetail();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
			}//end else
		} // end if

	}// mouseClicked

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (sl.getJtSell().getSelectedRow() != -1) {
			if (ae.getSource() == sl.getJbtModify()) {
				if(sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 6).equals("P")) {
					try {
						modifySaleList();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}//end catch

				} else {
					JOptionPane.showMessageDialog(sl, "�ش� ��ǰ�� �˼����Դϴ�.");
				}//end else
			} // end if
			if (ae.getSource() == sl.getJbtDelete()) {
				
				if(sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 6).equals("P")) {
				
				
				deleteSaleList();
				} else {
					JOptionPane.showMessageDialog(sl, "�ش� ��ǰ�� �˼����Դϴ�.");
				}//end else
			} // end if

		
		} else {
			JOptionPane.showMessageDialog(sl, "����/������ ���Ͻô� ��ǰ�� �������ּ���.");// end if
		} // end else
		

	}// actionPerformed

}// SaleListEvt
