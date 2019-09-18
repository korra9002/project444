package userControl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.SaleListVO;
import userView.PurchaseHistory;

public class PurchaseHistoryEvt extends MouseAdapter {

	private PurchaseHistory ph;
	private RunMarketMain rmm;
	String id;
	
	public PurchaseHistoryEvt(PurchaseHistory ph, RunMarketMain rmm ) throws SQLException {
		
		this.ph=ph;
		this.rmm=rmm;
		id=RunMarketMain.userId;
		setAllList();
		
	}//PurchaseHistoryEvt
	
	public void setAllList() throws SQLException {
		DefaultTableModel dtm=ph.getDtmPurchaseList();
		
		//JTable�� ���ڵ� �ʱ�ȭ
		dtm.setRowCount(0);
		
		Object[] rowData=null;
		
		String temp_flag="P";
		//DBMS���� ��ȸ
		UserDAO uDAO=UserDAO.getInstance();
		List<SaleListVO> list=uDAO.selectSaleList(id, temp_flag);
		if (list.isEmpty()) {
			JOptionPane.showMessageDialog(ph, "������ ��ǰ�� �����ϴ�.");
		}//end if
		SaleListVO slv=null;
		for(int i=0; i<list.size(); i++) {
			slv=list.get(i);
			//��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
			rowData=new Object[5];
			//�迭�� �� �Ҵ�
			rowData[0]=slv.getImage();
			rowData[1]=slv.getProductName()+"("+slv.getProductCode()+")";
			rowData[2]=slv.getPrice();
			rowData[3]=slv.getLoc_code();
			rowData[4]=slv.getUpload_date();
			//dtm�� �߰�
			dtm.addRow(rowData);		
		}//end for
		
	}//setAllList

	@Override
	public void mouseClicked(MouseEvent me) {

		
		
	}//mouseClicked
	
	
	
}//class