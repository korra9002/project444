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

	private JLabel jlbProductImg;
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
		
		jbtSelectImg = new JButton("���� ���");
		jbtSelectImg.addActionListener(new InsertProductEvt(this,rmm));
		
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
		jlbProductImg.setBounds(40, 20, 120, 100);//�̹��� ������ 120x100
		jbtSelectImg.setBounds(180, 65, 100, 30);
		jcbCategory.setBounds(300, 53, 120, 50);
		jtfSubject.setBounds(40, 170, 385, 30);
		jtfPrice.setBounds(40, 210, 385, 30);
		jspExplain.setBounds(40, 250, 385, 240);
		jbtOkay.setBounds(130, 510, 80, 30);
		jbtCancel.setBounds(260, 510, 80, 30);
		
		add(jlbProductImg);
		add(jbtSelectImg);
		add(jcbCategory);
		add(jtfSubject);
		add(jtfPrice);
		add(jspExplain);
		add(jbtOkay);
		add(jbtCancel);
		

		
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
