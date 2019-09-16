package adminView;

import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import adminControl.AdminMainEvt;

@SuppressWarnings("serial")
public class AdminMainView extends JFrame {
	
	private JTabbedPane jtp;
	private JButton jbtSearch1, jbtRefresh1, jbtRecent1, jbtReset1, jbtReset2, jbtReset3, jbtSearch2, jbtRefresh2, jbtRecent2, 
		jbtSearch3, jbtRefresh3;
	private JTable jtCheckList, jtProductList, jtUserList;
	private DefaultTableModel dtmCheckList, dtmProductList, dtmUserList;
	private JComboBox<String> jcbCategory1, jcbCategory2, jcbCategory3;
	private DefaultComboBoxModel<String> dcbCategory1, dcbCategory2, dcbCategory3;
	private JTextField jtfSearch1, jtfSearch2, jtfSearch3;
	private JRadioButton jrbID1, jrbSubject1, jrbID2, jrbSubject2;
	private JCheckBox onSale, deleteNComplete ;
	private String[] categoryList1, categoryList2;
	
	public AdminMainView() {
		super("관리자");
		////////////////////////////////첫번째 탭 시작////////////////////////////////
		categoryList1= new String[]{"카테고리 선택","디지털/가전","가구/인테리어","유아동/유아도서","생활/가공식품","여성의류","여성잡화",
				"뷰티/미용","남성패션/잡화","스포츠/레저","게임/취미","도서/티켓/음반","반려동물용품","기타 중고물품"};

		dcbCategory1 = new DefaultComboBoxModel<String>(categoryList1);
		jcbCategory1 = new JComboBox<String>(dcbCategory1);
		
		jtfSearch1 = new JTextField();
		
		jrbSubject1 = new JRadioButton("제품명", true);
		jrbID1 = new JRadioButton("아이디");
		ButtonGroup bg = new ButtonGroup();//두개 다 선택되는 것을 방지
		bg.add(jrbSubject1);
		bg.add(jrbID1);
		
		jbtSearch1 = new JButton("검색");
		jbtRefresh1 = new JButton("새로고침");
		jbtRecent1 = new JButton("최신순");
		jbtReset1 = new JButton("초기화");
		
		String[] checkCol= {"상품코드", "이미지", "아이디", "카테고리", "제품명", "가격", "올린일자"};
		Object[][] checkRow = {
				{"","","","","","",""}
				};
		
		dtmCheckList = new DefaultTableModel(checkRow, checkCol){//셀 내용 수정 금지
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtCheckList = new JTable(dtmCheckList) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
		};
		
		JScrollPane jspCheckList = new JScrollPane(jtCheckList);
		
		//////////////테이블 데이터 가운데 정렬 시작//////////////
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//테이블 데이터 가운데 정렬을 하기 위해
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);//가운데 정렬 세팅
		TableColumnModel tcm = jtCheckList.getColumnModel();//정렬할 테이블의 컬럼모델을 가져옴
		
		for (int i = 0; i < tcm.getColumnCount(); i++) {//컬럼의 수만큼 반복하여 가운데정렬함
			tcm.getColumn(i).setCellRenderer(dtcr);
		}//end for
		//////////////테이블 데이터 가운데 정렬 끝//////////////
		
		JTableHeader jh1 = jtCheckList.getTableHeader();
		jh1.setPreferredSize(new Dimension(100,40));
		
		////////테이블 크기설정 시작////////
		jtCheckList.getTableHeader().setReorderingAllowed(false);//테이블 컬럼 위치 변경 금지
		
