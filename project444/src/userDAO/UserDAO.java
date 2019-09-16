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
import userVO.AllListVO;
import userVO.ChatListVO;
import userVO.DCodeAndIdAO;
import userVO.ForgotIdVO;
import userVO.ForgotPwVO;
import userVO.LoginVO;
import userVO.MarketDetailVO;
import userVO.PersonalInformVO;
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

	public List<AllListVO> selectRefresh() throws SQLException {
		List<AllListVO> list = new ArrayList<AllListVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// Ŀ�ؼ� ���
			con = getConn();

			// 3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
			StringBuilder selectAll = new StringBuilder();
			selectAll

					.append(" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, PRODUCT.USER_ID, PRICE,loc_code ")
					.append(" from PRODUCT, id_info ")
					.append(" where ( PRODUCT.user_id= id_info.user_id) and all_flag ='P' ");

//        .append(" order by inputDate desc ");

			pstmt = con.prepareStatement(selectAll.toString());

			// 4. ���ε庯���� �� �ֱ�
			// 5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			AllListVO alv = null;

			while (rs.next()) {
				alv = new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc_code"), rs.getString("inputDate"),
						rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"), rs.getInt("PRICE"));

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
			// Ŀ�ؼ� ���
			con = getConn();

			// 3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
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

//			selectArea.append(" order by inputDate desc "); 

//			select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, USER_ID, PRICE
//			from PRODUCT
//			where all_flag ='P'  and user_id in ( select user_id  from id_info where loc_code ='07') and category_code ='04'  and product_name like '%����%'
//			order by inputDate desc ;

			/*
			 * select user_id,product_name from product where ( user_id in ( select user_id
			 * from id_info where loc_code ='12')) and all_flag ='P'; �̰���
			 */

			pstmt = con.prepareStatement(selectArea.toString());

			// 4. ���ε庯���� �� �ֱ�
			// 5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			AllListVO alv = null;

			while (rs.next()) {
				alv = new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc_code"), rs.getString("inputDate"),
						rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"), rs.getInt("PRICE"));
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

	}// selectAreaList

	public List<AllListVO> selectListByID(int jcbAreaIndex, int jcbCateIndex, String jtfText) throws SQLException {

		List<AllListVO> list = new ArrayList<AllListVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// Ŀ�ؼ� ���
			con = getConn();

			// 3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
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

			// 4. ���ε庯���� �� �ֱ�
			// 5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			AllListVO alv = null;

			while (rs.next()) {
				alv = new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc_code"), rs.getString("inputDate"),
						rs.getString("CATEGORY_CODE"), rs.getString("user_id"), rs.getInt("PRICE"));
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
			// Ŀ�ؼ� ���
			con = getConn();

			// 3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
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
//			where all_flag ='P'  and user_id in ( select user_id  from id_info where loc_code ='07') and category_code ='04'  and product_name like '%����%'
//			order by inputDate desc ;

			/*
			 * select user_id,product_name from product where ( user_id in ( select user_id
			 * from id_info where loc_code ='12')) and all_flag ='P'; �̰���
			 */

			pstmt = con.prepareStatement(selectArea.toString());

			// 4. ���ε庯���� �� �ֱ�
			// 5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			AllListVO alv = null;

			while (rs.next()) {
				alv = new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc_code"), rs.getString("inputDate"),
						rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"), rs.getInt("PRICE"));
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
			// Ŀ�ؼ� ���
			con = getConn();

			// 3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
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
//			where all_flag ='P'  and user_id in ( select user_id  from id_info where loc_code ='07') and category_code ='04'  and product_name like '%����%'
//			order by inputDate desc ;

			/*
			 * select user_id,product_name from product where ( user_id in ( select user_id
			 * from id_info where loc_code ='12')) and all_flag ='P'; �̰���
			 */

			pstmt = con.prepareStatement(selectArea.toString());

			// 4. ���ε庯���� �� �ֱ�
			// 5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			AllListVO alv = null;

			while (rs.next()) {
				alv = new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc_code"), rs.getString("inputDate"),
						rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"), rs.getInt("PRICE"));
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

	}// selectListRecent

	/**
	 * ��ǰ ������â���� ���� �ѱ�� method
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
			// Ŀ�ؼ� ���
			con = getConn();

			// 3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
			StringBuilder selectDetail = new StringBuilder();
			selectDetail.append(
					" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, PRODUCT.USER_ID, PRICE,loc_code, info ")
					.append(" from PRODUCT, id_info ")
					.append(" where ( PRODUCT.user_id= id_info.user_id) and all_flag ='P' and PRODUCT_CODE=? ");// ����ǥ��
																												// ''��
																												// ���̾���
																												// �ȵ�.
//				.append(" order by inputDate desc "); 

			pstmt = con.prepareStatement(selectDetail.toString());

			// 4. ���ε庯���� �� �ֱ�
			pstmt.setString(1, productCode);
			// 5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();

			if (rs.next()) {
				mdVO = new MarketDetailVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("loc_code"), rs.getString("inputDate"),
						rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"), rs.getString("INFO"),
						rs.getInt("PRICE"));
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
///////////////////////////////////// Mypage method ///////////////////////////////////////
	
/**
* �������������� ������ �Ǹ����� ��ǰ ����Ʈ �����ֱ�
* @return
* @throws SQLException
*/
public List<SaleListVO>selectSaleList(String id) throws SQLException {


List<SaleListVO> list=new ArrayList<SaleListVO>();

Connection con=null;
PreparedStatement pstmt=null;
ResultSet rs=null;



try {
//Ŀ�ؼ� ���
con=getConn();


//3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
StringBuilder selectAll = new StringBuilder();
selectAll			
.append(" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, PRODUCT.USER_ID, PRICE,loc_code, all_flag ")
.append(" from PRODUCT, id_info ")
.append(" where ( PRODUCT.user_id= id_info.user_id) and all_flag ='P' and PRODUCT.USER_ID=? ");




pstmt=con.prepareStatement(selectAll.toString());

//4. ���ε庯���� �� �ֱ�
pstmt.setString(1, id);
//5. ���� ���� �� ��� ���
rs=pstmt.executeQuery(); 
SaleListVO slv=null; 

while(rs.next()) {
slv=new SaleListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
rs.getString("PRODUCT_NAME"), rs.getString("loc_code"), rs.getString("inputDate"),  
rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"),rs.getString("all_flag"),rs.getInt("PRICE"));


list.add(slv);
}//end while
} finally {
//6. �������
if (rs !=null) { rs.close(); }//end if
if (pstmt !=null) { pstmt.close(); }//end if
if (con !=null) { con.close(); }//end if

}//end finally
return list;


}//selectSaleList

/**
* ���������� �Ǹų���-�ǸſϷ����� ������ �� ó��
* @return
* @throws SQLException
*/
public List<SaleListVO>selectCompList(String id) throws SQLException {


List<SaleListVO> list=new ArrayList<SaleListVO>();

Connection con=null;
PreparedStatement pstmt=null;
ResultSet rs=null;



try {
//Ŀ�ؼ� ���
con=getConn();


//3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
StringBuilder selectAll = new StringBuilder();
selectAll			
.append(" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, PRODUCT.USER_ID, PRICE,loc_code, all_flag ")
.append(" from PRODUCT, id_info ")
.append(" where ( PRODUCT.user_id= id_info.user_id) and all_flag ='B' and PRODUCT.USER_ID=? ");




pstmt=con.prepareStatement(selectAll.toString());

//4. ���ε庯���� �� �ֱ�
pstmt.setString(1, id);
//5. ���� ���� �� ��� ���
rs=pstmt.executeQuery(); 
SaleListVO slv=null; 

while(rs.next()) {
slv=new SaleListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
rs.getString("PRODUCT_NAME"), rs.getString("loc_code"), rs.getString("inputDate"),  
rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"),rs.getString("all_flag"),rs.getInt("PRICE"));


list.add(slv);
}//end while
} finally {
//6. �������
if (rs !=null) { rs.close(); }//end if
if (pstmt !=null) { pstmt.close(); }//end if
if (con !=null) { con.close(); }//end if

}//end finally
return list;


}//selectCompList




//////////////////////////////////// MyPage /////////////////////////////////////////////////////

public boolean deletePost(String product_code) throws SQLException {
boolean deleteFlag= false;
Connection con=null;
PreparedStatement pstmt=null;
ResultSet rs=null;

try {
con=getConn();

StringBuilder deletePost=new StringBuilder();
deletePost
.append("	update Product	")
//	  	.append("	set img='', strong_point='', delete_flag='Y'	") // not null �ɷ��־ ������
.append("	set all_flag='D'	")
.append("	where product_code=?	");

pstmt=con.prepareStatement(deletePost.toString());

pstmt.setString(1, product_code);

deleteFlag=pstmt.executeUpdate()==1;
} finally {
if( pstmt!=null) { pstmt.close(); }//end if
if( con!=null) { con.close(); }//end if
}//end finally
return deleteFlag;
}//deletePost
	

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
					+ "where sender = ? and reciever = ? order by input_date desc";
			pstmt = con.prepareStatement(selectLunch);
			pstmt.setString(1, you);
			pstmt.setString(2, me);
			rs = pstmt.executeQuery();

			ChatVO CV = null;
			while (rs.next()) {
				CV = new ChatVO(rs.getString("chat"), rs.getString("sender"), rs.getDate("input_date"));
				list.add(CV);// ��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			}

			pstmt.close();

			String setFlag = "update chatting set  read_flag = 'Y'	where read_flag ='N'and sender = ? and reciever = ?";
			pstmt = con.prepareStatement(setFlag);
			pstmt.setString(1, you);
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
					+ "where read_flag ='N' sender = ? and reciever = ? order by input_date desc";
			pstmt = con.prepareStatement(selectLunch);
			pstmt.setString(1, you);
			pstmt.setString(2, me);
			rs = pstmt.executeQuery();

			ChatVO CV = null;
			while (rs.next()) {
				CV = new ChatVO(rs.getString("chat"), rs.getString("sender"), rs.getDate("input_date"));
				list.add(CV);// ��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			}
 
			pstmt.close();

			String setFlag = "update chatting set  read_flag = 'Y'	where read_flag ='N'and sender = ? and reciever = ?";
			pstmt = con.prepareStatement(setFlag);
			pstmt.setString(1, you);
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
///////////////////////////// ä�ø���Ʈ �޼���!!!!!!!!!!!!///////////////////////////////////

//	public List<ChatListVO> setChatList() {
//		
//		
//		
//		
//	}

////////////////////////////////////////////////////////////////////////////////////////////

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

//	public static void main(String[] args) {
//
//	}//main

}// class
