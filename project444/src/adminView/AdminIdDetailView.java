package adminView;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import adminControl.AdminMainEvt;
import adminControl.AdminUserIdDetailViewEvt;
import adminVO.UserIdDetailVO;

@SuppressWarnings("serial")
public class AdminIdDetailView extends JDialog {
	private JTextField jtfId, jtfName, jtfGender, jtfPhone, jtfLoc, jtfJoinDate, jtfSuspendFlag;
	private JButton jbtOkey, jbtSuspend, jbtRelief; 
	
	private AdminMainView amv;
	
	public AdminIdDetailView(AdminMainView amv, UserIdDetailVO uidVO) {
		super(amv,"���̵� �� ����",true);
		this.amv = amv; 
		
		jtfId = new JTextField();
		jtfName = new JTextField();
		jtfGender = new JTextField();
		jtfPhone = new JTextField();
		jtfLoc = new JTextField();
		jtfJoinDate = new JTextField();
		jtfSuspendFlag = new JTextField();
		
		jtfId.setEditable(false);
		jtfName.setEditable(false);
		jtfGender.setEditable(false);
		jtfPhone.setEditable(false);
		jtfLoc.setEditable(false);
		jtfJoinDate.setEditable(false);
		jtfSuspendFlag.setEditable(false);
		
		jbtOkey = new JButton("Ȯ��");
		jbtSuspend = new JButton("����");
		jbtRelief = new JButton("����");
		
		jbtOkey .setBackground(new Color(0xFFCC66));
		jbtSuspend .setBackground(new Color(0xFFCC66));
		jbtRelief .setBackground(new Color(0xFFCC66));
		
		JLabel jlbID = new JLabel("ID"); 
		JLabel jlbName = new JLabel("�̸�");
		JLabel jlbGender = new JLabel("����"); 
		JLabel jlbPhone = new JLabel("��ȭ��ȣ"); 
		JLabel jlbLoc = new JLabel("����"); 
		JLabel jlbJoinDate = new JLabel("��������"); 
		JLabel jlbSuspendFlag = new JLabel("��������"); 
		
		//settext
		jtfId.setText(uidVO.getUser_id());
		jtfName.setText(uidVO.getUser_name());
		jtfGender.setText(uidVO.getGender().equals("M")?"����":"����");
		jtfPhone.setText(uidVO.getPhone());
		jtfLoc.setText(uidVO.getLoc());
		jtfJoinDate.setText(uidVO.getJoin_date());
		jtfSuspendFlag.setText(uidVO.getSuspend_flag());
		
		jlbID.setBounds(10, 10, 100, 30);
		jlbName.setBounds(10, 50, 100, 30);
		jlbGender.setBounds(10, 90, 100, 30);
		jlbPhone.setBounds(10, 130, 100, 30);
		jlbLoc.setBounds(10, 170, 100, 30);
		jlbJoinDate.setBounds(10, 210, 100, 30);
		jlbSuspendFlag.setBounds(10, 250, 100, 30);
		
		jtfId.setBounds(125, 10, 120, 30);
		jtfName.setBounds(125, 50, 120, 30);
		jtfGender.setBounds(125, 90, 120, 30);
		jtfPhone.setBounds(125, 130, 120, 30);
		jtfLoc.setBounds(125, 170, 120, 30);
		jtfJoinDate.setBounds(125, 210, 120, 30);
		jtfSuspendFlag.setBounds(125, 250, 120, 30);
		
		jbtOkey.setBounds(20, 300, 60, 30);
		jbtSuspend.setBounds(100, 300, 60, 30);
		jbtRelief.setBounds(180, 300, 60, 30);
		
		setLayout(null);
		add(jlbID);
		add(jtfId);
		add(jlbName);
		add(jtfName);
		add(jlbGender);
		add(jtfGender);
		add(jlbPhone);
		add(jtfPhone);
		add(jlbLoc);
		add(jtfLoc);
		add(jlbJoinDate);
		add(jtfJoinDate);
		add(jlbSuspendFlag);
		add(jtfSuspendFlag);
		add(jbtOkey);
		add(jbtSuspend);
		add(jbtRelief);
		
		AdminMainEvt ame = new AdminMainEvt(amv);
		AdminUserIdDetailViewEvt auidve=new AdminUserIdDetailViewEvt(this, ame);
		jbtOkey.addActionListener(auidve);
		jbtSuspend.addActionListener(auidve);
		jbtRelief.addActionListener(auidve);
		
	//////////////////////////////////�� ������/////////////////////////////////
			
	Container c = getContentPane();
	c.setBackground(new Color(0xf6f2ef));
	////////////////////////////////////////////////////////////////////////
			
		setBounds(amv.getX()+900, amv.getY()+50, 270, 380);
		setVisible(true);
		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//AdminDetailView

	public JTextField getJtfId() {
		return jtfId;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfGender() {
		return jtfGender;
	}

	public JTextField getJtfPhone() {
		return jtfPhone;
	}

	public JTextField getJtfLoc() {
		return jtfLoc;
	}

	public JTextField getJtfJoinDate() {
		return jtfJoinDate;
	}

	public JTextField getJtfSuspendFlag() {
		return jtfSuspendFlag;
	}

	public JButton getJbtOkey() {
		return jbtOkey;
	}

	public JButton getJbtSuspend() {
		return jbtSuspend;
	}

	public JButton getJbtRelief() {
		return jbtRelief;
	}

	public AdminMainView getAmv() {
		return amv;
	}
	
}//class
