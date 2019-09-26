package userView;

import java.awt.Color;
import java.awt.Container;
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

import userControl.ForgotIdEvt;

public class ForgotId extends JDialog {
	private JTextField jtfPhone,jtfPhone2, jtfName;
	private JButton jbtSearch, jbtCancle;
	private DefaultComboBoxModel<String> dcbPhoneNum;
	private JComboBox<String> jcbPhoneNum;
	public ForgotId(Login lo) {
		super(lo,"아이디찾기");
		JLabel jlPhone = new JLabel("전화번호");
		JLabel jlName = new JLabel("이름");
		JLabel jlHyphen = new JLabel("-");
		JLabel jlHyphen2 = new JLabel("-");
		jtfPhone = new JTextField();
		jtfPhone2 = new JTextField();
		jtfName = new JTextField();

		jbtSearch = new JButton("찾기");
		jbtCancle = new JButton("취소");
		
		String[] phoneNum = {"010","011","012","016","017","018"};
		dcbPhoneNum = new DefaultComboBoxModel<String>(phoneNum);
		jcbPhoneNum = new JComboBox<String>(dcbPhoneNum);
		
		
		setLayout(null);
		
		jlPhone.setBounds(30, 50, 60, 25);
		jlHyphen.setBounds(160, 50, 10, 25);
		jlHyphen2.setBounds(252, 50, 10, 25);
		jlName.setBounds(30, 100, 60, 25);
		jtfPhone.setBounds(170, 50,80, 25);
		jtfPhone2.setBounds(260, 50,80, 25);
		jtfName.setBounds(100, 100, 220, 25);
		jbtSearch.setBounds(110, 150, 70, 28);
		jbtCancle.setBounds(200, 150, 70, 28);
		
		jcbPhoneNum.setBounds(100, 50,55, 25);
		setBounds(100, 100, 380, 250);
		
		Container c = getContentPane();
		c.setBackground(new Color(0xE8E8E8));
		jbtCancle.setBackground(new Color(0xFFCC66));
		jbtSearch.setBackground(new Color(0xFFCC66));
		jcbPhoneNum.setBackground(new Color(0xE8E8E8));
		
		
		

		add(jlPhone);
		add(jlHyphen);
		add(jlHyphen2);
		add(jlName);
		add(jtfPhone);
		add(jtfPhone2);
		add(jtfName);
		add(jbtSearch);
		add(jbtCancle);
		add(jcbPhoneNum);
		//이벤트 처리
		ForgotIdEvt fie = new ForgotIdEvt(this);
		jcbPhoneNum.addActionListener(fie);
		jbtCancle.addActionListener(fie);
		jbtSearch.addActionListener(fie);
		jtfName.addActionListener(fie);
		setVisible(true);

		
	}// ForgotIdPw
	public JTextField getJtfPhone() {
		return jtfPhone;
	}//getJtfPhone
	public JTextField getJtfPhone2() {
		return jtfPhone2;
	}//getJtfPhone2
	public JTextField getJtfName() {
		return jtfName;
	}//getJtfName
	public JButton getJbtSearch() {
		return jbtSearch;
	}//getJbtSearch
	public JButton getJbtCancle() {
		return jbtCancle;
	}//getJbtCancle
	public JComboBox<String> getJcbPhoneNum() {
		return jcbPhoneNum;
	}//getJcbPhoneNum

	

}// class
