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
public class InterestList extends JFrame {
	
	private JButton jbtDelete;
	private JTable jtInterest;
	private DefaultTableModel dtmInterest;
	
	public InterestList() {
		super("���ɸ��");
		String[] sellCol= {"�̹���","��ǰ��","����","���̵�","����","�ð�"};
		Object[][] sellRow = {
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6}
				};
		
		dtmInterest = new DefaultTableModel(sellRow, sellCol){//�� ���� ���� ����
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtInterest = new JTable(dtmInterest) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
		};
		
		JScrollPane jspInterest = new JScrollPane(jtInterest);
		
		//////////////���̺� ������ ��� ����//////////////
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//���̺� ������ ��� ������ �ϱ� ����
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);//��� ���� ����
		TableColumnModel tcm = jtInterest.getColumnModel();//������ ���̺��� �÷����� ������
		
		for (int i = 0; i < tcm.getColumnCount(); i++) {//�÷��� ����ŭ �ݺ��Ͽ� ���������
			tcm.getColumn(i).setCellRenderer(dtcr);
		}//end for
		
		//////////////���̺� ������ ��� ����//////////////
		////////���̺� ũ�⼳��////////
		jtInterest.getTableHeader().setReorderingAllowed(false);//���̺� �÷� ��ġ ���� ����
		
		jtInterest.getColumnModel().getColumn(0).setPreferredWidth(120);
		jtInterest.getColumnModel().getColumn(0).setResizable(false);;//���̺� �÷� ������ ���� ����
		jtInterest.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtInterest.getColumnModel().getColumn(1).setResizable(false);;
		jtInterest.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtInterest.getColumnModel().getColumn(2).setResizable(false);;
		jtInterest.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtInterest.getColumnModel().getColumn(3).setResizable(false);;
		jtInterest.getColumnModel().getColumn(4).setPreferredWidth(80);
		jtInterest.getColumnModel().getColumn(4).setResizable(false);;
		jtInterest.getColumnModel().getColumn(5).setPreferredWidth(80);
		jtInterest.getColumnModel().getColumn(5).setResizable(false);;
		
		jtInterest.setRowHeight(100);
		
		////////���̺� ũ�⼳�� ��////////
		
		jbtDelete = new JButton("����");
		JPanel jpNorth = new JPanel(new BorderLayout());
		jpNorth.add("East",jbtDelete);
		jpNorth.setBorder(BorderFactory.createEmptyBorder(10,10,10,30));
		
		JPanel jpSellList = new JPanel(new BorderLayout());
		
		jpSellList.add("North",jpNorth);
		jpSellList.add("Center",jspInterest);
		
		/////////////////////////////////ù��° �� ��/////////////////////////////////
		
		add(jpSellList);
		
		setBounds(100, 100, 560, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//SaleList

	public static void main(String[] args) {
		new InterestList();
	}//main

}//class
