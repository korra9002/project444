package userDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import chatTest.ChatVO;
import userRun.RunMarketMain;
import userVO.AllListVO;
import userVO.ChatListVO;
import userVO.DCodeAndIdAO;
import userVO.FlagVO;
import userVO.ForgotIdVO;
import userVO.ForgotPwVO;
import userVO.InsertProductVO;
import userVO.InterestListVO;
import userVO.LoginVO;
import userVO.MarketDetailVO;
import userVO.PersonalInformVO;
import userVO.RecentChatVO;
import userVO.SaleListVO;
import userVO.SignUpVO;
import userVO.modifyInformVO;

public class UserDAO {
	public static UserDAO uDAO;

	DecimalFormat df = new DecimalFormat("00");

	private UserDAO() {

	}// userDAO

	public static UserDAO getInstance() {
		if (uDAO == null) {
			uDAO = new UserDAO();
		} // end if
		return uDAO;
	}// getInstance

	public Connection getConn() throws SQLException {
		Connection con = null;

		// 1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("ddddd");
			e.printStackTrace();
		} // end catch

		// 2. Connection 얻기

//		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String url = "jdbc:oracle:thin:@211.63.89.159:1521:orcl";
		String id = "junggo";
		String pass = "1234";

		con = DriverManager.getConnection(url, id, pass);

