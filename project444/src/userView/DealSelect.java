package userView;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;

import userControl.DealSelectEvt;

public class DealSelect extends JFrame {
	
	private JButton jbtsell , jbtcancell;	
	private DefaultTableModel dtmAreaList;
	private JTable jtpAreaList;
	private JLabel jlb;
	
	private MarketDetailSeller mds;
	
//	private String productName,productCode;
	
	public DealSelect(String nameAndCode,MarketDetailSeller mds) {
		this.mds = mds;
		jbtsell=new JButton("�ǸſϷ�");
		jbtcancell=new JButton("���");
		
		jlb=new JLabel("�ŷ� ��븦 �����ϼ���");
		
	
		
		String[] areaColumn= {"ID","����","�ŷ��ڵ�"};
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
	
	// ������Ʈ ��ġ
	

	jspAreaList.setBounds(30, 180, 400, 200);
	jbtsell.setBounds(130, 400, 90, 30);
	jbtcancell.setBounds(250, 400, 90, 30);
	jlb.setBounds(30, 50, 400, 90);
	
	setLayout(null);
	add(jspAreaList);
	add(jbtsell);
	add(jbtcancell);
	add(jlb);
	//////��, �� ������ //////////////
			Container c = getContentPane();
			c.setBackground(new Color(0xf6f2ef));
			jbtcancell.setBackground(new Color(0xFFCC66));
			jbtsell.setBackground(new Color(0xFFCC66));
			jtpAreaList.getTableHeader().setBackground(new Color(0xFFCC66));
	
			
			jspAreaList.getVerticalScrollBar().setBackground(new Color(0xFFFFFF));
			
			//��ũ�� �� ���� //
			jspAreaList.getVerticalScrollBar().setUI(new BasicScrollBarUI()
	        {
				
	            @Override
	            protected void configureScrollBarColors()
	            {
	                this.thumbColor = new Color(0xFFCC66);
	            }
	            
	        });
			
			//////////////////////////
		 
	
//	setResizable(false);
	setBounds(100, 100, 500, 600);
	setVisible(true);
	
DealSelectEvt dse = new DealSelectEvt(this,nameAndCode);
	jtpAreaList.addMouseListener(dse);
	jbtsell.addActionListener(dse);
	jbtcancell.addActionListener(dse);
		
	}//dealSelect

	public JLabel getJlb() {
		return jlb;
	}

	public MarketDetailSeller getMds() {
		return mds;
	}

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

	
	     //
	    

}//class
