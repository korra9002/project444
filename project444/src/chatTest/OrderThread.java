package chatTest;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

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
	private String me, you;
//	private StringBuilder stb;
	private JScrollPane jsp;

	public OrderThread(JTextArea jtaChatView, String me, String you, JScrollPane jsp) {
		this.jtaChatView = jtaChatView;
		this.me = me;
		this.you = you;
		this.jsp = jsp;
		// this.stb= stb;

	}

	public void run() {
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			List<ChatVO> list = null;
			Object[] rowData = null;
			ChatVO cv = null;

			boolean flag = false;
			while (true) {
				// 주문 목록을 조회

				list = aDAO.selectAllChat(me, you);

				for (int i = 0; i < list.size(); i++) {
					cv = list.get(i);

					jtaChatView.append(cv.getYou() + " : " + cv.getChat() + "\n");

				} // end for
				jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} // end catch
			} // end while
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

	}// run

}// class
