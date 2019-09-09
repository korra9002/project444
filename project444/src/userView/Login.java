package userView;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
		super("�α���");
		// JLabel
		jlId = new JLabel("ID");
		jlPw = new JLabel("PW");
		jlLoginFail = new JLabel("���̵� �Ǵ� ��й�ȣ�� Ȯ�����ּ���.");
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
		jbtSignUp.addActionListener(this);
		jbtForgotId.addActionListener(this);
		jbtForgotPw.addActionListener(this);
		jbtLogin.addActionListener(this);
		// ����ȭ
		setVisible(true);
		// ������ ����ó��
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
		if(ae.getSource()==jbtLogin) {
			try {
				new MarketMain();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}// actionPerformed //�����׽�Ʈ��

	public static void main(String[] args) {
		new Login();
	}// main

}// class
