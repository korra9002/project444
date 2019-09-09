package chatTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * 관리자에서 DBMS의 업무를 처리하는 클래스,
 * 
 * @author owner
 *
 */
public class AdminDAO {
	private static AdminDAO aDAO;

	private AdminDAO() {
	}// AdminDAO

	public static AdminDAO getInstance() {
		if (aDAO == null) {
			aDAO = new AdminDAO();
		} // end if
		return aDAO;
	}// getInstance

	/**
	 * DBMS의 연결을 반환하는 일.
	 * 
	 * @return 연결된 커넥션
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		Connection con = null;
		// 1. 드라이버 로딩 (ojdbc8.jar)
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end catch
			// 2. Connection 얻기
		String url = "jdbc:oracle:thin:@211.63.89.159:1521:orcl";
		String id = "junggo";
		String pass = "1234";

		con = DriverManager.getConnection(url, id, pass);

		return con;

	}// getConnection
	
	
	public void sendChat(String me, String you, String chat) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 2.
			con = getConnection();
			// 3.
			String sendChat = "";
			sendChat ="insert into chatting(sender,reciever,chat,deal_code) values(?,?,?,99)";

			pstmt = con.prepareStatement(sendChat);
			// 4.
			pstmt.setString(1, me);
			pstmt.setString(2, you);
			pstmt.setString(3, chat);

			// 5.
			pstmt.executeUpdate();

		} finally {
			// 6.
			if (pstmt != null)
				pstmt.close();// end if
			if (con != null)
				con.close();// end if

		} // end finally
	}//sendChat

	public List<ChatVO> selectAllChat(String me,String you) throws SQLException {
		List<ChatVO> list = new ArrayList<ChatVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 2.커넥션 얻기
			con = getConnection();

			// 3. 쿼리문 생성객체 얻기 : lunch테이블에서 이름, 코드, 가격, 입력일을 가장최근에 입력된
			// 것부터 조회
			String selectLunch = " select chat,sender,input_date from chatting "
					+ "where read_flag ='N'and sender = ? and reciever = ? order by input_date desc";
			pstmt = con.prepareStatement(selectLunch);
			pstmt.setString(1, you);
			pstmt.setString(2, me);
			rs = pstmt.executeQuery();

			ChatVO CV = null;
			while (rs.next()) {
				CV = new ChatVO(rs.getString("chat"),rs.getString("sender"),rs.getDate("input_date"));
				list.add(CV);// 조회된 레코드를 저장한 VO를 list에 추가
			}
			
			pstmt.close();
			
			String setFlag = "update chatting set  read_flag = 'Y'	where read_flag ='N'and sender = ? and reciever = ?";
			pstmt = con.prepareStatement(setFlag);
			pstmt.setString(1, you);
			pstmt.setString(2, me);
			System.out.println(pstmt.executeUpdate()+"플래그 사이즈");
			System.out.println(list.size()+"리스트 사이즈");
			System.out.println("------------------------------");
			
			// 4. 바인드 변수 값 넣기
			// 5. 쿼리 수행후 결과 얻기

		} finally {
			// 6. 연결 끊기
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		}
		return list;

	}// selectAllChat

	
	
	
	
	/**
	 * 아이디와 비밀번호를 입력받아 admin_login테이블에서 아이디와 비밀번호가 맞는다면 이름을 조회하는 일.
	 * 
	 * @param lv 아이디와 비번을 가진 VO
	 * @return 성공 - 이름, 실패 - empty
	 * @throws SQLException
	 */
//	public String selectLogin(LoginVO lv) throws SQLException {
//		String adminName = "";
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			// 2. Connection 얻기
//			con = getConnection();
//			// 3. 쿼리문 생성객체 얻기
//			StringBuilder selectName = new StringBuilder();
//			selectName.append("	select name	").append("	from admin_login	").append("	where id = ? and pass = ?	");
//
//			pstmt = con.prepareStatement(selectName.toString());
//
//			// 4. 바인드변수에 값넣기
//			pstmt.setString(1, lv.getId());
//			pstmt.setString(2, lv.getPass());
//
//			// 5. 쿼리문 실행 후 결과 얻기
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				adminName = rs.getString("name");
//			} // end if
//
//		} finally {
//			// 6. 연결 끊기
//			if (rs != null)
//				rs.close();
//			if (pstmt != null)
//				pstmt.close();
//			if (con != null)
//				con.close();
//
//		} // end finally
//		return adminName;
//	}// selectLogin
//
//	/**
//	 * DBMS테이블에 존재하는 모든 도시락 목록을 조회
//	 * 
//	 * @return 도시락 목록
//	 * @throws SQLException
//	 */
//	public List<LunchVO> selectAllLunch() throws SQLException {
//		List<LunchVO> list = new ArrayList<LunchVO>();
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			// 2.커넥션 얻기
//			con = getConnection();
//
//			// 3. 쿼리문 생성객체 얻기 : lunch테이블에서 이름, 코드, 가격, 입력일을 가장최근에 입력된
//			// 것부터 조회
//			String selectLunch = " select lunch_code, img,name,to_char(input_date,'yyyy-mm-dd hh24:mi') input_date, price from lunch "
//					+ "where delete_flag ='N' order by input_date desc";
//			pstmt = con.prepareStatement(selectLunch);
//			rs = pstmt.executeQuery();
//
//			LunchVO lV = null;
//			while (rs.next()) {
//				lV = new LunchVO(rs.getString("img"), rs.getString("lunch_code"), rs.getString("name"),
//						rs.getString("input_date"), rs.getInt("price"));
//				list.add(lV);// 조회된 레코드를 저장한 VO를 list에 추가
//			}
//			// 4. 바인드 변수 값 넣기
//			// 5. 쿼리 수행후 결과 얻기
//
//		} finally {
//			// 6. 연결 끊기
//			if (rs != null)
//				rs.close();
//			if (pstmt != null)
//				pstmt.close();
//			if (con != null)
//				con.close();
//
//		}
//		return list;
//
//	}// selectAllLunch
//
//	/**
//	 * 원본이미지와 특장점을 조회하여 VO(DTO의 역할을 하는)에 설정하는 일
//	 * 
//	 * @param ldVO
//	 * @throws SQLException
//	 */
//	public void selectDetailLunch(LunchDetailVO ldVO) throws SQLException {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			// 2.
//			con = getConnection();
//			// 3.
//			StringBuilder selectLunch = new StringBuilder();
//			selectLunch.append("	select	img,strong_point	").append("	from	lunch")
//					.append("	where	lunch_code = ?	");
//
//			pstmt = con.prepareStatement(selectLunch.toString());
//			// 4.
//
//			pstmt.setString(1, ldVO.getLunchCode());
//			// 5.
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				ldVO.setImg(rs.getString("img"));
//				ldVO.setStrongPoint(rs.getString("strong_point"));
//			} // end if
//
//		} finally {
//			// 6.
//			if (rs != null)
//				rs.close();
//			if (pstmt != null)
//				pstmt.close();
//			if (con != null)
//				con.close();
//
//		} // end finally
//
//	}// selectDetailLunch
//
//	/**
//	 * 이미지명, 도시락명, 가격,특장점을 도시락코드에 해당하는 레코드를 찾아 변경하는 일
//	 * 
//	 * @param luVO 값을 가진 객체
//	 * @return true-변경된 레코드 존재, false -변경된 레코드 존재x
//	 * @throws SQLException DBMS에서 문제 발생
//	 */
//	public boolean updateLunch(LunchUpdateVO luVO) throws SQLException {
//		boolean updateFlag = false;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			// 2.커넥션 얻기
//			con = getConnection();
//			// 3.쿼리문 생성객체 얻기
//			StringBuilder updateLunch = new StringBuilder();
//			updateLunch.append("	update lunch	").append("	set img =?, name=?, price=?, strong_point=?	")
//					.append("	where lunch_code=?	");
//
//			pstmt = con.prepareStatement(updateLunch.toString());
//			// System.out.println(luVO.getImg());
//			// 4.바인드변수에 값넣기
//			pstmt.setString(1, luVO.getImg());
//			pstmt.setString(2, luVO.getName());
//			pstmt.setInt(3, luVO.getPrice());
//			pstmt.setString(4, luVO.getStrongPoint());
//			pstmt.setString(5, luVO.getLunchCode());
//			// 5.쿼리수행 후 결과 얻기
//			updateFlag = pstmt.executeUpdate() == 1;
//
//		} finally {
//			// 6.연결 끊기
//			if (pstmt != null)
//				pstmt.close();// end if
//			if (con != null)
//				con.close();
//		} // end finally
//		return updateFlag;
//	}// updateLunch
//
//	/**
//	 * 도시락 코드에 해당하는 레코드의 값중 필요없는 값을 삭제
//	 * 
//	 * @param code 삭제할 도시락 코드
//	 * @return true - 삭제 성공 false - 삭제 실패
//	 * @throws SQLException
//	 */
//	public boolean deleteLunch(String code) throws SQLException {
//		boolean deleteFlag = false;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			// 2.
//			con = getConnection();
//			// 3.
//			StringBuilder deleteLunch = new StringBuilder();
//			deleteLunch.append("	update lunch	").append("	set delete_flag ='Y'	")
//					.append("	where lunch_code=?	");
//
//			pstmt = con.prepareStatement(deleteLunch.toString());
//			// 4.
//			pstmt.setString(1, code);
//			// 5.
//			deleteFlag = pstmt.executeUpdate() == 1;
//
//		} finally {
//			// 6.
//			if (pstmt != null)
//				pstmt.close();// end if
//			if (con != null)
//				con.close();// end if
//
//		} // end finally
//
//		return deleteFlag;
//	}// deleteLunch
//
//	public void insertLunch(LunchAddVO laVO) throws SQLException {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			// 2.
//			con = getConnection();
//			// 3.
//			StringBuilder insertLunch = new StringBuilder();
//			insertLunch.append("insert into lunch(LUNCH_CODE,IMG,NAME,PRICE,STRONG_POINT) values(lunch_code,?,?,?,?)");
//
//			pstmt = con.prepareStatement(insertLunch.toString());
//			// 4.
//			pstmt.setString(1, laVO.getImg());
//			pstmt.setString(2, laVO.getName());
//			pstmt.setInt(3, laVO.getPrice());
//			pstmt.setString(4, laVO.getStrongPoint());
//			// 5.
//			pstmt.executeUpdate();
//
//		} finally {
//			// 6.
//			if (pstmt != null)
//				pstmt.close();// end if
//			if (con != null)
//				con.close();// end if
//
//		} // end finally
//	}// insertLunch
//
//	public List<OrderVO> selectTodayOrder() throws SQLException {
//		List<OrderVO> list = new ArrayList<OrderVO>();
//		// Ordering 테이블에서 orderNum,quantity,
//		// lunchCode,lunchName,orderName,orderTime,status를 당일 17시까지의 주문만 조회;
//		// 제조상태가 미완료인 주문을 먼저 조회하고
//		// 제조 상태가 완료된 주문은 나중에 조회
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		// 2.
//		try {
//			con = getConnection();
//			String selectTodayOrder = "select order_num,quantity,o.lunch_code,to_char(order_date,'yy-mm-dd HH24:mm') order_date,status,l.name lunchname,o.name ordername,o.ip_addr,o.phone from ordering o, lunch l where (to_char(sysdate,'yy-mm-dd') = to_char(order_date,'yy-mm-dd') )and to_char(order_date,'HH24') <17 and o.lunch_code = l.lunch_code order by status asc";
//			pstmt = con.prepareStatement(selectTodayOrder);
//			rs = pstmt.executeQuery();
//			OrderVO ov = null;
//
//			while (rs.next()) {
//				ov = new OrderVO(rs.getInt("order_num"), rs.getInt("quantity"), rs.getString("lunch_code"),
//						rs.getString("lunchName"), rs.getString("ordername"), rs.getString("order_date"),
//						rs.getString("status"), rs.getString("ip_addr"), rs.getString("phone"));
//				list.add(ov);
//				// System.out.println(ov);
//			}
//		} finally {
//			if (con != null)
//				con.close();
//			if (pstmt != null)
//				pstmt.close();
//		}
//		// 3.
//		// 4.
//		// 5.
//		// 6.
//		return list;
//	}// selectTodayOrder
//
//	public boolean updateStatus(int orderNum, String status) throws SQLException {
//		boolean updateFlag = false;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			// 2.
//			con = getConnection();
//			// 3.
//			StringBuilder updateOrder = new StringBuilder();
//			updateOrder.append("	update ordering	").append("	set status =?	")
//					.append("	where order_num=?	");
//
//			pstmt = con.prepareStatement(updateOrder.toString());
//			// 4.
//			pstmt.setString(1,status);
//			pstmt.setInt(2, orderNum);
//			// 5.
//			updateFlag = pstmt.executeUpdate() == 1;
//
//		} finally {
//			// 6.
//			if (pstmt != null)
//				pstmt.close();// end if
//			if (con != null)
//				con.close();// end if
//
//		} // end finally
//		return updateFlag;
//	}// updateStatus
//
//	public List<CalcVO> selectCalc() throws SQLException {
//		List<CalcVO> list = new ArrayList<CalcVO>();
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		// 2.
//		try {
//			con = getConnection();
//			String selectClac = "select l.name ,l.lunch_code ,sum(quantity) quantity , sum(quantity*price) total_price  from lunch l,ordering o where (l.lunch_code = o.lunch_code) and status = 'Y' group by l.name ,l.lunch_code";
//			pstmt = con.prepareStatement(selectClac);
//			rs = pstmt.executeQuery();
//			CalcVO cv = null;
//
//			while (rs.next()) {
//				cv = new CalcVO(rs.getString("name"),rs.getString("lunch_code"),rs.getInt("quantity"),rs.getInt("total_price"));
//				list.add(cv);
//				// System.out.println(ov);
//			}
//		} finally {
//			if (con != null)
//				con.close();
//			if (pstmt != null)
//				pstmt.close();
//		}
//		return list;
//	}// selectCalc
//	
//	public int deleteOrder(int orderNum) throws SQLException {
//		int cnt = 0;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			con = getConnection();
//			String deleteOrder = "delete from ordering where order_num = ? ";
//			pstmt = con.prepareStatement(deleteOrder);
//			pstmt.setInt(1, orderNum);
//			cnt = pstmt.executeUpdate();
//
//	
//		} finally {
//			if (con != null)
//				con.close();
//			if (pstmt != null)
//				pstmt.close();
//		}
//		
//		return cnt;
//	}

//	public static void main(String[] args) {
//		AdminDAO ad = AdminDAO.getInstance();
//		try {
//	System.out.println(ad.selectCalc());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}//main

}// class
