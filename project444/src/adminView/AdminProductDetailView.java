package adminView;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import adminControl.AdminCheckDetailViewEvt;
//import adminControl.AdminCheckDetailEvt;
import adminControl.AdminMainEvt;
import adminControl.AdminProductDetailViewEvt;
import adminVO.CheckDetailVO;
import adminVO.ProductDetailVO;
import userRun.RunMarketMain;
 
@SuppressWarnings("serial")
public class AdminProductDetailView extends JDialog {

	private JLabel jlDetailImg;
	private JTextField jtfProductName, jtfPrice, jtfUserId, jtfUploadDate, jtfCategory;
	private JButton jbtGrant, jbtReject, jbtOk;
	private JTextArea jtaReason, jtaInfo;
	private String code;
 
	private AdminMainView amv;

	public AdminProductDetailView(AdminMainView amv, AdminMainEvt ame, ProductDetailVO pdVO) {
		super(amv, "제품상세", true);////////
		this.amv = amv;////////////

		jlDetailImg = new JLabel();// 썸네일X / 원본 이미지
		jlDetailImg.setBorder(new EtchedBorder(EtchedBorder.RAISED));// 이미지 라벨 테두리 설정

		JLabel jlId = new JLabel("판매자");
		JLabel jlName = new JLabel("제품이름");
		JLabel jlPrice = new JLabel("가격");
		JLabel jlInputDate = new JLabel("올린일자");
		JLabel jlCategory = new JLabel("카테고리");
		JLabel jlReason = new JLabel("거부사유");

		// JTextField
		jtfProductName = new JTextField();
		jtfPrice = new JTextField();
		jtfUserId = new JTextField();
		jtfUploadDate = new JTextField();
		jtfCategory = new JTextField();

		// 바꾸지 못하게
		jtfProductName.setEditable(false);
		jtfPrice.setEditable(false);
		jtfUserId.setEditable(false);
		jtfUploadDate.setEditable(false);
		jtfCategory.setEditable(false);

		// JTextArea
		jtaInfo = new JTextArea();
		jtaReason = new JTextArea();
		JScrollPane jspInfo = new JScrollPane(jtaInfo);
		JScrollPane jspReason = new JScrollPane(jtaReason);
		jtaInfo.setEditable(false);
		jtaReason.setEditable(false);

//		jbtGrant=new JButton("판매승인");
//		jbtReject=new JButton("판매거부");
		jbtOk = new JButton("확인");

		// set text
		if (new File(AdminLoginView.imgPath + "/" + pdVO.getImg_file()).exists()) {

			jlDetailImg.setIcon(new ImageIcon(new ImageIcon(AdminLoginView.imgPath + "/" + pdVO.getImg_file()).getImage()
					.getScaledInstance(320, 320, Image.SCALE_SMOOTH)));
		} else {
			jlDetailImg.setText(pdVO.getImg_file());
		}
//		jlDetailImg.setText(pdVO.getImg_file());
		jtfProductName.setText(pdVO.getProduct_name());
		jtfPrice.setText(pdVO.getPrice());
		jtfUserId.setText(pdVO.getUser_id());
		jtfUploadDate.setText(pdVO.getUpload_date());
		jtfCategory.setText(pdVO.getCategory());
		jtaReason.setText(pdVO.getRejectMsg());
		jtaInfo.setText(pdVO.getInfo());
		code = pdVO.getProduct_code();

		// setBounds
		jlDetailImg.setBounds(15, 15, 320, 320);

		jlName.setBounds(345, 15, 70, 30);
		jlPrice.setBounds(345, 50, 70, 30);
		jlId.setBounds(345, 85, 70, 30);
		jlInputDate.setBounds(345, 120, 70, 30);
		jlCategory.setBounds(345, 155, 70, 30);
		jlReason.setBounds(345, 190, 70, 30);

		jtfProductName.setBounds(420, 15, 215, 30);
		jtfPrice.setBounds(420, 50, 215, 30);
		jtfUserId.setBounds(420, 85, 215, 30);
		jtfUploadDate.setBounds(420, 120, 215, 30);
		jtfCategory.setBounds(420, 155, 215, 30);

		jspReason.setBounds(420, 190, 220, 65);
		jspInfo.setBounds(345, 260, 295, 120);

//		jbtGrant.setBounds(20, 350, 88, 30);
//		jbtReject.setBounds(130, 350, 88, 30);
		jbtOk.setBounds(240, 350, 88, 30);

		// 배치
		setLayout(null);
		add(jlDetailImg);
		add(jlName);
		add(jlPrice);
		add(jlId);
		add(jlInputDate);
		add(jlCategory);
		add(jlReason);
		add(jtfProductName);
		add(jtfPrice);
		add(jtfUserId);
		add(jspInfo);
		add(jspReason);
//		add(jbtGrant);
//		add(jbtReject);
		add(jbtOk);
		add(jtfUploadDate);
		add(jtfCategory);

		// event
		AdminProductDetailViewEvt apdve = new AdminProductDetailViewEvt(this, ame);
		jbtOk.addActionListener(apdve);

		setResizable(false);
		setBounds(amv.getX() + 800, amv.getY() + 50, 670, 440);
		setVisible(true);

	}// MarketDetail

	public JLabel getJlDetailImg() {
		return jlDetailImg;
	}

	public JTextField getJtfProductName() {
		return jtfProductName;
	}

	public JTextField getJtfPrice() {
		return jtfPrice;
	}

	public JTextField getJtfUserId() {
		return jtfUserId;
	}

	public JTextField getJtfUploadDate() {
		return jtfUploadDate;
	}

	public JTextField getJtfCategory() {
		return jtfCategory;
	}

	public JButton getJbtGrant() {
		return jbtGrant;
	}

	public JButton getJbtReject() {
		return jbtReject;
	}

	public JButton getJbtOk() {
		return jbtOk;
	}

	public JTextArea getJtaInfo() {
		return jtaInfo;
	}

	public String getCode() {
		return code;
	}

	public AdminMainView getAmv() {
		return amv;
	}

}// class
