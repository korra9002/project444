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
import userVO.SignUpVO;

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
		
		

	     //3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
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
		
		DecimalFormat df=new DecimalFormat("00");
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			//Ŀ�ؼ� ���
			con=getConn();
			
			
			
			//3.������ ������ü ��� : lunch���̺��� �̸� �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
			StringBuilder selectArea = new StringBuilder();
			selectArea			
			.append(" select PRODUCT_CODE, IMG_FILE, PRODUCT_NAME, to_char(UPLOAD_DATE,'yyyy-mm-dd hh24:mi') inputDate, PRICE ")
			.append(" from PRODUCT")
			.append(" where user_id in ( select user_id  from id_info where loc_code ='"+df.format(jcbindex)+"')"+ "and all_flag ='P' ")							
	        .append(" order by inputDate desc "); 
//				all_flag='P' and l.loc_code='"+0+jcbindex+"'")
			
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
						rs.getString("PRODUCT_NAME"), df.format(jcbindex), rs.getString("inputDate"), rs.getInt("PRICE"));
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

		public int IdCheck(String id) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs =null;
		int CheckFlag =0;
			try {
				con=getConn();
				StringBuilder selectID = new StringBuilder();
				selectID
				.append(" select count(user_id) ")
				.append(" from id_info ")
				.append("where user_id =?");
			
				pstmt=con.prepareStatement(selectID.toString());
				
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					CheckFlag= rs.getInt(1);
				}//end while
			}finally {
				if(con!=null) {con.close();}//end if
				if(pstmt!=null) {pstmt.close();}//end if
				if(rs!=null) {rs.close();}//end if
			}//end finally
			
			return CheckFlag;
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





	public static void main(String[] args) {

	}//main

}//class
