package adminView;

import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
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
		super("������ �α���");
		//Logo
		ImageIcon ii= new ImageIcon("C:/Users/seoyy/git/project444/project444/src/image/�ٳ�������.png");
		JLabel logo = new JLabel(ii);
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
		//Logo
		logo.setBounds(70,-20, 300, 225);
		// JLabel
		jlId.setBounds(60, 160, 20, 25);
		jlPw.setBounds(60, 190, 20, 25);
		jlLoginFail.setBounds(120, 120, 240, 25);
		
		// jtextField
		jtfId.setBounds(110, 160, 200, 30);
		jpfPw.setBounds(110, 190, 200, 30);
		
		// jbutton
		jbtLogin.setBounds(310, 160, 100, 60);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//		jtfId.setText("admin");
//		jpfPw.setText("1234");
		///////////////////////////////////////////////////////////////���� �����//////////////////////////////////////////////////////////////////////////////////
		//�� ������
		Container c = getContentPane();
		c.setBackground(new Color(0xf6f2ef));
		jbtLogin.setBackground(new Color(0xFFCC66));
		
		// ��ġ
		add(jlId);
		add(jlPw);
		add(jlLoginFail);

		add(jtfId);
		add(jpfPw);

		add(jbtLogin);
		
		add(logo);
		
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
