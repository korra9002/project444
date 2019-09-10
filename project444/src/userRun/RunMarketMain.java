package userRun;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import userView.Chat;
import userView.InsertProduct;
import userView.MarketMain;
import userView.UserInfo;


 
public class RunMarketMain extends JFrame{

	public RunMarketMain() throws SQLException {
		
		
		setLayout(null);
		JTabbedPane jtp = new JTabbedPane();
		jtp.add("Ȩ", new MarketMain());
		jtp.add("��ǰ�Է�", new InsertProduct());
		jtp.add("ä��", new Chat());
		jtp.add("MyPage", new UserInfo());
		  
		jtp.setBounds(10, 10, 660, 700);
		 
	 
		add(jtp); 
		
	 
		add(jtp);
		setVisible(true);
		setBounds(10, 10, 700, 780);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) throws SQLException {
//		new MarketMain();
		new RunMarketMain();
	}//main

}//class
