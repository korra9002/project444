package userView;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import userControl.LoginEvt;;

public class Login extends JFrame {
	private JLabel jlId, jlPw, jlLoginFail;
	private JTextField jtfId;
	private JPasswordField jpfPw;
	private JButton jbtLogin, jbtSignUp, jbtForgotId, jbtForgotPw;

	public Login() {
		super("�α���");
		// JLabel
		jlId = new JLabel("ID");
		jlPw = new JLabel("PW");
		jlLoginFail = new JLabel();
		// JTextField
		jtfId = new JTextField();
		jpfPw = new JPasswordField();
		// Jbutton
		jbtLogin = new JButton("�α���");
		jbtSignUp = new JButton("ȸ������");
		jbtForgotId = new JButton("IDã��");
		jbtForgotPw = new JButton("Pwã��");

		// ������ġ
		setLayout(null);
		// JLabel
		jlId.setBounds(60, 160, 20, 25);
		jlPw.setBounds(60, 190, 20, 25);
		jlLoginFail.setBounds(180, 140, 240, 25);
		// jtextField
		jtfId.setBounds(110, 160, 200, 30);
		jpfPw.setBounds(110, 190, 200, 30);
		// jbutton
		jbtLogin.setBounds(320, 160, 100, 55);
		jbtSignUp.setBounds(320, 220, 100, 25);
		jbtForgotId.setBounds(280, 250, 78, 25);
		jbtForgotPw.setBounds(360, 250, 78, 25);
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
		
		jlLoginFail.setForeground(Color.RED);
		
		// ������ ������
		setBounds(100, 100, 480, 330);

		// �ӽ� �׽�Ʈ�� �̺�Ʈó��
		LoginEvt le = new LoginEvt(this);
		jbtSignUp.addActionListener(le);
		jbtForgotId.addActionListener(le);
		jbtForgotPw.addActionListener(le);
		jbtLogin.addActionListener(le);
<<<<<<< HEAD
		jtfId.addActionListener(le);
		jpfPw.addActionListener(le);
=======
>>>>>>> branch 'master' of https://github.com/korra9002/project444.git
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
