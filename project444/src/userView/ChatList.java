package userView;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import userControl.ChatListEvt;

public class ChatList extends JPanel {

	private JButton jbRefresh, jbBuy, jbSell;

	private DefaultTableModel dtmProductList;
	private JTable jtProductList;
	
	private ChatListEvt cle;

	public ChatList() {

		//////////////////////// ä�� ȭ�� //////////////////////////////

//		Ȩ, �۾���, ä��, MyPage

		// JButton
		jbRefresh = new JButton("���ΰ�ħ");
		jbBuy = new JButton("������");
		jbSell = new JButton("�Ǹ���");
		// JTable

		String[] productColumn = { "��ǰ��", "ID", "����", "�ð�", "������ ��ȭ ����","�ŷ��ڵ�" };

		dtmProductList = new DefaultTableModel(productColumn, 6) {
		
			
			
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
			
		};
		
		jtProductList = new JTable(dtmProductList);

		JScrollPane jspProductList = new JScrollPane(jtProductList);
		// ����Ʈ ũ��, �̵�, ���� �Ұ����ϰ� ����

		jtProductList.setRowHeight(30);
		jtProductList.getTableHeader().setReorderingAllowed(false);

		jtProductList.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtProductList.getColumnModel().getColumn(1).setPreferredWidth(10);
		jtProductList.getColumnModel().getColumn(2).setPreferredWidth(10);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(90);
		jtProductList.getColumnModel().getColumn(4).setPreferredWidth(220);
		jtProductList.getColumnModel().getColumn(5).setPreferredWidth(10);
		/////////////////////////���̺� ��� ����////////////////////////////////
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = jtProductList.getColumnModel();
		tcm.getColumn(0).setCellRenderer(dtcrCenter);
		tcm.getColumn(1).setCellRenderer(dtcrCenter);
		tcm.getColumn(2).setCellRenderer(dtcrCenter);
		tcm.getColumn(3).setCellRenderer(dtcrCenter);
		tcm.getColumn(5).setCellRenderer(dtcrCenter);
		/////////////////////////���̺� ��� ����////////////////////////////////
//		setResizable(false);

		// ������Ʈ ��ġ
		setLayout(null);

		jspProductList.setBounds(30, 80, 750, 700);
		jbRefresh.setBounds(680, 20, 100,50);
		jbBuy.setBounds(30, 20, 90, 50);
		jbSell.setBounds(140, 20, 90, 50);

		add(jspProductList);
		add(jbRefresh);
		add(jbBuy);
		add(jbSell);

//		setBounds(100, 100,  600, 450);

		setVisible(true);
cle = new ChatListEvt(this);
jbRefresh.addActionListener(cle);
jbBuy.addActionListener(cle);
jbSell.addActionListener(cle);
jtProductList.addMouseListener(cle);

//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}// Chat

	public ChatListEvt getCle() {
		return cle;
	}


	public JButton getJbRefresh() {
		return jbRefresh;
	}

	public JButton getJbBuy() {
		return jbBuy;
	}

	public JButton getJbSell() {
		return jbSell;
	}

	public DefaultTableModel getDtmProductList() {
		return dtmProductList;
	}

	public JTable getJtProductList() {
		return jtProductList;
	}

}// class
