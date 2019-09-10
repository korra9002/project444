package adminDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import adminVO.CheckListVO;
import adminVO.CheckVO;

/**
 * 관리자에서 DMBS의 업무를 처리하는 클래스.
 * @author owner
 */
public class AdminDAO {
	
	private static AdminDAO aDAO;
	
	private AdminDAO() {
		
	}//AdminDAO
	
	public static AdminDAO getInstance() {
		
		if (aDAO == null) {
			aDAO = new AdminDAO();
		}//end if
		
		return aDAO;
		
	}//getInstance
	
	/**
	 * DBMS의 연결을 반환하는 일
	 * @return 연결된 Connection
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException{
		Connection con = null;
		//1. 드라이버 로딩 (ojdbc8.jar)
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch

		//2. Connection 얻기
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "junggo";
		String pass = "1234";
		
		con = DriverManager.getConnection(url, id, pass);
		
		return con;
	}//getConnection
	
	/**
	 * 아이디와 비밀번호를 입력받아 admin_login테이블에서 아이디와 비밀번호가 맞다면 이름을 조회하는 일.
	 * @param lv 아이디와 비번을 가진 VO
	 * @return 성공 - 이름, 실패 - empty
	 * @throws SQLException
	 */
//	public String selectLogin(LoginVO lv) throws SQLException {
//		String adminName= "";
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//		//2. connection 얻기
//		con = getConnection();
//		
//		//3. 쿼리문 생성 객체 얻기
//		StringBuilder selectName = new StringBuilder();
//		selectName
//		.append("	select name	")
//		.append("	from admin_login	")
//		.append("	where id = ? and pass = ?	");
//		
//		pstmt = con.prepareStatement(selectName.toString());
//		
//		//4. bind 변수에 값 넣기
//		pstmt.setString(1, lv.getId());
//		pstmt.setString(2, lv.getPass());
//		
//		//5. 쿼리문 실행 후 결과 얻기
//		rs = pstmt.executeQuery();
//		
//		if (rs.next()) {
//			adminName = rs.getString("name");
//		}//end if
//		
//		} finally {
//		//6. 연결 끊기
//			if(rs != null) {rs.close();}//end if
//			if(pstmt != null) {pstmt.close();}//end if
//			if(con != null) {con.close();}//end if
//		}//end finally
//		
//		return adminName;
//	}//selectLogin
	
	/**
	 * DMBS테이블에 존재하는 모든 검수 목록을 조회
	 * @return 검수 목록
	 * @throws SQLException
	 */
	public List<CheckListVO> selectAllCheckList(CheckVO cv) throws SQLException{
		List<CheckListVO> list = new ArrayList<CheckListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectCheck = new StringBuilder();
		
		try {
			//2. 커넥션 얻기
			con = getConnection();
			
			selectCheck
			.append("	select product_code, img_file, user_id, c.category, product_name, price, to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and all_flag = 'N'	");
			
			int bindCnt = 0;
			
			if( cv != null) {// 검색결과가 입력되었을때
				//카테고리가 선택된 경우
				if(!cv.getCategory().equals("카테고리 선택")) {
					selectCheck.append(" and category=?");
					bindCnt++;
				}//end if
				
				//검색창에 제목|아이디 검색어가 입력된 경우
				
				if(!cv.getValue().equals("")){
					selectCheck.append(" and ").append(cv.getCol_name().equals("제품명")?"product_name":"user_id").append("=?");
					bindCnt++;
				}//end if
				//최신순으로 정렬?
				
			}//end if
			
			System.out.println( selectCheck );
			
			pstmt= con.prepareStatement(selectCheck.toString());
				
			//바인드 변수에 값넣기
			System.out.println(bindCnt);
			if( bindCnt == 1 ) {
				pstmt.setString(1, cv.getCategory());
				
			} else if(bindCnt == 2){
				pstmt.setString(1, cv.getCategory());
				pstmt.setString(2, cv.getValue());
			}//end if
		
			//5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			CheckListVO clv = null;
			
			while(rs.next()) {
				clv = new CheckListVO(rs.getString("product_code"), rs.getString("img_file"), rs.getString("product_name"), 
						rs.getString("upload_date"), rs.getString("user_id"),rs.getString("category"),rs.getInt("price"));
				list.add(clv);//조회된 레코드를 저장한 VO를 list에 추가
			
			}//end while
		}finally {
			
			//6. 연결 끊기
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//selectAllCheckList
	
	
}//class







