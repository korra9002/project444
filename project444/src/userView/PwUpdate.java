package userView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import userControl.PwUpdateEvt;
import userRun.RunMarketMain;

public class PwUpdate extends JDialog {
	private JPasswordField jpfCurrentPw, jpfUpdatePw, jpfReUpdatePw;
	private JButton jbtOk, jbtCancle;

	public PwUpdate(RunMarketMain rmm) {
		super(rmm,"비밀번호 변경");
		// JLabel
		JLabel jlCurrentPw = new JLabel("현재 비밀번호");
		JLabel jlUpdatePw = new JLabel("변경할 비밀번호");
		JLabel jlReUdatePw = new JLabel("변경할 비밀번호 확인");
		// JTextField
		jpfCurrentPw = new JPasswordField();
		jpfUpdatePw = new JPasswordField();
		jpfReUpdatePw = new JPasswordField();
		// JButton
		jbtOk = new JButton("변경");
		jbtCancle = new JButton("취소");
		//수동배치
		setLayout(null);
		jlCurrentPw.setBounds(20, 30, 100, 25);
		jlUpdatePw.setBounds(20, 70, 100, 25);
		jlReUdatePw.setBounds(20, 110, 130, 25);
		
		jpfCurrentPw.setBounds(180, 30, 150, 25);
		jpfUpdatePw.setBounds(180, 70, 150, 25);
		jpfReUpdatePw.setBounds(180, 110, 150, 25);
		
		jbtOk.setBounds(110, 170, 80, 25);
		jbtCancle.setBounds(200, 170, 80, 25);
		//배치
		add(jlCurrentPw);
		add(jlUpdatePw);
		add(jlReUdatePw);
		
		add(jpfCurrentPw);
		add(jpfUpdatePw);
		add(jpfReUpdatePw);
		
		add(jbtOk);
		add(jbtCancle);
		//window sizing
		setBounds(100, 100, 400, 250);
		
		PwUpdateEvt pue = new PwUpdateEvt(this);
		jbtOk.addActionListener(pue);
		jbtCancle.addActionListener(pue);
		jpfCurrentPw.addActionListener(pue);
		jpfReUpdatePw.addActionListener(pue);
		jpfUpdatePw.addActionListener(pue);
		
		//visible
		setVisible(true);
		
	}// PwUpdate

	public JPasswordField getJpfCurrentPw() {
		return jpfCurrentPw;
	}//jpfCurrentPw

	public JPasswordField getJpfUpdatePw() {
		return jpfUpdatePw;
	}//jpfUpdatePw

	public JPasswordField getJpfReUpdatePw() {
		return jpfReUpdatePw;
	}//jpfReUpdatePw

	public JButton getJbtOk() {
		return jbtOk;
	}//jbtOk

	public JButton getJbtCancle() {
		return jbtCancle;
	}//jbtCancle

}// class
