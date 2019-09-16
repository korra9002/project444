package userView;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import userControl.UserInfoEvt;
import userRun.RunMarketMain;

public class UserInfo extends JPanel {
	private JLabel jlLevelImg, jlaId;
	private JButton jbtPersonalData, jbtSell, jbtBuy, jbtLike;
	private JTextArea jtaAd;
	
	String id;
	RunMarketMain rmm;
	
	
	
	public UserInfo(String id,RunMarketMain rmm) {
//	  jlLevelImg=new JLabel(new ImageIcon("C:/dev/workspace/jdbc_prj/src/imges/c1_c1.png/"));
		this.id=id;
		this.rmm=rmm;
		
		
		jlaId = new JLabel(id);
		jbtPersonalData = new JButton("개인정보");
		jbtSell = new JButton("판매내역");
		jbtBuy = new JButton("구매내역");
		jbtLike = new JButton("관심목록");
		jtaAd = new JTextArea("광고");

		// setBounds
//	  jlLevelImg.setBounds(50, 15, 200, 150);
		jlaId.setBounds(260, 20, 90, 30);
		jbtPersonalData.setBounds(400, 20, 90, 70);
		jbtSell.setBounds(50, 200, 90, 30);
		jbtBuy.setBounds(200, 200, 90, 30);
		jbtLike.setBounds(350, 200, 90, 30);
		jtaAd.setBounds(50, 250, 450, 180);

//	  add(jlLevelImg);
		add(jlaId);
		add(jbtPersonalData);
		add(jbtSell);
		add(jbtBuy);
		add(jbtLike);
		add(jtaAd);

		setLayout(null);
//      setResizable(false);
		setBounds(100, 100, 540, 470);

		// 이벤트처리
		UserInfoEvt uife = new UserInfoEvt(this, rmm);
		jbtPersonalData.addActionListener(uife);
		jbtBuy.addActionListener(uife);
		jbtLike.addActionListener(uife);
		jbtSell.addActionListener(uife);
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

	public JTextArea getJtaAd() {
		return jtaAd;
	}// getJtaAd

}// class
