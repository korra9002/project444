package userView;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import userRun.RunMarketMain;

public class LevelInfo extends JDialog{
	public LevelInfo() {
		setTitle("ȸ���������");
		ImageIcon ii = new ImageIcon(RunMarketMain.basicPath+"ȸ�����.png");
		JLabel jl = new JLabel(ii);
		add(jl);
		setBounds(180, 250, 500, 630);
		setResizable(false);
		setVisible(true);
		
		
	}//LevelInfo
	
	
}//class
