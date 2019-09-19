package userView;


	import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
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

import userControl.MarketMainEvt;


	public class MarketMain extends JPanel {
		
		public static final int DOUBLE_CLICK=2;
		
		
		private DefaultComboBoxModel<String> dcbm, dcbm1;
		private JComboBox<String> jcbArea, jcbCategory;
		private JTextField jtfSearch;
		private JButton jbSearch, jbRecent, jbPrice, jbRefresh;
		private JRadioButton jrbSubject, jrbId;
		
		
		private DefaultTableModel dtmProductList;
		private JTable jtProductList;
		private String id; 
		
		  // ����
		
		
		@SuppressWarnings("serial")
		public MarketMain(String id) throws SQLException {
//			super("�۾���");

			
		this.id=id;
			
		//////////////////////// Ȩ ȭ�� //////////////////////////////
		
//		Ȩ, ī�װ�, �۾���, ä��, MyPage
				
		
		//JComboBox
		String[] dataArea= {"������ü","������", "������", "���ϱ�", "������", "���Ǳ�", "������", "���α�", "��õ��",
				"�����", "������", "���빮��", "���۱�", "������", "���빮��", "���ʱ�", "������", "���ϱ�",
				"���ı�", "��õ��", "��������", "��걸", "����", "���α�", "�߱�", "�߶���"};
		
		dcbm = new DefaultComboBoxModel<String>(dataArea);	
		jcbArea=new JComboBox<String>(dcbm);
		
		String[] dataCategory= {"ī�װ���ü", "������/����", "����/���׸���", 	"���Ƶ�/���Ƶ���",
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
		
		
		//JRadioButton
		jrbSubject=new JRadioButton("����", true);
		jrbId=new JRadioButton("���̵�");
		ButtonGroup bg=new ButtonGroup(); //�ΰ� ���ÿ� ������ �� ������
		
		bg.add(jrbSubject);
		bg.add(jrbId);
		
		JPanel panel2=new JPanel();
		panel2.add(jcbCategory);
		panel2.add(jrbSubject);
		panel2.add(jrbId);
		
		//JButton 
		jbRecent=new JButton("�ֽż�");
		jbPrice=new JButton("���ݼ�");
		jbRefresh=new JButton("���ΰ�ħ");
		
		JPanel panel3=new JPanel();
		panel3.add(jbRecent);
		panel3.add(jbPrice);
		panel3.add(jbRefresh);
		
		
		//JTable
		
		String[] productColumn= {"�̹���", "��ǰ��", "����", "����", "�ð�", "ī�װ�", "�Ǹ��� ID" };
		
		dtmProductList=new DefaultTableModel(productColumn, 7) {

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				};
		
		};
		
		jtProductList=new JTable(dtmProductList);
				
				
		
		JScrollPane jspProductList=new JScrollPane(jtProductList);

		
//		
//		@Override
//		public boolean isCellEditable(String row, int column) {
//			return false;
//		}//isCellEditable
//	};
//	jtProductList = new JTable(dtmProductList) {
//		@Override
//		public Class<?> getColumnClass(int column) {
//			return getValueAt(0, column).getClass();
//		}//getColumnClass
//	};
		
		
		
		
		
		//����Ʈ ũ��, �̵�, ���� �Ұ����ϰ� ����
		
		
		jtProductList.setRowHeight(30);
		jtProductList.getTableHeader().setReorderingAllowed(false);
		
		jtProductList.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtProductList.getColumnModel().getColumn(1).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(2).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(4).setPreferredWidth(50);
		jtProductList.getColumnModel().getColumn(5).setPreferredWidth(50);
		jtProductList.getColumnModel().getColumn(6).setPreferredWidth(30);
		
//		setResizable(false);
		
	
		
		// ������Ʈ ��ġ 
		setLayout(null);
		
		panel1.setBounds(30, 30, 400, 40);
		panel2.setBounds(40, 70, 400, 40);
		panel3.setBounds(30, 110, 400, 40);
		jspProductList.setBounds(30, 190, 600, 450);
		
		add(panel1);
		add(panel2);
		add(panel3);
		add(jspProductList);
		
		setBounds(100, 100, 750, 750);
		
		//������ ����ȭ
		setVisible(true);
		
		
		
		
	//�̺�Ʈ ó��
		
		MarketMainEvt mme=new MarketMainEvt(this, id);
		
		jcbArea.addActionListener(mme);
		jcbCategory.addActionListener(mme);
		jtfSearch.addActionListener(mme);
		jbSearch.addActionListener(mme);
		jbRecent.addActionListener(mme);
		jbPrice.addActionListener(mme);
		jbRefresh.addActionListener(mme);
		jrbSubject.addActionListener(mme);
		jrbId.addActionListener(mme);	
		jtProductList.addMouseListener(mme);
		
	
		
		
		
//		jtProductList.addMouseListener(new MouseAdapter() {
//			//���̺� ����Ŭ���Ǿ��� �� 
//			@Override
//			public void mouseClicked(MouseEvent me) {
//				switch(me.getClickCount()) {
//				case DOUBLE_CLICK : 
//					
//				int selectedRow=jtProductList.getSelectedRow();
//				
//				new MarketDetailBuyer();
//					
//				}
//			
//			
//			}//mouseClicked
//			
//			
//		});
	
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}//MarketMain

		public DefaultComboBoxModel<String> getDcbm() {
			return dcbm;
		}

		public DefaultComboBoxModel<String> getDcbm1() {
			return dcbm1;
		}

		public JComboBox<String> getJcbArea() {
			return jcbArea;
		}

		public JComboBox<String> getJcbCategory() {
			return jcbCategory;
		}

		public JTextField getJtfSearch() {
			return jtfSearch;
		}

		public JButton getJbSearch() {
			return jbSearch;
		}

		public JButton getJbRecent() {
			return jbRecent;
		}

		public JButton getJbPrice() {
			return jbPrice;
		}

		public JButton getJbRefresh() {
			return jbRefresh;
		}

		public JRadioButton getJrbSubject() {
			return jrbSubject;
		}

		public JRadioButton getJrbId() {
			return jrbId;
		}

		public DefaultTableModel getDtmProductList() {
			return dtmProductList;
		}

		public JTable getJtProductList() {
			return jtProductList;
		}
		
		
		

	}//MarketMain


