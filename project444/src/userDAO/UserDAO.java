package userDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import userVO.AllListVO;
import userVO.LoginVO;

public class UserDAO {
	public static UserDAO uDAO;
	
	DecimalFormat df=new DecimalFormat("00");
	
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
		
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("ddddd");
			e.printStackTrace();
		}//end catch
		
		//2. Connection 얻기
		
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
		//커넥션 얻기
		con=getConn();
		
		

	     //3.쿼리문 생성객체 얻기 : lunch테이블에서 이름 코드, 가격, 입력일을 가장 최근에 입력된 것 부터 조회
        StringBuilder selectAll = new StringBuilder();
        selectAll
        

        .append(" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, PRODUCT_CODE, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, PRICE")
        .append(" from PRODUCT ")
        .append(" where all_flag='P' ");  
//        .append(" order by input_date desc ");
		 
		pstmt=con.prepareStatement(selectAll.toString());
				 
		//4. 바인드변수에 값 넣기
		//5. 쿼리 수행 후 결과 얻기
		rs=pstmt.executeQuery();
		AllListVO alv=null;
		
			while(rs.next()) {
				alv=new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), rs.getString("PRODUCT_CODE"), rs.getString("inputDate"), rs.getInt("PRICE"));
				list.add(alv);
			}//end while
		} finally {
		//6. 연결끊기
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
			//커넥션 얻기
			con=getConn();
			
			
			
			//3.쿼리문 생성객체 얻기 : lunch테이블에서 이름 코드, 가격, 입력일을 가장 최근에 입력된 것 부터 조회
			StringBuilder selectArea = new StringBuilder();
			selectArea			
			.append(" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, PRICE ")
			.append(" from PRODUCT")
			.append(" where user_id in ( select user_id  from id_info where loc_code ='"+df.format(jcbindex)+"')"+ "and all_flag ='P' ")							
	        .append(" order by inputDate desc "); 
			
			/*
			 * select user_id,product_name from product where ( user_id in ( select user_id
			 * from id_info where loc_code ='12')) and all_flag ='P';
			 이거임*/ 			

			
			pstmt=con.prepareStatement(selectArea.toString());
			 
			//4. 바인드변수에 값 넣기
			//5. 쿼리 수행 후 결과 얻기
			rs=pstmt.executeQuery(); 
			AllListVO alv=null; 
			
			while(rs.next()) {
				alv=new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), df.format(jcbindex), rs.getString("inputDate"), rs.getInt("PRICE"));
				list.add(alv);
			}//end while
		} finally {
			//6. 연결끊기
			if (rs !=null) { rs.close(); }//end if
			if (pstmt !=null) { pstmt.close(); }//end if
			if (con !=null) { con.close(); }//end if
			
		}//end finally
		return list;
		
	}//selectAreaList
	
	public List<AllListVO> selectCategoryList(int jcbindex) throws SQLException {
		List<AllListVO> list=new ArrayList<AllListVO>();
				
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			//커넥션 얻기
			con=getConn();
			
			
			
			//3.쿼리문 생성객체 얻기 : lunch테이블에서 이름 코드, 가격, 입력일을 가장 최근에 입력된 것 부터 조회
			StringBuilder selectArea = new StringBuilder();
			selectArea			
			.append(" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, PRICE ")
			.append(" from PRODUCT")
			.append(" where CATEGORY_CODE='"+df.format(jcbindex)+"'"+ "and all_flag ='P' ")							
			.append(" order by inputDate desc "); 
			
			System.out.println(df.format(jcbindex));
			
			/*
			 * select user_id,product_name from product where ( user_id in ( select user_id
			 * from id_info where loc_code ='12')) and all_flag ='P';
			 이거임*/ 			
			
			
			pstmt=con.prepareStatement(selectArea.toString());
			
			//4. 바인드변수에 값 넣기
			//5. 쿼리 수행 후 결과 얻기
			rs=pstmt.executeQuery(); 
			AllListVO alv=null; 
			
			while(rs.next()) {
				alv=new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), df.format(jcbindex), rs.getString("inputDate"), rs.getInt("PRICE")); //여기서 인덱스 지역아님.
				list.add(alv);
			}//end while
		} finally {
			//6. 연결끊기
			if (rs !=null) { rs.close(); }//end if
			if (pstmt !=null) { pstmt.close(); }//end if
			if (con !=null) { con.close(); }//end if
			
		}//end finally
		return list;
		
	}//selectCategoryList
	

	
	// 로그인 시 이름 출력!!
		public String[] loginRun(LoginVO lVO) throws SQLException {
			String[] loginInfo = new String[2];
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs= null;
			try {
				//연결
				con =getConn();
				//쿼리문 생성
				StringBuilder selectLogin = new StringBuilder();
				selectLogin.append("select user_name , suspend_flag ")
				.append(" from id_info ")
				.append(" where user_id=? ")
				.append(" and password= ?");
				
				
				pstmt = con.prepareStatement(selectLogin.toString());
				//바인드 값 변수 얻기
				pstmt.setString(1, lVO.getId());
				pstmt.setString(2, lVO.getPass());
				//쿼리 실행
				rs= pstmt.executeQuery();
				if(rs.next()){
					loginInfo[0] = rs.getString("user_name");
					loginInfo[1] =rs.getString("suspend_flag");
				}//end while
				
			}finally {
				if(con!=null) {con.close();}//end if
				if(pstmt!=null) {pstmt.close();}//end if
				if(rs!=null) {rs.close();}//end if
			}//end finally
			return loginInfo;
		}//loginRun
	


	public static void main(String[] args) {

	}//main

}//class
