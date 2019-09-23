package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

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
//					setUpInfo("판매된 상품입니다.");
//				}else {
//					setUpInfo("다른 이용자와 거래 완료된 상품입니다.");
//				}
//				break;
//			case "D":
//				System.out.println("ND");
//				if (userId.equals(RunMarketMain.userId)) {
//					setUpInfo("삭제된 상품 입니다.");
//				}else {
//					setUpInfo("삭제한 상품 입니다.");
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
//					setUpInfo("상대방의 구매확정을 기다리고 있습니다.");
//				}
//				break;
//			case "D":
//				System.out.println("YD");
//				if (userId.equals(RunMarketMain.userId)) {
//					setUpInfo("삭제된 상품 입니다.");
//				}else {
//					setUpInfo("삭제한 상품 입니다.");
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
//				setUpInfo("구매한 상품 입니다.");
//			}else {
//				setUpInfo("거래완료된 상품 입니다.");
//			}
//			break;
//
//		}// end switch
		return fVO;
	}
	
	public void delete() {

		switch (JOptionPane.showConfirmDialog(mds, "판매글을 삭제하시겠습니까?")) {
		case JOptionPane.OK_OPTION:

			// DBMS에서 레코드를 삭제
			UserDAO uDAO = UserDAO.getInstance();

			String msg = "선택하신 판매글을 삭제하지 못했습니다.";
			try {

//			StringBuilder removeFileName = new StringBuilder(ld.getJlImg().getIcon().toString());
				String temp = (String) mds.getJtfName().getText();
				String product_code = temp.substring(temp.lastIndexOf("(") + 1, temp.lastIndexOf(")"));
				System.out.println(product_code);
				if (uDAO.deletePost(product_code)) {
					// 삭제된 이후 업로드된 파일을 삭제한다.
//					File originFile=new File(removeFileName.toString());
//					File thumbFile=new File(removeFileName.insert(removeFileName.lastIndexOf("/")+1,"rs_").toString());
//					originFile.delete();
//					thumbFile.delete();

					msg = "선택하신 판매글을 삭제하였습니다.";
					mds.dispose();
					// 부모창의 도시락 리스트를 갱신
					
					// 현재창 닫기
				} // end if
			} catch (SQLException e) {
				msg = " 삭제 작업 중 문제가 발생하였습니다.";
				e.printStackTrace();
			} // end catch
			JOptionPane.showMessageDialog(mds, msg);
		}// end switch
	}// deleteSaleList
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mds.getJbtComplete()) {
			String temp = mds.getJtfName().getText();
//			temp = temp.substring(temp.lastIndexOf('(')+1,temp.lastIndexOf(')')).trim();
//			System.out.println(temp+"상품코드");
			System.out.println(temp);
			
			FlagVO fVO = checkFlag();
			
			new DealSelect(temp,mds);
		}//end if
		
		if(e.getSource() == mds.getJbtDelete()) {
			delete();
		}//end if
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
	}
}
