package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;


import chatTest.ChattingView;
import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.DCodeAndIdAO;
import userVO.InterestListVO;
import userView.MarketDetailBuyer;
import userView.MarketMain;

public class MarketDetailBuyerEvt implements ActionListener{
	private MarketDetailBuyer mdb;
	private MarketMain mm;
	private String id,sellerId;
	private DCodeAndIdAO DIAO;

	public MarketDetailBuyerEvt(MarketMain mm, MarketDetailBuyer mdb) {
		this.mdb = mdb;
		this.mm = mm;
		id = RunMarketMain.userId;
	}// MarketDetailBuyerEvt

	public void dealStart() {
		UserDAO uDAO = UserDAO.getInstance();
		try {
			int result = uDAO.addDeal(mdb.getProductCode(), mdb.getId());

			switch (result) {
			case -1:
				JOptionPane.showMessageDialog(mdb, "이미 거래중 입니다.");
				return;
			case 1:
				DIAO = uDAO.getDCodeAndId(mdb.getProductCode(), mdb.getId());
				new ChattingView(id,DIAO);
				break;
			default:
				JOptionPane.showMessageDialog(mdb, "실패");
				return;
			}

			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		
	}// dealStart
////////////////////////////변경사항/////////////////////////////////////////
	public void addInterest() throws SQLException {
		boolean checkFlag =mdb.getjckLike().isSelected();
		String productCode =mdb.getProductCode().trim();
		String userId = RunMarketMain.userId.trim();
		if(mdb.getjckLike().isSelected()) {
			JOptionPane.showMessageDialog(mdb, "관심목록에 추가되었습니다.");
			
		}else {
			JOptionPane.showMessageDialog(mdb,"관심목록에서 삭제되었습니다.");
		
		}//end else
		InterestListVO irVO = new InterestListVO(productCode,userId);
		
		UserDAO uDAO = UserDAO.getInstance();
		int flag = uDAO.insertInterest(irVO,checkFlag);
		
		
	}//addInterest
	
	
//////////////////////////////////////////////////////////////////////////	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mdb.getJbtChat()) {
//			new ChattingView(mm);
			dealStart();
		}//end if
////////////////////////////변경사항/////////////////////////////////////////
		if(ae.getSource()==mdb.getjckLike()) {
			try {
				addInterest();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
///////////////////////////////////////////////////////////////////////		
	}// actionPerformed




}// class
