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
import userVO.DealListVO;
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
import userVO.searchValueVO;

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

		// 1. ����̹� �ε�
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("ddddd");
			e.printStackTrace();
		} // end catch

		// 2. Connection ���

//		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String url = "jdbc:oracle:thin:@211.63.89.159:1521:orcl";
		String id = "junggo";
		String pass = "1234";

		con = DriverManager.getConnection(url, id, pass);

		return con;
	}// getConn

	public List<AllListVO> setList(searchValueVO svVO) throws SQLException {
		List<AllListVO> list = new ArrayList<AllListVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DecimalFormat df = new DecimalFormat("00");

		String area = "";
		String category = "";
		String textValue = "";
		String rb = "p.product_name";
		String sort = "";

		if (svVO.getAreaIndex() != 0) {
			area = "	and l.loc_code ='" + df.format(svVO.getAreaIndex()) + "'	";
		}

		if (svVO.getCategoryIndex() != 0) {
			category = "	and p.category_code='" + df.format(svVO.getCategoryIndex()) + "'	";
		}

		if (!svVO.getTextValue().isEmpty()) {
			if (svVO.getRbFlag().equals("ID")) {
				rb = "i.user_id";
			}
			textValue = "	and lower(" + rb + ") like '%" + svVO.getTextValue().toLowerCase() + "%'	";
		}

		switch (svVO.getSortFlag()) {
		case "UP":
			sort = "	order by price desc	";
			break;
		case "DP":
			sort = "	order by price asc	";
			break;
		case "UD":
			sort = "	order by inputdate desc	";
			break;
		case "DD":
			sort = "	order by inputdate asc	";
			break;

		}

		try {
			// Ŀ�ؼ� ���
			con = getConn();

			// 3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ

			String query = "	select p.PRODUCT_CODE, p.IMG_FILE, p.PRODUCT_NAME, to_char(p.UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, p.CATEGORY_CODE, P.USER_ID, p.PRICE, i.loc_code,l.loc, c.category "
					+ "		from PRODUCT p, id_info i, location_list l, category_list c  "
					+ "		where p.user_id= i.user_id and i.loc_code=l.loc_code and p.category_code=c.category_code and all_flag ='P' ";
			query += area + category + textValue + sort;
			System.out.println("���� ����:" + query);
			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();
			AllListVO alv = null;

			while (rs.next()) {
				alv = new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc"), rs.getString("inputDate"),
						rs.getString("CATEGORY"), rs.getString("USER_ID"), rs.getInt("PRICE"));

				list.add(alv);
			} // end while
		} finally {
			// 6. �������
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
	}// setList

