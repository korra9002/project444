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

		os = client.getOutputStream();
		dis2 = new DataInputStream(client.getInputStream());
		dis = new DataInputStream(client2.getInputStream());
		dos = new DataOutputStream(client2.getOutputStream());
		String temp = "";
		temp1 = null;
		File[] serverFile = null;


		temp = dis.readUTF(); // 11111
		System.out.println(temp+"받은 파일목록");
		
		


		temp1 = new File("c:/dev/adminRecieveFile");
		serverFile = temp1.listFiles();
		listSendFile = new ArrayList<String>();

		for (int j = 0; j < serverFile.length; j++) {
			if (!temp.contains(serverFile[j].getName())) {
				listSendFile.add(serverFile[j].getName());
			} // end if
		} // end for

		dos.writeInt(listSendFile.size());//2222222222


	}// FileHelper

	public void run() {

		try {

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
				System.out.println(client.getInetAddress());
				
				bis.close();
				
				int msg = dis.readInt();
				System.out.println(msg );
			
			} // end for
		} catch (IOException ie) {
			ie.printStackTrace();
		} // end catch

	}// run

}
