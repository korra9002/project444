package userControl;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import userRun.RunMarketMain;
import userView.InsertProduct;

public class InsertProductEvt implements ActionListener {
	
	private InsertProduct ip;
	private RunMarketMain rmm;
	private String id;
	
	public InsertProductEvt(InsertProduct ip, RunMarketMain rmm) {
		this.ip=ip;
		this.rmm=rmm;
		id=RunMarketMain.userId;
	}//InsertProductEvt
	
	public void addImg() {
		
		FileDialog fd=new FileDialog(rmm);
		
		FileDialog fdOpen=new FileDialog(fd,"ㅎㅇㅎㅇ",FileDialog.LOAD);
		fdOpen.setVisible(true);
		
		String path=fdOpen.getDirectory();
		String file=fdOpen.getFile();
		if (file != null) { //변경할 파일을 선택
			//확장자 확인
			String flagExt="jpg,gif,png,bmp";
			String ext=file.substring(file.lastIndexOf(".")+1);
			
			if ( !flagExt.contains(ext.toLowerCase()) ) {
				JOptionPane.showMessageDialog(ip, file +
				"은 이미지가 아니거나 사용할 수 없는 이미지 입니다.");
				return;
			}//end if
			
			//이미지를 미리보기 라벨에 설정
//			JLabel imgPreview=ip.j
			
			
			
			
		}//end if
		
	}//addImg
	
	public void uploadPost() {

		
//		//입력된 값을 가지고 VO에 설정하고
//		String img=new File(laf.getJlImg().getIcon().toString()).getName();
//		String name=laf.getJtfName().getText().trim();
//		String strongPoint=laf.getJtaStrongPoint().getText().trim();
//		int price=0;
//		try {
//			price=Integer.parseInt(laf.getJtfPrice().getText().trim());
//			
//			LunchAddVO laVO=new LunchAddVO(img, name, strongPoint, price);
//			if( !imgFlag ) {
//				JOptionPane.showMessageDialog(laf, "이미지를 선택해주세요.");
//				return;
//			}//end if
//			//이미지 업로드
//			uploadImg();
//			//DBMS에 추가
//			AdminDAO aDAO=AdminDAO.getInstance();
//			aDAO.insertLunch(laVO);
//			JOptionPane.showMessageDialog(laf, "도시락 정보를 추가하였습니다.");
//			//부모창의 도시락 리스트를 갱신하고
//			lme.setLunchList();
//			//다음 도시락이 추가될 때 이미지를 다시 선택할 수 있도록 기준 변수를 변경한다.
//			imgFlag=false;
//			
//		}catch(NumberFormatException nfe) {
//			JOptionPane.showMessageDialog(laf, "가격은 정수 형태로만 입력해주세요.");
//		}catch(IOException ie) {
//			JOptionPane.showMessageDialog(laf, "이미지 선택 중 문제 발생");
//		}catch(SQLException se) {
//			JOptionPane.showMessageDialog(laf, "DBMS에서 문제 발생");
//			se.printStackTrace();
//		}//end catch
		
		
	}//uploadPost
	
	public void close() {
//		ip.dispose();
	}//close
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource()==ip.getJbtSelectImg()) {
				 addImg();
			}//end if
			
			if(ae.getSource()==ip.getJbtOkay()) {
				uploadPost();
			}
			
			if(ae.getSource()==ip.getJbtCancel()) {		
				close();
			}
		}//actionPerformed
	}//class

	
	

