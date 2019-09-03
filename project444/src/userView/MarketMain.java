package userView;


	import javax.swing.DefaultComboBoxModel;
	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.JRadioButton;
	import javax.swing.JScrollPane;
	import javax.swing.JTabbedPane;
	import javax.swing.JTable;
	import javax.swing.JTextField;
	import javax.swing.table.DefaultTableModel;

//import javafx.scene.control.RadioButton;

	public class MarketMain extends JFrame {
		
		private JTabbedPane jtp;
		
		private DefaultComboBoxModel<String> dcbm, dcbm1;
		private JComboBox<String> jcbArea, jcbCategory;
		private JTextField jtfSearch;
		private JButton jbSearch, jbRecent, jbPrice;
		private JRadioButton jrbSubject, jrbId;
		
		private DefaultTableModel dtmProductList;
		private JTable jtProductList;
		  
		  // ����
		
		
		public MarketMain() {
			

			
			
			
		//////////////////////// Ȩ ȭ�� //////////////////////////////
		
//		Ȩ, ī�װ�, �۾���, ä��, MyPage
		jtp=new JTabbedPane();
		
		JPanel jpMarket1=new JPanel();
		JPanel jpMarket2=new JPanel();
		JPanel jpMarket3=new JPanel();
		JPanel jpMarket4=new JPanel();

		
		
		jtp.addTab("Ȩ", jpMarket1);
		jtp.addTab("�۾���",jpMarket2);
		jtp.addTab("ä��", jpMarket3);
		jtp.addTab("MyPage", jpMarket4);
		
		
		//JComboBox
		String[] dataArea= {"��ü","������", "������", "���ϱ�", "������", "���Ǳ�", "������", "���α�", "��õ��",
				"�����", "������", "���빮��", "���۱�", "������", "���빮��", "���ʱ�", "������", "���ϱ�",
				"���ı�", "��õ��", "��������", "��걸", "����", "���α�", "�߱�", "�߶���"};
		
		dcbm = new DefaultComboBoxModel<String>(dataArea);	
		jcbArea=new JComboBox<String>(dcbm);
		
		String[] dataCategory= {"������/����", "����/���׸���", 	"���Ƶ�/���Ƶ���",
				"��Ȱ/������ǰ", 	"�����Ƿ�", "������ȭ", "��Ƽ/�̿�", "�����м�/��ȭ",
				"������/����", "����/���", "����/Ƽ��/����", "�ݷ�������ǰ", "��Ÿ �߰�ǰ"};
		
		dcbm1 = new DefaultComboBoxModel<String>(dataCategory);	
		jcbCategory=new JComboBox<String>(dcbm1);
		
		jtfSearch=new JTextField(10);
		jbSearch=new JButton("�˻�");
		
		JPanel panel1=new JPanel();
		panel1.add(jcbArea);
		panel1.add(jtfSearch);
		panel1.add(jbSearch);
		
		
		//JRadioButton (+ ��� �߰� �ؾ��� : �� �� �Ѱ��� ������ �� �ֵ��� -> �̰� �ȵǸ� checkboxgroup���� ����)
		jrbSubject=new JRadioButton("����");
		jrbId=new JRadioButton("���̵�");
		
		JPanel panel2=new JPanel();
		panel2.add(jcbCategory);
		panel2.add(jrbSubject);
		panel2.add(jrbId);
		
		//JButton 
		jbRecent=new JButton("�ֽż�");
		jbPrice=new JButton("���ݼ�");
		
		JPanel panel3=new JPanel();
		panel3.add(jbRecent);
		panel3.add(jbPrice);
		
		//JTable
		
		String[] productColumn= {"�̹���", "��ǰ��", "����", "����", "�ð�" };
		
		dtmProductList=new DefaultTableModel(productColumn, 5);
		
		jtProductList=new JTable(dtmProductList);
		
		JScrollPane jspProductList=new JScrollPane(jtProductList);
		//����Ʈ ũ��, �̵�, ���� �Ұ����ϰ� ����
		
		
		jtProductList.setRowHeight(30);
		jtProductList.getTableHeader().setReorderingAllowed(false);
		
		jtProductList.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtProductList.getColumnModel().getColumn(1).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(2).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(50);
		
		setResizable(false);
		
		
		// ������Ʈ ��ġ 
		setLayout(null);
		
		jtp.setBounds(30, 30, 400, 30);
		panel1.setBounds(30, 60, 400, 40);
		panel2.setBounds(40, 100, 400, 40);
		panel3.setBounds(30, 140, 400, 40);
		jspProductList.setBounds(30, 180, 400, 200);
		
		add(jtp);
		add(panel1);
		add(panel2);
		add(panel3);
		add(jspProductList);
		
		setBounds(100, 100, 500, 600);
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}//MarketMain
		
		
		

	}//MarketMain


