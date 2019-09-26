package adminView;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import adminControl.AdminSuspendReasonViewEvt;
import adminVO.SuspendIdVO;

@SuppressWarnings("serial")
public class AdminSuspendReasonView extends JDialog {
	private JTable jtaSuspendList;
	private DefaultTableModel dtmSuspendList;
	private JButton jbtOk;
	private List<SuspendIdVO> list;
	
	private AdminMainView amv;
	
	public AdminSuspendReasonView(AdminMainView amv, List<SuspendIdVO> list) {
		super(amv,"������ ID ���",true);
		this.amv = amv;
		this.list = list;
		
		String [] cols = {"���̵�", "��������", "������¥", "�����Ⱓ(��)"};
		String [][] rows = {{"", "", ""}};
		
		jbtOk = new JButton("Ȯ��");
		jbtOk .setBackground(new Color(0xFFCC66));
		
		dtmSuspendList = new DefaultTableModel(rows, cols){//�� ���� ���� ����
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtaSuspendList = new JTable(dtmSuspendList);
		JScrollPane jspSuspendList = new JScrollPane(jtaSuspendList);
		//////////////���̺� ������ ��� ���� ����//////////////
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//���̺� ������ ��� ������ �ϱ� ����
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);//��� ���� ����
		TableColumnModel tcm = jtaSuspendList.getColumnModel();//������ ���̺��� �÷����� ������
		
		for (int i = 0; i < tcm.getColumnCount(); i++) {//�÷��� ����ŭ �ݺ��Ͽ� ���������
			tcm.getColumn(i).setCellRenderer(dtcr);
		}//end for
		//////////////���̺� ������ ��� ���� ��//////////////
		
		////////���̺� ũ�⼳�� ����////////
		jtaSuspendList.getTableHeader().setReorderingAllowed(false);//���̺� �÷� ��ġ ���� ����
		
		jtaSuspendList.getColumnModel().getColumn(0).setPreferredWidth(70);
		jtaSuspendList.getColumnModel().getColumn(0).setResizable(false);//���̺� �÷� ������ ���� ����
		jtaSuspendList.getColumnModel().getColumn(1).setPreferredWidth(250);
		jtaSuspendList.getColumnModel().getColumn(1).setResizable(false);
		jtaSuspendList.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtaSuspendList.getColumnModel().getColumn(2).setResizable(false);
		jtaSuspendList.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtaSuspendList.getColumnModel().getColumn(3).setResizable(false);
		
		jtaSuspendList.setRowHeight(50);
		////////���̺� ũ�⼳�� ��////////
		
		setLayout(null);
		jspSuspendList.setBounds(10, 10, 480, 250);
		jbtOk.setBounds(230, 280, 60, 30);
		
		add(jspSuspendList);
		add(jbtOk);
		
		AdminSuspendReasonViewEvt asrve = new AdminSuspendReasonViewEvt(this);
		jbtOk.addActionListener(asrve);
		
		setBounds(amv.getX()+800, amv.getY()+50, 520, 380);
		setVisible(true);
		setResizable(false);
		
	}//AdminDetailView

	public JTable getJtaSuspendList() {
		return jtaSuspendList;
	}

	public DefaultTableModel getDtmSuspendList() {
		return dtmSuspendList;
	}

	public List<SuspendIdVO> getList() {
		return list;
	}

	public AdminMainView getAmv() {
		return amv;
	}

	public JButton getJbtOk() {
		return jbtOk;
	}

}//class
