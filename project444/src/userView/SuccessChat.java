package userView;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SuccessChat extends JFrame {
//	private JLabel jlNotice;
	private JTextArea jtaChatView;
	private JTextField jtaChatField;
	private JButton jbtSend;
//	jbtOk, jbtCancle;

	public SuccessChat() {
		super("�ŷ� �Ϸ� ä��");
		JLabel jlSuccessNotice = new JLabel("�ŷ��Ϸ�� ä���Դϴ�.");
//		jlNotice = new JLabel("��±� �Կ��� ������ �����̳���?");
		jtaChatView = new JTextArea();
		jtaChatField = new JTextField();
		
		jbtSend = new JButton("������");
//		jbtOk = new JButton("��");
//		jbtCancle = new JButton("�ƴϿ�");
		
		//������ġ 
		setLayout(null);
		//TextArea
		jtaChatView.setBounds(20,60,345,380);
		jtaChatField.setBounds(20, 450, 260, 60);
		//JButton
		jbtSend.setBounds(285, 450, 80, 60);
//		jbtOk.setBounds(240, 520, 60, 25);
//		jbtCancle.setBounds(300,520, 75, 25);
		//JLabel
//		jlNotice.setBounds(35,520 , 250, 25);
		jlSuccessNotice.setBounds(60, 10, 300, 50);
		//��ġ
		add(jtaChatView);
		add(jtaChatField);
		add(jbtSend);
//		add(jlNotice);
//		add(jbtOk);
//		add(jbtCancle);
		add(jlSuccessNotice);
		
		jlSuccessNotice.setFont(new Font("",Font.BOLD,25));
		//Window sizing
		setBounds(100, 100, 400, 600);
		jtaChatView.setEditable(false);
		//visible
		setVisible(true);
		//window closing
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// Chatting

	public static void main(String[] args) {
		new SuccessChat();
	}// main

}// class
