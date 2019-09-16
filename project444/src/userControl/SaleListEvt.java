package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import userView.SaleList;

public class SaleListEvt extends MouseAdapter implements ActionListener {
	private SaleList sl;
		public SaleListEvt(SaleList sl) {
			this.sl = sl;
		}//SaleListEvt
	
		
		
		
	@Override
		public void mouseClicked(MouseEvent me) {

		}//mouseClicked



	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==sl.getJbtModify()) {
			
		}//end if
	}//actionPerformed

}//SaleListEvt
