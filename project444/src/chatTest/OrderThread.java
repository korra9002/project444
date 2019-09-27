package chatTest;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JScrollPane;

import javax.swing.JTextArea;

import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.FlagVO;

//import kr.co.sist.util.cipher.DataDecrypt;

public class OrderThread extends Thread {
//	private JTable jtOrder;
//	private DefaultTableModel dtm;
//
//	public OrderThread(JTable jtOrder, DefaultTableModel dtm) {
//		this.jtOrder = jtOrder;
//		this.dtm = dtm;
//	}// OrderThread
	private JTextArea jtaChatView;
	private String me, you, dealCode;
//	private StringBuilder stb;
	private JScrollPane jsp;
	
	private String dealFlag ;
	private String allFlag ;
	private String userId ;

	private ChattingView cv;
	
	public OrderThread(ChattingView cv, String me, String you, String dealCode) {
		this.cv = cv;
		jtaChatView = cv.getJtaChatView();
		jsp = cv.getJsp();
		this.me = me;
		this.you = you;
		this.dealCode = dealCode;
		// this.stb= stb;

	}
	
	public void setUpInfo(String msg) {
	cv.getJbtProductInfo().setVisible(false);
	 cv.getJlResult().setText(msg);
	 cv.getJlResult().setVisible(true);
	 cv.add(cv.getJlResult());
	}
	
	public void setDealInfo() {
		cv.getJlNotice().setText(you+"님에게 상품을 받으 셨나요?");
		cv.getJlNotice().setVisible(true);
		cv.getJbtOk().setVisible(true);
		cv.getJbtCancle().setVisible(true);
	}
	
	private FlagVO fVO = null;
	public void checkFlag() {
		UserDAO uDAO = UserDAO.getInstance();

		try {
			fVO = uDAO.checkFlag(dealCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		if(dealFlag == fVO.getDealFLag() && allFlag == fVO.getAllFlag()) {
			return;
		}
		dealFlag = fVO.getDealFLag();
		allFlag = fVO.getAllFlag();
		userId = fVO.getId();

		
		switch (dealFlag) {
		case "N":
			switch (allFlag) {
			case "B":
				System.out.println("NB");
				System.out.println(userId+"/"+RunMarketMain.userId);
				if (!userId.equals(RunMarketMain.userId)) {
					setUpInfo("판매된 상품입니다.");
				}else {
					setUpInfo("다른 이용자와 거래 완료된 상품입니다.");
				}
				break;
			case "D":
				System.out.println("ND");
				if (userId.equals(RunMarketMain.userId)) {
					setUpInfo("삭제된 상품 입니다.");
				}else {
					setUpInfo("삭제한 상품 입니다.");
				}
				break;
			}// end switch
			break;
		case "Y":
			switch (allFlag) {
			case "P":
				System.out.println("YP");
				if (userId.equals(RunMarketMain.userId)) {
					setDealInfo();
				}else {
					setUpInfo("상대방의 구매확정을 기다리고 있습니다.");
				}
				break;
			case "D":
				System.out.println("YD");
				if (userId.equals(RunMarketMain.userId)) {
					setUpInfo("삭제된 상품 입니다.");
				}else {
					setUpInfo("삭제한 상품 입니다.");
				}
				break;

			}// end switch

			break;
		case "P":
			System.out.println("P");
			if (userId.equals(RunMarketMain.userId)) {
				
				cv.getJlNotice().setVisible(false);
				cv.getJbtOk().setVisible(false);
				cv.getJbtCancle().setVisible(false);
				setUpInfo("구매한 상품 입니다.");
			}else {
				setUpInfo("거래완료된 상품 입니다.");
			}
			break;

		}// end switch
	}
	

	public void run() {
		UserDAO uDAO = UserDAO.getInstance();
		FlagVO fVO = null;
		try {
			List<ChatVO> list = null;
//			Object[] rowData = null;
			ChatVO cv = null;

			///// 채팅 시작시 플래그체크
	
			checkFlag();
//			
//			if (dealFlag.equals("N")) {
//			
//			}
//			if (dealFlag.equals("Y")) {
//		
//			}
//			if (dealFlag.equals("P")) {
//
//			}

			list = uDAO.selectAllChat(me, you, dealCode);

			for (int i = 0; i < list.size(); i++) {
				cv = list.get(i);

				jtaChatView.append(cv.getYou() + " : " + cv.getChat() + "\n");

			} // end for
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} // end catch

			jtaChatView.setCaretPosition(jtaChatView.getDocument().getLength());
//			jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
			list = null;

//			boolean flag = false;
			while (true) {
				// 주문 목록을 조회

				list = uDAO.selectChat(me, you, dealCode);

				for (int i = 0; i < list.size(); i++) {
					cv = list.get(i);
					System.out.println(cv.getYou() + " : " + cv.getChat() + "\n");
					jtaChatView.append(cv.getYou() + " : " + cv.getChat() + "\n");
					jtaChatView.setCaretPosition(jtaChatView.getDocument().getLength());
//					jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
				} // end for
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} // end catch

			checkFlag();

			} // end while
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// run

}// class
