package adminControl;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import adminVO.SuspendIdVO;
import adminView.AdminSuspendReasonView;

public class AdminSuspendReasonViewEvt {
	
	private AdminSuspendReasonView asrv;
	
	public AdminSuspendReasonViewEvt(AdminSuspendReasonView asrv) {
		this.asrv = asrv;
		selectSuspendList();
	}//AdminSuspendReasonViewEvt

	
	private void selectSuspendList() {
		DefaultTableModel dtm = asrv.getDtmSuspendList();
		
		dtm.setRowCount(0);
		String[] rowData = null;
		
		List<SuspendIdVO> list = asrv.getList();
		
//		System.out.println(list);
		if (list.isEmpty()) {
			JOptionPane.showMessageDialog(asrv, "정지된 아이디 이력이 없습니다.");
		}//end if
		
		SuspendIdVO siVO = null;
		
		for (int i = 0; i < list.size(); i++) {
			
			siVO = list.get(i);
			
			rowData = new String[3];
			
			rowData[0] = siVO.getUserId();
			rowData[1] = siVO.getSuspendReason();
			rowData[2] = "<HTML>" + siVO.getSuspendDate().replace(" ", " <br>");

			dtm.addRow(rowData);
			
		}//end for
		
	}//selectSuspendList
	
}//class
