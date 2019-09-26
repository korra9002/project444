package userControl;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import userDAO.UserDAO;
import userFileRecieve.UserFileRecieve;
import userRun.RunMarketMain;
import userVO.SaleListVO;
import userView.PurchaseHistory;

public class PurchaseHistoryEvt extends MouseAdapter {

	private PurchaseHistory ph;
	private RunMarketMain rmm;
	String id;
	DecimalFormat df=new DecimalFormat ("#,###,###");
	
	public PurchaseHistoryEvt(PurchaseHistory ph, RunMarketMain rmm ) throws SQLException {
		
		this.ph=ph;
		this.rmm=rmm;
		id=RunMarketMain.userId;
		setAllList();
		
	}//PurchaseHistoryEvt
	
	public void setAllList() throws SQLException {
		/////////////////// ������ �����ؼ� ���� �ޱ� /////////////////
		UserFileRecieve uFR = UserFileRecieve.getInstance();
		try {
			uFR.getImgFile();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(ph, "���ϼ����� ���� ����");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(ph, "���� �ε� ����");
			e.printStackTrace();
		}
		///////////////////////////////////////////////////
		DefaultTableModel dtm=ph.getDtmPurchaseList();
		
		//JTable�� ���ڵ� �ʱ�ȭ
		dtm.setRowCount(0);
		
		Object[] rowData=null;
		
		String temp_flag="P";
		//DBMS���� ��ȸ
		UserDAO uDAO=UserDAO.getInstance();
		List<SaleListVO> list=uDAO.selectSaleList(id, temp_flag);
//		if (list.isEmpty()) {
//			JOptionPane.showMessageDialog(ph, "������ ��ǰ�� �����ϴ�.");
//		}//end if
		SaleListVO slv=null;
		System.out.println(dtm.getColumnCount());
		for(int i=0; i<list.size(); i++) {
			slv=list.get(i);
			//��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
			rowData=new Object[6];
			//�迭�� �� �Ҵ�
			if (new File(RunMarketMain.imgPath + "/" + slv.getImage()).exists()) {

				rowData[0] = (new ImageIcon(new ImageIcon(RunMarketMain.imgPath + "/" + slv.getImage()).getImage()
						.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			} else {
				rowData[0] = (new ImageIcon(new ImageIcon(RunMarketMain.imgPath+"/"+"default.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			}
			rowData[1]=slv.getProductName()+"("+slv.getProductCode()+")";
			rowData[2]=df.format(slv.getPrice());
			rowData[3]=slv.getSellerID();
			rowData[4]=slv.getLoc_code();
			rowData[5]=slv.getUpload_date();
			//dtm�� �߰�
			dtm.addRow(rowData);		
		}//end for
		
	}//setAllList

	@Override
	public void mouseClicked(MouseEvent me) {

		
		
	}//mouseClicked
	
	
	
}//class
