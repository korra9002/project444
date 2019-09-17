package userControl;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class InsertProductEvt implements ActionListener {

	private InsertProduct ip;
	private RunMarketMain rmm;
	private String id;
	private boolean imgFlag;

	public InsertProductEvt(InsertProduct ip, RunMarketMain rmm) {
		this.ip = ip;
		this.rmm = rmm;
		id = RunMarketMain.userId;
	}// InsertProductEvt

	public void addImg() {

		FileDialog fd = new FileDialog(rmm);

		FileDialog fdOpen = new FileDialog(fd, "��������", FileDialog.LOAD);
		fdOpen.setVisible(true);

		String path = fdOpen.getDirectory();
		String file = fdOpen.getFile();
//		System.out.println(path+file);
		if (file != null) { // ������ ������ ����
			// Ȯ���� Ȯ��
			String flagExt = "jpg,gif,png,bmp";
			String ext = file.substring(file.lastIndexOf(".") + 1);

			if (!flagExt.contains(ext.toLowerCase())) {
				JOptionPane.showMessageDialog(ip, file + "�� �̹����� �ƴϰų� ����� �� ���� �̹��� �Դϴ�.");
				return;
			} // end if

			// �̹����� �̸����� �󺧿� ����
			JLabel imgPreview = ip.getJlbProductImg();
			imgPreview.setIcon(new ImageIcon(path + file));

			imgFlag = true; // �̹����� ���õǾ����� �����Ѵ�.
		} else {
			imgFlag = false;
		} // end if

	}// addImg
	
	private void uploadImg() throws IOException{
		//������ �̹����� �̹��� ������ ����
		File readFile=new File(ip.getJlbProductImg().getIcon().toString());// ������ �̹��� ���
	
		byte[] readData=new byte[512]; //�о���� ������ ������ �迭
		int len=0;
		
		FileOutputStream fos=null;
		FileInputStream fis=null;
		
		try {
		fis=new FileInputStream(readFile);
		if ( readFile.exists()) {
			File writeFile=new File("C:/Users/sist/git/project444/project444/src/images/"+readFile.getName());
			fos=new FileOutputStream(writeFile); //�̹��� ������ ����
			
			while ( (len=fis.read(readData))!=-1) {
				
				
				fos.write(readData, 0, len);
			}//end while
			
			fos.flush();		
			//�̹����� thumbnail image�� ����
			ImageResize.resizeImage(writeFile.getAbsolutePath(), 120, 100);		
		}//end if
		
	}finally {
		 if ( fos != null ) { fos.close(); } //end if
		 if ( fis != null ) { fis.close(); } //end if
	}//end finally
	
	}//uploadImg

	public void uploadPost() {

		//�Էµ� ���� ������ VO�� �����ϰ�
		String img=new File(ip.getJlbProductImg().getIcon().toString()).getName();
		String category=ip.getJcbCategory().getSelectedItem().toString();
		String subject=ip.getJtfSubject().getText().trim(); //������ ������ ��ǰ������-
		String pDetail=ip.getJtaExplain().getText().trim();
		int price=0;
//		try {
//			price=Integer.parseInt(ip.getJtfPrice().getText().trim());
//			InsertProductVO ipVO=new InsertProductVO(img, category, subject, pDetail, price);
//			if( !imgFlag ) {
//				JOptionPane.showMessageDialog(ip, "�̹����� �������ּ���.");
//				return;
//			}//end if
			//�̹��� ���ε�
//			uploadImg();
//			//DBMS�� �߰�
//			UserDAO uDAO=UserDAO.getInstance();
//			uDAO.(ipVO);
//			JOptionPane.showMessageDialog(laf, "���ö� ������ �߰��Ͽ����ϴ�.");
//			//�θ�â�� ���ö� ����Ʈ�� �����ϰ�
//			lme.setLunchList();
//			//���� ���ö��� �߰��� �� �̹����� �ٽ� ������ �� �ֵ��� ���� ������ �����Ѵ�.
//			imgFlag=false;
//			
//		}catch(NumberFormatException nfe) {
//			JOptionPane.showMessageDialog(laf, "������ ���� ���·θ� �Է����ּ���.");
//		}catch(IOException ie) {
//			JOptionPane.showMessageDialog(laf, "�̹��� ���� �� ���� �߻�");
//		}catch(SQLException se) {
//			JOptionPane.showMessageDialog(laf, "DBMS���� ���� �߻�");
//			se.printStackTrace();
//		}//end catch

	}// uploadPost

	public void close() {
//		ip.dispose();
	}// close

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == ip.getJbtSelectImg()) {
			addImg();
		} // end if

		if (ae.getSource() == ip.getJbtOkay()) {
			uploadPost();
		}

		if (ae.getSource() == ip.getJbtCancel()) {
			close();
		}
	}// actionPerformed
}// class
