package adminView;

import java.awt.event.WindowAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AdminCheckDetailView extends JDialog {
	
	private JLabel jlDetailImg;
	private JTextField jtfName, jtfPrice, jtfId,jtfInputDate,jtfCategory;
	private JButton jbtGrant, jbtReject;
	private JTextArea jtaStrongPoint;
	
//	private AdminView av; �����̶� ����/////// 

	
	public AdminCheckDetailView(/* CheckDetailView cdv, CheckListVO clVO*/) {
//		super(av,"��ǰ��",true);////////
//		this.av=av;////////////
		
		jlDetailImg = new JLabel(new ImageIcon("C:/dev/workspace/jdbc_prj/src/img/����.jpg/"));//�����X / ���� �̹���
//		jlDetailImg.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel jlId=new JLabel("�Ǹ���");
		JLabel jlName=new JLabel("��ǰ�̸�");
		JLabel jlPrice=new JLabel("����");
		JLabel jlInputDate=new JLabel("�ø�����");
		JLabel jlCategory=new JLabel("ī�װ�");

		
		//JTextField
		jtfName=new JTextField();
		jtfPrice=new JTextField();
		jtfId=new JTextField();
		jtfInputDate=new JTextField();
		jtfCategory=new JTextField();
		
		//�ٲ��� ���ϰ�
		jtfName.setEditable(false);
		jtfPrice.setEditable(false);
		jtfId.setEditable(false);
		jtfInputDate.setEditable(false);
		jtfCategory.setEditable(false);
		
		
		//JTextArea
		jtaStrongPoint=new JTextArea();
		JScrollPane jsp=new JScrollPane(jtaStrongPoint);
		
		jbtGrant=new JButton("�ǸŽ���");
		jbtReject=new JButton("�ǸŰź�");
		
		//setBounds
		jlDetailImg.setBounds(15, 15, 320, 320);
		
		jlName.setBounds(345, 50, 70, 30);
		jlPrice.setBounds(345, 85, 70, 30);
		jlId.setBounds(345, 120, 70, 30);
		jlInputDate.setBounds(345, 155, 70, 30);
		jlCategory.setBounds(345, 190, 70, 40);
		
		
		jtfName.setBounds(420, 50, 215, 30);
		jtfPrice.setBounds(420, 85, 215, 30);
		jtfId.setBounds(420, 120, 215, 30);
		jtfInputDate.setBounds(420, 155, 215, 30);
		jtfCategory.setBounds(420, 190, 215, 30);
		
		jsp.setBounds(345, 260, 295, 120);
		
		jbtGrant.setBounds(40, 350, 88, 30);
		jbtReject.setBounds(150, 350, 88, 30);

		
		//��ġ
		setLayout(null);
		add(jlDetailImg);
		add(jlName);
		add(jlPrice);
		add(jlId);
		add(jlInputDate);
		add(jlCategory);
		add(jtfName);
		add(jtfPrice);
		add(jtfId);
		add(jsp);
		add(jbtGrant);
		add(jbtReject);
		add(jtfInputDate);
		add(jtfCategory);
		
		//�̺�Ʈ ���/////////////////////////////////////
//		CheckDetailView cdv=new CheckDetailView(/*this,cdv,clVO.getCode*/);//CheckListVO ���� getter�� �ڵ�////
			
//		jbtGrant.addActionListener(cdv);//////
//		jbtReject.addAncestorListener(cdv);////////
		
//		addWindowListener(new WindowAdapter() {///////
		
//		@Override//////////
		
//		});/////////
		
		setResizable(false);
		setBounds(100, 100, 670, 440);
		setVisible(true);
		
		//setBounds
		
		
	}//MarketDetail


	public static void main(String[] args) {//////////////////����!!!!!!!!!!!!
		new AdminCheckDetailView();
	}
	
	//getter/////////////////////////////////
	

}//class
