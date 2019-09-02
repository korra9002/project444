package userView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ForgotId extends JFrame {
	private JTextField jtfPhone, jtfName;
	private JButton jbtSearch, jbtCancle;
	private JComboBox<String> jcbPhoneNum;
	public ForgotId() {
		super("���̵�ã��");
		JLabel jlPhone = new JLabel("��ȭ��ȣ");
		JLabel jlName = new JLabel("�̸�");
		jtfPhone = new JTextField();
		jtfName = new JTextField();

		jbtSearch = new JButton("ã��");
		jbtCancle = new JButton("���");
		
		jcbPhoneNum = new JComboBox<String>();
		
		String[] phoneNum = {"010","011","012","016","017","018"};
		for (int i = 0; i < phoneNum.length; i++) {
			jcbPhoneNum.addItem(phoneNum[i]);
		} // end for
		
		setLayout(null);

		jlPhone.setBounds(30, 50, 60, 25);
		jlName.setBounds(30, 100, 60, 25);
		jtfPhone.setBounds(160, 50,160, 25);
		jtfName.setBounds(100, 100, 220, 25);
		jbtSearch.setBounds(110, 150, 70, 28);
		jbtCancle.setBounds(200, 150, 70, 28);
		
		jcbPhoneNum.setBounds(100, 50,55, 25);
		setBounds(100, 100, 380, 250);

		add(jlPhone);
		add(jlName);
		add(jtfPhone);
		add(jtfName);
		add(jbtSearch);
		add(jbtCancle);
		add(jcbPhoneNum);
		setVisible(true);

		//���̵� ã�� â ����ó��
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent e) {
				dispose();
			}

		});
	}// ForgotIdPw

//	public static void main(String[] args) {
//		new ForgotId();
//
//	}// main

}// class