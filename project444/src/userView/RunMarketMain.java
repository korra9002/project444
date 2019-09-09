package userView;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;


 
public class RunMarketMain extends JFrame{

	public RunMarketMain() throws SQLException {
		setLayout(null);
		JTabbedPane jtp = new JTabbedPane();
		jtp.add("Ȩ", new MarketMain());
		jtp.add("��ǰ�Է�", new InsertProduct());
		jtp.add("ä��", new Chat());
		jtp.add("MyPage", new UserInfo());
		
		jtp.setBounds(10, 10, 500, 600);
		
	
		add(jtp);
		setVisible(true);
		setBounds(10, 10, 550, 670);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) throws SQLException {
//		new MarketMain();
		new RunMarketMain();
	}//main

}//class
