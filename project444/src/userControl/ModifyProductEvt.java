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
	private SaleList sl;
	private SaleListEvt sle;
	private RunMarketMain rmm;
	private String id;
	public ModifyProductEvt(ModifyProduct mp, SaleList sl, SaleListEvt sle, RunMarketMain rmm) {
		this.mp=mp;
		this.sl=sl;
		this.sle=sle;
		this.rmm=rmm;		
		
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
		
		//DBMS���� ��ȸ 
		UserDAO uDAO = UserDAO.getInstance();
		
		boolean updateFlag=uDAO.updatePost(productCode, price, pDetail);
		
		
		if (updateFlag) {
			JOptionPane.showMessageDialog(mp, "�Է��Ͻ� ������ �����Ϸ�Ǿ����ϴ�");
		} else {
			JOptionPane.showMessageDialog(mp, "�Է��Ͻ� ������ �������� �ʾҽ��ϴ�. �ٽ� �õ��� �ּ���.");
		}//end else 
		
		
		sle.setAllList();
		close();
		
	}//modifyPost


	

	@Override
	public void mouseClicked(MouseEvent me) {
		
		
	}//mouseClicked

	@Override
	public void actionPerformed(ActionEvent ae) {
		if ( ae.getSource() == mp.getJbtCancel() ) {
			close();
		}//end if
		
		if ( ae.getSource() == mp.getJbtOkay() ) {
			try {
				modifyPost();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(mp, "DBMS���� ������ ��Ȱ���� �ʽ��ϴ�.");
			}
		}//end if
 
	}//actionPerformed

	
	
	
}//class
