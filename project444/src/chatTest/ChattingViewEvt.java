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

// ä��â�� ����Ǿ��� �� �̺�Ʈ�� ó���ϴ� Ŭ����  
public class ChattingViewEvt extends MouseAdapter implements ActionListener {
	private ChattingView cv;
	private UserDAO uDAO; // DB��ȸ�� ���� Ŭ����
	private String me, you, dealCode; // me = �ڽ��� ���̵� you = ������ ���̵� dealCode = DB���� ���� ä���� �����ϴ� ���� �ڵ�
	private DCodeAndIdAO DIAO; // dealCode�� ������ ���̵� �����ϴ� Ŭ����

	public ChattingViewEvt(ChattingView cv, DCodeAndIdAO DIAO) {
		super();
		this.cv = cv;
		this.DIAO = DIAO;

		uDAO = UserDAO.getInstance();
		me = cv.getId();
		you = DIAO.getId();
		dealCode = DIAO.getDealCode();

		// ä��â�� ���� �� �� �ش� ä�ó����� DB���� �о���� �����带 ����
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

	// ä��â�� ��ũ���� �� �Ʒ��� �ű�� �޼���
	private void scrollPosition() {
		JTextArea jta = cv.getJtaChatView();
		jta.setCaretPosition(jta.getDocument().getLength());

	}

	// �޼����� ������ �޼���
	private void sendMsg() throws IOException {
		String msg = cv.getJtaChatField().getText().trim();

		if (!msg.isEmpty()) {
			cv.getJtaChatView().append(me + ": " + msg + "\n");
			try {
				// ���̵�� �޼��� �ŷ��ڵ带 ��� DB�� �߰�
				uDAO.sendChat(me, you, msg, dealCode);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cv.getJtaChatField().setText("");
			scrollPosition();
		}

	}// sendMsg

	// ä��â���� ���� Ȯ�� �� �ƴϿ� ������ ��
	private void selectDeal(String flag) {
		UserDAO uDAO = UserDAO.getInstance();
		int result = 0;
		try {
			// ����Ȯ�ο��� �� �ƴϿ� ���� ����� DB�� ������Ʈ
			result = uDAO.selectDeal(flag, dealCode);

			JOptionPane.showMessageDialog(cv, "ó���Ϸ�");
			cv.getJlNotice().setVisible(false);
			cv.getJbtOk().setVisible(false);
			cv.getJbtCancle().setVisible(false);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(cv, "���� �߻�");
			e.printStackTrace();
		}
	}// selectDeal

	// ���� �ŷ����� ��ǰ �������� �����ϴ� �޼���
	private void openDetail() {
		UserDAO uDAO = UserDAO.getInstance();
		String productCode = "";
		try {
			productCode = uDAO.getProCode(dealCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String classFlag = "M"; // userDAO���� selectProDetail method ����� �� ���п� �÷���

		try {
			MarketDetailVO mdVO = uDAO.selectProDetail(productCode, classFlag);
			if (mdVO != null) {
				//// ������ â�� ���� ���� ��ȸ�ÿ� �ش� ������ �÷��װ�
				// �ǸŵǴ� ���� ������ ����Ǿ� ��ȸ�� ���� ������ �޼����� �������� if��
				// ���� ������ ���̵�� ������ �Ǹ��� ���̵�� ������ MarketDetailBuyer
				// �ٸ��ٸ� MarketDetailSeller
				if (mdVO.getSellerID().equals(me)) {
					new MarketDetailSeller(null, mdVO, me, null);
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

		// ä��â���� ���͸� �����ų� ���۹�ư ������ ��
		if (e.getSource() == cv.getJtaChatField() || e.getSource() == cv.getJbtSend()) {
			try {
				sendMsg();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(cv, "��ȭ��밡 ������ �����Ͽ����ϴ�.");
				e1.printStackTrace();
			} // end catch
		}

		// �ŷ� Ȯ�� �������� �ش� �÷��׸� �Ű������� �޼��带 ����
		if (e.getSource() == cv.getJbtOk()) {
			selectDeal("P");
		}
		if (e.getSource() == cv.getJbtCancle()) {
			selectDeal("N");
		}

		// ��ǰ������â ����
		if (e.getSource() == cv.getJbtProductInfo()) {
			openDetail();
		}

	}// actionPerformed

}
