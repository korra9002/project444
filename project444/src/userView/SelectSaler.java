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
		super("거래 상대 선택");
		String[] sellCol= {"등급이미지","아이디","지역"};
		Object[][] sellRow = {
				{1,2,3},
				{1,2,3},
				{1,2,3},
				{1,2,3}
				};
		
		dtmChatList = new DefaultTableModel(sellRow, sellCol){//셀 내용 수정 금지
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
		
		//////////////테이블 데이터 가운데 정렬//////////////
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//테이블 데이터 가운데 정렬을 하기 위해
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);//가운데 정렬 세팅
		TableColumnModel tcm = jtChatList.getColumnModel();//정렬할 테이블의 컬럼모델을 가져옴
		
		for (int i = 0; i < tcm.getColumnCount(); i++) {//컬럼의 수만큼 반복하여 가운데정렬함
			tcm.getColumn(i).setCellRenderer(dtcr);
		}//end for
		
		//////////////테이블 데이터 가운데 정렬//////////////
		
		////////테이블 크기설정////////
		jtChatList.getTableHeader().setReorderingAllowed(false);//테이블 컬럼 위치 변경 금지
		
		jtChatList.getColumnModel().getColumn(0).setPreferredWidth(120);
		jtChatList.getColumnModel().getColumn(0).setResizable(false);;//테이블 컬럼 사이즈 변경 금지
		jtChatList.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtChatList.getColumnModel().getColumn(1).setResizable(false);;
		jtChatList.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtChatList.getColumnModel().getColumn(2).setResizable(false);;
		
		jtChatList.setRowHeight(100);
		
		////////테이블 크기설정 끝////////
		
		jbtSelect = new JButton("판매완료");
		jbtCancel = new JButton("취소");
		JPanel jpSouth = new JPanel();
		jpSouth.add(jbtSelect);
		jpSouth.add(jbtCancel);
		
		JPanel jpChatList = new JPanel(new BorderLayout());
		
		JLabel select = new JLabel("거래 상대를 선택해주세요.",CENTER);
		Font font = new Font("굴림", Font.BOLD, 15);
		select.setFont(font);
		
		JPanel jpNorth = new JPanel();//라벨에 여백을 넣기 위해 jpanel에 넣는다.
		jpNorth.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		jpNorth.add(select);
		
		jpChatList.add("North",jpNorth);
		jpChatList.add("Center",jspChatList);
		jpChatList.add("South",jpSouth);
		
		/////////////////////////////////첫번째 탭 끝/////////////////////////////////
		
		add(jpChatList);
		
		setBounds(100, 100, 320, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//SaleList

	public static void main(String[] args) {
		new SelectSaler();
	}//main

}//class
