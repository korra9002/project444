package login;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ForgotPw extends JFrame {
	private JTextField jtfId, jtfPwAnswer;
	private JComboBox<String> jcbPwHint;
	private JButton jbtSearch, jbtCancle;

	public ForgotPw() {
		JLabel jlId = new JLabel("���̵�");
		JLabel  jlPwHint= new JLabel("��й�ȣ ��Ʈ");
		JLabel jlPwAnswer= new JLabel("����");
		
		jtfId = new JTextField("���̵� �Է�");
		jtfPwAnswer = new JTextField("��Ʈ�� ���� �亯�� �ۼ�");

		jbtSearch = new JButton("ã��");
		jbtCancle = new JButton("���");

		jcbPwHint = new JComboBox<String>();
		// Password Hint ComboBox
		String[] PwHint = { "- ��й�ȣ ��Ʈ ���� -","���� �¿����?", "���� ���� �� 1ȣ��?", "���� �޴��� ��ȣ �� ���ڸ���?", "���� ��￡ ���ų� �����ϴ� ��Ҵ�?", "���� ģ�� ģ�� �̸���?",
				"���� �ް� ���� ������?", "���� �����ϴ� �뷡 ������?", "���� �����ϴ� ��������?", "���� �����ϴ� ������?", "���� �����ϴ� ���ڴ�?", "���� �����ϴ� ������?" };
		for (int i = 0; i < PwHint.length; i++) {
			jcbPwHint.addItem(PwHint[i]);
		} // end for
		
		setLayout(null);
		jlId.setBounds(40, 50, 60, 25);
		jlPwHint.setBounds(30, 90,100,25);
		jlPwAnswer.setBounds(50, 180, 50, 25);
		
		jtfId.setBounds(100, 50, 150, 25);
		jtfPwAnswer.setBounds(100, 180,350, 25);
		
		jbtSearch.setBounds(150, 250, 80, 30);
		jbtCancle.setBounds(250, 250, 80, 30);
		
		jcbPwHint.setBounds(100, 125, 300, 25);
		add(jlId);
		add(jlPwHint);
		add(jlPwAnswer);
		add(jtfId);
		add(jtfPwAnswer);
		add(jbtSearch);
		add(jbtCancle);
		add(jcbPwHint);
		setBounds(100, 100, 500, 350);
		setVisible(true);

		// �н����� ã�� â ���� ó��
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent e) {
				dispose();
			}

		});

	}// ForgetPw

//	public static void main(String[] args) {
//		new ForgotPw();
//	}// main

}// class
