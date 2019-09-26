package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private String id;
	private DCodeAndIdAO DIAO;
	private InterestListEvt ile;
	/////////////////////////////추가 김서영 2019-09-19/////////////////////
	int cnt = 0;
	public MarketDetailBuyerEvt(MarketMain mm, MarketDetailBuyer mdb,InterestListEvt ile) {
		this.mdb = mdb;
		this.mm = mm;
		this.ile=ile;
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
		
		
		InterestListVO irVO = new InterestListVO(productCode,userId);
		UserDAO uDAO = UserDAO.getInstance();
		
		if(mdb.getjckLike().isSelected()) {
			if(	!RunMarketMain.userId.equals(uDAO.selectInterestCheck(irVO))) {
				JOptionPane.showMessageDialog(mdb, "관심목록에 추가되었습니다.");
			}else {
				JOptionPane.showMessageDialog(mdb, "관심목록에 이미 존재합니다.");
				return;
				
			}//end else
		}else {
			if(	RunMarketMain.userId.equals(uDAO.selectInterestCheck(irVO))) {
			JOptionPane.showMessageDialog(mdb,"관심목록에서 삭제되었습니다.");
			}else {
				JOptionPane.showMessageDialog(mdb, "관심목록에서 이미 삭제되었습니다.");
				return;
			}
		}//end else
		
		uDAO.insertInterest(irVO,checkFlag);
		
		if(ile != null) {
			
			ile.setInterestList();
		}//end if
	
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
