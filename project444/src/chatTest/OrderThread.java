package chatTest;


import java.sql.SQLException;
import java.util.List;


import javax.swing.JScrollPane;

import javax.swing.JTextArea;


import userDAO.UserDAO;
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
	
	
	

	public OrderThread(ChattingView cv, String me, String you, String dealCode) {
		jtaChatView = cv.getJtaChatView();
		jsp = cv.getJsp();
		this.me = me;
		this.you = you;
		this.dealCode = dealCode;
		// this.stb= stb;

	}

	public void run() {
		UserDAO uDAO = UserDAO.getInstance();
		FlagVO fVO = null;
		try {
			List<ChatVO> list = null;
//			Object[] rowData = null;
			ChatVO cv = null;
			
			// �ֹ� ����� ��ȸ

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
				
				fVO = uDAO.checkFlag(dealCode);
				fVO.getDealFLag();
				fVO.getAllFlag();
				
				
			} // end while
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// run

}// class
