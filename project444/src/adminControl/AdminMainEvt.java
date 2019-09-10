package adminControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import adminDAO.AdminDAO;
import adminVO.CheckListVO;
import adminVO.CheckVO;
import adminView.AdminMainView;

public class AdminMainEvt extends MouseAdapter implements ActionListener{

	private AdminMainView amv;
	private int index;
	private String[] categoryTemp ;
//	private OrderThread ot;
	
	public AdminMainEvt(AdminMainView amv) {
		this.amv = amv;
		setCheckList();
	}//LunchMainEvt

	/**
	 * DBMS테이블에서 조회한 검수파일 리스트를 JTable에 설정 
	 */
	private void setCheckList() {
		DefaultTableModel dtm = amv.getDtmCheckList();
		String value = null;
		String col_name = null;
		String category = null;
		
		category = (String) amv.getJcbCategory1().getSelectedItem();
		value = amv.getJtfSearch1().getText().trim();
		
		if (amv.getJrbSubject1().isSelected()) {
			col_name = amv.getJrbSubject1().getText();
		}else {
			col_name = amv.getJrbID1().getText();
		}//end else
		
		CheckVO cv = new CheckVO(category, col_name, value);
		//JTable의 레코드 초기화
		dtm.setRowCount(0);
		Object[] rowData = null;//JTable에 넣을 데이터
		
		//DBMS에서 조회
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			List<CheckListVO> list = aDAO.selectAllCheckList(cv);
			
			if(list.isEmpty()) {//제품이 없는 경우
				JOptionPane.showMessageDialog(amv, "검수할 제품이 없습니다.");
			}//end if
			
			CheckListVO clv = null;
			
			for(int i = 0 ; i < list.size() ; i++) {
				clv = list.get(i);
				
				//조회결과로 JTable 레코드에 들어갈 데이터를 생성하고, dtm에 추가
				rowData = new Object[7];
				
				//배열에 값을 할당
				rowData[0] = clv.getProduct_code();
				rowData[1] = clv.getImg_file();
				rowData[2] = clv.getUser_id();
				rowData[3] = clv.getCategory();
				rowData[4] = clv.getProduct_name();
				rowData[5] = new Integer(clv.getPrice());
				rowData[6] = clv.getUpload_date();
				
				//dtm에 추가
				dtm.addRow(rowData);
				
			}//end for
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(amv, "관리자님, 서비스가 원활하지 못한 점 죄송합니다.");
			e.printStackTrace();
		}//end catch
		
	}//setLunchList

	private void openDetail() {
		
	}
	
	/////////////두번째 탭//////////////////////////////////////////////////////

	private void setProductList() {
//		DefaultTableModel dtm = amv.getDtmProductList();
//		
//		//JTable의 레코드 초기화
//		dtm.setRowCount(0);
//		Object[] rowData = null;//JTable에 넣을 데이터
//		
//		//DBMS에서 조회
//		AdminDAO aDAO = AdminDAO.getInstance();
//		try {
//			List<CheckListVO> list = aDAO.selectAllProductList();
//			
//			if(list.isEmpty()) {//제품이 없는 경우
//				JOptionPane.showMessageDialog(amv, "등록된 제품이 없습니다.");
//			}//end if
//			
//			CheckListVO cv = null;
//			
//			for(int i = 0 ; i < list.size() ; i++) {
//				cv = list.get(i);
//				
//				//조회결과로 JTable 레코드에 들어갈 데이터를 생성하고, dtm에 추가
//				rowData = new Object[8];
//				
//				//배열에 값을 할당
//				rowData[0] = cv.getProduct_code();//i + 1도 가능
//				rowData[1] = cv.getImg_file();
//				rowData[2] = cv.getUser_id();
//				rowData[3] = cv.getCategory();
//				rowData[4] = cv.getProduct_name();
//				rowData[5] = new Integer(cv.getPrice());
//				rowData[6] = cv.getUpload_date();
//				rowData[7] = cv.getAll_flag();
//				
//				//dtm에 추가
//				dtm.addRow(rowData);
//				
//			}//end for
//			
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(amv, "관리자님, 서비스가 원활하지 못한 점 죄송합니다.");
//			e.printStackTrace();
//		}//end catch
//		
	}//setProductList
	
	private void openDetail2() {
		
	}
	
	private void setRefreshList2() {
		
	}
	
	private void setCategoryList2() {
		
	}
	
	private void setRecentList2() {
		
	}
	
	private void productNameSelect2() {
		
	}
	
	private void userIdSelect2() {
		
	}
	
	//////////////////////////////세번째 탭//////////////////////////////
	
	private void setUserIdList() {
//		DefaultTableModel dtm = amv.getDtmUserList();
//		
//		//JTable의 레코드 초기화
//		dtm.setRowCount(0);
//		Object[] rowData = null;//JTable에 넣을 데이터
//		
//		//DBMS에서 조회
//		AdminDAO aDAO = AdminDAO.getInstance();
//		try {
//			List<UserIdListVO> list = aDAO.selectAllUserList();
//			
//			if(list.isEmpty()) {//등록된 유저id 없는 경우
//				JOptionPane.showMessageDialog(amv, "등록된 ID가 없습니다.");
//			}//end if
//			
//			UserIdListVO uilv = null;
//			
//			for(int i = 0 ; i < list.size() ; i++) {
//				uilv = list.get(i);
//				
//				//조회결과로 JTable 레코드에 들어갈 데이터를 생성하고, dtm에 추가
//				rowData = new Object[7];
//				
//				//배열에 값을 할당
//				rowData[0] = uilv.getUser_id();//i + 1도 가능
//				rowData[1] = uilv.getUser_name();
//				rowData[2] = uilv.getGender();
//				rowData[3] = uilv.getPhone();
//				rowData[4] = uilv.getLoc();
//				rowData[5] = uilv.getJoin_date();
//				rowData[6] = uilv.getSuspend_flag();
//				
//				//dtm에 추가
//				dtm.addRow(rowData);
//				
//			}//end for
//			
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(amv, "관리자님, 서비스가 원활하지 못한 점 죄송합니다.");
//			e.printStackTrace();
//		}//end catch
		
	}//setUserIdList
	
	
	
	
	
	
	
	
	
	
	
	private void setCategoryList(String category) {
//		DefaultTableModel dtm = amv.getDtmCheckList();
//		JComboBox<String> jcb = amv.getJcbCategory1();
//		
//		//JTable의 레코드 초기화
//		dtm.setRowCount(0);
//		Object[] rowData = null;//JTable에 넣을 데이터
//		
//		//DBMS에서 조회
//		AdminDAO aDAO = AdminDAO.getInstance();
//		try {
//			List<CheckListVO> list = aDAO.selectAllCheckList();
//			
//			if(list.isEmpty()) {//등록된 제품이 없는 경우
//				JOptionPane.showMessageDialog(amv, "검수할 제품이 없습니다.");
//				setCheckList();
//				jcb.setSelectedIndex(0);
//			}//end if
//			
//			CheckListVO clv = null;
//			int cnt = 0;
//			for(int i = 0 ; i < list.size() ; i++) {
//				clv = list.get(i);
//				
//				//조회결과로 JTable 레코드에 들어갈 데이터를 생성하고, dtm에 추가
//				rowData = new Object[7];
//				if (clv.getCategory().equals(category)) {
//					
//					//배열에 값을 할당
//					rowData[0] = clv.getProduct_code();
//					rowData[1] = clv.getImg_file();
//					rowData[2] = clv.getUser_id();
//					rowData[3] = clv.getCategory();
//					rowData[4] = clv.getProduct_name();
//					rowData[5] = new Integer(clv.getPrice());
//					rowData[6] = clv.getUpload_date();
//					cnt++;
//					
//				}//end if
//				
//				//dtm에 추가
//				dtm.addRow(rowData);
//				
//			}//end for
//			dtm.setRowCount(cnt);
//			
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(amv, "관리자님, 서비스가 원활하지 못한 점 죄송합니다.");
//			e.printStackTrace();
//		}//end catch
//		
	}//setCategoryList
	
	private void setProductNameList(String productName) {
//		
//		DefaultTableModel dtm = amv.getDtmCheckList();
////		JComboBox<String> jcb = amv.getJcbCategory1();
//		
//		//JTable의 레코드 초기화
//		dtm.setRowCount(0);
//		Object[] rowData = null;//JTable에 넣을 데이터
//		
//		//DBMS에서 조회
//		AdminDAO aDAO = AdminDAO.getInstance();
//		try {
//			List<CheckListVO> list = aDAO.selectAllCheckList();
//			
////			if(list.isEmpty()) {//등록된 제품이 없는 경우
////				JOptionPane.showMessageDialog(amv, "검수할 제품이 없습니다.");
////				setCheckList();
////				jcb.setSelectedIndex(0);
////			}//end if
//			
//			CheckListVO clv = null;
//			int cnt = 0;
//			for(int i = 0 ; i < list.size() ; i++) {
//				clv = list.get(i);
//				
//				//조회결과로 JTable 레코드에 들어갈 데이터를 생성하고, dtm에 추가
//				rowData = new Object[7];
//				
//				//배열에 값을 할당
//				
//				if (clv.getProduct_name().contains(productName)) {
//					rowData[0] = clv.getProduct_code();
//					rowData[1] = clv.getImg_file();
//					rowData[2] = clv.getUser_id();
//					rowData[3] = clv.getCategory();
//					rowData[4] = clv.getProduct_name();
//					rowData[5] = new Integer(clv.getPrice());
//					rowData[6] = clv.getUpload_date();
//					cnt++;
//				}//end if
//				
//				
//				//dtm에 추가
//				dtm.addRow(rowData);
//				
//			}//end for
//			dtm.setRowCount(cnt);
//			
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(amv, "관리자님, 서비스가 원활하지 못한 점 죄송합니다.");
//			e.printStackTrace();
//		}//end catch
//		
//		
	}//selectProductNameList
	
	
