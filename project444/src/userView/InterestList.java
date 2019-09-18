package userView;

import java.awt.BorderLayout;

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

import userControl.InterestListEvt;
import userRun.RunMarketMain;

@SuppressWarnings("serial")
public class InterestList extends JDialog {
	
	private JButton jbtDelete;
	private JTable jtInterest;
	private DefaultTableModel dtmInterest; 
	
	public InterestList(RunMarketMain rmm) {
		super(rmm,"���ɸ��");
		String[] sellCol= {"�̹���","��ǰ��","����","���̵�","����","�ð�","ī�װ�"};
		Object[][] sellRow = {
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7}
				
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
		jtInterest.getColumnModel().getColumn(0).setResizable(false);//���̺� �÷� ������ ���� ����
		jtInterest.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtInterest.getColumnModel().getColumn(1).setResizable(false);
		jtInterest.getColumnModel().getColumn(2).setPreferredWidth(75);
		jtInterest.getColumnModel().getColumn(2).setResizable(false);
		jtInterest.getColumnModel().getColumn(3).setPreferredWidth(75);
		jtInterest.getColumnModel().getColumn(3).setResizable(false);
		jtInterest.getColumnModel().getColumn(4).setPreferredWidth(75);
		jtInterest.getColumnModel().getColumn(4).setResizable(false);
		jtInterest.getColumnModel().getColumn(5).setPreferredWidth(75);
		jtInterest.getColumnModel().getColumn(5).setResizable(false);
		
		jtInterest.setRowHeight(100);
		
		////////���̺� ũ�⼳�� ��////////
		
		jbtDelete = new JButton("����");
		
		JPanel jpInterestList = new JPanel(null);
		jbtDelete.setBounds(450, 15, 80, 30);
		jspInterest.setBounds(10, 60, 540, 480);
		
		jpInterestList.add(jbtDelete);
		jpInterestList.add(jspInterest);
		
		/////////////////////////////////ù��° �� ��/////////////////////////////////
		add(jpInterestList);
		//////////////////////��������//////////////////////
		InterestListEvt ile = new InterestListEvt(this);
		 jbtDelete.addActionListener(ile);
		 jtInterest.addMouseListener(ile);
		 
		/////////////////////////////////////////////////
		setBounds(100, 100, 565, 600);
		setVisible(true);
		setResizable(false);
		
	}//SaleList

	public JButton getJbtDelete() {
		return jbtDelete;
	}//getJbtDelete

	public JTable getJtInterest() {
		return jtInterest;
	}//getJtInterest

	public DefaultTableModel getDtmInterest() {
		return dtmInterest;
	}//getDtmInterest


}//class
