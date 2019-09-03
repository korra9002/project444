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

@SuppressWarnings("serial")
public class InsertProduct extends JFrame {

	private JTabbedPane jtp;
	private JLabel jlbProductImg;
	private JButton jbtSelectImg, jbtOkey, jbtCancle ;
	private JTextField jtfSubject, jtfPrice;
	private JTextArea jtaExplain;
	private JComboBox<String> jcbCategory;
	private DefaultComboBoxModel<String> dcbCategory; 
	
	public InsertProduct() {
		super("��ǰ �Է�");
		
		
		//JTabbedPane
		
		jtp=new JTabbedPane();
		
		JPanel jpMarket1=new JPanel();
		JPanel jpMarket2=new JPanel(null);
		JPanel jpMarket3=new JPanel();
		JPanel jpMarket4=new JPanel();
		
		
		jtp.addTab("Ȩ", jpMarket1);
		jtp.addTab("�۾���",jpMarket2);
		jtp.addTab("ä��", jpMarket3);
		jtp.addTab("MyPage", jpMarket4);
		
		jlbProductImg = new JLabel("��ǰ �̹���",JLabel.CENTER);
		jlbProductImg.setBorder(new EtchedBorder(EtchedBorder.RAISED));//�̹��� �� �׵θ� ����
		
		jbtSelectImg = new JButton("���� ���");
		
		String[] categoryList= {"������/����","����/���׸���","���Ƶ�/���Ƶ���","��Ȱ/������ǰ","�����Ƿ�","������ȭ",
							"��Ƽ/�̿�","�����м�/��ȭ","������/����","����/���","����/Ƽ��/����","�ݷ�������ǰ","��Ÿ �߰�ǰ"};
		
		dcbCategory = new DefaultComboBoxModel<String>(categoryList);
		jcbCategory = new JComboBox<String>(dcbCategory);
		jcbCategory.setBorder(new TitledBorder("ī�װ� ����"));
		
		jtfSubject = new JTextField("�� ����");//�̺�Ʈó��-Ŭ�� �� �ؽ�Ʈ �������
		jtfPrice = new JTextField("���� �Է�");//�̺�Ʈó��-Ŭ�� �� �ؽ�Ʈ �������
		
		jtaExplain = new JTextArea("�� ����");//�̺�Ʈó��-Ŭ�� �� �ؽ�Ʈ �������
		JScrollPane jspExplain = new JScrollPane(jtaExplain);
		
		jbtOkey = new JButton("�Ϸ�");
		jbtCancle = new JButton("���");
		
//		setLayout(null);
		
//		jtp.setBounds(0, 10, 400, 30);
		jlbProductImg.setBounds(10, 20, 120, 100);//�̹��� ������ 120x100
		jbtSelectImg.setBounds(150, 65, 100, 30);
		jcbCategory.setBounds(270, 53, 120, 50);
		jtfSubject.setBounds(10, 170, 385, 30);
		jtfPrice.setBounds(10, 210, 385, 30);
		jspExplain.setBounds(10, 250, 385, 240);
		jbtOkey.setBounds(100, 510, 80, 30);
		jbtCancle.setBounds(230, 510, 80, 30);
		
		jpMarket2.add(jlbProductImg);
		jpMarket2.add(jbtSelectImg);
		jpMarket2.add(jcbCategory);
		jpMarket2.add(jtfSubject);
		jpMarket2.add(jtfPrice);
		jpMarket2.add(jspExplain);
		jpMarket2.add(jbtOkey);
		jpMarket2.add(jbtCancle);
		
		add(jtp);
		
		setBounds(100, 100, 420, 640);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//InsertProduct
	
	public static void main(String[] args) {
		new InsertProduct();
	}//main

}//class
