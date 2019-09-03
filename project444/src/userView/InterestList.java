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
		super("관심목록");
		String[] sellCol= {"이미지","제품명","가격","아이디","지역","시간"};
		Object[][] sellRow = {
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6},
				{1,2,3,4,5,6}
				};
		
		dtmInterest = new DefaultTableModel(sellRow, sellCol){//셀 내용 수정 금지
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
		
		//////////////테이블 데이터 가운데 정렬//////////////
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//테이블 데이터 가운데 정렬을 하기 위해
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);//가운데 정렬 세팅
		TableColumnModel tcm = jtInterest.getColumnModel();//정렬할 테이블의 컬럼모델을 가져옴
		
		for (int i = 0; i < tcm.getColumnCount(); i++) {//컬럼의 수만큼 반복하여 가운데정렬함
			tcm.getColumn(i).setCellRenderer(dtcr);
		}//end for
		
		//////////////테이블 데이터 가운데 정렬//////////////
		////////테이블 크기설정////////
		jtInterest.getTableHeader().setReorderingAllowed(false);//테이블 컬럼 위치 변경 금지
		
		jtInterest.getColumnModel().getColumn(0).setPreferredWidth(120);
		jtInterest.getColumnModel().getColumn(0).setResizable(false);;//테이블 컬럼 사이즈 변경 금지
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
		
		////////테이블 크기설정 끝////////
		
		jbtDelete = new JButton("삭제");
		JPanel jpNorth = new JPanel(new BorderLayout());
		jpNorth.add("East",jbtDelete);
		jpNorth.setBorder(BorderFactory.createEmptyBorder(10,10,10,30));
		
		JPanel jpSellList = new JPanel(new BorderLayout());
		
		jpSellList.add("North",jpNorth);
		jpSellList.add("Center",jspInterest);
		
		/////////////////////////////////첫번째 탭 끝/////////////////////////////////
		
		add(jpSellList);
		
		setBounds(100, 100, 560, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//SaleList

	public static void main(String[] args) {
		new InterestList();
	}//main

}//class
