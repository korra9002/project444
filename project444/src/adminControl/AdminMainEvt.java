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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import adminDAO.AdminDAO;
import adminVO.CheckListVO;
import adminVO.CheckVO;
import adminVO.UserIdDetailVO;
import adminVO.UserIdVO;
import adminVO.CheckDetailVO;
import adminView.AdminCheckDetailView;
import adminView.AdminIdDetailView;
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

	
	/////////////////////////////////////////////////////////////////////첫번째 탭//////////////////////////////////////////////////////////////////////////
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
				resetCheckList();
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
			JOptionPane.showMessageDialog(amv, "관리자님, db서비스가 원활하지 못한 점 죄송합니다.");
			e.printStackTrace();
		}//end catch
		
	}//setLunchList

	private void setRecentList1() {
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
			List<CheckListVO> list = aDAO.selectOrderbyList(cv);
			
			if(list.isEmpty()) {//제품이 없는 경우
				JOptionPane.showMessageDialog(amv, "정렬할 제품이 없습니다.");
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
			JOptionPane.showMessageDialog(amv, "관리자님, 최신순 정렬 실패! 서비스가 원활하지 못한 점 죄송합니다.");
			e.printStackTrace();
		}//end catch
		
	}//setRecentList1
	
	private void resetCheckList() {
		DefaultTableModel dtm = amv.getDtmCheckList();
		//JTable의 레코드 초기화
		dtm.setRowCount(0);
		Object[] rowData = null;//JTable에 넣을 데이터
		
		//DBMS에서 조회
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			List<CheckListVO> list = aDAO.reselectAllCheckList();
			
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
			
			amv.getJtfSearch1().setText("");
			amv.getJcbCategory1().setSelectedIndex(0);
			amv.getJrbSubject1().setSelected(true);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(amv, "관리자님, reset실패! 서비스가 원활하지 못한 점 죄송합니다.");
			e.printStackTrace();
		}//end catch
		
	}//end resetCheckList
	
	private void openCheckDetail(JTable temp){
		CheckDetailVO dv = new CheckDetailVO();
		
		//도시락 리스트에서 얻을 수 있는 값
		//선택한 행의 도시락 코드를 얻기
		String product_code = (String)temp.getValueAt(temp.getSelectedRow(), 0);
		
		dv.setProduct_code(product_code);
		
//		System.out.println(dv);
		
		//DBTable에서 얻을 수 있는 값
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			aDAO.checkDetail(dv);
			
			//값을 가진 VO를 할당하여 상세화면을 띄워준다.
			new AdminCheckDetailView(amv, this, dv);
			
//			System.out.println(dv);
		} catch (SQLException e) {
			e.printStackTrace();
		}//도시락 코드에 따른 원본이미지와 특장점 조회
		
	}//openCheckDetail
	
	/////////////////////////////////////////////////////////////////////////두번째 탭/////////////////////////////////////////////////////////////////

	private void setProductList() {
	}//setProductList
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////세번째 탭////////////.//////////////////////////////////////////////
	private void setUserIdList() {
		DefaultTableModel dtm = amv.getDtmUserList();
		String userId = null;
		
		userId = amv.getJtfSearch3().getText().trim();
		
		//JTable의 레코드 초기화
		dtm.setRowCount(0);
		Object[] rowData = null;//JTable에 넣을 데이터
		
		//DBMS에서 조회
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			List<UserIdVO> list = aDAO.selectAllUserIdList(userId);
			
			if(list.isEmpty()) {//제품이 없는 경우
				JOptionPane.showMessageDialog(amv, "등록된 유저가 없습니다.");
//				resetUserIdList();
			}//end if
			
			UserIdVO uiVO = null;
			
			for(int i = 0 ; i < list.size() ; i++) {
				uiVO = list.get(i);
				
				//조회결과로 JTable 레코드에 들어갈 데이터를 생성하고, dtm에 추가
				rowData = new Object[7];
				
				//배열에 값을 할당
				rowData[0] = uiVO.getUser_id();
				rowData[1] = uiVO.getUser_name();
				if (uiVO.getGender().equals("F")) {
					rowData[2] = "여자";
				}else {
					rowData[2] = "남자";
				}
//				rowData[2] = uiVO.getGender();
				rowData[3] = uiVO.getPhone();
				rowData[4] = uiVO.getLoc();
				rowData[5] = uiVO.getJoin_date();
				rowData[6] = uiVO.getSuspend_flag();
				
				//dtm에 추가
				dtm.addRow(rowData);
				
			}//end for
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(amv, "관리자님, UserID db서비스가 원활하지 못한 점 죄송합니다.");
			e.printStackTrace();
		}//end catch
		
	}//setUserIdList
	
	private void resetUserIdList() {
		
		DefaultTableModel dtm = amv.getDtmUserList();
		//JTable의 레코드 초기화
		dtm.setRowCount(0);
		Object[] rowData = null;//JTable에 넣을 데이터
		
		//DBMS에서 조회
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			List<UserIdVO> list = aDAO.reselectAllUserIdList();
			
			if(list.isEmpty()) {//제품이 없는 경우
				JOptionPane.showMessageDialog(amv, "등록된 유저가 없습니다.");
			}//end if
			
			UserIdVO uiVO = null;
			
			for(int i = 0 ; i < list.size() ; i++) {
				uiVO = list.get(i);
				
				//조회결과로 JTable 레코드에 들어갈 데이터를 생성하고, dtm에 추가
				rowData = new Object[7];
				
				//배열에 값을 할당
				rowData[0] = uiVO.getUser_id();
				rowData[1] = uiVO.getUser_name();
				if (uiVO.getGender().equals("F")) {
					rowData[2] = "여자";
				}else {
					rowData[2] = "남자";
				}
//				rowData[2] = uiVO.getGender();
				rowData[3] = uiVO.getPhone();
				rowData[4] = uiVO.getLoc();
				rowData[5] = uiVO.getJoin_date();
				rowData[6] = uiVO.getSuspend_flag();
				
				//dtm에 추가
				dtm.addRow(rowData);
				
			}//end for
			
			amv.getJtfSearch3().setText("");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(amv, "관리자님, reset실패! 서비스가 원활하지 못한 점 죄송합니다.");
			e.printStackTrace();
		}//end catch
		
	}//resetUserIdList
	
	private void openUserIdDetail(JTable temp){
		UserIdDetailVO uidVO = new UserIdDetailVO();
		
		//도시락 리스트에서 얻을 수 있는 값
		//선택한 행의 도시락 코드를 얻기
		String user_id = (String)temp.getValueAt(temp.getSelectedRow(), 0);
		
		uidVO.setUser_id(user_id);
		
//		System.out.println(dv);
		
		//DBTable에서 얻을 수 있는 값
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			aDAO.UserIdDetail(uidVO);
			
			//값을 가진 VO를 할당하여 상세화면을 띄워준다.
			new AdminIdDetailView(amv, this, uidVO);
			
//			System.out.println(dv);
		} catch (SQLException e) {
			e.printStackTrace();
		}//도시락 코드에 따른 원본이미지와 특장점 조회
		
	}//openCheckDetail
	
	
	/////////////////////////////////////////////////////////////actionPerformed////////////////////////////////////////////////////////////////////////////////
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == amv.getJcbCategory1()) {//카테고리1 선택 시
			JComboBox jcb = (JComboBox) ae.getSource();
			
			index = jcb.getSelectedIndex();
			
			if (index != 0) {
				setCheckList();
			}//end if
			
		}//end if
		
		if(ae.getSource() == amv.getJcbCategory2()) {//카테고리2 선택 시
			JComboBox jcb = (JComboBox) ae.getSource();
			
			index = jcb.getSelectedIndex();
			
			if (index != 0) {
//				setCheckList();
			}//end if
			
		}//end if
		
		if(ae.getSource() == amv.getJbtSearch1()) {//검색1버튼 클릭
			
			if (amv.getJtfSearch1().getText().isEmpty()) {
				JOptionPane.showMessageDialog(amv, "검색어를 입력해주세요.");
				amv.getJtfSearch1().getCursor();
				
			}else {
				setCheckList();
			}//end else
			
		}//end if
		
		if(ae.getSource() == amv.getJbtSearch2()) {//검색2버튼 클릭
			
			if (amv.getJtfSearch2().getText().isEmpty()) {
				JOptionPane.showMessageDialog(amv, "검색어를 입력해주세요.");
				amv.getJtfSearch2().getCursor();
				
			}else {
//				setCheckList();
			}//end else
			
		}//end if
		
		if(ae.getSource() == amv.getJbtSearch3()) {//검색3버튼 클릭
			
			if (amv.getJtfSearch3().getText().isEmpty()) {
				JOptionPane.showMessageDialog(amv, "검색어를 입력해주세요.");
				amv.getJtfSearch3().getCursor();
				
			}else {
				setUserIdList();
			}//end else
			
		}//end if
		
		
		if(ae.getSource() == amv.getJbtRecent1()) {//최신순 버튼1 클릭
			setRecentList1();
		}//end if
		
		if(ae.getSource() == amv.getJbtRecent2()) {//최신순 버튼2 클릭
//			setRecentList1();
		}//end if
		
		if(ae.getSource() == amv.getJbtRefresh1()) {//새로고침 버튼1 클릭
			setCheckList();
		}//end if
		
		if(ae.getSource() == amv.getJbtRefresh2()) {//새로고침 버튼2 클릭
//			setCheckList();
		}//end if
		
		if(ae.getSource() == amv.getJbtRefresh3()) {//새로고침 버튼3 클릭
			setUserIdList();;
		}//end if
		
		if(ae.getSource() == amv.getJbtReset1()) {//초기화 버튼1 클릭
			resetCheckList();
		}//end if
		
		if(ae.getSource() == amv.getJbtReset2()) {//초기화 버튼2 클릭
//			resetCheckList();
		}//end if
		
		if(ae.getSource() == amv.getJbtReset3()) {//초기화 버튼3 클릭
			resetUserIdList();
		}//end if
		
	}//actionPerformed
	
	
/////////////////////////////////////////////////////////////mouseClicked////////////////////////////////////////////////////////////////////////////////
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
//					ot = new OrderThread(lm.getJtOrderList(), lm.getDtmOrderList());
//					ot.start();
//				
//				}//end if
				
			} //end else if
			
		}//end if
		
		if(me.getClickCount() == 2) {
			//이벤트를 발생한 JTable을 받는다.
			JTable temp1 = amv.getJtCheckList();
			JTable temp2 = amv.getJtProductList();
			JTable temp3 = amv.getJtUserList();
			
			if(me.getSource() == temp1) {//도시락 리스트에서 이벤트 발생
				openCheckDetail(temp1);
			}//end if
			
			if(me.getSource() == temp2) {//도시락 리스트에서 이벤트 발생
//				openProductDetail(temp2);
			}//end if
			
			if(me.getSource() == temp3) {//도시락 리스트에서 이벤트 발생
				openUserIdDetail(temp3);
			}//end if
			
		}//end if
		
	}//mouseClicked
	
}//class







