package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import chatTest.ChattingView;
import userView.MarketDetailBuyer;
import userView.MarketMain;

public class MarketDetailBuyerEvt extends MouseAdapter implements ActionListener{
	private MarketDetailBuyer mdb;
	private MarketMain mm;
	private String id;
	
	public MarketDetailBuyerEvt (MarketMain mm, MarketDetailBuyer mdb) {
		this.mdb=mdb;
		this.mm=mm;
		this.id=id;
	}//MarketDetailBuyerEvt


	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==mdb.getJbtChat()) {
			new ChattingView(mm);
		}
	}//actionPerformed


	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getSource()==mdb.getCkLike()) {
			
		}
	}//mouseClicked
	
	
	
	
	
}//class
