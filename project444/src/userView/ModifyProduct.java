package userView;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ModifyProduct extends JFrame {

	private JLabel jlbProductImg;
	private JButton jbtSelectImg, jbtOkey, jbtCancle ;
	private JTextField jtfSubject, jtfPrice;
	private JTextArea jtaExplain;
	private JComboBox<String> jcbCategory;
	private DefaultComboBoxModel<String> dcbCategory;
	
	public ModifyProduct() {
		super("제품 입력");
		jlbProductImg = new JLabel("제품 이미지",JLabel.CENTER);
		jlbProductImg.setBorder(new EtchedBorder(EtchedBorder.RAISED));//이미지 라벨 테두리 설정
		
		jbtSelectImg = new JButton("사진 등록");
		
		String[] categoryList= {"디지털/가전","가구/인테리어","유아동/유아도서","생활/가공식품","여성의류","여성잡화",
							"뷰티/미용","남성패션/잡화","스포츠/레저","게임/취미","도서/티켓/음반","반려동물용품","기타 중고물품"};
		
		dcbCategory = new DefaultComboBoxModel<String>(categoryList);
		jcbCategory = new JComboBox<String>(dcbCategory);
		jcbCategory.setBorder(new TitledBorder("카테고리 종류"));
		
		jtfSubject = new JTextField("글 제목");//이벤트처리-클릭 시 텍스트 사라지게
		jtfPrice = new JTextField("가격 입력");//이벤트처리-클릭 시 텍스트 사라지게
		
		jtaExplain = new JTextArea("상세 설명");//이벤트처리-클릭 시 텍스트 사라지게
		JScrollPane jspExplain = new JScrollPane(jtaExplain);
		
		jbtOkey = new JButton("완료");
		jbtCancle = new JButton("취소");
		
		setLayout(null);
		
		jlbProductImg.setBounds(10, 20, 120, 100);//이미지 사이즈 120x100
		jbtSelectImg.setBounds(150, 55, 100, 30);
		jcbCategory.setBounds(270, 43, 120, 50);
		jtfSubject.setBounds(10, 160, 385, 30);
		jtfPrice.setBounds(10, 200, 385, 30);
		jspExplain.setBounds(10, 240, 385, 240);
		jbtOkey.setBounds(100, 500, 80, 30);
		jbtCancle.setBounds(230, 500, 80, 30);
		
		add(jlbProductImg);
		add(jbtSelectImg);
		add(jcbCategory);
		add(jtfSubject);
		add(jtfPrice);
		add(jspExplain);
		add(jbtOkey);
		add(jbtCancle);
		
		setBounds(100, 100, 420, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//InsertProduct
	
	public static void main(String[] args) {
		new ModifyProduct();
	}//main

}//class
