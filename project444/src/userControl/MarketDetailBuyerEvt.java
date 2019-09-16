package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.sun.webkit.ContextMenu.ShowContext;

import chatTest.ChattingView;
import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.DCodeAndIdAO;
import userView.MarketDetailBuyer;
import userView.MarketMain;

public class MarketDetailBuyerEvt extends MouseAdapter implements ActionListener {
	private MarketDetailBuyer mdb;
	private MarketMain mm;
	private String id;
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

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mdb.getJbtChat()) {
//			new ChattingView(mm);
			dealStart();
		}
	}// actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getSource() == mdb.getCkLike()) {

		}
	}// mouseClicked

}// class