//	
//
//	/**
//	 * 관리자 창을 닫는 일
//	 */
//	private void lunchClose() {
//		switch (JOptionPane.showConfirmDialog(lm, "'원 도시락' 관리자 프로그램을 종료하시겠습니까?")) {
//		case JOptionPane.OK_OPTION:
//			lm.dispose();
//
//		}//switch
//	
//	}//lunchClose
//	
//	private void addForm() {
//		new LunchAddFrm(lm, this);
//	}//addForm
//	
//	
//	/**
//	 * 암호화된 ip를 복호화 하여 제공하는 일
//	 */
//	private void ipView() {
//		JTable temp = lm.getJtOrderList();
//		
//		int selectRow = temp.getSelectedRow();
//		
//		if (selectRow == -1) {//제작 완료 할 도시락의 행이 선택되지 않았을 때
//			JOptionPane.showMessageDialog(lm, "ip주소를 확인할 행을 선택해주세요");
//			return;
//		}//end if
//		
//		try {
//			DataDecrypt dd = new DataDecrypt("1111111111111111");
//			//복호화할 데이터 얻기
//			String cipherText = (String) temp.getValueAt(selectRow, 6);
//			String name = (String) temp.getValueAt(selectRow, 3);
//			
//			String plainText = dd.decryption(cipherText);//복호화
//			
//			JOptionPane.showMessageDialog(lm, name + " 주문자의 ip Address는\n'" + plainText + "'\n입니다.");
//			
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (GeneralSecurityException e) {
//			e.printStackTrace();
//		}//end catch
//		
//	}//ipView
//	
//	
//	/**
//	 * 제작상태를 변경
//	 */
//	private void orderStatus() {
//		JTable temp = lm.getJtOrderList();
//		int selectRow = temp.getSelectedRow();
//		
//		if (selectRow == -1) {//제작 완료 할 도시락의 행이 선택되지 않았을 때
//			JOptionPane.showMessageDialog(lm, "제작 완료한 도시락의 행을 선택해주세요");
//			return;
//		}//end if
//		
//		String status = (String) temp.getValueAt(selectRow, 8);
//		Integer orderNum = (Integer) temp.getValueAt(selectRow, 0);
//		String lunchName = (String) temp.getValueAt(selectRow, 1);
//		
//		StringBuilder msg = new StringBuilder();
//		msg.append(lunchName).append("(").append(orderNum).append(") 도시락을 ");
//		
//		if(status.equals("N")) {
//			msg.append("제조완료 상태로 변경하시겠습니까?");
//		} else {
//			msg.append("제조완료 상태를 취소하시겠습니까?");
//		}//end else
//		
//		switch (JOptionPane.showConfirmDialog(lm, msg.toString())) {
//		case JOptionPane.OK_OPTION:
//			if(status.equals("N")) {
//				status = "Y";
//			} else {
//				status = "N";
//			}//end if
//			
//			//화면을 변경
//			temp.setValueAt(status, selectRow, 8);
//			
//			//DBMS를 변경
//			AdminDAO aDAO = AdminDAO.getInstance();
//			try {
//				String resultMsg = "도시락 상태가 변경되지 않았습니다.";
//				
//				if(aDAO.updateStatus(orderNum, status)) {
//					resultMsg = "도시락 상태가 변경되었습니다.";
//				}//end if
//				
//				JOptionPane.showMessageDialog(lm, resultMsg);
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}//end catch
//			
//		}//end switch
//		
//	}//orderStatus
//	
//	
//	private void viewCalc() {
//		AdminDAO aDAO = AdminDAO.getInstance();
//		try {
//			List<CalcVO> list = aDAO.selectCalc();
//			
//			JTextArea jta = new JTextArea(20,50);
//			JScrollPane jsp = new JScrollPane(jta);
//			
//			if (list.isEmpty()) {
//				jta.append("정산할 수 있는 거래가 이루어지지 않았습니다.");
//			}//end if
//			
//			jta.append("-----------------------------------------------------------------------------------------------------------\n");
//			jta.append("번호\t도시락명(코드)\t\t총 판매수량\t총 가격\t\n");
//			jta.append("-----------------------------------------------------------------------------------------------------------\n");
//			CalcVO cv = null;
//			int totalCnt = 0;
//			int totalPrice = 0;
//			
//			for (int i = 0; i < list.size(); i++) {
//				cv = list.get(i);
//				jta.append((i+1)+"\t" + cv.getName() + "(" + cv.getLunchCode() + ")\t" + cv.getCnt() + "\t" + cv.getTotalPrice() + "\n");
//				totalCnt += cv.getCnt();
//				totalPrice += cv.getTotalPrice();
//			}//end for
//			jta.append("-----------------------------------------------------------------------------------------------------------\n");
//			jta.append("총 수량 : " + totalCnt + "개,	총 매출 : " + totalPrice + "원");
//			
//			
//			JOptionPane.showMessageDialog(lm, jsp);
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(lm, "DBMS에 문제가 발생하였습니다.");
//			e.printStackTrace();
//		}//end catch
//		
//	}//viewCalc
//	
//	private void removeOrder() {
//		int deleteCnt = 0;
//		int orderNum = (int) lm.getJtOrderList().getValueAt(lm.getJtOrderList().getSelectedRow(), 0);
//		AdminDAO aDAO = AdminDAO.getInstance();
//		
//		switch (JOptionPane.showConfirmDialog(lm, "주문을 삭제하시겠습니까?")) {
//		case JOptionPane.OK_OPTION:
//			try {
//				deleteCnt = aDAO.deleteOrder(orderNum);
//				
//				if (deleteCnt == 0) {
//					JOptionPane.showMessageDialog(lm, "삭제할 데이터가 없습니다.");
//					
//				} else {
//					JOptionPane.showMessageDialog(lm, orderNum + "번 주문이 삭제되었습니다.");
//					
//				}//end else
//				
////				ot.start();
//				
//			} catch (SQLException e) {
//				JOptionPane.showMessageDialog(lm, "삭제 작업 중 문제가 발생하였습니다.");
//				e.printStackTrace();
//			}//end catch
//			
//		}//end switch
//		
//	}//removeOrder
//	
//	
//	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == amv.getJcbCategory1()) {//종료버튼
			JComboBox jcb = (JComboBox) ae.getSource();
			
			index = jcb.getSelectedIndex();
