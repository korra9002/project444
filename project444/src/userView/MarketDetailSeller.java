package userView;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import adminView.ScrollBarUI;
import userControl.MarketDetailBuyerEvt;
import userControl.MarketDetailSellerEvt;
import userControl.SaleListEvt;
import userRun.RunMarketMain;
import userVO.MarketDetailVO;

public class MarketDetailSeller extends JDialog {

	private JLabel jlDetailImg;
	private JTextField jtfName, jtfPrice, jtfId, jtfInputDate, jtfCategory;
	private JButton jbtComplete, jbtDelete, jbtChange;
	private JTextArea jtaStrongPoint;
	private String id;
	private MarketDetailVO mdVO;
	private SaleListEvt sle;
	DecimalFormat df = new DecimalFormat("#,###,###");

	public SaleListEvt getSle() {
		return sle;
	}

	public DecimalFormat getDf() {
		return df;
	}

	public MarketDetailSeller(MarketMain mm, MarketDetailVO mdVO, String id, SaleListEvt sle) {
		this.sle = sle;
		this.id = id;
		this.mdVO = mdVO;
//		jlDetailImg = new JLabel(new ImageIcon("C:/dev/workspace/jdbc_prj/src/img/무민.jpg/"));//썸네일X / 원본 이미지
//		jlDetailImg.setHorizontalAlignment(JLabel.CENTER);

		JLabel jlId = new JLabel("판매자");
		JLabel jlName = new JLabel("제품이름");
		JLabel jlPrice = new JLabel("가격");
		JLabel jlInputDate = new JLabel("올린일자");
		JLabel jlCategory = new JLabel("카테고리");

		// JTextField
		jlDetailImg = new JLabel();
		if (new File(RunMarketMain.imgPath + "/" + mdVO.getImage()).exists()) {

			jlDetailImg.setIcon(new ImageIcon(new ImageIcon(RunMarketMain.imgPath + "/" + mdVO.getImage()).getImage()
					.getScaledInstance(320, 320, Image.SCALE_SMOOTH)));
		} else {
			jlDetailImg.setText(mdVO.getImage());
		}

		jtfName = new JTextField(mdVO.getProductName() + "(" + mdVO.getProductCode() + ")");
		jtfPrice = new JTextField((df.format(mdVO.getPrice())));
		jtfId = new JTextField(mdVO.getSellerID());
		jtfInputDate = new JTextField(mdVO.getUpload_date());
		jtfCategory = new JTextField(mdVO.getCategory());

		// JTextArea
		jtaStrongPoint = new JTextArea("상세정보");
		jtaStrongPoint = new JTextArea(mdVO.getpDetail());
		JScrollPane jsp = new JScrollPane(jtaStrongPoint);

		// JButton
		jbtComplete = new JButton("판매완료");
		jbtDelete = new JButton("삭제");
		jbtChange = new JButton("수정");

		// 바꾸지 못하게
		jtfName.setEditable(false);
		jtfPrice.setEditable(false);
		jtfId.setEditable(false);
		jtfInputDate.setEditable(false);
		jtfCategory.setEditable(false);
		jtaStrongPoint.setEditable(false);

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

		jbtComplete.setBounds(240, 350, 88, 30);
		jbtDelete.setBounds(20, 350, 88, 30);
		jbtChange.setBounds(130, 350, 88, 30);

		// 색 디자인
		Container c = getContentPane();
		c.setBackground(new Color(0xf6f2ef));
		jbtChange.setBackground(new Color(0xFFCC66));
		jbtComplete.setBackground(new Color(0xFFCC66));
		jbtDelete.setBackground(new Color(0xFFCC66));
		jsp.getVerticalScrollBar().setUI(new ScrollBarUI());
		jtaStrongPoint.setLineWrap(true);

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
		add(jbtComplete);
		add(jbtDelete);
		add(jbtChange);
		add(jtfInputDate);
		add(jtfCategory);

		setResizable(false);
		setBounds(100, 100, 670, 440);
		setVisible(true);

		// setBounds

		MarketDetailSellerEvt mdse = new MarketDetailSellerEvt(mm, this);

		jbtComplete.addActionListener(mdse);
		jbtDelete.addActionListener(mdse);
		jbtChange.addActionListener(mdse);

	}// MarketDetail

	public MarketDetailVO getMdVO() {
		return mdVO;
	}

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

	public JButton getJbtComplete() {
		return jbtComplete;
	}

	public JButton getJbtDelete() {
		return jbtDelete;
	}

	public JButton getJbtChange() {
		return jbtChange;
	}

	public JTextArea getJtaStrongPoint() {
		return jtaStrongPoint;
	}

	public String getId() {
		return id;
	}

//	public static void main(String[] args) {
//		new MarketDetailSeller();
//	}// main

}// class
