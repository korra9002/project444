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
import javax.swing.text.JTextComponent;

import userDAO.UserDAO;
import userVO.DCodeAndIdAO;
import userVO.MarketDetailVO;
import userView.MarketDetailBuyer;
import userView.MarketDetailSeller;

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
//		JTextComponent txtLog;
//		\cv.getJtaChatView()
//		cv.getJtaChatView().setCaretPosition(txtLog.getDocument().getLength());

 
	}

	private void sendMsg() throws IOException {
		// ��Ʈ���� ����Ǿ� �ִٸ�
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

///////ä��â���� ���� Ȯ�� �� �ƴϿ� ������ ��
	private void selectDeal(String flag) {
		UserDAO uDAO = UserDAO.getInstance();
		int result =0;
		try {
			result =uDAO.selectDeal(flag, dealCode);
		
			JOptionPane.showMessageDialog(cv, "ó���Ϸ�");
			cv.getJlNotice().setVisible(false);
			cv.getJbtOk().setVisible(false);
			cv.getJbtCancle().setVisible(false);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(cv, "���� �߻�");
			e.printStackTrace();
		}
	}//selectDeal
	
	private void openDetail() {
		UserDAO uDAO = UserDAO.getInstance();
		String productCode="";
		try {
			productCode= uDAO.getProCode(dealCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String classFlag = "M"; // userDAO���� selectProDetail method ����� �� ���п� �÷���

		try {
			MarketDetailVO mdVO = uDAO.selectProDetail(productCode, classFlag);
			if (mdVO != null) {//// ������ â�� ���� ���� ��ȸ�ÿ� �ش� ������ �÷��װ�
				// �ǸŵǴ� ���� ������ ����Ǿ� ��ȸ�� ���� ������ �޼����� �������� if��
				// ���� ������ ���̵�� ������ �Ǹ��� ���̵�� ������ MarketDetailBuyer
				// �ٸ��ٸ� MarketDetailSeller
				System.out.println(me + "/" + mdVO.getSellerID());
				if (mdVO.getSellerID().equals(me)) {
					new MarketDetailSeller(null, mdVO, me);
				} else {
					new MarketDetailBuyer(null, mdVO, me, null);
				} // end else

			} else {
				JOptionPane.showMessageDialog(cv, "�Ǹ����� ��ǰ�� �ƴմϴ�.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
		
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == cv.getJtaChatField() || e.getSource() == cv.getJbtSend()) {
			try {
				sendMsg();
				System.out.println("�޼��� ����");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(cv, "��ȭ��밡 ������ �����Ͽ����ϴ�.");
				e1.printStackTrace();
			} // end catch
		}
		
		if(e.getSource() == cv.getJbtOk()) {
			selectDeal("P");
		}
		if(e.getSource() == cv.getJbtCancle()) {
			selectDeal("N");
		}
		
		if(e.getSource() == cv.getJbtProductInfo()) {
			openDetail();
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
