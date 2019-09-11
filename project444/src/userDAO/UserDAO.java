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
import userVO.ForgotIdVO;
import userVO.ForgotPwVO;
import userVO.LoginVO;
import userVO.MarketDetailVO;
import userVO.PersonalInformVO;
import userVO.SignUpVO;

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
	
//	public List<AllListVO> selectAllList() throws SQLException {
//		List<AllListVO> list=new ArrayList<AllListVO>();
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		try {
//		//커넥션 얻기
//		con=getConn();
//		
//		
//
//	     //3.쿼리문 생성객체 얻기 : lunch테이블에서 이름 코드, 가격, 입력일을 가장 최근에 입력된 것 부터 조회
//        StringBuilder selectAll = new StringBuilder();
//        selectAll
//        
//
//        .append(" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, PRODUCT_CODE, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, "
//        		+"CATEGORY_CODE, USER_ID, PRICE")
//        .append(" from PRODUCT ")
//        .append(" where all_flag='P' ");  
////        .append(" order by input_date desc ");
//		 
//		pstmt=con.prepareStatement(selectAll.toString());
//				 
//		//4. 바인드변수에 값 넣기
//		//5. 쿼리 수행 후 결과 얻기
//		rs=pstmt.executeQuery();
//		AllListVO alv=null;
//		
//			while(rs.next()) {
//				alv=new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
//						rs.getString("PRODUCT_NAME"), rs.getString("PRODUCT_CODE"), rs.getString("inputDate"),  
//						rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"),rs.getInt("PRICE"));
//				list.add(alv);
//			}//end while
//		} finally {
//		//6. 연결끊기
//		if (rs !=null) { rs.close(); }//end if
//		if (pstmt !=null) { pstmt.close(); }//end if
//		if (con !=null) { con.close(); }//end if
//		
//		}//end finally
//		return list;
//		
//	}//selectAllList
	
	
	
	public List<AllListVO> selectAllList( int jcbAreaIndex, int jcbCateIndex, String jtfText) throws SQLException {
		List<AllListVO> list=new ArrayList<AllListVO>();
		
		
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String area ="";
		String category ="";
		String productName ="";
		
		if(jcbAreaIndex != 0) {
			area = " and user_id in ( select user_id  from id_info where loc_code ='"+df.format(jcbAreaIndex)+"') ";
		}
		if(jcbCateIndex != 0) {
			category = " and category_code ='"+df.format(jcbCateIndex)+"' ";
		}
		if(!jtfText.isEmpty()) {
			productName = " and product_name like '%"+jtfText+"%' ";
		}
		
		
		
		try {
			//커넥션 얻기
			con=getConn();
			

			
			
			//3.쿼리문 생성객체 얻기 : lunch테이블에서 이름 코드, 가격, 입력일을 가장 최근에 입력된 것 부터 조회
			StringBuilder selectArea = new StringBuilder();
			selectArea			
			.append(" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,"
					+ "CATEGORY_CODE, USER_ID, PRICE ")
			.append(" from PRODUCT ")
			.append(" where all_flag ='P' "+area+category+productName )					
	        .append(" order by inputDate desc "); 
			
			
//			select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, USER_ID, PRICE
//			from PRODUCT
//			where all_flag ='P'  and user_id in ( select user_id  from id_info where loc_code ='07') and category_code ='04'  and product_name like '%은과%'
//			order by inputDate desc ;
			
			
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
						rs.getString("PRODUCT_NAME"), df.format(jcbAreaIndex), rs.getString("inputDate"),  
						rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"),rs.getInt("PRICE"));
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
	
	
	
	/**
	 * 상품 디테일창으로 정보 넘기는 method
	 * @param productCode
	 * @param loc_code
	 * @return
	 * @throws SQLException
	 */
	public MarketDetailVO selectProDetail(String productCode, String loc_code) throws SQLException {
		
		MarketDetailVO mdVO=null;

		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try { 
			//커넥션 얻기
			con=getConn();		
				  
				
				//3.쿼리문 생성객체 얻기 : lunch테이블에서 이름 코드, 가격, 입력일을 가장 최근에 입력된 것 부터 조회
				StringBuilder selectDetail = new StringBuilder();
				selectDetail			
				.append(" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, PRICE, INFO ")
				.append(" from PRODUCT ")
				.append(" where all_flag ='P' and PRODUCT_CODE=? and user_id in ( select user_id  from id_info where LOC_CODE =?) ")	//물음표랑 ''랑 같이쓰면 안됨.						
				.append(" order by inputDate desc "); 
				
								
	
				
				pstmt=con.prepareStatement(selectDetail.toString());
				
				//4. 바인드변수에 값 넣기
				pstmt.setString(1, productCode);
				pstmt.setString(2, loc_code);
				//5. 쿼리 수행 후 결과 얻기
				rs=pstmt.executeQuery(); 
				
				if(rs.next()) {
					mdVO=new MarketDetailVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
							rs.getString("PRODUCT_NAME"), df.format(loc_code), rs.getString("inputDate"),  
							rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"),rs.getString("INFO"),rs.getInt("PRICE"));
				}//end if
			} finally {
				//6. 연결끊기
				if (rs !=null) { rs.close(); }//end if
				if (pstmt !=null) { pstmt.close(); }//end if
				if (con !=null) { con.close(); }//end if
				
			}//end finally
		return mdVO;
	}//selectProDetail
	

	
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
//중복된 아이디 체크 
		public String IdCheck(String id) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs =null;
			String checkId ="";
			try {
				con=getConn();
				StringBuilder selectID = new StringBuilder();
				selectID
				.append(" select user_id ")
				.append(" from id_info ")
				.append("where user_id =?");
			
				pstmt=con.prepareStatement(selectID.toString());
				
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					checkId=rs.getString(1);
				}//end while
			}finally {
				if(con!=null) {con.close();}//end if
				if(pstmt!=null) {pstmt.close();}//end if
				if(rs!=null) {rs.close();}//end if
			}//end finally
			
			return checkId;
		}//IdCheck		
		
		
//회원가입
public int insertLogin(SignUpVO suVO) throws SQLException {
int insertflag =0;

Connection con= null;
PreparedStatement pstmt=null;
try {
con=getConn();
StringBuilder insertResigter = new StringBuilder();
insertResigter
.append("insert into id_info(user_id,password, user_name, gender, phone, answer,  loc_code, hint_code)values(?,?,?,?,?,?,?,?)");

pstmt=con.prepareStatement(insertResigter.toString());
pstmt.setString(1,suVO.getId());
pstmt.setString(2,suVO.getPw());
pstmt.setString(3,suVO.getName());
pstmt.setString(4,suVO.getGender());
pstmt.setString(5,suVO.getPhone());
pstmt.setString(6,suVO.getPwAnswer());
pstmt.setString(7,suVO.getLoc());
pstmt.setString(8,suVO.getPwHint());

insertflag = pstmt.executeUpdate();


}finally{
if(con!=null) {con.close();}//end if
if(pstmt!=null) {pstmt.close();}//end if
}//end finally



return insertflag;

}//updateRegister


//분실 아이디찾기
public String selectId(ForgotIdVO fiVO) throws SQLException {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String id="";
	try {
		con = getConn();
		StringBuilder forgotId = new StringBuilder();
		forgotId
		.append("select user_id from id_info where user_name=? and phone=?");
		
		pstmt=con.prepareStatement(forgotId.toString());
		
		pstmt.setString(1, fiVO.getName());
		pstmt.setString(2, fiVO.getPhone());
		
		rs= pstmt.executeQuery();
		
		while(rs.next()) {
			id=rs.getString(1);
		}
		
	}finally {
		if(con!=null) {con.close();}//end if
		if(pstmt!=null) {pstmt.close();}//end if
		if(rs!=null) {rs.close();}//end if
		
	}
	return id;
}//selectId
public boolean updateForgetPw(ForgotPwVO fpVO,String uuid) throws SQLException {
	boolean updateFlag = false;
	Connection con = null;
	PreparedStatement pstmt = null;
	try {
		con = getConn();
		StringBuilder updatePw = new StringBuilder();
		updatePw
		.append("Update id_info set password='")
		.append(uuid)
		.append("' where user_id =? and answer=? and hint_code=?");
		
		pstmt=con.prepareStatement(updatePw.toString());
		
		pstmt.setString(1, fpVO.getId());
		pstmt.setString(2, fpVO.getPwAnswer());
		pstmt.setString(3, fpVO.getPwHint());
		
		updateFlag=pstmt.executeUpdate()==1;
		
	}finally {
		if(con!=null) {con.close();}//end if
		if(pstmt!=null) {pstmt.close();}//end if
	}//end finally
	
	return updateFlag;
}//updateForgetPw
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public PersonalInformVO selectPersonalInfom(String id) throws SQLException {
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		PersonalInformVO piVO=null ;
		try {
			con =getConn();
			StringBuilder selectAllInfo = new StringBuilder();
			selectAllInfo
			.append("select user_id,password,user_name,gender,phone,answer,loc_code,hint_code from id_info where user_id=?");
			pstmt= con.prepareStatement(selectAllInfo.toString());
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
		piVO = new PersonalInformVO(rs.getString("user_id"),rs.getString("password"),rs.getString("user_name"),
				rs.getString("gender"),rs.getString("phone"),rs.getString("answer"),rs.getString("loc_code"),rs.getString("hint_code"));
			}//end while
		}finally {
			if(con!=null) {con.close();}//end if
			if(pstmt!=null) {pstmt.close();}//end if
			if(rs!=null) {rs.close();}//end if
		}//end finally
		return piVO;
	}//selectPersonalInfom


