package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.MarketDetailVO;
import userView.MarketDetailBuyer;
import userView.MarketDetailSeller;
import userView.ModifyProduct;
import userView.SaleList;

public class ModifyProductEvt extends MouseAdapter implements ActionListener{
	
	private ModifyProduct mp;
	private SaleListEvt sle ;
	private RunMarketMain rmm;
	private String id;
	public ModifyProductEvt(ModifyProduct mp, SaleListEvt sle, RunMarketMain rmm) {
		this.mp=mp;
		this.sle=sle;
		this.rmm=rmm;		
		System.out.println(sle+"3");
		id=RunMarketMain.userId;
	}//ModifyProductEvt
	 
	public void close() {
		mp.dispose();
	}//close
	
	public void modifyPost() throws SQLException {
		
		String temp = mp.getJtfSubject().getText().trim();
		String productCode = temp.substring(temp.lastIndexOf("(") + 1, temp.lastIndexOf(")"));
		System.out.println(productCode);
		int price = Integer.parseInt(mp.getJtfPrice().getText().trim());
		String pDetail= mp.getJtaExplain().getText().trim();
		
		//DBMS에서 조회 
		UserDAO uDAO = UserDAO.getInstance();
		
		boolean updateFlag=uDAO.updatePost(productCode, price, pDetail);
		
		
		if (updateFlag) {
			JOptionPane.showMessageDialog(mp, "입력하신 내용이 수정완료되었습니다");
		} else {
			JOptionPane.showMessageDialog(mp, "입력하신 내용이 수정되지 않았습니다. 다시 시도해 주세요.");
		}//end else 
		
		//SY : 마켓메인도 불러서 새로고침 해야함.
		//상품 디테일 창도 새로고침 되야함.
		if( sle != null) {
			sle.setAllList();
			
		}
		
		close();
		
	}//modifyPost


	

	@Override
	public void mouseClicked(MouseEvent me) {
		
		
	}//mouseClicked

	@Override
	public void actionPerformed(ActionEvent ae) {
		if ( ae.getSource() == mp.getJbtCancel() ) {
			switch (JOptionPane.showConfirmDialog(mp,"상품정보 수정을 취소하시겠습니까?","상품정보 취소",JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE)) {
			case JOptionPane.OK_OPTION:
				JOptionPane.showMessageDialog(mp, "상품수정작업이 취소되었습니다.");
				close();				
			case JOptionPane.CANCEL_OPTION:
				
			}//end switch
		}//end if
		
		if ( ae.getSource() == mp.getJbtOkay() ) {
			try {
				modifyPost();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(mp, "DBMS와의 연결이 원활하지 않습니다.");
			}
		}//end if
 
	}//actionPerformed

	
	
	
}//class
