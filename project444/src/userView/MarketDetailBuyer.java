package userView;

import java.awt.Image;
import java.io.File;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import userControl.InterestListEvt;
import userControl.MarketDetailBuyerEvt;
import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.InterestListVO;
import userVO.MarketDetailVO;

public class MarketDetailBuyer extends JDialog {

	private JLabel jlDetailImg;
	private JTextField jtfName, jtfPrice, jtfId, jtfInputDate, jtfCategory;
	private JButton jbtChat;
	private JTextArea jtaStrongPoint;
	private JCheckBox jckLike;
	private MarketMain mm;
	private String productCode, id;
	private InterestListEvt ile;
	public MarketDetailBuyer(MarketMain mm, MarketDetailVO mdVO, String id, InterestListEvt ile) throws SQLException {
		this.id = id;
//		jlDetailImg = new JLabel(new ImageIcon("C:/dev/workspace/jdbc_prj/src/img/무민.jpg/"));// 썸네일X / 원본 이미지
//		jlDetailImg.setHorizontalAlignment(JLabel.CENTER);
		this.ile = ile;
		this.mm = mm;
		productCode = mdVO.getProductCode();

		JLabel jlId = new JLabel("판매자");
		JLabel jlName = new JLabel("제품이름");
		JLabel jlPrice = new JLabel("가격");
		JLabel jlInputDate = new JLabel("올린일자");
		JLabel jlCategory = new JLabel("카테고리");

		jckLike = new JCheckBox("관심목록에 추가");
		///////////변경사항 //////////////////////--- 나중에 Evt로 옮기기
		InterestListVO irVO = new InterestListVO(productCode, id);
		UserDAO uDAO = UserDAO.getInstance();
		String flag = uDAO.selectInterestCheck(irVO);
		if(flag.isEmpty()) {
			jckLike.setSelected(false);
		}else {
			jckLike.setSelected(true);
		}
		
		
		
		// JTextField
		jlDetailImg = new JLabel();
		if(new File(RunMarketMain.imgPath+"/"+mdVO.getImage()).exists()) {
			
			jlDetailImg.setIcon(new ImageIcon(new ImageIcon(RunMarketMain.imgPath+"/"+mdVO.getImage()).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
		}else {
			jlDetailImg.setText(mdVO.getImage());
		}
		
		jtfName = new JTextField(mdVO.getProductName());
		jtfPrice = new JTextField(Integer.toString(mdVO.getPrice()));
		jtfId = new JTextField(mdVO.getSellerID());
		jtfInputDate = new JTextField(mdVO.getUpload_date());
		jtfCategory = new JTextField(mdVO.getCategory());

		// 바꾸지 못하게
		jtfName.setEditable(false);
		jtfPrice.setEditable(false);
		jtfId.setEditable(false);
		jtfInputDate.setEditable(false);
		jtfCategory.setEditable(false);

		// JTextArea
		jtaStrongPoint = new JTextArea(mdVO.getpDetail());
		JScrollPane jsp = new JScrollPane(jtaStrongPoint);
		jtaStrongPoint.setEditable(false);

		jbtChat = new JButton("채팅으로 거래하기");

		// setBounds
		jlDetailImg.setBounds(15, 15, 320, 320);

		jlName.setBounds(345, 50, 70, 30);
		jlPrice.setBounds(345, 85, 70, 30);
		jlId.setBounds(345, 120, 70, 30);
		jlInputDate.setBounds(345, 155, 70, 30);
		jlCategory.setBounds(345, 190, 70, 40);

		jtfName.setBounds(420, 50, 215, 30);
		jtfPrice.setBounds(420, 85, 215, 30);
		jtfId.setBounds(420, 120, 215, 30);
		jtfInputDate.setBounds(420, 155, 215, 30);
		jtfCategory.setBounds(420, 190, 215, 30);

		jsp.setBounds(345, 260, 295, 120);

		jbtChat.setBounds(20, 350, 150, 30);

		jckLike.setBounds(220, 346, 150, 50);

		// 배치
		setLayout(null);
		add(jlDetailImg);
		add(jlName);
		add(jlPrice);
		add(jlId);
		add(jlInputDate);
		add(jlCategory);
		add(jtfName);
		add(jtfPrice);
		add(jtfId);
		add(jsp);
		add(jbtChat);
		add(jckLike);
		add(jtfInputDate);
		add(jtfCategory);

		setResizable(false);
		setBounds(100, 100, 670, 440);
		setVisible(true);

		MarketDetailBuyerEvt mdbe = new MarketDetailBuyerEvt(mm, this,ile);

		jbtChat.addActionListener(mdbe);
////////////////////////////변경사항/////////////////////////////////////////
		jckLike.addActionListener(mdbe);
/////////////////////////////////////////////////////////////////////////
	}// MarketDetail

	public JLabel getJlDetailImg() {
		return jlDetailImg;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfPrice() {
		return jtfPrice;
	}

	public JTextField getJtfId() {
		return jtfId;
	}

	public JTextField getJtfInputDate() {
		return jtfInputDate;
	}

	public JTextField getJtfCategory() {
		return jtfCategory;
	}

	public JButton getJbtChat() {
		return jbtChat;
	}

	public JTextArea getJtaStrongPoint() {
		return jtaStrongPoint;
	}

	public JCheckBox getjckLike() {
		return jckLike;
	}

	public MarketMain getMm() {
		return mm;
	}

	public String getProductCode() {
		return productCode;
	}

	public String getId() {
		return id;
	}

//	public static void main(String[] args) {
//		new MarketDetailBuyer();
//	}// main
//	

}// class
