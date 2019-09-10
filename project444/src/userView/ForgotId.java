package userView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ForgotId extends JDialog {
	private JTextField jtfPhone, jtfName;
	private JButton jbtSearch, jbtCancle;
	private DefaultComboBoxModel<String> dcbPhoneNum;
	private JComboBox<String> jcbPhoneNum;
	public ForgotId(Login lo) {
		super(lo,"아이디찾기");
		JLabel jlPhone = new JLabel("전화번호");
		JLabel jlName = new JLabel("이름");
		jtfPhone = new JTextField();
		jtfName = new JTextField();

		jbtSearch = new JButton("찾기");
		jbtCancle = new JButton("취소");
		
		String[] phoneNum = {"010","011","012","016","017","018"};
		dcbPhoneNum = new DefaultComboBoxModel<String>(phoneNum);
		jcbPhoneNum = new JComboBox<String>(dcbPhoneNum);
		
		
		setLayout(null);

		jlPhone.setBounds(30, 50, 60, 25);
		jlName.setBounds(30, 100, 60, 25);
		jtfPhone.setBounds(160, 50,160, 25);
		jtfName.setBounds(100, 100, 220, 25);
		jbtSearch.setBounds(110, 150, 70, 28);
		jbtCancle.setBounds(200, 150, 70, 28);
		
		jcbPhoneNum.setBounds(100, 50,55, 25);
		setBounds(100, 100, 380, 250);

		add(jlPhone);
		add(jlName);
		add(jtfPhone);
		add(jtfName);
		add(jbtSearch);
		add(jbtCancle);
		add(jcbPhoneNum);
		setVisible(true);

		//아이디 찾기 창 종료처리
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent e) {
				dispose();
			}

		});
	}// ForgotIdPw

	

}// class
