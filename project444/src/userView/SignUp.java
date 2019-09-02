package userView;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUp extends JFrame {
	private JLabel jlId, jlPw, jlName, jlGender, jlPhone, jlLoc, jlPwHint, jlPwAnswer;
	private JTextField jtfId, jtfPass, jtfName, jtfPhone, jtfPwAnswer;
	private JPasswordField jpfPw;
	private JButton jbtIdCheck, jbtRegister, jbtCancle;
	private CheckboxGroup cbgGender;
	private Checkbox cbWomen, cbMan;
	static JComboBox<String> jcbPhoneNum,jcbLoc, jcbPwHint;
	private String[] PwHint;

	public SignUp() {
		super("ȸ������");
		// JLabel
		jlId = new JLabel("���̵�  ");
		jlPw = new JLabel("��й�ȣ  ");
		jlName = new JLabel("�̸�  ");
		jlGender = new JLabel("����");
		jlPhone = new JLabel("����ó");
		jlLoc = new JLabel("����");
		jlPwHint = new JLabel("��й�ȣ ��Ʈ");
		jlPwAnswer = new JLabel("��Ʈ ����");

		// JTextField
		jtfId = new JTextField();
		jtfPass = new JTextField();
		jtfName = new JTextField();
		jtfPhone = new JTextField();
		jtfPwAnswer = new JTextField();

		// JPasswordField
		jpfPw = new JPasswordField();

		// JButton
		jbtIdCheck = new JButton("�ߺ� üũ");
		jbtRegister = new JButton("���");
		jbtCancle = new JButton("���");

		// CheckBoxGroup
		cbgGender = new CheckboxGroup();
		// CheckBox
		cbWomen = new Checkbox("����", cbgGender, false);
		cbMan = new Checkbox("����", cbgGender, false);

		// JComboBox
		jcbLoc = new JComboBox<String>();
		jcbPwHint = new JComboBox<String>();
		jcbPhoneNum = new JComboBox<String>();
		// Loc ComboBox
		String[] Loc = { "-��������-","������", "������", "���ϱ�", "������", "���Ǳ�", "������", "���α�", "��õ��", "�����", "������", "���빮��", "���۱�", "������",
				"���빮��", "���ʱ�", "������", "���ϱ�", "���ı�", "��õ��", "��������", "��걸", "����", "���α�", "�߱�", "�߶���" };
		for (int i = 0; i < Loc.length; i++) {
			jcbLoc.addItem(Loc[i]);
		} // end for

		// Password Hint ComboBox
		String[] PwHint = { "-��й�ȣ ��Ʈ ����-","���� �¿����?", "���� ���� �� 1ȣ��?", "���� �޴��� ��ȣ �� ���ڸ���?", "���� ��￡ ���ų� �����ϴ� ��Ҵ�?", "���� ģ�� ģ�� �̸���?",
				"���� �ް� ���� ������?", "���� �����ϴ� �뷡 ������?", "���� �����ϴ� ��������?", "���� �����ϴ� ������?", "���� �����ϴ� ���ڴ�?", "���� �����ϴ� ������?" };
		for (int i = 0; i < PwHint.length; i++) {
			jcbPwHint.addItem(PwHint[i]);
		} // end for
		String[] phoneNum = {"010","011","012","016","017","018"};
		for (int i = 0; i < phoneNum.length; i++) {
			jcbPhoneNum.addItem(phoneNum[i]);
		} // end for
		// ������ġ
		setLayout(null);

		jlId.setBounds(60, 100, 80, 25);
		jlPw.setBounds(60, 150, 80, 25);
		jlName.setBounds(60, 200, 50, 25);
		jlGender.setBounds(60, 260, 50, 25);
		jlPhone.setBounds(60, 310, 60, 25);
		jlLoc.setBounds(60, 360, 50, 25);
		jlPwHint.setBounds(40, 410, 100, 25);
		jlPwAnswer.setBounds(60, 470, 100, 25);

		jtfId.setBounds(140, 100, 180, 25);
		jtfPass.setBounds(140, 150, 220, 25);
		jtfName.setBounds(140, 200, 220, 25);
		jtfPhone.setBounds(205, 310, 180, 25);
		jtfPwAnswer.setBounds(140, 470, 250, 25);

		cbMan.setBounds(160, 260, 100, 20);
		cbWomen.setBounds(260, 260, 100, 20);

		jcbLoc.setBounds(140, 360, 150, 25);
		jcbPwHint.setBounds(140, 410, 250, 25);
		jcbPhoneNum.setBounds(140, 310, 60, 25);
		
		jbtIdCheck.setBounds(350, 100, 100, 30);
		jbtRegister.setBounds(230, 580, 100, 30);
		jbtCancle.setBounds(360, 580, 100, 30);

		// ��ġ
		add(jlId);
		add(jlPw);
		add(jlName);
		add(jlGender);
		add(jlLoc);
		add(jlPhone);
		add(jlPwHint);
		add(jlPwAnswer);

		add(jtfId);
		add(jtfPass);
		add(jtfName);
		add(jtfPhone);
		add(jtfPwAnswer);

		add(cbMan);
		add(cbWomen);

		add(jcbLoc);
		add(jcbPwHint);
		add(jcbPhoneNum);
		
		add(jbtIdCheck);
		add(jbtRegister);
		add(jbtCancle);

		// WindowSizing
		setBounds(100, 100, 550, 700);
		// visible
		setVisible(true);
		// close
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent e) {
			dispose();
			}
			
		});
	}// SineUp

	public JComboBox<String> getJcbPwHint() {
		return jcbPwHint;
	}//getJcbPwHint

//	public static void main(String[] args) {
//		new SignUp();
//	}// main

}// class
