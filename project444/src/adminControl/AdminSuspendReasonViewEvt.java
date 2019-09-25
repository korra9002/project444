package adminControl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import adminVO.SuspendIdVO;
import adminView.AdminSuspendReasonView;

public class AdminSuspendReasonViewEvt /*implements Runnable*/{
	
	private AdminSuspendReasonView asrv;
	
	public AdminSuspendReasonViewEvt(AdminSuspendReasonView asrv) {
		this.asrv = asrv;
//		Thread t = new Thread();
		selectSuspendList();
//		t.start();
		
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
			rowData[2] = "<HTML>" + sdf.format(siVO.getSuspendDate()).replace(" ", " <br>");
			rowData[3] = new Integer(siVO.getPeriod());

			dtm.addRow(rowData);
			
		}//end for

		
		System.out.println(dtm.getRowCount());
		
	}//selectSuspendList
	
	private void clock() {
		DefaultTableModel dtm = asrv.getDtmSuspendList();
		
		for (int i = 0; i < dtm.getRowCount(); i++) {
			System.out.println(dtm.getValueAt(i, 2));
		}
		
		Calendar calBefore = Calendar.getInstance();
		Calendar calAfter = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");

		Date today = new Date();
		String day1 = null;
		
//		System.out.println(today);
		calAfter.setTime(today);
		calBefore.add(Calendar.DAY_OF_MONTH, 30);
		day1 = format1.format(calAfter.getTime());
//		System.out.println(day1);
		
		
	}//clock

//	@Override
//	public void run() {
//		while(true) {
//			try {
//				clock();
//				Thread.sleep(1000);
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}//end catch
//			
//		}//end while
//		
//	}//run
	
}//class
