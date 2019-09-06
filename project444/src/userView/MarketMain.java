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


	public class MarketMain extends JPanel {
		
		
		private DefaultComboBoxModel<String> dcbm, dcbm1;
		private JComboBox<String> jcbArea, jcbCategory;
		private JTextField jtfSearch;
		private JButton jbSearch, jbRecent, jbPrice, jbRefresh;
		private JRadioButton jrbSubject, jrbId;
		
		
		private DefaultTableModel dtmProductList;
		private JTable jtProductList;
		  
		  // 수정
		
		
		public MarketMain() {
//			super("글쓰기");

			
			
			
		//////////////////////// 홈 화면 //////////////////////////////
		
//		홈, 카테고리, 글쓰기, 채팅, MyPage
				
		
		//JComboBox
		String[] dataArea= {"지역전체","강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구",
				"노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구",
				"송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"};
		
		dcbm = new DefaultComboBoxModel<String>(dataArea);	
		jcbArea=new JComboBox<String>(dcbm);
		
		String[] dataCategory= {"카테고리전체", "디지털/가전", "가구/인테리어", 	"유아동/유아도서",
				"생활/가공식품", 	"여성의류", "여성잡화", "뷰티/미용", "남성패션/잡화",
				"스포츠/레저", "게임/취미", "도서/티켓/음반", "반려동물용품", "기타 중고물품"};
		
		dcbm1 = new DefaultComboBoxModel<String>(dataCategory);	
		jcbCategory=new JComboBox<String>(dcbm1);
		
		jtfSearch=new JTextField(10);
		jbSearch=new JButton("검색");
		
		JPanel panel1=new JPanel();
		panel1.add(jcbArea);
		panel1.add(jtfSearch);
		panel1.add(jbSearch);
		
		
		//JRadioButton (+ 기능 추가 해야함 : 둘 중 한개만 선택할 수 있도록 -> 이거 안되면 checkboxgroup으로 묶기)
		jrbSubject=new JRadioButton("제목");
		jrbId=new JRadioButton("아이디");
		
		JPanel panel2=new JPanel();
		panel2.add(jcbCategory);
		panel2.add(jrbSubject);
		panel2.add(jrbId);
		
		//JButton 
		jbRecent=new JButton("최신순");
		jbPrice=new JButton("가격순");
		jbRefresh=new JButton("새로고침");
		
		JPanel panel3=new JPanel();
		panel3.add(jbRecent);
		panel3.add(jbPrice);
		panel3.add(jbRefresh);
		
		
		//JTable
		
		String[] productColumn= {"이미지", "제품명", "지역", "가격", "시간" };
		
		dtmProductList=new DefaultTableModel(productColumn, 5);
		
		jtProductList=new JTable(dtmProductList);
		
		JScrollPane jspProductList=new JScrollPane(jtProductList);
		//리스트 크기, 이동, 편집 불가능하게 설정
		
		
		jtProductList.setRowHeight(30);
		jtProductList.getTableHeader().setReorderingAllowed(false);
		
		jtProductList.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtProductList.getColumnModel().getColumn(1).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(2).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(50);
		
//		setResizable(false);
		
		//이벤트 처리
		
		MarketMainEvt mme=new MarketMainEvt(this);
		
		jcbArea.addActionListener(mme);
		jcbCategory.addActionListener(mme);
		jtfSearch.addActionListener(mme);
		jbSearch.addActionListener(mme);
		jbRecent.addActionListener(mme);
		jbPrice.addActionListener(mme);
		jbRefresh.addActionListener(mme);
		jrbSubject.addActionListener(mme);
		jrbId.addActionListener(mme);			
	
		
		// 컴포넌트 배치 
		setLayout(null);
		
		panel1.setBounds(30, 30, 400, 40);
		panel2.setBounds(40, 70, 400, 40);
		panel3.setBounds(30, 110, 400, 40);
		jspProductList.setBounds(40, 190, 400, 200);
		
		add(panel1);
		add(panel2);
		add(panel3);
		add(jspProductList);
		
		setBounds(100, 100, 500, 600);
		
		setVisible(true);
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}//MarketMain

		public DefaultComboBoxModel<String> getDcbm() {
			return dcbm;
		}

		public DefaultComboBoxModel<String> getDcbm1() {
			return dcbm1;
		}

		public JComboBox<String> getJcbArea() {
			return jcbArea;
		}

		public JComboBox<String> getJcbCategory() {
			return jcbCategory;
		}

		public JTextField getJtfSearch() {
			return jtfSearch;
		}

		public JButton getJbSearch() {
			return jbSearch;
		}

		public JButton getJbRecent() {
			return jbRecent;
		}

		public JButton getJbPrice() {
			return jbPrice;
		}

		public JButton getJbRefresh() {
			return jbRefresh;
		}

		public JRadioButton getJrbSubject() {
			return jrbSubject;
		}

		public JRadioButton getJrbId() {
			return jrbId;
		}

		public DefaultTableModel getDtmProductList() {
			return dtmProductList;
		}

		public JTable getJtProductList() {
			return jtProductList;
		}
		
		
		

	}//MarketMain


