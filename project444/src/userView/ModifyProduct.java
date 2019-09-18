package userView;

import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
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
import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.InsertProductVO;
import userVO.SaleListVO;

@SuppressWarnings("serial")
public class ModifyProduct extends JFrame {

	private JLabel jlbProductImg;
	private JButton jbtSelectImg, jbtOkay, jbtCancel ;
	private JTextField jtfSubject, jtfPrice;
	private JTextArea jtaExplain;
	private JComboBox<String> jcbCategory;
	private DefaultComboBoxModel<String> dcbCategory;
	
	public ModifyProduct(SaleList sl, RunMarketMain rmm) {
		super("��ǰ �Է�");
		
		String selectedValue0=(String)sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 0);
		String selectedValue1=(String)sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 1);		
		int selectedValue2=(int)sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 2);		
		String selectedValue3=(String)sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 3);
		
		System.out.println(selectedValue3);
		
		jlbProductImg = new JLabel(selectedValue0,JLabel.CENTER);
		jlbProductImg.setBorder(new EtchedBorder(EtchedBorder.RAISED));//�̹��� �� �׵θ� ����
		
		jbtSelectImg = new JButton("���� ���");
		
		String[] categoryList= {"������/����","����/���׸���","���Ƶ�/���Ƶ���","��Ȱ/������ǰ","�����Ƿ�","������ȭ",
							"��Ƽ/�̿�","�����м�/��ȭ","������/����","����/���","����/Ƽ��/����","�ݷ�������ǰ","��Ÿ �߰�ǰ"};
		
		dcbCategory = new DefaultComboBoxModel<String>(categoryList);
		jcbCategory = new JComboBox<String>(dcbCategory);
		jcbCategory.setBorder(new TitledBorder("ī�װ� ����"));
		jcbCategory.setSelectedIndex(Integer.parseInt(selectedValue3)-1);
		
		
		jtfSubject = new JTextField(selectedValue1);//�̺�Ʈó��-Ŭ�� �� �ؽ�Ʈ �������
		jtfPrice = new JTextField(String.valueOf(selectedValue2));//�̺�Ʈó��-Ŭ�� �� �ؽ�Ʈ �������
		
		
		///////////////////////// �󼼼��� �������� /////////////////////////////////////////
		//�� ���� ��������� �ͼ� ���� ��Ź��
		
		jtaExplain = new JTextArea("�� ����");//�̺�Ʈó��-Ŭ�� �� �ؽ�Ʈ �������
		
		String temp_flag = "S";
		
		UserDAO uDAO=UserDAO.getInstance();
//		List<SaleListVO> list =
		


		
		///////////////////////// �󼼼��� �������� �� /////////////////////////////////////////
		
		
		JScrollPane jspExplain = new JScrollPane(jtaExplain);
		
		jbtOkay = new JButton("�Ϸ�");
		jbtCancel = new JButton("���");
		
		setLayout(null);
		
		jlbProductImg.setBounds(10, 20, 120, 100);//�̹��� ������ 120x100
		jbtSelectImg.setBounds(150, 55, 100, 30);
		jcbCategory.setBounds(270, 43, 120, 50);
		jtfSubject.setBounds(10, 160, 385, 30);
		jtfPrice.setBounds(10, 200, 385, 30);
		jspExplain.setBounds(10, 240, 385, 240);
		jbtOkay.setBounds(100, 500, 80, 30);
		jbtCancel.setBounds(230, 500, 80, 30);
		
		add(jlbProductImg);
		add(jbtSelectImg);
		add(jcbCategory);
		add(jtfSubject);
		add(jtfPrice);
		add(jspExplain);
		add(jbtOkay);
		add(jbtCancel);
		
		ModifyProductEvt mpe=new ModifyProductEvt(this, sl, rmm);
		
		setBounds(100, 100, 420, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
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
