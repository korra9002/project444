package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import userDAO.UserDAO;
import userVO.AllListVO;
import userView.MarketMain;
import userView.SignUp;

public class MarketMainEvt implements ActionListener{
	private MarketMain mm;
	
	public MarketMainEvt(MarketMain mm) throws SQLException {
		this.mm=mm;		
		setAllList();
	}//MarketMainEvt
	
	public void setAllList() throws SQLException {
		DefaultTableModel dtm=mm.getDtmProductList();
		
		//JTable의 레코드 초기화
		dtm.setRowCount(0);
		
		Object[] rowData= null;
		//JTable 넣을 데이터 // object는 자바의 모든 값을 다 담을 수 있다.
		
		//DBMS에서 조회
		UserDAO uDAO =UserDAO.getInstance();
			List<AllListVO> list=uDAO.selectAllList();
				if(list.isEmpty()) { 
					JOptionPane.showMessageDialog(mm, "검색조건에 맞는 상품이 없습니다.");
				}
			AllListVO alv=null;
			for(int i=0; i<list.size(); i++) {
				alv=list.get(i);
				//조회 결과로 JTable 레코드에 들어갈 데이터를 생성하고
				rowData=new Object[5];
				//배열에 값 할당
				rowData[0]=alv.getImage();
				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
				rowData[2]=alv.getLoc_code();
				rowData[3]=alv.getPrice();
				rowData[4]=alv.getUpload_date();
			//dtm에 추가
			dtm.addRow(rowData);		
		}//end for		
	}//setAllList
	
		
		
		public void setArea(int jcbindex) throws SQLException {
			DefaultTableModel dtm=mm.getDtmProductList();
			
			//JTable의 레코드 초기화
			dtm.setRowCount(0);
			
			Object[] rowData= null;
			//JTable 넣을 데이터 // object는 자바의 모든 값을 다 담을 수 있다.
			
			//DBMS에서 조회
			UserDAO uDAO =UserDAO.getInstance();
			List<AllListVO> list=uDAO.selectAreaList(jcbindex);
			if(list.isEmpty()) { 
				JOptionPane.showMessageDialog(mm, "검색조건에 맞는 상품이 없습니다.");
			}
			AllListVO alv=null;
			for(int i=0; i<list.size(); i++) {
				alv=list.get(i);
				//조회 결과로 JTable 레코드에 들어갈 데이터를 생성하고
				rowData=new Object[5];
				//배열에 값 할당
				rowData[0]=alv.getImage();
				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
				rowData[2]=alv.getLoc_code();
				rowData[3]=alv.getPrice();
				rowData[4]=alv.getUpload_date();
				//dtm에 추가
				dtm.addRow(rowData);		
			}//end for		
		}//setArea
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mm.getJcbArea()) {
			try {
				setArea(mm.getJcbArea().getSelectedIndex());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//end if
		if (ae.getSource() == mm.getJbSearch()) {
			new SignUp();
		}//end if
		if (ae.getSource() == mm.getJcbCategory()) {
			new SignUp();
		}//end if
		if (ae.getSource() == mm.getJbRecent()) {
			new SignUp();
		}//end if
		if (ae.getSource() == mm.getJbPrice()) {
			new SignUp();
		}//end if
		if (ae.getSource() == mm.getJbRefresh()) {
			try {
				setAllList();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end if
		
	}//actionPerformed

}//class
