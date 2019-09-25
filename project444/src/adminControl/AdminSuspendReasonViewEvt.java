package adminControl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import adminVO.SuspendIdVO;
import adminView.AdminSuspendReasonView;

public class AdminSuspendReasonViewEvt{
	
	private AdminSuspendReasonView asrv;
	
	public AdminSuspendReasonViewEvt(AdminSuspendReasonView asrv) {
		this.asrv = asrv;
		selectSuspendList();
		
	}//AdminSuspendReasonViewEvt

	
	private void selectSuspendList() {
		DefaultTableModel dtm = asrv.getDtmSuspendList();
		SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH");
		
		dtm.setRowCount(0);
		Object[] rowData = null;
		
		List<SuspendIdVO> list = asrv.getList();
		
//		System.out.println(list);
		if (list.isEmpty()) {
			JOptionPane.showMessageDialog(asrv, "������ ���̵� �̷��� �����ϴ�.");
		}//end if
		
		SuspendIdVO siVO = null;
		
		for (int i = 0; i < list.size(); i++) {
			
			siVO = list.get(i);
			
			rowData = new Object[4];
			
			rowData[0] = siVO.getUserId();
			rowData[1] = siVO.getSuspendReason();
//			rowData[2] = siVO.getSuspendDate();
//			date[i] =  siVO.getSuspendDate();
			rowData[2] = sdf.format(siVO.getSuspendDate());
			rowData[3] = new Integer(siVO.getPeriod());

			dtm.addRow(rowData);
			
		}//end for

	}//selectSuspendList
	
}//class
