package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import userRun.RunMarketMain;

public class RunMarketMainEvt extends MouseAdapter implements ActionListener{
	private RunMarketMain rmm;

	
	
	
	public RunMarketMainEvt(RunMarketMain rmm) {
		super();
		this.rmm = rmm;
	}




	@Override
	public void mouseClicked(MouseEvent e) {
	if(e.getSource() == rmm.getJtp()) {
		if(rmm.getJtp().getSelectedIndex() == 2) {
			System.out.println("”î");
		}
		
		
	}
	
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	

}
