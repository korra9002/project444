package adminFileServer;

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

public class AdminFileSendHelper extends Thread {
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
	private byte[] byteArray;
	private DataInputStream dis2;

	public AdminFileSendHelper(ServerSocket server,Socket client,Socket client2) throws IOException {
		byteArray = "END".getBytes();
		this.client = client;
		this.client2 = client2;
		this.server = server;
		// 4. 데이터를 주고 받을 스트림 연결

		os = client.getOutputStream();
		dis2 = new DataInputStream(client.getInputStream());
		dis = new DataInputStream(client2.getInputStream());
		dos = new DataOutputStream(client2.getOutputStream());
		String temp = "";
		temp1 = null;
		File[] serverFile = null;

		// 6. 클라이언트가 보내오는 파일 목록 받기

		temp = dis.readUTF(); // 11111
		System.out.println(temp+"받은 파일목록");
		
		
		// C:\dev\workspace\jdbc_prj\src\kr\co\sist\admin\img
		// temp에 존재하는 파일과 admin에 존재하는 파일을 비교하여 없는 파일을 찾아 출력

		temp1 = new File("c:/dev/adminRecieveFile");
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
				
		
			
				System.out.println(listSendFile.get(i)+"파일이름");
				dos.writeUTF(listSendFile.get(i));//3333333
				dos.flush();
 
				File myFile = new File(temp1.getAbsolutePath() + "/" + listSendFile.get(i));
				int fileSize =(int) myFile.length();
				System.out.println("파일크기"+fileSize);
				dos.writeInt(fileSize);/// 3.5555 파일 크기 
				
				System.out.println(temp1.getAbsolutePath() + "/" + listSendFile.get(i)+"파일경로");
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
//				os.write(byteArray,0,byteArray.length);
				
				bis.close();
//				fis.close();
				
				int msg = dis.readInt();
				System.out.println(msg );
//				System.out.println(dis2.readUTF());///5555
			
			} // end for
		} catch (IOException ie) {
			ie.printStackTrace();
		} // end catch

	}// run

}
