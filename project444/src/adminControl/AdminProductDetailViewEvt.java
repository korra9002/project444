package adminControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import adminView.AdminProductDetailView;

public class AdminProductDetailViewEvt implements ActionListener {
	
	private AdminProductDetailView apdv;
	
	public AdminProductDetailViewEvt(AdminProductDetailView apdv) {
		this.apdv = apdv;
	}//AdminCheckDetailViewEvt

	private void close() {
		apdv.dispose();
	}//close
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==apdv.getJbtOk()) {
			close();
		}//end if
		
	}//actionPerformed

}//class
