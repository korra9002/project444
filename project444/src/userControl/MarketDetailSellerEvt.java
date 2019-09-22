package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.FlagVO;
import userView.DealSelect;
import userView.MarketDetailSeller;
import userView.MarketMain;



public class MarketDetailSellerEvt extends MouseAdapter implements ActionListener {
	private MarketDetailSeller mds;
	private MarketMain mm;
	private String id;
	
	public MarketDetailSellerEvt(MarketMain mm, MarketDetailSeller mds) {
	
	this.mm=mm;
	this.mds=mds;
	this.id=id;
	
	
}
	public FlagVO checkFlag() {
		FlagVO fVO = null;
		String productCode = mds.getMdVO().getProductCode();
		UserDAO uDAO = UserDAO.getInstance();
		try {
			fVO = uDAO.checkFlag(productCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return fVO;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mds.getJbtComplete()) {
			String temp = mds.getJtfName().getText();
//			temp = temp.substring(temp.lastIndexOf('(')+1,temp.lastIndexOf(')')).trim();
//			System.out.println(temp+"��ǰ�ڵ�");
			System.out.println(temp);
			
			FlagVO fVO = checkFlag();
			
			new DealSelect(temp,mds);
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
	}
}
