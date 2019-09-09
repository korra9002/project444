package userView;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class PurchaseHistory extends JFrame {
	
	private JButton jbtDelete;
	private JTable jtPurchaseList;
	private DefaultTableModel dtmPurchaseList;
	public static final int CENTER = 0;
	 
	public PurchaseHistory() {
		super("���ų���");
		String[] sellCol= {"�̹���","��ǰ��","����","���̵�","����","���Žð�"};
		Object[][] sellRow = { 
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6}
				};
		
		dtmPurchaseList = new DefaultTableModel(sellRow, sellCol){//�� ���� ���� ����
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtPurchaseList = new JTable(dtmPurchaseList) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
		};
		
		JScrollPane jspPurchase = new JScrollPane(jtPurchaseList);
		
		//////////////���̺� ������ ��� ����//////////////
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//���̺� ������ ��� ������ �ϱ� ����
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);//��� ���� ����
		TableColumnModel tcm = jtPurchaseList.getColumnModel();//������ ���̺��� �÷����� ������
		
		for (int i = 0; i < tcm.getColumnCount(); i++) {//�÷��� ����ŭ �ݺ��Ͽ� ���������
			tcm.getColumn(i).setCellRenderer(dtcr);
		}//end for
		
		//////////////���̺� ������ ��� ����//////////////
		////////���̺� ũ�⼳��////////
		jtPurchaseList.getTableHeader().setReorderingAllowed(false);//���̺� �÷� ��ġ ���� ����
		
		jtPurchaseList.getColumnModel().getColumn(0).setPreferredWidth(120);
		jtPurchaseList.getColumnModel().getColumn(0).setResizable(false);;//���̺� �÷� ������ ���� ����
		jtPurchaseList.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtPurchaseList.getColumnModel().getColumn(1).setResizable(false);;
		jtPurchaseList.getColumnModel().getColumn(2).setPreferredWidth(75);
		jtPurchaseList.getColumnModel().getColumn(2).setResizable(false);;
		jtPurchaseList.getColumnModel().getColumn(3).setPreferredWidth(75);
		jtPurchaseList.getColumnModel().getColumn(3).setResizable(false);;
		jtPurchaseList.getColumnModel().getColumn(4).setPreferredWidth(75);
		jtPurchaseList.getColumnModel().getColumn(4).setResizable(false);;
		jtPurchaseList.getColumnModel().getColumn(5).setPreferredWidth(75);
		jtPurchaseList.getColumnModel().getColumn(5).setResizable(false);;
		
		jtPurchaseList.setRowHeight(100);
		
		////////���̺� ũ�⼳�� ��////////
		
//		jbtDelete = new JButton("����");
		
		JPanel jpHistory = new JPanel(null);
		
		jspPurchase.setBounds(10, 60, 540, 480);
		jpHistory.add(jspPurchase);
		
		/////////////////////////////////ù��° �� ��/////////////////////////////////
		
		add(jpHistory);
		
		setBounds(100, 100, 565, 600);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//SaleList

	public static void main(String[] args) {
		new PurchaseHistory();
	}//main

}//class
