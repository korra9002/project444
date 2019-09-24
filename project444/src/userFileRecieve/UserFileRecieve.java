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
	
	public synchronized void sendFileList() throws UnknownHostException, IOException {
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
			// 2. ���ϻ��� : ������ ����
			client = new Socket("211.63.89.159", 5000);
			client2 = new Socket("211.63.89.159",1025);
			// 4. �����͸� �ְ� ���� ��Ʈ�� ����
			is = client.getInputStream();
			dos2= new DataOutputStream(client.getOutputStream());
			dos = new DataOutputStream(client2.getOutputStream());
			dis = new DataInputStream(client2.getInputStream());

			File file = new File("c:/dev/userRecieveFile");
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
			System.out.println(fileCnt+"������ ���� ���� ����");
			String revFileName = "";

			  // receive file
			
			for (int i = 0; i < fileCnt; i++) {
//				if(dis == null) {
//					dis = new DataInputStream(is);
//				}
		      byte [] mybytearray  = new byte [6022386];
		      revFileName = dis.readUTF();//333333
		  	System.out.println(revFileName+"���� ���� ����");
		  	int fileSize = dis.readInt();
		  	System.out.println(fileSize+"���� ���� ũ��");
//		      dis.close();
		      File myFile = new File("c:/dev/userRecieveFile/"+revFileName);
		      fos = new FileOutputStream(myFile);
		      bos = new BufferedOutputStream(fos);
		     
		       current =0;
		       bytesRead =0;
		       
//		      bytesRead = is.read(mybytearray,0,mybytearray.length); //4444 ���� 444
//		      current = bytesRead;
//		      System.out.println("ù��° "+current);
//		      if(fileSize>65536) {
		    	  
		    	  do {
		    		  bytesRead =
		    				  is.read(mybytearray, current, (mybytearray.length-current));
		    		  if(bytesRead >= 0) current += bytesRead;
		    		  System.out.println(bytesRead+"/"+current+"/"+fileSize);
		    	  } while(current < fileSize);
//		      }
		      System.out.println("���Ϲޱ� �ϴ� ��");
		      
		      bos.write(mybytearray, 0 , current);
		      bos.flush();
		      System.out.println("File " + "c:/dev/userRecieveFile/"+revFileName
		          + " downloaded (" + current + " bytes read)");
		      bos.close();
//		      fos.close();
		      
		      dos.writeInt(0);
//		      dos2.writeUTF("���� �ٹ��� ��ȣ");///5555
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
