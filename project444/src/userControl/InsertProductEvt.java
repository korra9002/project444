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
		System.out.println("������!!!!!");
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

		FileDialog fdOpen = new FileDialog(rmm, "���ε� ���� ����", FileDialog.LOAD);
		fdOpen.setVisible(true);

		String path = fdOpen.getDirectory();
		String name = fdOpen.getFile();
		
//		System.out.println(path+file);
		if (path != null && name != null) {

			if ((name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png")) ) {
				try {
//				this.setTitle(file.getName());
//				t = new Test(file);
//				jlnotice.setText(String.format(" ����  [ %d - %d ]", 1, t.getLastIndex()));
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

				JOptionPane.showMessageDialog(rmm, "�̹��� ������ �ƴմϴ�.");
//				imgFlag = false;
				addImg();
			} // end else
				// �̹����� �̸����� �󺧿� ����
//			JLabel imgPreview = ip.getJlbProductImg();
//			imgPreview.setText("");
//			imgPreview.setIcon(new ImageIcon(path + file));

			// �̹����� ���õǾ����� �����Ѵ�.
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
//		// ������ �̹����� �̹��� ������ ����
//		File readFile = new File(ip.getJlbProductImg().getIcon().toString());// ������ �̹��� ���
//		
//		byte[] readData = new byte[512]; // �о���� ������ ������ �迭
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
//				fos = new FileOutputStream(writeFile); // �̹��� ������ ����
//				
//				while ((len = fis.read(readData)) != -1) {
//					
//					fos.write(readData, 0, len);
//				} // end while
//				
//				fos.flush();
//				// �̹����� thumbnail image�� ����
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
		// �Էµ� ���� ������ VO�� �����ϰ�
		String img = file.getName();
		String category = Integer.toString(ip.getJcbCategory().getSelectedIndex());
		String subject = ip.getJtfSubject().getText().trim(); // ������ ������ ��ǰ������-
		String pDetail = ip.getJtaExplain().getText().trim();
		String newName = id+"_"+s.format(today)+"_"+img;
		int price = 0;
		try {
			price = Integer.parseInt(ip.getJtfPrice().getText().trim());
			System.out.println(img + " " + category + " " + subject + " " + pDetail + " " + price);
			InsertProductVO ipVO = new InsertProductVO(newName, category, subject, pDetail, price);

//			System.out.println(imgFlag);

			if (!imgFlag) {
				JOptionPane.showMessageDialog(ip, "�̹����� �������ּ���.");
				return;
			} // end if
				// �̹��� ���ε�
			
			System.out.println(newName+"���̸�");
			uploadImg(newName);
			
			// DBMS�� �߰�
			UserDAO uDAO = UserDAO.getInstance();
			uDAO.insertProduct(ipVO, id);
			JOptionPane.showMessageDialog(ip, "��ǰ ������ �߰��Ͽ����ϴ�.");
			reset();
			// �θ�â�� ���ö� ����Ʈ�� �����ϰ�
			// �̰� ���� ���ϴ��ߤ�
//			lme.setLunchList();
			// ���� ���ö��� �߰��� �� �̹����� �ٽ� ������ �� �ֵ��� ���� ������ �����Ѵ�.
			imgFlag = false;

		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			JOptionPane.showMessageDialog(ip, "������ ���� ���·θ� �Է����ּ���.");
		} catch (IOException ie) {
			ie.printStackTrace();
			JOptionPane.showMessageDialog(ip, "�̹��� ���� �� ���� �߻�");
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(ip, "DBMS���� ���� �߻�");
			se.printStackTrace();
		} // end catch

	}// uploadPost

	@Override
	public void mouseClicked(MouseEvent me) {
		// 0917- flag �����
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
		ip.getJtaExplain().setText("�� ����");
		ip.getJtfSubject().setText("�� ����");
		ip.getJtfPrice().setText("���� �Է�");
		ip.getJlbProductImg().setIcon(null);
		ip.getJlbProductImg().setText("��ǰ �̹���");
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
//			System.out.println(imgFlag+"   �� true�ȳ���?");
			uploadPost();
//			ip.getJlbProductImg().setIcon(null);
//			ip.getJtfSubject().setText("");
//			ip.getJtfPrice().setText("");
//			ip.getJtaExplain().setText("");
//			ip.getJtfSubject().requestFocus();
		}

		if (ae.getSource() == ip.getJbtSelectImg()) {
			addImg();
//			System.out.println(imgFlag+"addimg()�� �÷��װ�");			
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
			reset();
		}
		
		
		////////////////////////////////////////// ���ľ��� //////////////////////////////////////////////////
		if(rmm.getJtp().getSelectedIndex()!=1) {
			System.out.println("�ǳ�?");
		}
		if (ae.getSource() == rmm.getJtp()) {
			System.out.println("�־ȉ�?");
			reset();
			//����Ҷ��� �̵��� �� �����߸޼��� �ޱ�
		}
	}// actionPerformed
}// class
