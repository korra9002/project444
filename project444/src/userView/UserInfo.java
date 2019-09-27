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
	private JLabel jlaAd;
	
	String id;
	RunMarketMain rmm;
	
	
	
	public UserInfo(String id,RunMarketMain rmm) {
	  jlLevelImg=new JLabel();
		this.id=id;
		this.rmm=rmm;
		
		
		jlaId = new JLabel(id);
		Dimension size = jlaId.getPreferredSize();
		int num = (int)size.getWidth();
		jbtPersonalData = new JButton("개인정보변경");
		jbtSell = new JButton("판매내역");
		jbtBuy = new JButton("구매내역");
		jbtLike = new JButton("관심목록");
		jbtLogout = new JButton("로그아웃");
		jbtLevInfo=new JButton("!");
		jlaAd = new JLabel("광고받습니다.");
		Font ft = new Font(Font.DIALOG,Font.BOLD, 20);
		Font ft1 = new Font(Font.DIALOG,Font.BOLD, 30);
		jlaId.setFont(ft1);
		jbtSell.setFont(ft);
		jbtBuy.setFont(ft);
		jbtLike.setFont(ft);
		jbtPersonalData.setFont(ft);
		// setBounds
	  jlLevelImg.setBounds(10, 10, 300, 300);
		jlaId.setBounds(300, 70, num+200, 50);
		jbtLogout.setBounds(630, 50, 120, 70);
		jbtSell.setBounds(50, 350, 300, 100);
		jbtBuy.setBounds(445, 350, 300, 100);
		jbtLike.setBounds(50, 470, 300, 100);
		jbtPersonalData.setBounds(445, 470, 300, 100);
		jbtLevInfo.setBounds(250,50,20,20);
		jlaAd.setBounds(50, 600, 700, 180);
		jlaAd.setOpaque(true);
		jlaAd.setBackground(Color.WHITE);
	  add(jlLevelImg);
		add(jlaId);
		add(jbtPersonalData);
		add(jbtSell);
		add(jbtBuy);
		add(jbtLike);
		add(jbtLogout);
		add(jlaAd);
		add(jbtLevInfo);

		setLayout(null);
//      setResizable(false);
//		setBounds(100, 100, 540, 470);

		setBackground(new Color(0xf6f2ef));
		jbtPersonalData.setBackground(new Color(0xFFCC66));
		jbtBuy.setBackground(new Color(0xFFCC66));	
		jbtSell.setBackground(new Color(0xFFCC66));
		jbtLike.setBackground(new Color(0xFFCC66));	
		jbtLogout.setBackground(new Color(0xFFCC66));	
		jbtLevInfo.setBackground(new Color(0xFFCC66));
		jbtLevInfo.setBorder(new LineBorder(null,0));
	
		// 이벤트처리
		UserInfoEvt uife = new UserInfoEvt(this, rmm);
		jbtPersonalData.addActionListener(uife);
		jbtBuy.addActionListener(uife);
		jbtLike.addActionListener(uife);
		jbtSell.addActionListener(uife);
		jbtLogout.addActionListener(uife);
		// 가시화
		setVisible(true);
		
		
	}// UserMy

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
