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
	 
	
	public void modify() throws SQLException {
		
		
		MarketDetailVO mdVO=null;
		//VO에 넣을 값들
		String temp = (String) mds.getJtfName().getText();;	//상품명+코드
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
		
		//지역을 빼긴 뺐는데....
		//DAO에서 불러오면 상품정보 가져오면 되는거 아님????????
	

	}//modify
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == mds.getJbtComplete()) {
			String temp = mds.getJtfName().getText();
//			temp = temp.substring(temp.lastIndexOf('(')+1,temp.lastIndexOf(')')).trim();
//			System.out.println(temp+"상품코드");
			System.out.println(temp);
			
			//FlagVO fVO = checkFlag();
			
			sellerFlagVO sfVO = checkFlag();
			if(!sfVO.getAllFLag().equals("P")) {
				JOptionPane.showMessageDialog(mds, "판매중인 상품이 아닙니다. ");
//				mds.dispose();
				return;
			}else if (sfVO.getpFlag()>0) {
				JOptionPane.showMessageDialog(mds, "판매 완료된 상품입니다.");
				return;
				
			}else if (sfVO.getyFlag()>0) {
				JOptionPane.showMessageDialog(mds, "거래 진행중인 상품입니다.");
				return;
				
			}
			
			
			new DealSelect(temp,mds);
		}//end if
		
		if(ae.getSource() == mds.getJbtDelete()) {
			sellerFlagVO sfVO = checkFlag();
			if(!sfVO.getAllFLag().equals("P")) {
				JOptionPane.showMessageDialog(mds, "판매중인 상품이 아닙니다. ");
//				mds.dispose();
				return;
			}else if (sfVO.getpFlag()>0) {
				JOptionPane.showMessageDialog(mds, "판매 완료된 상품입니다.");
				return;
				
			}else if (sfVO.getyFlag()>0) {
				JOptionPane.showMessageDialog(mds, "거래 진행중인 상품입니다.");
				return;
				
			}
			delete();
		}//end if
		
		if(ae.getSource() == mds.getJbtChange()) {
			sellerFlagVO sfVO = checkFlag();
			if(!sfVO.getAllFLag().equals("P")) {
				JOptionPane.showMessageDialog(mds, "판매중인 상품이 아닙니다. ");
//				mds.dispose();
				return;
			}else if (sfVO.getpFlag()>0) {
				JOptionPane.showMessageDialog(mds, "판매 완료된 상품입니다.");
				return;
				
			}else if (sfVO.getyFlag()>0) {
				JOptionPane.showMessageDialog(mds, "거래 진행중인 상품입니다.");
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
