package userView;

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
		JLabel jlId = new JLabel("아이디");
		JLabel  jlPwHint= new JLabel("비밀번호 힌트");
		JLabel jlPwAnswer= new JLabel("내용");
		
		jtfId = new JTextField("아이디를 입력");
		jtfPwAnswer = new JTextField("힌트에 대한 답변을 작성");

		jbtSearch = new JButton("찾기");
		jbtCancle = new JButton("취소");

		jcbPwHint = new JComboBox<String>();
		// Password Hint ComboBox
		String[] PwHint = { "- 비밀번호 힌트 선택 -","나의 좌우명은?", "나의 보물 제 1호는?", "나의 휴대폰 번호 끝 네자리는?", "가장 기억에 남거나 좋아하는 장소는?", "가장 친한 친구 이름은?",
				"가장 받고 싶은 선물은?", "가장 좋아하는 노래 제목은?", "가장 좋아하는 연예인은?", "가장 좋아하는 음식은?", "가장 좋아하는 숫자는?", "가장 좋아하는 색깔은?" };
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

		// 패스워드 찾기 창 종료 처리
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
