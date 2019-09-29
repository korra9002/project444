package userView;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import userRun.RunMarketMain;

public class LevelInfo extends JDialog{
	public LevelInfo() {
		setTitle("회원등급정보");
		ImageIcon ii = new ImageIcon(RunMarketMain.basicPath+"회원등급.png");
		JLabel jl = new JLabel(ii);
		add(jl);
		setBounds(180, 250, 500, 630);
		setResizable(false);
		setVisible(true);
		
		
	}//LevelInfo
	
	
}//class
