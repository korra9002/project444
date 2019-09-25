package userView;

import java.awt.Checkbox;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import userControl.MarketDetailBuyerEvt;
import userControl.MarketDetailSellerEvt;
import userRun.RunMarketMain;
import userVO.MarketDetailVO;


public class MarketDetailSeller extends JDialog {
	
	
	private JLabel jlDetailImg;
	private JTextField jtfName, jtfPrice, jtfId,jtfInputDate,jtfCategory;
	private JButton jbtComplete, jbtDelete, jbtChange;
	private JTextArea jtaStrongPoint;
	private String id;
	private MarketDetailVO mdVO;
	public MarketDetailSeller() {
		
	}//MarketDetailSeller
	
	public MarketDetailSeller(MarketMain mm, MarketDetailVO mdVO, String id) {
		this.id = id;
		this.mdVO = mdVO;
//		jlDetailImg = new JLabel(new ImageIcon("C:/dev/workspace/jdbc_prj/src/img/����.jpg/"));//�����X / ���� �̹���
//		jlDetailImg.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel jlId=new JLabel("�Ǹ���");
		JLabel jlName=new JLabel("��ǰ�̸�");
		JLabel jlPrice=new JLabel("����");
		JLabel jlInputDate=new JLabel("�ø�����");
		JLabel jlCategory=new JLabel("ī�װ�");

		
		//JTextField
		jlDetailImg = new JLabel();
		if(new File(RunMarketMain.imgPath+"/"+mdVO.getImage()).exists()) {
			
			jlDetailImg.setIcon(new ImageIcon(new ImageIcon(RunMarketMain.imgPath+"/"+mdVO.getImage()).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
		}else {
			jlDetailImg.setText(mdVO.getImage());
		}
		
		
		jtfName=new JTextField(mdVO.getProductName()+"("+mdVO.getProductCode()+")");
		jtfPrice=new JTextField(Integer.toString(mdVO.getPrice()));
		jtfId=new JTextField(mdVO.getSellerID());
		jtfInputDate=new JTextField(mdVO.getUpload_date());
		jtfCategory=new JTextField(mdVO.getCategory());
		
		
		//JTextArea
		jtaStrongPoint=new JTextArea("������");
		jtaStrongPoint=new JTextArea(mdVO.getpDetail());
		JScrollPane jsp=new JScrollPane(jtaStrongPoint);
		
		//JButton
		jbtComplete=new JButton("�ǸſϷ�");
		jbtDelete=new JButton("����");
		jbtChange=new JButton("����");
		
		//�ٲ��� ���ϰ�
		jtfName.setEditable(false);
		jtfPrice.setEditable(false);
		jtfId.setEditable(false);
		jtfInputDate.setEditable(false);
		jtfCategory.setEditable(false);
		jtaStrongPoint.setEditable(false);
		
		
		//setBounds
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

		
		//��ġ
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
		
		//setBounds
		
		MarketDetailSellerEvt mdse=new MarketDetailSellerEvt(mm, this);
		
			
		jbtComplete.addActionListener(mdse);
		jbtDelete.addActionListener(mdse);
		jbtChange.addActionListener(mdse);
		
		
	}//MarketDetail

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
	

}//class
