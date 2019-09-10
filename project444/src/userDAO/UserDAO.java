package userDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import userVO.AllListVO;

public class UserDAO {
	public static UserDAO uDAO;
	
	private UserDAO() { 
		
	}//userDAO 
	
	public static UserDAO getInstance() {
		if(uDAO==null) {
			uDAO=new UserDAO();
		}//end if
		return uDAO;
	}//getInstance 
	
	public Connection getConn() throws SQLException { 
		Connection con=null;
		
		//1. ����̹� �ε�
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("ddddd");
			e.printStackTrace();
		}//end catch
		
		//2. Connection ���
		
		String url="jdbc:oracle:thin:@211.63.89.159:1521:orcl";
		String id="junggo";
		String pass="1234";
		
		con=DriverManager.getConnection(url,id,pass);
		
		return con;			
	}//getConn
	
	public List<AllListVO> selectAllList() throws SQLException {
		List<AllListVO> list=new ArrayList<AllListVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		//Ŀ�ؼ� ���
		con=getConn();
		
		

	     //3.������ ������ü ��� : lunch���̺����� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
        StringBuilder selectAll = new StringBuilder();
        selectAll
        

        .append(" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, PRODUCT_CODE, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, PRICE")
        .append(" from PRODUCT ")
        .append(" where all_flag='P' ");  
//        .append(" order by input_date desc ");
		 
		pstmt=con.prepareStatement(selectAll.toString());
				 
		//4. ���ε庯���� �� �ֱ�
		//5. ���� ���� �� ��� ���
		rs=pstmt.executeQuery();
		AllListVO alv=null;
		
			while(rs.next()) {
				alv=new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("PRODUCT_CODE"), rs.getString("inputDate"), rs.getInt("PRICE"));
				list.add(alv);
			}//end while
		} finally {
		//6. �������
		if (rs !=null) { rs.close(); }//end if
		if (pstmt !=null) { pstmt.close(); }//end if
		if (con !=null) { con.close(); }//end if
		
		}//end finally
		return list;
		
	}//selectAllList
	
	
	
	public List<AllListVO> selectAreaList(int jcbindex) throws SQLException {
		List<AllListVO> list=new ArrayList<AllListVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			//Ŀ�ؼ� ���
			con=getConn();
			
			
			
			//3.������ ������ü ��� : lunch���̺����� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
			StringBuilder selectArea = new StringBuilder();
			selectArea			
			.append(" select p.PRODUCT_CODE PRODUCT_CODE, p.IMG_FILE IMG_FILE, p.PRODUCT_NAME PRODUCT_NAME, l.LOC_CODE LOC_CODE, to_char(p.UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, p.PRICE ")
			.append(" from PRODUCT p, LOCATION_LIST l")
			.append(" where p.all_flag='P' and l.loc_code='"+0+jcbindex+"'");  
			
			/*
			 * select user_id,product_name from product where ( user_id in ( select user_id
			 * from id_info where loc_code ='12')) and all_flag ='P';
			 �̰���*/ 
			
			
/*			 select user_id,product_name
			  from product
			  where    user_id =    ( select user_id from id_info where loc_code ='12') and all_flag ='P';  */
			
		//	select user_id from product, id_info i where all_flag ='p' and i.loc_code =0+jcbindex
//			System.out.println(selectArea);
			
//        .append(" order by input_date desc ");
			
			pstmt=con.prepareStatement(selectArea.toString());
			
			//4. ���ε庯���� �� �ֱ�
			//5. ���� ���� �� ��� ���
			rs=pstmt.executeQuery();
			AllListVO alv=null;
			
			while(rs.next()) {
				alv=new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("LOC_CODE"), rs.getString("inputDate"), rs.getInt("PRICE"));
				list.add(alv);
			}//end while
		} finally {
			//6. �������
			if (rs !=null) { rs.close(); }//end if
			if (pstmt !=null) { pstmt.close(); }//end if
			if (con !=null) { con.close(); }//end if
			
		}//end finally
		return list;
		
	}//selectAllList
	

	


	public static void main(String[] args) {

	}//main

}//class