package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
 
import userDAO.UserDAO;
import userVO.AllListVO;
import userView.MarketDetailBuyer;
import userView.MarketMain;

public class MarketMainEvt extends MouseAdapter implements ActionListener{
	private MarketMain mm;
	private JTable jtProductList;
	public static final int DOUBLE_CLICK=2;
	
	public MarketMainEvt(MarketMain mm) throws SQLException {
		this.mm=mm;	
		this.jtProductList=mm.getJtProductList();
		setAllList();
	}//MarketMainEvt
	
//	public void setAllList() throws SQLException {
//		DefaultTableModel dtm=mm.getDtmProductList();
//		
//		//JTable의 레코드 초기화
//		dtm.setRowCount(0);
//		
//		Object[] rowData= null;
//		//JTable 넣을 데이터 // object는 자바의 모든 값을 다 담을 수 있다.
//		
//		//DBMS에서 조회
//		UserDAO uDAO =UserDAO.getInstance();
//			List<AllListVO> list=uDAO.selectAllList();
//				if(list.isEmpty()) { 
//					JOptionPane.showMessageDialog(mm, "검색조건에 맞는 상품이 없습니다.");
//				}
//			AllListVO alv=null;
//			for(int i=0; i<list.size(); i++) {
//				alv=list.get(i);
//				//조회 결과로 JTable 레코드에 들어갈 데이터를 생성하고
//				rowData=new Object[7];
//				//배열에 값 할당 		String[] productColumn= {"이미지", "제품명", "지역", "가격", "시간", "카테고리", "판매자 ID" };
//				rowData[0]=alv.getImage();
//				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
//				rowData[2]=alv.getLoc_code();
//				rowData[3]=alv.getPrice();
//				rowData[4]=alv.getUpload_date();
//				rowData[5]=alv.getCategory();
//				rowData[6]=alv.getSellerID();
//			//dtm에 추가
//			dtm.addRow(rowData);		
//		}//end for		
//	}//setAllList
	
		
		
		public void setAllList() throws SQLException {
			
//			if (jcbAreaIndex==0 && jcbCateIndex==0 && jtfText==null) {
//				setAllList();
//			}//end if
			DefaultTableModel dtm=mm.getDtmProductList();
			
			//JTable의 레코드 초기화
			dtm.setRowCount(0);
			
			Object[] rowData= null;
			//JTable 넣을 데이터 // object는 자바의 모든 값을 다 담을 수 있다.
		
//			int area =0;
//			try {
//			area =  mm.getJcbArea().getSelectedIndex();
//			}catch (Exception e) {
//           e.printStackTrace();
// 	 	
//			}	
//			int category =0;
//			try {
//				category =  mm.getJcbCategory().getSelectedIndex();
//			}catch (Exception e) {
//				e.printStackTrace();
//				
//			}	
//			
//			String proName ="";
//			try {
//				proName =  mm.getJtfSearch().getText().trim();
//			}catch (Exception e) {
//				e.printStackTrace();
//				
//			}	
			
			//DBMS에서 조회
			UserDAO uDAO =UserDAO.getInstance();
		List<AllListVO> list=uDAO.selectAllList( mm.getJcbArea().getSelectedIndex(), mm.getJcbCategory().getSelectedIndex(),mm.getJtfSearch().getText().trim());
		//ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠgetselectedText아니고 getText()입니다ㅠㅠㅠㅠㅠㅠㅠ머리에 넣읍시다ㅠ
	//		List<AllListVO> list=uDAO.selectAllList(0, 0,"");
			if(list.isEmpty()) { 
				JOptionPane.showMessageDialog(mm, "검색조건에 맞는 상품이 없습니다.");
			}
			AllListVO alv=null;
			for(int i=0; i<list.size(); i++) {
				alv=list.get(i);
				//조회 결과로 JTable 레코드에 들어갈 데이터를 생성하고
				rowData=new Object[7];
				//배열에 값 할당
				rowData[0]=alv.getImage();
				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
				rowData[2]=alv.getLoc_code();
				rowData[3]=alv.getPrice();
				rowData[4]=alv.getUpload_date();
				rowData[5]=alv.getCategory();
				rowData[6]=alv.getSellerID();
				//dtm에 추가
				dtm.addRow(rowData);		
			}//end for		
		}//setArea
		
		public void productDetail() {
	
//			//선택한 행의 도시락 코드를 가져와서 상세 정보를 조회
//			
//			JTable jtLunch=lu.getJtLunch();
//			String temp=(String)jtLunch.getValueAt(jtLunch.getSelectedRow(), 2);
//			String code=temp.substring(temp.lastIndexOf("(")+1, temp.lastIndexOf(")"));
//			
//			LunchUserDAO luDAO=LunchUserDAO.getInstance();
//			
//			try {
//				LunchDetailVO ldVO=luDAO.selectLunchDetail(code);
//				new LunchDetail(lu, ldVO); 
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} //try catch해줘야함.
//			
//			System.out.println( code );
//			
////			내가 잘못했었던 것.		
////			String lunchCode=lu.getJtLunch().getSelectedRow()
////			System.out.println(loVO.getLunchCode());
			
			
		}//end if
		
	

	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mm.getJcbArea() || ae.getSource() == mm.getJcbCategory() || ae.getSource() == mm.getJbSearch()) {
			try {
				setAllList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//end if

		if (ae.getSource() == mm.getJbRecent()) {
//			new SignUp();
		}//end if
//		if (ae.getSource() == mm.getJbPrice()) {
//			new SignUp();
//		}//end if
		if (ae.getSource() == mm.getJbRefresh()) {			
			try {
				setAllList();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end if
		
	}//actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getClickCount() == 2) {//더블클릭
			if(me.getSource() == mm.getJtProductList()) {
				productDetail();
			}//end if			
		}//end if
		
//		switch(me.getClickCount()) {
//		case DOUBLE_CLICK : 
//			
////		int selectedRow=jtProductList.getSelectedRow();
//		
//		new MarketDetailBuyer(mm);
//			
//		}	
	
	}

}//class
