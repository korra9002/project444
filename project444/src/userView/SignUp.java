package userView;



import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import userControl.SignUpEvt;

public class SignUp extends JFrame {
	private JLabel jlId, jlPw,jlRePw, jlName, jlGender, jlPhone,pHyphen,pHyphen2, jlLoc, jlPwHint, jlPwAnswer;
	private JTextField jtfId, jtfName, jtfPhone,jtfPhone2, jtfPwAnswer;
	private JPasswordField jpfPw, jpfRePass;
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
		jlRePw = new JLabel("��й�ȣ Ȯ��");
		jlName = new JLabel("�̸�  ");
		jlGender = new JLabel("����");
		jlPhone = new JLabel("����ó");
		pHyphen = new JLabel("-");
		pHyphen2 = new JLabel("-");
		jlLoc = new JLabel("����");
		jlPwHint = new JLabel("��й�ȣ ��Ʈ");
		jlPwAnswer = new JLabel("��Ʈ ����");
		
		
		// JTextField
		jtfId = new JTextField();
		jtfName = new JTextField();
		jtfPhone = new JTextField();
		jtfPhone2= new JTextField();
		jtfPwAnswer = new JTextField();
		// JPasswordField
		jpfPw = new JPasswordField();
		jpfRePass = new JPasswordField();

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
		jlRePw.setBounds(50, 200, 120, 25);
		jlName.setBounds(60, 240, 50, 25);
		jlGender.setBounds(60, 280, 50, 25);
//		jlPhone.setBounds(60, 320, 60, 25);
		jlLoc.setBounds(60, 370, 50, 25);
		jlPwHint.setBounds(40, 420, 100, 25);
		jlPwAnswer.setBounds(60, 480, 100, 25);
		
		jtfId.setBounds(140, 100, 180, 25);
		jpfPw.setBounds(140, 150, 220, 25);
		jpfRePass.setBounds(140, 200, 220, 25);
		jtfName.setBounds(140, 240, 220, 25);
//		jtfPhone.setBounds(225, 320, 80, 25);
//		jtfPhone2.setBounds(320, 320,85, 25);
		jtfPwAnswer.setBounds(140, 480, 250, 25);

		cbMan.setBounds(160, 280, 100, 20);
		cbWomen.setBounds(260, 280, 100, 20);

		jcbLoc.setBounds(140, 370, 150, 25);
		jcbPwHint.setBounds(140, 420, 250, 25);
		jcbPhoneNum.setBounds(140, 320, 60, 25);
		
		jbtIdCheck.setBounds(350, 100, 100, 30);
		jbtRegister.setBounds(230, 580, 100, 30);
		jbtCancle.setBounds(360, 580, 100, 30);
		
		
		JPanel jpPhone = new JPanel();
		jpPhone.setLayout(null);
		jlPhone.setBounds(0, 0, 80, 25);
		jpPhone.add(jlPhone);
		jcbPhoneNum.setBounds(80,0, 60, 25);
		jpPhone.add(jcbPhoneNum);
		pHyphen.setBounds(150, 0, 10, 25);
		jpPhone.add(pHyphen);
		jtfPhone.setBounds(160, 0, 80, 25);
		jpPhone.add(jtfPhone);
		pHyphen2.setBounds(250, 0, 10, 25);
		jpPhone.add(pHyphen2);
		jtfPhone2.setBounds(260, 0, 80, 25);
		jpPhone.add(jtfPhone2);
		jpPhone.setBounds(60, 320, 550, 25);
		
		// ��ġ
		add(jlId);
		add(jlPw);
		add(jlRePw);
		add(jlName);
		add(jlGender);
		add(jlLoc);
//		add(jlPhone);
		add(jlPwHint);
		add(jlPwAnswer);
		
		add(jtfId);
		add(jpfPw);
		add(jpfRePass);
		add(jtfName);
//		add(jtfPhone);
//		add(jtfPhone2);
		add(jtfPwAnswer);

		add(cbMan);
		add(cbWomen);

		add(jcbLoc);
		add(jcbPwHint);
//		add(jcbPhoneNum);
		
		add(jbtIdCheck);
		add(jbtRegister);
		add(jbtCancle);
		add(jpPhone);
		//�̺�Ʈó��
		SignUpEvt sue = new SignUpEvt(this);
		jtfId.addActionListener(sue);
		jpfPw.addActionListener(sue);
		jpfRePass.addActionListener(sue);
		jtfName.addActionListener(sue);
		cbMan.addMouseListener(sue);
		cbWomen.addMouseListener(sue);
		jcbPhoneNum.addActionListener(sue);
		jtfPhone.addActionListener(sue);
		jcbLoc.addActionListener(sue);
		jcbPwHint.addActionListener(sue);
		jtfPwAnswer.addActionListener(sue);
		jbtRegister.addActionListener(sue);
		jbtIdCheck.addActionListener(sue);
		jbtCancle.addActionListener(sue);
		// WindowSizing
		setBounds(100, 100, 550, 700);
		// visible
		setVisible(true);
	}// SineUp

	public JLabel getpHyphen() {
		return pHyphen;
	}//getpHyphen

	public JLabel getpHyphen2() {
		return pHyphen2;
	}//getpHyphen2

	public JTextField getJtfId() {
		return jtfId;
	}//getJtfId

	public JTextField getJtfName() {
		return jtfName;
	}//getJtfName

	public JTextField getJtfPhone() {
		return jtfPhone;
	}//getJtfPhone

	public JTextField getJtfPhone2() {
		return jtfPhone2;
	}//getJtfPhone2

	public JTextField getJtfPwAnswer() {
		return jtfPwAnswer;
	}//getJtfPwAnswer

	public JPasswordField getJpfPw() {
		return jpfPw;
	}//getJpfPw

	public JPasswordField getJpfRePass() {
		return jpfRePass;
	}//getJpfRePass

	public JButton getJbtIdCheck() {
		return jbtIdCheck;
	}//getJbtIdCheck

	public JButton getJbtRegister() {
		return jbtRegister;
	}//getJbtRegister

	public JButton getJbtCancle() {
		return jbtCancle;
	}//getJbtCancle

	public CheckboxGroup getCbgGender() {
		return cbgGender;
	}//getCbgGender

	public Checkbox getCbWomen() {
		return cbWomen;
	}//getCbWomen

	public Checkbox getCbMan() {
		return cbMan;
	}//getCbMan

	public static JComboBox<String> getJcbPhoneNum() {
		return jcbPhoneNum;
	}//getJcbPhoneNum

	public static JComboBox<String> getJcbLoc() {
		return jcbLoc;
	}//getJcbLoc

	public static JComboBox<String> getJcbPwHint() {
		return jcbPwHint;
	}//getJcbPwHint

	public String[] getPwHint() {
		return PwHint;
	}//getPwHint



	

	
}// class
