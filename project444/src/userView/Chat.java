package userView;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Chat extends JPanel {

	private JButton jbRefresh;

	private DefaultTableModel dtmProductList;
	private JTable jtProductList;

	public Chat() {

		//////////////////////// ä�� ȭ�� //////////////////////////////

//		Ȩ, �۾���, ä��, MyPage

		// JButton
		jbRefresh = new JButton("���ΰ�ħ");

		// JTable

		String[] productColumn = { "�̹���", "ID", "����", "�ð�", "������ ��ȭ ����" };

		dtmProductList = new DefaultTableModel(productColumn, 5);

		jtProductList = new JTable(dtmProductList);

		JScrollPane jspProductList = new JScrollPane(jtProductList);
		// ����Ʈ ũ��, �̵�, ���� �Ұ����ϰ� ����

		jtProductList.setRowHeight(30);
		jtProductList.getTableHeader().setReorderingAllowed(false);

		jtProductList.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtProductList.getColumnModel().getColumn(1).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(2).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(50);

//		setResizable(false);

		// ������Ʈ ��ġ
		setLayout(null);

		jspProductList.setBounds(30, 180, 400, 200);
		jbRefresh.setBounds(30, 400, 90, 30);

		add(jspProductList);
		add(jbRefresh);

		setBounds(100, 100, 500, 600);

		setVisible(true);

//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}// Chat

	public static void main(String[] args) {
		new Chat();

	}// main

}// class
