package userRun;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

import userControl.RunMarketMainEvt;
import userView.ChatList;
import userView.InsertProduct;
import userView.MarketMain;
import userView.UserInfo;


 
public class RunMarketMain extends JFrame{
public static String userId;
public static String imgPath = "c:/dev/userRecieveFile";
private JTabbedPane jtp;


private ChatList cl;

	public RunMarketMain(String id) throws SQLException {
		
		
		userId = id;
		setLayout(null);
		jtp = new JTabbedPane();
		
		jtp.add("홈", new MarketMain(id));
		jtp.add("상품입력", new InsertProduct(this));
		jtp.add("채팅", cl =new ChatList());
		jtp.add("MyPage", new UserInfo(id,this));
		  
		jtp.setBounds(10, 10, 810, 830);
		jtp.setBackground(new Color(0xFFCC66));
		Container c = getContentPane();
		c.setBackground(Color.white);
		
		
		add(jtp); 
		
	 
		setVisible(true);
		setBounds(10, 10, 850, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		RunMarketMainEvt rmme = new RunMarketMainEvt(this);
		jtp.addMouseListener(rmme);
		setResizable(false);
		
	}




	public ChatList getCl() {
		return cl;
	}


	public JTabbedPane getJtp() {
		return jtp;
	}
	
//	public static void main(String[] args) throws SQLException {
//		new MarketMain();
//		new RunMarketMain();
//	}//main

}//class