		jtCheckList.getColumnModel().getColumn(0).setPreferredWidth(70);
		jtCheckList.getColumnModel().getColumn(0).setResizable(false);//테이블 컬럼 사이즈 변경 금지
		jtCheckList.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtCheckList.getColumnModel().getColumn(1).setResizable(false);
		jtCheckList.getColumnModel().getColumn(2).setPreferredWidth(90);
		jtCheckList.getColumnModel().getColumn(2).setResizable(false);
		jtCheckList.getColumnModel().getColumn(3).setPreferredWidth(90);
		jtCheckList.getColumnModel().getColumn(3).setResizable(false);
		jtCheckList.getColumnModel().getColumn(4).setPreferredWidth(90);
		jtCheckList.getColumnModel().getColumn(4).setResizable(false);
		jtCheckList.getColumnModel().getColumn(5).setPreferredWidth(70);
		jtCheckList.getColumnModel().getColumn(5).setResizable(false);;
		jtCheckList.getColumnModel().getColumn(6).setPreferredWidth(70);
		jtCheckList.getColumnModel().getColumn(6).setResizable(false);
		
		jtCheckList.setRowHeight(100);
		////////테이블 크기설정 끝////////
		
		JPanel jpCheckList = new JPanel(null);
		
		jspCheckList.setBounds(10, 10, 670, 500);
		jbtRefresh1.setBounds(570, 540, 90, 30);
		jbtReset1.setBounds(570, 590, 90, 30);
		jbtRecent1.setBounds(50, 580, 90, 30);
		jcbCategory1.setBounds(50, 530, 120, 30);
		jtfSearch1.setBounds(230, 565, 150, 30);
		jbtSearch1.setBounds(410, 565, 90, 30);
		jrbSubject1.setBounds(230, 530, 70, 30);
		jrbID1.setBounds(300, 530, 70, 30);
		
		jpCheckList.add(jspCheckList);
		jpCheckList.add(jbtRecent1);
		jpCheckList.add(jbtReset1);
		jpCheckList.add(jbtRefresh1);
		jpCheckList.add(jcbCategory1);
		jpCheckList.add(jtfSearch1);
		jpCheckList.add(jbtSearch1);
		jpCheckList.add(jrbSubject1);
		jpCheckList.add(jrbID1);
		
		/////////////////////////////////첫번째 탭 끝////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		////////////////////////////////두번째 탭 시작//////////////////////////////////////////////////////////////////////////////////////////////////////
		categoryList2= new String[]{"카테고리 선택","디지털/가전","가구/인테리어","유아동/유아도서","생활/가공식품","여성의류","여성잡화",
				"뷰티/미용","남성패션/잡화","스포츠/레저","게임/취미","도서/티켓/음반","반려동물용품","기타 중고물품"};

		dcbCategory2 = new DefaultComboBoxModel<String>(categoryList2);
		jcbCategory2 = new JComboBox<String>(dcbCategory2);
		
		onSale = new JCheckBox("판매중", true);
		deleteNComplete = new JCheckBox("<html>판매완료/<br>삭제", true);
		
		jtfSearch2 = new JTextField();
		
		jrbSubject2 = new JRadioButton("제품명", true);
		jrbID2 = new JRadioButton("아이디");
		ButtonGroup bg2 = new ButtonGroup();//두개 다 선택되는 것을 방지
		bg2.add(jrbSubject2);
		bg2.add(jrbID2);
		
		jbtSearch2 = new JButton("검색");
		jbtRefresh2 = new JButton("새로고침");
		jbtRecent2 = new JButton("최신순");
		jbtReset2 = new JButton("초기화");
		
		String[] productCol= {"상품코드","이미지","아이디","카테고리","제품명","가격","올린일자","<html>판매여부/<br/>삭제여부"};
		Object[][] productRow = {
				{"","","","","","",""},
				};
		
		dtmProductList = new DefaultTableModel(productRow, productCol) {//셀 내용 수정 금지
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtProductList = new JTable(dtmProductList) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
		};
		
		JScrollPane jspProductList = new JScrollPane(jtProductList);

		//////////////테이블 데이터 가운데 정렬 시작//////////////
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();//테이블 데이터 가운데 정렬을 하기 위해
		dtcr2.setHorizontalAlignment(SwingConstants.CENTER);//가운데 정렬 세팅
		TableColumnModel tcm2 = jtProductList.getColumnModel();//정렬할 테이블의 컬럼모델을 가져옴
		
