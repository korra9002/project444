package adminView;

import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import adminControl.AdminMainEvt;

@SuppressWarnings("serial")
public class AdminMainView extends JFrame {
	
	private JTabbedPane jtp;
	private JButton jbtSearch1, jbtRefresh1, jbtRecent1, jbtReset1, jbtReset2, jbtReset3, jbtSearch2, jbtRefresh2, jbtRecent2, 
		jbtSearch3, jbtRefresh3, jbtReason;
	private JTable jtCheckList, jtProductList, jtUserList;
	private DefaultTableModel dtmCheckList, dtmProductList, dtmUserList;
	private JComboBox<String> jcbCategory1, jcbCategory2, jcbCategory3;
	private DefaultComboBoxModel<String> dcbCategory1, dcbCategory2, dcbCategory3;
	private JTextField jtfSearch1, jtfSearch2, jtfSearch3;
	private JRadioButton jrbID1, jrbSubject1, jrbID2, jrbSubject2;
	private JCheckBox onSale, deleteNComplete ;
	private String[] categoryList1, categoryList2;
	
	public AdminMainView() {
		super("������");
		////////////////////////////////ù��° �� ����////////////////////////////////
		categoryList1= new String[]{"ī�װ� ��ü","������/����","����/���׸���","���Ƶ�/���Ƶ���","��Ȱ/������ǰ","�����Ƿ�","������ȭ",
				"��Ƽ/�̿�","�����м�/��ȭ","������/����","����/���","����/Ƽ��/����","�ݷ�������ǰ","��Ÿ �߰�ǰ"};

		dcbCategory1 = new DefaultComboBoxModel<String>(categoryList1);
		jcbCategory1 = new JComboBox<String>(dcbCategory1);
		
		jtfSearch1 = new JTextField();
		
		jrbSubject1 = new JRadioButton("��ǰ��", true);
		jrbID1 = new JRadioButton("���̵�");
		ButtonGroup bg = new ButtonGroup();//�ΰ� �� ���õǴ� ���� ����
		bg.add(jrbSubject1);
		bg.add(jrbID1);
		
		jbtSearch1 = new JButton("�˻�");
		jbtRefresh1 = new JButton("���ΰ�ħ");
		jbtRecent1 = new JButton("�ֽż�");
		jbtReset1 = new JButton("�ʱ�ȭ");
		
		String[] checkCol= {"��ǰ�ڵ�", "�̹���", "���̵�", "ī�װ�", "��ǰ��", "����", "�ø�����"};
		Object[][] checkRow = {
				{"","","","","","",""}
				};
		
		dtmCheckList = new DefaultTableModel(checkRow, checkCol){//�� ���� ���� ����
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtCheckList = new JTable(dtmCheckList) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
		};
		
		JScrollPane jspCheckList = new JScrollPane(jtCheckList);
		
		//////////////���̺� ������ ��� ���� ����//////////////
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//���̺� ������ ��� ������ �ϱ� ����
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);//��� ���� ����
		TableColumnModel tcm = jtCheckList.getColumnModel();//������ ���̺��� �÷����� ������
		
