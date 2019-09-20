package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import userView.DealSelect;
import userView.MarketDetailSeller;
import userView.MarketMain;



public class MarketDetailSellerEvt extends MouseAdapter implements ActionListener {
	private MarketDetailSeller mds;
	private MarketMain mm;
	private String id;
	
	public MarketDetailSellerEvt(MarketMain mm, MarketDetailSeller mds) {
	
	this.mm=mm;
	this.mds=mds;
	this.id=id;
	
	
}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mds.getJbtComplete()) {
			String temp = mds.getJtfName().getText();
			temp = temp.substring(temp.lastIndexOf('(')+1,temp.lastIndexOf(')')).trim();
//			System.out.println(temp+"상품코드");
			System.out.println(temp);
			new DealSelect(temp);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
	}
}
