package userView;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import userControl.InsertProductEvt;
import userRun.RunMarketMain;

@SuppressWarnings("serial")
public class InsertProduct extends JPanel {

	private JLabel jlbProductImg, jlSubject, jlPrice, jlExplain;
	private JButton jbtSelectImg, jbtOkay, jbtCancel ;
	private JTextField jtfSubject, jtfPrice;
	private JTextArea jtaExplain;
	private JComboBox<String> jcbCategory;
	private DefaultComboBoxModel<String> dcbCategory; 
	


	
	RunMarketMain rmm;
	
	public InsertProduct(RunMarketMain rmm) {
//		super("��ǰ �Է�");
		
		this.rmm=rmm;
		
		
		jlbProductImg = new JLabel("��ǰ �̹���",JLabel.CENTER);
		jlbProductImg.setBorder(new EtchedBorder(EtchedBorder.RAISED));//�̹��� �� �׵θ� ����
		
		jlSubject=new JLabel("��ǰ��", JLabel.CENTER);
		jlPrice=new JLabel("��ǰ ����", JLabel.CENTER);
		jlExplain=new JLabel("��ǰ ����", JLabel.CENTER);
		
		jbtSelectImg = new JButton("���� ���");
//		jbtSelectImg.addActionListener(new InsertProductEvt(this,rmm));
		
		String[] categoryList= {"������/����","����/���׸���","���Ƶ�/���Ƶ���","��Ȱ/������ǰ","�����Ƿ�","������ȭ",
							"��Ƽ/�̿�","�����м�/��ȭ","������/����","����/���","����/Ƽ��/����","�ݷ�������ǰ","��Ÿ �߰�ǰ"};
		
		dcbCategory = new DefaultComboBoxModel<String>(categoryList);
		jcbCategory = new JComboBox<String>(dcbCategory);
		jcbCategory.setBorder(new TitledBorder("ī�װ� ����"));
		
		jtfSubject = new JTextField("�� ����");//�̺�Ʈó��-Ŭ�� �� �ؽ�Ʈ �������
		jtfPrice = new JTextField("���� �Է�");//�̺�Ʈó��-Ŭ�� �� �ؽ�Ʈ �������
		
		jtaExplain = new JTextArea("�� ����");//�̺�Ʈó��-Ŭ�� �� �ؽ�Ʈ �������
		JScrollPane jspExplain = new JScrollPane(jtaExplain);
		
		jbtOkay = new JButton("�Ϸ�");
		jbtCancel = new JButton("���");
		
		setLayout(null);
		
//		jtp.setBounds(0, 10, 400, 30);
		jlbProductImg.setBounds(50, 30, 200, 200);//�̹��� ������ 120x100
		jbtSelectImg.setBounds(330, 100, 140, 40);
		jlSubject.setBounds(50, 270, 130, 40);
		jlPrice.setBounds(50, 320, 130, 40);
		jlExplain.setBounds(50, 380, 130, 40);
		jcbCategory.setBounds(530, 80, 200, 60);
		jtfSubject.setBounds(180, 270, 550, 40);
		jtfPrice.setBounds(180, 320, 550, 40);
		jspExplain.setBounds(180, 380, 550, 310);
		jbtOkay.setBounds(240, 720, 120, 40); 
		jbtCancel.setBounds(400, 720, 120, 40);
		
		add(jlbProductImg);
		add(jbtSelectImg);
		add(jcbCategory);
		add(jlSubject);
		add(jlPrice);
		add(jlExplain);
		add(jtfSubject);
		add(jtfPrice);
		add(jspExplain);
		add(jbtOkay);
		add(jbtCancel);
		InsertProductEvt ipe=new InsertProductEvt(this, rmm);
		
		jbtSelectImg.addActionListener(ipe);
		jbtOkay.addActionListener(ipe);
		jbtCancel.addActionListener(ipe);
		
		jtfSubject.addMouseListener(ipe);
		jtfSubject.addActionListener(ipe);
		jtfPrice.addMouseListener(ipe);
		jtfPrice.addActionListener(ipe);
		jtaExplain.addMouseListener(ipe);
		
		
		
		
		setBounds(100, 100, 420, 640);
		setVisible(true);
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//InsertProduct

	public JLabel getJlbProductImg() {
		return jlbProductImg;
	}//getJlbProductImg

	public JButton getJbtSelectImg() {
		return jbtSelectImg;
	}//getJbtSelectImg

	public JButton getJbtOkay() {
		return jbtOkay;
	}//getJbtOkey

	public JButton getJbtCancel() {
		return jbtCancel;
	}//getJbtCancle

	public JTextField getJtfSubject() {
		return jtfSubject;
	}//getJtfSubject

	public JTextField getJtfPrice() {
		return jtfPrice;
	}//getJtfPrice

	public JTextArea getJtaExplain() {
		return jtaExplain;
	}//getJtaExplain

	public JComboBox<String> getJcbCategory() {
		return jcbCategory;
	}//getJcbCategory

	public DefaultComboBoxModel<String> getDcbCategory() {
		return dcbCategory;
	}//getDcbCategory
	
//	public static void main(String[] args) {
//		new InsertProduct();
//	}//main

}//class
