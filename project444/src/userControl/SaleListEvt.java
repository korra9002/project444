package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.AllListVO;
import userVO.SaleListVO;
import userView.ModifyProduct;
import userView.SaleList;

public class SaleListEvt extends MouseAdapter implements ActionListener {
	private SaleList sl;
	private RunMarketMain rmm;
	String id;
		public SaleListEvt(SaleList sl, RunMarketMain rmm) throws SQLException  {
			this.sl = sl;
			this.rmm=rmm;
			id=RunMarketMain.userId;
			setAllList();

		}//SaleListEvt
		

	
	/**
	 * 판매목록 띄우기
	 * @param sl
	 * @throws SQLException
	 */
	public void setAllList() throws SQLException {
		
		DefaultTableModel dtm=sl.getDtmSell();
		
		//JTable의 레코드 초기화
		dtm.setRowCount(0);
		
		Object[] rowData=null;
		String temp_flag="S";
		//DBMS에서 조회
		UserDAO uDAO =UserDAO.getInstance();
	List<SaleListVO> list=uDAO.selectSaleList(id, temp_flag);
		if(list.isEmpty()) { 
			JOptionPane.showMessageDialog(sl, "현재 판매하시는 상품이 없습니다. ");
			
		}
		SaleListVO slv=null;
		for(int i=0; i<list.size(); i++) {
			slv=list.get(i);
			//조회 결과로 JTable 레코드에 들어갈 데이터를 생성하고
			rowData=new Object[6];
			//배열에 값 할당
			rowData[0]=slv.getImage();
			rowData[1]=slv.getProductName()+"("+slv.getProductCode()+")";
			rowData[2]=slv.getPrice();
			rowData[3]=slv.getLoc_code();
			rowData[4]=slv.getUpload_date();
			rowData[5]=slv.getCheck();
			//dtm에 추가
			dtm.addRow(rowData);		
		}//end for	
	}//setAllList
		
	
	/**
	 * 판매완료된 상품목록 띄우기
	 * @param sl
	 * @throws SQLException
	 */
	public void setCompList() throws SQLException {
		
		DefaultTableModel dtm=sl.getDtmComp();
		
		//JTable의 레코드 초기화
		dtm.setRowCount(0);
		
		Object[] rowData=null;
		
		//DBMS에서 조회
		UserDAO uDAO =UserDAO.getInstance();
		List<SaleListVO> list=uDAO.selectCompList(RunMarketMain.userId);
		if(list.isEmpty()) { 
			JOptionPane.showMessageDialog(sl, "현재 판매완료된 상품이 없습니다. ");
			
		}
		SaleListVO slv=null;
		for(int i=0; i<list.size(); i++) {
			slv=list.get(i);
			//조회 결과로 JTable 레코드에 들어갈 데이터를 생성하고
			rowData=new Object[5];
			//배열에 값 할당
			rowData[0]=slv.getImage();
			rowData[1]=slv.getProductName()+"("+slv.getProductCode()+")";
			rowData[2]=slv.getPrice();
			rowData[3]=slv.getLoc_code();
			rowData[4]=slv.getSellerID();
			//dtm에 추가
			dtm.addRow(rowData);		
		}//end for	
	}//setAllList
	
	public void deleteSaleList() {

		switch(JOptionPane.showConfirmDialog(sl, "판매글을 삭제하시겠습니까?")) {
		case JOptionPane.OK_OPTION: 
			
			//DBMS에서 레코드를 삭제 
			UserDAO uDAO=UserDAO.getInstance();
			
			String msg="선택하신 판매글을 삭제하지 못했습니다.";
			try {
			
//			StringBuilder removeFileName = new StringBuilder(ld.getJlImg().getIcon().toString());
			String temp=(String)sl.getJtSell().getValueAt(sl.getJtSell().getSelectedRow(), 1);
			String product_code=temp.substring(temp.lastIndexOf("(")+1,temp.lastIndexOf(")"));
			System.out.println(product_code);
			if ( uDAO.deletePost(product_code) ) {
				//삭제된 이후 업로드된 파일을 삭제한다.
//					File originFile=new File(removeFileName.toString());
//					File thumbFile=new File(removeFileName.insert(removeFileName.lastIndexOf("/")+1,"rs_").toString());
//					originFile.delete();
//					thumbFile.delete();
					
					msg="선택하신 판매글을 삭제하였습니다.";
				
					
					//부모창의 도시락 리스트를 갱신
					setAllList();
					//현재창 닫기
					close();
				}//end if
			} catch (SQLException e) {
				msg=" 삭제 작업 중 문제가 발생하였습니다.";
				e.printStackTrace();
			}//end catch
			JOptionPane.showMessageDialog(sl, msg);
		}//end switch	
	}//deleteSaleList
	
	/**
	 * 판매내역 창 닫기
	 */
	public void close() {
		sl.dispose();		
	}//close
	

	@Override
	public void mouseClicked(MouseEvent me) {
		//판매완료 상품탭이 눌렸을 때 처리

		if(me.getSource()==sl.getJtp()) {
			JTabbedPane jtpTemp=(JTabbedPane)me.getSource();
			
				if(jtpTemp.getSelectedIndex()== 0) {
					try {
						setAllList();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (jtpTemp.getSelectedIndex()== 1) {
				
					try {
						setCompList();
					} catch (SQLException e) {
						e.printStackTrace();
					}//end catch
			
				}//end if
				
		}
			
	}
		
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		

		if(ae.getSource()==sl.getJbtModify()) {
			new ModifyProduct();
		}//end if
		if(ae.getSource()==sl.getJbtDelete()) {
			deleteSaleList();
				
			}
		}//end if



}//SaleListEvt