		return con;
	}// getConn

	public List<AllListVO> selectRefresh() throws SQLException {
		List<AllListVO> list = new ArrayList<AllListVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 커넥션 얻기
			con = getConn();

			// 3.쿼리문 생성객체 얻기 : lunch테이블에서 이름 코드, 가격, 입력일을 가장 최근에 입력된 것 부터 조회
			StringBuilder selectAll = new StringBuilder();
			selectAll

					.append(" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, PRODUCT.USER_ID, PRICE,loc_code ")
					.append(" from PRODUCT, id_info ")
					.append(" where ( PRODUCT.user_id= id_info.user_id) and all_flag ='P' ");

//        .append(" order by inputDate desc ");

			pstmt = con.prepareStatement(selectAll.toString());

			// 4. 바인드변수에 값 넣기
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			AllListVO alv = null;

			while (rs.next()) {
				alv = new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc_code"), rs.getString("inputDate"),
						rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"), rs.getInt("PRICE"));

				list.add(alv);
			} // end while
		} finally {
			// 6. 연결끊기
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if

		} // end finally
		return list;

	}// selectRefresh

	public List<AllListVO> selectAllList(int jcbAreaIndex, int jcbCateIndex, String jtfText) throws SQLException {
		List<AllListVO> list = new ArrayList<AllListVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String area = "";
		String category = "";
		String productName = "";

		try {
			// 커넥션 얻기
			con = getConn();

			// 3.쿼리문 생성객체 얻기 : lunch테이블에서 이름 코드, 가격, 입력일을 가장 최근에 입력된 것 부터 조회
			StringBuilder selectArea = new StringBuilder();
			selectArea.append(
					" select p.PRODUCT_CODE, p.IMG_FILE, p.PRODUCT_NAME, to_char(p.UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, p.CATEGORY_CODE,"
							+ " P.USER_ID, p.PRICE, i.loc_code,l.loc ")
					.append(" from PRODUCT p, id_info i, location_list l ")
					.append(" where ( p.user_id= i.user_id and i.loc_code=l.loc_code) and all_flag ='P' ");

			if (jcbAreaIndex != 0) {
//				selectArea.append( " and user_id in ( select user_id  from id_info where loc_code ='"+df.format(jcbAreaIndex)+"') ");
				selectArea.append(" and l.loc_code =" + df.format(jcbAreaIndex) + " ");
			}
			if (jcbCateIndex != 0) {
				selectArea.append(" and p.category_code ='" + df.format(jcbCateIndex) + "' ");
			}
			if (!jtfText.isEmpty()) {
				selectArea.append(" and p.product_name like '%" + jtfText + "%' ");
			}

//			selectArea.append(" order by inputDate desc "); 

//			select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, USER_ID, PRICE
//			from PRODUCT
//			where all_flag ='P'  and user_id in ( select user_id  from id_info where loc_code ='07') and category_code ='04'  and product_name like '%은과%'
//			order by inputDate desc ;

			/*
			 * select user_id,product_name from product where ( user_id in ( select user_id
			 * from id_info where loc_code ='12')) and all_flag ='P'; 이거임
			 */

			pstmt = con.prepareStatement(selectArea.toString());

			// 4. 바인드변수에 값 넣기
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			AllListVO alv = null;

			while (rs.next()) {
				alv = new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc"), rs.getString("inputDate"),
						rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"), rs.getInt("PRICE"));
				list.add(alv);
			} // end while
		} finally {
			// 6. 연결끊기
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if

		} // end finally
		return list;

	}// selectAreaList

	public List<AllListVO> selectListByID(int jcbAreaIndex, int jcbCateIndex, String jtfText) throws SQLException {

		List<AllListVO> list = new ArrayList<AllListVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 커넥션 얻기
			con = getConn();

			// 3.쿼리문 생성객체 얻기 : lunch테이블에서 이름 코드, 가격, 입력일을 가장 최근에 입력된 것 부터 조회
			StringBuilder selectArea = new StringBuilder();
			selectArea.append(
					" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, PRODUCT.user_id, PRICE,loc_code ")
					.append(" from PRODUCT, id_info ")
					.append(" where ( PRODUCT.user_id= id_info.user_id) and all_flag ='P' ");

			if (jcbAreaIndex != 0) {
//				selectArea.append( " and user_id in ( select user_id  from id_info where loc_code ='"+df.format(jcbAreaIndex)+"') ");
				selectArea.append(" and loc_code =" + df.format(jcbAreaIndex) + " ");
			}
			if (jcbCateIndex != 0) {
				selectArea.append(" and category_code ='" + df.format(jcbCateIndex) + "' ");
			}
			if (!jtfText.isEmpty()) {
				selectArea.append(" and id_info.user_id like '%" + jtfText + "%' ");
			}

			pstmt = con.prepareStatement(selectArea.toString());

			// 4. 바인드변수에 값 넣기
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			AllListVO alv = null;

			while (rs.next()) {
				alv = new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc_code"), rs.getString("inputDate"),
						rs.getString("CATEGORY_CODE"), rs.getString("user_id"), rs.getInt("PRICE"));
				list.add(alv);
			} // end while
		} finally {
			// 6. 연결끊기
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if

		} // end finally
		return list;

	}// selectListByID

	public List<AllListVO> selectListRecent(int jcbAreaIndex, int jcbCateIndex, String jtfText) throws SQLException {
		List<AllListVO> list = new ArrayList<AllListVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String area = "";
		String category = "";
		String productName = "";

		try {
			// 커넥션 얻기
			con = getConn();

			// 3.쿼리문 생성객체 얻기 : lunch테이블에서 이름 코드, 가격, 입력일을 가장 최근에 입력된 것 부터 조회
			StringBuilder selectArea = new StringBuilder();
			selectArea.append(
					" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, PRODUCT.USER_ID, PRICE,loc_code ")
					.append(" from PRODUCT, id_info ")
					.append(" where ( PRODUCT.user_id= id_info.user_id) and all_flag ='P' ");

			if (jcbAreaIndex != 0) {
//				selectArea.append( " and user_id in ( select user_id  from id_info where loc_code ='"+df.format(jcbAreaIndex)+"') ");
				selectArea.append(" and loc_code =" + df.format(jcbAreaIndex) + " ");
			}
			if (jcbCateIndex != 0) {
				selectArea.append(" and category_code ='" + df.format(jcbCateIndex) + "' ");
			}
			if (!jtfText.isEmpty()) {
				selectArea.append(" and product_name like '%" + jtfText + "%' ");
			}

			selectArea.append(" order by inputDate desc ");

//			select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, USER_ID, PRICE
//			from PRODUCT
//			where all_flag ='P'  and user_id in ( select user_id  from id_info where loc_code ='07') and category_code ='04'  and product_name like '%은과%'
//			order by inputDate desc ;

			/*
			 * select user_id,product_name from product where ( user_id in ( select user_id
			 * from id_info where loc_code ='12')) and all_flag ='P'; 이거임
			 */

			pstmt = con.prepareStatement(selectArea.toString());

			// 4. 바인드변수에 값 넣기
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			AllListVO alv = null;

			while (rs.next()) {
				alv = new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc_code"), rs.getString("inputDate"),
						rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"), rs.getInt("PRICE"));
				list.add(alv);
			} // end while
		} finally {
			// 6. 연결끊기
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if

		} // end finally
		return list;

	}// selectListRecent

	public List<AllListVO> selectListPrice(int jcbAreaIndex, int jcbCateIndex, String jtfText) throws SQLException {
		List<AllListVO> list = new ArrayList<AllListVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String area = "";
		String category = "";
		String productName = "";

		try {
			// 커넥션 얻기
			con = getConn();

			// 3.쿼리문 생성객체 얻기 : lunch테이블에서 이름 코드, 가격, 입력일을 가장 최근에 입력된 것 부터 조회
			StringBuilder selectArea = new StringBuilder();
			selectArea.append(
					" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, PRODUCT.USER_ID, PRICE,loc_code ")
					.append(" from PRODUCT, id_info ")
					.append(" where ( PRODUCT.user_id= id_info.user_id) and all_flag ='P' ");

			if (jcbAreaIndex != 0) {
//				selectArea.append( " and user_id in ( select user_id  from id_info where loc_code ='"+df.format(jcbAreaIndex)+"') ");
				selectArea.append(" and loc_code =" + df.format(jcbAreaIndex) + " ");
			}
			if (jcbCateIndex != 0) {
				selectArea.append(" and category_code ='" + df.format(jcbCateIndex) + "' ");
			}
			if (!jtfText.isEmpty()) {
				selectArea.append(" and product_name like '%" + jtfText + "%' ");
			}

			selectArea.append(" order by price desc ");

//			select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, USER_ID, PRICE
//			from PRODUCT
//			where all_flag ='P'  and user_id in ( select user_id  from id_info where loc_code ='07') and category_code ='04'  and product_name like '%은과%'
//			order by inputDate desc ;

			/*
			 * select user_id,product_name from product where ( user_id in ( select user_id
			 * from id_info where loc_code ='12')) and all_flag ='P'; 이거임
			 */

			pstmt = con.prepareStatement(selectArea.toString());

			// 4. 바인드변수에 값 넣기
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			AllListVO alv = null;

			while (rs.next()) {
				alv = new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc_code"), rs.getString("inputDate"),
						rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"), rs.getInt("PRICE"));
				list.add(alv);
			} // end while
		} finally {
			// 6. 연결끊기
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if

		} // end finally
		return list;

	}// selectListRecent

	/**
	 * 상품 디테일창으로 정보 넘기는 method
	 * 
	 * @param productCode
	 * @param loc_code
	 * @return
	 * @throws SQLException
	 */
	public MarketDetailVO selectProDetail(String productCode, String loc_code) throws SQLException {

		MarketDetailVO mdVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 커넥션 얻기
			con = getConn();

			// 3.쿼리문 생성객체 얻기 : lunch테이블에서 이름 코드, 가격, 입력일을 가장 최근에 입력된 것 부터 조회
			StringBuilder selectDetail = new StringBuilder();
			selectDetail.append(
					" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, PRODUCT.USER_ID, PRICE,loc_code, info ")
					.append(" from PRODUCT, id_info ")
					.append(" where ( PRODUCT.user_id= id_info.user_id) and all_flag ='P' and PRODUCT_CODE=? ");// 물음표랑
																												// ''랑
																												// 같이쓰면
																												// 안됨.
//				.append(" order by inputDate desc "); 

			pstmt = con.prepareStatement(selectDetail.toString());

			// 4. 바인드변수에 값 넣기
			pstmt.setString(1, productCode);
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();

			if (rs.next()) {
				mdVO = new MarketDetailVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc_code"), rs.getString("inputDate"),
						rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"), rs.getString("INFO"),
						rs.getInt("PRICE"));
			} // end if
		} finally {
			// 6. 연결끊기
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if

		} // end finally
		return mdVO;
	}// selectProDetail