		for (int i = 0; i < tcm2.getColumnCount(); i++) {//컬럼의 수만큼 반복하여 가운데정렬함
			tcm2.getColumn(i).setCellRenderer(dtcr2);
		}//end for
		//////////////테이블 데이터 가운데 정렬 끝//////////////
		
		JTableHeader jh2 = jtProductList.getTableHeader();
		jh2.setPreferredSize(new Dimension(100,40));
		
		
		////////테이블 크기설정 시작////////
		jtProductList.getTableHeader().setReorderingAllowed(false);//테이블 컬럼 위치 변경 금지
		
		jtProductList.getColumnModel().getColumn(0).setPreferredWidth(70);
		jtProductList.getColumnModel().getColumn(0).setResizable(false);//테이블 컬럼 사이즈 변경 금지
		jtProductList.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtProductList.getColumnModel().getColumn(1).setResizable(false);
		jtProductList.getColumnModel().getColumn(2).setPreferredWidth(90);
		jtProductList.getColumnModel().getColumn(2).setResizable(false);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(90);
		jtProductList.getColumnModel().getColumn(3).setResizable(false);
		jtProductList.getColumnModel().getColumn(4).setPreferredWidth(90);
		jtProductList.getColumnModel().getColumn(4).setResizable(false);
		jtProductList.getColumnModel().getColumn(5).setPreferredWidth(70);
		jtProductList.getColumnModel().getColumn(5).setResizable(false);;
		jtProductList.getColumnModel().getColumn(6).setPreferredWidth(70);
		jtProductList.getColumnModel().getColumn(6).setResizable(false);
		jtProductList.getColumnModel().getColumn(7).setPreferredWidth(70);
		jtProductList.getColumnModel().getColumn(7).setResizable(false);
		
		jtProductList.setRowHeight(100);
		////////테이블 크기설정 끝////////

		JPanel jpProductList = new JPanel(null);
		
		jspProductList.setBounds(10, 10, 670, 500);
		jcbCategory2.setBounds(50, 530, 120, 30);
		jbtRecent2.setBounds(50, 580, 90, 30);
		jrbSubject2.setBounds(230, 530, 70, 30);
		jrbID2.setBounds(300, 530, 70, 30);
		jtfSearch2.setBounds(230, 565, 150, 30);
		jbtSearch2.setBounds(410, 565, 90, 30);
		onSale.setBounds(520, 530, 80, 30);
		deleteNComplete.setBounds(610, 530, 90, 40);
		jbtRefresh2.setBounds(510, 590, 90, 30);
		jbtReset2.setBounds(610, 590, 90, 30);
		
		jpProductList.add(jspProductList);
		jpProductList.add(jbtRecent2);
		jpProductList.add(jbtRefresh2);
		jpProductList.add(jbtReset2);
		jpProductList.add(jcbCategory2);
		jpProductList.add(jtfSearch2);
		jpProductList.add(jbtSearch2);
		jpProductList.add(jrbSubject2);
		jpProductList.add(jrbID2);
		jpProductList.add(onSale);
		jpProductList.add(deleteNComplete);
		/////////////////////////////////두번째 탭 끝//////////////////////////////////////////////////////////////////////////////////////////////
		
		
		////////////////////////////////세번째 탭 시작//////////////////////////////////////////////////////////////////////////////////////////////
		String[] searchList= {"전체","아이디","이름","전화번호","이메일"};

		dcbCategory3 = new DefaultComboBoxModel<String>(searchList);
		jcbCategory3 = new JComboBox<String>(dcbCategory3);
		
		jtfSearch3 = new JTextField();
		
		jbtSearch3 = new JButton("검색");
		jbtRefresh3 = new JButton("새로고침");
		jbtReset3 = new JButton("초기화");
		JLabel jlbId = new JLabel("아이디");
		
