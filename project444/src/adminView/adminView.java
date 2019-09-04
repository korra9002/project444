package adminView;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class adminView extends JFrame {
	
	private JTabbedPane jtp;
	private JButton jbtSearch1, jbtRefresh1, jbtRecent1, jbtSearch2, jbtRefresh2, jbtRecent2, 
		jbtSearch3, jbtRefresh3;
	private JTable jtCheckList, jtProductList, jtUserList;
	private DefaultTableModel dtmCheckList, dtmProductList, dtmUserList;
	private JComboBox<String> jcbCategory1, jcbCategory2, jcbCategory3;
	private DefaultComboBoxModel<String> dcbCategory1, dcbCategory2, dcbCategory3;
	private JTextField jtfSearch1, jtfSearch2, jtfSearch3;
	private JRadioButton jrbID1, jrbSubject1, jrbID2, jrbSubject2;
	private JCheckBox onSale, deleteNComplete ;
	
	public adminView() {
		super("������");
		////////////////////////////////ù��° �� ����////////////////////////////////
		String[] categoryList1= {"ī�װ� ����","������/����","����/���׸���","���Ƶ�/���Ƶ���","��Ȱ/������ǰ","�����Ƿ�","������ȭ",
				"��Ƽ/�̿�","�����м�/��ȭ","������/����","����/���","����/Ƽ��/����","�ݷ�������ǰ","��Ÿ �߰�ǰ"};

		dcbCategory1 = new DefaultComboBoxModel<String>(categoryList1);
		jcbCategory1 = new JComboBox<String>(dcbCategory1);
		
		jtfSearch1 = new JTextField();
		
		jrbSubject1 = new JRadioButton("����");
		jrbID1 = new JRadioButton("���̵�");
		ButtonGroup bg = new ButtonGroup();//�ΰ� �� ���õǴ� ���� ����
		bg.add(jrbSubject1);
		bg.add(jrbID1);
		
		jbtSearch1 = new JButton("�˻�");
		jbtRefresh1 = new JButton("���ΰ�ħ");
		jbtRecent1 = new JButton("�ֽż�");
		
		
		String[] checkCol= {"ī�װ�","�Ǹ���","�̹���","����","����","�ø���¥",""};
		Object[][] checkRow = {
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7}
				};
		
		dtmCheckList = new DefaultTableModel(checkRow, checkCol){//�� ���� ���� ����
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtCheckList = new JTable(dtmCheckList) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
		};
		
		JScrollPane jspCheckList = new JScrollPane(jtCheckList);
		
		//////////////���̺� ������ ��� ���� ����//////////////
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//���̺� ������ ��� ������ �ϱ� ����
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);//��� ���� ����
		TableColumnModel tcm = jtCheckList.getColumnModel();//������ ���̺��� �÷����� ������
		
		for (int i = 0; i < tcm.getColumnCount(); i++) {//�÷��� ����ŭ �ݺ��Ͽ� ���������
			tcm.getColumn(i).setCellRenderer(dtcr);
		}//end for
		//////////////���̺� ������ ��� ���� ��//////////////
		
		////////���̺� ũ�⼳�� ����////////
		jtCheckList.getTableHeader().setReorderingAllowed(false);//���̺� �÷� ��ġ ���� ����
		
		jtCheckList.getColumnModel().getColumn(0).setPreferredWidth(70);
		jtCheckList.getColumnModel().getColumn(0).setResizable(false);;//���̺� �÷� ������ ���� ����
		jtCheckList.getColumnModel().getColumn(1).setPreferredWidth(80);
		jtCheckList.getColumnModel().getColumn(1).setResizable(false);;
		jtCheckList.getColumnModel().getColumn(2).setPreferredWidth(120);
		jtCheckList.getColumnModel().getColumn(2).setResizable(false);;
		jtCheckList.getColumnModel().getColumn(3).setPreferredWidth(70);
		jtCheckList.getColumnModel().getColumn(3).setResizable(false);;
		jtCheckList.getColumnModel().getColumn(4).setPreferredWidth(120);
		jtCheckList.getColumnModel().getColumn(4).setResizable(false);;
		jtCheckList.getColumnModel().getColumn(5).setPreferredWidth(70);
		jtCheckList.getColumnModel().getColumn(5).setResizable(false);;
		jtCheckList.getColumnModel().getColumn(6).setPreferredWidth(70);
		jtCheckList.getColumnModel().getColumn(6).setResizable(false);;
		
		jtCheckList.setRowHeight(100);
		////////���̺� ũ�⼳�� ��////////
		
		JPanel jpCheckList = new JPanel(null);
		
		jspCheckList.setBounds(10, 10, 600, 500);
		jbtRefresh1.setBounds(500, 550, 90, 30);
		jbtRecent1.setBounds(50, 580, 90, 30);
		jcbCategory1.setBounds(50, 530, 120, 30);
		jtfSearch1.setBounds(200, 530, 150, 30);
		jbtSearch1.setBounds(380, 530, 90, 30);
		jrbSubject1.setBounds(200, 580, 70, 30);
		jrbID1.setBounds(270, 580, 70, 30);
		
		jpCheckList.add(jspCheckList);
		jpCheckList.add(jbtRecent1);
		jpCheckList.add(jbtRefresh1);
		jpCheckList.add(jcbCategory1);
		jpCheckList.add(jtfSearch1);
		jpCheckList.add(jbtSearch1);
		jpCheckList.add(jrbSubject1);
		jpCheckList.add(jrbID1);
		
		/////////////////////////////////ù��° �� ��/////////////////////////////////
		
		
		////////////////////////////////�ι�° �� ����////////////////////////////////
		String[] categoryList2= {"ī�װ� ����","������/����","����/���׸���","���Ƶ�/���Ƶ���","��Ȱ/������ǰ","�����Ƿ�","������ȭ",
				"��Ƽ/�̿�","�����м�/��ȭ","������/����","����/���","����/Ƽ��/����","�ݷ�������ǰ","��Ÿ �߰�ǰ"};

		dcbCategory2 = new DefaultComboBoxModel<String>(categoryList2);
		jcbCategory2 = new JComboBox<String>(dcbCategory2);
		
		onSale = new JCheckBox("�Ǹ���");
		deleteNComplete = new JCheckBox("<html>�ǸſϷ�/<br>����");
		
		jtfSearch2 = new JTextField();
		
		jrbSubject2 = new JRadioButton("����");
		jrbID2 = new JRadioButton("���̵�");
		ButtonGroup bg2 = new ButtonGroup();//�ΰ� �� ���õǴ� ���� ����
		bg2.add(jrbSubject2);
		bg2.add(jrbID2);
		
		jbtSearch2 = new JButton("�˻�");
		jbtRefresh2 = new JButton("���ΰ�ħ");
		jbtRecent2 = new JButton("�ֽż�");

		
		String[] productCol= {"ī�װ�","�Ǹ���","�̹���","����","����","�ø���¥","�Ǹ���/�ǸſϷ�"};
		Object[][] productRow = {
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7}
				};
		
		dtmProductList = new DefaultTableModel(productRow, productCol) {//�� ���� ���� ����
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtProductList = new JTable(dtmProductList) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
		};
		
		JScrollPane jspProductList = new JScrollPane(jtProductList);

		//////////////���̺� ������ ��� ���� ����//////////////
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();//���̺� ������ ��� ������ �ϱ� ����
		dtcr2.setHorizontalAlignment(SwingConstants.CENTER);//��� ���� ����
		TableColumnModel tcm2 = jtProductList.getColumnModel();//������ ���̺��� �÷����� ������
		
		for (int i = 0; i < tcm2.getColumnCount(); i++) {//�÷��� ����ŭ �ݺ��Ͽ� ���������
			tcm2.getColumn(i).setCellRenderer(dtcr2);
		}//end for
		//////////////���̺� ������ ��� ���� ��//////////////
		
		////////���̺� ũ�⼳�� ����////////
		jtProductList.getTableHeader().setReorderingAllowed(false);//���̺� �÷� ��ġ ���� ����
		
		jtProductList.getColumnModel().getColumn(0).setPreferredWidth(70);
		jtProductList.getColumnModel().getColumn(0).setResizable(false);;//���̺� �÷� ������ ���� ����
		jtProductList.getColumnModel().getColumn(1).setPreferredWidth(80);
		jtProductList.getColumnModel().getColumn(1).setResizable(false);;
		jtProductList.getColumnModel().getColumn(2).setPreferredWidth(120);
		jtProductList.getColumnModel().getColumn(2).setResizable(false);;
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(70);
		jtProductList.getColumnModel().getColumn(3).setResizable(false);;
		jtProductList.getColumnModel().getColumn(4).setPreferredWidth(120);
		jtProductList.getColumnModel().getColumn(4).setResizable(false);;
		jtProductList.getColumnModel().getColumn(5).setPreferredWidth(70);
		jtProductList.getColumnModel().getColumn(5).setResizable(false);;
		jtProductList.getColumnModel().getColumn(6).setPreferredWidth(70);
		jtProductList.getColumnModel().getColumn(6).setResizable(false);;
		
		jtProductList.setRowHeight(100);
		////////���̺� ũ�⼳�� ��////////

		JPanel jpProductList = new JPanel(null);
		
		jspProductList.setBounds(10, 10, 600, 500);
		jbtRefresh2.setBounds(500, 590, 90, 30);
		jbtRecent2.setBounds(50, 580, 90, 30);
		jcbCategory2.setBounds(50, 530, 120, 30);
		jtfSearch2.setBounds(200, 530, 150, 30);
		jbtSearch2.setBounds(380, 530, 90, 30);
		jrbSubject2.setBounds(200, 580, 70, 30);
		jrbID2.setBounds(270, 580, 70, 30);
		onSale.setBounds(500, 510, 90, 30);
		deleteNComplete.setBounds(500, 540, 90, 40);
		
		jpProductList.add(jspProductList);
		jpProductList.add(jbtRecent2);
		jpProductList.add(jbtRefresh2);
		jpProductList.add(jcbCategory2);
		jpProductList.add(jtfSearch2);
		jpProductList.add(jbtSearch2);
		jpProductList.add(jrbSubject2);
		jpProductList.add(jrbID2);
		jpProductList.add(onSale);
		jpProductList.add(deleteNComplete);
		/////////////////////////////////�ι�° �� ��/////////////////////////////////
		
		
		////////////////////////////////����° �� ����////////////////////////////////
		String[] searchList= {"��ü","���̵�","�̸�","��ȭ��ȣ","�̸���"};

		dcbCategory3 = new DefaultComboBoxModel<String>(searchList);
		jcbCategory3 = new JComboBox<String>(dcbCategory3);
		
		jtfSearch3 = new JTextField();
		
		jbtSearch3 = new JButton("�˻�");
		jbtRefresh3 = new JButton("���ΰ�ħ");
		
		String[] idCol= {"���̵�","�̸�","����","��ȭ��ȣ","�̸���","���Գ�¥"," "};
		Object[][] idRow = {
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7}
				};
		
		dtmUserList = new DefaultTableModel(idRow, idCol) {//�� ���� ���� ����
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtUserList = new JTable(dtmUserList) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
		};
		
		JScrollPane jspUserList = new JScrollPane(jtUserList);
		
		//////////////���̺� ������ ��� ���� ����//////////////
		DefaultTableCellRenderer dtcr3 = new DefaultTableCellRenderer();//���̺� ������ ��� ������ �ϱ� ����
		dtcr3.setHorizontalAlignment(SwingConstants.CENTER);//��� ���� ����
		TableColumnModel tcm3 = jtUserList.getColumnModel();//������ ���̺��� �÷����� ������
		
		for (int i = 0; i < tcm3.getColumnCount(); i++) {//�÷��� ����ŭ �ݺ��Ͽ� ���������
			tcm3.getColumn(i).setCellRenderer(dtcr3);
		}//end for
		//////////////���̺� ������ ��� ���� ��//////////////
		
		////////���̺� ũ�⼳�� ����////////
		jtUserList.getTableHeader().setReorderingAllowed(false);//���̺� �÷� ��ġ ���� ����
		
		jtUserList.getColumnModel().getColumn(0).setPreferredWidth(70);
		jtUserList.getColumnModel().getColumn(0).setResizable(false);;//���̺� �÷� ������ ���� ����
		jtUserList.getColumnModel().getColumn(1).setPreferredWidth(80);
		jtUserList.getColumnModel().getColumn(1).setResizable(false);;
		jtUserList.getColumnModel().getColumn(2).setPreferredWidth(120);
		jtUserList.getColumnModel().getColumn(2).setResizable(false);;
		jtUserList.getColumnModel().getColumn(3).setPreferredWidth(70);
		jtUserList.getColumnModel().getColumn(3).setResizable(false);;
		jtUserList.getColumnModel().getColumn(4).setPreferredWidth(120);
		jtUserList.getColumnModel().getColumn(4).setResizable(false);;
		jtUserList.getColumnModel().getColumn(5).setPreferredWidth(70);
		jtUserList.getColumnModel().getColumn(5).setResizable(false);;
		jtUserList.getColumnModel().getColumn(6).setPreferredWidth(70);
		jtUserList.getColumnModel().getColumn(6).setResizable(false);;
		
		jtUserList.setRowHeight(100);
		////////���̺� ũ�⼳�� ��////////
		
		JPanel jpUserList = new JPanel(null);
		
		jspUserList.setBounds(10, 10, 600, 500);
		jcbCategory3.setBounds(50, 550, 120, 30);
		jbtRefresh3.setBounds(500, 550, 90, 30);
		jtfSearch3.setBounds(200, 550, 150, 30);
		jbtSearch3.setBounds(380, 550, 90, 30);
		
		jpUserList.add(jspUserList);
		jpUserList.add(jcbCategory3);
		jpUserList.add(jbtRefresh3);
		jpUserList.add(jtfSearch3);
		jpUserList.add(jbtSearch3);
		/////////////////////////////////����° �� ��/////////////////////////////////
		
		jtp = new JTabbedPane();
		
		jtp.add("�˼����",jpCheckList);
		jtp.add("��ǰ���",jpProductList);
		jtp.add("�������",jpUserList);
		add(jtp);
		
		setBounds(100, 100, 640, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//SaleList

	public static void main(String[] args) {
		new adminView();
	}//main

}//class