///////////////////////////////////// 상품 입력창 //////////////////////////////////////////////

	public void insertProduct(InsertProductVO ipVO, String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConn();

			System.out.println(id + "바보");
			StringBuilder insertProduct = new StringBuilder();
			insertProduct
					.append("insert into product (product_code, img_file, category_code, product_name, info, price,"
							+ "user_id) values(pro_code,?,?,?,?,?,?)");

			// insert into product (product_code,img_file, category_code, product_name,
			// info, price,
			// user_id) values(pro_code,'주루미.jpg','07','귀욤귀욤 주루미','주루미 열쇠고리',999999,
			// 'baek');

			System.out.println(df.format(Integer.parseInt(ipVO.getCategory()) + 1));
			pstmt = con.prepareStatement(insertProduct.toString());

			pstmt.setString(1, ipVO.getImg());
			pstmt.setString(2, df.format(Integer.parseInt(ipVO.getCategory()) + 1));
			pstmt.setString(3, ipVO.getSubject());
			pstmt.setString(4, ipVO.getpDetail());
			pstmt.setInt(5, ipVO.getPrice());
			pstmt.setString(6, id);

			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally
	}// insertProduct

///////////////////////////////////// Mypage method ///////////////////////////////////////

	/**
	 * 마이페이지에서 본인이 판매중인 상품 리스트 보여주기
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<SaleListVO> selectSaleList(String id, String temp_flag) throws SQLException {

		List<SaleListVO> list = new ArrayList<SaleListVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//커넥션 얻기
			con = getConn();

//3.쿼리문 생성객체 얻기 : lunch테이블에서 이름 코드, 가격, 입력일을 가장 최근에 입력된 것 부터 조회
			StringBuilder selectAll = new StringBuilder();

			if (temp_flag.equals("S")) {
				selectAll.append(
						" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, PRODUCT.USER_ID, PRICE,loc_code, all_flag ")
						.append(" from PRODUCT, id_info ")
						.append(" where ( PRODUCT.user_id= id_info.user_id) and all_flag ='P' and PRODUCT.USER_ID=? ");
			} else if (temp_flag.equals("P")) {
				selectAll.append(
						" select PRODUCT.PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, PRODUCT.USER_ID, "
								+ "DEAL.SALE_DATE, PRICE, DEAL.SALE_FLAG, DEAL.USER_ID, id_info.loc_code ")
						.append(" from PRODUCT, DEAL , id_info  ")
						.append(" where ( PRODUCT.PRODUCT_CODE= DEAL.PRODUCT_CODE and deal.user_id= id_info.user_id) and sale_flag ='P' and DEAL.USER_ID=? ");
			} // end else

			pstmt = con.prepareStatement(selectAll.toString());

//4. 바인드변수에 값 넣기
			pstmt.setString(1, id);
//5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			SaleListVO slv = null;

			while (rs.next()) {
				slv = new SaleListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc_code"), rs.getString("inputDate"),
						rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"), rs.getString("all_flag"),
						rs.getInt("PRICE"));

				list.add(slv);
			} // end while
		} finally {
//6. 연결끊기
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if

		} // end finally
		return list;

	}// selectSaleList

	/**
	 * 마이페이지 판매내역-판매완료탭이 눌렸을 때 처리
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<SaleListVO> selectCompList(String id) throws SQLException {

		List<SaleListVO> list = new ArrayList<SaleListVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//커넥션 얻기
			con = getConn();

//3.쿼리문 생성객체 얻기 : lunch테이블에서 이름 코드, 가격, 입력일을 가장 최근에 입력된 것 부터 조회
			StringBuilder selectAll = new StringBuilder();
			selectAll.append(
					" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, PRODUCT.USER_ID, PRICE,loc_code, all_flag ")
					.append(" from PRODUCT, id_info  ")
					.append(" where ( PRODUCT.user_id= id_info.user_id) and all_flag ='B' and PRODUCT.USER_ID=? ");

			pstmt = con.prepareStatement(selectAll.toString());

//4. 바인드변수에 값 넣기
			pstmt.setString(1, id);
//5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();
			SaleListVO slv = null;

			while (rs.next()) {
				slv = new SaleListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc_code"), rs.getString("inputDate"),
						rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"), rs.getString("all_flag"),
						rs.getInt("PRICE"));

				list.add(slv);
			} // end while
		} finally {
//6. 연결끊기
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if

		} // end finally
		return list;

	}// selectCompList

	public boolean deletePost(String product_code) throws SQLException {
		boolean deleteFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConn();

			StringBuilder deletePost = new StringBuilder();
			deletePost.append("	update Product	")
					// .append(" set img='', strong_point='', delete_flag='Y' ") // not null 걸려있어서
					// 에러뜸
					.append("	set all_flag='D'	").append("	where product_code=?	");

			pstmt = con.prepareStatement(deletePost.toString());

			pstmt.setString(1, product_code);

			deleteFlag = pstmt.executeUpdate() == 1;
		} finally {
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if
		} // end finally
		return deleteFlag;
	}// deletePost

/////////////////////////////////////////////////////////////////////////////////MyPage ////////////////////////////////////////////////////////////////////////

	////////////////////////////////////// 채팅관련
	////////////////////////////////////// 메서드!!!!///////////////////////////////////

	public int addDeal(String productCode, String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 커넥션 얻기
			con = getConn();

			String check = "select deal_code from deal where product_code =? and user_id=?";

			pstmt = con.prepareStatement(check);

			// 4. 바인드변수에 값 넣기
			pstmt.setString(1, productCode);
			pstmt.setString(2, id);
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("거래중입니다.");
				return -1;
			}
			pstmt.close();

			String insert = "insert into deal(deal_code,product_code,user_id) values(deal_code,?,?) ";
			pstmt = con.prepareStatement(insert);
			pstmt.setString(1, productCode);
			pstmt.setString(2, id);
			int cnt = pstmt.executeUpdate();

			System.out.println(cnt);
			return cnt;

		} finally {
			// 6. 연결끊기
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if

		} // end finally

	}// addDeal

	public DCodeAndIdAO getDCodeAndId(String productCode, String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 커넥션 얻기
			con = getConn();

			String check = "select deal_code, p.user_id seller_id from deal d,product p where p.product_code = ? and  d.user_id= ? and (d.product_code = p.product_code)";

			pstmt = con.prepareStatement(check);

			// 4. 바인드변수에 값 넣기
			pstmt.setString(1, productCode);
			pstmt.setString(2, id);
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return new DCodeAndIdAO(rs.getString("deal_code"), rs.getString("seller_id"));
			} else {
				System.out.println("거래가 없습니다.");
				return new DCodeAndIdAO("", "");
			}

		} finally {
			// 6. 연결끊기
			if (rs != null) {
				rs.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
			if (con != null) {
				con.close();
			} // end if

		} // end finally
	}// getDealCode

	public List<ChatVO> selectAllChat(String me, String you, String dealCode) throws SQLException {
		List<ChatVO> list = new ArrayList<ChatVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 2.커넥션 얻기
			con = getConn();

			// 3. 쿼리문 생성객체 얻기 : lunch테이블에서 이름, 코드, 가격, 입력일을 가장최근에 입력된
			// 것부터 조회
			String selectLunch = " select chat,sender,input_date from chatting "
					+ "where deal_code = ? order by input_date asc";
			pstmt = con.prepareStatement(selectLunch);
			pstmt.setString(1, dealCode);
//	         pstmt.setString(2, me);
			rs = pstmt.executeQuery();

			ChatVO CV = null;
			while (rs.next()) {
				CV = new ChatVO(rs.getString("chat"), rs.getString("sender"), rs.getDate("input_date"));
				list.add(CV);// 조회된 레코드를 저장한 VO를 list에 추가
			}

			pstmt.close();

			String setFlag = "update chatting set  read_flag = 'Y'   where read_flag ='N'and deal_code = ? and reciever =?";
			pstmt = con.prepareStatement(setFlag);
			pstmt.setString(1, dealCode);
			pstmt.setString(2, me);
			System.out.println(pstmt.executeUpdate() + "플래그 사이즈");
			System.out.println(list.size() + "리스트 사이즈");
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

	public List<ChatVO> selectChat(String me, String you, String dealCode) throws SQLException {
		List<ChatVO> list = new ArrayList<ChatVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 2.커넥션 얻기
			con = getConn();

			// 3. 쿼리문 생성객체 얻기 : lunch테이블에서 이름, 코드, 가격, 입력일을 가장최근에 입력된
			// 것부터 조회
			String selectLunch = " select chat,sender,input_date from chatting "
					+ "where read_flag ='N' and deal_code = ? and reciever =? order by input_date asc";
			pstmt = con.prepareStatement(selectLunch);
			pstmt.setString(1, dealCode);
			pstmt.setString(2, me);
			rs = pstmt.executeQuery();

			ChatVO CV = null;
			while (rs.next()) {
				CV = new ChatVO(rs.getString("chat"), rs.getString("sender"), rs.getDate("input_date"));
				list.add(CV);// 조회된 레코드를 저장한 VO를 list에 추가
			}

			pstmt.close();

			String setFlag = "update chatting set  read_flag = 'Y'   where read_flag ='N'and deal_code = ? and reciever =? ";
			pstmt = con.prepareStatement(setFlag);
			pstmt.setString(1, dealCode);
			pstmt.setString(2, me);
			System.out.println(pstmt.executeUpdate() + "플래그 사이즈");
			System.out.println(list.size() + "리스트 사이즈");
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

	public void sendChat(String me, String you, String chat, String dealCode) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 2.
			con = getConn();
			// 3.
			String sendChat = "";
			sendChat = "insert into chatting(sender,reciever,chat,deal_code) values(?,?,?,?)";

			pstmt = con.prepareStatement(sendChat);
			// 4.
			pstmt.setString(1, me);
			pstmt.setString(2, you);
			pstmt.setString(3, chat);
			pstmt.setString(4, dealCode);

			// 5.
			pstmt.executeUpdate();

		} finally {
			// 6.
			if (pstmt != null)
				pstmt.close();// end if
			if (con != null)
				con.close();// end if

		} // end finally
	}// sendChat
	///////////////////////////// 채팅리스트
	///////////////////////////// 메서드!!!!!!!!!!!!///////////////////////////////////

	public List<ChatListVO> setChatList(String flag) throws SQLException {
		List<ChatListVO> list = new ArrayList<ChatListVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String dealCode = "";
		try {
			// 2.커넥션 얻기
			con = getConn();

			// 3. 쿼리문 생성객체 얻기 : lunch테이블에서 이름, 코드, 가격, 입력일을 가장최근에 입력된
			// 것부터 조회
			String chatList = "   select p.product_name product_name ,p.user_id id ,l.loc loc,deal_code from product p, deal d,id_info i,location_list l   "
					+ " where  d.user_id=? and p.product_code = d.product_code and p.user_id = i.user_id and l.loc_code=i.loc_code   ";
			if (flag.equals("sell")) {
				chatList = "   select  p.product_name ,d.user_id id,l.loc loc ,deal_code from  product p, deal d,id_info i,location_list l "
						+ "   where  p.user_id =? and p.product_code = d.product_code and p.user_id = i.user_id and l.loc_code=i.loc_code   ";
			}

			pstmt = con.prepareStatement(chatList);
			pstmt.setString(1, RunMarketMain.userId);

			rs = pstmt.executeQuery();

			ChatListVO clVO = null;
			RecentChatVO rcVO = null;
			while (rs.next()) {
				dealCode = rs.getString("deal_code");
				rcVO = recentChat(dealCode);
				clVO = new ChatListVO(rs.getString("product_name"), rs.getString("id"), rs.getString("loc"),
						rcVO.getTime(), rcVO.getChat(), dealCode);
				list.add(clVO);// 조회된 레코드를 저장한 VO를 list에 추가
			}

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

	}// setChatList

	public RecentChatVO recentChat(String dealCode) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		RecentChatVO rcVO = null;

		try {
			// 2.커넥션 얻기
			con = getConn();

			// 3. 쿼리문 생성객체 얻기 : lunch테이블에서 이름, 코드, 가격, 입력일을 가장최근에 입력된
			// 것부터 조회
			String RecentChat = "   select to_char(input_date,'yyyy-mm-dd hh24:mi:ss') input_date ,chat from  chatting where  (select max(input_date) from chatting where deal_code =?) = input_date   ";

			pstmt = con.prepareStatement(RecentChat);
			pstmt.setString(1, dealCode);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				rcVO = new RecentChatVO(rs.getString("input_date"), rs.getString("chat"));
			} else {
				rcVO = new RecentChatVO("", "");
			}

		} finally {
			// 6. 연결 끊기
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		}
		return rcVO;

	}// recentChat

	////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////// 채팅  시작시에 상품의  플래그 확인 메서드!!!!!!!!!!!//////////////////////////////
	public FlagVO checkFlag(String dealCode) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		FlagVO fVO = null;

		try {
			// 2.커넥션 얻기
			con = getConn();

			// 3. 쿼리문 생성객체 얻기 : lunch테이블에서 이름, 코드, 가격, 입력일을 가장최근에 입력된
			// 것부터 조회
			String checkFlag = "   select   sale_flag, all_flag from deal d,product p where  deal_code = ? and d.product_code = p.product_code   ";

			pstmt = con.prepareStatement(checkFlag);
			pstmt.setString(1, dealCode);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				fVO = new FlagVO(rs.getString("sale_flag"), rs.getString("all_flag"));
			}

		} finally {
			// 6. 연결 끊기
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		}
		return fVO;

	}

/////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
// 로그인 시 이름 출력!!
public String[] loginRun(LoginVO lVO) throws SQLException {
String[] loginInfo = new String[2];
Connection con = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
try {
// 연결
con = getConn();
// 쿼리문 생성
StringBuilder selectLogin = new StringBuilder();
selectLogin.append("select user_name , suspend_flag ").append(" from id_info ").append(" where user_id=? ")
.append(" and password= ?");

pstmt = con.prepareStatement(selectLogin.toString());
// 바인드 값 변수 얻기
pstmt.setString(1, lVO.getId());
pstmt.setString(2, lVO.getPass());
// 쿼리 실행
rs = pstmt.executeQuery();
if (rs.next()) {
loginInfo[0] = rs.getString("user_name");
loginInfo[1] = rs.getString("suspend_flag");
} // end while

} finally {
if (con != null) {
con.close();
} // end if
if (pstmt != null) {
pstmt.close();
} // end if
if (rs != null) {
rs.close();
} // end if
} // end finally
return loginInfo;
}// loginRun
//중복된 아이디 체크 

public String idCheck(String id) throws SQLException {
Connection con = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
String checkId = "";
try {
con = getConn();
StringBuilder selectID = new StringBuilder();
selectID.append(" select user_id ").append(" from id_info ").append("where user_id =?");

pstmt = con.prepareStatement(selectID.toString());

pstmt.setString(1, id);
rs = pstmt.executeQuery();
while (rs.next()) {
checkId = rs.getString(1);
} // end while
} finally {
if (con != null) {
con.close();
} // end if
if (pstmt != null) {
pstmt.close();
} // end if
if (rs != null) {
rs.close();
} // end if
} // end finally

return checkId;
}// IdCheck

//회원가입
public int insertLogin(SignUpVO suVO) throws SQLException {
int insertflag = 0;

Connection con = null;
PreparedStatement pstmt = null;
try {
con = getConn();
StringBuilder insertResigter = new StringBuilder();
insertResigter.append(
"insert into id_info(user_id,password, user_name, gender, phone, answer,  loc_code, hint_code)values(?,?,?,?,?,?,?,?)");

pstmt = con.prepareStatement(insertResigter.toString());
pstmt.setString(1, suVO.getId());
pstmt.setString(2, suVO.getPw());
pstmt.setString(3, suVO.getName());
pstmt.setString(4, suVO.getGender());
pstmt.setString(5, suVO.getPhone());
pstmt.setString(6, suVO.getPwAnswer());
pstmt.setString(7, suVO.getLoc());
pstmt.setString(8, suVO.getPwHint());

insertflag = pstmt.executeUpdate();

} finally {
if (con != null) {
con.close();
} // end if
if (pstmt != null) {
pstmt.close();
} // end if
} // end finally

return insertflag;

}// updateRegister

//분실 아이디찾기
public String selectIdCheck(ForgotIdVO fiVO) throws SQLException {
Connection con = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
String id = "";
try {
con = getConn();
StringBuilder forgotId = new StringBuilder();
forgotId.append("select user_id from id_info where user_name=? and phone=?");

pstmt = con.prepareStatement(forgotId.toString());

pstmt.setString(1, fiVO.getName());
pstmt.setString(2, fiVO.getPhone());

rs = pstmt.executeQuery();

while (rs.next()) {
id = rs.getString(1);
}

} finally {
if (con != null) {
con.close();
} // end if
if (pstmt != null) {
pstmt.close();
} // end if
if (rs != null) {
rs.close();
} // end if

}
return id;
}// selectId

public boolean updateForgotPw(ForgotPwVO fpVO, String uuid) throws SQLException {
boolean updateFlag = false;
Connection con = null;
PreparedStatement pstmt = null;
try {
con = getConn();
StringBuilder updatePw = new StringBuilder();
updatePw.append("Update id_info set password='").append(uuid)
.append("' where user_id =? and answer=? and hint_code=?");

pstmt = con.prepareStatement(updatePw.toString());

pstmt.setString(1, fpVO.getId());
pstmt.setString(2, fpVO.getPwAnswer());
pstmt.setString(3, fpVO.getPwHint());

updateFlag = pstmt.executeUpdate() == 1;

} finally {
if (con != null) {
con.close();
} // end if
if (pstmt != null) {
pstmt.close();
} // end if
} // end finally

return updateFlag;
}// updateForgetPw
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public PersonalInformVO selectPersonalInfom(String id) throws SQLException {
Connection con = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
PersonalInformVO piVO = null;
try {
con = getConn();
StringBuilder selectAllInfo = new StringBuilder();
selectAllInfo.append(
"select user_id,password,user_name,gender,phone,answer,loc_code,hint_code from id_info where user_id=?");
pstmt = con.prepareStatement(selectAllInfo.toString());

pstmt.setString(1, id);
rs = pstmt.executeQuery();

while (rs.next()) {
piVO = new PersonalInformVO(rs.getString("user_id"), rs.getString("password"),
rs.getString("user_name"), rs.getString("gender"), rs.getString("phone"),
rs.getString("answer"), rs.getString("loc_code"), rs.getString("hint_code"));
} // end while
} finally {
if (con != null) {
con.close();
} // end if
if (pstmt != null) {
pstmt.close();
} // end if
if (rs != null) {
rs.close();
} // end if
} // end finally
return piVO;
}// selectPersonalInfom

public String selectPw(String pw) throws SQLException {
String curPw = "";
Connection con = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
try {
con = getConn();
StringBuilder selectPw = new StringBuilder();
selectPw.append("select password from id_info where password=?");

pstmt = con.prepareStatement(selectPw.toString());
pstmt.setString(1, pw);
rs = pstmt.executeQuery();
while (rs.next()) {
curPw = rs.getString(1);
} // end while
} finally {
if (con != null) {
con.close();
} // end if
if (pstmt != null) {
pstmt.close();
} // end if
if (rs != null) {
rs.close();
} // end if
} // end finally
return curPw;
}// selectPw

public boolean updatePw(String id, String pw) throws SQLException {
boolean updateFlag = false;

Connection con = null;
PreparedStatement pstmt = null;
try {
con = getConn();
StringBuilder updatePw = new StringBuilder();
updatePw.append("update id_info ").append(" set password =? ").append(" where user_id =? ");
pstmt = con.prepareStatement(updatePw.toString());
pstmt.setString(1, pw);
pstmt.setString(2, id);

updateFlag = pstmt.executeUpdate() == 1;

} finally {
if (con != null) {
con.close();
} // end if
if (pstmt != null) {
pstmt.close();
} // end if
} // end finally
return updateFlag;
}// updatePw

public boolean updateThing(modifyInformVO miVO) throws SQLException {
Connection con = null;
PreparedStatement pstmt = null;
boolean flag = false;
try {
con = getConn();
StringBuilder udThing = new StringBuilder();
udThing.append(" update id_info ").append("set ").append(miVO.getThing()).append("=? where user_id=?");

pstmt = con.prepareStatement(udThing.toString());
pstmt.setString(1, miVO.getValue());
pstmt.setString(2, miVO.getId());

flag = pstmt.executeUpdate() == 1;

} finally {
if (con != null) {
con.close();
} // end if
if (pstmt != null) {
pstmt.close();
} // end if

} // end finally

return flag;
}// selectThing
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//관심목록 추가 

public int insertInterest(InterestListVO irVO,boolean checkFlag) throws SQLException {
Connection con = null;
PreparedStatement pstmt = null;
int flag = 0;
try {
con=getConn();
if(checkFlag) {
StringBuilder insertInterest = new StringBuilder();
insertInterest
.append("insert into INTERESTED_PRODUCT(product_code , user_id)values")
.append("((select product_code from product where product_code=?),?)");
pstmt= con.prepareStatement(insertInterest.toString());
System.out.println(irVO.getProduct_code()+" "+irVO.getUser_id());
pstmt.setString(1,irVO.getProduct_code());
pstmt.setString(2,irVO.getUser_id());
flag = pstmt.executeUpdate();
pstmt.close();
}else if(!checkFlag) {
StringBuilder deletInterest = new StringBuilder();
deletInterest
.append("delete from INTERESTED_PRODUCT where product_code=?");
pstmt=con.prepareStatement(deletInterest.toString());
pstmt.setString(1,irVO.getProduct_code());
flag = pstmt.executeUpdate();
}//end if
} finally{
if(con!=null) {con.close();}//end if
if(pstmt!=null) {pstmt.close();}//end if
}//end finally



return flag;
}//insertInterest
public String selectInterestCheck(InterestListVO irVO) throws SQLException {
Connection con =null;
PreparedStatement pstmt = null;
ResultSet rs= null;
String user_id = "";
try{
con = getConn();
String selectCheck= "select user_id from INTERESTED_PRODUCT where product_code=? and user_id=?";
pstmt = con.prepareStatement(selectCheck);

pstmt.setString(1, irVO.getProduct_code());
pstmt.setString(2, irVO.getUser_id());
rs = pstmt.executeQuery();
while(rs.next()) {
user_id = rs.getString(1);
}
}finally {
if(con!=null) {con.close();}//end if
if(pstmt!=null) {pstmt.close();}//end if
if(rs!=null) {rs.close();}//end if

}//end finally


return user_id;
}//selectInterestCheck
//관심목록 보이기
public List<AllListVO> selectInterestList() throws SQLException{
List<AllListVO> list = new ArrayList<AllListVO>();
Connection con = null;
PreparedStatement pstmt = null;
ResultSet rs=null;
try {
con=getConn();
StringBuilder selectinterest = new StringBuilder();
selectinterest
.append(" select p.PRODUCT_CODE, p.IMG_FILE, p.PRODUCT_NAME, p.PRICE, P.USER_ID, i.loc_code,l.loc, to_char(p.UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,c.category ")
.append(" from PRODUCT p, id_info i, location_list l,category_list c,interested_product ip ")
.append(" where ( p.user_id= i.user_id and i.loc_code=l.loc_code and c.category_code=p.category_code and p.product_code=ip.product_code) ")
.append(" and ip.user_id=?");

pstmt = con.prepareStatement(selectinterest.toString());
pstmt.setString(1, RunMarketMain.userId);
rs = pstmt.executeQuery();
while(rs.next()) {
AllListVO aVO= new AllListVO(rs.getString("PRODUCT_CODE"),rs.getString("IMG_FILE"), 
rs.getString("PRODUCT_NAME"), rs.getString("loc"), rs.getString("inputDate"),
rs.getString("category"), rs.getString("USER_ID"), rs.getInt("PRICE"));
list.add(aVO);
}//end while

}finally {
if(con!=null) {con.close();}//end if
if(pstmt!=null) {pstmt.close();}//end if
if(rs!=null) {rs.close();}//end if
}//end finally

return list;
}//selectInterestList

///////////////////////////////////////////////////////////////////////여기까지 김서영 2019.9.19 수정//////////////////////////////////////////////////////////////   

//	public static void main(String[] args) {
//
//	}//main

}// class