//	public List<AllListVO> selectRefresh() throws SQLException {
//		List<AllListVO> list = new ArrayList<AllListVO>();
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			// Ŀ�ؼ� ���
//			con = getConn();
//
//			// 3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
//			StringBuilder selectAll = new StringBuilder();
//			selectAll
//					.append(	" select p.PRODUCT_CODE, p.IMG_FILE, p.PRODUCT_NAME, to_char(p.UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, p.CATEGORY_CODE,"
//							+ " P.USER_ID, p.PRICE, i.loc_code,l.loc, c.category ")
//					.append(" from PRODUCT p, id_info i, location_list l, category_list c ")
//					.append(" where ( p.user_id= i.user_id and i.loc_code=l.loc_code and p.category_code=c.category_code) and all_flag ='P' ");
//
//			
////        .append(" order by inputDate desc ");
//
//			pstmt = con.prepareStatement(selectAll.toString());
//
//			// 4. ���ε庯���� �� �ֱ�
//			// 5. ���� ���� �� ��� ���
//			rs = pstmt.executeQuery();
//			AllListVO alv = null;
//
//			while (rs.next()) {
//				alv = new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
//						rs.getString("PRODUCT_NAME"), rs.getString("loc"), rs.getString("inputDate"),
//						rs.getString("CATEGORY"), rs.getString("USER_ID"), rs.getInt("PRICE"));
//
//				list.add(alv);
//			} // end while
//		} finally {
//			// 6. �������
//			if (rs != null) {
//				rs.close();
//			} // end if
//			if (pstmt != null) {
//				pstmt.close();
//			} // end if
//			if (con != null) {
//				con.close();
//			} // end if
//
//		} // end finally
//		return list;
//
//	}// selectRefresh
//
//	public List<AllListVO> selectAllList(int jcbAreaIndex, int jcbCateIndex, String jtfText) throws SQLException {
//		List<AllListVO> list = new ArrayList<AllListVO>();
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		String area = "";
//		String category = "";
//		String productName = "";
//
//		try {
//			// Ŀ�ؼ� ���
//			con = getConn();
//
//			// 3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
//			StringBuilder selectArea = new StringBuilder();
//			selectArea.append(
//					" select p.PRODUCT_CODE, p.IMG_FILE, p.PRODUCT_NAME, to_char(p.UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, p.CATEGORY_CODE,"
//							+ " P.USER_ID, p.PRICE, i.loc_code,l.loc, c.category ")
//					.append(" from PRODUCT p, id_info i, location_list l, category_list c ")
//					.append(" where ( p.user_id= i.user_id and i.loc_code=l.loc_code and p.category_code=c.category_code) and all_flag ='P' ");
//
//			if (jcbAreaIndex != 0) {
////				selectArea.append( " and user_id in ( select user_id  from id_info where loc_code ='"+df.format(jcbAreaIndex)+"') ");
//				selectArea.append(" and l.loc_code =" + df.format(jcbAreaIndex) + " ");
//			}
//			if (jcbCateIndex != 0) {
//				selectArea.append(" and p.category_code ='" + df.format(jcbCateIndex) + "' ");
//			}
//			if (!jtfText.isEmpty()) {
//				selectArea.append(" and lower(p.product_name) like '%" +  jtfText.toLowerCase()  + "%' ");
//			}
//
////			selectArea.append(" order by inputDate desc "); 
//
////			select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, USER_ID, PRICE
////			from PRODUCT
////			where all_flag ='P'  and user_id in ( select user_id  from id_info where loc_code ='07') and category_code ='04'  and product_name like '%����%'
////			order by inputDate desc ;
//
//			/*
//			 * select user_id,product_name from product where ( user_id in ( select user_id
//			 * from id_info where loc_code ='12')) and all_flag ='P'; �̰���
//			 */
//
//			pstmt = con.prepareStatement(selectArea.toString());
//
//			// 4. ���ε庯���� �� �ֱ�
//			// 5. ���� ���� �� ��� ���
//			rs = pstmt.executeQuery();
//			AllListVO alv = null;
//
//			while (rs.next()) {
//				alv = new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
//						rs.getString("PRODUCT_NAME"), rs.getString("loc"), rs.getString("inputDate"),
//						rs.getString("CATEGORY"), rs.getString("USER_ID"), rs.getInt("PRICE"));
//				list.add(alv);
//			} // end while
//		} finally {
//			// 6. �������
//			if (rs != null) {
//				rs.close();
//			} // end if
//			if (pstmt != null) {
//				pstmt.close();
//			} // end if
//			if (con != null) {
//				con.close();
//			} // end if
//
//		} // end finally
//		return list;
//
//	}// selectAreaList
//
//	public List<AllListVO> selectListByID(int jcbAreaIndex, int jcbCateIndex, String jtfText) throws SQLException {
//
//		List<AllListVO> list = new ArrayList<AllListVO>();
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//
//		try {
//			// Ŀ�ؼ� ���
//			con = getConn();
//
//			// 3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
//			StringBuilder selectArea = new StringBuilder();
//			selectArea.append(
//					" select p.PRODUCT_CODE, p.IMG_FILE, p.PRODUCT_NAME, to_char(p.UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, p.CATEGORY_CODE,"
//					+ " P.USER_ID, p.PRICE, i.loc_code,l.loc, c.category ")
//				.append(" from PRODUCT p, id_info i, location_list l, category_list c ")
//				.append(" where ( p.user_id= i.user_id and i.loc_code=l.loc_code and p.category_code=c.category_code) and all_flag ='P' ");
//
//			
//			
//
//			if (jcbAreaIndex != 0) {
////				selectArea.append( " and user_id in ( select user_id  from id_info where loc_code ='"+df.format(jcbAreaIndex)+"') ");
//				selectArea.append(" and l.loc_code =" + df.format(jcbAreaIndex) + " ");
//			}
//			if (jcbCateIndex != 0) {
//				selectArea.append(" and p.category_code ='" + df.format(jcbCateIndex) + "' ");
//			}
//			if (!jtfText.isEmpty()) {
//				selectArea.append(" and lower(i.user_id) like '%" + jtfText.toLowerCase() + "%' ");
//			}
//
//			pstmt = con.prepareStatement(selectArea.toString());
//
//			// 4. ���ε庯���� �� �ֱ�
//			// 5. ���� ���� �� ��� ���
//			rs = pstmt.executeQuery();
//			AllListVO alv = null;
//
//			while (rs.next()) {
//				alv = new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
//						rs.getString("PRODUCT_NAME"), rs.getString("loc"), rs.getString("inputDate"),
//						rs.getString("CATEGORY"), rs.getString("user_id"), rs.getInt("PRICE"));
//				list.add(alv);
//			} // end while
//		} finally {
//			// 6. �������
//			if (rs != null) {
//				rs.close();
//			} // end if
//			if (pstmt != null) {
//				pstmt.close();
//			} // end if
//			if (con != null) {
//				con.close();
//			} // end if
//
//		} // end finally
//		return list;
//
//	}// selectListByID
//
//	public List<AllListVO> selectListRecent(int jcbAreaIndex, int jcbCateIndex, String jtfText) throws SQLException {
//		List<AllListVO> list = new ArrayList<AllListVO>();
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		String area = "";
//		String category = "";
//		String productName = "";
//
//		try {
//			// Ŀ�ؼ� ���
//			con = getConn();
//
//			// 3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
//			StringBuilder selectArea = new StringBuilder();
//			selectArea.append(
//					" select p.PRODUCT_CODE, p.IMG_FILE, p.PRODUCT_NAME, to_char(p.UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, p.CATEGORY_CODE,"
//					+ " P.USER_ID, p.PRICE, i.loc_code,l.loc, c.category ")
//			.append(" from PRODUCT p, id_info i, location_list l, category_list c ")
//			.append(" where ( p.user_id= i.user_id and i.loc_code=l.loc_code and p.category_code=c.category_code) and all_flag ='P' ");
//
//
//
//			if (jcbAreaIndex != 0) {
////				selectArea.append( " and user_id in ( select user_id  from id_info where loc_code ='"+df.format(jcbAreaIndex)+"') ");
//				selectArea.append(" and i.loc_code =" + df.format(jcbAreaIndex) + " ");
//			}
//			if (jcbCateIndex != 0) {
//				selectArea.append(" and p.category_code ='" + df.format(jcbCateIndex) + "' ");
//			}
//			if (!jtfText.isEmpty()) {
//				selectArea.append(" and lower(p.product_name) like '%" + jtfText.toLowerCase() + "%' ");
//			}
//
//			selectArea.append(" order by inputDate desc ");
//
////			select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, USER_ID, PRICE
////			from PRODUCT
////			where all_flag ='P'  and user_id in ( select user_id  from id_info where loc_code ='07') and category_code ='04'  and product_name like '%����%'
////			order by inputDate desc ;
//
//			/*
//			 * select user_id,product_name from product where ( user_id in ( select user_id
//			 * from id_info where loc_code ='12')) and all_flag ='P'; �̰���
//			 */
//
//			pstmt = con.prepareStatement(selectArea.toString());
//
//			// 4. ���ε庯���� �� �ֱ�
//			// 5. ���� ���� �� ��� ���
//			rs = pstmt.executeQuery();
//			AllListVO alv = null;
//
//			while (rs.next()) {
//				alv = new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
//						rs.getString("PRODUCT_NAME"), rs.getString("loc"), rs.getString("inputDate"),
//						rs.getString("CATEGORY"), rs.getString("USER_ID"), rs.getInt("PRICE"));
//				list.add(alv);
//			} // end while
//		} finally {
//			// 6. �������
//			if (rs != null) {
//				rs.close();
//			} // end if
//			if (pstmt != null) {
//				pstmt.close();
//			} // end if
//			if (con != null) {
//				con.close();
//			} // end if
//
//		} // end finally
//		return list;
//
//	}// selectListRecent
//
//	public List<AllListVO> selectListPrice(int jcbAreaIndex, int jcbCateIndex, String jtfText) throws SQLException {
//		List<AllListVO> list = new ArrayList<AllListVO>();
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		String area = "";
//		String category = "";
//		String productName = "";
//
//		try {
//			// Ŀ�ؼ� ���
//			con = getConn();
//
//			// 3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
//			StringBuilder selectArea = new StringBuilder();
//			selectArea.append(
//					" select p.PRODUCT_CODE, p.IMG_FILE, p.PRODUCT_NAME, to_char(p.UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, p.CATEGORY_CODE,"
//					+ " P.USER_ID, p.PRICE, i.loc_code,l.loc, c.category ")
//			.append(" from PRODUCT p, id_info i, location_list l, category_list c ")
//			.append(" where ( p.user_id= i.user_id and i.loc_code=l.loc_code and p.category_code=c.category_code) and all_flag ='P' ");
//			
//			
//
//			if (jcbAreaIndex != 0) {
////				selectArea.append( " and user_id in ( select user_id  from id_info where loc_code ='"+df.format(jcbAreaIndex)+"') ");
//				selectArea.append(" and i.loc_code =" + df.format(jcbAreaIndex) + " ");
//			}
//			if (jcbCateIndex != 0) {
//				selectArea.append(" and p.category_code ='" + df.format(jcbCateIndex) + "' ");
//			}
//			if (!jtfText.isEmpty()) {
//				selectArea.append(" and lower(p.product_name) like '%" + jtfText.toLowerCase() + "%' ");
//			}
//
//			selectArea.append(" order by price desc ");
//
////			select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, USER_ID, PRICE
////			from PRODUCT
////			where all_flag ='P'  and user_id in ( select user_id  from id_info where loc_code ='07') and category_code ='04'  and product_name like '%����%'
////			order by inputDate desc ;
//
//			/*
//			 * select user_id,product_name from product where ( user_id in ( select user_id
//			 * from id_info where loc_code ='12')) and all_flag ='P'; �̰���
//			 */
//
//			pstmt = con.prepareStatement(selectArea.toString());
//
//			// 4. ���ε庯���� �� �ֱ�
//			// 5. ���� ���� �� ��� ���
//			rs = pstmt.executeQuery();
//			AllListVO alv = null;
//
//			while (rs.next()) {
//				alv = new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
//						rs.getString("PRODUCT_NAME"), rs.getString("loc"), rs.getString("inputDate"),
//						rs.getString("CATEGORY"), rs.getString("USER_ID"), rs.getInt("PRICE"));
//				list.add(alv);
//			} // end while
//		} finally {
//			// 6. �������
//			if (rs != null) {
//				rs.close();
//			} // end if
//			if (pstmt != null) {
//				pstmt.close();
//			} // end if
//			if (con != null) {
//				con.close();
//			} // end if
//
//		} // end finally
//		return list;
//
//	}// selectListRecent

	/**
	 * ��ǰ ������â���� ���� �ѱ�� method
	 * 
	 * @param productCode
	 * @param loc_code
	 * @return
	 * @throws SQLException
	 */
	public MarketDetailVO selectProDetail(String productCode, String classFlag) throws SQLException {

		MarketDetailVO mdVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// Ŀ�ؼ� ���
			con = getConn();

			// 3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
			StringBuilder selectDetail = new StringBuilder();

			// Flag ����
			// I- InterestListEvt
			// M- MainMarketEvt
			// S- SaleListEvt���� �Ǹ����� ���
			// C- SaleListEvt���� �ǸſϷ�� ���
			if (classFlag == "I" || classFlag == "M" ) {
				System.out.println("1���̴�.");
				System.out.println(productCode);
				selectDetail.append(
						" select p.PRODUCT_CODE, p.IMG_FILE, p.PRODUCT_NAME, to_char(p.UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, p.CATEGORY_CODE,"
								+ " P.USER_ID, p.PRICE, p.info, i.loc_code, l.loc, c.category ")
						.append(" from PRODUCT p, id_info i, location_list l, category_list c ")
						.append(" where ( p.user_id= i.user_id and i.loc_code=l.loc_code and p.category_code=c.category_code) and all_flag ='P' and p.PRODUCT_CODE=? ");// ����ǥ��
																																										// ''��
																																										// ���̾���
																																										// �ȵ�
			} else if (classFlag == "C") {
				System.out.println(productCode);
				System.out.println("2���̴�.");
				selectDetail.append(					
						" select p.PRODUCT_CODE, p.IMG_FILE, p.PRODUCT_NAME, to_char(d.SALE_DATE,'yyyy-mm-dd hh24:mi') inputDate, p.CATEGORY_CODE,\r\n" + 
						" p.USER_ID, p.PRICE, p.info, i.loc_code, l.loc, c.category, d.sale_flag ")
						.append(" from PRODUCT p, id_info i, location_list l, category_list c, deal d ")
						.append(" p.user_id= i.user_id and i.loc_code=l.loc_code and p.category_code=c.category_code and d.product_code=p.product_code ) and sale_flag ='P' and p.PRODUCT_CODE=? ");
			} else if( classFlag == "S") {
				selectDetail.append(
						" select p.PRODUCT_CODE, p.IMG_FILE, p.PRODUCT_NAME, to_char(p.UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, p.CATEGORY_CODE,"
								+ " P.USER_ID, p.PRICE, p.info, i.loc_code, l.loc, c.category ")
						.append(" from PRODUCT p, id_info i, location_list l, category_list c ")
						.append(" where ( p.user_id= i.user_id and i.loc_code=l.loc_code and p.category_code=c.category_code) and (all_flag ='P' or all_flag='N') and p.PRODUCT_CODE=? ");
			}

			pstmt = con.prepareStatement(selectDetail.toString());

			// 4. ���ε庯���� �� �ֱ�
			pstmt.setString(1, productCode);
			// 5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();

			if (rs.next()) {
				mdVO = new MarketDetailVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc"), rs.getString("inputDate"),
						rs.getString("category"), rs.getString("USER_ID"), rs.getString("INFO"), rs.getInt("PRICE"));
			} // end if

		} finally {
			// 6. �������
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

///////////////////////////////////// ��ǰ �Է�â //////////////////////////////////////////////

	public void insertProduct(InsertProductVO ipVO, String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConn();

			System.out.println(id + "�ٺ�");
			StringBuilder insertProduct = new StringBuilder();
			insertProduct
					.append("insert into product (product_code, img_file, category_code, product_name, info, price,"
							+ "user_id) values(pro_code,?,?,?,?,?,?)");

			// insert into product (product_code,img_file, category_code, product_name,
			// info, price,
			// user_id) values(pro_code,'�ַ��.jpg','07','�Ϳ�Ϳ� �ַ��','�ַ�� �����',999999,
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
	 * �������������� ������ �Ǹ����� ��ǰ ����Ʈ �����ֱ�
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
//Ŀ�ؼ� ���
			con = getConn();

//3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
			StringBuilder selectAll = new StringBuilder();

			if (temp_flag.equals("S")) { // ����������-�Ǹų���-�Ǹ���
				selectAll.append(
						" select p.PRODUCT_CODE, p.IMG_FILE, p.PRODUCT_NAME, to_char(p.UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, p.CATEGORY_CODE,"
								+ " P.USER_ID, p.PRICE, i.loc_code,l.loc, p.all_flag, c.category, p.info ")
						.append(" from PRODUCT p, id_info i, location_list l, category_list c ")
						.append(" where ( p.user_id= i.user_id and i.loc_code=l.loc_code and p.category_code=c.category_code) and (all_flag ='P'or all_flag ='N') and p.USER_ID=? ");
			} else if (temp_flag.equals("P")) { // ����������-���ų���
				selectAll.append(
						" select p.PRODUCT_CODE, p.IMG_FILE, p.PRODUCT_NAME, to_char(p.UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, p.CATEGORY_CODE,"
								+ "p.PRICE, i.loc_code,l.loc,c.category, p.USER_ID, d.SALE_FLAG, to_char(d.SALE_DATE,'yyyy-mm-dd hh24:mi') sale_date, p.info ")
						.append(" from PRODUCT p, id_info i, location_list l, category_list c , DEAL d  ")
						.append(" where ( d.user_id= i.user_id and i.loc_code=l.loc_code and p.category_code=c.category_code and p.PRODUCT_CODE= d.PRODUCT_CODE) and d.sale_flag ='P' and d.USER_ID=?");

			} // end else

			pstmt = con.prepareStatement(selectAll.toString());

//4. ���ε庯���� �� �ֱ�
			pstmt.setString(1, id);
//5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			SaleListVO slv = null;

			while (rs.next()) {
				slv = new SaleListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc"), temp_flag.equals("P") ?rs.getString("sale_date"):rs.getString("inputDate"),
						rs.getString("CATEGORY"), rs.getString("USER_ID"), temp_flag.equals("P") ? rs.getString("sale_flag"):rs.getString("all_flag"),
						rs.getString("INFO"), rs.getInt("PRICE"));
System.out.println(slv);
				list.add(slv);
			} // end while
		} finally {
//6. �������
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
	 * ���������� �Ǹų���-�ǸſϷ����� ������ �� ó��
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
//Ŀ�ؼ� ���
			con = getConn();

//3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
			StringBuilder selectAll = new StringBuilder();
			selectAll.append(
					" select p.PRODUCT_CODE, p.IMG_FILE, p.PRODUCT_NAME, to_char(p.UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, p.CATEGORY_CODE,"
							+ " P.USER_ID, p.PRICE, i.loc_code,l.loc, c.category, p.all_flag, p.info ")
					.append(" from PRODUCT p, id_info i, location_list l, category_list c ")
					.append(" where ( p.user_id= i.user_id and i.loc_code=l.loc_code and p.category_code=c.category_code) and p.all_flag ='B' and p.USER_ID=? ");

			pstmt = con.prepareStatement(selectAll.toString());

//4. ���ε庯���� �� �ֱ�
			pstmt.setString(1, id);
//5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			SaleListVO slv = null;

			while (rs.next()) {
				slv = new SaleListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc"), rs.getString("inputDate"),
						rs.getString("CATEGORY"), rs.getString("USER_ID"), rs.getString("all_flag"), rs.getString("info"),
						rs.getInt("PRICE"));

				list.add(slv);
			} // end while
		} finally {
//6. �������
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
	
	//////////////////////////////////////// ���� ���� - 20190924 ////////////////////////////////////////////////////////////
	
	
	public boolean updatePost(String productCode, int price, String pDetail) throws SQLException {
		
		boolean updateFlag = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConn();
			StringBuilder updatePost = new StringBuilder();
			updatePost.append("update product ").append(" set info =?, price=? ").append(" where product_code =? ");
			pstmt = con.prepareStatement(updatePost.toString());
			pstmt.setString(1, pDetail);
			pstmt.setInt(2, price);
			pstmt.setString(3, productCode);

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
		
	}//updatePost
	
	
	//////////////////////////////////////// ���� ���� �� - 20190924 ////////////////////////////////////////////////////////////

	public boolean deletePost(String product_code) throws SQLException {
		boolean deleteFlag = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try { 
			con = getConn();

			StringBuilder deletePost = new StringBuilder();
			deletePost.append("	update Product	")
					// .append(" set img='', strong_point='', delete_flag='Y' ") // not null �ɷ��־
					// ������
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

	////////////////////////////////////// ä�ð���
	////////////////////////////////////// �޼���!!!!///////////////////////////////////

	public int addDeal(String productCode, String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// Ŀ�ؼ� ���
			con = getConn();

			String check = "select deal_code from deal where product_code =? and user_id=?";

			pstmt = con.prepareStatement(check);

			// 4. ���ε庯���� �� �ֱ�
			pstmt.setString(1, productCode);
			pstmt.setString(2, id);
			// 5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("�ŷ����Դϴ�.");
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
			// 6. �������
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
			// Ŀ�ؼ� ���
			con = getConn();

			String check = "select deal_code, p.user_id seller_id from deal d,product p where p.product_code = ? and  d.user_id= ? and (d.product_code = p.product_code)";

			pstmt = con.prepareStatement(check);

			// 4. ���ε庯���� �� �ֱ�
			pstmt.setString(1, productCode);
			pstmt.setString(2, id);
			// 5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return new DCodeAndIdAO(rs.getString("deal_code"), rs.getString("seller_id"));
			} else {
				System.out.println("�ŷ��� �����ϴ�.");
				return new DCodeAndIdAO("", "");
			}

		} finally {
			// 6. �������
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
			// 2.Ŀ�ؼ� ���
			con = getConn();

			// 3. ������ ������ü ��� : lunch���̺��� �̸�, �ڵ�, ����, �Է����� �����ֱٿ� �Էµ�
			// �ͺ��� ��ȸ
			String selectLunch = " select chat,sender,input_date from chatting "
					+ "where deal_code = ? order by input_date asc";
			pstmt = con.prepareStatement(selectLunch);
			pstmt.setString(1, dealCode);
//	         pstmt.setString(2, me);
			rs = pstmt.executeQuery();

			ChatVO CV = null;
			while (rs.next()) {
				CV = new ChatVO(rs.getString("chat"), rs.getString("sender"), rs.getDate("input_date"));
				list.add(CV);// ��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			}

			pstmt.close();

			String setFlag = "update chatting set  read_flag = 'Y'   where read_flag ='N'and deal_code = ? and reciever =?";
			pstmt = con.prepareStatement(setFlag);
			pstmt.setString(1, dealCode);
			pstmt.setString(2, me);
			System.out.println(pstmt.executeUpdate() + "�÷��� ������");
			System.out.println(list.size() + "����Ʈ ������");
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

	public List<ChatVO> selectChat(String me, String you, String dealCode) throws SQLException {
		List<ChatVO> list = new ArrayList<ChatVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 2.Ŀ�ؼ� ���
			con = getConn();

			// 3. ������ ������ü ��� : lunch���̺��� �̸�, �ڵ�, ����, �Է����� �����ֱٿ� �Էµ�
			// �ͺ��� ��ȸ
			String selectLunch = " select chat,sender,input_date from chatting "
					+ "where read_flag ='N' and deal_code = ? and reciever =? order by input_date asc";
			pstmt = con.prepareStatement(selectLunch);
			pstmt.setString(1, dealCode);
			pstmt.setString(2, me);
			rs = pstmt.executeQuery();

			ChatVO CV = null;
			while (rs.next()) {
				CV = new ChatVO(rs.getString("chat"), rs.getString("sender"), rs.getDate("input_date"));
				list.add(CV);// ��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			}

			pstmt.close();

			String setFlag = "update chatting set  read_flag = 'Y'   where read_flag ='N'and deal_code = ? and reciever =? ";
			pstmt = con.prepareStatement(setFlag);
			pstmt.setString(1, dealCode);
			pstmt.setString(2, me);
			System.out.println(pstmt.executeUpdate() + "�÷��� ������");
			System.out.println(list.size() + "����Ʈ ������");
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
		///////////////////////////// ä�ø���Ʈ
		///////////////////////////// �޼���!!!!!!!!!!!!///////////////////////////////////

	public List<ChatListVO> setChatList(String flag) throws SQLException {
		List<ChatListVO> list = new ArrayList<ChatListVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String dealCode = "";
		try {
			// 2.Ŀ�ؼ� ���
			con = getConn();

			// 3. ������ ������ü ��� : lunch���̺��� �̸�, �ڵ�, ����, �Է����� �����ֱٿ� �Էµ�
			// �ͺ��� ��ȸ
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
				list.add(clVO);// ��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			}

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

	}// setChatList

	public RecentChatVO recentChat(String dealCode) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		RecentChatVO rcVO = null;

		try {
			// 2.Ŀ�ؼ� ���
			con = getConn();

			// 3. ������ ������ü ��� : lunch���̺��� �̸�, �ڵ�, ����, �Է����� �����ֱٿ� �Էµ�
			// �ͺ��� ��ȸ
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
			// 6. ���� ����
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
////////////////////////// ä��  ���۽ÿ� ��ǰ��  �÷��� Ȯ�� �޼���!!!!!!!!!!!//////////////////////////////
	public FlagVO checkFlag(String dealCode) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		FlagVO fVO = null;

		try {
			// 2.Ŀ�ؼ� ���
			con = getConn();

			// 3. ������ ������ü ��� : lunch���̺��� �̸�, �ڵ�, ����, �Է����� �����ֱٿ� �Էµ�
			// �ͺ��� ��ȸ
			String checkFlag = "   select   sale_flag, all_flag,d.user_id user_id from deal d,product p where  deal_code = ? and d.product_code = p.product_code   ";

			pstmt = con.prepareStatement(checkFlag);
			pstmt.setString(1, dealCode);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				fVO = new FlagVO(rs.getString("sale_flag"), rs.getString("all_flag"), rs.getString("user_id"));
			}

		} finally {
			// 6. ���� ����
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		}
		return fVO;

	}

	/////////////////// �ǸſϷ� ������ ä�� ����Ʈ �ҷ����� �޼���ѤѤѤѤѤѤѤѤѤ�/////////////////
	public List<DealListVO> setDealList(String productCode) throws SQLException {
		List<DealListVO> list = new ArrayList<DealListVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 2.Ŀ�ؼ� ���
			con = getConn();

			// 3. ������ ������ü ��� : lunch���̺��� �̸�, �ڵ�, ����, �Է����� �����ֱٿ� �Էµ�
			// �ͺ��� ��ȸ
			String dealList = "	select  d.user_id user_id,loc ,deal_code from deal d, id_info i, location_list l  where d.product_code = ? and d.user_id = i.user_id and i.loc_code = l.loc_code	";

			pstmt = con.prepareStatement(dealList);
			pstmt.setString(1, productCode);

			rs = pstmt.executeQuery();

			DealListVO dlVO = null;
			while (rs.next()) {
				dlVO = new DealListVO(rs.getString("user_id"), rs.getString("loc"), rs.getString("deal_code"));
				list.add(dlVO);
			}

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

	}

	//////////////////////// �Ǹ� �Ϸ� ������ �÷��� ������//////////////////
	public int changeFlag(String dealCode) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int result = 0;
		try {
			// 2.Ŀ�ؼ� ���
			con = getConn();

			// 3. ������ ������ü ��� : lunch���̺��� �̸�, �ڵ�, ����, �Է����� �����ֱٿ� �Էµ�
			// �ͺ��� ��ȸ
			String changeFlag = "	update deal set sale_flag='Y' where deal_code = ?	";

			pstmt = con.prepareStatement(changeFlag);
			pstmt.setString(1, dealCode);

			result = pstmt.executeUpdate();

		} finally {
			// 6. ���� ����
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		}

		return result;
	}

	////////////////////// ä��â���� �ŷ� Ȯ�� �������� �÷��� ������///////
	public int selectDeal(String flag, String dealCode) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		int result = 0;
		try {
			// 2.Ŀ�ؼ� ���
			con = getConn();

			// 3. ������ ������ü ��� : lunch���̺��� �̸�, �ڵ�, ����, �Է����� �����ֱٿ� �Էµ�
			// �ͺ��� ��ȸ
			
			String changeFlag = "	update deal set sale_flag=? where deal_code = ?	";
			
			if(flag.equals("P")) {
				changeFlag = "	update deal set sale_flag=?, sale_date=sysdate  where deal_code = ?	";
			}
			
			
			pstmt = con.prepareStatement(changeFlag);
			pstmt.setString(1, flag);
			pstmt.setString(2, dealCode);

			result = pstmt.executeUpdate();

			if (flag.equals("P")) {
				pstmt.close();
				changeFlag = "	update product set all_flag = 'B' where  product_code = (select product_code from deal where deal_code =?)  ";
				pstmt = con.prepareStatement(changeFlag);
				pstmt.setString(1, dealCode);
				result += pstmt.executeUpdate();

			}

		} finally {
			// 6. ���� ����
//			if (rs != null)
//				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		}

		return result;
	}// selectDeal

	/////////////////////////////////////////////////////////
	//////////////////ä��â���� ������â ����!
	//////////////////�켱�� ���ڵ忡�� ��ǰ�ڵ� ��������
	public String getProCode(String dealCode) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		String proCode= "";
		ResultSet rs = null;

		try {
			// 2.Ŀ�ؼ� ���
			con = getConn();

			// 3. ������ ������ü ��� : lunch���̺��� �̸�, �ڵ�, ����, �Է����� �����ֱٿ� �Էµ�
			// �ͺ��� ��ȸ
			
			String query = "	select product_code from deal where deal_code=?	";
			
		
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dealCode);
	

			rs = pstmt.executeQuery();

			if (rs.next()) {
			proCode =rs.getString("product_code");
			}

		} finally {
			// 6. ���� ����
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		}
		
		return proCode	;
	}
	
	///////////////////////////////////////////////
	////////////////////// ������â���� ���� ���������� �÷��� Ȯ��
	///////(�˼������� �ŷ������� �ǸſϷ����� ���)
	public FlagVO checkFlag2(String productCode) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		FlagVO fVO = null;

		try {
			// 2.Ŀ�ؼ� ���
			con = getConn();

			// 3. ������ ������ü ��� : lunch���̺��� �̸�, �ڵ�, ����, �Է����� �����ֱٿ� �Էµ�
			// �ͺ��� ��ȸ
			String checkFlag = "   select   sale_flag, all_flag,d.user_id user_id from deal d,product p where  p.product_code = ? and d.product_code = p.product_code   ";

			pstmt = con.prepareStatement(checkFlag);
			pstmt.setString(1, productCode);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				fVO = new FlagVO(rs.getString("sale_flag"), rs.getString("all_flag"), rs.getString("user_id"));
			}

		} finally {
			// 6. ���� ����
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
// �α��� �� �̸� ���!!
	public String[] loginRun(LoginVO lVO) throws SQLException {
		String[] loginInfo = new String[2];
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
// ����
			con = getConn();
// ������ ����
			StringBuilder selectLogin = new StringBuilder();
			selectLogin.append("select user_name , suspend_flag ").append(" from id_info ").append(" where user_id=? ")
					.append(" and password= ?");

			pstmt = con.prepareStatement(selectLogin.toString());
// ���ε� �� ���� ���
			pstmt.setString(1, lVO.getId());
			pstmt.setString(2, lVO.getPass());
// ���� ����
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
//�ߺ��� ���̵� üũ 

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

//ȸ������
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

//�н� ���̵�ã��
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
//���ɸ�� �߰� 

	public int insertInterest(InterestListVO irVO, boolean checkFlag) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			con = getConn();
			if (checkFlag) {
				StringBuilder insertInterest = new StringBuilder();
				insertInterest.append("insert into INTERESTED_PRODUCT(product_code , user_id)values")
						.append("((select product_code from product where product_code=?),?)");
				pstmt = con.prepareStatement(insertInterest.toString());
				System.out.println(irVO.getProduct_code() + " " + irVO.getUser_id());
				pstmt.setString(1, irVO.getProduct_code());
				pstmt.setString(2, irVO.getUser_id());
				flag = pstmt.executeUpdate();
				pstmt.close();
			} else if (!checkFlag) {
				StringBuilder deletInterest = new StringBuilder();
				deletInterest.append("delete from INTERESTED_PRODUCT where product_code=?");
				pstmt = con.prepareStatement(deletInterest.toString());
				pstmt.setString(1, irVO.getProduct_code());
				flag = pstmt.executeUpdate();
			} // end if
		} finally {
			if (con != null) {
				con.close();
			} // end if
			if (pstmt != null) {
				pstmt.close();
			} // end if
		} // end finally

		return flag;
	}// insertInterest

	public String selectInterestCheck(InterestListVO irVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String user_id = "";
		try {
			con = getConn();
			String selectCheck = "select user_id from INTERESTED_PRODUCT where product_code=? and user_id=?";
			pstmt = con.prepareStatement(selectCheck);

			pstmt.setString(1, irVO.getProduct_code());
			pstmt.setString(2, irVO.getUser_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user_id = rs.getString(1);
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

		} // end finally

		return user_id;
	}// selectInterestCheck
//���ɸ�� ���̱�

	public List<AllListVO> selectInterestList() throws SQLException {
		List<AllListVO> list = new ArrayList<AllListVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConn();
			StringBuilder selectinterest = new StringBuilder();
			selectinterest.append(
					" select p.PRODUCT_CODE, p.IMG_FILE, p.PRODUCT_NAME, p.PRICE, P.USER_ID, i.loc_code,l.loc, to_char(p.UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,c.category ")
					.append(" from PRODUCT p, id_info i, location_list l,category_list c,interested_product ip ")
					.append(" where ( p.user_id= i.user_id and i.loc_code=l.loc_code and c.category_code=p.category_code and p.product_code=ip.product_code) ")
					.append(" and ip.user_id=? and p.all_flag='P'");

			pstmt = con.prepareStatement(selectinterest.toString());
			pstmt.setString(1, RunMarketMain.userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AllListVO aVO = new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc"), rs.getString("inputDate"),
						rs.getString("category"), rs.getString("USER_ID"), rs.getInt("PRICE"));
				list.add(aVO);
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

		return list;
	}// selectInterestList

///////////////////////////////////////////////////////////////////////������� �輭�� 2019.9.19 ����//////////////////////////////////////////////////////////////   

//	public static void main(String[] args) {
//
//	}//main

}// class
