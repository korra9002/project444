package userView;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
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
	  jlLevelImg=new JLabel(new ImageIcon("C:/Users/owner/git/project444/project444/src/image/�ٳ�������.png"));
		this.id=id;
		this.rmm=rmm;
		
		
		jlaId = new JLabel(id);
		jbtPersonalData = new JButton("������������");
		jbtSell = new JButton("�Ǹų���");
		jbtBuy = new JButton("���ų���");
		jbtLike = new JButton("���ɸ��");
		jbtLogout = new JButton("�α׾ƿ�");
		jlaAd = new JLabel("����޽��ϴ�.");
		Font ft = new Font(Font.DIALOG,Font.BOLD, 20);
		jlaId.setFont(ft);
		jbtSell.setFont(ft);
		jbtBuy.setFont(ft);
		jbtLike.setFont(ft);
		jbtPersonalData.setFont(ft);
		// setBounds
	  jlLevelImg.setBounds(-20, -70, 300, 250);
		jlaId.setBounds(500, 50, 100, 50);
		jbtLogout.setBounds(650, 50, 100, 50);
		jbtSell.setBounds(50, 200, 300, 100);
		jbtBuy.setBounds(445, 200, 300, 100);
		jbtLike.setBounds(50, 320, 300, 100);
		jbtPersonalData.setBounds(445, 320, 300, 100);
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

		setLayout(null);
//      setResizable(false);
//		setBounds(100, 100, 540, 470);

		setBackground(new Color(0xf6f2ef));
		jbtPersonalData.setBackground(new Color(0xFFCC66));
		jbtBuy.setBackground(new Color(0xFFCC66));	
		jbtSell.setBackground(new Color(0xFFCC66));
		jbtLike.setBackground(new Color(0xFFCC66));	
		jbtLogout.setBackground(new Color(0xFFCC66));	
	
		// �̺�Ʈó��
		UserInfoEvt uife = new UserInfoEvt(this, rmm);
		jbtPersonalData.addActionListener(uife);
		jbtBuy.addActionListener(uife);
		jbtLike.addActionListener(uife);
		jbtSell.addActionListener(uife);
		jbtLogout.addActionListener(uife);
		// ����ȭ
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
