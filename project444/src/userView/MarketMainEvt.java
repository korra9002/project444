package userView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class MarketMainEvt implements ActionListener{
	private MarketMain mm;
	
	public MarketMainEvt(MarketMain mm) throws SQLException {
		this.mm=mm;		
		setAllList();
	}//MarketMainEvt
	
	public void setAllList() throws SQLException {
		DefaultTableModel dtm=mm.getDtmProductList();
		
		//JTable�� ���ڵ� �ʱ�ȭ
		dtm.setRowCount(0);
		
		Object[] rowData= null;
		//JTable ���� ������ // object�� �ڹ��� ��� ���� �� ���� �� �ִ�.
		
		//DBMS���� ��ȸ
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
