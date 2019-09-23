package fileTestAdmin;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class FileHelper extends Thread {
	private Socket client,client2;
	private DataInputStream dis;
	private DataOutputStream dos;
	private List<String> listSendFile; // �����ڿ��� ���� ����Ʈ
	private File temp1 = null;

private ServerSocket server;
	////////////////
	private FileInputStream fis;
	private BufferedInputStream bis;
	private OutputStream os;
	private byte[] byteArray;

	public FileHelper(ServerSocket server,Socket client,Socket client2) throws IOException {
		byteArray = "END".getBytes();
		this.client = client;
		this.client2 = client2;
		this.server = server;
		// 4. �����͸� �ְ� ���� ��Ʈ�� ����

	
		dis = new DataInputStream(client2.getInputStream());
		dos = new DataOutputStream(client2.getOutputStream());
		String temp = "";
		temp1 = null;
		File[] serverFile = null;

		// 6. Ŭ���̾�Ʈ�� �������� ���� ��� �ޱ�

		temp = dis.readUTF(); // 11111
		
		
		
		// C:\dev\workspace\jdbc_prj\src\kr\co\sist\admin\img
		// temp�� �����ϴ� ���ϰ� admin�� �����ϴ� ������ ���Ͽ� ���� ������ ã�� ���

		temp1 = new File("c:\\dev\\filetest");
		serverFile = temp1.listFiles();
		listSendFile = new ArrayList<String>();

		for (int j = 0; j < serverFile.length; j++) {
			// && !serverFile[j].getName().startsWith("rs_")
			if (!temp.contains(serverFile[j].getName())) {
				listSendFile.add(serverFile[j].getName());
			} // end if
		} // end for

		// 7. ������ ������ ���� ������ (Ŭ���̾�Ʈ�� �� Ƚ���� �ݺ����� �д´�.)
		dos.writeInt(listSendFile.size());//2222222222
		// dos.flush();

		////////////////////////////////////
		// dis.close();

	}// FileHelper

	public void run() {

		try {
			// 8. ������ ���� ���� ���

			for (int i = 0; i < listSendFile.size(); i++) {
				
		
				os = client.getOutputStream();
				System.out.println(listSendFile.get(i)+"�����̸�");
				dos.writeUTF(listSendFile.get(i));//3333333
 
				File myFile = new File(temp1.getAbsolutePath() + "\\" + listSendFile.get(i));
				System.out.println(temp1.getAbsolutePath() + "\\" + listSendFile.get(i)+"���ϰ��");
				byte[] mybytearray = new byte[(int) myFile.length()];
				fis = new FileInputStream(myFile);
				bis = new BufferedInputStream(fis);
				bis.read(mybytearray, 0, mybytearray.length);//���� �о ���� /////////

				
				System.out.println("Sending " + temp1.getAbsolutePath() + "/" + listSendFile.get(i) + "(" + mybytearray.length + " bytes)");
				os.write(mybytearray, 0, mybytearray.length); //444444
				os.flush();
				System.out.println("Done.");
				System.out.println(client.getInetAddress());
//				client.shutdownOutput();
				System.out.println(client.getInetAddress());
				os.write(byteArray,0,byteArray.length);
				
//				System.out.println(dis.readUTF());///5555
			
			} // end for
		} catch (IOException ie) {
			ie.printStackTrace();
		} // end catch

	}// run

//	public void run() {
////		byte[] readData = new byte[512];// ���Ͽ��� �о� ���� ������ ����
//		int sendCnt = 0; // ������ readData����
//		int readSize = 0; // �о���� �迵�l ���� ����.
//		
//		try {
//			// 8. ������ ���� ���� ���
//			
//			for (int i = 0; i < listSendFile.size(); i++) {
//				byte[] readData = new byte[512];
//				//	sendCnt = 0;
//				// System.out.println();
//				fis = new FileInputStream(new File(temp1.getAbsolutePath() + "/" + listSendFile.get(i)));
//				bis = new BufferedInputStream(fis);
////				while ((readSize = fis.read(readData)) != -1) {
////					sendCnt++;// ������ Ƚ��
////				} // end while
////				fis.close();
////				fis = new FileInputStream(new File(temp1.getAbsolutePath() + "/" + listSendFile.get(i)));
////				System.out.println(sendCnt+"����Ƚ��");
////				System.out.println(dis.readUTF()+"���۽�ȣ");
//				// 9.������ Ƚ���� Ŭ���̾�Ʈ���� ������.
////			dos.writeInt(sendCnt);
//				//	dos.flush();
//				// 11.������ ���ϸ��� ������. 
//				dos.writeUTF(listSendFile.get(i));
//				//	dos.flush();
//				// 12.���Ͽ��� �о���� Ƚ����ŭ Ŭ���̾�Ʈ�� ������.
////				System.out.println(dis.readUTF()+"ī��Ʈ�� ���ϸ� ����");// Ȯ�� ������ ���� ������.
////				while (sendCnt > 0) {
////					readSize = fis.read(readData);
//////				System.out.println(readSize+" / ");
////					dos.write(readData, 0, readSize);
////					sendCnt--;
////				} // end while
//				//////////////////////////////////////////////////
//				int len = 0;
//				
//				while((len = bis.read(readData))>0) {
//					dos.write(readData,0,len);
//					System.out.println(len+"����");
//				}
//				dos.flush();
//				
//				//////////////////////////////
//				
////				dos.write(-1);
//				//////////////
//				////////////
//				fis.close();
//				bis.close();
////				System.out.println(dis.readUTF()+"�������� ��");// ���� ���� �� Ȯ��
//				System.out.println("����������");
//				
//				////////////
//				//	dos = new DataOutputStream(client.getOutputStream());
//				/////// 
//			} // end for
//		} catch (IOException ie) {
//			ie.printStackTrace();
//		} // end catch
//		
//	}// run
}
