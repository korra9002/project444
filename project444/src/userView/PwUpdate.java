package userView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import userRun.RunMarketMain;

public class PwUpdate extends JDialog {
	private JPasswordField jpfCurrentPw, jpfUpdatePw, jpfReUpdatePw;
	private JButton jbtOk, jbtCancle;

	public PwUpdate(RunMarketMain rmm) {
		super(rmm,"��й�ȣ ����");
		// JLabel
		JLabel jlCurrentPw = new JLabel("���� ��й�ȣ");
		JLabel jlUpdatePw = new JLabel("������ ��й�ȣ");
		JLabel jlReUdatePw = new JLabel("������ ��й�ȣ Ȯ��");
		// JTextField
		jpfCurrentPw = new JPasswordField();
		jpfUpdatePw = new JPasswordField();
		jpfReUpdatePw = new JPasswordField();
		// JButton
		jbtOk = new JButton("����");
		jbtCancle = new JButton("���");
		//������ġ
		setLayout(null);
		jlCurrentPw.setBounds(20, 30, 100, 25);
		jlUpdatePw.setBounds(20, 70, 100, 25);
		jlReUdatePw.setBounds(20, 110, 130, 25);
		
		jpfCurrentPw.setBounds(180, 30, 150, 25);
		jpfUpdatePw.setBounds(180, 70, 150, 25);
		jpfReUpdatePw.setBounds(180, 110, 150, 25);
		
		jbtOk.setBounds(110, 170, 80, 25);
		jbtCancle.setBounds(200, 170, 80, 25);
		//��ġ
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
		//visible
		setVisible(true);
		//window closing 
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent e) {
				dispose();
			}
			
		});//addWindowListener
	}// PwUpdate

}// class
