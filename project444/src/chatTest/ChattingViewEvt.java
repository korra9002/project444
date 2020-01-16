package chatTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import userDAO.UserDAO;
import userVO.DCodeAndIdAO;
import userVO.MarketDetailVO;
import userView.MarketDetailBuyer;
import userView.MarketDetailSeller;

// 채팅창이 실행되었을 때 이벤트를 처리하는 클래스  
public class ChattingViewEvt extends MouseAdapter implements ActionListener {
	private ChattingView cv;
	private UserDAO uDAO; // DB조회를 위한 클래스
	private String me, you, dealCode; // me = 자신의 아이디 you = 상대방의 아이디 dealCode = DB에서 현재 채팅을 구별하는 고유 코드
	private DCodeAndIdAO DIAO; // dealCode와 상대방의 아이디를 저장하는 클래스

	public ChattingViewEvt(ChattingView cv, DCodeAndIdAO DIAO) {
		super();
		this.cv = cv;
		this.DIAO = DIAO;

		uDAO = UserDAO.getInstance();
		me = cv.getId();
		you = DIAO.getId();
		dealCode = DIAO.getDealCode();

		// 채팅창이 실행 될 때 해당 채팅내역을 DB에서 읽어오는 쓰레드를 실행
		OrderThread ot = new OrderThread(cv, me, you, dealCode);
		ot.start();

		cv.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

				ot.stop();
				cv.dispose();
			}// windowClosing

		});// addWindowListener

	}

	// 채팅창의 스크롤을 맨 아래로 옮기는 메서드
	private void scrollPosition() {
		JTextArea jta = cv.getJtaChatView();
		jta.setCaretPosition(jta.getDocument().getLength());

	}

	// 메세지를 보내는 메서드
	private void sendMsg() throws IOException {
		String msg = cv.getJtaChatField().getText().trim();

		if (!msg.isEmpty()) {
			cv.getJtaChatView().append(me + ": " + msg + "\n");
			try {
				// 아이디와 메세지 거래코드를 담아 DB에 추가
				uDAO.sendChat(me, you, msg, dealCode);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cv.getJtaChatField().setText("");
			scrollPosition();
		}

	}// sendMsg

	// 채팅창에서 구매 확인 예 아니오 눌렀을 때
	private void selectDeal(String flag) {
		UserDAO uDAO = UserDAO.getInstance();
		int result = 0;
		try {
			// 구매확인에서 예 아니오 누른 결과를 DB에 업데이트
			result = uDAO.selectDeal(flag, dealCode);

			JOptionPane.showMessageDialog(cv, "처리완료");
			cv.getJlNotice().setVisible(false);
			cv.getJbtOk().setVisible(false);
			cv.getJbtCancle().setVisible(false);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(cv, "오류 발생");
			e.printStackTrace();
		}
	}// selectDeal

	// 현재 거래중인 상품 디테일을 실행하는 메서드
	private void openDetail() {
		UserDAO uDAO = UserDAO.getInstance();
		String productCode = "";
		try {
			productCode = uDAO.getProCode(dealCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String classFlag = "M"; // userDAO에서 selectProDetail method 사용할 때 구분용 플래그

		try {
			MarketDetailVO mdVO = uDAO.selectProDetail(productCode, classFlag);
			if (mdVO != null) {
				//// 디테일 창을 띄우기 위한 조회시에 해당 물건의 플래그가
				// 판매되는 등의 이유로 변경되어 조회된 값이 없을떄 메세지를 띄우기위한 if문
				// 현재 접속한 아이디와 포스팅 판매자 아이디와 같으면 MarketDetailBuyer
				// 다르다면 MarketDetailSeller
				if (mdVO.getSellerID().equals(me)) {
					new MarketDetailSeller(null, mdVO, me, null);
				} else {
					new MarketDetailBuyer(null, mdVO, me, null);
				} // end else

			} else {
				JOptionPane.showMessageDialog(cv, "판매중인 상품이 아닙니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}

	public void actionPerformed(ActionEvent e) {

		// 채팅창에서 엔터를 누르거나 전송버튼 눌렀을 때
		if (e.getSource() == cv.getJtaChatField() || e.getSource() == cv.getJbtSend()) {
			try {
				sendMsg();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(cv, "대화상대가 접속을 종료하엿습니다.");
				e1.printStackTrace();
			} // end catch
		}

		// 거래 확인 눌렀을때 해당 플래그를 매개변수로 메서드를 실행
		if (e.getSource() == cv.getJbtOk()) {
			selectDeal("P");
		}
		if (e.getSource() == cv.getJbtCancle()) {
			selectDeal("N");
		}

		// 상품디테일창 실행
		if (e.getSource() == cv.getJbtProductInfo()) {
			openDetail();
		}

	}// actionPerformed

}
