package userView;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;



public class RunMarketMain extends JFrame{

	public RunMarketMain() {
		setLayout(null);
		JTabbedPane jtp = new JTabbedPane();
		jtp.add("홈", new MarketMain());
		jtp.add("상품입력", new InsertProduct());
		jtp.add("채팅", new Chat());
		jtp.add("MyPage", new UserInfo());
		
		jtp.setBounds(10, 10, 500, 600);
	
		add(jtp);
		setVisible(true);
		setBounds(10, 10, 550, 670);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String[] args) {
//		new MarketMain();
		new RunMarketMain();
	}//main

}//class
