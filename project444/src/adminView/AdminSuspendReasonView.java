package adminView;

import java.util.List;

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
	private List<SuspendIdVO> list;
	
	private AdminMainView amv;
	
	public AdminSuspendReasonView(AdminMainView amv, List<SuspendIdVO> list) {
		super(amv,"정지된 ID 목록",true);
		this.amv = amv;
		this.list = list;
		
		String [] cols = {"아이디", "정지사유", "정지날짜", "정지기간(일)"};
		String [][] rows = {{"", "", ""}};
		
		dtmSuspendList = new DefaultTableModel(rows, cols){//셀 내용 수정 금지
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}//isCellEditable
		};
		jtaSuspendList = new JTable(dtmSuspendList);
		JScrollPane jspSuspendList = new JScrollPane(jtaSuspendList);
		//////////////테이블 데이터 가운데 정렬 시작//////////////
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();//테이블 데이터 가운데 정렬을 하기 위해
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);//가운데 정렬 세팅
		TableColumnModel tcm = jtaSuspendList.getColumnModel();//정렬할 테이블의 컬럼모델을 가져옴
		
		for (int i = 0; i < tcm.getColumnCount(); i++) {//컬럼의 수만큼 반복하여 가운데정렬함
			tcm.getColumn(i).setCellRenderer(dtcr);
		}//end for
		//////////////테이블 데이터 가운데 정렬 끝//////////////
		
		////////테이블 크기설정 시작////////
		jtaSuspendList.getTableHeader().setReorderingAllowed(false);//테이블 컬럼 위치 변경 금지
		
		jtaSuspendList.getColumnModel().getColumn(0).setPreferredWidth(70);
		jtaSuspendList.getColumnModel().getColumn(0).setResizable(false);//테이블 컬럼 사이즈 변경 금지
		jtaSuspendList.getColumnModel().getColumn(1).setPreferredWidth(250);
		jtaSuspendList.getColumnModel().getColumn(1).setResizable(false);
		jtaSuspendList.getColumnModel().getColumn(2).setPreferredWidth(80);
		jtaSuspendList.getColumnModel().getColumn(2).setResizable(false);
		jtaSuspendList.getColumnModel().getColumn(3).setPreferredWidth(80);
		jtaSuspendList.getColumnModel().getColumn(3).setResizable(false);
		
		jtaSuspendList.setRowHeight(50);
		////////테이블 크기설정 끝////////
		
		setLayout(null);
		jspSuspendList.setBounds(10, 10, 480, 300);
		
		add(jspSuspendList);
		
		AdminSuspendReasonViewEvt asrve = new AdminSuspendReasonViewEvt(this);
		
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

}//class
