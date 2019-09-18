package chatTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import userDAO.UserDAO;
import userVO.DCodeAndIdAO;

public class ChattingViewEvt extends MouseAdapter implements ActionListener {
	private ChattingView cv;
	private UserDAO uDAO;
	private String me, you, dealCode;
	private DCodeAndIdAO DIAO;

	public ChattingViewEvt(ChattingView cv, DCodeAndIdAO DIAO) {
		super();
		this.cv = cv;
		this.DIAO = DIAO;

		uDAO = UserDAO.getInstance();
		me = cv.getId();
		you = DIAO.getId();
		dealCode = DIAO.getDealCode();

		OrderThread ot = new OrderThread(cv, me, you, dealCode);
//		ot.setDaemon(true);
		ot.start();

		cv.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

				try {
					close();
					ot.stop();
				} catch (IOException e1) {
					e1.printStackTrace();
				} // end catch
				cv.dispose();
			}// windowClosing

		});// addWindowListener

	}

	private void scrollPosition() {
		cv.getJsp().getVerticalScrollBar().setValue(cv.getJsp().getVerticalScrollBar().getMaximum());
	}

	private void sendMsg() throws IOException {
		// 스트림이 연결되어 있다면
		String msg = cv.getJtaChatField().getText().trim();

		if (!msg.isEmpty()) {
		cv.getJtaChatView().append(me+": "+msg+"\n");
			System.out.println(msg);
			try {
				uDAO.sendChat(me, you, msg, dealCode);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cv.getJtaChatField().setText("");
		scrollPosition();
			System.out.println(msg);
		}

	}// sendMsg

	private void close() throws IOException {

	}// close

//	public static void main(String[] args) {
//		new ChattingView();
//	}// main

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == cv.getJtaChatField() || e.getSource() == cv.getJbtSend()) {
			try {
				sendMsg();
				System.out.println("메세지 보냄");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(cv, "대화상대가 접속을 종료하엿습니다.");
				e1.printStackTrace();
			} // end catch
		}

	}// actionPerformed

//	@Override
//	public void mouseClicked(MouseEvent e) {
//		if (e.getClickCount() == 1) {
//			if (e.getSource() == cv.getJbtSend()) {
//				try {
//					sendMsg();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		}

//	}// mouseClicked

}
