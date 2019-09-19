package chatTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import userVO.DCodeAndIdAO;
import userView.MarketMain;

public class ChattingView extends JFrame {
	private JLabel jlNotice,jlResult;
	private JTextArea jtaChatView;
	private JTextField jtaChatField;
	private JButton jbtProductInfo, jbtSend, jbtOk, jbtCancle;
	private JScrollPane jsp;
	
	
//	private AdminDAO aDAO;
	private String id;
//	private MarketMain mm;

	public ChattingView(String id,DCodeAndIdAO DIAO) {
		super("채팅");
		this.id = id;
	//	this.mm=mm;
		
		
		jlNotice = new JLabel("------");
		jlResult = new JLabel("------");
		
		
		jtaChatView = new JTextArea();
		jtaChatField = new JTextField();
		
		jsp = new JScrollPane(jtaChatView);
		
		JPanel jp = new JPanel();
		
		
		jbtProductInfo = new JButton("거래중인 상품 정보 보기");
		jbtSend = new JButton("보내기");
		jbtOk = new JButton("예");
		jbtCancle = new JButton("아니요");
		
		jp.add(jlNotice);
		jp.add(jbtOk);
		jp.add(jbtCancle);
		//수동배치 
		setLayout(null);
		//TextArea
		jsp.setBounds(20,60,345,380);
		jtaChatField.setBounds(20, 450, 260, 60);
		//JButton
		jbtProductInfo.setBounds(90, 5, 200, 50);
		jlResult.setBounds(90, 5, 200, 50);		
		jbtSend.setBounds(285, 450, 80, 60);
		jbtOk.setBounds(240, 520, 60, 25);
		jbtCancle.setBounds(300,520, 75, 25);
		//JLabel
		jlNotice.setBounds(35,520 , 250, 25);
		//배치
//	
		add(jbtProductInfo);
		add(jsp);
		add(jtaChatField);
		add(jbtSend);
		add(jlNotice);
		add(jbtOk);
		add(jbtCancle);
		//// 판매확인 메세지 숨기기
		jlNotice.setVisible(true);
		jbtOk.setVisible(false);
		jbtCancle.setVisible(false);
		
///////////	상단 상품정보 버튼 or 거래완료 삭제 메세지 
		jbtProductInfo.setVisible(false);
		jlResult.setVisible(true);
		add(jlResult);
		
//////////////////////
		//////////////테스트 
		
		
		//Window sizing
		setBounds(100, 100, 400, 600);
		jtaChatView.setEditable(false);
		//visible
		setVisible(true);
		//window closing
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		ChattingViewEvt cVE = new ChattingViewEvt(this, DIAO);
//		aDAO = AdminDAO.getInstance();
//		me = "shin";
//		you = "baek"; 
//		
//		
//		
//		OrderThread ot = new OrderThread(jtaChatView, me, you,jsp);
////		ot.setDaemon(true);
//		ot.start();
//		
		
		
		
		jbtSend.addActionListener(cVE);
		jbtSend.addActionListener(cVE);
		jtaChatField.addActionListener(cVE);
//		addWindowListener(new WindowAdapter() {
//
//			@Override
//			public void windowClosing(WindowEvent e) {
//
//				try {
//					close();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				} // end catch
//				dispose();
//			}// windowClosing
//
//		});// addWindowListener
//		

	
		

		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}// Chatting
	
//	private void scrollPosition() {
//		jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
//	}
//
//	
//	
//	
//	private void sendMsg() throws IOException {
//		// 스트림이 연결되어 있다면
//		String msg = jtaChatField.getText().trim();
//		
//		if(!msg.isEmpty()) {
//		jtaChatView.append(me+": "+msg+"\n");
//		System.out.println(msg);
//		try {
//			aDAO.sendChat(me, you, msg);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		jtaChatField.setText("");
//		scrollPosition();
//		System.out.println(msg);
//		}
//		
//		
//	}// sendMsg
//
//	
//
//	private void close() throws IOException {
//
//	}// close
//	
//	
//
////	public static void main(String[] args) {
////		new ChattingView();
////	}// main
//
//	
//	public void actionPerformed(ActionEvent e) {
//		
//		
//		
//		
//		if(e.getSource() == jbtSend || e.getSource() ==jtaChatField) {
//		try {
//			sendMsg();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			JOptionPane.showMessageDialog(this,"대화상대가 접속을 종료하엿습니다.");
//			e1.printStackTrace();
//		} // end catch
//		}
//
//	}// actionPerformed

	public JLabel getJlNotice() {
		return jlNotice;
	}

	public JTextArea getJtaChatView() {
		return jtaChatView;
	}

	public JTextField getJtaChatField() {
		return jtaChatField;
	}

	public JButton getJbtProductInfo() {
		return jbtProductInfo;
	}

	public JButton getJbtSend() {
		return jbtSend;
	}

	public JButton getJbtOk() {
		return jbtOk;
	}

	public JButton getJbtCancle() {
		return jbtCancle;
	}

	public JScrollPane getJsp() {
		return jsp;
	}

//	public AdminDAO getaDAO() {
//		return aDAO;
//	}
 
	public String getId() {
		return id;
	}




	
	
//	@Override
//	public void actionPerformed(ActionEvent e) {
////		if(e.getSource() == jbtSend) {
////		jlNotice.setVisible(true);
////		jbtOk.setVisible(true);
////		jbtCancle.setVisible(true);
////		System.out.println("d");
////		}
//	}

}// class

