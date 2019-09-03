package userView;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class SelectSaler extends JFrame {
	
	private JButton jbtSelect, jbtCancel;
	private JTable jtChatList;
	private DefaultTableModel dtmChatList;
	public static final int CENTER = 0;
	
	public SelectSaler() {
		super("�ŷ� ��� ����");
		String[] sellCol= {"����̹���","���̵�","����"};
		Object[][] sellRow = {
				{1,2,3},
				{1,2,3},
				{1,2,3},
				{1,2,3}
				};
		
		dtmChatList = new DefaultTableModel(sellRow, sellCol){//�� ���� ���� ����
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtChatList = new JTable(dtmChatList) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
		};
		
		JScrollPane jspChatList = new JScrollPane(jtChatList);
		
		//////////////���̺� ������ ��� ����//////////////
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//���̺� ������ ��� ������ �ϱ� ����
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);//��� ���� ����
		TableColumnModel tcm = jtChatList.getColumnModel();//������ ���̺��� �÷����� ������
		
		for (int i = 0; i < tcm.getColumnCount(); i++) {//�÷��� ����ŭ �ݺ��Ͽ� ���������
			tcm.getColumn(i).setCellRenderer(dtcr);
		}//end for
		
		//////////////���̺� ������ ��� ����//////////////
		
		////////���̺� ũ�⼳��////////
		jtChatList.getTableHeader().setReorderingAllowed(false);//���̺� �÷� ��ġ ���� ����
		
		jtChatList.getColumnModel().getColumn(0).setPreferredWidth(120);
		jtChatList.getColumnModel().getColumn(0).setResizable(false);;//���̺� �÷� ������ ���� ����
		jtChatList.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtChatList.getColumnModel().getColumn(1).setResizable(false);;
		jtChatList.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtChatList.getColumnModel().getColumn(2).setResizable(false);;
		
		jtChatList.setRowHeight(100);
		
		////////���̺� ũ�⼳�� ��////////
		
		jbtSelect = new JButton("�ǸſϷ�");
		jbtCancel = new JButton("���");
		JPanel jpSouth = new JPanel();
		jpSouth.add(jbtSelect);
		jpSouth.add(jbtCancel);
		
		JPanel jpChatList = new JPanel(new BorderLayout());
		
		JLabel select = new JLabel("�ŷ� ��븦 �������ּ���.",CENTER);
		Font font = new Font("����", Font.BOLD, 15);
		select.setFont(font);
		
		JPanel jpNorth = new JPanel();//�󺧿� ������ �ֱ� ���� jpanel�� �ִ´�.
		jpNorth.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		jpNorth.add(select);
		
		jpChatList.add("North",jpNorth);
		jpChatList.add("Center",jspChatList);
		jpChatList.add("South",jpSouth);
		
		/////////////////////////////////ù��° �� ��/////////////////////////////////
		
		add(jpChatList);
		
		setBounds(100, 100, 320, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//SaleList

	public static void main(String[] args) {
		new SelectSaler();
	}//main

}//class
