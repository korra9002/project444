package userControl;

import java.awt.FileDialog;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import kr.co.sist.util.img.ImageResize;
import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.InsertProductVO;
import userView.InsertProduct;

public class InsertProductEvt extends MouseAdapter implements ActionListener {

	private InsertProduct ip;
	private RunMarketMain rmm;
	private String id;
	private File file;
	private String pathAndName ;

	private boolean imgFlag;
	private static boolean subjectFlag = true;
	private static boolean priceFlag = true;
	private static boolean detailFlag = true;

	public InsertProductEvt(InsertProduct ip, RunMarketMain rmm) {
		System.out.println("생성자!!!!!");
		this.ip = ip;
		this.rmm = rmm;
		id = RunMarketMain.userId;

		ip.getJtfSubject().addKeyListener(new KeyAdapter() {
//			boolean flag = true;
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (subjectFlag) {
						ip.getJtfSubject().setText("");
//						flag = false;
						subjectFlag = false;
	//					priceFlag=false;
	//					detailFlag=false;
				} // end if
			}// keyPressed
		});// KeyAdapter

		ip.getJtfPrice().addKeyListener(new KeyAdapter() {
//			boolean flag = true;

			@Override
			public void keyPressed(KeyEvent e) {
				if (priceFlag) {
					ip.getJtfPrice().setText("");
//					flag = false;
//					subjectFlag=false;
					priceFlag = false;
//					detailFlag=false;
				} // end if
			}// keyPressed
		});// KeyAdapter

