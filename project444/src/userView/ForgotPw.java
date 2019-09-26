package userView;

import java.awt.Color;
import java.awt.Container;
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
		super(lo,"비밀번호 찾기");
		JLabel jlId = new JLabel("아이디");
		JLabel  jlPwHint= new JLabel("비밀번호 힌트");
		JLabel jlPwAnswer= new JLabel("내용");
		
		jtfId = new JTextField();
		jtfPwAnswer = new JTextField();

		jbtSearch = new JButton("찾기");
		jbtCancle = new JButton("취소");

		// Password Hint ComboBox
		String[] PwHint = { "- 비밀번호 힌트 선택 -","나의 좌우명은?", "나의 보물 제 1호는?", "나의 휴대폰 번호 끝 네자리는?", "가장 기억에 남거나 좋아하는 장소는?", "가장 친한 친구 이름은?",
				"가장 받고 싶은 선물은?", "가장 좋아하는 노래 제목은?", "가장 좋아하는 연예인은?", "가장 좋아하는 음식은?", "가장 좋아하는 숫자는?", "가장 좋아하는 색깔은?" };
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
		
		Container c = getContentPane();
		c.setBackground(new Color(0xf6f2ef));
		jbtCancle.setBackground(new Color(0xFFCC66));
		jbtSearch.setBackground(new Color(0xFFCC66));
		jcbPwHint.setBackground(new Color(0xFFFFFF));
		
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
