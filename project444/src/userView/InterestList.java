package userView;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import userControl.InterestListEvt;
import userRun.RunMarketMain;

@SuppressWarnings("serial")
public class InterestList extends JDialog {
	
	private JButton jbtDelete;
	private JTable jtInterest;
	private DefaultTableModel dtmInterest; 
	
	public InterestList(RunMarketMain rmm) {
		super(rmm,"관심목록");
		String[] sellCol= {"이미지","제품명","가격","아이디","지역","시간","카테고리"};
		Object[][] sellRow = {
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7},
				{1,2,3,4,5,6,7}
				
				};
		
		dtmInterest = new DefaultTableModel(sellCol,7);
		jtInterest = new JTable(dtmInterest){//셀 내용 수정 금지
			
			@Override
			public Class<?> getColumnClass(int column) {
				// 입력된 행 하나의 모든 컬럼의 값을 원래의 클래스로 반환하는 일
				// 0행 현재 입력된 행 하나만 대상으로 처리
				return getValueAt(0, column).getClass();
			}
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		}; 
		
		JScrollPane jspInterest = new JScrollPane(jtInterest);
		
		//////////////테이블 데이터 가운데 정렬//////////////
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//테이블 데이터 가운데 정렬을 하기 위해
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);//가운데 정렬 세팅
		TableColumnModel tcm = jtInterest.getColumnModel();//정렬할 테이블의 컬럼모델을 가져옴
		
		for (int i = 1; i < tcm.getColumnCount(); i++) {//컬럼의 수만큼 반복하여 가운데정렬함
			tcm.getColumn(i).setCellRenderer(dtcr);
		}//end for
		
		//////////////테이블 데이터 가운데 정렬//////////////
		////////테이블 크기설정////////
		jtInterest.getTableHeader().setReorderingAllowed(false);//테이블 컬럼 위치 변경 금지
		
		jtInterest.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtInterest.getColumnModel().getColumn(0).setResizable(false);//테이블 컬럼 사이즈 변경 금지
		jtInterest.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtInterest.getColumnModel().getColumn(1).setResizable(false);
		jtInterest.getColumnModel().getColumn(2).setPreferredWidth(50);
		jtInterest.getColumnModel().getColumn(2).setResizable(false);
		jtInterest.getColumnModel().getColumn(3).setPreferredWidth(75);
		jtInterest.getColumnModel().getColumn(3).setResizable(false);
		jtInterest.getColumnModel().getColumn(4).setPreferredWidth(50);
		jtInterest.getColumnModel().getColumn(4).setResizable(false);
		jtInterest.getColumnModel().getColumn(5).setPreferredWidth(80);
		jtInterest.getColumnModel().getColumn(5).setResizable(false);
		
		jtInterest.setRowHeight(100);
		
		////////테이블 크기설정 끝////////
		
		jbtDelete = new JButton("삭제");
		
		JPanel jpInterestList = new JPanel(null);
		jbtDelete.setBounds(450, 15, 80, 30);
		jspInterest.setBounds(10, 60, 540, 480);
		
		jpInterestList.add(jbtDelete);
		jpInterestList.add(jspInterest);
		
		/////////////////////////////////첫번째 탭 끝/////////////////////////////////
		add(jpInterestList);
		//////////////////////수정사항//////////////////////
		InterestListEvt ile = new InterestListEvt(this);
		 jbtDelete.addActionListener(ile);
		 jtInterest.addMouseListener(ile);
		 
		/////////////////////////////////////////////////
		 
	////// 탭, 색 디자인 //////////////
			Container c = getContentPane();
			c.setBackground(new Color(0xf6f2ef));
		 	setBackground(new Color(0xFFCC66));
			jbtDelete.setBackground(new Color(0xFFCC66));
			jbtDelete.setBackground(new Color(0xFFCC66));
			jtInterest.getTableHeader().setBackground(new Color(0xFFCC66));
			jspInterest.getVerticalScrollBar().setBackground(new Color(0xFFFFFF));
			
			//스크롤 색 변경 //
			jspInterest.getVerticalScrollBar().setUI(new BasicScrollBarUI()
	        {
				
	            @Override
	            protected void configureScrollBarColors()
	            {
	                this.thumbColor = new Color(0xFFCC66);
	            }
	            
	        });
			
			//////////////////////////
		 
		 
		 
		setBounds(100, 100, 565, 600);
		setVisible(true);
		setResizable(false);
		
	}//SaleList

	public JButton getJbtDelete() {
		return jbtDelete;
	}//getJbtDelete

	public JTable getJtInterest() {
		return jtInterest;
	}//getJtInterest

	public DefaultTableModel getDtmInterest() {
		return dtmInterest;
	}//getDtmInterest


}//class
