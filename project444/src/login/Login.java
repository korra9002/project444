package login;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
	private JLabel jlId, jlPw, jlLoginFail;
	private JTextField jtfId;
	private JPasswordField jpfPw;
	private JButton jbtLogin, jbtSignUp, jbtForgotId, jbtForgotPw;

	public Login() {
		super("로그인");
		// JLabel
		jlId = new JLabel("ID");
		jlPw = new JLabel("PW");
		jlLoginFail = new JLabel("아이디 또는 비밀번호를 확인해주세요.");
		// JTextField
		jtfId = new JTextField();
		jpfPw = new JPasswordField();
		// Jbutton
		jbtLogin = new JButton("로그인");
		jbtSignUp = new JButton("회원가입");
		jbtForgotId = new JButton("ID찾기");
		jbtForgotPw = new JButton("Pw찾기");

		// 수동배치
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
		// 배치
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
		
		// 윈도우 사이즈
		setBounds(100, 100, 480, 330);

		// 임시 테스트용 이벤트처리
		jbtSignUp.addActionListener(this);
		jbtForgotId.addActionListener(this);
		jbtForgotPw.addActionListener(this);
		// 가시화
		setVisible(true);
		// 윈도우 종료처리
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// Login

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jbtSignUp) {
			new SignUp();
		} // end if
		if (ae.getSource() == jbtForgotId) {
			new ForgotId();
		} // end if
		if (ae.getSource() == jbtForgotPw) {
			new ForgotPw();
		} // end if

	}// actionPerformed //단위테스트용

	public static void main(String[] args) {
		new Login();
	}// main

}// class
