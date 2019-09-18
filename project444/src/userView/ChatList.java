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
import javax.swing.table.DefaultTableModel;

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

		String[] productColumn = { "�̹���", "ID", "����", "�ð�", "������ ��ȭ ����","�ŷ��ڵ�" };

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

		jtProductList.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtProductList.getColumnModel().getColumn(1).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(2).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(4).setPreferredWidth(50);
		jtProductList.getColumnModel().getColumn(5).setPreferredWidth(50);

//		setResizable(false);

		// ������Ʈ ��ġ
		setLayout(null);

		jspProductList.setBounds(30, 180, 400, 200);
		jbRefresh.setBounds(30, 400, 90, 30);
		jbBuy.setBounds(150, 400, 90, 30);
		jbSell.setBounds(270, 400, 90, 30);

		add(jspProductList);
		add(jbRefresh);
		add(jbBuy);
		add(jbSell);

		setBounds(100, 100, 500, 600);

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

	public static void main(String[] args) {
		new ChatList();

	}// main

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
