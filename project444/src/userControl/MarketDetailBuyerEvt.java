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
import userVO.sellerFlagVO;
import userView.MarketDetailBuyer;
import userView.MarketMain;

public class MarketDetailBuyerEvt implements ActionListener{
	private MarketDetailBuyer mdb;
	private MarketMain mm;
	private String id;
	private DCodeAndIdAO DIAO;
	private InterestListEvt ile;
	/////////////////////////////�߰� �輭�� 2019-09-19/////////////////////
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
				JOptionPane.showMessageDialog(mdb, "�̹� �ŷ��� �Դϴ�.");
				return;
			case 1:
				DIAO = uDAO.getDCodeAndId(mdb.getProductCode(), mdb.getId());
				new ChattingView(id,DIAO);
				break;
			default:
				JOptionPane.showMessageDialog(mdb, "����");
				return;
			}

			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		
	}// dealStart
////////////////////////////�������/////////////////////////////////////////
	public void addInterest() throws SQLException {
		boolean checkFlag =mdb.getjckLike().isSelected();
		String productCode =mdb.getProductCode().trim();
		String userId = RunMarketMain.userId.trim();
		
		
		InterestListVO irVO = new InterestListVO(productCode,userId);
		UserDAO uDAO = UserDAO.getInstance();
		
		if(mdb.getjckLike().isSelected()) {
			if(	!RunMarketMain.userId.equals(uDAO.selectInterestCheck(irVO))) {
				JOptionPane.showMessageDialog(mdb, "���ɸ�Ͽ� �߰��Ǿ����ϴ�.");
			}else {
				JOptionPane.showMessageDialog(mdb, "���ɸ�Ͽ� �̹� �����մϴ�.");
				return;
				
			}//end else
		}else {
			if(	RunMarketMain.userId.equals(uDAO.selectInterestCheck(irVO))) {
			JOptionPane.showMessageDialog(mdb,"���ɸ�Ͽ��� �����Ǿ����ϴ�.");
			}else {
				JOptionPane.showMessageDialog(mdb, "���ɸ�Ͽ��� �̹� �����Ǿ����ϴ�.");
				return;
			}
		}//end else
		
		uDAO.insertInterest(irVO,checkFlag);
		
		if(ile != null) {
			
			ile.setInterestList();
		}//end if
	
	}//addInterest
	public sellerFlagVO checkFlag() {
		sellerFlagVO sFVO = null;
		String productCode = mdb.getProductCode();
		UserDAO uDAO = UserDAO.getInstance();
		try {
			sFVO = uDAO.checkFlag2(productCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		return sFVO;
	}
	
//////////////////////////////////////////////////////////////////////////	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mdb.getJbtChat()) {
//			new ChattingView(mm);
			sellerFlagVO sfVO = checkFlag();
			if(!sfVO.getAllFLag().equals("P")) {
				JOptionPane.showMessageDialog(mdb, "�Ǹ����� ��ǰ�� �ƴմϴ�. ");
//				mds.dispose();
				return;
			}else if (sfVO.getpFlag()>0) {
				JOptionPane.showMessageDialog(mdb, "�Ǹ� �Ϸ�� ��ǰ�Դϴ�.");
				return;
				
			}else if (sfVO.getyFlag()>0) {
				JOptionPane.showMessageDialog(mdb, "�ŷ� �������� ��ǰ�Դϴ�.");
				return;
				
			}
			dealStart();
		}//end if
////////////////////////////�������/////////////////////////////////////////
		if(ae.getSource()==mdb.getjckLike()) {
			try {
				sellerFlagVO sfVO = checkFlag();
				if(!sfVO.getAllFLag().equals("P")) {
					JOptionPane.showMessageDialog(mdb, "�Ǹ����� ��ǰ�� �ƴմϴ�. ");
//					mds.dispose();
					return;
				}else if (sfVO.getpFlag()>0) {
					JOptionPane.showMessageDialog(mdb, "�Ǹ� �Ϸ�� ��ǰ�Դϴ�.");
					return;
					
				}else if (sfVO.getyFlag()>0) {
					JOptionPane.showMessageDialog(mdb, "�ŷ� �������� ��ǰ�Դϴ�.");
					return;
					
				}
				
				addInterest();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
///////////////////////////////////////////////////////////////////////		
	}// actionPerformed




}// class
