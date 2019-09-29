package adminView;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AdminDetailView extends JDialog {
	private adminView av;
	private JTextField jtfId, jtfName, jtfGender, jtfLoc, jtfPwHint, jtfHintAnswer;
	private JButton jbtOkey, jbtStop;
	
	public AdminDetailView(adminView av) {
		super(av,"아이디 상세 정보",true);
		this.av = av;
		 
		jtfId = new JTextField();
		jtfName = new JTextField();
		jtfGender = new JTextField();
		jtfLoc = new JTextField();
		jtfPwHint = new JTextField();
		jtfHintAnswer = new JTextField();
		
		jtfId.setEditable(false);
		jtfName.setEditable(false);
		jtfGender.setEditable(false);
		jtfLoc.setEditable(false);
		jtfPwHint.setEditable(false);
		jtfHintAnswer.setEditable(false);
		
		jbtOkey = new JButton("확인");
		jbtStop = new JButton("정지");
		
		JLabel jlbID = new JLabel("ID"); 
		JLabel jlbName = new JLabel("이름");
		JLabel jlbGender = new JLabel("성별"); 
		JLabel jlbLoc = new JLabel("지역"); 
		JLabel jlbPwHint = new JLabel("비밀번호 힌트"); 
		JLabel jlbHintAnswer = new JLabel("힌트 정답"); 
		
		jlbID.setBounds(10, 10, 100, 30);
		jlbName.setBounds(10, 50, 100, 30);
		jlbGender.setBounds(10, 90, 100, 30);
		jlbLoc.setBounds(10, 130, 100, 30);
		jlbPwHint.setBounds(10, 170, 100, 30);
		jlbHintAnswer.setBounds(10, 210, 100, 30);
		
		jtfId.setBounds(125, 10, 120, 30);
		jtfName.setBounds(125, 50, 120, 30);
		jtfGender.setBounds(125, 90, 120, 30);
		jtfLoc.setBounds(125, 130, 120, 30);
		jtfPwHint.setBounds(125, 170, 120, 30);
		jtfHintAnswer.setBounds(125, 210, 120, 30);
		
		jbtOkey.setBounds(40, 260, 80, 30);
		jbtStop.setBounds(150, 260, 80, 30);
		
		setLayout(null);
		add(jlbID);
		add(jtfId);
		add(jlbName);
		add(jtfName);
		add(jlbGender);
		add(jtfGender);
		add(jlbLoc);
		add(jtfLoc);
		add(jlbPwHint);
		add(jtfPwHint);
		add(jlbHintAnswer);
		add(jtfHintAnswer);
		add(jbtOkey);
		add(jbtStop);
		
		
		
		setBounds(300, 100, 270, 340);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
	}//AdminDetailView

	public adminView getAv() {
		return av;
	}

	public JTextField getJtfId() {
		return jtfId;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfGender() {
		return jtfGender;
	}

	public JTextField getJtfLoc() {
		return jtfLoc; 
	}

	public JTextField getJtfPwHint() {
		return jtfPwHint;
	}

	public JTextField getJtfHintAnswer() {
		return jtfHintAnswer;
	}

	public JButton getJbtOkey() {
		return jbtOkey;
	}

	public JButton getJbtStop() {
		return jbtStop;
	}
	
	
//	public static void main(String[] args) {
//		new AdminDetailView();
//	}
	
}//class
