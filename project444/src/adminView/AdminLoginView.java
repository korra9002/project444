package adminView;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import adminControl.AdminLoginEvt;


@SuppressWarnings("serial")
public class AdminLoginView extends JFrame {
	private JLabel jlLoginFail;
	private JTextField jtfId;
	private JPasswordField jpfPw;
	private JButton jbtLogin;
	public static String imgPath = "c:/dev/adminRecieveFile";
	
	public AdminLoginView() {
		super("관리자 로그인");
		
		// JLabel
		JLabel jlId = new JLabel("ID");
		JLabel jlPw = new JLabel("PW");
		jlLoginFail = new JLabel("");
		
		// JTextField
		jtfId = new JTextField();
		jpfPw = new JPasswordField();
		
		// Jbutton
		jbtLogin = new JButton("로그인");

		// 수동배치
		setLayout(null);
		
		// JLabel
		jlId.setBounds(60, 160, 20, 25);
		jlPw.setBounds(60, 190, 20, 25);
		jlLoginFail.setBounds(120, 120, 240, 25);
		
		// jtextField
		jtfId.setBounds(110, 160, 200, 30);
		jpfPw.setBounds(110, 190, 200, 30);
		
		// jbutton
		jbtLogin.setBounds(320, 160, 100, 55);
		
		// 배치
		add(jlId);
		add(jlPw);
		add(jlLoginFail);

		add(jtfId);
		add(jpfPw);

		add(jbtLogin);
		
		jlLoginFail.setForeground(Color.RED);
		
		AdminLoginEvt ale = new AdminLoginEvt(this);
		jbtLogin.addActionListener(ale);
		
		
		// 윈도우 사이즈
		setBounds(100, 100, 480, 330);

		// 가시화
		setVisible(true);
		
		// 윈도우 종료처리
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
	}// Login
	
	public JLabel getJlLoginFail() {
		return jlLoginFail;
	}

	public JTextField getJtfId() {
		return jtfId;
	}

	public JPasswordField getJpfPw() {
		return jpfPw;
	}

	public JButton getJbtLogin() {
		return jbtLogin;
	}

}// class
