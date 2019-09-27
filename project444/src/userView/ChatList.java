package userView;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import adminView.ScrollBarUI;
import userControl.ChatListEvt;

public class ChatList extends JPanel {

	private JButton jbRefresh, jbBuy, jbSell;
	private JLabel jlComplete;

	private DefaultTableModel dtmProductList, dtmProductList2;
	private JTable jtProductList,jtProductList2;
	
	private ChatListEvt cle;

	public ChatList() {

		/////////////////////////////////////////// ä�� ȭ�� /////////////////////////////////////////////

//		Ȩ, �۾���, ä��, MyPage

		// JButton
		jbRefresh = new JButton("���ΰ�ħ");
		jbBuy = new JButton("������");
		jbSell = new JButton("�Ǹ���");		   
		// JTable

		String[] productColumn = { "��ǰ��", "ID", "����", "�ð�", "������ ��ȭ ����","�ŷ��ڵ�" };

		dtmProductList = new DefaultTableModel(productColumn, 6) {
		
			
			
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
			
		};

		
		jtProductList = new JTable(dtmProductList);


		JScrollPane jspProductList = new JScrollPane(jtProductList);

		// ����Ʈ ũ��, �̵�, ���� �Ұ����ϰ� ����

		jtProductList.setRowHeight(30);
		jtProductList.getTableHeader().setReorderingAllowed(false);

		jtProductList.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtProductList.getColumnModel().getColumn(1).setPreferredWidth(10);
		jtProductList.getColumnModel().getColumn(2).setPreferredWidth(10);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(90);
		jtProductList.getColumnModel().getColumn(4).setPreferredWidth(220);
		jtProductList.getColumnModel().getColumn(5).setPreferredWidth(10);
		/////////////////////////���̺� ��� ����////////////////////////////////
		DefaultTableCellRenderer dtcrCenter = new DefaultTableCellRenderer();
		dtcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = jtProductList.getColumnModel();
		tcm.getColumn(0).setCellRenderer(dtcrCenter);
		tcm.getColumn(1).setCellRenderer(dtcrCenter);
		tcm.getColumn(2).setCellRenderer(dtcrCenter);
		tcm.getColumn(3).setCellRenderer(dtcrCenter);
		tcm.getColumn(5).setCellRenderer(dtcrCenter);
		/////////////////////////���̺� ��� ����////////////////////////////////
		
		
		
		// +++++++++++++++++++++++++++++++++++++++++++++++++++ 2��° ���̺� ++++++++++++++++++++++++++++++++++++++++++++++++ //
		
		//Jlabel
		jlComplete =new JLabel("�� �����ٸ�~");
		
		//JTable
		dtmProductList2 = new DefaultTableModel(productColumn, 6) {		
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}					
		};
		
		jtProductList2 = new JTable(dtmProductList2);
		JScrollPane jspProductList2 = new JScrollPane(jtProductList2);
		
		// ����Ʈ ũ��, �̵�, ���� �Ұ����ϰ� ����
		jtProductList2.setRowHeight(30);
		jtProductList2.getTableHeader().setReorderingAllowed(false);
		
		jtProductList2.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtProductList2.getColumnModel().getColumn(1).setPreferredWidth(10);
		jtProductList2.getColumnModel().getColumn(2).setPreferredWidth(10);
		jtProductList2.getColumnModel().getColumn(3).setPreferredWidth(90);
		jtProductList2.getColumnModel().getColumn(4).setPreferredWidth(220);
		jtProductList2.getColumnModel().getColumn(5).setPreferredWidth(10);
		/////////////////////////���̺� ��� ����////////////////////////////////
		DefaultTableCellRenderer dtcrCenter2 = new DefaultTableCellRenderer();
		dtcrCenter2.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm2 = jtProductList.getColumnModel();
		tcm2.getColumn(0).setCellRenderer(dtcrCenter);
		tcm2.getColumn(1).setCellRenderer(dtcrCenter);
		tcm2.getColumn(2).setCellRenderer(dtcrCenter);
		tcm2.getColumn(3).setCellRenderer(dtcrCenter);
		tcm2.getColumn(5).setCellRenderer(dtcrCenter);
		/////////////////////////���̺� ��� ����////////////////////////////////

		
		// +++++++++++++++++++++++++++++++++++++++++++++++++++ 2��° ���̺� ++++++++++++++++++++++++++++++++++++++++++++++++ //
		
				

		// ������Ʈ ��ġ
		setLayout(null);

		jspProductList.setBounds(30, 80, 750, 300);
		jbRefresh.setBounds(680, 20, 100,50);
		jbBuy.setBounds(30, 20, 90, 50);
		jbSell.setBounds(140, 20, 90, 50);
		
		
		jspProductList2.setBounds(30, 450, 750, 300);
		jlComplete.setBounds(30, 410, 120, 50);
		
		add(jspProductList);
		add(jbRefresh);
		add(jbBuy);
		add(jbSell);
		add(jlComplete);
		
		add(jspProductList2);

//		setBounds(100, 100,  600, 450);
		setBackground(new Color(0xf6f2ef));
		jtProductList.getTableHeader().setBackground(new Color(0xFFCC66));
		jbBuy.setBackground(new Color(0xFFCC66));
		jbSell.setBackground(new Color(0xFFCC66));
		jbRefresh.setBackground(new Color(0xFFCC66));
		jtProductList2.getTableHeader().setBackground(new Color(0xFFCC66));

		
		
		setVisible(true);
		cle = new ChatListEvt(this);
		jbRefresh.addActionListener(cle);
		jbBuy.addActionListener(cle);
		jbSell.addActionListener(cle);
		jtProductList.addMouseListener(cle);
		

		jtProductList2.addMouseListener(cle);

		jspProductList.getVerticalScrollBar().setUI(new ScrollBarUI());
		jspProductList2.getVerticalScrollBar().setUI(new ScrollBarUI());
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}// Chat

	public JButton getJbRefresh() {
		return jbRefresh;
	}

	public JButton getJbBuy() {
		return jbBuy;
	}

	public JButton getJbSell() {
		return jbSell;
	}

	public JLabel getJlComplete() {
		return jlComplete;
	}

	public DefaultTableModel getDtmProductList() {
		return dtmProductList;
	}

	public DefaultTableModel getDtmProductList2() {
		return dtmProductList2;
	}

	public JTable getJtProductList() {
		return jtProductList;
	}

	public JTable getJtProductList2() {
		return jtProductList2;
	}

	public ChatListEvt getCle() {
		return cle;
	}


	



}// class
