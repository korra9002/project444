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
	
//	public List<AllListVO> selectAllList() throws SQLException {
//		List<AllListVO> list=new ArrayList<AllListVO>();
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		try {
//		//Ŀ�ؼ� ���
//		con=getConn();
//		
//		
//
//	     //3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
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
//		//4. ���ε庯���� �� �ֱ�
//		//5. ���� ���� �� ��� ���
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
//		//6. �������
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
			//Ŀ�ؼ� ���
			con=getConn();
			

			
			
			//3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
			StringBuilder selectArea = new StringBuilder();
			selectArea			
			.append(" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,"
					+ "CATEGORY_CODE, USER_ID, PRICE ")
			.append(" from PRODUCT ")
			.append(" where all_flag ='P' "+area+category+productName )					
	        .append(" order by inputDate desc "); 
			
			
//			select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate,CATEGORY_CODE, USER_ID, PRICE
//			from PRODUCT
//			where all_flag ='P'  and user_id in ( select user_id  from id_info where loc_code ='07') and category_code ='04'  and product_name like '%����%'
//			order by inputDate desc ;
			
			
			/*
			 * select user_id,product_name from product where ( user_id in ( select user_id
			 * from id_info where loc_code ='12')) and all_flag ='P';
			 �̰���*/ 			

			
			pstmt=con.prepareStatement(selectArea.toString());
			 
			//4. ���ε庯���� �� �ֱ�
			//5. ���� ���� �� ��� ���
			rs=pstmt.executeQuery(); 
			AllListVO alv=null; 
			
			while(rs.next()) {
				alv=new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
						rs.getString("PRODUCT_NAME"), df.format(jcbAreaIndex), rs.getString("inputDate"),  
						rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"),rs.getInt("PRICE"));
				list.add(alv);
			}//end while
		} finally {
			//6. �������
			if (rs !=null) { rs.close(); }//end if
			if (pstmt !=null) { pstmt.close(); }//end if
			if (con !=null) { con.close(); }//end if
			
		}//end finally
		return list;
		
	}//selectAreaList
	
//	public List<AllListVO> selectCategoryList( int jcbAreaIndex, int jcbCateIndex) throws SQLException {
//		List<AllListVO> list=new ArrayList<AllListVO>();
//				
//		if(jcbCateIndex==0 ) {
//			selectAllList();
//		} else { 
//		
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		try {
//			//Ŀ�ؼ� ���
//			con=getConn();
//			
//			
//				
//				
//				//3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
//				StringBuilder selectArea = new StringBuilder();
//				selectArea			
//				.append(" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, PRICE ")
//				.append(" from PRODUCT")
//				.append(" where CATEGORY_CODE='"+df.format(jcbCateIndex)+"'"+ "and all_flag ='P' ")							
//				.append(" order by inputDate desc "); 
//				
//				
//				/*
//				 * select user_id,product_name from product where ( user_id in ( select user_id
//				 * from id_info where loc_code ='12')) and all_flag ='P';
//				 �̰���*/ 			
//				
//				
//				pstmt=con.prepareStatement(selectArea.toString());
//				
//				//4. ���ε庯���� �� �ֱ�
//				//5. ���� ���� �� ��� ���
//				rs=pstmt.executeQuery(); 
//				AllListVO alv=null; 
//				
//				while(rs.next()) {
//					alv=new AllListVO(rs.getString("PRODUCT_CODE"), rs.getString("IMG_FILE"),
//							rs.getString("PRODUCT_NAME"), rs.getString("PRODUCT_CODE"), rs.getString("inputDate"),  
//							rs.getString("CATEGORY_CODE"), rs.getString("USER_ID"),rs.getInt("PRICE"));
//					list.add(alv);
//				}//end while
//			} finally {
//				//6. �������
//				if (rs !=null) { rs.close(); }//end if
//				if (pstmt !=null) { pstmt.close(); }//end if
//				if (con !=null) { con.close(); }//end if
//				
//			}//end finally
//		}//else if
//		return list;
//	}//selectCategoryList
	

	
	// �α��� �� �̸� ���!!
		public String[] loginRun(LoginVO lVO) throws SQLException {
			String[] loginInfo = new String[2];
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs= null;
			try {
				//����
				con =getConn();
				//������ ����
				StringBuilder selectLogin = new StringBuilder();
				selectLogin.append("select user_name , suspend_flag ")
				.append(" from id_info ")
				.append(" where user_id=? ")
				.append(" and password= ?");
				
				
				pstmt = con.prepareStatement(selectLogin.toString());
				//���ε� �� ���� ���
				pstmt.setString(1, lVO.getId());
				pstmt.setString(2, lVO.getPass());
				//���� ����
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
//�ߺ��� ���̵� üũ 
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
		
		
//ȸ������
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


//�н� ���̵�ã��
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
	public String[] selectPersonalInfom(String id) throws SQLException {
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String[] setInfo = new String[4];
		try {
			con =getConn();
			StringBuilder selectAllInfo = new StringBuilder();
			selectAllInfo
			.append("select user_name,gender,phone,loc_code from id_info where user_id=?");
			pstmt= con.prepareStatement(selectAllInfo.toString());
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				setInfo[0]=rs.getString(1);
				setInfo[1]=rs.getString(2);
				setInfo[2]=rs.getString(3);
				setInfo[3]=rs.getString(4);
			}//end while
		}finally {
			if(con!=null) {con.close();}//end if
			if(pstmt!=null) {pstmt.close();}//end if
			if(rs!=null) {rs.close();}//end if
		}//end finally
		return setInfo;
	}//selectPersonalInfom

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public boolean updatePw() throws SQLException {
boolean updateFlag = false;

Connection con =null;
PreparedStatement pstmt = null;
try {

	con=getConn();

}finally {
if(con!=null) {con.close();}//end if
if(pstmt!=null) {pstmt.close();}//end if
}//end finally

return updateFlag;
}



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//	public static void main(String[] args) {
//
//	}//main

}//class
