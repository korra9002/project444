package chatTest;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
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
	private JTextArea jtaDisplay;
	private String me,you;
//	private StringBuilder stb;
	
	
	public OrderThread(JTextArea jtaDisplay,String me, String you) {
		this.jtaDisplay = jtaDisplay;
		this.me = me;
		this.you = you;
	//	this.stb= stb;
		
	}
	
	public void run() {
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			List<ChatVO> list = null;
			Object[] rowData = null;
			ChatVO cv = null;
//			StringBuilder chat = new StringBuilder(stb);

//			DataDecrypt dd = null;
//			try {
//				dd = new DataDecrypt("1111111111111111");
//			} catch (UnsupportedEncodingException e1) {
//				e1.printStackTrace();
//			} // end catch
			boolean flag = false;
			while (true) {
				// 주문 목록을 조회

				list = aDAO.selectAllChat(me, you);

//				if (!flag) {
//
//					if (list.isEmpty()) {
//						JOptionPane.showMessageDialog(jtOrder, "주문내역이 존재하지 않습니다. 홍보가 시급합니다");
//					} // end if
//					flag = true;
//				} // end if
					// 데이터를 채우기 전에 D.T.M을 초기화
//				dtm.setRowCount(0);
				// 테이블에 출력
				for (int i = 0; i < list.size(); i++) {
					cv = list.get(i);
					jtaDisplay.append(cv.getYou()+" : "+cv.getChat()+"\n");
					
					// 조회된 데이터를 D.T.M에 설정하기 위해 배열에 할당
//					rowData = new Object[9];
//					rowData[0] = new Integer(ov.getOrderNum());
//					rowData[1] = ov.getLunchName();
//					rowData[2] = ov.getLunchCode();
//					rowData[3] = ov.getOrderName();
//					rowData[4] = new Integer(ov.getQuantity());
//					try {
//						rowData[5] = dd.decryption(ov.getPhone());
//					} catch (NoSuchAlgorithmException e) {
//						e.printStackTrace();
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					} catch (GeneralSecurityException e) {
//						e.printStackTrace();
//					} // end catch
//					rowData[6] = ov.getIpAddr();
//					rowData[7] = ov.getOrderTime();
//					rowData[8] = ov.getStatus();

					// D.T.M에 데이터 채우기
//					dtm.addRow(rowData);

				} // end for
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} // end catch
			} // end while
		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(jtOrder, "DBMS에서 문제발생");
			e.printStackTrace();
		} // end catch

	}// run

}// class
