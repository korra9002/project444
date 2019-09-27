package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.FlagVO;
import userVO.MarketDetailVO;
import userVO.sellerFlagVO;
import userView.DealSelect;
import userView.MarketDetailSeller;
import userView.MarketMain;
import userView.ModifyProduct;



public class MarketDetailSellerEvt extends MouseAdapter implements ActionListener {
	private MarketDetailSeller mds;
	private MarketMain mm;
	private String id;
	
	public MarketDetailSellerEvt(MarketMain mm, MarketDetailSeller mds) {
	
	this.mm=mm;
	this.mds=mds;
	this.id=id;
	
	
}
	public sellerFlagVO checkFlag() {
		sellerFlagVO sFVO = null;
		String productCode = mds.getMdVO().getProductCode();
		UserDAO uDAO = UserDAO.getInstance();
		try {
			sFVO = uDAO.checkFlag2(productCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		return sFVO;
	}
	
		
//		if(dealFlag == fVO.getDealFLag() && allFlag == fVO.getAllFlag()) {
//			return;
//		}
//		String dealFlag = fVO.getDealFLag();
//		String allFlag = fVO.getAllFlag();
//		String userId = fVO.getId();

//		
//		switch (dealFlag) {
//		case "N":
//			switch (allFlag) {
//			case "B":
//				System.out.println("NB");
//				if (userId.equals(RunMarketMain.userId)) {
//					setUpInfo("�Ǹŵ� ��ǰ�Դϴ�.");
//				}else {
//					setUpInfo("�ٸ� �̿��ڿ� �ŷ� �Ϸ�� ��ǰ�Դϴ�.");
//				}
//				break;
//			case "D":
//				System.out.println("ND");
//				if (userId.equals(RunMarketMain.userId)) {
//					setUpInfo("������ ��ǰ �Դϴ�.");
//				}else {
//					setUpInfo("������ ��ǰ �Դϴ�.");
//				}
//				break;
//			}// end switch
//			break;
//		case "Y":
//			switch (allFlag) {
//			case "P":
//				System.out.println("YP");
//				if (userId.equals(RunMarketMain.userId)) {
//					setDealInfo();
//				}else {
//					setUpInfo("������ ����Ȯ���� ��ٸ��� �ֽ��ϴ�.");
//				}
//				break;
//			case "D":
//				System.out.println("YD");
//				if (userId.equals(RunMarketMain.userId)) {
//					setUpInfo("������ ��ǰ �Դϴ�.");
//				}else {
//					setUpInfo("������ ��ǰ �Դϴ�.");
//				}
//				break;
//
//			}// end switch
//
//			break;
//		case "P":
//			System.out.println("P");
//			if (userId.equals(RunMarketMain.userId)) {
//				
//				cv.getJlNotice().setVisible(true);
//				cv.getJbtOk().setVisible(true);
//				cv.getJbtCancle().setVisible(true);
//				setUpInfo("������ ��ǰ �Դϴ�.");
//			}else {
//				setUpInfo("�ŷ��Ϸ�� ��ǰ �Դϴ�.");
//			}
//			break;
//
//		}// end switch

	public void delete() {

		switch (JOptionPane.showConfirmDialog(mds,"�Ǹű��� �����Ͻðڽ��ϱ�?","�Ǹű� ����",JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE)) {
		case JOptionPane.OK_OPTION:

			// DBMS���� ���ڵ带 ����
			UserDAO uDAO = UserDAO.getInstance();

			String msg = "�����Ͻ� �Ǹű��� �������� ���߽��ϴ�.";
			try {

//			StringBuilder removeFileName = new StringBuilder(ld.getJlImg().getIcon().toString());
				String temp = (String) mds.getJtfName().getText();
				String product_code = temp.substring(temp.lastIndexOf("(") + 1, temp.lastIndexOf(")"));
				System.out.println(product_code);
				if (uDAO.deletePost(product_code)) {
					// ������ ���� ���ε�� ������ �����Ѵ�.
//					File originFile=new File(removeFileName.toString());
//					File thumbFile=new File(removeFileName.insert(removeFileName.lastIndexOf("/")+1,"rs_").toString());
//					originFile.delete();
//					thumbFile.delete();

					msg = "�����Ͻ� �Ǹű��� �����Ͽ����ϴ�.";
					mds.dispose();
					// �θ�â�� ���ö� ����Ʈ�� ����
					
					// ����â �ݱ�
				} // end if
			} catch (SQLException e) {
				msg = " ���� �۾� �� ������ �߻��Ͽ����ϴ�.";
				e.printStackTrace();
			} // end catch
			JOptionPane.showMessageDialog(mds, msg);
		}// end switch
	}// deleteSaleList
	 
	 
	public void modify() throws SQLException {
		
		
		MarketDetailVO mdVO=null;
		//VO�� ���� ����
		String temp = (String) mds.getJtfName().getText();;	//��ǰ��+�ڵ�
		String product_Code = temp.substring(temp.lastIndexOf("(") + 1, temp.lastIndexOf(")"));

		UserDAO uDAO=UserDAO.getInstance();
		uDAO.selectProDetail(product_Code, "M");

		
		
		mdVO=uDAO.selectProDetail(product_Code, "M");
		
		new ModifyProduct(mdVO ,null, null);
		
		/*
		 * ImageIcon image=(ImageIcon)mds.getJlDetailImg().getIcon();
		 * 
		 * int price= Integer.parseInt(mds.getJtfPrice().getText().trim()); String
		 * category=mds.getJtfCategory().getText().trim(); String
		 * pDetail=mds.getJtaStrongPoint().getText().trim(); String
		 * sellerID=mds.getJtfId().getText().trim();
		 */
		
		//������ ���� ���µ�....
		//DAO���� �ҷ����� ��ǰ���� �������� �Ǵ°� �ƴ�????????
	

	}//modify
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == mds.getJbtComplete()) {
			String temp = mds.getJtfName().getText();
//			temp = temp.substring(temp.lastIndexOf('(')+1,temp.lastIndexOf(')')).trim();
//			System.out.println(temp+"��ǰ�ڵ�");
			System.out.println(temp);
			
			//FlagVO fVO = checkFlag();
			
			sellerFlagVO sfVO = checkFlag();
			System.out.println(sfVO);
			if(!sfVO.getAllFLag().equals("P")) {
				JOptionPane.showMessageDialog(mds, "�Ǹ����� ��ǰ�� �ƴմϴ�. ");
//				mds.dispose();
				return;
			}else if (sfVO.getpFlag()>0) {
				JOptionPane.showMessageDialog(mds, "�Ǹ� �Ϸ�� ��ǰ�Դϴ�.");
				return;
				
			}else if (sfVO.getyFlag()>0) {
				JOptionPane.showMessageDialog(mds, "�ŷ� �������� ��ǰ�Դϴ�.");
				return;
				
			}
			
			
			new DealSelect(temp,mds);
		}//end if
		
		if(ae.getSource() == mds.getJbtDelete()) {
			sellerFlagVO sfVO = checkFlag();
			System.out.println(sfVO);
			if(!sfVO.getAllFLag().equals("P")) {
				JOptionPane.showMessageDialog(mds, "�Ǹ����� ��ǰ�� �ƴմϴ�. ");
//				mds.dispose();
				return;
			}else if (sfVO.getpFlag()>0) {
				JOptionPane.showMessageDialog(mds, "�Ǹ� �Ϸ�� ��ǰ�Դϴ�.");
				return;
				
			}else if (sfVO.getyFlag()>0) {
				JOptionPane.showMessageDialog(mds, "�ŷ� �������� ��ǰ�Դϴ�.");
				return;
				
			}
			delete();
		}//end if
		
		if(ae.getSource() == mds.getJbtChange()) {
			sellerFlagVO sfVO = checkFlag();
			System.out.println(sfVO);
			if(!sfVO.getAllFLag().equals("P")) {
				JOptionPane.showMessageDialog(mds, "�Ǹ����� ��ǰ�� �ƴմϴ�. ");
//				mds.dispose();
				return;
			}else if (sfVO.getpFlag()>0) {
				JOptionPane.showMessageDialog(mds, "�Ǹ� �Ϸ�� ��ǰ�Դϴ�.");
				return;
				
			}else if (sfVO.getyFlag()>0) {
				JOptionPane.showMessageDialog(mds, "�ŷ� �������� ��ǰ�Դϴ�.");
				return;
				
			}
			try {
				modify();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
	}
}
