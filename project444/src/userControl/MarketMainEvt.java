package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
 
import userDAO.UserDAO;
import userVO.AllListVO;
import userVO.MarketDetailVO;
import userView.MarketDetailBuyer;
import userView.MarketDetailSeller;
import userView.MarketMain;

public class MarketMainEvt extends MouseAdapter implements ActionListener{
	public static final int DOUBLE_CLICK=2;
	
	private MarketMain mm;
	private String id;
	
	
	
	public MarketMainEvt(MarketMain mm, String id) throws SQLException {
		this.mm=mm;	
		this.id=id;
		setAllList();
	}//MarketMainEvt
	
//	public void setAllList() throws SQLException {
//		DefaultTableModel dtm=mm.getDtmProductList();
//		
//		//JTable税 傾坪球 段奄鉢
//		dtm.setRowCount(0);
//		
//		Object[] rowData= null;
//		//JTable 隔聖 汽戚斗 // object澗 切郊税 乞窮 葵聖 陥 眼聖 呪 赤陥.
//		
//		//DBMS拭辞 繕噺
//		UserDAO uDAO =UserDAO.getInstance();
//			List<AllListVO> list=uDAO.selectAllList();
//				if(list.isEmpty()) { 
//					JOptionPane.showMessageDialog(mm, "伊事繕闇拭 限澗 雌念戚 蒸柔艦陥.");
//				}
//			AllListVO alv=null;
//			for(int i=0; i<list.size(); i++) {
//				alv=list.get(i);
//				//繕噺 衣引稽 JTable 傾坪球拭 級嬢哀 汽戚斗研 持失馬壱
//				rowData=new Object[7];
//				//壕伸拭 葵 拝雁 		String[] productColumn= {"戚耕走", "薦念誤", "走蝕", "亜維", "獣娃", "朝砺壱軒", "毒古切 ID" };
//				rowData[0]=alv.getImage();
//				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
//				rowData[2]=alv.getLoc_code();
//				rowData[3]=alv.getPrice();
//				rowData[4]=alv.getUpload_date();
//				rowData[5]=alv.getCategory();
//				rowData[6]=alv.getSellerID();
//			//dtm拭 蓄亜
//			dtm.addRow(rowData);		
//		}//end for		
//	}//setAllList
	
		
		
