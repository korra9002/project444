package userView;

import java.awt.Checkbox;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MarketDetailBuyer extends JFrame {
	
	private JLabel jlDetailImg;
	private JTextField jtfName, jtfPrice, jtfId,jtfInputDate,jtfCategory;
	private JButton jbtChat;
	private JTextArea jtaStrongPoint;
	private Checkbox ckLike;

	
	public MarketDetailBuyer() {
		
		jlDetailImg = new JLabel(new ImageIcon("C:/dev/workspace/jdbc_prj/src/img/����.jpg/"));//�����X / ���� �̹���
//		jlDetailImg.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel jlId=new JLabel("�Ǹ���");
		JLabel jlName=new JLabel("��ǰ�̸�");
		JLabel jlPrice=new JLabel("����");
		JLabel jlInputDate=new JLabel("�ø�����");
		JLabel jlCategory=new JLabel("ī�װ���");
		
		ckLike = new Checkbox("���ɸ�Ͽ� �߰�");

		
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
		
		jbtChat=new JButton("ä������ �ŷ��ϱ�");
		
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
		
		jbtChat.setBounds(180, 280, 150, 30);
		
		ckLike.setBounds(180, 320, 150, 50);

		
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
		add(jbtChat);
		add(ckLike);
		add(jtfInputDate);
		add(jtfCategory);
		
		setResizable(false);
		setBounds(100, 100, 670, 440);
		setVisible(true);
		
		//setBounds
		
		
	}//MarketDetail

	public static void main(String[] args) {
		new MarketDetailBuyer();
	}// main
	

}//class