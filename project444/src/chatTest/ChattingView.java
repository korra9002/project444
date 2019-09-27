package chatTest;

import java.awt.Color;
import java.awt.Container;
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
import javax.swing.plaf.basic.BasicScrollBarUI;

import adminView.ScrollBarUI;
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
		super("ä��");
		this.id = id;
	//	this.mm=mm;
		
		jlResult = new JLabel();
		
		jlNotice = new JLabel();
	
		
		
		jtaChatView = new JTextArea();
		jtaChatField = new JTextField();
		jtaChatView.setLineWrap(true);
		jsp = new JScrollPane(jtaChatView);
		
		JPanel jp = new JPanel();
		
		
		jbtProductInfo = new JButton("�ŷ����� ��ǰ ���� ����");
		jbtSend = new JButton("������");
		jbtOk = new JButton("��");
		jbtCancle = new JButton("�ƴϿ�");
		
		jp.add(jlNotice);
		jp.add(jbtOk);
		jp.add(jbtCancle);
		//������ġ 
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
		
		Container c = getContentPane();
		c.setBackground(new Color(0xf6f2ef));
		jbtCancle.setBackground(new Color(0xFFCC66));
		jbtOk.setBackground(new Color(0xFFCC66));
		jbtProductInfo.setBackground(new Color(0xFFCC66));
		jbtSend.setBackground(new Color(0xFFCC66));
		
		jsp.getVerticalScrollBar().setBackground(new Color(0xFFFFFF));
		jsp.getVerticalScrollBar().setUI(new ScrollBarUI());
		
		
		//��ġ
//	
		add(jbtProductInfo);
		add(jsp);
		add(jtaChatField);
		add(jbtSend);
		add(jlNotice);
		add(jbtOk);
		add(jbtCancle);
		//// �Ǹ�Ȯ�� �޼��� �����
		jlNotice.setVisible(true);
		jbtOk.setVisible(false);
		jbtCancle.setVisible(false);
		
//		jbtProductInfo.setVisible(false);
///////////	��� ��ǰ���� ��ư or �ŷ��Ϸ� ���� �޼��� 
//		jlResult.setVisible(true);
//		add(jlResult);
		 
//////////////////////
		//////////////�׽�Ʈ 
		
		
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
		
		
		jbtProductInfo.addActionListener(cVE);
		jbtSend.addActionListener(cVE);
		jtaChatField.addActionListener(cVE);
		jbtOk.addActionListener(cVE);
		jbtCancle.addActionListener(cVE);
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
//		// ��Ʈ���� ����Ǿ� �ִٸ�
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
//			JOptionPane.showMessageDialog(this,"��ȭ��밡 ������ �����Ͽ����ϴ�.");
//			e1.printStackTrace();
//		} // end catch
//		}
//
//	}// actionPerformed

	public JLabel getJlNotice() {
		return jlNotice;
	}

	public JLabel getJlResult() {
		return jlResult;
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