//			categoryTemp = amv.getCategoryList1();
			
//			System.out.println(index);
//			System.out.println(temp[index]);
			if (index != 0) {
				setCheckList();
			}//end if
			
		}//end if
		
		if(ae.getSource() == amv.getJbtSearch1()) {//추가버튼
			
			if (amv.getJtfSearch1().getText().isEmpty()) {
				JOptionPane.showMessageDialog(amv, "검색어를 입력해주세요.");
				amv.getJtfSearch1().getCursor();
				
			}else {
				setCheckList();
			}
			
			
		}//end if
		
//		
//		if(ae.getSource() == lm.getJmStatus()) {
//			orderStatus();
//		}//end if
//		
//		if(ae.getSource() == lm.getJmIpView()) {
//			ipView();
//		}//end if
//		
//		if(ae.getSource() == lm.getJmDelete()) {
//			removeOrder();
//		}//end if
//		
//		if (ae.getSource() == lm.getJbtCalc()) {
//			viewCalc();
//		}//end if
//		
	}//actionPerformed
//	
//	/**
//	 * 선택한 도시락의 상세정보창을 띄워주는 일
//	 */
//	private void lunchDetail(JTable temp) {
//		//상세창에서 사용할 값을 VO(DTO의 역할)에 설정
//		LunchDetailVO ldVO = new LunchDetailVO();
//		
//		//도시락 리스트에서 얻을 수 있는 값
//		//선택한 행의 도시락 코드를 얻기
//		String lunchName = (String)temp.getValueAt(temp.getSelectedRow(), 2);
//		String lunchCode = (String)temp.getValueAt(temp.getSelectedRow(), 3);
//		Integer lunchPrice = (Integer)temp.getValueAt(temp.getSelectedRow(), 4);
//		String lunchInputDate = (String)temp.getValueAt(temp.getSelectedRow(), 5);
//		
//		ldVO.setLunchCode(lunchCode);
//		ldVO.setName(lunchName);
//		ldVO.setPrice(lunchPrice.intValue());
//		ldVO.setInputDate(lunchInputDate);
//		
////		System.out.println(ldVO);
//		
//		//DBTable에서 얻을 수 있는 값
//		AdminDAO aDAO = AdminDAO.getInstance();
//		try {
//			aDAO.selectDetailLunch(ldVO);
//			
//			//값을 가진 VO를 할당하여 상세화면을 띄워준다.
//			new LunchDetail(lm, this, ldVO);
//			
////			System.out.println(ldVO);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//도시락 코드에 따른 원본이미지와 특장점 조회
//		
//	}//lunchDetail
//
//	
//	
	@Override
	public void mouseClicked(MouseEvent me) {
		
		if(me.getSource() == amv.getJtp()) {
			//주문 탭이 눌렸을 때 처리
			JTabbedPane jtpTemp = (JTabbedPane)me.getSource();
			
			if(jtpTemp.getSelectedIndex() == 1) {
				
				setProductList();
//				if (ot == null) {//조회 thread가 생성되어있지 않음. 주문조회x
//					ot = new OrderThread(lm.getJtOrderList(), lm.getDtmOrderList());
//					ot.start();
//					
//				}//end if
				
			} else if (jtpTemp.getSelectedIndex() == 2) {
				setUserIdList();
//				if (ot == null) {//조회 thread가 생성되어있지 않음. 주문조회x
//				ot = new OrderThread(lm.getJtOrderList(), lm.getDtmOrderList());
//				ot.start();
//				
//				}//end if
				
			} //end else if
			
		}//end if
		
//		if(me.getClickCount() == 2) {
//			//이벤트를 발생한 JTable을 받는다.
//			JTable temp = lm.getJtLunchList();
//			
//			if(me.getSource() == temp) {//도시락 리스트에서 이벤트 발생
//				lunchDetail(temp);
//								
//			}//end if
//			
//		}//end if
//		
	}//mouseClicked
//
	
	
}//class







