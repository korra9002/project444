package userView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import userControl.PurchaseHistoryEvt;
import userRun.RunMarketMain;

@SuppressWarnings("serial")
public class PurchaseHistory extends JDialog {
	
	private JButton jbtDelete;
	private JTable jtPurchaseList;
	private DefaultTableModel dtmPurchaseList;
	public static final int CENTER = 0;
	
	 
	public PurchaseHistory(RunMarketMain rmm) throws SQLException {
		super(rmm,"구매내역");
		String[] sellCol= {"이미지","제품명","가격","아이디","지역","구매시간"};
//		Object[][] sellRow = { 
//				{1,2,3,4,5,6},
//				{1,2,3,4,5,6},
//				{1,2,3,4,5,6},
//				{1,2,3,4,5,6},
//				{1,2,3,4,5,6},
//				{1,2,3,4,5,6},
//				{1,2,3,4,5,6}
//				};
		
		dtmPurchaseList = new DefaultTableModel(sellCol, 6){//셀 내용 수정 금지
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtPurchaseList = new JTable(dtmPurchaseList); 
		
		JScrollPane jspPurchase = new JScrollPane(jtPurchaseList);
		
		//////////////테이블 데이터 가운데 정렬//////////////
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//테이블 데이터 가운데 정렬을 하기 위해
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);//가운데 정렬 세팅
		TableColumnModel tcm = jtPurchaseList.getColumnModel();//정렬할 테이블의 컬럼모델을 가져옴
		
		for (int i = 1; i < tcm.getColumnCount(); i++) {//컬럼의 수만큼 반복하여 가운데정렬함
			tcm.getColumn(i).setCellRenderer(dtcr);
		}//end for
		
		//////////////테이블 데이터 가운데 정렬//////////////
		////////테이블 크기설정////////
		jtPurchaseList.getTableHeader().setReorderingAllowed(false);//테이블 컬럼 위치 변경 금지
		
		jtPurchaseList.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtPurchaseList.getColumnModel().getColumn(0).setResizable(false);;//테이블 컬럼 사이즈 변경 금지
		jtPurchaseList.getColumnModel().getColumn(1).setPreferredWidth(170);
		jtPurchaseList.getColumnModel().getColumn(1).setResizable(false);;
		jtPurchaseList.getColumnModel().getColumn(2).setPreferredWidth(50);
		jtPurchaseList.getColumnModel().getColumn(2).setResizable(false);;
		jtPurchaseList.getColumnModel().getColumn(3).setPreferredWidth(50);
		jtPurchaseList.getColumnModel().getColumn(3).setResizable(false);;
		jtPurchaseList.getColumnModel().getColumn(4).setPreferredWidth(48);
		jtPurchaseList.getColumnModel().getColumn(4).setResizable(false);;
		jtPurchaseList.getColumnModel().getColumn(5).setPreferredWidth(110);
		jtPurchaseList.getColumnModel().getColumn(5).setResizable(false);;
		
		jtPurchaseList.setRowHeight(100);
		
		////////테이블 크기설정 끝////////
		
//		jbtDelete = new JButton("삭제");
		
		JPanel jpHistory = new JPanel(null);
		
		jspPurchase.setBounds(10, 60, 540, 480);
		jpHistory.add(jspPurchase);
		
		///////////////////////// 이벤트 처리 //////////////////////////////////////
		PurchaseHistoryEvt phe=new PurchaseHistoryEvt(this, rmm);
		
		jtPurchaseList.addMouseListener(phe);
		
		/////////////////////////////////첫번째 탭 끝/////////////////////////////////
		//색 디자인
		Container c = getContentPane();
		c.setBackground(new Color(0xf6f2ef));
		jbtDelete.setBackground(new Color(0xFFCC66));
		jtPurchaseList.getTableHeader().setBackground(new Color(0xFFCC66));
		jspPurchase.setBackground(new Color(0xFFFFFF));
		
		//스크롤 색 변경 //
		jspPurchase.getVerticalScrollBar().setUI(new BasicScrollBarUI()
        {
			
            @Override
            protected void configureScrollBarColors()
            {
                this.thumbColor = new Color(0xFFCC66);
            }
            
        });
		
		//////////////////////////
		
		add(jpHistory);
		
		setBounds(100, 100, 565, 600);
		setVisible(true);
		setResizable(false);
		
		
	}//SaleList

	public JButton getJbtDelete() {
		return jbtDelete;
	}

	public JTable getJtPurchaseList() {
		return jtPurchaseList;
	}

	public DefaultTableModel getDtmPurchaseList() {
		return dtmPurchaseList;
	}

	

}//class
