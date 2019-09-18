package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import userDAO.UserDAO;
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
	public InterestListEvt(InterestList il) {
		this.il =il;
		try {
			setInterestList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//InterestListEvt
	
	public void setInterestList() throws SQLException {
		DefaultTableModel dtm=il.getDtmInterest();
		
		//JTable�� ���ڵ� �ʱ�ȭ
		dtm.setRowCount(0);
		
		Object[] rowData= null;
		//JTable ���� ������ // object�� �ڹ��� ��� ���� �� ���� �� �ִ�.

		
		//DBMS���� ��ȸ
		UserDAO uDAO =UserDAO.getInstance();
		List<AllListVO> list=uDAO.selectInterestList();


		if(list.isEmpty()) { 
			JOptionPane.showMessageDialog(il, "���ɻ�ǰ�� �������� �ʽ��ϴ�. ");
			
			
		}
		AllListVO alv=null;
		for(int i=0; i<list.size(); i++) {
			alv=list.get(i);
			//��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
			rowData=new Object[7];
			//�迭�� �� �Ҵ�
			rowData[0]=alv.getImage();
			rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
			rowData[2]=alv.getPrice();
			rowData[3]=alv.getSellerID();
			rowData[4]=alv.getLoc_code();
			rowData[5]=alv.getUpload_date();
			rowData[6]=alv.getCategory();
			//dtm�� �߰�
			dtm.addRow(rowData);
			loc_code = alv.getLoc_code();
		}//end for		
	
	}//setInterestList
	
	public void deleteInterestList() throws SQLException {
		UserDAO uDAO = UserDAO.getInstance();
		InterestListVO irVO = new InterestListVO(productCode,RunMarketMain.userId);
		int flag=uDAO.insertInterest(irVO,false);
		if(flag==1) {
			JOptionPane.showMessageDialog(il, "�ش� ��ǰ�� �����Ǿ����ϴ�.");
		}else {
			JOptionPane.showMessageDialog(il,"�����Ͻ� ��ǰ�� �������ּ���." );
		}//end else
		setInterestList();
		
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
				mdVO = uDAO.selectProDetail(productCode, loc_code);
			new MarketDetailBuyer(mm, mdVO, RunMarketMain.userId);
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
