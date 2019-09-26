package userView;

import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import userControl.ModifyProductEvt;
import userControl.SaleListEvt;
import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.InsertProductVO;
import userVO.MarketDetailVO;
import userVO.SaleListVO;

@SuppressWarnings("serial")
public class ModifyProduct extends JFrame {

	private JLabel jlbProductImg, jlCategory ;
	private JButton jbtSelectImg, jbtOkay, jbtCancel ;
	private JTextField jtfSubject, jtfPrice;
	private JTextArea jtaExplain;
	private JComboBox<String> jcbCategory;
	private DefaultComboBoxModel<String> dcbCategory;
	
	public ModifyProduct(MarketDetailVO mdVO, SaleListEvt sle, RunMarketMain rmm) {
		super("상품 수정");
		
		String selectedValue0=mdVO.getImage();//이미지
		String selectedValue1=mdVO.getProductName()+"("+mdVO.getProductCode()+")"; 
		int selectedValue2=mdVO.getPrice();	//가격	
		String selectedValue3=mdVO.getCategory(); //카테고리
		String selectedValue4=mdVO.getpDetail(); // 상품디테일

		System.out.println(selectedValue3);
		
		jlbProductImg = new JLabel();
		if(new File(RunMarketMain.imgPath+"/"+selectedValue0).exists()) {
			
			jlbProductImg.setIcon(new ImageIcon(new ImageIcon(RunMarketMain.imgPath+"/"+selectedValue0).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}else {
			jlbProductImg.setText(selectedValue0);
//			jlbProductImg.setIcon(new ImageIcon(RunMarketMain.imgPath+"/"+"default.png"));
		}
		jlbProductImg.setBorder(new EtchedBorder(EtchedBorder.RAISED));//이미지 라벨 테두리 설정
		
		jbtSelectImg = new JButton("사진 등록");
		
		String[] categoryList= {"디지털/가전","가구/인테리어","유아동/유아도서","생활/가공식품","여성의류","여성잡화",
							"뷰티/미용","남성패션/잡화","스포츠/레저","게임/취미","도서/티켓/음반","반려동물용품","기타 중고물품"};
		
		dcbCategory = new DefaultComboBoxModel<String>(categoryList);
//		jcbCategory = new JComboBox<String>(dcbCategory);
	//	jcbCategory.setBorder(new TitledBorder("카테고리 종류"));
	//	jcbCategory.setSelectedItem(selectedValue3);
	//	jcbCategory.setEditable(false);
		
		jlCategory=new JLabel(selectedValue3);
		jlCategory.setBorder(new TitledBorder("카테고리 종류"));

		
		jtfSubject = new JTextField(selectedValue1);//이벤트처리-클릭 시 텍스트 사라지게
		jtfSubject.setEditable(false);
		
		jtfPrice = new JTextField(String.valueOf(selectedValue2));//이벤트처리-클릭 시 텍스트 사라지게
		
		
		///////////////////////// 상세설명 가져오기 /////////////////////////////////////////

		
		String temp_flag = "S";
		
		UserDAO uDAO=UserDAO.getInstance();
//		List<SaleListVO> list =
		

		jtaExplain = new JTextArea(selectedValue4);//이벤트처리-클릭 시 텍스트 사라지게

		
		///////////////////////// 상세설명 가져오기 끝 /////////////////////////////////////////
		
		
		JScrollPane jspExplain = new JScrollPane(jtaExplain);
		
		jbtOkay = new JButton("완료");
		jbtCancel = new JButton("취소");
		
		setLayout(null);
		
		jlbProductImg.setBounds(10, 20, 100, 100);//이미지 사이즈 120x100
//		jbtSelectImg.setBounds(150, 55, 100, 30);
		jlCategory.setBounds(270, 43, 120, 50);
		jtfSubject.setBounds(10, 160, 385, 30);
		jtfPrice.setBounds(10, 200, 385, 30);
		jspExplain.setBounds(10, 240, 385, 240);
		jbtOkay.setBounds(100, 500, 80, 30);
		jbtCancel.setBounds(230, 500, 80, 30);
		
		add(jlbProductImg);
//		add(jbtSelectImg);
		add(jlCategory);
		add(jtfSubject);
		add(jtfPrice);
		add(jspExplain);
		add(jbtOkay);
		add(jbtCancel);
		 
		ModifyProductEvt mpe=new ModifyProductEvt(this, sle,  rmm);
		
		jbtOkay.addActionListener(mpe);
		jbtCancel.addActionListener(mpe);
		jtfPrice.addActionListener(mpe);
		jtaExplain.addMouseListener(mpe);
		
		
		
		setBounds(100, 100, 420, 600);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}//InsertProduct

	public JButton getJbtSelectImg() {
		return jbtSelectImg;
	}

	public JButton getJbtOkay() {
		return jbtOkay;
	}

	public JButton getJbtCancel() {
		return jbtCancel;
	}

	public JTextField getJtfSubject() {
		return jtfSubject;
	}

	public JTextField getJtfPrice() {
		return jtfPrice;
	}

	public JTextArea getJtaExplain() {
		return jtaExplain;
	}

	public JComboBox<String> getJcbCategory() {
		return jcbCategory;
	}
	
//	public static void main(String[] args) {
//	new ModifyProduct();
//	}//main

}//class
