package userView;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import userControl.PurchaseHistoryEvt;
import userRun.RunMarketMain;

@SuppressWarnings("serial")
public class PurchaseHistory extends JDialog {
	
	private JButton jbtDelete;
	private JTable jtPurchaseList;
	private DefaultTableModel dtmPurchaseList;
	public static final int CENTER = 0;
	 
	public PurchaseHistory(RunMarketMain rmm) throws SQLException {
		super(rmm,"���ų���");
		String[] sellCol= {"�̹���","��ǰ��","����","���̵�","����","���Žð�"};
//		Object[][] sellRow = { 
//				{1,2,3,4,5,6},
//				{1,2,3,4,5,6},
//				{1,2,3,4,5,6},
//				{1,2,3,4,5,6},
//				{1,2,3,4,5,6},
//				{1,2,3,4,5,6},
//				{1,2,3,4,5,6}
//				};
		
		dtmPurchaseList = new DefaultTableModel(sellCol, 6){//�� ���� ���� ����
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtPurchaseList = new JTable(dtmPurchaseList); 
		
		JScrollPane jspPurchase = new JScrollPane(jtPurchaseList);
		
		//////////////���̺� ������ ��� ����//////////////
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//���̺� ������ ��� ������ �ϱ� ����
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);//��� ���� ����
		TableColumnModel tcm = jtPurchaseList.getColumnModel();//������ ���̺��� �÷����� ������
		
		for (int i = 1; i < tcm.getColumnCount(); i++) {//�÷��� ����ŭ �ݺ��Ͽ� ���������
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
		
		///////////////////////// �̺�Ʈ ó�� //////////////////////////////////////
		PurchaseHistoryEvt phe=new PurchaseHistoryEvt(this, rmm);
		
		jtPurchaseList.addMouseListener(phe);
		
		/////////////////////////////////ù��° �� ��/////////////////////////////////
		
		add(jpHistory);
		
		setBounds(100, 100, 565, 600);
		setVisible(true);
		setResizable(false);
		
		
	}//SaleList

	public JButton getJbtDelete() {
		return jbtDelete;
	}

	public JTable getJtPurchaseList() {
		return jtPurchaseList;
	}

	public DefaultTableModel getDtmPurchaseList() {
		return dtmPurchaseList;
	}

	

}//class
