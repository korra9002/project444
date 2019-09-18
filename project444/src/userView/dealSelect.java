package userView;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class dealSelect extends JFrame {
	
	private JButton jbtsell , jbtcancell;	
	private DefaultTableModel dtmAreaList;
	private JTable jtpAreaList;
	private JTextArea jta;
	
	
	public dealSelect() {
		
		jbtsell=new JButton("판매완료");
		jbtcancell=new JButton("취소");
		
		jta=new JTextArea("거래 상대를 선택하세요");
		
		jta.setEditable(false);
		
		String[] areaColumn= {"ID","지역"};
		dtmAreaList=new DefaultTableModel(areaColumn,2) {
		
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
		
	};
	
	jtpAreaList=new JTable(dtmAreaList);
	
	JScrollPane jspAreaList=new JScrollPane(jtpAreaList);
	
	jtpAreaList.setRowHeight(30);
	jtpAreaList.getTableHeader().setReorderingAllowed(false);
	
	jtpAreaList.getColumnModel().getColumn(0).setPreferredWidth(50);
	jtpAreaList.getColumnModel().getColumn(1).setPreferredWidth(30);
	
	// 컴포넌트 배치
	

	jspAreaList.setBounds(30, 180, 400, 200);
	jbtsell.setBounds(130, 400, 90, 30);
	jbtcancell.setBounds(250, 400, 90, 30);
	jta.setBounds(30, 50, 400, 90);
	
	setLayout(null);
	add(jspAreaList);
	add(jbtsell);
	add(jbtcancell);
	add(jta);
	
//	setResizable(false);
	setBounds(100, 100, 500, 600);
	setVisible(true);
	
		
	}//dealSelect
	
	     //
	    
	public static void main(String[] args) {
		new dealSelect();
	}//main

}//class
