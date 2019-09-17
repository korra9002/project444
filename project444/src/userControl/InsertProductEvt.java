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
		
		FileDialog fdOpen=new FileDialog(fd,"��������",FileDialog.LOAD);
		fdOpen.setVisible(true);
		
		String path=fdOpen.getDirectory();
		String file=fdOpen.getFile();
		if (file != null) { //������ ������ ����
			//Ȯ���� Ȯ��
			String flagExt="jpg,gif,png,bmp";
			String ext=file.substring(file.lastIndexOf(".")+1);
			
			if ( !flagExt.contains(ext.toLowerCase()) ) {
				JOptionPane.showMessageDialog(ip, file +
				"�� �̹����� �ƴϰų� ����� �� ���� �̹��� �Դϴ�.");
				return;
			}//end if
			
			//�̹����� �̸����� �󺧿� ����
//			JLabel imgPreview=ip.j
			
			
			
			
		}//end if
		
	}//addImg
	
	public void uploadPost() {

		
//		//�Էµ� ���� ������ VO�� �����ϰ�
//		String img=new File(laf.getJlImg().getIcon().toString()).getName();
//		String name=laf.getJtfName().getText().trim();
//		String strongPoint=laf.getJtaStrongPoint().getText().trim();
//		int price=0;
//		try {
//			price=Integer.parseInt(laf.getJtfPrice().getText().trim());
//			
//			LunchAddVO laVO=new LunchAddVO(img, name, strongPoint, price);
//			if( !imgFlag ) {
//				JOptionPane.showMessageDialog(laf, "�̹����� �������ּ���.");
//				return;
//			}//end if
//			//�̹��� ���ε�
//			uploadImg();
//			//DBMS�� �߰�
//			AdminDAO aDAO=AdminDAO.getInstance();
//			aDAO.insertLunch(laVO);
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

	
	