		ip.getJtaExplain().addKeyListener(new KeyAdapter() {
//			boolean flag = true;

			@Override
			public void keyPressed(KeyEvent e) {
				if (detailFlag) {
					ip.getJtaExplain().setText("");
//					flag = false;
//					subjectFlag=false;
//					priceFlag=false;
					detailFlag = false;
				} // end if
			}// keyPressed
		});// KeyAdapter

	}// InsertProductEvt

	public void addImg() {

//		FileDialog fd = new FileDialog(rmm);

		FileDialog fdOpen = new FileDialog(rmm, "업로드 파일 선택", FileDialog.LOAD);
		fdOpen.setVisible(true);

		String path = fdOpen.getDirectory();
		String name = fdOpen.getFile();
		
//		System.out.println(path+file);
		if (path != null && name != null) {

			if ((name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png")) ) {
				try {
//				this.setTitle(file.getName());
//				t = new Test(file);
//				jlnotice.setText(String.format(" 범위  [ %d - %d ]", 1, t.getLastIndex()));
					// openFlag = true;
					pathAndName = path + name;
//					Image img = new ImageIcon(pathAndName).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
					ip.getJlbProductImg().setText("");
					ip.getJlbProductImg().setIcon(new ImageIcon(new ImageIcon(pathAndName).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
					file = new File(pathAndName);
					imgFlag = true;
					return;
				} catch (NullPointerException ne) {
					ne.printStackTrace();
				}

			} else {

				JOptionPane.showMessageDialog(rmm, "이미지 파일이 아닙니다.");
//				imgFlag = false;
				addImg();
			} // end else
				// 이미지를 미리보기 라벨에 설정
//			JLabel imgPreview = ip.getJlbProductImg();
//			imgPreview.setText("");
//			imgPreview.setIcon(new ImageIcon(path + file));

			// 이미지가 선택되었음을 설정한다.
		} // end if
//		System.out.println(imgFlag);

	}// addImg

	private void uploadImg(String newName) throws IOException {
		FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    OutputStream os = null;
//	    ServerSocket servsock = null;
	    Socket sock = null;

//		FileInputStream fis = null;
//		BufferedInputStream bis = null;
//		OutputStream os = null;
////		ServerSocket servsock = null;
//		Socket sock = null;
//		DataOutputStream dos = null;
//		try {
//			servsock = new ServerSocket(5001);

//			while (true) {
				System.out.println("Waiting...");
				try {
					sock = new Socket("211.63.89.159",5001);
					os = sock.getOutputStream();
					System.out.println("Accepted connection : " + sock);
					// send file
					File myFile = new File(pathAndName);
					byte[] mybytearray = new byte[(int) myFile.length()];
					fis = new FileInputStream(myFile);
					bis = new BufferedInputStream(fis);
					bis.read(mybytearray, 0, mybytearray.length);
					DataOutputStream dos = new DataOutputStream(os);
					dos.writeUTF(newName);
//					dos.flush();
					
					System.out.println("Sending " + pathAndName + "(" + mybytearray.length + " bytes)");
					os.write(mybytearray, 0, mybytearray.length);
					os.flush();
					
//					bis.close();///////////////////////////////////
					System.out.println("Done.");
					
//					dos.close();
				} finally {
						
			          if (bis != null) bis.close();
			          if (os != null) os.close();
			          if (sock!=null) sock.close();

				
			
			}
//		} finally {
//			
//		}

	}// uploadImg

//	private void uploadImg() throws IOException {
//		// 선택한 이미지를 이미지 폴더에 복사
//		File readFile = new File(ip.getJlbProductImg().getIcon().toString());// 선택한 이미지 경로
//		
//		byte[] readData = new byte[512]; // 읽어들인 정보를 저장할 배열
//		int len = 0;
//		
//		FileOutputStream fos = null;
//		FileInputStream fis = null;
//		
//		System.out.println(readFile.getName());
//		try {
//			fis = new FileInputStream(readFile);
//			if (readFile.exists()) {
//				File writeFile = new File("C:/dev/" + readFile.getName());
//				fos = new FileOutputStream(writeFile); // 이미지 폴더에 폭사
//				
//				while ((len = fis.read(readData)) != -1) {
//					
//					fos.write(readData, 0, len);
//				} // end while
//				
//				fos.flush();
//				// 이미지를 thumbnail image로 생성
//				ImageResize.resizeImage(writeFile.getAbsolutePath(), 120, 100);
//			} // end if
//			
//		} finally {
//			if (fos != null) {
//				fos.close();
//			} // end if
//			if (fis != null) {
//				fis.close();
//			} // end if
//		} // end finally
//		
//	}// uploadImg
	
	
	

	public void uploadPost() {

		Date today = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println(s.format(today));
		// 입력된 값을 가지고 VO에 설정하고
		String img = file.getName();
		String category = Integer.toString(ip.getJcbCategory().getSelectedIndex());
		String subject = ip.getJtfSubject().getText().trim(); // 제목은 무조건 상품명으로-
		String pDetail = ip.getJtaExplain().getText().trim();
		String newName = id+"_"+s.format(today)+"_"+img;
		int price = 0;
		try {
			price = Integer.parseInt(ip.getJtfPrice().getText().trim());
			System.out.println(img + " " + category + " " + subject + " " + pDetail + " " + price);
			InsertProductVO ipVO = new InsertProductVO(newName, category, subject, pDetail, price);

//			System.out.println(imgFlag);

			if (!imgFlag) {
				JOptionPane.showMessageDialog(ip, "이미지를 선택해주세요.");
				return;
			} // end if
				// 이미지 업로드
			
			System.out.println(newName+"새이름");
			uploadImg(newName);
			
			// DBMS에 추가
			UserDAO uDAO = UserDAO.getInstance();
			uDAO.insertProduct(ipVO, id);
			JOptionPane.showMessageDialog(ip, "상품 정보를 추가하였습니다.");
			reset();
			// 부모창의 도시락 리스트를 갱신하고
			// 이거 지금 못하는중ㅠ
//			lme.setLunchList();
			// 다음 도시락이 추가될 때 이미지를 다시 선택할 수 있도록 기준 변수를 변경한다.
			imgFlag = false;

		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			JOptionPane.showMessageDialog(ip, "가격은 정수 형태로만 입력해주세요.");
		} catch (IOException ie) {
			ie.printStackTrace();
			JOptionPane.showMessageDialog(ip, "이미지 선택 중 문제 발생");
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(ip, "DBMS에서 문제 발생");
			se.printStackTrace();
		} // end catch

	}// uploadPost

	@Override
	public void mouseClicked(MouseEvent me) {
		// 0917- flag 줘야함
		if (subjectFlag) {
			if (me.getSource() == ip.getJtfSubject()) {
				ip.getJtfSubject().setText("");
				subjectFlag = false;
			} // end if
		} // end if
		if (priceFlag) {
			if (me.getSource() == ip.getJtfPrice()) {
				ip.getJtfPrice().setText("");
				priceFlag = false;
			} // end if
		} // end if
		if (detailFlag) {
			if (me.getSource() == ip.getJtaExplain()) {
				ip.getJtaExplain().setText("");
				detailFlag = false;
			} // end if
		} // end if		


	}// mouseClicked

	public void reset() {
		ip.getJtaExplain().setText("상세 설명");
		ip.getJtfSubject().setText("글 제목");
		ip.getJtfPrice().setText("가격 입력");
		ip.getJcbCategory().setSelectedIndex(0);
		ip.getJlbProductImg().setIcon(null);
		ip.getJlbProductImg().setText("제품 이미지");
		ip.getJlbProductImg().setHorizontalTextPosition(JLabel.CENTER);
		pathAndName="";
		file = null;
		subjectFlag = true;
		priceFlag = true;
		detailFlag = true;
		imgFlag = true;

	}

	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ip.getJbtOkay()) {
//			System.out.println(imgFlag+"   왜 true안나와?");
			uploadPost();
//			ip.getJlbProductImg().setIcon(null);
//			ip.getJtfSubject().setText("");
//			ip.getJtfPrice().setText("");
//			ip.getJtaExplain().setText("");
//			ip.getJtfSubject().requestFocus();
		}

		if (ae.getSource() == ip.getJbtSelectImg()) {
			addImg();
//			System.out.println(imgFlag+"addimg()후 플래그값");			
		} // end if

		if (ae.getSource() == ip.getJtfSubject()) {
			if (!ip.getJtfSubject().getText().equals("")) {
				ip.getJtfPrice().requestFocus();
				if (priceFlag) {
					ip.getJtfPrice().setText("");
					priceFlag = false;
				} // end if
			} // end if
		} // end if	
		

		if (ae.getSource() == ip.getJtfPrice()) {
			if (!ip.getJtfPrice().getText().equals("")) {
				ip.getJtaExplain().requestFocus();
				if (priceFlag) {
					ip.getJtaExplain().setText("");
					detailFlag = false;
				} // end if
			} // end if
		} // end if

		if (ae.getSource() == ip.getJbtCancel()) {		
			
			switch (JOptionPane.showConfirmDialog(ip,"상품등록을 취소하시겠습니까?","취소",JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE)) {
			case JOptionPane.OK_OPTION:
				reset();
				JOptionPane.showMessageDialog(ip, "상품등록이 취소되었습니다.");
			case JOptionPane.CANCEL_OPTION:
				
			}//end switch
		}//end if
		 
		////////////////////////////////////////// 고쳐야함 //////////////////////////////////////////////////
		if(rmm.getJtp().getSelectedIndex()!=1) {
			System.out.println("되나?");
		}
		if (ae.getSource() == rmm.getJtp()) {
			System.out.println("왜안됑?");
			reset();
			//취소할때나 이동할 때 ㅌ컨펌메세지 받기
		}
	}// actionPerformed
}// class
