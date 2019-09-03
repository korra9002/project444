package userView;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;



public class RunMarketMain extends JFrame{

	public RunMarketMain() {
		setLayout(null);
		JTabbedPane jtp = new JTabbedPane();
		jtp.add("marketmain", new MarketMain());
		jtp.add("marketmain", new ModifyProduct());
		
		jtp.setBounds(10, 10, 800		, 800);
	
		add(jtp);
		setVisible(true);
		setBounds(10, 10, 1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String[] args) {
//		new MarketMain();
		new RunMarketMain();
	}//main

}//class
