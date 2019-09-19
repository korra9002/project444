package userView;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import userControl.DealSelectEvt;

public class DealSelect extends JFrame {
	
	private JButton jbtsell , jbtcancell;	
	private DefaultTableModel dtmAreaList;
	private JTable jtpAreaList;
	private JLabel jlb;
	
	private String productCode;
	
	public DealSelect(String productCode) {
		
		this.productCode = productCode;
		jbtsell=new JButton("판매완료");
		jbtcancell=new JButton("취소");
		
		jlb=new JLabel("거래 상대를 선택하세요");
		
	
		
		String[] areaColumn= {"ID","지역","거래코드"};
		dtmAreaList=new DefaultTableModel(areaColumn,0) {
		
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
	jlb.setBounds(30, 50, 400, 90);
	
	setLayout(null);
	add(jspAreaList);
	add(jbtsell);
	add(jbtcancell);
	add(jlb);
	
//	setResizable(false);
	setBounds(100, 100, 500, 600);
	setVisible(true);
	
DealSelectEvt dse = new DealSelectEvt(this,productCode);
	
		
	}//dealSelect

	public JButton getJbtsell() {
		return jbtsell;
	}

	public JButton getJbtcancell() {
		return jbtcancell;
	}

	public DefaultTableModel getDtmAreaList() {
		return dtmAreaList;
	}

	public JTable getJtpAreaList() {
		return jtpAreaList;
	}

	public JLabel getjlb() {
		return jlb;
	}

	public String getProductCode() {
		return productCode;
	}
	
	     //
	    

}//class
