package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

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
		

		
//		userDAO uDAO= userDAO.geti
		
	}//setAllList
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mm.getJcbArea()) {
			new SignUp();
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
			new SignUp();
		}//end if
		
	}//actionPerformed

}//class
