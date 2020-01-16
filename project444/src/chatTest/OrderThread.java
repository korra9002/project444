package chatTest;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JTextArea;
import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.FlagVO;

public class OrderThread extends Thread {
	private JTextArea jtaChatView;
	private String me, you, dealCode; // me = �ڽ��� ���̵� you = ������ ���̵� dealCode = DB���� ���� ä���� �����ϴ� ���� �ڵ�

	private String dealFlag; // �ŷ����� ��ǰ�� �������θ� Ȯ���ϴ� �÷���
	private String allFlag; // �ŷ����� ��ǰ�� �������θ� Ȯ���ϴ� �÷���
	private String userId;

	private ChattingView cv; //ä�ú� Ŭ���� 
	
	private FlagVO fVO; //�ŷ� ���� �÷��׿� ���̵� �����ϴ� Ŭ����
	
	//ä�ú� Ŭ������ �ŷ��ڵ�  ����� �ڽ��� ���̵� �������� �Ű������� �޴´�
	public OrderThread(ChattingView cv, String me, String you, String dealCode) {
		this.cv = cv;
		jtaChatView = cv.getJtaChatView();
		this.me = me;
		this.you = you;
		this.dealCode = dealCode;

	}
	
	//���� �ŷ����� ��ǰ�� ������ ������� �ش� �޼����� �����ְ� ������â ���� ��ư�� �������� ���´� 
	public void setUpInfo(String msg) {
		cv.getJbtProductInfo().setVisible(false);
		cv.getJlResult().setText(msg);
		cv.getJlResult().setVisible(true);
		cv.add(cv.getJlResult());
	}

	//�ŷ� Ȯ�� �޼����� �����ְ� �� �ƴϿ� ��ư�� �����ش�
	public void setDealInfo() {
		cv.getJlNotice().setText(you + "�Կ��� ��ǰ�� ���� �̳���?");
		cv.getJlNotice().setVisible(true);
		cv.getJbtOk().setVisible(true);
		cv.getJbtCancle().setVisible(true);
	}

	
	//�ŷ� ���¿� ���� �÷��׸� ��ȸ�ϴ� �޼���
	public void checkFlag() {
		UserDAO uDAO = UserDAO.getInstance();

		try {
			//DB���� �ش� ��ǰ�� ���������� ��ȸ
			fVO = uDAO.checkFlag(dealCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//��ǰ�� ���������� ������ �Ʒ� ������ �ǳ� �ڴ�
		if (dealFlag == fVO.getDealFLag() && allFlag == fVO.getAllFlag()) {
			return;
		}
		dealFlag = fVO.getDealFLag();
		allFlag = fVO.getAllFlag();
		userId = fVO.getId();

		//�÷��׿� ���� ��Ȳ�� �����Ͽ� �޼����� �����ش�
		switch (dealFlag) {
		case "N":
			switch (allFlag) {
			case "B":
				if (!userId.equals(RunMarketMain.userId)) {
					setUpInfo("�Ǹŵ� ��ǰ�Դϴ�.");
				} else {
					setUpInfo("�ٸ� �̿��ڿ� �ŷ� �Ϸ�� ��ǰ�Դϴ�.");
				}
				break;
			case "D":
				if (userId.equals(RunMarketMain.userId)) {
					setUpInfo("������ ��ǰ �Դϴ�.");
				} else {
					setUpInfo("������ ��ǰ �Դϴ�.");
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
					setUpInfo("������ ����Ȯ���� ��ٸ��� �ֽ��ϴ�.");
				}
				break;
			case "D":
				if (userId.equals(RunMarketMain.userId)) {
					setUpInfo("������ ��ǰ �Դϴ�.");
				} else {
					setUpInfo("������ ��ǰ �Դϴ�.");
				}
				break;

			}// end switch

			break;
		case "P":
			if (userId.equals(RunMarketMain.userId)) {

				cv.getJlNotice().setVisible(false);
				cv.getJbtOk().setVisible(false);
				cv.getJbtCancle().setVisible(false);
				setUpInfo("������ ��ǰ �Դϴ�.");
			} else {
				setUpInfo("�ŷ��Ϸ�� ��ǰ �Դϴ�.");
			}
			break;

		}// end switch
	}

	public void run() {
		UserDAO uDAO = UserDAO.getInstance();
		try {
			List<ChatVO> list = null;
			ChatVO cv = null;

			///// ä�� ���۽� �÷���üũ

			checkFlag();
			//ä��â�� ó�� �������� ��� �ش�ŷ��� ä�ó����� ����Ʈ�� ����
			list = uDAO.selectAllChat(me, you, dealCode);
			
			//����Ʈ�� ����� ä�ó������ ȭ�鿡 ����
			for (int i = 0; i < list.size(); i++) {
				cv = list.get(i);

				jtaChatView.append(cv.getYou() + " : " + cv.getChat() + "\n");

			} // end for
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} // end catch

			//��ũ���� �� �Ʒ��� ����
			jtaChatView.setCaretPosition(jtaChatView.getDocument().getLength());
			
			list = null;

			//ä�ó����� DB���� 0.5�ʸ��� ��ȸ�Ͽ� �߰� �޼����� ������ �����ش�
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