		public void setAllList() throws SQLException {
			
//			if (jcbAreaIndex==0 && jcbCateIndex==0 && jtfText==null) {
//				setAllList();
//			}//end if
			DefaultTableModel dtm=mm.getDtmProductList();
			
			//JTable税 傾坪球 段奄鉢
			dtm.setRowCount(0);
			
			Object[] rowData= null;
			//JTable 隔聖 汽戚斗 // object澗 切郊税 乞窮 葵聖 陥 眼聖 呪 赤陥.
		
//			int area =0;
//			try {
//			area =  mm.getJcbArea().getSelectedIndex();
//			}catch (Exception e) {
//           e.printStackTrace();
// 	 	
//			}	
//			int category =0;
//			try {
//				category =  mm.getJcbCategory().getSelectedIndex();
//			}catch (Exception e) {
//				e.printStackTrace();
//				
//			}	
//			
//			String proName ="";
//			try {
//				proName =  mm.getJtfSearch().getText().trim();
//			}catch (Exception e) {
//				e.printStackTrace();
//				
//			}	
			
			//DBMS拭辞 繕噺
			UserDAO uDAO =UserDAO.getInstance();
		List<AllListVO> list=uDAO.selectAllList( mm.getJcbArea().getSelectedIndex(), mm.getJcbCategory().getSelectedIndex(),mm.getJtfSearch().getText().trim());
		//ばばばばばばばばばばばgetselectedText焼艦壱 getText()脊艦陥ばばばばばばば袴軒拭 隔西獣陥ば
	//		List<AllListVO> list=uDAO.selectAllList(0, 0,"");
			if(list.isEmpty()) { 
				JOptionPane.showMessageDialog(mm, "伊事繕闇拭 限澗 雌念戚 蒸柔艦陥. ");
			}
			AllListVO alv=null;
			for(int i=0; i<list.size(); i++) {
				alv=list.get(i);
				//繕噺 衣引稽 JTable 傾坪球拭 級嬢哀 汽戚斗研 持失馬壱
				rowData=new Object[7];
				//壕伸拭 葵 拝雁
				rowData[0]=alv.getImage();
				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
				rowData[2]=alv.getLoc_code();
				rowData[3]=alv.getPrice();
				rowData[4]=alv.getUpload_date();
				rowData[5]=alv.getCategory();
				rowData[6]=alv.getSellerID();
				//dtm拭 蓄亜
				dtm.addRow(rowData);		
			}//end for		
		}//setAllList
		
		
		public void setListByID() throws SQLException {
			
			DefaultTableModel dtm=mm.getDtmProductList();
			
			//JTable税 傾坪球 段奄鉢
			dtm.setRowCount(0);
			
			Object[] rowData= null;
			//JTable 隔聖 汽戚斗 // object澗 切郊税 乞窮 葵聖 陥 眼聖 呪 赤陥.
			
			
			//DBMS拭辞 繕噺
			UserDAO uDAO =UserDAO.getInstance();
			List<AllListVO> list=uDAO.selectListByID( mm.getJcbArea().getSelectedIndex(), mm.getJcbCategory().getSelectedIndex(),mm.getJtfSearch().getText().trim());
			if(list.isEmpty()) { 
				JOptionPane.showMessageDialog(mm, "伊事繕闇拭 限澗 雌念戚 蒸柔艦陥.");
			}
			AllListVO alv=null;
			for(int i=0; i<list.size(); i++) {
				alv=list.get(i);
				//繕噺 衣引稽 JTable 傾坪球拭 級嬢哀 汽戚斗研 持失馬壱
				rowData=new Object[7];
				//壕伸拭 葵 拝雁
				rowData[0]=alv.getImage();
				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
				rowData[2]=alv.getLoc_code();
				rowData[3]=alv.getPrice();
				rowData[4]=alv.getUpload_date();
				rowData[5]=alv.getCategory();
				rowData[6]=alv.getSellerID();
				//dtm拭 蓄亜
				dtm.addRow(rowData);		
			}//end for		
		}//setListByID 
		
		
		public void setRefresh() throws SQLException {
			

			DefaultTableModel dtm=mm.getDtmProductList();
			
			//JTable税 傾坪球 段奄鉢
			dtm.setRowCount(0);
			
			Object[] rowData= null;
			//JTable 隔聖 汽戚斗 // object澗 切郊税 乞窮 葵聖 陥 眼聖 呪 赤陥.
	
			
			//DBMS拭辞 繕噺
			UserDAO uDAO =UserDAO.getInstance();
			List<AllListVO> list=uDAO.selectRefresh();
			if(list.isEmpty()) { 
				JOptionPane.showMessageDialog(mm, "伊事繕闇拭 限澗 雌念戚 蒸柔艦陥.");
			}
			AllListVO alv=null;
			for(int i=0; i<list.size(); i++) {
				alv=list.get(i);
				//繕噺 衣引稽 JTable 傾坪球拭 級嬢哀 汽戚斗研 持失馬壱
				rowData=new Object[7];
				//壕伸拭 葵 拝雁
				rowData[0]=alv.getImage();
				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
				rowData[2]=alv.getLoc_code();
				rowData[3]=alv.getPrice();
				rowData[4]=alv.getUpload_date();
				rowData[5]=alv.getCategory();
				rowData[6]=alv.getSellerID();
				//dtm拭 蓄亜
				dtm.addRow(rowData);		
			}//end for		
		}//setRefresh 
		
		
		public void setListRecent() throws SQLException {
			
			
			DefaultTableModel dtm=mm.getDtmProductList();
			
			//JTable税 傾坪球 段奄鉢
			dtm.setRowCount(0);
			
			Object[] rowData= null;
			//JTable 隔聖 汽戚斗 // object澗 切郊税 乞窮 葵聖 陥 眼聖 呪 赤陥.
			
			
			//DBMS拭辞 繕噺
			UserDAO uDAO =UserDAO.getInstance();
			List<AllListVO> list=uDAO.selectListRecent( mm.getJcbArea().getSelectedIndex(), mm.getJcbCategory().getSelectedIndex(),mm.getJtfSearch().getText().trim());
			if(list.isEmpty()) { 
				JOptionPane.showMessageDialog(mm, "伊事繕闇拭 限澗 雌念戚 蒸柔艦陥.");
			}
			AllListVO alv=null;
			for(int i=0; i<list.size(); i++) {
				alv=list.get(i);
				//繕噺 衣引稽 JTable 傾坪球拭 級嬢哀 汽戚斗研 持失馬壱
				rowData=new Object[7];
				//壕伸拭 葵 拝雁
				rowData[0]=alv.getImage();
				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
				rowData[2]=alv.getLoc_code();
				rowData[3]=alv.getPrice();
				rowData[4]=alv.getUpload_date();
				rowData[5]=alv.getCategory();
				rowData[6]=alv.getSellerID();
				//dtm拭 蓄亜
				dtm.addRow(rowData);		
			}//end for		
		}//setListRecent 
		
		
		public void setListPrice() throws SQLException {
			
			
			DefaultTableModel dtm=mm.getDtmProductList();
			
			//JTable税 傾坪球 段奄鉢
			dtm.setRowCount(0);
			
			Object[] rowData= null;
			//JTable 隔聖 汽戚斗 // object澗 切郊税 乞窮 葵聖 陥 眼聖 呪 赤陥.
			
			
			//DBMS拭辞 繕噺
			UserDAO uDAO =UserDAO.getInstance();
			List<AllListVO> list=uDAO.selectListPrice( mm.getJcbArea().getSelectedIndex(), mm.getJcbCategory().getSelectedIndex(),mm.getJtfSearch().getText().trim());
			if(list.isEmpty()) { 
				JOptionPane.showMessageDialog(mm, "伊事繕闇拭 限澗 雌念戚 蒸柔艦陥.");
			}
			AllListVO alv=null;
			for(int i=0; i<list.size(); i++) {
				alv=list.get(i);
				//繕噺 衣引稽 JTable 傾坪球拭 級嬢哀 汽戚斗研 持失馬壱
				rowData=new Object[7];
				//壕伸拭 葵 拝雁
				rowData[0]=alv.getImage();
				rowData[1]=alv.getProductName()+"("+alv.getProductCode()+")";
				rowData[2]=alv.getLoc_code();
				rowData[3]=alv.getPrice();
				rowData[4]=alv.getUpload_date();
				rowData[5]=alv.getCategory();
				rowData[6]=alv.getSellerID();
				//dtm拭 蓄亜
				dtm.addRow(rowData);		
			}//end for		
		}//setListPrice 
		
		
		
		
		public void productDetail() {		
			
			//識澱廃 楳税 坪球研 亜閃人辞 雌室 舛左研 繕噺
			  
			 
			JTable jtProductList=mm.getJtProductList();
			String temp=(String) jtProductList.getValueAt(jtProductList.getSelectedRow(), 1);
			String loc_code=(String) jtProductList.getValueAt(jtProductList.getSelectedRow(), 2);
			String productCode=temp.substring(temp.lastIndexOf("(")+1, temp.lastIndexOf(")"));
			
			
			
			//DBMS拭辞 繕噺
			UserDAO uDAO =UserDAO.getInstance();

			try {
				MarketDetailVO mdVO=uDAO.selectProDetail(productCode, loc_code);
				
				
				//薄仙 羨紗廃 焼戚巨人 匂什特 毒古切 焼戚巨人 旭生檎 MarketDetailBuyer
				//陥牽陥檎 MarketDetailSeller
				if (mdVO.getSellerID()==id) {
					new MarketDetailSeller(mm, mdVO, id);
				} else {
					new MarketDetailBuyer(mm, mdVO, id);
				}//end else
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
	
		}//productDetail
		
	

	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mm.getJcbArea() || ae.getSource() == mm.getJcbCategory() ) {
			try {
				setAllList();
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
		}//end if				

		if (mm.getJrbId().isSelected() && ae.getSource() == mm.getJbSearch()) {
			try {
				setListByID();
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
		} else if(mm.getJrbSubject().isSelected() && ae.getSource() == mm.getJbSearch()) {
			try { 
				setAllList();
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
		}//end if
		
		if (ae.getSource() == mm.getJbRecent()) {
				try {
					setListRecent();
				} catch (SQLException e) {
					e.printStackTrace();
				}//end catch
		}//end if
		if (ae.getSource() == mm.getJbPrice()) {
				try {
					setListPrice();
				} catch (SQLException e) {
					e.printStackTrace();
				}//end catch
		}//end if
		if (ae.getSource() == mm.getJbRefresh()) {			
			try {
				setRefresh();
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
		}//end if
		
	}//actionPerformed

	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getClickCount() == 2) {//希鷺適遣
			if(me.getSource() == mm.getJtProductList()) {
				productDetail();
			}//end if			
		}//end if
		

	
	}

}//class
