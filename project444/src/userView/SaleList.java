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
		super(rmm,"판매내역");
		////////////////////////////////첫번째 탭 시작////////////////////////////////
		String[] sellCol= {"이미지","제품명","가격","카테고리","지역","등록시간","검수여부"};
//		Object[][] sellRow = {
//				{1,2,3,4,5,6},
//				{1,2,3,4,5,6}
//				};
		
		dtmSell = new DefaultTableModel(sellCol, 6){//셀 내용 수정 금지
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtSell = new JTable(dtmSell) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
		};
		
		JScrollPane jspSell = new JScrollPane(jtSell);
		
		//////////////테이블 데이터 가운데 정렬//////////////
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//테이블 데이터 가운데 정렬을 하기 위해
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);//가운데 정렬 세팅
		TableColumnModel tcm = jtSell.getColumnModel();//정렬할 테이블의 컬럼모델을 가져옴
		
		for (int i = 0; i < tcm.getColumnCount(); i++) {//컬럼의 수만큼 반복하여 가운데정렬함
			tcm.getColumn(i).setCellRenderer(dtcr);
		}//end for
		
		//////////////테이블 데이터 가운데 정렬//////////////
		
		////////테이블 크기설정////////
		jtSell.getTableHeader().setReorderingAllowed(false);//테이블 컬럼 위치 변경 금지
		
		jtSell.getColumnModel().getColumn(0).setPreferredWidth(120);
		jtSell.getColumnModel().getColumn(0).setResizable(false);//테이블 컬럼 사이즈 변경 금지
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
		
		////////테이블 크기설정 끝////////
		
		jbtDelete = new JButton("삭제");
		jbtModify = new JButton("수정");
		JPanel jpSouth = new JPanel();
		jpSouth.add(jbtDelete);
		jpSouth.add(jbtModify);
		
		JPanel jpSellList = new JPanel(new BorderLayout());
		
		jpSellList.add("Center",jspSell);
		jpSellList.add("South",jpSouth);
		
		/////////////////////////////////첫번째 탭 끝/////////////////////////////////
		////////////////////////////////두번째 탭 시작////////////////////////////////
		String[] compCol= {"이미지","제품명","가격","카테고리", "지역","구매자ID","판매완료일자"};
//		Object[][] compRow = {
//				{1,2,3,4,5},
//				{1,2,3,4,5}
//				};
		
		dtmComp = new DefaultTableModel(compCol, 6) {//셀 내용 수정 금지
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtComplete = new JTable(dtmComp) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
		};
		
		JScrollPane jspComp = new JScrollPane(jtComplete);
		
		//////////////테이블 데이터 가운데 정렬//////////////
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();//테이블 데이터 가운데 정렬을 하기 위해
		dtcr2.setHorizontalAlignment(SwingConstants.CENTER);//가운데 정렬 세팅
		TableColumnModel tcm2 = jtComplete.getColumnModel();//정렬할 테이블의 컬럼모델을 가져옴
		
		for (int i = 0; i < tcm2.getColumnCount(); i++) {//컬럼의 수만큼 반복하여 가운데정렬함
			tcm2.getColumn(i).setCellRenderer(dtcr2);
		}//end for
		
		//////////////테이블 데이터 가운데 정렬//////////////
		
		////////테이블 크기설정////////
		jtComplete.getTableHeader().setReorderingAllowed(false);//테이블 컬럼 위치 변경 금지
		
		jtComplete.getColumnModel().getColumn(0).setPreferredWidth(120);
		jtComplete.getColumnModel().getColumn(0).setResizable(false);//테이블 컬럼 사이즈 변경 금지
		jtComplete.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtComplete.getColumnModel().getColumn(1).setResizable(false);
		jtComplete.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtComplete.getColumnModel().getColumn(2).setResizable(false);
		jtComplete.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtComplete.getColumnModel().getColumn(3).setResizable(false);
		jtComplete.getColumnModel().getColumn(4).setPreferredWidth(80);
		jtComplete.getColumnModel().getColumn(4).setResizable(false);
		
		jtComplete.setRowHeight(100);
		
		////////테이블 크기설정 끝////////
		
//		jbtDelete = new JButton("삭제");
//		jbtModify = new JButton("수정");
//		JPanel jpSouth = new JPanel();
//		jpSouth.add(jbtDelete);
//		jpSouth.add(jbtModify);
		
		JPanel jpCompList = new JPanel(new BorderLayout());
		
		jpCompList.add("Center",jspComp);
		/////////////////////////////////두번째 탭 끝/////////////////////////////////
		jtp = new JTabbedPane();
		
		jtp.add("판매중",jpSellList);
		jtp.add("거래완료",jpCompList);
		add(jtp);
		
		setBounds(100, 100, 560, 700);
		
		////////////////////////////////이벤트처리//////////////////////////////
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
