package userView;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import userControl.SaleListEvt;
import userRun.RunMarketMain;

@SuppressWarnings("serial")
public class SaleList extends JDialog {
	
	private JTabbedPane jtp;
	private JButton jbtDelete, jbtModify;
	private JTable jtSell, jtComplete;
	private DefaultTableModel dtmSell, dtmComp;
	
	public SaleList(RunMarketMain rmm) throws SQLException {
		super(rmm,"�Ǹų���");
		////////////////////////////////ù��° �� ����////////////////////////////////
		String[] sellCol= {"�̹���","��ǰ��","����","ī�װ�","����","��Ͻð�","�˼�����"};
//		Object[][] sellRow = {
//				{1,2,3,4,5,6},
//				{1,2,3,4,5,6}
//				};
		
		dtmSell = new DefaultTableModel(sellCol, 0){//�� ���� ���� ����
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtSell = new JTable(dtmSell) ;
		
		JScrollPane jspSell = new JScrollPane(jtSell);
		
		//////////////���̺� ������ ��� ����//////////////
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//���̺� ������ ��� ������ �ϱ� ����
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);//��� ���� ����
		TableColumnModel tcm = jtSell.getColumnModel();//������ ���̺��� �÷����� ������
		
		for (int i = 1; i < tcm.getColumnCount(); i++) {//�÷��� ����ŭ �ݺ��Ͽ� ���������
			tcm.getColumn(i).setCellRenderer(dtcr);
		}//end for
		
		//////////////���̺� ������ ��� ����//////////////
		
		////////���̺� ũ�⼳��////////
		jtSell.getTableHeader().setReorderingAllowed(false);//���̺� �÷� ��ġ ���� ����
		
		jtSell.getColumnModel().getColumn(0).setPreferredWidth(120);
		jtSell.getColumnModel().getColumn(0).setResizable(false);//���̺� �÷� ������ ���� ����
		jtSell.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtSell.getColumnModel().getColumn(1).setResizable(false);
		jtSell.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtSell.getColumnModel().getColumn(2).setResizable(false);
		jtSell.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtSell.getColumnModel().getColumn(3).setResizable(false);
		jtSell.getColumnModel().getColumn(4).setPreferredWidth(80);
		jtSell.getColumnModel().getColumn(4).setResizable(false);
		jtSell.getColumnModel().getColumn(5).setPreferredWidth(80);
		jtSell.getColumnModel().getColumn(5).setResizable(false);
		
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
		String[] compCol= {"�̹���","��ǰ��","����","ī�װ�", "����","������ID","�ǸſϷ�����"};
//		Object[][] compRow = {
//				{1,2,3,4,5},
//				{1,2,3,4,5}
//				};
		
		dtmComp = new DefaultTableModel(compCol, 0) {//�� ���� ���� ����
			
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtComplete = new JTable(dtmComp) ;
		JScrollPane jspComp = new JScrollPane(jtComplete);
		
		//////////////���̺� ������ ��� ����//////////////
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();//���̺� ������ ��� ������ �ϱ� ����
		dtcr2.setHorizontalAlignment(SwingConstants.CENTER);//��� ���� ����
		TableColumnModel tcm2 = jtComplete.getColumnModel();//������ ���̺��� �÷����� ������
		
		for (int i = 1; i < tcm2.getColumnCount(); i++) {//�÷��� ����ŭ �ݺ��Ͽ� ���������
			tcm2.getColumn(i).setCellRenderer(dtcr2);
		}//end for
		
		//////////////���̺� ������ ��� ����//////////////
		
		////////���̺� ũ�⼳��////////
		jtComplete.getTableHeader().setReorderingAllowed(false);//���̺� �÷� ��ġ ���� ����
		
		jtComplete.getColumnModel().getColumn(0).setPreferredWidth(120);
		jtComplete.getColumnModel().getColumn(0).setResizable(false);//���̺� �÷� ������ ���� ����
		jtComplete.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtComplete.getColumnModel().getColumn(1).setResizable(false);
		jtComplete.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtComplete.getColumnModel().getColumn(2).setResizable(false);
		jtComplete.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtComplete.getColumnModel().getColumn(3).setResizable(false);
		jtComplete.getColumnModel().getColumn(4).setPreferredWidth(80);
		jtComplete.getColumnModel().getColumn(4).setResizable(false);
		
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
		
		////////////////////////////////�̺�Ʈó��//////////////////////////////
		SaleListEvt sle = new SaleListEvt(this, rmm);
		jbtModify.addActionListener(sle);
		jbtDelete.addActionListener(sle);
		
		jtp.addMouseListener(sle);
		jtSell.addMouseListener(sle);
		jtComplete.addMouseListener(sle);
		setVisible(true);
		
	}//SaleList

	public JTabbedPane getJtp() {
		return jtp;
	}//getJtp

	public JButton getJbtDelete() {
		return jbtDelete;
	}//getJbtDelete

	public JButton getJbtModify() {
		return jbtModify;
	}//getJbtModify

	public JTable getJtSell() {
		return jtSell;
	}//getJtSell

	public JTable getJtComplete() {
		return jtComplete;
	}//getJtComplete

	public DefaultTableModel getDtmSell() {
		return dtmSell;
	}//getDtmSell

	public DefaultTableModel getDtmComp() {
		return dtmComp;
	}//getDtmComp
	
	

}//class
