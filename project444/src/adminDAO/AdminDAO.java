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
import adminVO.ProductDetailVO;
import adminVO.ProductListVO;
import adminVO.ProductVO;
import adminVO.SuspendIdVO;
import adminVO.UserIdControlVO;
import adminVO.UserIdDetailVO;
import adminVO.UserIdVO;
import adminVO.CheckDetailVO;

/**
 * �����ڿ��� DMBS�� ������ ó���ϴ� Ŭ����.
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
	 * DBMS�� ������ ��ȯ�ϴ� ��
	 * @return ����� Connection
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException{
		Connection con = null;
		//1. ����̹� �ε� (ojdbc8.jar)
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch

		//2. Connection ���
		String url = "jdbc:oracle:thin:@211.63.89.159:1521:orcl";
		String id = "junggo";
		String pass = "1234";
		
		con = DriverManager.getConnection(url, id, pass);
		
		return con;
	}//getConnection
	
	
	/**
	 * DMBS���̺� �����ϴ� ��� �˼� ����� ��ȸ
	 * @return �˼� ���
	 * @throws SQLException
	 */
	public List<CheckListVO> selectAllCheckList(CheckVO cVO) throws SQLException{
		List<CheckListVO> list = new ArrayList<CheckListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectCheck = new StringBuilder();
		
		try {
			//2. Ŀ�ؼ� ���
			con = getConnection();
			
			selectCheck
			.append("	select product_code, img_file, user_id, c.category, product_name, to_char(price, '9,999,999') price, to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and all_flag = 'N'	");
			
			int bindCnt = 0;
			
			if( cVO != null) {// �˻������ �ԷµǾ�����
				//ī�װ��� ���õ� ���
				if(!cVO.getCategory().equals("ī�װ� ��ü")&&cVO.getValue().equals("")) {
					selectCheck.append(" and category=?");
					bindCnt = 1;
				}else if(cVO.getCategory().equals("ī�װ� ��ü")&&!cVO.getValue().equals("")){
					selectCheck.append(" and ").append(cVO.getCol_name().equals("��ǰ��")?"product_name":"user_id").append("	like '%'||?||'%'");
					bindCnt = 2;
				}else if(!cVO.getCategory().equals("ī�װ� ��ü")&&!cVO.getValue().equals("")) {
					selectCheck.append(" and category=? and ").append(cVO.getCol_name().equals("��ǰ��")?"product_name":"user_id").append("	like '%'||?||'%'");
					bindCnt = 3;
				}else {
					bindCnt = 0;
				}//end if
				
			}//end if
			
			
			pstmt= con.prepareStatement(selectCheck.toString());
				
			//���ε� ������ ���ֱ�
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
		
			//5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			CheckListVO clv = null;
			
			while(rs.next()) {
				clv = new CheckListVO(rs.getString("product_code"), rs.getString("img_file"), rs.getString("product_name"), 
						rs.getString("upload_date"), rs.getString("user_id"),rs.getString("category"),rs.getString("price"));
				list.add(clv);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			
			}//end while
			
//			System.out.println(list);
		}finally {
			
			//6. ���� ����
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//selectAllCheckList
	
	/**
	 * DMBS���̺� �����ϴ� ��� �˼� ����� �ֽż����� ��ȸ
	 * @return �˼� ���
	 * @throws SQLException
	 */
	public List<CheckListVO> selectCheckOrderbyList(CheckVO cVO) throws SQLException{
		List<CheckListVO> list = new ArrayList<CheckListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectCheck = new StringBuilder();
		
		try {
			//2. Ŀ�ؼ� ���
			con = getConnection();
			
			selectCheck
			.append("	select product_code, img_file, user_id, c.category, product_name, to_char(price,'9,999,999')price, to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and all_flag = 'N'	");
			
			int bindCnt = 0;
			
			if( cVO != null) {// �˻������ �ԷµǾ�����
				//ī�װ��� ���õ� ���
				if(!cVO.getCategory().equals("ī�װ� ��ü")&&cVO.getValue().equals("")) {
					selectCheck.append(" and category=?");
					bindCnt = 1;
				}else if(cVO.getCategory().equals("ī�װ� ��ü")&&!cVO.getValue().equals("")){
					selectCheck.append(" and ").append(cVO.getCol_name().equals("��ǰ��")?"product_name":"user_id").append("	like '%'||?||'%'");
					bindCnt = 2;
				}else if(!cVO.getCategory().equals("ī�װ� ��ü")&&!cVO.getValue().equals("")) {
					selectCheck.append(" and category=? and ").append(cVO.getCol_name().equals("��ǰ��")?"product_name":"user_id").append("	like '%'||?||'%'");
					bindCnt = 3;
				}//end if
				
			}//end if
			
//			System.out.println( selectCheck );
			selectCheck.append("	order by upload_date desc	");
			pstmt= con.prepareStatement(selectCheck.toString());
				
			//���ε� ������ ���ֱ�
//			System.out.println(bindCnt);
			if( bindCnt == 1 ) {
				pstmt.setString(1, cVO.getCategory());
			} else if(bindCnt == 2){
				pstmt.setString(1, cVO.getValue());
			} else if(bindCnt == 3) {
				pstmt.setString(1, cVO.getCategory());
				pstmt.setString(2, cVO.getValue());
			}//end else
		
			//5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			CheckListVO clv = null;
			
			while(rs.next()) {
				clv = new CheckListVO(rs.getString("product_code"), rs.getString("img_file"), rs.getString("product_name"), 
						rs.getString("upload_date"), rs.getString("user_id"),rs.getString("category"),rs.getString("price"));
				list.add(clv);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			
			}//end while
		} finally {
			
			//6. ���� ����
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//selectCheckOrderbyList
	
	/**
	 * DMBS���̺� �����ϴ� ��� ������ �ʱ�ȭ�Ͽ� �˼� ����� ��ȸ
	 * @return �˼� ���
	 * @throws SQLException
	 */
	public List<CheckListVO> reselectAllCheckList() throws SQLException{
		List<CheckListVO> list = new ArrayList<CheckListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectCheck = new StringBuilder();
		
		try {
			//2. Ŀ�ؼ� ���
			con = getConnection();
			
			selectCheck
			.append("	select product_code, img_file, user_id, c.category, product_name, to_char(price,'9,999,999')price, to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and all_flag = 'N'	");
			
			pstmt= con.prepareStatement(selectCheck.toString());
			
			//���ε� ������ ���ֱ�
			
			//5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			CheckListVO clv = null;
			
			while(rs.next()) {
				clv = new CheckListVO(rs.getString("product_code"), rs.getString("img_file"), rs.getString("product_name"), 
						rs.getString("upload_date"), rs.getString("user_id"),rs.getString("category"),rs.getString("price"));
				list.add(clv);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
				
			}//end while
			
		}finally {
			//6. ���� ����
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//reselectAllCheckList
	
	/**
	 * ������ â�� �� �� �ʿ��� ������ VO(DTO ����)�� �ִ� �۾�
	 * @param cdVO ������ â�� �ʿ��� ���� ���� VO
	 * @throws SQLException
	 */
	public void checkDetail(CheckDetailVO cdVO) throws SQLException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectCheckDetail = new StringBuilder();
		
		try {
			//2. Ŀ�ؼ� ���
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
			
			//6. ���� ����
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
	}//checkDetail
	
	////////////////////////////////////////////////////////////////////////�ι�° �� start/////////////////////////////////////////////////////////////////////////////////////
	

	/**
	 * DMBS���̺� �����ϴ� ��� ��ǰ ����� ��ȸ
	 * @return ��ǰ ���
	 * @throws SQLException
	 */
	public List<ProductListVO> selectAllProductList(ProductVO pVO) throws SQLException{
		List<ProductListVO> list = new ArrayList<ProductListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectProduct = new StringBuilder();
		
		try {
			//2. Ŀ�ؼ� ���
			con = getConnection();
			
			selectProduct
			.append("	select product_code, img_file, user_id, c.category, product_name, to_char(price,'9,999,999')price, to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date, all_flag	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and 	").append(pVO.getQuery());
			
//			System.out.println(selectProduct);
			
			int bindCnt = 0;
			
			if( pVO != null) {// �˻������ �ԷµǾ�����
				//ī�װ��� ���õ� ���
				if(!pVO.getCategory().equals("ī�װ� ��ü")&&pVO.getValue().equals("")) {
					selectProduct.append(" and category=?");
					bindCnt = 1;
				}else if(pVO.getCategory().equals("ī�װ� ��ü")&&!pVO.getValue().equals("")){
					selectProduct.append(" and ").append(pVO.getCol_name().equals("��ǰ��")?"product_name":"user_id").append("	like '%'||?||'%'");
					bindCnt = 2;
				}else if(!pVO.getCategory().equals("ī�װ� ��ü")&&!pVO.getValue().equals("")) {
					selectProduct.append(" and category=? and ").append(pVO.getCol_name().equals("��ǰ��")?"product_name":"user_id").append("	like '%'||?||'%'");
					bindCnt = 3;
				}//end if
				
			}//end if
			
//			System.out.println( selectProduct );
//			System.out.println( pVO );
			
			pstmt= con.prepareStatement(selectProduct.toString());
				
			//���ε� ������ ���ֱ�
//			System.out.println(bindCnt);
			if( bindCnt == 1 ) {
				pstmt.setString(1, pVO.getCategory());
			} else if(bindCnt == 2){
				pstmt.setString(1, pVO.getValue());
			} else if(bindCnt == 3) {
				pstmt.setString(1, pVO.getCategory());
				pstmt.setString(2, pVO.getValue());
			}//end if
		
			//5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			ProductListVO plv = null;
			
			while(rs.next()) {
				plv = new ProductListVO(rs.getString("product_code"), rs.getString("img_file"), rs.getString("product_name"), 
						rs.getString("upload_date"), rs.getString("user_id"),rs.getString("category"),rs.getString("all_flag"),rs.getString("price"));
				list.add(plv);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			
			}//end while
//			System.out.println(list);
		}finally {
			
			//6. ���� ����
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//selectAllProductList
	
	/**
	 * DMBS���̺� �����ϴ� ��� ������ �ʱ�ȭ�Ͽ� �˼� ����� ��ȸ
	 * @return �˼� ���
	 * @throws SQLException
	 */
	public List<ProductListVO> reselectAllProductList() throws SQLException{
		List<ProductListVO> list = new ArrayList<ProductListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectCheck = new StringBuilder();
		
		try {
			//2. Ŀ�ؼ� ���
			con = getConnection();
			
			selectCheck
			.append("	select product_code, img_file, user_id, c.category, product_name, to_char(price,'9,999,999')price, to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date, all_flag	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and (all_flag != 'N' and all_flag != 'F')	");
			
			pstmt= con.prepareStatement(selectCheck.toString());
			
			//���ε� ������ ���ֱ�
			
			//5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			ProductListVO plv = null;
			
			while(rs.next()) {
				plv = new ProductListVO(rs.getString("product_code"), rs.getString("img_file"), rs.getString("product_name"), 
						rs.getString("upload_date"), rs.getString("user_id"),rs.getString("category"),rs.getString("all_flag"),rs.getString("price"));
				list.add(plv);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
				
			}//end while
			
		}finally {
			//6. ���� ����
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//reselectAllProductList
	
	/**
	 * DMBS���̺� �����ϴ� ��� ��ǰ ����� �ֽż����� ��ȸ
	 * @return ��ǰ ���
	 * @throws SQLException
	 */
	public List<ProductListVO> selectOrderByProductList(ProductVO pVO) throws SQLException{
		List<ProductListVO> list = new ArrayList<ProductListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectProduct = new StringBuilder();
		
		try {
			//2. Ŀ�ؼ� ���
			con = getConnection();
			
			selectProduct
			.append("	select product_code, img_file, user_id, c.category, product_name, to_char(price,'9,999,999')price, to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date, all_flag	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and 	").append(pVO.getQuery());
			
//			System.out.println(selectProduct);
			
			int bindCnt = 0;
			
			if( pVO != null) {// �˻������ �ԷµǾ�����
				//ī�װ��� ���õ� ���
				if(!pVO.getCategory().equals("ī�װ� ��ü")&&pVO.getValue().equals("")) {
					selectProduct.append(" and category=?");
					bindCnt = 1;
				}else if(pVO.getCategory().equals("ī�װ� ��ü")&&!pVO.getValue().equals("")){
					selectProduct.append(" and ").append(pVO.getCol_name().equals("��ǰ��")?"product_name":"user_id").append("	like '%'||?||'%'");
					bindCnt = 2;
				}else if(!pVO.getCategory().equals("ī�װ� ��ü")&&!pVO.getValue().equals("")) {
					selectProduct.append(" and category=? and ").append(pVO.getCol_name().equals("��ǰ��")?"product_name":"user_id").append("	like '%'||?||'%'");
					bindCnt = 3;
				}//end if
				
			}//end if
			
//			System.out.println( pVO );
			selectProduct.append("	order by upload_date desc	");
			pstmt= con.prepareStatement(selectProduct.toString());
				
//			System.out.println( selectProduct );
			//���ε� ������ ���ֱ�
//			System.out.println(bindCnt);
			if( bindCnt == 1 ) {
				pstmt.setString(1, pVO.getCategory());
			} else if(bindCnt == 2){
				pstmt.setString(1, pVO.getValue());
			} else if(bindCnt == 3) {
				pstmt.setString(1, pVO.getCategory());
				pstmt.setString(2, pVO.getValue());
			}//end if
		
			//5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			ProductListVO plv = null;
			
			while(rs.next()) {
				plv = new ProductListVO(rs.getString("product_code"), rs.getString("img_file"), rs.getString("product_name"), 
						rs.getString("upload_date"), rs.getString("user_id"),rs.getString("category"),rs.getString("all_flag"),rs.getString("price"));
				list.add(plv);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			
			}//end while
//			System.out.println(list);
		}finally {
			
			//6. ���� ����
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//selectOrderByProductList
	
	/**
	 * ������ â�� �� �� �ʿ��� ������ VO(DTO ����)�� �ִ� �۾�
	 * @param cdVO ������ â�� �ʿ��� ���� ���� VO
	 * @throws SQLException
	 */
	public void productDetail(ProductDetailVO pdVO) throws SQLException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectProductDetail = new StringBuilder();
		
		try {
			//2. Ŀ�ؼ� ���
			con = getConnection();
			selectProductDetail
			.append("	select img_file, product_name, info,  to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date, user_id, c.category, price, rejection_reason	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and product_code = ?	");
			
			pstmt = con.prepareStatement(selectProductDetail.toString());
			
			pstmt.setString(1, pdVO.getProduct_code());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pdVO.setImg_file(rs.getString("img_file"));
				pdVO.setProduct_name(rs.getString("product_name"));
				pdVO.setInfo(rs.getString("info"));
				pdVO.setUpload_date(rs.getString("upload_date"));
				pdVO.setUser_id(rs.getString("user_id"));
				pdVO.setCategory(rs.getString("category"));
				pdVO.setPrice(rs.getString("price"));
				pdVO.setRejectMsg(rs.getString("rejection_reason"));
			}//end while
			
//			System.out.println(dv);
		}finally {
			
			//6. ���� ����
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
	}//checkDetail
	
	////////////////////////////////////////////////////////////////////////�ι�° �� end/////////////////////////////////////////////////////////////////////////////////////

	/**
	 * DMBS���̺� �����ϴ� ��� ����ID ����� ��ȸ
	 * @return ����ID ���
	 * @throws SQLException
	 */
	public List<UserIdVO> selectAllUserIdList(String userId) throws SQLException{
		List<UserIdVO> list = new ArrayList<UserIdVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectUserId = new StringBuilder();
		
		try {
			//2. Ŀ�ؼ� ���
			con = getConnection();
			
			selectUserId
			.append("	select user_id, user_name, gender, phone, l.loc, to_char(join_date,'yyyy-mm-dd hh24:mi')join_date, suspend_flag	")
			.append("	from id_info i, location_list l	")
			.append("	where (l.loc_code = i.loc_code)	");
			
			int bindCnt = 0;
			
			if( !userId.equals("")) {// �˻������ �ԷµǾ�����
				selectUserId.append(" and user_id=?");
				bindCnt++;
			}//end if
			
			pstmt= con.prepareStatement(selectUserId.toString());
				
			//���ε� ������ ���ֱ�
//			System.out.println(bindCnt);
			if( bindCnt == 1 ) {
				pstmt.setString(1, userId);
			}//end if
		
			//5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			UserIdVO uiVO = null;
			
			while(rs.next()) {
				uiVO = new UserIdVO(rs.getString("user_id"), rs.getString("user_name"), rs.getString("gender"), 
						rs.getString("phone"), rs.getString("loc"),rs.getString("join_date"),rs.getString("suspend_flag"));
				list.add(uiVO);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			
			}//end while
			
		}finally {
			
			//6. ���� ����
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//selectAllUserIdList
	
	/**
	 * DMBS���̺� �����ϴ� ��� ������ �ʱ�ȭ�Ͽ� �˼� ����� ��ȸ
	 * @return �˼� ���
	 * @throws SQLException
	 */
	public List<UserIdVO> reselectAllUserIdList() throws SQLException{
		
		List<UserIdVO> list = new ArrayList<UserIdVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectUserId = new StringBuilder();
		
		try {
			//2. Ŀ�ؼ� ���
			con = getConnection();
			
			selectUserId
			.append("	select user_id, user_name, gender, phone, l.loc, to_char(join_date,'yyyy-mm-dd hh24:mi')join_date, suspend_flag	")
			.append("	from id_info i, location_list l	")
			.append("	where (l.loc_code = i.loc_code)	");
			
			pstmt= con.prepareStatement(selectUserId.toString());
				
			//���ε� ������ ���ֱ�
		
			//5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			UserIdVO uiVO = null;
			
			while(rs.next()) {
				uiVO = new UserIdVO(rs.getString("user_id"), rs.getString("user_name"), rs.getString("gender"), 
						rs.getString("phone"), rs.getString("loc"),rs.getString("join_date"),rs.getString("suspend_flag"));
				list.add(uiVO);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			
			}//end while
			
		}finally {
			
			//6. ���� ����
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//reselectAllUserIdList
	
	/**
	 * ������ â�� �� �� �ʿ��� ������ VO(DTO ����)�� �ִ� �۾�
	 * @param uidVO ������ â�� �ʿ��� ���� ���� VO
	 * @throws SQLException
	 */
	public void userIdDetail(UserIdDetailVO uidVO) throws SQLException{
		
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
			
			//6. ���� ����
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
	}//UserIdDetail
	
	public List<SuspendIdVO> selectSuspendList() throws SQLException{
		
		List<SuspendIdVO> list = new ArrayList<SuspendIdVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectSuspend = new StringBuilder();
		
		try {
			//2. Ŀ�ؼ� ���
			con = getConnection();
			
			selectSuspend
			.append("	select user_id, suspend_reason, suspend_date, period	")
			.append("	from suspended_user	");
			
			pstmt= con.prepareStatement(selectSuspend.toString());
				
			//���ε� ������ ���ֱ�
		
			//5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			SuspendIdVO siVO = null;
			
			while(rs.next()) {
				siVO = new SuspendIdVO(rs.getString("user_id"), rs.getString("suspend_reason"), rs.getDate("suspend_date"), rs.getInt("period"));
				list.add(siVO);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			
			}//end while
			
		}finally {
			
			//6. ���� ����
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//selectSuspendList
	
	
	
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
	
	public boolean suspendControl(UserIdControlVO uicVO) throws SQLException {
		boolean updateFlag=false;
		int bindCnt = 0;
		String suspendFlag = uicVO.getSuspendFlag();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=getConnection();
			
			StringBuilder update = new StringBuilder();
			
			update
			.append("	update id_info 	")
			.append("	set suspend_flag = ?	")
			.append("	where user_id = ?	");
			
			if (uicVO.getSuspendFlag().equals("N")) {
				suspendFlag = "Y";
				bindCnt++;
			}else if (uicVO.getSuspendFlag().equals("Y")) {
				suspendFlag = "N";
			}//end else
			
//			System.out.println(suspendFlag + "/" + uicVO.getUserId());
			
			pstmt= con.prepareStatement(update.toString());
			
			pstmt.setString(1, suspendFlag);
			pstmt.setString(2, uicVO.getUserId());
						
			if(bindCnt == 1) {
				pstmt.executeUpdate();
			
				pstmt.close();//insert�� ���� 1�� ���� ����
				
				StringBuilder insert = new StringBuilder();
				
				insert
				.append("	insert into suspended_user(user_id, suspend_reason, period) 	")
				.append("	values(?,?,?)	");
				
				pstmt= con.prepareStatement(insert.toString());
				
				pstmt.setString(1, uicVO.getUserId());
				pstmt.setString(2, uicVO.getSuspendMsg());
				pstmt.setInt(3, uicVO.getPeriod());
				
			}//end if
			
			updateFlag=pstmt.executeUpdate()==1;
			
		} finally {
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return updateFlag;
		
	}//updateSuspend
	
}//class