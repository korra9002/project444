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
		super(lo, "회원가입");
		// JLabel
		JLabel jlId = new JLabel("아이디  ");
		JLabel jlPw = new JLabel("비밀번호  ");
		JLabel jlRePw = new JLabel("비밀번호 확인");
		JLabel jlName = new JLabel("이름  ");
		JLabel jlGender = new JLabel("성별");
		JLabel jlPhone = new JLabel("연락처");
		JLabel pHyphen = new JLabel("-");
		JLabel pHyphen2 = new JLabel("-");
		JLabel jlLoc = new JLabel("지역");
		JLabel jlPwHint = new JLabel("비밀번호 힌트");
		JLabel jlPwAnswer = new JLabel("힌트 정답");
		
		
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
		jbtIdCheck = new JButton("중복 체크");
		jbtRegister = new JButton("등록");
		jbtCancle = new JButton("취소");

		// CheckBox
		jrbWomen = new JRadioButton("여자");
		jrbMan = new JRadioButton("남자"); 
		// ButtonGroup
		bgGender = new ButtonGroup();
		bgGender.add(jrbWomen);
		bgGender.add(jrbMan);
		// Loc ComboBox
		String[] loc = { "-지역선택-","강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구",
				"서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구" };
		dcbLoc = new DefaultComboBoxModel<String>(loc);
		// Password Hint ComboBox
		String[] PwHint = { "-비밀번호 힌트 선택-","나의 좌우명은?", "나의 보물 제 1호는?", "나의 휴대폰 번호 끝 네자리는?", "가장 기억에 남거나 좋아하는 장소는?", "가장 친한 친구 이름은?",
				"가장 받고 싶은 선물은?", "가장 좋아하는 노래 제목은?", "가장 좋아하는 연예인은?", "가장 좋아하는 음식은?", "가장 좋아하는 숫자는?", "가장 좋아하는 색깔은?" };
		dcbPwHint = new DefaultComboBoxModel<String>(PwHint);
		// PhoneNum ComboBox
		String[] phoneNum = {"010","011","012","016","017","018"};
		dcbPhone = new DefaultComboBoxModel<String>(phoneNum);
		// JComboBox
		jcbLoc = new JComboBox<String>(dcbLoc);
		jcbPwHint = new JComboBox<String>(dcbPwHint);
		jcbPhoneNum = new JComboBox<String>(dcbPhone);
		
		// 수동배치
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
		// 배치
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
		//이벤트처리
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
