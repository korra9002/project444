package userRun;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import userControl.RunMarketMainEvt;
import userView.ChatList;
import userView.InsertProduct;
import userView.MarketMain;
import userView.UserInfo;


 
public class RunMarketMain extends JFrame{
public static String userId;
private JTabbedPane jtp;

	public RunMarketMain(String id) throws SQLException {
		userId = id;
		
		setLayout(null);
		jtp = new JTabbedPane();
		jtp.add("홈", new MarketMain(id));
		jtp.add("상품입력", new InsertProduct(this));
		jtp.add("채팅", new ChatList());
		jtp.add("MyPage", new UserInfo(id,this));
		  
		jtp.setBounds(10, 10, 660, 700);
		 
	 
		add(jtp); 
		
	 
		add(jtp);
		setVisible(true);
		setBounds(10, 10, 700, 780);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		RunMarketMainEvt rmme = new RunMarketMainEvt(this);
		jtp.addMouseListener(rmme);
		
		
	}


	public JTabbedPane getJtp() {
		return jtp;
	}
	
//	public static void main(String[] args) throws SQLException {
//		new MarketMain();
//		new RunMarketMain();
//	}//main

}//class
