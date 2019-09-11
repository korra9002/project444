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
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
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
	public List<CheckListVO> selectAllCheckList(CheckVO cv) throws SQLException{
		List<CheckListVO> list = new ArrayList<CheckListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectCheck = new StringBuilder();
		
		try {
			//2. Ŀ�ؼ� ���
			con = getConnection();
			
			selectCheck
			.append("	select product_code, img_file, user_id, c.category, product_name, price, to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and all_flag = 'N'	");
			
			int bindCnt = 0;
			
			if( cv != null) {// �˻������ �ԷµǾ�����
				//ī�װ��� ���õ� ���
				if(!cv.getCategory().equals("ī�װ� ����")&&cv.getValue().equals("")) {
					selectCheck.append(" and category=?");
					bindCnt = 1;
				}else if(cv.getCategory().equals("ī�װ� ����")&&!cv.getValue().equals("")){
					selectCheck.append(" and ").append(cv.getCol_name().equals("��ǰ��")?"product_name":"user_id").append("=?");
					bindCnt = 2;
				}else if(!cv.getCategory().equals("ī�װ� ����")&&!cv.getValue().equals("")) {
					selectCheck.append(" and category=? and ").append(cv.getCol_name().equals("��ǰ��")?"product_name":"user_id").append("=?");
					bindCnt = 3;
				}//end if
				
			}//end if
			
			System.out.println( selectCheck );
			
			pstmt= con.prepareStatement(selectCheck.toString());
				
			//���ε� ������ ���ֱ�
			System.out.println(bindCnt);
			if( bindCnt == 1 ) {
				pstmt.setString(1, cv.getCategory());
				
			} else if(bindCnt == 2){
				pstmt.setString(1, cv.getValue());
			} else if(bindCnt == 3) {
				pstmt.setString(1, cv.getCategory());
				pstmt.setString(2, cv.getValue());
				
			}
		
			//5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			CheckListVO clv = null;
			
			while(rs.next()) {
				clv = new CheckListVO(rs.getString("product_code"), rs.getString("img_file"), rs.getString("product_name"), 
						rs.getString("upload_date"), rs.getString("user_id"),rs.getString("category"),rs.getInt("price"));
				list.add(clv);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			
			}//end while
		}finally {
			
			//6. ���� ����
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//selectAllCheckList
	
	/**
	 * DMBS���̺� �����ϴ� ��� �˼� ����� ��ȸ
	 * @return �˼� ���
	 * @throws SQLException
	 */
	public List<CheckListVO> selectOrderbyList(CheckVO cv) throws SQLException{
		List<CheckListVO> list = new ArrayList<CheckListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectCheck = new StringBuilder();
		
		try {
			//2. Ŀ�ؼ� ���
			con = getConnection();
			
			selectCheck
			.append("	select product_code, img_file, user_id, c.category, product_name, price, to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and all_flag = 'N'	");
			
			int bindCnt = 0;
			
			if( cv != null) {// �˻������ �ԷµǾ�����
				//ī�װ��� ���õ� ���
				if(!cv.getCategory().equals("ī�װ� ����")&&cv.getValue().equals("")) {
					selectCheck.append(" and category=?");
					bindCnt = 1;
				}else if(cv.getCategory().equals("ī�װ� ����")&&!cv.getValue().equals("")){
					selectCheck.append(" and ").append(cv.getCol_name().equals("��ǰ��")?"product_name":"user_id").append("=?");
					bindCnt = 2;
				}else if(!cv.getCategory().equals("ī�װ� ����")&&!cv.getValue().equals("")) {
					selectCheck.append(" and category=? and ").append(cv.getCol_name().equals("��ǰ��")?"product_name":"user_id").append("=?");
					bindCnt = 3;
				}//end if
				
			}//end if
			
			System.out.println( selectCheck );
			selectCheck.append("	order by upload_date	");
			pstmt= con.prepareStatement(selectCheck.toString());
				
			//���ε� ������ ���ֱ�
			System.out.println(bindCnt);
			if( bindCnt == 1 ) {
				pstmt.setString(1, cv.getCategory());
				
			} else if(bindCnt == 2){
				pstmt.setString(1, cv.getValue());
			} else if(bindCnt == 3) {
				pstmt.setString(1, cv.getCategory());
				pstmt.setString(2, cv.getValue());
				
			}
		
			//5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			CheckListVO clv = null;
			
			while(rs.next()) {
				clv = new CheckListVO(rs.getString("product_code"), rs.getString("img_file"), rs.getString("product_name"), 
						rs.getString("upload_date"), rs.getString("user_id"),rs.getString("category"),rs.getInt("price"));
				list.add(clv);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			
			}//end while
		}finally {
			
			//6. ���� ����
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		return list;
		
	}//selectOrderbyList
	
	
	public List<ProductListVO> selectAllProductList(ProductVO pv) throws SQLException{
		List<ProductListVO> list = new ArrayList<ProductListVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder selectCheck = new StringBuilder();
		
		try {
			//2. Ŀ�ؼ� ���
			con = getConnection();
			
			selectCheck
			.append("	select product_code, img_file, user_id, c.category, product_name, price, to_char(upload_date,'yyyy-mm-dd hh24:mi')upload_date, all_flag	")
			.append("	from product p, category_list c	")
			.append("	where (p.category_code = c.category_code) and all_flag = 'N'	");
			
			int bindCnt = 0;
			   
			if( pv != null) {// �˻������ �ԷµǾ�����
				//ī�װ��� ���õ� ���
				if(!pv.getCategory().equals("ī�װ� ����")&&pv.getValue().equals("")) {
					selectCheck.append(" and category=?");
					bindCnt = 1;
				}else if(pv.getCategory().equals("ī�װ� ����")&&!pv.getValue().equals("")){
					selectCheck.append(" and ").append(pv.getCol_name().equals("��ǰ��")?"product_name":"user_id").append("=?");
					bindCnt = 2;
				}else if(!pv.getCategory().equals("ī�װ� ����")&&!pv.getValue().equals("")) {
					selectCheck.append(" and category=? and ").append(pv.getCol_name().equals("��ǰ��")?"product_name":"user_id").append("=?");
					bindCnt = 3;
				}//end if
				
			}//end if
			
			System.out.println( selectCheck );
			
			pstmt= con.prepareStatement(selectCheck.toString());
				
			//���ε� ������ ���ֱ�
			System.out.println(bindCnt);
			if( bindCnt == 1 ) {
				pstmt.setString(1, pv.getCategory());
				
			} else if(bindCnt == 2){
				pstmt.setString(1, pv.getValue());
			} else if(bindCnt == 3) {
				pstmt.setString(1, pv.getCategory());
				pstmt.setString(2, pv.getValue());
			}
		
			//5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery();
			ProductListVO plv = null;
			
			while(rs.next()) {
				plv = new ProductListVO(rs.getString("product_code"), rs.getString("img_file"), rs.getString("product_name"), 
						rs.getString("upload_date"), rs.getString("user_id"),rs.getString("category"),rs.getString("all_flag"),rs.getInt("price"));
				list.add(plv);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			
			}//end while
		}finally {
			
			//6. ���� ����
			if(rs != null) {rs.close();}//end if
			if(pstmt != null) {pstmt.close();}//end if
			if(con != null) {con.close();}//end if
		}//end finally
		
		
		
		return list;
	}//selectAllProductList
	
	
}//class







