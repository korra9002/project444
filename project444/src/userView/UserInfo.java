package userView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

import oracle.sql.CLOB;
import userControl.UserInfoEvt;
import userRun.RunMarketMain;

@SuppressWarnings("serial")
public class UserInfo extends JPanel {
	private JLabel jlLevelImg, jlaId;
	private JButton jbtPersonalData, jbtSell, jbtBuy, jbtLike,jbtLogout,jbtLevInfo;
	private JLabel jlaAd,sellInfo;
	
	String id;
	RunMarketMain rmm;
	
	
	
	public UserInfo(String id,RunMarketMain rmm) {
	  jlLevelImg=new JLabel();
	  JLabel jlLine= new JLabel(new ImageIcon("C:/Users/seoyy/git/project444/project444/src/image/아이디받침.png"));
		this.id=id;
		this.rmm=rmm;
		
		/////////////// 
		sellInfo = new JLabel();
	//////////////////////////
		jlaId = new JLabel(id);
		Dimension size = jlaId.getPreferredSize();
		int num = (int)size.getWidth();
		jbtPersonalData = new JButton(new ImageIcon("C:/Users/seoyy/git/project444/project444/src/image/개인정보변경.png"));
		jbtSell = new JButton(new ImageIcon("C:/Users/seoyy/git/project444/project444/src/image/판매내역.png"));
		jbtBuy = new JButton(new ImageIcon("C:/Users/seoyy/git/project444/project444/src/image/구매내역.png"));
		jbtLike = new JButton(new ImageIcon("C:/Users/seoyy/git/project444/project444/src/image/관심목록.png"));
		jbtLogout = new JButton("로그아웃");
		jbtLevInfo=new JButton("?");
		
		
		
		jlaAd = new JLabel(new ImageIcon("C:/Users/seoyy/git/project444/project444/src/image/광고이미지.png"));
		Font ft = new Font(Font.DIALOG,Font.BOLD, 20);
		Font ft1 = new Font(Font.DIALOG,Font.BOLD, 30);
		
		jlaId.setFont(ft1);
//		//////////////////////////
	//	sellInfo.setFont(ft1);
//////////////////////////
		jbtSell.setFont(ft);
		jbtBuy.setFont(ft);
		jbtLike.setFont(ft);
		jbtPersonalData.setFont(ft);
		// setBounds
	  jlLevelImg.setBounds(50,30, 200, 200);
		jlaId.setBounds(260, 80, num+200, 50);
//////////////////////////
		sellInfo.setBounds(260, 130, 200,50);
//////////////////////////
		jlLine.setBounds(200, 120,400 , 5);
		//jbtLogout.setBounds(630, 50, 120, 70);
		jbtLogout.setBounds(630, 50, 120, 40);
		jbtSell.setBounds(50, 310, 345, 110);
		jbtBuy.setBounds(405, 310, 345, 110);
		jbtLike.setBounds(50, 430, 345, 110);
		jbtPersonalData.setBounds(405, 430, 345, 110);
		jbtLevInfo.setBounds(50,50,20,20);
		jlaAd.setBounds(50, 630, 700, 128);
		jlaAd.setOpaque(true);
		jlaAd.setBackground(Color.WHITE);
		
		add(sellInfo);
	  add(jlLevelImg);
		add(jlaId);
		add(jbtPersonalData);
		add(jbtSell);
		add(jbtBuy);
		add(jbtLike);
		add(jbtLogout);
		add(jlaAd);
		add(jbtLevInfo);
		add(jlLine);
		setLayout(null);
//      setResizable(false);
//		setBounds(100, 100, 540, 470);

		setBackground(new Color(0xf6f2ef));
		jbtPersonalData.setBackground(new Color(0xFFCC66));
		jbtBuy.setBackground(new Color(0xFFCC66));	
		jbtSell.setBackground(new Color(0xFFCC66));
		jbtLike.setBackground(new Color(0xFFCC66));	
//		jbtLogout.setBackground(new Color(0xFFCC66));	
		jbtLogout.setBackground(new Color(0xf6f2ef));	
//		jbtLogout.setBorder(new LineBorder(null,1));
		jbtLevInfo.setBackground(new Color(0xFFCC66));
		jbtLevInfo.setBorder(new LineBorder(null,0));
		jbtLogout.setBorder(new LineBorder(Color.GRAY,1));
	
		// 이벤트처리
		UserInfoEvt uife = new UserInfoEvt(this, rmm);
		jbtPersonalData.addActionListener(uife);
		jbtBuy.addActionListener(uife);
		jbtLike.addActionListener(uife);
		jbtSell.addActionListener(uife);
		jbtLogout.addActionListener(uife);
		jbtLevInfo.addActionListener(uife);
		// 가시화
		setVisible(true);
		
		
	}// UserMy

	public JLabel getSellInfo() {
		return sellInfo;
	}

	public JButton getJbtLevInfo() {
		return jbtLevInfo;
	}//getJbtLevInfo


	public JLabel getJlLevelImg() {
		return jlLevelImg;
	}// getJlLevelImg

	public JLabel getjlaId() {
		return jlaId;
	}// getjlaId

	public JButton getJbtPersonalData() {
		return jbtPersonalData;
	}// getJbtPersonalData

	public JButton getJbtSell() {
		return jbtSell;
	}// getJbtSell

	public JButton getJbtBuy() {
		return jbtBuy;
	}// getJbtBuy

	public JButton getJbtLike() {
		return jbtLike;
	}// getJbtLike

	public JLabel getjlaAd() {
		return jlaAd;
	}// getjlaAd
	public JButton getjbtLogout() {
		return jbtLogout;
	}//getjbtLogout
}// class