public String selectPw(String pw) throws SQLException {
String curPw ="";
Connection con = null;
PreparedStatement pstmt =null;
ResultSet rs = null;
try {
con=getConn();
StringBuilder selectPw = new StringBuilder();
selectPw
.append("select password from id_info where password=?");

pstmt = con.prepareStatement(selectPw.toString());
pstmt.setString(1, pw);
rs = pstmt.executeQuery();
while(rs.next()) {
curPw=rs.getString(1);
}//end while
}finally {
if(con!=null) {con.close();}//end if
if(pstmt!=null) {pstmt.close();}//end if
if(rs!=null) {rs.close();}//end if
}//end finally
return curPw;
}//selectPw

	public boolean updatePw(String id,String pw) throws SQLException {
		boolean updateFlag = false;
		
		Connection con =null;
		PreparedStatement pstmt = null;
		try {
		con=getConn();
		StringBuilder updatePw = new StringBuilder();
		updatePw
		.append("update id_info ")
		.append(" set password =? ")
		.append(" where user_id =? ");
		pstmt = con.prepareStatement(updatePw.toString());
		pstmt.setString(1, pw);
		pstmt.setString(2, id);
		
		updateFlag =pstmt.executeUpdate()==1;
		
		}finally {
		if(con!=null) {con.close();}//end if
		if(pstmt!=null) {pstmt.close();}//end if
		}//end finally
		return updateFlag;
	}//updatePw







////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//	public static void main(String[] args) {
//
//	}//main

}//class
