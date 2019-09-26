package adminControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import adminVO.SuspendIdVO;
import adminView.AdminSuspendReasonView;

public class AdminSuspendReasonViewEvt implements ActionListener{
	
	private AdminSuspendReasonView asrv;
	
	public AdminSuspendReasonViewEvt(AdminSuspendReasonView asrv) {
		this.asrv = asrv;
		selectSuspendList();
		
	}//AdminSuspendReasonViewEvt

	
	private void selectSuspendList() {
		DefaultTableModel dtm = asrv.getDtmSuspendList();
		SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		
		dtm.setRowCount(0);
		Object[] rowData = null;
		
		List<SuspendIdVO> list = asrv.getList();
		
//		System.out.println(list);
		if (list.isEmpty()) {
			JOptionPane.showMessageDialog(asrv, "정지된 아이디 이력이 없습니다.");
		}//end if
		
		SuspendIdVO siVO = null;
		
		for (int i = 0; i < list.size(); i++) {
			
			siVO = list.get(i);
			
			rowData = new Object[4];
			
			rowData[0] = siVO.getUserId();
			rowData[1] = siVO.getSuspendReason();
//			rowData[2] = siVO.getSuspendDate();
//			date[i] =  siVO.getSuspendDate();
			rowData[2] = "<HTML>"+sdf.format(siVO.getSuspendDate()).replace(" ", "<br/>");
			rowData[3] = new Integer(siVO.getPeriod());

			dtm.addRow(rowData);
			
		}//end for

	}//selectSuspendList

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == asrv.getJbtOk()) {
			asrv.dispose();
		}//end if
		
	}//actionPerformed
	
}//class