		String[] idCol= {"아이디","이름","성별","전화번호","지역","가입일자","정지여부"};
		Object[][] idRow = {
				{"","","","","","",""},
				};
		
		dtmUserList = new DefaultTableModel(idRow, idCol) {//셀 내용 수정 금지
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtUserList = new JTable(dtmUserList) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
		};
		
		JScrollPane jspUserList = new JScrollPane(jtUserList);
		
		//////////////테이블 데이터 가운데 정렬 시작//////////////
		DefaultTableCellRenderer dtcr3 = new DefaultTableCellRenderer();//테이블 데이터 가운데 정렬을 하기 위해
		dtcr3.setHorizontalAlignment(SwingConstants.CENTER);//가운데 정렬 세팅
		TableColumnModel tcm3 = jtUserList.getColumnModel();//정렬할 테이블의 컬럼모델을 가져옴
		
		for (int i = 0; i < tcm3.getColumnCount(); i++) {//컬럼의 수만큼 반복하여 가운데정렬함
			tcm3.getColumn(i).setCellRenderer(dtcr3);
		}//end for
		//////////////테이블 데이터 가운데 정렬 끝//////////////
		
		JTableHeader jh3 = jtUserList.getTableHeader();
		jh3.setPreferredSize(new Dimension(100,40));
		
		////////테이블 크기설정 시작////////
		jtUserList.getTableHeader().setReorderingAllowed(false);//테이블 컬럼 위치 변경 금지
		
		jtUserList.getColumnModel().getColumn(0).setPreferredWidth(70);
		jtUserList.getColumnModel().getColumn(0).setResizable(false);;//테이블 컬럼 사이즈 변경 금지
		jtUserList.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtUserList.getColumnModel().getColumn(1).setResizable(false);;
		jtUserList.getColumnModel().getColumn(2).setPreferredWidth(90);
		jtUserList.getColumnModel().getColumn(2).setResizable(false);;
		jtUserList.getColumnModel().getColumn(3).setPreferredWidth(90);
		jtUserList.getColumnModel().getColumn(3).setResizable(false);;
		jtUserList.getColumnModel().getColumn(4).setPreferredWidth(90);
		jtUserList.getColumnModel().getColumn(4).setResizable(false);;
		jtUserList.getColumnModel().getColumn(5).setPreferredWidth(70);
		jtUserList.getColumnModel().getColumn(5).setResizable(false);;
		jtUserList.getColumnModel().getColumn(6).setPreferredWidth(70);
		jtUserList.getColumnModel().getColumn(6).setResizable(false);;
		
		jtUserList.setRowHeight(100);
		////////테이블 크기설정 끝////////
		
		JPanel jpUserList = new JPanel(null);
		
		jspUserList.setBounds(10, 10, 670, 500);
//		jcbCategory3.setBounds(50, 550, 120, 30);
		jbtRefresh3.setBounds(570, 530, 90, 30);
		jbtReset3.setBounds(570, 570, 90, 30);
		jtfSearch3.setBounds(230, 550, 150, 30);
		jbtSearch3.setBounds(410, 550, 90, 30);
		jlbId.setBounds(150, 550, 90, 30);
		
		jpUserList.add(jspUserList);
		jpUserList.add(jcbCategory3);
		jpUserList.add(jbtRefresh3);
		jpUserList.add(jtfSearch3);
		jpUserList.add(jbtSearch3);
		jpUserList.add(jbtReset3);
		jpUserList.add(jlbId);
		/////////////////////////////////세번째 탭 끝/////////////////////////////////
		
		jtp = new JTabbedPane();
		
		jtp.add("검수목록",jpCheckList);
		jtp.add("제품목록",jpProductList);
		jtp.add("아이디목록",jpUserList);
		add(jtp);
		
