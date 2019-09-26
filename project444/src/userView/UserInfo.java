package userView;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import userControl.UserInfoEvt;
import userRun.RunMarketMain;

@SuppressWarnings("serial")
public class UserInfo extends JPanel {
	private JLabel jlLevelImg, jlaId;
	private JButton jbtPersonalData, jbtSell, jbtBuy, jbtLike,jbtLogout;
	private JLabel jlaAd;
	
	String id;
	RunMarketMain rmm;
	
	
	
	public UserInfo(String id,RunMarketMain rmm) {
//	  jlLevelImg=new JLabel(new ImageIcon("C:/dev/workspace/jdbc_prj/src/kr/co/sist/admin/img/c1_c1.png"));
		this.id=id;
		this.rmm=rmm;
		
		
		jlaId = new JLabel(id);
		jbtPersonalData = new JButton("개인정보변경");
		jbtSell = new JButton("판매내역");
		jbtBuy = new JButton("구매내역");
		jbtLike = new JButton("관심목록");
		jbtLogout = new JButton("로그아웃");
		jlaAd = new JLabel("광고받습니다.");
		Font ft = new Font(Font.DIALOG,Font.BOLD, 40);
		Font ft2 = new Font(Font.DIALOG,Font.BOLD, 20);
		jlaId.setFont(ft);
		jbtSell.setFont(ft2);
		jbtBuy.setFont(ft2);
		jbtLike.setFont(ft2);
		jbtPersonalData.setFont(ft2);
		// setBounds
//	  jlLevelImg.setBounds(50, 15, 200, 150);
		jlaId.setBounds(50, 50, 600, 50);
		jbtLogout.setBounds(650, 50, 100, 50);
		jbtSell.setBounds(50, 200, 300, 100);
		jbtBuy.setBounds(445, 200, 300, 100);
		jbtLike.setBounds(50, 320, 300, 100);
		jbtPersonalData.setBounds(445, 320, 300, 100);
		jlaAd.setBounds(50, 600, 700, 180);
		jlaAd.setOpaque(true);
		jlaAd.setBackground(Color.WHITE);
//	  add(jlLevelImg);
		add(jlaId);
		add(jbtPersonalData);
		add(jbtSell);
		add(jbtBuy);
		add(jbtLike);
		add(jbtLogout);
		add(jlaAd);

		setLayout(null);
//      setResizable(false);
//		setBounds(100, 100, 540, 470);

		setBackground(new Color(0xFFFFFF));
		jbtPersonalData.setBackground(new Color(0xc8ddf2));
		jbtBuy.setBackground(new Color(0xc8ddf2));	
		jbtSell.setBackground(new Color(0xc8ddf2));
		jbtLike.setBackground(new Color(0xc8ddf2));	
		jbtLogout.setBackground(new Color(0xc8ddf2));	
	
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
