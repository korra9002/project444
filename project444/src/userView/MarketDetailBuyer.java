package userView;

import java.awt.Checkbox;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import userControl.MarketDetailBuyerEvt;
import userVO.MarketDetailVO;

public class MarketDetailBuyer extends JDialog {

	private JLabel jlDetailImg;
	private JTextField jtfName, jtfPrice, jtfId, jtfInputDate, jtfCategory;
	private JButton jbtChat;
	private JTextArea jtaStrongPoint;
	private Checkbox ckLike;

	private MarketMain mm;
	private String productCode, id;

	public MarketDetailBuyer(MarketMain mm, MarketDetailVO mdVO, String id) {
		this.id = id;
		jlDetailImg = new JLabel(new ImageIcon("C:/dev/workspace/jdbc_prj/src/img/무민.jpg/"));// 썸네일X / 원본 이미지
//		jlDetailImg.setHorizontalAlignment(JLabel.CENTER);

		this.mm = mm;
		productCode = mdVO.getProductCode();

		JLabel jlId = new JLabel("판매자");
		JLabel jlName = new JLabel("제품이름");
		JLabel jlPrice = new JLabel("가격");
		JLabel jlInputDate = new JLabel("올린일자");
		JLabel jlCategory = new JLabel("카테고리");

		ckLike = new Checkbox("관심목록에 추가");

		// JTextField
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

		jbtChat.setBounds(180, 280, 150, 30);

		ckLike.setBounds(180, 320, 150, 50);

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
		add(ckLike);
		add(jtfInputDate);
		add(jtfCategory);

		setResizable(false);
		setBounds(100, 100, 670, 440);
		setVisible(true);

		MarketDetailBuyerEvt mdbe = new MarketDetailBuyerEvt(mm, this);

		jbtChat.addActionListener(mdbe);
		ckLike.addMouseListener(mdbe);

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

	public Checkbox getCkLike() {
		return ckLike;
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
