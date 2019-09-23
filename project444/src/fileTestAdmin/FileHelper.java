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
	private List<String> listSendFile; // 접속자에게 보낼 리스트
	private File temp1 = null;

private ServerSocket server;
	////////////////
	private FileInputStream fis;
	private BufferedInputStream bis;
	private OutputStream os;

	public FileHelper(ServerSocket server,Socket client,Socket client2) throws IOException {
		this.client = client;
		this.client2 = client2;
		this.server = server;
		// 4. 데이터를 주고 받을 스트림 연결

	
		dis = new DataInputStream(client2.getInputStream());
		dos = new DataOutputStream(client2.getOutputStream());
		String temp = "";
		temp1 = null;
		File[] serverFile = null;

		// 6. 클라이언트가 보내오는 파일 목록 받기

		temp = dis.readUTF(); // 11111
		
		
		
		// C:\dev\workspace\jdbc_prj\src\kr\co\sist\admin\img
		// temp에 존재하는 파일과 admin에 존재하는 파일을 비교하여 없는 파일을 찾아 출력

		temp1 = new File("c:\\dev\\filetest");
		serverFile = temp1.listFiles();
		listSendFile = new ArrayList<String>();

		for (int j = 0; j < serverFile.length; j++) {
			// && !serverFile[j].getName().startsWith("rs_")
			if (!temp.contains(serverFile[j].getName())) {
				listSendFile.add(serverFile[j].getName());
			} // end if
		} // end for

		// 7. 전송할 파일의 갯수 보내기 (클라이언트는 이 횟수로 반복시켜 읽는다.)
		dos.writeInt(listSendFile.size());//2222222222
		// dos.flush();

		////////////////////////////////////
		// dis.close();

	}// FileHelper

	public void run() {

		try {
			// 8. 전송할 파일 정보 얻기

			for (int i = 0; i < listSendFile.size(); i++) {
				
		
				os = client.getOutputStream();
				System.out.println(listSendFile.get(i)+"파일이름");
				dos.writeUTF(listSendFile.get(i));//3333333

				File myFile = new File(temp1.getAbsolutePath() + "\\" + listSendFile.get(i));
				System.out.println(temp1.getAbsolutePath() + "\\" + listSendFile.get(i)+"파일경로");
				byte[] mybytearray = new byte[(int) myFile.length()];
				fis = new FileInputStream(myFile);
				bis = new BufferedInputStream(fis);
				bis.read(mybytearray, 0, mybytearray.length);//파일 읽어서 저장 /////////

				
				System.out.println("Sending " + temp1.getAbsolutePath() + "/" + listSendFile.get(i) + "(" + mybytearray.length + " bytes)");
				os.write(mybytearray, 0, mybytearray.length); //444444
				os.flush();
				System.out.println("Done.");
				System.out.println(client.getInetAddress());
//				client.shutdownOutput();
				System.out.println(client.getInetAddress());
				
				
//				System.out.println(dis.readUTF());///5555
			
			} // end for
		} catch (IOException ie) {
			ie.printStackTrace();
		} // end catch

	}// run

//	public void run() {
////		byte[] readData = new byte[512];// 파일에서 읽어 들인 내용을 저장
//		int sendCnt = 0; // 전송할 readData갯수
//		int readSize = 0; // 읽어들인 배영릐 방의 갯수.
//		
//		try {
//			// 8. 전송할 파일 정보 얻기
//			
//			for (int i = 0; i < listSendFile.size(); i++) {
//				byte[] readData = new byte[512];
//				//	sendCnt = 0;
//				// System.out.println();
//				fis = new FileInputStream(new File(temp1.getAbsolutePath() + "/" + listSendFile.get(i)));
//				bis = new BufferedInputStream(fis);
////				while ((readSize = fis.read(readData)) != -1) {
////					sendCnt++;// 전송할 횟수
////				} // end while
////				fis.close();
////				fis = new FileInputStream(new File(temp1.getAbsolutePath() + "/" + listSendFile.get(i)));
////				System.out.println(sendCnt+"전송횟수");
////				System.out.println(dis.readUTF()+"시작신호");
//				// 9.전송할 횟수를 클라이언트에게 보낸다.
////			dos.writeInt(sendCnt);
//				//	dos.flush();
//				// 11.전송할 파일명을 보낸다. 
//				dos.writeUTF(listSendFile.get(i));
//				//	dos.flush();
//				// 12.파일에서 읽어들인 횟수만큼 클라이언트에 보낸다.
////				System.out.println(dis.readUTF()+"카운트랑 파일명 받음");// 확인 받으면 파일 보낸다.
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
//					System.out.println(len+"헬퍼");
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
////				System.out.println(dis.readUTF()+"파일전송 끝");// 파일 전송 끝 확인
//				System.out.println("서버끝났다");
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
