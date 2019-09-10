package adminView;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AdminLogin extends JFrame {
	private JLabel jlLoginFail;
	private JTextField jtfId;
	private JPasswordField jpfPw;
	private JButton jbtLogin;

	public AdminLogin() {
		super("������ �α���");
		
		// JLabel
		JLabel jlId = new JLabel("ID");
		JLabel jlPw = new JLabel("PW");
		jlLoginFail = new JLabel("");
		
		// JTextField
		jtfId = new JTextField();
		jpfPw = new JPasswordField();
		
		// Jbutton
		jbtLogin = new JButton("�α���");

		// ������ġ
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
		
		// ��ġ
		add(jlId);
		add(jlPw);
		add(jlLoginFail);

		add(jtfId);
		add(jpfPw);

		add(jbtLogin);
		
		jlLoginFail.setForeground(Color.RED);
		
		AdminLoginEvt ale = new AdminLoginEvt(this);
		jbtLogin.addActionListener(ale);
		
		
		// ������ ������
		setBounds(100, 100, 480, 330);

		// ����ȭ
		setVisible(true);
		
		// ������ ����ó��
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