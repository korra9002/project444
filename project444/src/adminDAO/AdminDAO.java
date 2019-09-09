//package adminDAO;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * �����ڿ��� DMBS�� ������ ó���ϴ� Ŭ����.
// * @author owner
// */
//public class AdminDAO {
//	
//	private static AdminDAO aDAO;
//	
//	private AdminDAO() {
//		
//	}//AdminDAO
//	
//	public static AdminDAO getInstance() {
//		
//		if (aDAO == null) {
//			aDAO = new AdminDAO();
//		}//end if
//		
//		return aDAO;
//		
//	}//getInstance
//	
//	/**
//	 * DMBS�� ������ ��ȯ�ϴ� ��
//	 * @return ����� Connection
//	 * @throws SQLException
//	 */
//	private Connection getConnection() throws SQLException{
//		Connection con = null;
//		//1. ����̹� �ε� (ojdbc8.jar)
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}//end catch
//
//		//2. Connection ���
//		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//		String id = "lunch";
//		String pass = "tiger";
//		
//		con = DriverManager.getConnection(url, id, pass);
//		
//		return con;
//	}//getConnection
//	
//	/**
//	 * ���̵�� ��й�ȣ�� �Է¹޾� admin_login���̺��� ���̵�� ��й�ȣ�� �´ٸ� �̸��� ��ȸ�ϴ� ��.
//	 * @param lv ���̵�� ����� ���� VO
//	 * @return ���� - �̸�, ���� - empty
//	 * @throws SQLException
//	 */
//	public String selectLogin(LoginVO lv) throws SQLException {
//		String adminName= "";
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//		//2. connection ���
//		con = getConnection();
//		
//		//3. ������ ���� ��ü ���
//		StringBuilder selectName = new StringBuilder();
//		selectName
//		.append("	select name	")
//		.append("	from admin_login	")
//		.append("	where id = ? and pass = ?	");
//		
//		pstmt = con.prepareStatement(selectName.toString());
//		
//		//4. bind ������ �� �ֱ�
//		pstmt.setString(1, lv.getId());
//		pstmt.setString(2, lv.getPass());
//		
//		//5. ������ ���� �� ��� ���
//		rs = pstmt.executeQuery();
//		
//		if (rs.next()) {
//			adminName = rs.getString("name");
//		}//end if
//		
//		} finally {
//		//6. ���� ����
//			if(rs != null) {rs.close();}//end if
//			if(pstmt != null) {pstmt.close();}//end if
//			if(con != null) {con.close();}//end if
//		}//end finally
//		
//		return adminName;
//	}//selectLogin
//	
//	/**
//	 * DMBS���̺� �����ϴ� ��� ���ö� ����� ��ȸ
//	 * @return ���ö� ���
//	 * @throws SQLException
//	 */
//	public List<CheckVO> selectAllCheckList() throws SQLException{
//		List<CheckVO> list = new ArrayList<CheckVO>();
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//		//2. Ŀ�ؼ� ���
//			con = getConnection();
//			
//		//3. ������ ���� ��ü ��� : lunch ���̺��� �̸�, �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �ͺ��� ��ȸ
//			StringBuilder selectLunch = new StringBuilder();
//			selectLunch
//			.append("	select img, name, lunch_code, price, to_char(input_date,'yyyy-mm-dd hh24:mi')input_date	")
//			.append("	from lunch	")
//			.append("	where delete_flag = 'N'	")
//			.append("	order by input_date desc	");
//			
//			pstmt = con.prepareStatement(selectLunch.toString());
//			
//		//4. bind ������ �� �ֱ�
//			
//		//5. ���� ���� �� ��� ���
//			rs = pstmt.executeQuery();
//			CheckVO lv = null;
//			
//			while(rs.next()) {
//				lv = new CheckVO(rs.getString("img"), rs.getString("lunch_code"), rs.getString("name"), 
//						rs.getString("input_date"), rs.getInt("price"));
//				list.add(lv);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
//			}//end while
//			
//		}finally {
//		//6. ���� ����
//			if(rs != null) {rs.close();}//end if
//			if(pstmt != null) {pstmt.close();}//end if
//			if(con != null) {con.close();}//end if
//		}//end finally
//		
//		return list;
//	}//selectAllLunch
//	
//	/**
//	 * ���� �̹����� Ư������ ��ȸ�Ͽ� VO(DTO�� ����)�� �����ϴ� ��
//	 * @param ldVO
//	 * @throws SQLException
//	 */
//	public void selectDetailLunch(LunchDetailVO ldVO) throws SQLException{
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//		//2. 
//			con = getConnection();
//			
//		//3.
//			StringBuilder selectLunch = new StringBuilder();
//			selectLunch
//			.append("	select img, strong_point	")
//			.append("	from lunch	")
//			.append("	where lunch_code = ?	");
//			
//			pstmt = con.prepareStatement(selectLunch.toString());
//			
//		//4.
//			pstmt.setString(1, ldVO.getLunchCode());
//			
//		//5.
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				ldVO.setImg(rs.getString("img"));
//				ldVO.setStrongPoint(rs.getString("strong_point"));
//			}
//			
//		} finally {
//		//6.
//			if(rs != null) {rs.close();}//end if
//			if(pstmt != null) {pstmt.close();}//end if
//			if(con != null) {con.close();}//end if
//		}//end finally
//		
//	}//selectDetailLunch
//	
//	/**
//	 * �̹�����, ���ö���, ����, Ư������ ���ö� �ڵ忡 �ش��ϴ� ���ڵ带 ã�� �����ϴ� ��
//	 * @param luVO ���� ���� ��ü
//	 * @return true = ����� ���ڵ� ����, false = ����� ���ڵ� �������� ����
//	 * @throws SQLException DBMS���� ���� �߻�
//	 */
//	public boolean updateLunch(LunchUpdateVO luVO) throws SQLException{
//		boolean updateFlag = false;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//		//2. Ŀ�ؼ� ���
//			con = getConnection();
//		
//		//3. ������ ������ü ���
//			StringBuilder updateLunch = new StringBuilder();
//			updateLunch
//			.append("	update lunch 	")
//			.append("	set img = ?, name = ?, price = ?, strong_point = ?	")
//			.append("	where lunch_code = ?	");
//			
//			pstmt = con.prepareStatement(updateLunch.toString());
//			
//		//4. ���ε� ������ �� �ֱ�
//			pstmt.setString(1, luVO.getImg());
//			pstmt.setString(2, luVO.getName());
//			pstmt.setInt(3, luVO.getPrice());
//			pstmt.setString(4, luVO.getStrongPoint());
//			pstmt.setString(5, luVO.getLunchCode());
//			
//		//5. ���� ���� �� ��� ���
//			updateFlag = pstmt.executeUpdate() == 1;
//		}finally {
//		//6. ���� ����
//			if(pstmt != null) {pstmt.close();}//end if
//			if(con != null) {con.close();}//end if
//		}//end finally
//		
//		return updateFlag;
//	}//updateLunch
//	
//	/**
//	 * ���ö� �ڵ忡 �ش��ϴ� ���ڵ��� �� �� �ʿ� ���� ���� ����
//	 * @param code ������ ���ö� �ڵ�
//	 * @return true = ���� ����, false = ���� ����
//	 * @throws SQLException �����߻�(DBMS)
//	 */
//	public boolean deleteLunch(String code)throws SQLException{
//		boolean deleteFlag = false;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//		//2
//			con = getConnection();
//			
//		//3
//			StringBuilder deleteLunch = new StringBuilder();
//			deleteLunch
//			.append("	update lunch	")
//			.append("	set delete_flag = 'Y'	")
//			.append("	where lunch_code = ?	");
//			
//			pstmt = con.prepareStatement(deleteLunch.toString());
//			
//		//4
//			pstmt.setString(1, code);
//			
//		//5
//			deleteFlag = pstmt.executeUpdate() == 1;
//			
//		} finally {
//		//6
//			if(pstmt != null) {pstmt.close();}//end if
//			if(con != null) {con.close();}//end if
//		}//end finally
//		
//		return deleteFlag;
//	}//deleteLunch
//	
//	public void insertLunch(LunchAddVO laVO)throws SQLException{
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//		//2
//			con = getConnection();
//			
//		//3
//			StringBuilder insertLunch = new StringBuilder();
//			insertLunch
//			.append("insert into lunch(lunch_code,img,name,price,strong_point) values(lunch_code,?,?,?,?)");
//			
//			pstmt = con.prepareStatement(insertLunch.toString());
//			
//		//4
//			pstmt.setString(1, laVO.getImg());
//			pstmt.setString(2, laVO.getName());
//			pstmt.setInt(3, laVO.getPrice());
//			pstmt.setString(4, laVO.getStrongPoint());
//			
//		//5
//			pstmt.executeUpdate();
//			
//		} finally {
//		//6
//			if(pstmt != null) {pstmt.close();}//end if
//			if(con != null) {con.close();}//end if
//		}
//	}//insertLunch
//	
//	public List<OrderVO> selectTodayOrder() throws SQLException {
//		List<OrderVO> list = new ArrayList<OrderVO>();
//		
//		//Ordering ���̺��� orderNum, quantity, lunchCode, lunchName, orderName, orderTime, status�� ���� 17�ñ����� �ֹ��� ��ȸ
//		//�������°� �̿Ϸ��� �ֹ��� ���� ��ȸ�ϰ� 
//		
//		//�������°� �Ϸ�� �ֹ��� ���߿� ��ȸ
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//		//2. Ŀ�ؼ� ���
//			con = getConnection();
//			
//		//3. ������ ���� ��ü ��� : lunch ���̺��� �̸�, �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �ͺ��� ��ȸ
//			StringBuilder selectOrder = new StringBuilder();
//			selectOrder
//			.append("	select od.order_num, od.quantity, lc.lunch_code, lc.name lunch_name, od.name order_name, od.ip_addr, od.phone,	")
//			.append("	to_char(od.order_date,'yyyy-mm-dd hh24:mi') order_date, od.status	")
//			.append("	from ordering od, lunch lc	")
//			.append("	where (od.lunch_code = lc.lunch_code) and (to_char(order_date,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd'))	")
//			.append("	and (to_char(order_date,'hh24') < 18)	")
//			.append("	order by status	");
//			
//			pstmt = con.prepareStatement(selectOrder.toString());
//			
//		//4. bind ������ �� �ֱ�
//			
//		//5. ���� ���� �� ��� ���
//			rs = pstmt.executeQuery();
//			OrderVO ov = null;
//			
//			while(rs.next()) {
//				ov = new OrderVO(rs.getInt("order_num"), rs.getInt("quantity"), rs.getString("lunch_code"),
//						rs.getString("lunch_name"), rs.getString("order_name"), rs.getString("order_date"),
//						rs.getString("status"), rs.getString("ip_addr"), rs.getString("phone"));
//				
//				list.add(ov);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
//			}//end while
//			
//		}finally {
//		//6. ���� ����
//			if(rs != null) {rs.close();}//end if
//			if(pstmt != null) {pstmt.close();}//end if
//			if(con != null) {con.close();}//end if
//		}//end finally
//		
//		return list;
//		
//	}//selectTodayOrder
//	
//	
//	
//	public boolean updateStatus(int orderNum, String status) throws SQLException{
//		boolean updateFlag = false;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//		//2
//			con = getConnection();
//			
//		//3
//			StringBuilder updateOrder = new StringBuilder();
//			updateOrder
//			.append("	update ordering	")
//			.append("	set status = ?	")
//			.append("	where order_num = ?	");
//			
//			pstmt = con.prepareStatement(updateOrder.toString());
//			
//		//4
//			pstmt.setString(1, status);
//			pstmt.setInt(2, orderNum);
//			
//		//5
//			updateFlag = pstmt.executeUpdate() == 1;
//			
//		} finally {
//		//6
//			if(pstmt != null) {pstmt.close();}//end if
//			if(con != null) {con.close();}//end if
//		}//end finally
//		
//		return updateFlag;
//	}//updateStatus
//	
//	public List<CalcVO> selectCalc()throws SQLException{
//		List<CalcVO> list = new ArrayList<CalcVO>();
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//		//2. Ŀ�ؼ� ���
//			con = getConnection();
//			
//		//3. ������ ���� ��ü ��� : lunch ���̺��� �̸�, �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �ͺ��� ��ȸ
//			StringBuilder selectCalc = new StringBuilder();
//			selectCalc
//			.append("	select l.name, l.lunch_code, sum(o.quantity) quantity, sum(l.price * o.quantity) total_price	")
//			.append("	from lunch l, ordering o	")
//			.append("	where (o.lunch_code = l.lunch_code) and o.status = 'Y'	")
//			.append("	group by l.name, l.lunch_code	");
//			
//			pstmt = con.prepareStatement(selectCalc.toString());
//			
//		//4. bind ������ �� �ֱ�
//			
//		//5. ���� ���� �� ��� ���
//			rs = pstmt.executeQuery();
//			CalcVO cv = null;
//			
//			while(rs.next()) {
//				cv = new CalcVO(rs.getString("name"), rs.getString("lunch_code"), rs.getInt("quantity"),
//						rs.getInt("total_price"));
//				
//				list.add(cv);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
//			}//end while
//			
//		}finally {
//		//6. ���� ����
//			if(rs != null) {rs.close();}//end if
//			if(pstmt != null) {pstmt.close();}//end if
//			if(con != null) {con.close();}//end if
//		}//end finally
//		
//		return list;
//	}//selectCalc
//	
//	
//	public int deleteOrder(int orderNum)throws SQLException{
//		int deleteCnt = 0;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//		//2
//			con = getConnection();
//			
//		//3
//			StringBuilder deleteOrder = new StringBuilder();
//			deleteOrder
//			.append("	delete from ordering	")
//			.append("	where order_num = ?	");
//			
//			pstmt = con.prepareStatement(deleteOrder.toString());
//			
//		//4
//			pstmt.setInt(1, orderNum);
//			
//		//5
//			deleteCnt = pstmt.executeUpdate();
//			
//		} finally {
//		//6
//			if(pstmt != null) {pstmt.close();}//end if
//			if(con != null) {con.close();}//end if
//		}//end finally
//		
//		return deleteCnt;
//	}//deleteLunch
//	
//	
////	public static void main(String[] args) {
////		AdminDAO ad = AdminDAO.getInstance();
////		
////		try {
////			System.out.println(ad.selectCalc());
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
////	}//main
//	
//}//class
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
