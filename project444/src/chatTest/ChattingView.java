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

public class ChattingView extends JFrame implements ActionListener{
	private JLabel jlNotice;
	private JTextArea jtaChatView;
	private JTextField jtaChatField;
	private JButton jbtProductInfo, jbtSend, jbtOk, jbtCancle;
	
	private JScrollPane jsp;
	
	
	private AdminDAO aDAO;
	private String me, you;
	
	

	public ChattingView() {
		super("ä��");
		jlNotice = new JLabel("------");
		jtaChatView = new JTextArea();
		jtaChatField = new JTextField();
		
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
		jtaChatView.setBounds(20,60,345,380);
		jtaChatField.setBounds(20, 450, 260, 60);
		//JButton
		jbtProductInfo.setBounds(90, 5, 200, 50);
		jbtSend.setBounds(285, 450, 80, 60);
		jbtOk.setBounds(240, 520, 60, 25);
		jbtCancle.setBounds(300,520, 75, 25);
		//JLabel
		jlNotice.setBounds(35,520 , 250, 25);
		//��ġ
		add(jbtProductInfo);
		add(jtaChatView);
		add(jtaChatField);
		add(jbtSend);
		add(jlNotice);
		add(jbtOk);
		add(jbtCancle);
		
		//// �Ǹ�Ȯ�� �޼��� �����
		jlNotice.setVisible(false);
		jbtOk.setVisible(false);
		jbtCancle.setVisible(false);
		
		//////////////�׽�Ʈ 
		jbtSend.addActionListener(this);
		
		
		//Window sizing
		setBounds(100, 100, 400, 600);
		jtaChatView.setEditable(false);
		//visible
		setVisible(true);
		//window closing
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		aDAO = AdminDAO.getInstance();
		me = "shin";
		you = "baek";
		
		
		
		OrderThread ot = new OrderThread(jtaChatView, me, you,jsp);
//		ot.setDaemon(true);
		ot.start();
		
		
		jbtSend.addActionListener(this);
		jtaChatField.addActionListener(this);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

				try {
					close();
				} catch (IOException e1) {
					e1.printStackTrace();
				} // end catch
				dispose();
			}// windowClosing

		});// addWindowListener
		

	
		

		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}// Chatting
	
	private void scrollPosition() {
		jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
	}

	
	
	
	private void sendMsg() throws IOException {
		// ��Ʈ���� ����Ǿ� �ִٸ�
		String msg = jtaChatField.getText().trim();
		if(!msg.isEmpty()) {
		jtaChatView.append(me+": "+msg+"\n");
		System.out.println(msg);
		try {
			aDAO.sendChat(me, you, msg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jtaChatField.setText("");
		scrollPosition();
		System.out.println(msg);
		}
		
		
	}// sendMsg

	

	private void close() throws IOException {

	}// close
	
	

	public static void main(String[] args) {
		new ChattingView();
	}// main

	
	public void actionPerformed(ActionEvent e) {
		
		
		
		
		if(e.getSource() == jbtSend || e.getSource() ==jtaChatField) {
		try {
			sendMsg();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this,"��ȭ��밡 ������ �����Ͽ����ϴ�.");
			e1.printStackTrace();
		} // end catch
		}

	}// actionPerformed
	
	
	
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
