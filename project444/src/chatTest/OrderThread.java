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
		cv.getJlNotice().setText(you+"�Կ��� ��ǰ�� ���� �̳���?");
		cv.getJlNotice().setVisible(true);
		cv.getJbtOk().setVisible(true);
		cv.getJbtCancle().setVisible(true);
	}
	
	public void checkFlag(UserDAO uDAO) {
		FlagVO fVO = null;

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
				if (userId.equals(RunMarketMain.userId)) {
					setUpInfo("�ٸ� �̿��ڿ��� �Ǹŵ� ��ǰ�Դϴ�.");
				}else {
					setUpInfo("�ٸ� �̿��ڿ��� �Ǹ��� ��ǰ�Դϴ�.");
				}
				break;
			case "D":
				if (userId.equals(RunMarketMain.userId)) {
					setUpInfo("������ ��ǰ �Դϴ�.");
				}else {
					setUpInfo("���� ������ ��ǰ �Դϴ�.");
				}
				break;
			}// end switch
			break;
		case "Y":
			switch (allFlag) {
			case "P":
				if (userId.equals(RunMarketMain.userId)) {
					setDealInfo();
				}else {
					setUpInfo("������ ����Ȯ���� ��ٸ��� �ֽ��ϴ�.");
				}
				break;
			case "D":
				if (userId.equals(RunMarketMain.userId)) {
					
					setUpInfo("������ ��ǰ �Դϴ�.");
				}else {
					setUpInfo("���� ������ ��ǰ �Դϴ�.");
				}
				break;

			}// end switch

			break;
		case "P":
			if (userId.equals(RunMarketMain.userId)) {
				
				setUpInfo("���� ������ ��ǰ �Դϴ�.");
			}else {
				setUpInfo("���� �ǸſϷ��� ��ǰ �Դϴ�.");
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

			///// ä�� ���۽� �÷���üũ
	
			checkFlag(uDAO);
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

			jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
			list = null;

//			boolean flag = false;
			while (true) {
				// �ֹ� ����� ��ȸ

				list = uDAO.selectChat(me, you, dealCode);

				for (int i = 0; i < list.size(); i++) {
					cv = list.get(i);
					System.out.println(cv.getYou() + " : " + cv.getChat() + "\n");
					jtaChatView.append(cv.getYou() + " : " + cv.getChat() + "\n");

					jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
				} // end for
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} // end catch

			checkFlag(uDAO);

			} // end while
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// run

}// class
