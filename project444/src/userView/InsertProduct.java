package userView;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import userControl.InsertProductEvt;
import userRun.RunMarketMain;

@SuppressWarnings("serial")
public class InsertProduct extends JPanel {

	private JLabel jlbProductImg;
	private JButton jbtSelectImg, jbtOkay, jbtCancel ;
	private JTextField jtfSubject, jtfPrice;
	private JTextArea jtaExplain;
	private JComboBox<String> jcbCategory;
	private DefaultComboBoxModel<String> dcbCategory; 
	


	
	RunMarketMain rmm;
	
	public InsertProduct(RunMarketMain rmm) {
//		super("제품 입력");
		
		this.rmm=rmm;
		
		
		jlbProductImg = new JLabel("제품 이미지",JLabel.CENTER);
		jlbProductImg.setBorder(new EtchedBorder(EtchedBorder.RAISED));//이미지 라벨 테두리 설정
		
		jbtSelectImg = new JButton("사진 등록");
//		jbtSelectImg.addActionListener(new InsertProductEvt(this,rmm));
		
		String[] categoryList= {"디지털/가전","가구/인테리어","유아동/유아도서","생활/가공식품","여성의류","여성잡화",
							"뷰티/미용","남성패션/잡화","스포츠/레저","게임/취미","도서/티켓/음반","반려동물용품","기타 중고물품"};
		
		dcbCategory = new DefaultComboBoxModel<String>(categoryList);
		jcbCategory = new JComboBox<String>(dcbCategory);
		jcbCategory.setBorder(new TitledBorder("카테고리 종류"));
		
		jtfSubject = new JTextField("글 제목");//이벤트처리-클릭 시 텍스트 사라지게
		jtfPrice = new JTextField("가격 입력");//이벤트처리-클릭 시 텍스트 사라지게
		
		jtaExplain = new JTextArea("상세 설명");//이벤트처리-클릭 시 텍스트 사라지게
		JScrollPane jspExplain = new JScrollPane(jtaExplain);
		
		jbtOkay = new JButton("완료");
		jbtCancel = new JButton("취소");
		
		setLayout(null);
		
//		jtp.setBounds(0, 10, 400, 30);
		jlbProductImg.setBounds(50, 30, 220, 200);//이미지 사이즈 120x100
		jbtSelectImg.setBounds(330, 100, 140, 40);
		jcbCategory.setBounds(530, 80, 200, 60);
		jtfSubject.setBounds(50, 270, 680, 40);
		jtfPrice.setBounds(50, 320, 680, 40);
		jspExplain.setBounds(50, 380, 680, 310);
		jbtOkay.setBounds(240, 720, 120, 40);
		jbtCancel.setBounds(400, 720, 120, 40);
		
		add(jlbProductImg);
		add(jbtSelectImg);
		add(jcbCategory);
		add(jtfSubject);
		add(jtfPrice);
		add(jspExplain);
		add(jbtOkay);
		add(jbtCancel);
		InsertProductEvt ipe=new InsertProductEvt(this, rmm);
		
		jbtSelectImg.addActionListener(ipe);
		jbtOkay.addActionListener(ipe);
		jbtCancel.addActionListener(ipe);
		
		jtfSubject.addMouseListener(ipe);
		jtfSubject.addActionListener(ipe);
		jtfPrice.addMouseListener(ipe);
		jtfPrice.addActionListener(ipe);
		jtaExplain.addMouseListener(ipe);
		
		
		
		
		setBounds(100, 100, 420, 640);
		setVisible(true);
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//InsertProduct

	public JLabel getJlbProductImg() {
		return jlbProductImg;
	}//getJlbProductImg

	public JButton getJbtSelectImg() {
		return jbtSelectImg;
	}//getJbtSelectImg

	public JButton getJbtOkay() {
		return jbtOkay;
	}//getJbtOkey

	public JButton getJbtCancel() {
		return jbtCancel;
	}//getJbtCancle

	public JTextField getJtfSubject() {
		return jtfSubject;
	}//getJtfSubject

	public JTextField getJtfPrice() {
		return jtfPrice;
	}//getJtfPrice

	public JTextArea getJtaExplain() {
		return jtaExplain;
	}//getJtaExplain

	public JComboBox<String> getJcbCategory() {
		return jcbCategory;
	}//getJcbCategory

	public DefaultComboBoxModel<String> getDcbCategory() {
		return dcbCategory;
	}//getDcbCategory
	
//	public static void main(String[] args) {
//		new InsertProduct();
//	}//main

}//class
