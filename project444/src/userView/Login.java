package userView;

import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import userControl.LoginEvt;;

public class Login extends JFrame {
	private JLabel jlLoginFail;
	private JTextField jtfId;
	private JPasswordField jpfPw;
	private JButton jbtLogin, jbtSignUp, jbtForgotId, jbtForgotPw;
	public Login() {
		super("�α���");
		// JLabel
		ImageIcon ii= new ImageIcon("C:/Users/seoyy/git/project444/project444/src/image/�ٳ�������.png");
		JLabel logo = new JLabel(ii);
		JLabel jlId = new JLabel("ID");
		JLabel jlPw = new JLabel("PW");
		jlLoginFail = new JLabel();
		// JTextField
		jtfId = new JTextField("baek");
		jpfPw = new JPasswordField("1234");
		// Jbutton
		jbtLogin = new JButton("�α���");
		jbtSignUp = new JButton("ȸ������");
		jbtForgotId = new JButton("IDã��");
		jbtForgotPw = new JButton("Pwã��");

		// ������ġ
		setLayout(null);
		// �ΰ�
		logo.setBounds(50,50, 300, 225);
		// JLabel
		jlId.setBounds(30, 310, 20, 25);
		jlPw.setBounds(30, 340, 20, 25);
		// jtextField
		jtfId.setBounds(80, 310, 200, 30);
		jpfPw.setBounds(80, 340, 200, 30);
		// jbutton
		jbtLogin.setBounds(290, 310, 100, 55);
		jbtSignUp.setBounds(290, 370, 100, 25);
		jbtForgotId.setBounds(250, 400, 78, 25);
		jbtForgotPw.setBounds(330, 400, 78, 25);
//		// �ΰ�
//		logo.setBounds(70,-20, 300, 225);
//		// JLabel
//		jlId.setBounds(60, 160, 20, 25);
//		jlPw.setBounds(60, 190, 20, 25);
//		// jtextField
//		jtfId.setBounds(110, 160, 200, 30);
//		jpfPw.setBounds(110, 190, 200, 30);
//		// jbutton
//		jbtLogin.setBounds(320, 160, 100, 55);
//		jbtSignUp.setBounds(320, 220, 100, 25);
//		jbtForgotId.setBounds(280, 250, 78, 25);
//		jbtForgotPw.setBounds(360, 250, 78, 25);
		// ��ġ
		add(jlId);
		add(jlPw);
		add(jlLoginFail);

		add(jtfId);
		add(jpfPw);

		add(jbtLogin);
		add(jbtSignUp);
		add(jbtForgotId);
		add(jbtForgotPw);
		
		add(logo);
		
		jlLoginFail.setForeground(Color.RED);
		Container c = getContentPane();
//		c.setBackground(Color.white);
		c.setBackground(new Color(0xf6f2ef));
		// ������ ������
//		setBounds(100, 100, 480, 330);
		setBounds(100, 100, 450, 500);
		setBackground(Color.WHITE);
		// �ӽ� �׽�Ʈ�� �̺�Ʈó��
		LoginEvt le = new LoginEvt(this);
		jbtSignUp.addActionListener(le);
		jbtForgotId.addActionListener(le);
		jbtForgotPw.addActionListener(le);
		jbtLogin.addActionListener(le);

		jtfId.addActionListener(le);
		jpfPw.addActionListener(le);
		
		jbtSignUp.setBackground(new Color(0xFFCC66));
		jbtForgotId.setBackground(new Color(0xFFCC66));
		jbtLogin.setBackground(new Color(0xFFCC66));
		jbtForgotPw.setBackground(new Color(0xFFCC66));

		// ����ȭ
		setVisible(true);
		// ������ ����ó��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// Login

	public JButton getJbtLogin() {
		return jbtLogin;
	}//getJbtLogin

	public JButton getJbtSignUp() {
		return jbtSignUp;
	}//getJbtSignUp

	public JButton getJbtForgotId() {
		return jbtForgotId;
	}//getJbtForgotId

	public JButton getJbtForgotPw() {
		return jbtForgotPw;
	}//getJbtForgotPw

	public JTextField getJtfId() {
		return jtfId;
	}//getJtfId

	public JPasswordField getJpfPw() {
		return jpfPw;
	}//getJpfPw

	public JLabel getJlLoginFail() {
		return jlLoginFail;
	}//getJlLoginFail


}// class
