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
		System.out.println("dealselectEvt 생성자");
	this.ds = ds;
	this.productCode = productCode;
	ds.getjlb().setText(productCode);
	setList();
}

	public void setList() {
		System.out.println("setlist");
		UserDAO uDAO = UserDAO.getInstance();
		DefaultTableModel dtm = ds.getDtmAreaList();
		dtm.setRowCount(0);

		Object[] rowData = null;
		try {
			List<DealListVO> list = uDAO.setDealList(productCode);

			DealListVO dlVO = null;
			for (int i = 0; i < list.size(); i++) {
				dlVO = list.get(i);
				rowData = new Object[3];

				rowData[0] = dlVO.getId();
				rowData[1] = dlVO.getLoc();
				rowData[2] = dlVO.getDealCode();
			System.out.println("for문");
				dtm.addRow(rowData);
			} // end for

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
