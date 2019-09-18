package userControl;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

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
	private boolean imgFlag;

	public InsertProductEvt(InsertProduct ip, RunMarketMain rmm) {
		System.out.println("생성자!!!!!");
		this.ip = ip;
		this.rmm = rmm;
		id = RunMarketMain.userId;
	}// InsertProductEvt

	public void addImg() {

		FileDialog fd = new FileDialog(rmm);

		FileDialog fdOpen = new FileDialog(fd, "ㅎㅇㅎㅇ", FileDialog.LOAD);
		fdOpen.setVisible(true);

		String path = fdOpen.getDirectory();
		String file = fdOpen.getFile();
//		System.out.println(path+file);
		if (file != null) { // 변경할 파일을 선택
			// 확장자 확인
			String flagExt = "jpg,gif,png,bmp";
			String ext = file.substring(file.lastIndexOf(".") + 1);

			if (!flagExt.contains(ext.toLowerCase())) {
				JOptionPane.showMessageDialog(ip, file + "은 이미지가 아니거나 사용할 수 없는 이미지 입니다.");
				return;
			} // end if

			// 이미지를 미리보기 라벨에 설정
			JLabel imgPreview = ip.getJlbProductImg();
			imgPreview.setIcon(new ImageIcon(path + file));

			imgFlag = true; // 이미지가 선택되었음을 설정한다.
		} else {
			imgFlag = false;

		} // end if
//		System.out.println(imgFlag);

	}// addImg

	private void uploadImg() throws IOException {
		// 선택한 이미지를 이미지 폴더에 복사
		File readFile = new File(ip.getJlbProductImg().getIcon().toString());// 선택한 이미지 경로

		byte[] readData = new byte[512]; // 읽어들인 정보를 저장할 배열
		int len = 0;

		FileOutputStream fos = null;
		FileInputStream fis = null;

		System.out.println(readFile.getName());
		try {
			fis = new FileInputStream(readFile);
			if (readFile.exists()) {
				File writeFile = new File("C:/Users/sist/git/project444/project444/src/images/" + readFile.getName());
				fos = new FileOutputStream(writeFile); // 이미지 폴더에 폭사

				while ((len = fis.read(readData)) != -1) {

					fos.write(readData, 0, len);
				} // end while

				fos.flush();
				// 이미지를 thumbnail image로 생성
				ImageResize.resizeImage(writeFile.getAbsolutePath(), 120, 100);
			} // end if

		} finally {
			if (fos != null) {
				fos.close();
			} // end if
			if (fis != null) {
				fis.close();
			} // end if
		} // end finally

	}// uploadImg

	public void uploadPost() {

		// 입력된 값을 가지고 VO에 설정하고
		String img = new File(ip.getJlbProductImg().getIcon().toString()).getName();
		String category = Integer.toString(ip.getJcbCategory().getSelectedIndex());
		String subject = ip.getJtfSubject().getText().trim(); // 제목은 무조건 상품명으로-
		String pDetail = ip.getJtaExplain().getText().trim();
		int price = 0;
		try {
			price = Integer.parseInt(ip.getJtfPrice().getText().trim());
			System.out.println(img + " " + category + " " + subject + " " + pDetail + " " + price);
			InsertProductVO ipVO = new InsertProductVO(img, category, subject, pDetail, price);

//			System.out.println(imgFlag);

			if (!imgFlag) {
				JOptionPane.showMessageDialog(ip, "이미지를 선택해주세요.");
				return;
			} // end if
				// 이미지 업로드
			uploadImg();
			// DBMS에 추가
			UserDAO uDAO = UserDAO.getInstance();
			System.out.println("왜안ㄷㄷ좰ㄴ왜롲대뢔");
			uDAO.insertProduct(ipVO, id);
			JOptionPane.showMessageDialog(ip, "도시락 정보를 추가하였습니다.");
			// 부모창의 도시락 리스트를 갱신하고
			// 이거 지금 못하는중ㅠ
//			lme.setLunchList();
			// 다음 도시락이 추가될 때 이미지를 다시 선택할 수 있도록 기준 변수를 변경한다.
			imgFlag = false;

		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(ip, "가격은 정수 형태로만 입력해주세요.");
		} catch (IOException ie) {
			JOptionPane.showMessageDialog(ip, "이미지 선택 중 문제 발생");
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(ip, "DBMS에서 문제 발생");
			se.printStackTrace();
		} // end catch

	}// uploadPost

	@Override
	public void mouseClicked(MouseEvent me) {
		//0917- flag 줘야함
		if (me.getSource() == ip.getJtfSubject()) {
			ip.getJtfSubject().setText("");
		} else if (me.getSource() == ip.getJtfPrice()) {
			ip.getJtfPrice().setText("");
		} else if (me.getSource() == ip.getJtaExplain()) {
			ip.getJtaExplain().setText("");
		} // end if

	}// mouseClicked

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ip.getJbtOkay()) {
//			System.out.println(imgFlag+"   왜 true안나와?");
			uploadPost();
			ip.getJlbProductImg().setIcon(null);
			ip.getJtfSubject().setText("");
			ip.getJtfPrice().setText("");
			ip.getJtaExplain().setText("");
			ip.getJtfSubject().requestFocus();
		}

		if (ae.getSource() == ip.getJbtSelectImg()) {
			addImg();
//			System.out.println(imgFlag+"addimg()후 플래그값");			
		} // end if

	}// actionPerformed
}// class
