package userView;



import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import userControl.SignUpEvt;

public class SignUp extends JDialog {
	
	private JTextField jtfId, jtfName, jtfPhone,jtfPhone2, jtfPwAnswer;
	private JPasswordField jpfPw, jpfRePass;
	private JButton jbtIdCheck, jbtRegister, jbtCancle;
	private JRadioButton jrbWomen, jrbMan;
	private ButtonGroup bgGender;
	private JComboBox<String> jcbPhoneNum,jcbLoc, jcbPwHint;
	private DefaultComboBoxModel<String> dcbLoc, dcbPwHint,dcbPhone;
	private String[] PwHint;

	public SignUp(Login lo) {
		super(lo, "ȸ������");
		// JLabel
		JLabel jlId = new JLabel("���̵�  ");
		JLabel jlPw = new JLabel("��й�ȣ  ");
		JLabel jlRePw = new JLabel("��й�ȣ Ȯ��");
		JLabel jlName = new JLabel("�̸�  ");
		JLabel jlGender = new JLabel("����");
		JLabel jlPhone = new JLabel("����ó");
		JLabel pHyphen = new JLabel("-");
		JLabel pHyphen2 = new JLabel("-");
		JLabel jlLoc = new JLabel("����");
		JLabel jlPwHint = new JLabel("��й�ȣ ��Ʈ");
		JLabel jlPwAnswer = new JLabel("��Ʈ ����");
		
		
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

		// CheckBox
		jrbWomen = new JRadioButton("����");
		jrbMan = new JRadioButton("����"); 
		// ButtonGroup
		bgGender = new ButtonGroup();
		bgGender.add(jrbWomen);
		bgGender.add(jrbMan);
		// Loc ComboBox
		String[] loc = { "-��������-","������", "������", "���ϱ�", "������", "���Ǳ�", "������", "���α�", "��õ��", "�����", "������", "���빮��", "���۱�", "������",
				"���빮��", "���ʱ�", "������", "���ϱ�", "���ı�", "��õ��", "��������", "��걸", "����", "���α�", "�߱�", "�߶���" };
		dcbLoc = new DefaultComboBoxModel<String>(loc);
		// Password Hint ComboBox
		String[] PwHint = { "-��й�ȣ ��Ʈ ����-","���� �¿����?", "���� ���� �� 1ȣ��?", "���� �޴��� ��ȣ �� ���ڸ���?", "���� ��￡ ���ų� �����ϴ� ��Ҵ�?", "���� ģ�� ģ�� �̸���?",
				"���� �ް� ���� ������?", "���� �����ϴ� �뷡 ������?", "���� �����ϴ� ��������?", "���� �����ϴ� ������?", "���� �����ϴ� ���ڴ�?", "���� �����ϴ� ������?" };
		dcbPwHint = new DefaultComboBoxModel<String>(PwHint);
		// PhoneNum ComboBox
		String[] phoneNum = {"010","011","012","016","017","018"};
		dcbPhone = new DefaultComboBoxModel<String>(phoneNum);
		// JComboBox
		jcbLoc = new JComboBox<String>(dcbLoc);
		jcbPwHint = new JComboBox<String>(dcbPwHint);
		jcbPhoneNum = new JComboBox<String>(dcbPhone);
		
		// ������ġ
		setLayout(null);

		jlId.setBounds(60, 100, 80, 25);
		jlPw.setBounds(60, 150, 80, 25);
		jlRePw.setBounds(50, 200, 120, 25);
		jlName.setBounds(60, 240, 50, 25);
		jlGender.setBounds(60, 280, 50, 25);
		jlLoc.setBounds(60, 370, 50, 25);
		jlPwHint.setBounds(40, 420, 100, 25);
		jlPwAnswer.setBounds(60, 480, 100, 25);
		
		jtfId.setBounds(140, 100, 180, 25);
		jpfPw.setBounds(140, 150, 220, 25);
		jpfRePass.setBounds(140, 200, 220, 25);
		jtfName.setBounds(140, 240, 220, 25);
		jtfPwAnswer.setBounds(140, 480, 250, 25);

		jrbMan.setBounds(160, 280, 100, 20);
		jrbWomen.setBounds(260, 280, 100, 20);

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
		
		
		Container c = getContentPane();
		c.setBackground(new Color(0xf6f2ef));
		jbtRegister.setBackground(new Color(0xFFCC66));
		jbtCancle.setBackground(new Color(0xFFCC66));
		jbtIdCheck.setBackground(new Color(0xFFCC66));
		jpPhone.setBackground(new Color(0xf6f2ef));
		jrbMan.setBackground(new Color(0xf6f2ef));
		jrbWomen.setBackground(new Color(0xf6f2ef));
		jcbLoc.setBackground(new Color(0xFFFFFF));
		jcbPhoneNum.setBackground(new Color(0xFFFFFF));
		jcbPwHint.setBackground(new Color(0xFFFFFF));
		// ��ġ
		add(jlId);
		add(jlPw);
		add(jlRePw);
		add(jlName);
		add(jlGender);
		add(jlLoc);
		add(jlPwHint);
		add(jlPwAnswer);
		
		add(jtfId);
		add(jpfPw);
		add(jpfRePass);
		add(jtfName);
		add(jtfPwAnswer);

		add(jrbMan);
		add(jrbWomen);

		add(jcbLoc);
		add(jcbPwHint);
		
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
		jrbMan.addMouseListener(sue);
		jrbWomen.addMouseListener(sue);
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


	public JTextField getJtfId() {
		return jtfId;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfPhone() {
		return jtfPhone;
	}

	public JTextField getJtfPhone2() {
		return jtfPhone2;
	}

	public JTextField getJtfPwAnswer() {
		return jtfPwAnswer;
	}

	public JPasswordField getJpfPw() {
		return jpfPw;
	}

	public JPasswordField getJpfRePass() {
		return jpfRePass;
	}

	public JButton getJbtIdCheck() {
		return jbtIdCheck;
	}

	public JButton getJbtRegister() {
		return jbtRegister;
	}

	public JButton getJbtCancle() {
		return jbtCancle;
	}

	public JRadioButton getJrbWomen() {
		return jrbWomen;
	}

	public JRadioButton getJrbMan() {
		return jrbMan;
	}

	public ButtonGroup getBgGender() {
		return bgGender;
	}

	public JComboBox<String> getJcbPhoneNum() {
		return jcbPhoneNum;
	}

	public  JComboBox<String> getJcbLoc() {
		return jcbLoc;
	}

	public  JComboBox<String> getJcbPwHint() {
		return jcbPwHint;
	}

	public String[] getPwHint() {
		return PwHint;
	}

	


	

	
}// class
