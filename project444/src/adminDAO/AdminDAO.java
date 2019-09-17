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
import adminVO.ProductListVO;
import adminVO.ProductVO;
import adminVO.UserIdDetailVO;
import adminVO.UserIdVO;
import adminVO.CheckDetailVO;

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
		String url = "jdbc:oracle:thin:@211.63.89.159:1521:orcl";
		String id = "junggo";
		String pass = "1234";
		
		con = DriverManager.getConnection(url, id, pass);
		
		return con;
	}//getConnection
	
	
	/**
	 * DMBS테이블에 존재하는 모든 검수 목록을 조회
	 * @return 검수 목록
	 * @throws SQLException
	 */
	public List<CheckListVO> selectAllCheckList(CheckVO cVO) throws SQLException{
		List<CheckListVO> list = new ArrayList<CheckListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectCheck = new StringBuilder();
		
		try {
			//2. 커넥션 얻기
			con = getConnection();
			
			selectCheck
			.append("	select product_code, img_file, user_id, c.category, product_name, to_char(price, '9,999,999') price, to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and all_flag = 'N'	");
			
			int bindCnt = 0;
			
			if( cVO != null) {// 검색결과가 입력되었을때
				//카테고리가 선택된 경우
				if(!cVO.getCategory().equals("카테고리 전체")&&cVO.getValue().equals("")) {
					selectCheck.append(" and category=?");
					bindCnt = 1;
				}else if(cVO.getCategory().equals("카테고리 전체")&&!cVO.getValue().equals("")){
					selectCheck.append(" and ").append(cVO.getCol_name().equals("제품명")?"product_name":"user_id").append("	like '%'||?||'%'");
					bindCnt = 2;
				}else if(!cVO.getCategory().equals("카테고리 전체")&&!cVO.getValue().equals("")) {
					selectCheck.append(" and category=? and ").append(cVO.getCol_name().equals("제품명")?"product_name":"user_id").append("	like '%'||?||'%'");
					bindCnt = 3;
				}else {
					bindCnt = 0;
				}//end if
				
			}//end if
			
			
			pstmt= con.prepareStatement(selectCheck.toString());
				
			//바인드 변수에 값넣기
//			System.out.println(bindCnt);
			if( bindCnt == 1 ) {
				pstmt.setString(1, cVO.getCategory());
			} else if(bindCnt == 2){
				pstmt.setString(1, cVO.getValue());
			} else if(bindCnt == 3) {
				pstmt.setString(1, cVO.getCategory());
				pstmt.setString(2, cVO.getValue());
			}//end else
			
//			System.out.println( selectCheck );
		
			//5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			CheckListVO clv = null;
			
			while(rs.next()) {
				clv = new CheckListVO(rs.getString("product_code"), rs.getString("img_file"), rs.getString("product_name"), 
						rs.getString("upload_date"), rs.getString("user_id"),rs.getString("category"),rs.getString("price"));
				list.add(clv);//조회된 레코드를 저장한 VO를 list에 추가
			
			}//end while
			
//			System.out.println(list);
		}finally {
			
			//6. 연결 끊기
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//selectAllCheckList
	
	/**
	 * DMBS테이블에 존재하는 모든 검수 목록을 최신순으로 조회
	 * @return 검수 목록
	 * @throws SQLException
	 */
	public List<CheckListVO> selectCheckOrderbyList(CheckVO cVO) throws SQLException{
		List<CheckListVO> list = new ArrayList<CheckListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectCheck = new StringBuilder();
		
		try {
			//2. 커넥션 얻기
			con = getConnection();
			
			selectCheck
			.append("	select product_code, img_file, user_id, c.category, product_name, to_char(price,'9,999,999')price, to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and all_flag = 'N'	");
			
			int bindCnt = 0;
			
			if( cVO != null) {// 검색결과가 입력되었을때
				//카테고리가 선택된 경우
				if(!cVO.getCategory().equals("카테고리 전체")&&cVO.getValue().equals("")) {
					selectCheck.append(" and category=?");
					bindCnt = 1;
				}else if(cVO.getCategory().equals("카테고리 전체")&&!cVO.getValue().equals("")){
					selectCheck.append(" and ").append(cVO.getCol_name().equals("제품명")?"product_name":"user_id").append("	like '%'||?||'%'");
					bindCnt = 2;
				}else if(!cVO.getCategory().equals("카테고리 전체")&&!cVO.getValue().equals("")) {
					selectCheck.append(" and category=? and ").append(cVO.getCol_name().equals("제품명")?"product_name":"user_id").append("	like '%'||?||'%'");
					bindCnt = 3;
				}//end if
				
			}//end if
			
//			System.out.println( selectCheck );
			selectCheck.append("	order by upload_date desc	");
			pstmt= con.prepareStatement(selectCheck.toString());
				
			//바인드 변수에 값넣기
//			System.out.println(bindCnt);
			if( bindCnt == 1 ) {
				pstmt.setString(1, cVO.getCategory());
			} else if(bindCnt == 2){
				pstmt.setString(1, cVO.getValue());
			} else if(bindCnt == 3) {
				pstmt.setString(1, cVO.getCategory());
				pstmt.setString(2, cVO.getValue());
			}//end else
		
			//5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			CheckListVO clv = null;
			
			while(rs.next()) {
				clv = new CheckListVO(rs.getString("product_code"), rs.getString("img_file"), rs.getString("product_name"), 
						rs.getString("upload_date"), rs.getString("user_id"),rs.getString("category"),rs.getString("price"));
				list.add(clv);//조회된 레코드를 저장한 VO를 list에 추가
			
			}//end while
		} finally {
			
			//6. 연결 끊기
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//selectCheckOrderbyList
	
	/**
	 * DMBS테이블에 존재하는 모든 조건을 초기화하여 검수 목록을 조회
	 * @return 검수 목록
	 * @throws SQLException
	 */
	public List<CheckListVO> reselectAllCheckList() throws SQLException{
		List<CheckListVO> list = new ArrayList<CheckListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectCheck = new StringBuilder();
		
		try {
			//2. 커넥션 얻기
			con = getConnection();
			
			selectCheck
			.append("	select product_code, img_file, user_id, c.category, product_name, to_char(price,'9,999,999')price, to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and all_flag = 'N'	");
			
			pstmt= con.prepareStatement(selectCheck.toString());
			
			//바인드 변수에 값넣기
			
			//5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			CheckListVO clv = null;
			
			while(rs.next()) {
				clv = new CheckListVO(rs.getString("product_code"), rs.getString("img_file"), rs.getString("product_name"), 
						rs.getString("upload_date"), rs.getString("user_id"),rs.getString("category"),rs.getString("price"));
				list.add(clv);//조회된 레코드를 저장한 VO를 list에 추가
				
			}//end while
			
		}finally {
			//6. 연결 끊기
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//reselectAllCheckList
	
	/**
	 * 디테일 창을 열 때 필요한 값들을 VO(DTO 역할)에 넣는 작업
	 * @param cdVO 디테일 창에 필요한 값을 넣을 VO
	 * @throws SQLException
	 */
	public void checkDetail(CheckDetailVO cdVO) throws SQLException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectCheckDetail = new StringBuilder();
		
		try {
			//2. 커넥션 얻기
			con = getConnection();
			selectCheckDetail
			.append("	select img_file, product_name, info,  to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date, user_id, c.category, price	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and product_code = ?	");
			
			pstmt = con.prepareStatement(selectCheckDetail.toString());
			
			pstmt.setString(1, cdVO.getProduct_code());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				cdVO.setImg_file(rs.getString("img_file"));
				cdVO.setProduct_name(rs.getString("product_name"));
				cdVO.setInfo(rs.getString("info"));
				cdVO.setUpload_date(rs.getString("upload_date"));
				cdVO.setUser_id(rs.getString("user_id"));
				cdVO.setCategory(rs.getString("category"));
				cdVO.setPrice(rs.getInt("price"));
			}//end while
			
//			System.out.println(dv);
		}finally {
			
			//6. 연결 끊기
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
	}//checkDetail
	
	////////////////////////////////////////////////////////////////////////두번째 탭 start/////////////////////////////////////////////////////////////////////////////////////
	

	/**
	 * DMBS테이블에 존재하는 모든 제품 목록을 조회
	 * @return 제품 목록
	 * @throws SQLException
	 */
	public List<ProductListVO> selectAllProductList(ProductVO pVO) throws SQLException{
		List<ProductListVO> list = new ArrayList<ProductListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectProduct = new StringBuilder();
		
		try {
			//2. 커넥션 얻기
			con = getConnection();
			
			selectProduct
			.append("	select product_code, img_file, user_id, c.category, product_name, to_char(price,'9,999,999')price, to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date, all_flag	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and 	").append(pVO.getQuery());
			
//			System.out.println(selectProduct);
			
			int bindCnt = 0;
			
			if( pVO != null) {// 검색결과가 입력되었을때
				//카테고리가 선택된 경우
				if(!pVO.getCategory().equals("카테고리 전체")&&pVO.getValue().equals("")) {
					selectProduct.append(" and category=?");
					bindCnt = 1;
				}else if(pVO.getCategory().equals("카테고리 전체")&&!pVO.getValue().equals("")){
					selectProduct.append(" and ").append(pVO.getCol_name().equals("제품명")?"product_name":"user_id").append("	like '%'||?||'%'");
					bindCnt = 2;
				}else if(!pVO.getCategory().equals("카테고리 전체")&&!pVO.getValue().equals("")) {
					selectProduct.append(" and category=? and ").append(pVO.getCol_name().equals("제품명")?"product_name":"user_id").append("	like '%'||?||'%'");
					bindCnt = 3;
				}//end if
				
			}//end if
			
//			System.out.println( selectProduct );
//			System.out.println( pVO );
			
			pstmt= con.prepareStatement(selectProduct.toString());
				
			//바인드 변수에 값넣기
//			System.out.println(bindCnt);
			if( bindCnt == 1 ) {
				pstmt.setString(1, pVO.getCategory());
			} else if(bindCnt == 2){
				pstmt.setString(1, pVO.getValue());
			} else if(bindCnt == 3) {
				pstmt.setString(1, pVO.getCategory());
				pstmt.setString(2, pVO.getValue());
			}//end if
		
			//5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			ProductListVO plv = null;
			
			while(rs.next()) {
				plv = new ProductListVO(rs.getString("product_code"), rs.getString("img_file"), rs.getString("product_name"), 
						rs.getString("upload_date"), rs.getString("user_id"),rs.getString("category"),rs.getString("all_flag"),rs.getString("price"));
				list.add(plv);//조회된 레코드를 저장한 VO를 list에 추가
			
			}//end while
//			System.out.println(list);
		}finally {
			
			//6. 연결 끊기
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//selectAllProductList
	
	/**
	 * DMBS테이블에 존재하는 모든 조건을 초기화하여 검수 목록을 조회
	 * @return 검수 목록
	 * @throws SQLException
	 */
	public List<ProductListVO> reselectAllProductList() throws SQLException{
		List<ProductListVO> list = new ArrayList<ProductListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectCheck = new StringBuilder();
		
		try {
			//2. 커넥션 얻기
			con = getConnection();
			
			selectCheck
			.append("	select product_code, img_file, user_id, c.category, product_name, to_char(price,'9,999,999')price, to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date, all_flag	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and (all_flag != 'N' and all_flag != 'F')	");
			
			pstmt= con.prepareStatement(selectCheck.toString());
			
			//바인드 변수에 값넣기
			
			//5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			ProductListVO plv = null;
			
			while(rs.next()) {
				plv = new ProductListVO(rs.getString("product_code"), rs.getString("img_file"), rs.getString("product_name"), 
						rs.getString("upload_date"), rs.getString("user_id"),rs.getString("category"),rs.getString("all_flag"),rs.getString("price"));
				list.add(plv);//조회된 레코드를 저장한 VO를 list에 추가
				
			}//end while
			
		}finally {
			//6. 연결 끊기
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//reselectAllProductList
	
	/**
	 * DMBS테이블에 존재하는 모든 제품 목록을 최신순으로 조회
	 * @return 제품 목록
	 * @throws SQLException
	 */
	public List<ProductListVO> selectOrderByProductList(ProductVO pVO) throws SQLException{
		List<ProductListVO> list = new ArrayList<ProductListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectProduct = new StringBuilder();
		
		try {
			//2. 커넥션 얻기
			con = getConnection();
			
			selectProduct
			.append("	select product_code, img_file, user_id, c.category, product_name, to_char(price,'9,999,999')price, to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date, all_flag	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and 	").append(pVO.getQuery());
			
//			System.out.println(selectProduct);
			
			int bindCnt = 0;
			
			if( pVO != null) {// 검색결과가 입력되었을때
				//카테고리가 선택된 경우
				if(!pVO.getCategory().equals("카테고리 전체")&&pVO.getValue().equals("")) {
					selectProduct.append(" and category=?");
					bindCnt = 1;
				}else if(pVO.getCategory().equals("카테고리 전체")&&!pVO.getValue().equals("")){
					selectProduct.append(" and ").append(pVO.getCol_name().equals("제품명")?"product_name":"user_id").append("	like '%'||?||'%'");
					bindCnt = 2;
				}else if(!pVO.getCategory().equals("카테고리 전체")&&!pVO.getValue().equals("")) {
					selectProduct.append(" and category=? and ").append(pVO.getCol_name().equals("제품명")?"product_name":"user_id").append("	like '%'||?||'%'");
					bindCnt = 3;
				}//end if
				
			}//end if
			
//			System.out.println( selectProduct );
//			System.out.println( pVO );
			selectProduct.append("	order by upload_date desc	");
			pstmt= con.prepareStatement(selectProduct.toString());
				
			//바인드 변수에 값넣기
//			System.out.println(bindCnt);
			if( bindCnt == 1 ) {
				pstmt.setString(1, pVO.getCategory());
			} else if(bindCnt == 2){
				pstmt.setString(1, pVO.getValue());
			} else if(bindCnt == 3) {
				pstmt.setString(1, pVO.getCategory());
				pstmt.setString(2, pVO.getValue());
			}//end if
		
			//5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			ProductListVO plv = null;
			
			while(rs.next()) {
				plv = new ProductListVO(rs.getString("product_code"), rs.getString("img_file"), rs.getString("product_name"), 
						rs.getString("upload_date"), rs.getString("user_id"),rs.getString("category"),rs.getString("all_flag"),rs.getString("price"));
				list.add(plv);//조회된 레코드를 저장한 VO를 list에 추가
			
			}//end while
//			System.out.println(list);
		}finally {
			
			//6. 연결 끊기
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//selectOrderByProductList
	
	////////////////////////////////////////////////////////////////////////두번째 탭 end/////////////////////////////////////////////////////////////////////////////////////

	/**
	 * DMBS테이블에 존재하는 모든 유저ID 목록을 조회
	 * @return 유저ID 목록
	 * @throws SQLException
	 */
	public List<UserIdVO> selectAllUserIdList(String userId) throws SQLException{
		List<UserIdVO> list = new ArrayList<UserIdVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectUserId = new StringBuilder();
		
		try {
			//2. 커넥션 얻기
			con = getConnection();
			
			selectUserId
			.append("	select user_id, user_name, gender, phone, l.loc, to_char(join_date,'yyyy-mm-dd hh24:mi')join_date, suspend_flag	")
			.append("	from id_info i, location_list l	")
			.append("	where (l.loc_code = i.loc_code)	");
			
			int bindCnt = 0;
			
			if( !userId.equals("")) {// 검색결과가 입력되었을때
				selectUserId.append(" and user_id=?");
				bindCnt++;
			}//end if
			
			pstmt= con.prepareStatement(selectUserId.toString());
				
			//바인드 변수에 값넣기
//			System.out.println(bindCnt);
			if( bindCnt == 1 ) {
				pstmt.setString(1, userId);
			}//end if
		
			//5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			UserIdVO uiVO = null;
			
			while(rs.next()) {
				uiVO = new UserIdVO(rs.getString("user_id"), rs.getString("user_name"), rs.getString("gender"), 
						rs.getString("phone"), rs.getString("loc"),rs.getString("join_date"),rs.getString("suspend_flag"));
				list.add(uiVO);//조회된 레코드를 저장한 VO를 list에 추가
			
			}//end while
			
		}finally {
			
			//6. 연결 끊기
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//selectAllUserIdList
	
	/**
	 * DMBS테이블에 존재하는 모든 조건을 초기화하여 검수 목록을 조회
	 * @return 검수 목록
	 * @throws SQLException
	 */
	public List<UserIdVO> reselectAllUserIdList() throws SQLException{
		
		List<UserIdVO> list = new ArrayList<UserIdVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectUserId = new StringBuilder();
		
		try {
			//2. 커넥션 얻기
			con = getConnection();
			
			selectUserId
			.append("	select user_id, user_name, gender, phone, l.loc, to_char(join_date,'yyyy-mm-dd hh24:mi')join_date, suspend_flag	")
			.append("	from id_info i, location_list l	")
			.append("	where (l.loc_code = i.loc_code)	");
			
			pstmt= con.prepareStatement(selectUserId.toString());
				
			//바인드 변수에 값넣기
		
			//5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			UserIdVO uiVO = null;
			
			while(rs.next()) {
				uiVO = new UserIdVO(rs.getString("user_id"), rs.getString("user_name"), rs.getString("gender"), 
						rs.getString("phone"), rs.getString("loc"),rs.getString("join_date"),rs.getString("suspend_flag"));
				list.add(uiVO);//조회된 레코드를 저장한 VO를 list에 추가
			
			}//end while
			
		}finally {
			
			//6. 연결 끊기
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//reselectAllUserIdList
	
	/**
	 * 디테일 창을 열 때 필요한 값들을 VO(DTO 역할)에 넣는 작업
	 * @param uidVO 디테일 창에 필요한 값을 넣을 VO
	 * @throws SQLException
	 */
	public void UserIdDetail(UserIdDetailVO uidVO) throws SQLException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectCheckDetail = new StringBuilder();
		
		try {
			con = getConnection();
			selectCheckDetail
			.append("	select user_id, user_name, gender, phone, l.loc,  to_char(join_date,'yyyy-mm-dd hh24:mi')join_date, suspend_flag	")
			.append("	from id_info i, location_list l	")
			.append("	where (l.loc_code = i.loc_code) and user_id = ?	");
			
			pstmt = con.prepareStatement(selectCheckDetail.toString());
			
			pstmt.setString(1, uidVO.getUser_id());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				uidVO.setUser_name(rs.getString("user_name"));
				uidVO.setGender(rs.getString("gender"));
				uidVO.setPhone(rs.getString("phone"));
				uidVO.setLoc(rs.getString("loc"));
				uidVO.setJoin_date(rs.getString("join_date"));
				uidVO.setSuspend_flag(rs.getString("suspend_flag"));
			}//end while
			
//			System.out.println(dv);
		}finally {
			
			//6. 연결 끊기
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
	}//UserIdDetail
	
	public boolean updateApproval(String code) throws SQLException {
		boolean updateFlag=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection();
			
			StringBuilder update=new StringBuilder();
			
			update
			.append("	update product 	")
			.append("	set all_flag='P'")
			.append("	where product_code=?	");
			
			pstmt= con.prepareStatement(update.toString());
			
			pstmt.setString(1, code);
			
			updateFlag=pstmt.executeUpdate()==1;
			
		} finally {
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}
		
		return updateFlag;
	}
	
	public boolean updateRefuse(String code, String msg) throws SQLException {
		boolean updateFlag=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection();
			
			StringBuilder update=new StringBuilder();
			
			update
			.append("	update product 	")
			.append("	set all_flag='F', rejection_reason=? ")
			.append("	where product_code=?	");
			
			pstmt= con.prepareStatement(update.toString());
			
			
			pstmt.setString(1, msg);
			pstmt.setString(2, code);
			
			updateFlag=pstmt.executeUpdate()==1;
			
		} finally {
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}
		
		return updateFlag;
	}
	
	public boolean updateSuspend(String id, String msg) throws SQLException {
		boolean updateFlag=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection();
			
			StringBuilder update=new StringBuilder();
			
			update
			.append("	update id_info 	")
			.append("	set suspend_flag='Y'	")
			.append("	where user_id=?	");
			
			pstmt= con.prepareStatement(update.toString());
			
			
			pstmt.setString(1, id);
						
			pstmt.executeUpdate();
			
			pstmt.close();
			
			StringBuilder insert=new StringBuilder();
			
			insert
			.append("	insert into suspended_user(user_id, suspend_reason) 	")
			.append("	values(?,?)	");
			
			pstmt= con.prepareStatement(insert.toString());
			
			
			pstmt.setString(1, id);
			pstmt.setString(2, msg);
			
			updateFlag=pstmt.executeUpdate()==1;
			
		} finally {
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}
		
		return updateFlag;
	}
}//class