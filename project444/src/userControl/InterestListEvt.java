package userControl;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel; 

import userDAO.UserDAO;
import userFileRecieve.UserFileRecieve;
import userRun.RunMarketMain;
import userVO.AllListVO;
import userVO.InterestListVO;
import userVO.MarketDetailVO;
import userView.InterestList;
import userView.MarketDetailBuyer;
import userView.MarketMain;

public class InterestListEvt extends MouseAdapter implements ActionListener{
	private InterestList il;
	private MarketMain mm;
	public static final int DOUBLE_CLICK=2;
	private String productCode="";
	private String loc_code="";
	private String classFlag="I"; ////userDAO에서 selectProDetail method 사용할 때 구분용 플래그
	DecimalFormat df=new DecimalFormat ("#,###,###");
	
	public InterestListEvt(InterestList il) {
		this.il =il;
		try {
			setInterestList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//InterestListEvt
	
	public void setInterestList() throws SQLException {
		
		/////////////////// 서버에 접속해서 파일 받기 /////////////////
		UserFileRecieve uFR = UserFileRecieve.getInstance();
		try {
			uFR.getImgFile();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(il, "파일서버에 접속 실패");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(il, "파일 로드 실패");
			e.printStackTrace();
		}
		///////////////////////////////////////////////////
		
		DefaultTableModel dtm=il.getDtmInterest();
		
		//JTable의 레코드 초기화
		dtm.setRowCount(0);
		
		Object[] rowData= null;
		//JTable 넣을 데이터 // object는 자바의 모든 값을 다 담을 수 있다.

		
		//DBMS에서 조회
		UserDAO uDAO =UserDAO.getInstance();
		List<AllListVO> list=uDAO.selectInterestList();


//		if(list.isEmpty()) { 
//			JOptionPane.showMessageDialog(il, "관심상품이 존재하지 않습니다. ");
//			
//			
//		}
		AllListVO alv=null;
		for(int i=0; i<list.size(); i++) {
			alv=list.get(i);
			//조회 결과로 JTable 레코드에 들어갈 데이터를 생성하고
			rowData=new Object[7];
			//배열에 값 할당
			if (new File(RunMarketMain.imgPath + "/" + alv.getImage()).exists()) {

				rowData[0] = (new ImageIcon(new ImageIcon(RunMarketMain.imgPath + "/" + alv.getImage()).getImage()
						.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			} else {
				rowData[0] =(new ImageIcon(new ImageIcon(RunMarketMain.imgPath+"/"+"default.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			}
			rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
			rowData[2]=df.format(alv.getPrice());
			rowData[3]=alv.getSellerID();
			rowData[4]=alv.getLoc_code();
			rowData[5]=alv.getUpload_date();
			rowData[6]=alv.getCategory();
			//dtm에 추가
			dtm.addRow(rowData);
			loc_code = alv.getLoc_code();
		}//end for		
	
	}//setInterestList
	
	public void deleteInterestList() throws SQLException {
		if(JOptionPane.showConfirmDialog(il,"관심목록에서 삭제 하시겠습니까?","관심목록 삭제",JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE)==0) {
		UserDAO uDAO = UserDAO.getInstance();
		InterestListVO irVO = new InterestListVO(productCode,RunMarketMain.userId);
		int flag=uDAO.insertInterest(irVO,false);
		if(flag==1) {
			
				JOptionPane.showMessageDialog(il, "해당 제품이 삭제되었습니다.");
		}else {
			JOptionPane.showMessageDialog(il,"삭제하실 제품을 선택해주세요." );
		}//end else
		setInterestList();
		
		}//end if
	}//deleteInterestList
	
	 

	@Override
	public void mouseClicked(MouseEvent me) {
		
		
		
		if(me.getSource()==il.getJtInterest()) {
				productCode =il.getJtInterest().getValueAt(il.getJtInterest().getSelectedRow(), 1).toString();
				productCode=productCode.substring(productCode.indexOf("(")+1,productCode.indexOf(")"));
		if(me.getClickCount()==DOUBLE_CLICK) {
			try {
			UserDAO uDAO = UserDAO.getInstance();
			MarketDetailVO mdVO;
				mdVO = uDAO.selectProDetail(productCode, classFlag);
				if(mdVO != null) {
					new MarketDetailBuyer(mm, mdVO, RunMarketMain.userId,this);
					
				}else {
					JOptionPane.showMessageDialog(il, "판매중인 상품이 아닙니다.");
					try {
						setInterestList();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		}//end if 
		}//end if
		}//mouseClicked
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==il.getJbtDelete()) {
			try {
				deleteInterestList();
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
		}//end if
	}//actionPerformed
}//class
