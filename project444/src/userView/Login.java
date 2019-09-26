package userView;

import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import userControl.LoginEvt;;

public class Login extends JFrame {
	private JLabel jlLoginFail;
	private JTextField jtfId;
	private JPasswordField jpfPw;
	private JButton jbtLogin, jbtSignUp, jbtForgotId, jbtForgotPw;
	public Login() {
		super("�α���");
		// JLabel
		ImageIcon ii= new ImageIcon("C:/Users/owner/git/project444/project444/src/image/�ٳ�������.png");
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
		logo.setBounds(70,-20, 300, 225);
		// JLabel
		jlId.setBounds(60, 155, 20, 25);
		jlPw.setBounds(60, 185, 20, 25);
		// jtextField
		jtfId.setBounds(110, 155, 200, 30);
		jpfPw.setBounds(110, 185, 200, 30);
		// jbutton
		jbtLogin.setBounds(310, 155, 100, 60);
		jbtSignUp.setBounds(310, 217, 100, 30);
//		jbtForgotId.setBounds(270, 250, 78, 25);
//		jbtForgotPw.setBounds(348, 250, 78, 25);
		jbtForgotId.setBounds(100, 217, 50, 25);
		jbtForgotPw.setBounds(150, 217, 50, 25);
		jbtSignUp.setBounds(340, 217, 70, 25);
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
		c.setBackground(new Color(0xf6f2ef));
		// ������ ������
		setBounds(100, 100, 480, 330);
		setResizable(false);
		// �ӽ� �׽�Ʈ�� �̺�Ʈó��
		LoginEvt le = new LoginEvt(this);
		jbtSignUp.addActionListener(le);
		jbtForgotId.addActionListener(le);
		jbtForgotPw.addActionListener(le);
		jbtLogin.addActionListener(le);

		jtfId.addActionListener(le);
		jpfPw.addActionListener(le);
		
		jbtSignUp.setBackground(new Color(0xFFCC66));
		jbtLogin.setBackground(new Color(0xFFCC66));
//		jbtForgotId.setBackground(new Color(0xFFCC66));
//		jbtForgotPw.setBackground(new Color(0xFFCC66));
		jbtForgotId.setBackground(new Color(0xf6f2ef));
		jbtForgotPw.setBackground(new Color(0xf6f2ef));
		jbtSignUp.setBackground(new Color(0xf6f2ef));
		jbtForgotId.setBorder(new LineBorder(null,0));
		jbtForgotPw.setBorder(new LineBorder(null,0));
		jbtSignUp.setBorder(new LineBorder(null,0));
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
