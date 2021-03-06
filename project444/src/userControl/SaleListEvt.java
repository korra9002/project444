package userControl;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import userDAO.UserDAO;
import userFileRecieve.UserFileRecieve;
import userRun.RunMarketMain;
import userVO.AllListVO;
import userVO.MarketDetailVO;
import userVO.SaleListVO;
import userView.MarketDetailBuyer;
import userView.MarketDetailSeller;
import userView.ModifyProduct;
import userView.SaleList;

public class SaleListEvt extends MouseAdapter implements ActionListener {
	private SaleList sl;
	private RunMarketMain rmm;
	String id;
	String classFlag = "";
	String productCode = "";
	DecimalFormat df=new DecimalFormat ("#,###,###");
	
	public SaleListEvt(SaleList sl, RunMarketMain rmm) throws SQLException {
		this.sl = sl;
		this.rmm = rmm;
		id = RunMarketMain.userId;
		setAllList();

	}// SaleListEvt

	public void loadFile() {
		/////////////////// 서버에 접속해서 파일 받기 /////////////////
		UserFileRecieve uFR = UserFileRecieve.getInstance();
		try {
			uFR.getImgFile();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(sl, "파일서버에 접속 실패");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(sl, "파일 로드 실패");
			e.printStackTrace();
		}
		///////////////////////////////////////////////////
	}//loadFile
	
	
	/**
	 * 판매목록 띄우기
	 * 
	 * @param sl
	 * @throws SQLException
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
	public void setAllList() throws SQLException {
		loadFile();
		

		DefaultTableModel dtm = sl.getDtmSell();

		// JTable의 레코드 초기화
		dtm.setRowCount(0);

		Object[] rowData = null;
		String temp_flag = "S";
		// DBMS에서 조회
		UserDAO uDAO = UserDAO.getInstance();
		List<SaleListVO> list = uDAO.selectSaleList(id, temp_flag);
//		if (list.isEmpty()) {
//			JOptionPane.showMessageDialog(sl, "현재 판매하시는 상품이 없습니다. ");
//
//		} /// 메세지 안띄어도 괜찮은거 같음
		SaleListVO slv = null;
		for (int i = 0; i < list.size(); i++) {
			slv = list.get(i);
			// 조회 결과로 JTable 레코드에 들어갈 데이터를 생성하고
			rowData = new Object[7];
			// 배열에 값 할당
			if (new File(RunMarketMain.imgPath + "/" + slv.getImage()).exists()) {

				rowData[0] = (new ImageIcon(new ImageIcon(RunMarketMain.imgPath + "/" + slv.getImage()).getImage()
						.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			} else {
				rowData[0] = (new ImageIcon(new ImageIcon(RunMarketMain.imgPath+"/"+"default.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			}
			rowData[1] = slv.getProductName() + "(" + slv.getProductCode() + ")";
			rowData[2] = df.format(slv.getPrice());
			rowData[3] = slv.getCategory();
			rowData[4] = slv.getLoc_code();
			rowData[5] = slv.getUpload_date();
			rowData[6] = slv.getCheck();
			// dtm에 추가
			dtm.addRow(rowData);
		} // end for
	}// setAllList

	/**
	 * 판매완료된 상품목록 띄우기
	 * 
	 * @param sl
	 * @throws SQLException
	 */
	public void setCompList() throws SQLException {
		loadFile();
		
		DefaultTableModel dtm = sl.getDtmComp();

		// JTable의 레코드 초기화
		dtm.setRowCount(0);

		Object[] rowData = null;

		// DBMS에서 조회
		UserDAO uDAO = UserDAO.getInstance();
		List<SaleListVO> list = uDAO.selectCompList(RunMarketMain.userId);
//		if (list.isEmpty()) {
//			JOptionPane.showMessageDialog(sl, "현재 판매완료된 상품이 없습니다. ");
//
//		}
		SaleListVO slv = null;
		for (int i = 0; i < list.size(); i++) {
			slv = list.get(i);
			// 조회 결과로 JTable 레코드에 들어갈 데이터를 생성하고
			rowData = new Object[7];
			// 배열에 값 할당
			if (new File(RunMarketMain.imgPath + "/" + slv.getImage()).exists()) {

				rowData[0] = (new ImageIcon(new ImageIcon(RunMarketMain.imgPath + "/" + slv.getImage()).getImage()
						.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			} else {
				rowData[0] = (new ImageIcon(new ImageIcon(RunMarketMain.imgPath+"/"+"default.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			}
			rowData[1] = slv.getProductName() + "(" + slv.getProductCode() + ")";
			rowData[2] = df.format(slv.getPrice());
			rowData[3] = slv.getCategory();
			rowData[4] = slv.getLoc_code();
			rowData[5] = slv.getSellerID();
			rowData[6] = slv.getUpload_date();

			// dtm에 추가
			dtm.addRow(rowData);
		} // end for
	}// setAllList
	
	public void modifySaleList() throws SQLException {
		
		MarketDetailVO mdVO=null;
		//VO에 넣을 값들
		String temp=(String)sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 1);	//상품명+코드
		String product_Code = temp.substring(temp.lastIndexOf("(") + 1, temp.lastIndexOf(")"));
		
//		ImageIcon image=(ImageIcon)sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 0); //이미지
//		String productName= temp.substring(0, temp.lastIndexOf( "(" ) - 1);
//		
//		int price=(int)sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 2);		//가격
//		String category=(String)sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 3); //카테고리
//
//		String seller=sl.getJtSell().get
//		
//		String pDetail=mds.getJtaStrongPoint().getText().trim();
//		String sellerID=mds.getJtfId().getText().trim();
		
		UserDAO uDAO=UserDAO.getInstance();
		uDAO.selectProDetail(product_Code, "M");

		
		
		mdVO=uDAO.selectProDetail(product_Code, "M");
		System.out.println(this+"1");
		new ModifyProduct(mdVO ,this, rmm);
		

		
		
	}//modifySaleList

	public void deleteSaleList() {

		switch (JOptionPane.showConfirmDialog(sl,"판매글을 삭제하시겠습니까??","판매글 삭제",JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE)) {
		case JOptionPane.OK_OPTION:

			// DBMS에서 레코드를 삭제
			UserDAO uDAO = UserDAO.getInstance();

			String msg = "선택하신 판매글을 삭제하지 못했습니다.";
			try {

//			StringBuilder removeFileName = new StringBuilder(ld.getJlImg().getIcon().toString());
				String temp = (String) sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 1);
				String product_code = temp.substring(temp.lastIndexOf("(") + 1, temp.lastIndexOf(")"));
//				System.out.println(product_code);
				if (uDAO.deletePost(product_code)) {
					// 삭제된 이후 업로드된 파일을 삭제한다.
//					File originFile=new File(removeFileName.toString());
//					File thumbFile=new File(removeFileName.insert(removeFileName.lastIndexOf("/")+1,"rs_").toString());
//					originFile.delete();
//					thumbFile.delete();

					msg = "선택하신 판매글을 삭제하였습니다.";

					// 부모창의 도시락 리스트를 갱신
					setAllList();
					// 현재창 닫기
					// close();
				} // end if
			} catch (SQLException e) {
				msg = " 삭제 작업 중 문제가 발생하였습니다.";
				e.printStackTrace();
			} // end catch
			JOptionPane.showMessageDialog(sl, msg);
		}// end switch
	}// deleteSaleList

	public void openDetail() throws SQLException {

		if (classFlag == "S") {
			JTable jtProductList = sl.getJtSell();

			String temp = (String) jtProductList.getValueAt(jtProductList.getSelectedRow(), 1);
			productCode = temp.substring(temp.lastIndexOf("(") + 1, temp.lastIndexOf(")"));

		} else if (classFlag == "C") {
			JTable jtComList = sl.getJtComplete();

			String temp = (String) jtComList.getValueAt(jtComList.getSelectedRow(), 1);
			productCode = temp.substring(temp.lastIndexOf("(") + 1, temp.lastIndexOf(")"));

		}

		// DBMS에서 조회
		UserDAO uDAO = UserDAO.getInstance();

		MarketDetailVO mdVO = uDAO.selectProDetail(productCode, classFlag);

		if (mdVO != null) {

			// 현재 접속한 아이디와 포스팅 판매자 아이디와 같으면 MarketDetailBuyer
			// 다르다면 MarketDetailSeller
			new MarketDetailSeller(null, mdVO, id,this);
		} else {
			JOptionPane.showMessageDialog(sl, "판매중인 상품이 아닙니다.");
		}

	}// openDetail

	/**
	 * 판매내역 창 닫기
	 */
	public void close() {
		sl.dispose();
	}// close

	@Override
	public void mouseClicked(MouseEvent me) {
		// 판매완료 상품탭이 눌렸을 때 처리

		if (me.getSource() == sl.getJtp()) {
			JTabbedPane jtpTemp = (JTabbedPane) me.getSource();

			if (jtpTemp.getSelectedIndex() == 0) {
				try {
					setAllList();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (jtpTemp.getSelectedIndex() == 1) {

				try {
					setCompList();
				} catch (SQLException e) {
					e.printStackTrace();
				} // end catch

			} // end if

		} // end if

		if (me.getClickCount() == 2) {// 더블클릭			
			if (me.getSource() == sl.getJtSell()) {
				if(sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(),6).equals("P")) {						
					try {
						classFlag = "S"; // userDAO에서 selectProDetail method 사용할 때 구분용 플래그 ---->판매중인목록
	
						openDetail();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}//end catch
					
				} else {
					JOptionPane.showMessageDialog(sl, "해당 상품은 검수중입니다");
				}//end if
			} else if (me.getSource() == sl.getJtComplete()) {
//				try {
////					classFlag = "C"; // userDAO에서 selectProDetail method 사용할 때 구분용 플래그 ---->판매완료된목록
////					openDetail();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
			}//end else
		} // end if

	}// mouseClicked

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (sl.getJtSell().getSelectedRow() != -1) {
			if (ae.getSource() == sl.getJbtModify()) {
				if(sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 6).equals("P")) {
					try {
						modifySaleList();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}//end catch

				} else {
					JOptionPane.showMessageDialog(sl, "해당 상품은 검수중입니다.");
				}//end else
			} // end if
			if (ae.getSource() == sl.getJbtDelete()) {
				
				if(sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 6).equals("P")) {
				
				
				deleteSaleList();
				} else {
					JOptionPane.showMessageDialog(sl, "해당 상품은 검수중입니다.");
				}//end else
			} // end if

		
		} else {
			JOptionPane.showMessageDialog(sl, "수정/삭제를 원하시는 상품을 선택해주세요.");// end if
		} // end else
		

	}// actionPerformed

}// SaleListEvt
