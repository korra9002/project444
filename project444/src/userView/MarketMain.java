package userView;


   import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
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

import userControl.MarketMainEvt;


   public class MarketMain extends JPanel {
      
      public static final int DOUBLE_CLICK=2;
      
      
      private DefaultComboBoxModel<String> dcbm, dcbm1;
      private JComboBox<String> jcbArea, jcbCategory;
      private JTextField jtfSearch;
      private JButton jbSearch, jbRecent, jbPrice, jbRefresh;
      private JRadioButton jrbSubject, jrbId;
      
      
      private DefaultTableModel dtmProductList;
      private JTable jtProductList;
      private String id; 
      
        // 수정
      
      
      @SuppressWarnings("serial")
      public MarketMain(String id) throws SQLException {
//         super("글쓰기");

         
      this.id=id;
         
      //////////////////////// 홈 화면 //////////////////////////////
      
//      홈, 카테고리, 글쓰기, 채팅, MyPage
            
      
      //JComboBox
      String[] dataArea= {"지역전체","강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구",
            "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구",
            "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"};
      
      dcbm = new DefaultComboBoxModel<String>(dataArea);   
      jcbArea=new JComboBox<String>(dcbm);
      
      String[] dataCategory= {"카테고리전체", "디지털/가전", "가구/인테리어",    "유아동/유아도서",
            "생활/가공식품",    "여성의류", "여성잡화", "뷰티/미용", "남성패션/잡화",
            "스포츠/레저", "게임/취미", "도서/티켓/음반", "반려동물용품", "기타 중고물품"};
      
      dcbm1 = new DefaultComboBoxModel<String>(dataCategory);   
      jcbCategory=new JComboBox<String>(dcbm1);
      
      jtfSearch=new JTextField(10);
      jbSearch=new JButton("검색");

      
      
      //JRadioButton
      jrbSubject=new JRadioButton("제목", true);
      jrbId=new JRadioButton("아이디");
      ButtonGroup bg=new ButtonGroup(); //두개 동시에 선택할 수 없도록
      
      bg.add(jrbSubject);
      bg.add(jrbId);

      
      //JButton 
      jbRecent=new JButton("최신순");
      jbPrice=new JButton("가격순");
      jbRefresh=new JButton("새로고침");

      
      //JTable
      
      String[] productColumn= {"이미지", "제품명", "지역", "가격", "시간", "카테고리", "판매자 ID" };
      
      dtmProductList=new DefaultTableModel(productColumn, 7) {
    	  
    	  @Override
			public Class<?> getColumnClass(int column) {
				// 입력된 행 하나의 모든 컬럼의 값을 원래의 클래스로 반환하는 일
				// 0행 현재 입력된 행 하나만 대상으로 처리
				return getValueAt(0, column).getClass();
			}

            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            };
      
      };
      
      jtProductList=new JTable(dtmProductList);
            
            
      
      JScrollPane jspProductList=new JScrollPane(jtProductList);

      
//      
//      @Override
//      public boolean isCellEditable(String row, int column) {
//         return false;
//      }//isCellEditable
//   };
//   jtProductList = new JTable(dtmProductList) {
//      @Override
//      public Class<?> getColumnClass(int column) {
//         return getValueAt(0, column).getClass();
//      }//getColumnClass
//   };
      
      
      
      
      
      //리스트 크기, 이동, 편집 불가능하게 설정
      
      
      jtProductList.setRowHeight(100);
      jtProductList.getTableHeader().setReorderingAllowed(false);
      
      jtProductList.getColumnModel().getColumn(0).setPreferredWidth(50);
      jtProductList.getColumnModel().getColumn(1).setPreferredWidth(30);
      jtProductList.getColumnModel().getColumn(2).setPreferredWidth(30);
      jtProductList.getColumnModel().getColumn(3).setPreferredWidth(30);
      jtProductList.getColumnModel().getColumn(4).setPreferredWidth(50);
      jtProductList.getColumnModel().getColumn(5).setPreferredWidth(50);
      jtProductList.getColumnModel().getColumn(6).setPreferredWidth(30);
      
//      setResizable(false);
   
      System.out.println("gdged");
      
      // 컴포넌트 배치 
      setLayout(null);
      
      add(jspProductList);
      add(jcbArea);
      add(jtfSearch);
      add(jbSearch);
      
      add(jcbCategory);
      add(jrbSubject);
      add(jrbId);
      
      
      add(jbRecent);
      add(jbPrice);
      add(jbRefresh);
            
      
      jspProductList.setBounds(20, 180, 760, 600);
      jcbArea.setBounds(20, 20, 180, 40);
      jcbCategory.setBounds(20, 80, 180, 40);
      jtfSearch.setBounds(220, 20, 360, 35);
      jbSearch.setBounds(600, 20, 180, 35);
      jrbSubject.setBounds(310, 60, 90, 35);
      jrbId.setBounds(440, 60, 90, 35);
      jbRecent.setBounds(490, 130, 90, 35);
      jbPrice.setBounds(590, 130, 90, 35);
      jbRefresh.setBounds(690, 130, 90, 35);
      setBounds(100, 100, 750, 750);
      
      //윈도우 가시화
      setVisible(true);
      
      
      
      
   //이벤트 처리
      
      MarketMainEvt mme=new MarketMainEvt(this, id,null);
      
      jcbArea.addActionListener(mme);
      jcbCategory.addActionListener(mme);
      jtfSearch.addActionListener(mme);
      jbSearch.addActionListener(mme);
      jbRecent.addActionListener(mme);
      jbPrice.addActionListener(mme);
      jbRefresh.addActionListener(mme);
      jrbSubject.addActionListener(mme);
      jrbId.addActionListener(mme);   
      jtProductList.addMouseListener(mme);
      
   
      
      
      
//      jtProductList.addMouseListener(new MouseAdapter() {
//         //테이블 더블클릭되었을 때 
//         @Override
//         public void mouseClicked(MouseEvent me) {
//            switch(me.getClickCount()) {
//            case DOUBLE_CLICK : 
//               
//            int selectedRow=jtProductList.getSelectedRow();
//            
//            new MarketDetailBuyer();
//               
//            }
//             
      
//         }//mouseClicked
//         
//         
//      });
   
//      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
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

