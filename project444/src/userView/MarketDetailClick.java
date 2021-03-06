package userView;

import java.awt.Checkbox;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MarketDetailClick extends JFrame {
	
	private JLabel jlDetailImg;
	private JTextField jtfName, jtfPrice, jtfId,jtfInputDate,jtfCategory;
	private JButton jbtStandBy;
	private JTextArea jtaStrongPoint;

	
	public MarketDetailClick() {
		
		jlDetailImg = new JLabel(new ImageIcon("C:/dev/workspace/jdbc_prj/src/img/무민.jpg/"));//썸네일X / 원본 이미지
//		jlDetailImg.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel jlId=new JLabel("판매자");
		JLabel jlName=new JLabel("제품이름");
		JLabel jlPrice=new JLabel("가격");
		JLabel jlInputDate=new JLabel("올린일자");
		JLabel jlCategory=new JLabel("카테고리");

		
		//JTextField
		jtfName=new JTextField();
		jtfPrice=new JTextField();
		jtfId=new JTextField();
		jtfInputDate=new JTextField();
		jtfCategory=new JTextField();
		
		//바꾸지 못하게
		jtfName.setEditable(false);
		jtfPrice.setEditable(false);
		jtfId.setEditable(false);
		jtfInputDate.setEditable(false);
		jtfCategory.setEditable(false);
		
		//JTextArea
		jtaStrongPoint=new JTextArea();
		JScrollPane jsp=new JScrollPane(jtaStrongPoint);
		
		jbtStandBy=new JButton("구매완료 대기중");
		
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
		
		jbtStandBy.setBounds(200, 300, 130, 30);

		
		//배치
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
		add(jbtStandBy);
		add(jtfInputDate);
		add(jtfCategory);	

		
		setResizable(false);
		setBounds(100, 100, 670, 440);
		setVisible(true);
		
		//setBounds
		
		
	}//MarketDetail

	public static void main(String[] args) {
		new MarketDetailClick();
	}// main
	

}//class
