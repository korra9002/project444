package userControl;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import userRun.RunMarketMain;
import userView.InsertProduct;

public class InsertProductEvt implements ActionListener {
	
	private InsertProduct ip;
	private RunMarketMain rmm;
	
	public InsertProductEvt(InsertProduct ip, RunMarketMain rmm) {
		this.ip=ip;
		this.rmm=rmm;
	}//InsertProductEvt
	
	public void addImg() {
		

		
	}//addImg
	
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource()==ip.getJbtSelectImg()) {
//				Frame f=new Frame();
//				RunMarketMain rm=null;
//				try {
//					rm = new RunMarketMain(null);
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				FileDialog fd=new FileDialog(rmm);
				
				FileDialog fdOpen=new FileDialog(fd,"ぞしぞし",FileDialog.LOAD);
				fdOpen.setVisible(true);

			}
		}
	}

	
	

