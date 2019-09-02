package userView;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class SaleList extends JFrame {
	
	private JTabbedPane jtp;
	private JButton jbtDelete, jbtModify;
	private JTable jtSell, jtComplete;
	private DefaultTableModel dtmSell, dtmComp;
	
	public SaleList() {
		super("�Ǹų���");
		////////////////////////////////ù��° �� ����////////////////////////////////
		String[] sellCol= {"�̹���","��ǰ��","����","����","��Ͻð�","�˼�����"};
		Object[][] sellRow = {
				{1,2,3,4,5,6},
				{1,2,3,4,5,6}
				};
		
		dtmSell = new DefaultTableModel(sellRow, sellCol){//�� ���� ���� ����
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtSell = new JTable(dtmSell) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
		};
		
		JScrollPane jspSell = new JScrollPane(jtSell);
		
		//////////////���̺� ������ ��� ����//////////////
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//���̺� ������ ��� ������ �ϱ� ����
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);//��� ���� ����
		TableColumnModel tcm = jtSell.getColumnModel();//������ ���̺��� �÷����� ������
		
		for (int i = 0; i < tcm.getColumnCount(); i++) {//�÷��� ����ŭ �ݺ��Ͽ� ���������
			tcm.getColumn(i).setCellRenderer(dtcr);
		}//end for
		
		//////////////���̺� ������ ��� ����//////////////
		
		////////���̺� ũ�⼳��////////
		jtSell.getTableHeader().setReorderingAllowed(false);//���̺� �÷� ��ġ ���� ����
		
		jtSell.getColumnModel().getColumn(0).setPreferredWidth(120);
		jtSell.getColumnModel().getColumn(0).setResizable(false);;//���̺� �÷� ������ ���� ����
		jtSell.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtSell.getColumnModel().getColumn(1).setResizable(false);;
		jtSell.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtSell.getColumnModel().getColumn(2).setResizable(false);;
		jtSell.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtSell.getColumnModel().getColumn(3).setResizable(false);;
		jtSell.getColumnModel().getColumn(4).setPreferredWidth(80);
		jtSell.getColumnModel().getColumn(4).setResizable(false);;
		jtSell.getColumnModel().getColumn(5).setPreferredWidth(80);
		jtSell.getColumnModel().getColumn(5).setResizable(false);;
		
		jtSell.setRowHeight(100);
		
		////////���̺� ũ�⼳�� ��////////
		
		jbtDelete = new JButton("����");
		jbtModify = new JButton("����");
		JPanel jpSouth = new JPanel();
		jpSouth.add(jbtDelete);
		jpSouth.add(jbtModify);
		
		JPanel jpSellList = new JPanel(new BorderLayout());
		
		jpSellList.add("Center",jspSell);
		jpSellList.add("South",jpSouth);
		
		/////////////////////////////////ù��° �� ��/////////////////////////////////
		////////////////////////////////�ι�° �� ����////////////////////////////////
		String[] compCol= {"�̹���","��ǰ��","����","����","������ID"};
		Object[][] compRow = {
				{1,2,3,4,5},
				{1,2,3,4,5}
				};
		
		dtmComp = new DefaultTableModel(compRow, compCol) {//�� ���� ���� ����
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtComplete = new JTable(dtmComp) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
		};
		
		JScrollPane jspComp = new JScrollPane(jtComplete);
		
		//////////////���̺� ������ ��� ����//////////////
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();//���̺� ������ ��� ������ �ϱ� ����
		dtcr2.setHorizontalAlignment(SwingConstants.CENTER);//��� ���� ����
		TableColumnModel tcm2 = jtComplete.getColumnModel();//������ ���̺��� �÷����� ������
		
		for (int i = 0; i < tcm2.getColumnCount(); i++) {//�÷��� ����ŭ �ݺ��Ͽ� ���������
			tcm2.getColumn(i).setCellRenderer(dtcr2);
		}//end for
		
		//////////////���̺� ������ ��� ����//////////////
		
		////////���̺� ũ�⼳��////////
		jtComplete.getTableHeader().setReorderingAllowed(false);//���̺� �÷� ��ġ ���� ����
		
		jtComplete.getColumnModel().getColumn(0).setPreferredWidth(120);
		jtComplete.getColumnModel().getColumn(0).setResizable(false);;//���̺� �÷� ������ ���� ����
		jtComplete.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtComplete.getColumnModel().getColumn(1).setResizable(false);;
		jtComplete.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtComplete.getColumnModel().getColumn(2).setResizable(false);;
		jtComplete.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtComplete.getColumnModel().getColumn(3).setResizable(false);;
		jtComplete.getColumnModel().getColumn(4).setPreferredWidth(80);
		jtComplete.getColumnModel().getColumn(4).setResizable(false);;
		
		jtComplete.setRowHeight(100);
		
		////////���̺� ũ�⼳�� ��////////
		
//		jbtDelete = new JButton("����");
//		jbtModify = new JButton("����");
//		JPanel jpSouth = new JPanel();
//		jpSouth.add(jbtDelete);
//		jpSouth.add(jbtModify);
		
		JPanel jpCompList = new JPanel(new BorderLayout());
		
		jpCompList.add("Center",jspComp);
		/////////////////////////////////�ι�° �� ��/////////////////////////////////
		jtp = new JTabbedPane();
		
		jtp.add("�Ǹ���",jpSellList);
		jtp.add("�ŷ��Ϸ�",jpCompList);
		add(jtp);
		
		setBounds(100, 100, 560, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//SaleList

	public static void main(String[] args) {
		new SaleList();
	}//main

}//class
