package userRun;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import userView.ChatList;
import userView.InsertProduct;
import userView.MarketMain;
import userView.UserInfo;


 
public class RunMarketMain extends JFrame{

	public RunMarketMain(String id) throws SQLException {
		
		setLayout(null);
		JTabbedPane jtp = new JTabbedPane();
		jtp.add("홈", new MarketMain());
		jtp.add("상품입력", new InsertProduct());
		jtp.add("채팅", new ChatList());
		jtp.add("MyPage", new UserInfo(id,this));
		  
		jtp.setBounds(10, 10, 500, 600);
		 
	 
		add(jtp); 
		
	 
		add(jtp);
		setVisible(true);
		setBounds(10, 10, 550, 670);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
//	public static void main(String[] args) throws SQLException {
//		new MarketMain();
//		new RunMarketMain();
//	}//main

}//class
