package chatTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * �����ڿ��� DBMS�� ������ ó���ϴ� Ŭ����,
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
	 * DBMS�� ������ ��ȯ�ϴ� ��.
	 * 
	 * @return ����� Ŀ�ؼ�
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		Connection con = null;
		// 1. ����̹� �ε� (ojdbc8.jar)
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end catch
			// 2. Connection ���
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
			sendChat ="insert into chatting(sender,reciever,chat) values(?,?,?)";

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
			// 2.Ŀ�ؼ� ���
			con = getConnection();

			// 3. ������ ������ü ��� : lunch���̺��� �̸�, �ڵ�, ����, �Է����� �����ֱٿ� �Էµ�
			// �ͺ��� ��ȸ
			String selectLunch = " select chat,sender,input_date from chatting "
					+ "where read_flag ='N'and sender = ? and reciever = ? order by input_date desc";
			pstmt = con.prepareStatement(selectLunch);
			pstmt.setString(1, you);
			pstmt.setString(2, me);
			rs = pstmt.executeQuery();

			ChatVO CV = null;
			while (rs.next()) {
				CV = new ChatVO(rs.getString("chat"),rs.getString("sender"),rs.getDate("input_date"));
				list.add(CV);// ��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			}
			
			pstmt.close();
			
			String setFlag = "update chatting set  read_flag = 'Y'	where read_flag ='N'and sender = ? and reciever = ?";
			pstmt = con.prepareStatement(setFlag);
			pstmt.setString(1, you);
			pstmt.setString(2, me);
			System.out.println(pstmt.executeUpdate()+"�÷��� ������");
			System.out.println(list.size()+"����Ʈ ������");
			System.out.println("------------------------------");
			
			// 4. ���ε� ���� �� �ֱ�
			// 5. ���� ������ ��� ���

		} finally {
			// 6. ���� ����
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
	 * ���̵�� ��й�ȣ�� �Է¹޾� admin_login���̺��� ���̵�� ��й�ȣ�� �´´ٸ� �̸��� ��ȸ�ϴ� ��.
	 * 
	 * @param lv ���̵�� ����� ���� VO
	 * @return ���� - �̸�, ���� - empty
	 * @throws SQLException
	 */
//	public String selectLogin(LoginVO lv) throws SQLException {
//		String adminName = "";
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			// 2. Connection ���
//			con = getConnection();
//			// 3. ������ ������ü ���
//			StringBuilder selectName = new StringBuilder();
//			selectName.append("	select name	").append("	from admin_login	").append("	where id = ? and pass = ?	");
//
//			pstmt = con.prepareStatement(selectName.toString());
//
//			// 4. ���ε庯���� ���ֱ�
//			pstmt.setString(1, lv.getId());
//			pstmt.setString(2, lv.getPass());
//
//			// 5. ������ ���� �� ��� ���
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				adminName = rs.getString("name");
//			} // end if
//
//		} finally {
//			// 6. ���� ����
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
//	 * DBMS���̺� �����ϴ� ��� ���ö� ����� ��ȸ
//	 * 
//	 * @return ���ö� ���
//	 * @throws SQLException
//	 */
//	public List<LunchVO> selectAllLunch() throws SQLException {
//		List<LunchVO> list = new ArrayList<LunchVO>();
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			// 2.Ŀ�ؼ� ���
//			con = getConnection();
//
//			// 3. ������ ������ü ��� : lunch���̺��� �̸�, �ڵ�, ����, �Է����� �����ֱٿ� �Էµ�
//			// �ͺ��� ��ȸ
//			String selectLunch = " select lunch_code, img,name,to_char(input_date,'yyyy-mm-dd hh24:mi') input_date, price from lunch "
//					+ "where delete_flag ='N' order by input_date desc";
//			pstmt = con.prepareStatement(selectLunch);
//			rs = pstmt.executeQuery();
//
//			LunchVO lV = null;
//			while (rs.next()) {
//				lV = new LunchVO(rs.getString("img"), rs.getString("lunch_code"), rs.getString("name"),
//						rs.getString("input_date"), rs.getInt("price"));
//				list.add(lV);// ��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
//			}
//			// 4. ���ε� ���� �� �ֱ�
//			// 5. ���� ������ ��� ���
//
//		} finally {
//			// 6. ���� ����
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
//	 * �����̹����� Ư������ ��ȸ�Ͽ� VO(DTO�� ������ �ϴ�)�� �����ϴ� ��
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
//	 * �̹�����, ���ö���, ����,Ư������ ���ö��ڵ忡 �ش��ϴ� ���ڵ带 ã�� �����ϴ� ��
//	 * 
//	 * @param luVO ���� ���� ��ü
//	 * @return true-����� ���ڵ� ����, false -����� ���ڵ� ����x
//	 * @throws SQLException DBMS���� ���� �߻�
//	 */
//	public boolean updateLunch(LunchUpdateVO luVO) throws SQLException {
//		boolean updateFlag = false;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			// 2.Ŀ�ؼ� ���
//			con = getConnection();
//			// 3.������ ������ü ���
//			StringBuilder updateLunch = new StringBuilder();
//			updateLunch.append("	update lunch	").append("	set img =?, name=?, price=?, strong_point=?	")
//					.append("	where lunch_code=?	");
//
//			pstmt = con.prepareStatement(updateLunch.toString());
//			// System.out.println(luVO.getImg());
//			// 4.���ε庯���� ���ֱ�
//			pstmt.setString(1, luVO.getImg());
//			pstmt.setString(2, luVO.getName());
//			pstmt.setInt(3, luVO.getPrice());
//			pstmt.setString(4, luVO.getStrongPoint());
//			pstmt.setString(5, luVO.getLunchCode());
//			// 5.�������� �� ��� ���
//			updateFlag = pstmt.executeUpdate() == 1;
//
//		} finally {
//			// 6.���� ����
//			if (pstmt != null)
//				pstmt.close();// end if
//			if (con != null)
//				con.close();
//		} // end finally
//		return updateFlag;
//	}// updateLunch
//
//	/**
//	 * ���ö� �ڵ忡 �ش��ϴ� ���ڵ��� ���� �ʿ���� ���� ����
//	 * 
//	 * @param code ������ ���ö� �ڵ�
//	 * @return true - ���� ���� false - ���� ����
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
//		// Ordering ���̺��� orderNum,quantity,
//		// lunchCode,lunchName,orderName,orderTime,status�� ���� 17�ñ����� �ֹ��� ��ȸ;
//		// �������°� �̿Ϸ��� �ֹ��� ���� ��ȸ�ϰ�
//		// ���� ���°� �Ϸ�� �ֹ��� ���߿� ��ȸ
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
