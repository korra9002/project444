package userFileRecieve;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class UserFileRecieve {
	public static UserFileRecieve uFR;
	
	private UserFileRecieve() {
		
	}
	
	public static UserFileRecieve getInstance() {
		if(uFR == null) {
			uFR = new UserFileRecieve();
		}
		return uFR;
	}
	
	public synchronized void getImgFile() throws UnknownHostException, IOException {
		int bytesRead =0;
		int current = 0;
		Socket client = null;
		Socket client2 = null;
		
		DataOutputStream dos = null;
		DataInputStream dis = null;
		//////////////////////////
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		DataOutputStream dos2 = null;
		InputStream is = null;
		


		try {
			// 2. 소켓생성 : 서버로 연결
			client2 = new Socket("localhost",1025);
			client = new Socket("localhost", 5000);
			// 4. 데이터를 주고 받을 스트림 연결
			is = client.getInputStream();
			dos2= new DataOutputStream(client.getOutputStream());
			dos = new DataOutputStream(client2.getOutputStream());
			dis = new DataInputStream(client2.getInputStream());

			File file = new File("c:/dev/userRecieveFile");
			if(!file.exists()) {
				file.mkdirs();
			}
			File[] temp = file.listFiles();

			StringBuilder csvFile = new StringBuilder();
			for (int i = 0; i < temp.length; i++) {
				if (i != 0) {
					csvFile.append(",");
				} // end if
				csvFile.append(temp[i].getName());
			} // end if

			dos.writeUTF(csvFile.toString()); // 111111

			int fileCnt = dis.readInt();//2222222222222222
			System.out.println(fileCnt+"앞으로 받을 파일 갯수");
			String revFileName = "";

			  // receive file
			
			for (int i = 0; i < fileCnt; i++) {
//				if(dis == null) {
//					dis = new DataInputStream(is);
//				}
		      byte [] mybytearray  = new byte [6022386];
		      revFileName = dis.readUTF();//333333
		  	System.out.println(revFileName+"받을 파일 제목");
		  	int fileSize = dis.readInt();
		  	System.out.println(fileSize+"받을 파일 크기");
//		      dis.close();
		      File myFile = new File("c:/dev/userRecieveFile/"+revFileName);
		      fos = new FileOutputStream(myFile);
		      bos = new BufferedOutputStream(fos);
		     
		       current =0;
		       bytesRead =0;
		       
//		      bytesRead = is.read(mybytearray,0,mybytearray.length); //4444 이하 444
//		      current = bytesRead;
//		      System.out.println("첫번째 "+current);
//		      if(fileSize>65536) {
		    	  
		    	  do {
		    		  bytesRead =
		    				  is.read(mybytearray, current, (mybytearray.length-current));
		    		  if(bytesRead >= 0) current += bytesRead;
		    		  System.out.println(bytesRead+"/"+current+"/"+fileSize);
		    	  } while(current < fileSize);
//		      }
		      System.out.println("파일받기 일단 끝");
		      
		      bos.write(mybytearray, 0 , current);
		      bos.flush();
		      System.out.println("File " + "c:/dev/userRecieveFile/"+revFileName
		          + " downloaded (" + current + " bytes read)");
		      bos.close();
//		      fos.close();
		      
		      dos.writeInt(0);
//		      dos2.writeUTF("파일 다받은 신호");///5555
//		      dos2.flush();
		  //    dos2.flush();
			} // end for
		      

		} finally {
			if (dis != null)
				dis.close();
			if (dos != null)
				dos.close();
			if (client != null)
				client.close();
			if (fos != null)
				fos.close();
			if(client2 != null)
				client2.close();
		}
	}// sendFileList

}
