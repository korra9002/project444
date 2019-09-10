package userView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import userControl.ForgotPwEvt;

public class ForgotPw extends JDialog {
	private JTextField jtfId, jtfPwAnswer;
	private DefaultComboBoxModel<String> dcbPwHint;
	private JComboBox<String> jcbPwHint;
	private JButton jbtSearch, jbtCancle;

	public ForgotPw(Login lo) {
		super(lo,"��й�ȣ ã��");
		JLabel jlId = new JLabel("���̵�");
		JLabel  jlPwHint= new JLabel("��й�ȣ ��Ʈ");
		JLabel jlPwAnswer= new JLabel("����");
		
		jtfId = new JTextField();
		jtfPwAnswer = new JTextField();

		jbtSearch = new JButton("ã��");
		jbtCancle = new JButton("���");

		// Password Hint ComboBox
		String[] PwHint = { "- ��й�ȣ ��Ʈ ���� -","���� �¿����?", "���� ���� �� 1ȣ��?", "���� �޴��� ��ȣ �� ���ڸ���?", "���� ��￡ ���ų� �����ϴ� ��Ҵ�?", "���� ģ�� ģ�� �̸���?",
				"���� �ް� ���� ������?", "���� �����ϴ� �뷡 ������?", "���� �����ϴ� ��������?", "���� �����ϴ� ������?", "���� �����ϴ� ���ڴ�?", "���� �����ϴ� ������?" };
		dcbPwHint = new DefaultComboBoxModel<String>(PwHint);
		jcbPwHint = new JComboBox<String>(dcbPwHint);
		
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
		
		ForgotPwEvt fpe = new ForgotPwEvt(this);
		jtfId.addActionListener(fpe);
		jtfPwAnswer.addActionListener(fpe);
		jcbPwHint.addActionListener(fpe);
		jbtCancle.addActionListener(fpe);
		jbtSearch.addActionListener(fpe);
		setVisible(true);

		
	

	}// ForgetPw

	public JTextField getJtfId() {
		return jtfId;
	}//getJtfId

	public JTextField getJtfPwAnswer() {
		return jtfPwAnswer;
	}//getJtfPwAnswer

	public DefaultComboBoxModel<String> getDcbPwHint() {
		return dcbPwHint;
	}//getDcbPwHint

	public JComboBox<String> getJcbPwHint() {
		return jcbPwHint;
	}//getJcbPwHint

	public JButton getJbtSearch() {
		return jbtSearch;
	}//getJbtSearch

	public JButton getJbtCancle() {
		return jbtCancle;
	}//getJbtCancle

	

}// class
