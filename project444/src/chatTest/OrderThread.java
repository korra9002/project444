package chatTest;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JTextArea;
import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.FlagVO;

public class OrderThread extends Thread {
	private JTextArea jtaChatView;
	private String me, you, dealCode; // me = 자신의 아이디 you = 상대방의 아이디 dealCode = DB에서 현재 채팅을 구별하는 고유 코드

	private String dealFlag; // 거래중인 상품의 변동여부를 확인하는 플래그
	private String allFlag; // 거래중인 상품의 변동여부를 확인하는 플래그
	private String userId;

	private ChattingView cv; //채팅뷰 클래스 
	
	private FlagVO fVO; //거래 관련 플래그와 아이디를 저장하는 클래스
	
	//채팅뷰 클래스와 거래코드  상대방과 자신의 아이디를 생성자의 매개변수로 받는다
	public OrderThread(ChattingView cv, String me, String you, String dealCode) {
		this.cv = cv;
		jtaChatView = cv.getJtaChatView();
		this.me = me;
		this.you = you;
		this.dealCode = dealCode;

	}
	
	//현재 거래중인 상품에 변동이 있을경우 해당 메세지를 보여주고 디테일창 실행 버튼을 못누르게 막는다 
	public void setUpInfo(String msg) {
		cv.getJbtProductInfo().setVisible(false);
		cv.getJlResult().setText(msg);
		cv.getJlResult().setVisible(true);
		cv.add(cv.getJlResult());
	}

	//거래 확정 메세지를 보여주고 예 아니오 버튼을 보여준다
	public void setDealInfo() {
		cv.getJlNotice().setText(you + "님에게 상품을 받으 셨나요?");
		cv.getJlNotice().setVisible(true);
		cv.getJbtOk().setVisible(true);
		cv.getJbtCancle().setVisible(true);
	}

	
	//거래 상태에 관한 플래그를 조회하는 메서드
	public void checkFlag() {
		UserDAO uDAO = UserDAO.getInstance();

		try {
			//DB에서 해당 상품의 변동사항을 조회
			fVO = uDAO.checkFlag(dealCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//상품의 변동사항이 없으면 아래 과정을 건너 뛴다
		if (dealFlag == fVO.getDealFLag() && allFlag == fVO.getAllFlag()) {
			return;
		}
		dealFlag = fVO.getDealFLag();
		allFlag = fVO.getAllFlag();
		userId = fVO.getId();

		//플래그에 따라 상황을 구별하여 메세지를 보여준다
		switch (dealFlag) {
		case "N":
			switch (allFlag) {
			case "B":
				if (!userId.equals(RunMarketMain.userId)) {
					setUpInfo("판매된 상품입니다.");
				} else {
					setUpInfo("다른 이용자와 거래 완료된 상품입니다.");
				}
				break;
			case "D":
				if (userId.equals(RunMarketMain.userId)) {
					setUpInfo("삭제된 상품 입니다.");
				} else {
					setUpInfo("삭제한 상품 입니다.");
				}
				break;
			}// end switch
			break;
		case "Y":
			switch (allFlag) {
			case "P":
				if (userId.equals(RunMarketMain.userId)) {
					setDealInfo();
				} else {
					setUpInfo("상대방의 구매확정을 기다리고 있습니다.");
				}
				break;
			case "D":
				if (userId.equals(RunMarketMain.userId)) {
					setUpInfo("삭제된 상품 입니다.");
				} else {
					setUpInfo("삭제한 상품 입니다.");
				}
				break;

			}// end switch

			break;
		case "P":
			if (userId.equals(RunMarketMain.userId)) {

				cv.getJlNotice().setVisible(false);
				cv.getJbtOk().setVisible(false);
				cv.getJbtCancle().setVisible(false);
				setUpInfo("구매한 상품 입니다.");
			} else {
				setUpInfo("거래완료된 상품 입니다.");
			}
			break;

		}// end switch
	}

	public void run() {
		UserDAO uDAO = UserDAO.getInstance();
		try {
			List<ChatVO> list = null;
			ChatVO cv = null;

			///// 채팅 시작시 플래그체크

			checkFlag();
			//채팅창이 처음 열렸을때 모든 해당거래의 채팅내역을 리스트에 저장
			list = uDAO.selectAllChat(me, you, dealCode);
			
			//리스트에 저장된 채팅내용들을 화면에 띄운다
			for (int i = 0; i < list.size(); i++) {
				cv = list.get(i);

				jtaChatView.append(cv.getYou() + " : " + cv.getChat() + "\n");

			} // end for
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} // end catch

			//스크롤을 맨 아래로 조정
			jtaChatView.setCaretPosition(jtaChatView.getDocument().getLength());
			
			list = null;

			//채팅내용을 DB에서 0.5초마다 조회하여 추가 메세지가 있으면 보여준다
			while (true) {

				list = uDAO.selectChat(me, you, dealCode);

				for (int i = 0; i < list.size(); i++) {
					cv = list.get(i);
					jtaChatView.append(cv.getYou() + " : " + cv.getChat() + "\n");
					jtaChatView.setCaretPosition(jtaChatView.getDocument().getLength());
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
