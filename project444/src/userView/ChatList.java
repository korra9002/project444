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

public class ChatList extends JPanel {

	private JButton jbRefresh;

	private DefaultTableModel dtmProductList;
	private JTable jtProductList;

	public ChatList() {

		//////////////////////// 채팅 화면 //////////////////////////////

//		홈, 글쓰기, 채팅, MyPage

		// JButton
		jbRefresh = new JButton("새로고침");

		// JTable

		String[] productColumn = { "이미지", "ID", "지역", "시간", "마지막 대화 내용" };

		dtmProductList = new DefaultTableModel(productColumn, 5);

		jtProductList = new JTable(dtmProductList);

		JScrollPane jspProductList = new JScrollPane(jtProductList);
		// 리스트 크기, 이동, 편집 불가능하게 설정

		jtProductList.setRowHeight(30);
		jtProductList.getTableHeader().setReorderingAllowed(false);

		jtProductList.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtProductList.getColumnModel().getColumn(1).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(2).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(50);

//		setResizable(false);

		// 컴포넌트 배치
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
		new ChatList();

	}// main

}// class