		for (int i = 0; i < tcm.getColumnCount(); i++) {//�÷��� ����ŭ �ݺ��Ͽ� ���������
		if(i!=1) {
			
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
		}//end for
		//////////////���̺� ������ ��� ���� ��//////////////
		
		JTableHeader jh1 = jtCheckList.getTableHeader();
		jh1.setPreferredSize(new Dimension(100,40));
		
		////////���̺� ũ�⼳�� ����////////
		jtCheckList.getTableHeader().setReorderingAllowed(false);//���̺� �÷� ��ġ ���� ����
		
		jtCheckList.getColumnModel().getColumn(0).setPreferredWidth(70);
		jtCheckList.getColumnModel().getColumn(0).setResizable(false);//���̺� �÷� ������ ���� ����
		jtCheckList.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtCheckList.getColumnModel().getColumn(1).setResizable(false);
		jtCheckList.getColumnModel().getColumn(2).setPreferredWidth(90);
		jtCheckList.getColumnModel().getColumn(2).setResizable(false);
		jtCheckList.getColumnModel().getColumn(3).setPreferredWidth(90);
		jtCheckList.getColumnModel().getColumn(3).setResizable(false);
		jtCheckList.getColumnModel().getColumn(4).setPreferredWidth(140);
		jtCheckList.getColumnModel().getColumn(4).setResizable(false);
		jtCheckList.getColumnModel().getColumn(5).setPreferredWidth(100);
		jtCheckList.getColumnModel().getColumn(5).setResizable(false);;
		jtCheckList.getColumnModel().getColumn(6).setPreferredWidth(130);
		jtCheckList.getColumnModel().getColumn(6).setResizable(false);
		
		jtCheckList.setRowHeight(100);
		////////���̺� ũ�⼳�� ��////////
		
		JPanel jpCheckList = new JPanel(null);
		
		jspCheckList.setBounds(10, 10, 810, 500);
		jcbCategory1.setBounds(80, 530, 120, 30);
		jbtRecent1.setBounds(80, 580, 90, 30);
		
		jrbSubject1.setBounds(300, 530, 70, 30);
		jrbID1.setBounds(370, 530, 70, 30);
		jtfSearch1.setBounds(300, 565, 150, 30);
		jbtSearch1.setBounds(480, 565, 90, 30);
		
		jbtRefresh1.setBounds(680, 540, 90, 30);
		jbtReset1.setBounds(680, 590, 90, 30);
		
		jpCheckList.add(jspCheckList);
		jpCheckList.add(jbtRecent1);
		jpCheckList.add(jbtReset1);
		jpCheckList.add(jbtRefresh1);
		jpCheckList.add(jcbCategory1);
		jpCheckList.add(jtfSearch1);
		jpCheckList.add(jbtSearch1);
		jpCheckList.add(jrbSubject1);
		jpCheckList.add(jrbID1);
		
		/////////////////////////////////ù��° �� ��////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		////////////////////////////////�ι�° �� ����//////////////////////////////////////////////////////////////////////////////////////////////////////
		categoryList2= new String[]{"ī�װ� ��ü","������/����","����/���׸���","���Ƶ�/���Ƶ���","��Ȱ/������ǰ","�����Ƿ�","������ȭ",
				"��Ƽ/�̿�","�����м�/��ȭ","������/����","����/���","����/Ƽ��/����","�ݷ�������ǰ","��Ÿ �߰�ǰ"};

		dcbCategory2 = new DefaultComboBoxModel<String>(categoryList2);
		jcbCategory2 = new JComboBox<String>(dcbCategory2);
		
		onSale = new JCheckBox("�Ǹ���", true);
		deleteNComplete = new JCheckBox("<html>�ǸſϷ�/<br>����", true);
		
		jtfSearch2 = new JTextField();
		
		jrbSubject2 = new JRadioButton("��ǰ��", true);
		jrbID2 = new JRadioButton("���̵�");
		ButtonGroup bg2 = new ButtonGroup();//�ΰ� �� ���õǴ� ���� ����
		bg2.add(jrbSubject2);
		bg2.add(jrbID2);
		
		jbtSearch2 = new JButton("�˻�");
		jbtRefresh2 = new JButton("���ΰ�ħ");
		jbtRecent2 = new JButton("�ֽż�");
		jbtReset2 = new JButton("�ʱ�ȭ");
		
		String[] productCol= {"��ǰ�ڵ�","�̹���","���̵�","ī�װ�","��ǰ��","����","�ø�����","<html>�Ǹſ���/<br/>��������"};
		Object[][] productRow = {
				{"","","","","","",""},
				};
		
		dtmProductList = new DefaultTableModel(productRow, productCol) {//�� ���� ���� ����
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtProductList = new JTable(dtmProductList) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
		};
		
		JScrollPane jspProductList = new JScrollPane(jtProductList);

		//////////////���̺� ������ ��� ���� ����//////////////
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();//���̺� ������ ��� ������ �ϱ� ����
		dtcr2.setHorizontalAlignment(SwingConstants.CENTER);//��� ���� ����
		TableColumnModel tcm2 = jtProductList.getColumnModel();//������ ���̺��� �÷����� ������
		
		for (int i = 0; i < tcm2.getColumnCount(); i++) {//�÷��� ����ŭ �ݺ��Ͽ� ���������
			if(i!=1) {
				
				tcm2.getColumn(i).setCellRenderer(dtcr2);
			}
		}//end for
		//////////////���̺� ������ ��� ���� ��//////////////
		
		JTableHeader jh2 = jtProductList.getTableHeader();
		jh2.setPreferredSize(new Dimension(100,40));
		
		
		////////���̺� ũ�⼳�� ����////////
		jtProductList.getTableHeader().setReorderingAllowed(false);//���̺� �÷� ��ġ ���� ����
		
		jtProductList.getColumnModel().getColumn(0).setPreferredWidth(70);
		jtProductList.getColumnModel().getColumn(0).setResizable(false);//���̺� �÷� ������ ���� ����
		jtProductList.getColumnModel().getColumn(1).setPreferredWidth(120);
		jtProductList.getColumnModel().getColumn(1).setResizable(false);
		jtProductList.getColumnModel().getColumn(2).setPreferredWidth(90);
		jtProductList.getColumnModel().getColumn(2).setResizable(false);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(90);
		jtProductList.getColumnModel().getColumn(3).setResizable(false);
		jtProductList.getColumnModel().getColumn(4).setPreferredWidth(140);
		jtProductList.getColumnModel().getColumn(4).setResizable(false);
		jtProductList.getColumnModel().getColumn(5).setPreferredWidth(100);
		jtProductList.getColumnModel().getColumn(5).setResizable(false);;
		jtProductList.getColumnModel().getColumn(6).setPreferredWidth(130);
		jtProductList.getColumnModel().getColumn(6).setResizable(false);
		jtProductList.getColumnModel().getColumn(7).setPreferredWidth(70);
		jtProductList.getColumnModel().getColumn(7).setResizable(false);
		
		jtProductList.setRowHeight(100);
		////////���̺� ũ�⼳�� ��////////

		JPanel jpProductList = new JPanel(null);
		
		jspProductList.setBounds(10, 10, 810, 500);
		jcbCategory2.setBounds(80, 530, 120, 30);
		jbtRecent2.setBounds(80, 580, 90, 30);
		
		jrbSubject2.setBounds(300, 530, 70, 30);
		jrbID2.setBounds(370, 530, 70, 30);
		jtfSearch2.setBounds(300, 565, 150, 30);
		jbtSearch2.setBounds(480, 565, 90, 30);
		
		onSale.setBounds(620, 530, 80, 30);
		jbtRefresh2.setBounds(610, 590, 90, 30);
		deleteNComplete.setBounds(720, 530, 90, 40);
		jbtReset2.setBounds(720, 590, 90, 30);
		
		jpProductList.add(jspProductList);
		jpProductList.add(jbtRecent2);
		jpProductList.add(jbtRefresh2);
		jpProductList.add(jbtReset2);
		jpProductList.add(jcbCategory2);
		jpProductList.add(jtfSearch2);
		jpProductList.add(jbtSearch2);
		jpProductList.add(jrbSubject2);
		jpProductList.add(jrbID2);
		jpProductList.add(onSale);
		jpProductList.add(deleteNComplete);
		/////////////////////////////////�ι�° �� ��//////////////////////////////////////////////////////////////////////////////////////////////
		
		
		////////////////////////////////����° �� ����//////////////////////////////////////////////////////////////////////////////////////////////
		String[] searchList= {"��ü","���̵�","�̸�","��ȭ��ȣ","�̸���"};

		dcbCategory3 = new DefaultComboBoxModel<String>(searchList);
		jcbCategory3 = new JComboBox<String>(dcbCategory3);
		
		jtfSearch3 = new JTextField();
		
		jbtSearch3 = new JButton("�˻�");
		jbtRefresh3 = new JButton("���ΰ�ħ");
		jbtReset3 = new JButton("�ʱ�ȭ");
		jbtReason = new JButton("��������");
		JLabel jlbId = new JLabel("���̵�");
		
		String[] idCol= {"���̵�","�̸�","����","��ȭ��ȣ","����","��������","��������"};
		Object[][] idRow = {
				{"","","","","","",""},
				};
		
		dtmUserList = new DefaultTableModel(idRow, idCol) {//�� ���� ���� ����
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtUserList = new JTable(dtmUserList) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getColumnClass
		};
		
		JScrollPane jspUserList = new JScrollPane(jtUserList);
		
		//////////////���̺� ������ ��� ���� ����//////////////
		DefaultTableCellRenderer dtcr3 = new DefaultTableCellRenderer();//���̺� ������ ��� ������ �ϱ� ����
		dtcr3.setHorizontalAlignment(SwingConstants.CENTER);//��� ���� ����
		TableColumnModel tcm3 = jtUserList.getColumnModel();//������ ���̺��� �÷����� ������
		
		for (int i = 0; i < tcm3.getColumnCount(); i++) {//�÷��� ����ŭ �ݺ��Ͽ� ���������
			tcm3.getColumn(i).setCellRenderer(dtcr3);
		}//end for
		//////////////���̺� ������ ��� ���� ��//////////////
		
		JTableHeader jh3 = jtUserList.getTableHeader();
		jh3.setPreferredSize(new Dimension(100,40));
		
		////////���̺� ũ�⼳�� ����////////
		jtUserList.getTableHeader().setReorderingAllowed(false);//���̺� �÷� ��ġ ���� ����
		
		jtUserList.getColumnModel().getColumn(0).setPreferredWidth(90);
		jtUserList.getColumnModel().getColumn(0).setResizable(false);;//���̺� �÷� ������ ���� ����
		jtUserList.getColumnModel().getColumn(1).setPreferredWidth(140);
		jtUserList.getColumnModel().getColumn(1).setResizable(false);;
		jtUserList.getColumnModel().getColumn(2).setPreferredWidth(110);
		jtUserList.getColumnModel().getColumn(2).setResizable(false);;
		jtUserList.getColumnModel().getColumn(3).setPreferredWidth(130);
		jtUserList.getColumnModel().getColumn(3).setResizable(false);;
		jtUserList.getColumnModel().getColumn(4).setPreferredWidth(110);
		jtUserList.getColumnModel().getColumn(4).setResizable(false);;
		jtUserList.getColumnModel().getColumn(5).setPreferredWidth(150);
		jtUserList.getColumnModel().getColumn(5).setResizable(false);;
		jtUserList.getColumnModel().getColumn(6).setPreferredWidth(90);
		jtUserList.getColumnModel().getColumn(6).setResizable(false);;
		
		jtUserList.setRowHeight(100);
		////////���̺� ũ�⼳�� ��////////
		
		JPanel jpUserList = new JPanel(null);
		
		jspUserList.setBounds(10, 10, 810, 500);
//		jcbCategory3.setBounds(50, 550, 120, 30);
		jbtReason.setBounds(80, 580, 90, 30);
		
		jlbId.setBounds(220, 565, 90, 30);
		jtfSearch3.setBounds(300, 565, 150, 30);
		jbtSearch3.setBounds(480, 565, 90, 30);
		
		jbtRefresh3.setBounds(680, 540, 90, 30);
		jbtReset3.setBounds(680, 590, 90, 30);
		
		jpUserList.add(jspUserList);
		jpUserList.add(jcbCategory3);
		jpUserList.add(jbtRefresh3);
		jpUserList.add(jtfSearch3);
		jpUserList.add(jbtSearch3);
		jpUserList.add(jbtReset3);
		jpUserList.add(jbtReason);
		jpUserList.add(jlbId);
		/////////////////////////////////����° �� ��/////////////////////////////////
		
		jtp = new JTabbedPane();
		
		jtp.add("�˼����",jpCheckList);
		jtp.add("��ǰ���",jpProductList);
		jtp.add("���̵���",jpUserList);
		add(jtp);
		
		///////////////////////////////�̺�Ʈó�� ����////////////////////////////////
		AdminMainEvt ame = new AdminMainEvt(this);
		jtp.addMouseListener(ame);
		jtCheckList.addMouseListener(ame);
		jtProductList.addMouseListener(ame);
		jtUserList.addMouseListener(ame);
		
		jbtRecent1.addActionListener(ame);
		jbtRecent2.addActionListener(ame);
		jbtRefresh1.addActionListener(ame);
		jbtRefresh2.addActionListener(ame);
		jbtRefresh3.addActionListener(ame);
		jbtSearch1.addActionListener(ame);
		jbtSearch2.addActionListener(ame);
		jbtSearch3.addActionListener(ame);
		jbtReset1.addActionListener(ame);
		jbtReset2.addActionListener(ame);
		jbtReset3.addActionListener(ame);
		jbtReason.addActionListener(ame);
		
		jcbCategory1.addActionListener(ame);
		jcbCategory2.addActionListener(ame);
		jcbCategory3.addActionListener(ame);
		
		///////////////////////////////�̺�Ʈó�� ��////////////////////////////////
		
		
		
		setBounds(100, 100, 850, 700);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//SaleList
	
	public JTabbedPane getJtp() {
		return jtp;
	}

	public JButton getJbtSearch1() {
		return jbtSearch1;
	}

	public JButton getJbtRefresh1() {
		return jbtRefresh1;
	}

	public JButton getJbtRecent1() {
		return jbtRecent1;
	}

	public JButton getJbtSearch2() {
		return jbtSearch2;
	}

	public JButton getJbtRefresh2() {
		return jbtRefresh2;
	}

	public JButton getJbtRecent2() {
		return jbtRecent2;
	}

	public JButton getJbtSearch3() {
		return jbtSearch3;
	}

	public JButton getJbtRefresh3() {
		return jbtRefresh3;
	}

	public JTable getJtCheckList() {
		return jtCheckList;
	}

	public JTable getJtProductList() {
		return jtProductList;
	}

	public JTable getJtUserList() {
		return jtUserList;
	}

	public DefaultTableModel getDtmCheckList() {
		return dtmCheckList;
	}

	public DefaultTableModel getDtmProductList() {
		return dtmProductList;
	}

	public DefaultTableModel getDtmUserList() {
		return dtmUserList;
	}

	public JComboBox<String> getJcbCategory1() {
		return jcbCategory1;
	}

	public JComboBox<String> getJcbCategory2() {
		return jcbCategory2;
	}

	public JComboBox<String> getJcbCategory3() {
		return jcbCategory3;
	}

	public DefaultComboBoxModel<String> getDcbCategory1() {
		return dcbCategory1;
	}

	public DefaultComboBoxModel<String> getDcbCategory2() {
		return dcbCategory2;
	}

	public DefaultComboBoxModel<String> getDcbCategory3() {
		return dcbCategory3;
	}

	public JTextField getJtfSearch1() {
		return jtfSearch1;
	}
	public JButton getJbtReset1() {
		return jbtReset1;
	}

	public JButton getJbtReset2() {
		return jbtReset2;
	}
	
	public JButton getJbtReset3() {
		return jbtReset3;
	}
	
	public JButton getJbtReason() {
		return jbtReason;
	}
	
	public JTextField getJtfSearch2() {
		return jtfSearch2;
	}

	public JTextField getJtfSearch3() {
		return jtfSearch3;
	}

	public JRadioButton getJrbID1() {
		return jrbID1;
	}

	public JRadioButton getJrbSubject1() {
		return jrbSubject1;
	}

	public JRadioButton getJrbID2() {
		return jrbID2;
	}

	public JRadioButton getJrbSubject2() {
		return jrbSubject2;
	}

	public JCheckBox getOnSale() {
		return onSale;
	}

	public JCheckBox getDeleteNComplete() {
		return deleteNComplete;
	}

	public String[] getCategoryList1() {
		return categoryList1;
	}

	public String[] getCategoryList2() {
		return categoryList2;
	}
	
//	public static void main(String[] args) {
//		new AdminMainView();
//	}//main

}//class
