package userControl;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
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
	
	public PurchaseHistoryEvt(PurchaseHistory ph, RunMarketMain rmm ) throws SQLException {
		
		this.ph=ph;
		this.rmm=rmm;
		id=RunMarketMain.userId;
		setAllList();
		
	}//PurchaseHistoryEvt
	
	public void setAllList() throws SQLException {
		/////////////////// 서버에 접속해서 파일 받기 /////////////////
		UserFileRecieve uFR = UserFileRecieve.getInstance();
		try {
			uFR.getImgFile();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(ph, "파일서버에 접속 실패");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(ph, "파일 로드 실패");
			e.printStackTrace();
		}
		///////////////////////////////////////////////////
		DefaultTableModel dtm=ph.getDtmPurchaseList();
		
		//JTable의 레코드 초기화
		dtm.setRowCount(0);
		
		Object[] rowData=null;
		
		String temp_flag="P";
		//DBMS에서 조회
		UserDAO uDAO=UserDAO.getInstance();
		List<SaleListVO> list=uDAO.selectSaleList(id, temp_flag);
//		if (list.isEmpty()) {
//			JOptionPane.showMessageDialog(ph, "구매한 상품이 없습니다.");
//		}//end if
		SaleListVO slv=null;
		System.out.println(dtm.getColumnCount());
		for(int i=0; i<list.size(); i++) {
			slv=list.get(i);
			//조회 결과로 JTable 레코드에 들어갈 데이터를 생성하고
			rowData=new Object[6];
			//배열에 값 할당
			if (new File(RunMarketMain.imgPath + "/" + slv.getImage()).exists()) {

				rowData[0] = (new ImageIcon(new ImageIcon(RunMarketMain.imgPath + "/" + slv.getImage()).getImage()
						.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			} else {
				rowData[0] = (new ImageIcon(new ImageIcon(RunMarketMain.imgPath+"/"+"default.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			}
			rowData[1]=slv.getProductName()+"("+slv.getProductCode()+")";
			rowData[2]=slv.getPrice();
			rowData[3]=slv.getSellerID();
			rowData[4]=slv.getLoc_code();
			rowData[5]=slv.getUpload_date();
			//dtm에 추가
			dtm.addRow(rowData);		
		}//end for
		
	}//setAllList

	@Override
	public void mouseClicked(MouseEvent me) {

		
		
	}//mouseClicked
	
	
	
}//class