		///////////////////////////////이벤트처리 시작////////////////////////////////
		AdminMainEvt ame = new AdminMainEvt(this);
		jtp.addMouseListener(ame);
		jtCheckList.addMouseListener(ame);
		jtProductList.addMouseListener(ame);
		jtUserList.addMouseListener(ame);
		
		jbtRecent1.addActionListener(ame);
		jbtRecent2.addActionListener(ame);
		jbtRefresh1.addActionListener(ame);
		jbtRefresh2.addActionListener(ame);
		jbtRefresh3.addActionListener(ame);
		jbtSearch1.addActionListener(ame);
		jbtSearch2.addActionListener(ame);
		jbtSearch3.addActionListener(ame);
		jbtReset1.addActionListener(ame);
		jbtReset2.addActionListener(ame);
		jbtReset3.addActionListener(ame);
		
		jcbCategory1.addActionListener(ame);
		jcbCategory2.addActionListener(ame);
		jcbCategory3.addActionListener(ame);
		
		///////////////////////////////이벤트처리 끝////////////////////////////////
		
		
		
		setBounds(100, 100, 720, 700);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//SaleList
	
	public JTabbedPane getJtp() {
		return jtp;
	}

	public JButton getJbtSearch1() {
		return jbtSearch1;
	}

	public JButton getJbtRefresh1() {
		return jbtRefresh1;
	}

	public JButton getJbtRecent1() {
		return jbtRecent1;
	}

	public JButton getJbtSearch2() {
		return jbtSearch2;
	}

	public JButton getJbtRefresh2() {
		return jbtRefresh2;
	}

	public JButton getJbtRecent2() {
		return jbtRecent2;
	}

	public JButton getJbtSearch3() {
		return jbtSearch3;
	}

	public JButton getJbtRefresh3() {
		return jbtRefresh3;
	}

	public JTable getJtCheckList() {
		return jtCheckList;
	}

	public JTable getJtProductList() {
		return jtProductList;
	}

	public JTable getJtUserList() {
		return jtUserList;
	}

	public DefaultTableModel getDtmCheckList() {
		return dtmCheckList;
	}

	public DefaultTableModel getDtmProductList() {
		return dtmProductList;
	}

	public DefaultTableModel getDtmUserList() {
		return dtmUserList;
	}

	public JComboBox<String> getJcbCategory1() {
		return jcbCategory1;
	}

	public JComboBox<String> getJcbCategory2() {
		return jcbCategory2;
	}

	public JComboBox<String> getJcbCategory3() {
		return jcbCategory3;
	}

	public DefaultComboBoxModel<String> getDcbCategory1() {
		return dcbCategory1;
	}

	public DefaultComboBoxModel<String> getDcbCategory2() {
		return dcbCategory2;
	}

	public DefaultComboBoxModel<String> getDcbCategory3() {
		return dcbCategory3;
	}

	public JTextField getJtfSearch1() {
		return jtfSearch1;
	}
	public JButton getJbtReset1() {
		return jbtReset1;
	}

	public JButton getJbtReset2() {
		return jbtReset2;
	}
	
	public JButton getJbtReset3() {
		return jbtReset3;
	}
	
	public JTextField getJtfSearch2() {
		return jtfSearch2;
	}

	public JTextField getJtfSearch3() {
		return jtfSearch3;
	}

	public JRadioButton getJrbID1() {
		return jrbID1;
	}

	public JRadioButton getJrbSubject1() {
		return jrbSubject1;
	}

	public JRadioButton getJrbID2() {
		return jrbID2;
	}

	public JRadioButton getJrbSubject2() {
		return jrbSubject2;
	}

	public JCheckBox getOnSale() {
		return onSale;
	}

	public JCheckBox getDeleteNComplete() {
		return deleteNComplete;
	}

	public String[] getCategoryList1() {
		return categoryList1;
	}

	public String[] getCategoryList2() {
		return categoryList2;
	}
	
//	public static void main(String[] args) {
//		new AdminMainView();
//	}//main

}//class
