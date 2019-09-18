package chatTest;


import java.sql.SQLException;
import java.util.List;


import javax.swing.JScrollPane;

import javax.swing.JTextArea;


import userDAO.UserDAO;

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
	

	public OrderThread(JTextArea jtaChatView, String me, String you, JScrollPane jsp, String dealCode) {
		this.jtaChatView = jtaChatView;
		this.me = me;
		this.you = you;
		this.jsp = jsp;
		this.dealCode = dealCode;
		// this.stb= stb;

	}

	public void run() {
		UserDAO uDAO = UserDAO.getInstance();
		try {
			List<ChatVO> list = null;
//			Object[] rowData = null;
			ChatVO cv = null;
			
			// 주문 목록을 조회

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
				// 주문 목록을 조회

				list = uDAO.selectChat(me, you, dealCode);

				for (int i = 0; i < list.size(); i++) {
					cv = list.get(i);
					System.out.println(cv.getYou() + " : " + cv.getChat() + "\n");
					jtaChatView.append(cv.getYou() + " : " + cv.getChat() + "\n");

					jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
				} // end for
				list = null;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} // end catch
			} // end while
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// run

}// class
