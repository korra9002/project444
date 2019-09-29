package userRun;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import userControl.RunMarketMainEvt;
import userView.ChatList;
import userView.InsertProduct;
import userView.MarketMain;
import userView.UserInfo;


 
public class RunMarketMain extends JFrame{
public static String userId;
public static String imgPath = "c:/dev/userRecieveFile";
public static String basicPath = "C:/dev/pro4Images/";
public static String ip;
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
		   UIManager.put("jtp.selected", new Color(0XFFFFFF));
		   jtp.updateUI();
		Container c = getContentPane();
		c.setBackground(Color.white);
		
		// 텝 테두리 없애기 //
				jtp.setUI(new BasicTabbedPaneUI() {
				        private final Insets borderInsets = new Insets(0, 0, 0, 0);
				        @Override
				        protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
				        }
				        @Override
				        protected Insets getContentBorderInsets(int tabPlacement) {
				            return borderInsets;
				        }
				    });
				
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
