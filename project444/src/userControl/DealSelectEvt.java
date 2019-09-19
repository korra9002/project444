package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import userDAO.UserDAO;
import userVO.ChatListVO;
import userVO.DealListVO;
import userView.DealSelect;

public class DealSelectEvt extends MouseAdapter implements ActionListener{
private DealSelect ds;
private String productCode;

	


	public DealSelectEvt(DealSelect ds, String productCode) {
	super();
	this.ds = ds;
	this.productCode = productCode;
	ds.getjlb().setText(productCode);
}

	public void setList() {
		UserDAO uDAO = UserDAO.getInstance();
		DefaultTableModel dtm = ds.getDtmAreaList();
		dtm.setRowCount(0);

		Object[] rowData = null;
//		try {
//			List<DealListVO> list = uDAO.setChatList(flag);
//
//			DealListVO dlVO = null;
//			for (int i = 0; i < list.size(); i++) {
//				dlVO = list.get(i);
//				rowData = new Object[6];
//
//				rowData[0] = clVO.getProName();
//				rowData[1] = clVO.getId();
//				rowData[2] = clVO.getLoc();
//				rowData[3] = clVO.getTime();
//				rowData[4] = clVO.getLastchat();
//				rowData[5] = clVO.getDealCode();
//
//				dtm.addRow(rowData);
//			} // end for
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
