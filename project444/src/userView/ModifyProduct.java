package userView;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import userControl.ModifyProductEvt;
import userControl.SaleListEvt;
import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.InsertProductVO;
import userVO.MarketDetailVO;
import userVO.SaleListVO;

@SuppressWarnings("serial")
public class ModifyProduct extends JFrame {

	private JLabel jlbProductImg, jlCategory ;
	private JButton jbtSelectImg, jbtOkay, jbtCancel ;
	private JTextField jtfSubject, jtfPrice;
	private JTextArea jtaExplain;
	private JComboBox<String> jcbCategory;
	private DefaultComboBoxModel<String> dcbCategory;
	
	public ModifyProduct(MarketDetailVO mdVO, SaleListEvt sle, RunMarketMain rmm) {
		super("��ǰ ����");
		
		String selectedValue0=mdVO.getImage();//�̹���
		String selectedValue1=mdVO.getProductName()+"("+mdVO.getProductCode()+")"; 
		int selectedValue2=mdVO.getPrice();	//����	
		String selectedValue3=mdVO.getCategory(); //ī�װ�
		String selectedValue4=mdVO.getpDetail(); // ��ǰ������

		System.out.println(selectedValue3);
		
		jlbProductImg = new JLabel();
		if(new File(RunMarketMain.imgPath+"/"+selectedValue0).exists()) {
			
			jlbProductImg.setIcon(new ImageIcon(new ImageIcon(RunMarketMain.imgPath+"/"+selectedValue0).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}else {
			jlbProductImg.setText(selectedValue0);
//			jlbProductImg.setIcon(new ImageIcon(RunMarketMain.imgPath+"/"+"default.png"));
		}
		jlbProductImg.setBorder(new EtchedBorder(EtchedBorder.RAISED));//�̹��� �� �׵θ� ����
		
		jbtSelectImg = new JButton("���� ���");
		
		String[] categoryList= {"������/����","����/���׸���","���Ƶ�/���Ƶ���","��Ȱ/������ǰ","�����Ƿ�","������ȭ",
							"��Ƽ/�̿�","�����м�/��ȭ","������/����","����/���","����/Ƽ��/����","�ݷ�������ǰ","��Ÿ �߰�ǰ"};
		
		dcbCategory = new DefaultComboBoxModel<String>(categoryList);
//		jcbCategory = new JComboBox<String>(dcbCategory);
	//	jcbCategory.setBorder(new TitledBorder("ī�װ� ����"));
	//	jcbCategory.setSelectedItem(selectedValue3);
	//	jcbCategory.setEditable(false);
		
		jlCategory=new JLabel(selectedValue3);
		jlCategory.setBorder(new TitledBorder("ī�װ� ����"));

		
		jtfSubject = new JTextField(selectedValue1);//�̺�Ʈó��-Ŭ�� �� �ؽ�Ʈ �������
		jtfSubject.setEditable(false);
		
		jtfPrice = new JTextField(String.valueOf(selectedValue2));//�̺�Ʈó��-Ŭ�� �� �ؽ�Ʈ �������
		
		
		///////////////////////// �󼼼��� �������� /////////////////////////////////////////

		
		String temp_flag = "S";
		
		UserDAO uDAO=UserDAO.getInstance();
//		List<SaleListVO> list =
		

		jtaExplain = new JTextArea(selectedValue4);//�̺�Ʈó��-Ŭ�� �� �ؽ�Ʈ �������

		
		///////////////////////// �󼼼��� �������� �� /////////////////////////////////////////
		
		
		JScrollPane jspExplain = new JScrollPane(jtaExplain);
		
		jbtOkay = new JButton("�Ϸ�");
		jbtCancel = new JButton("���");
		
		setLayout(null);
		
		jlbProductImg.setBounds(10, 20, 100, 100);//�̹��� ������ 120x100
//		jbtSelectImg.setBounds(150, 55, 100, 30);
		jlCategory.setBounds(270, 43, 120, 50);
		jtfSubject.setBounds(10, 160, 385, 30);
		jtfPrice.setBounds(10, 200, 385, 30);
		jspExplain.setBounds(10, 240, 385, 240);
		jbtOkay.setBounds(100, 500, 80, 30);
		jbtCancel.setBounds(230, 500, 80, 30);
		
	////// ��, �� ������ //////////////
			Container c = getContentPane();
			c.setBackground(new Color(0xf6f2ef));
			jbtCancel.setBackground(new Color(0xFFCC66));
			jbtOkay.setBackground(new Color(0xFFCC66));
			jbtSelectImg.setBackground(new Color(0xFFCC66));
		
		add(jlbProductImg);
//		add(jbtSelectImg);
		add(jlCategory);
		add(jtfSubject);
		add(jtfPrice);
		add(jspExplain);
		add(jbtOkay);
		add(jbtCancel);
		 
		ModifyProductEvt mpe=new ModifyProductEvt(this, sle,  rmm);
		
		jbtOkay.addActionListener(mpe);
		jbtCancel.addActionListener(mpe);
		jtfPrice.addActionListener(mpe);
		jtaExplain.addMouseListener(mpe);
		
		
		
		setBounds(100, 100, 420, 600);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}//InsertProduct

	public JButton getJbtSelectImg() {
		return jbtSelectImg;
	}

	public JButton getJbtOkay() {
		return jbtOkay;
	}

	public JButton getJbtCancel() {
		return jbtCancel;
	}

	public JTextField getJtfSubject() {
		return jtfSubject;
	}

	public JTextField getJtfPrice() {
		return jtfPrice;
	}

	public JTextArea getJtaExplain() {
		return jtaExplain;
	}

	public JComboBox<String> getJcbCategory() {
		return jcbCategory;
	}
	
//	public static void main(String[] args) {
//	new ModifyProduct();
//	}//main

}//class
