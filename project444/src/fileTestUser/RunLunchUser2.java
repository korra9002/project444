package fileTestUser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class RunLunchUser2 {

	public RunLunchUser2() {

	}// RunLunchUser

	public void sendFileList() throws UnknownHostException, IOException {

		Socket client = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		FileOutputStream fos = null;
		
		try {
			// 2. 소켓생성 : 서버로 연결
			client = new Socket("211.63.89.159", 5000);
			// 4. 데이터를 주고 받을 스트림 연결
			dos = new DataOutputStream(client.getOutputStream());
			dis = new DataInputStream(client.getInputStream());

			File file = new File("C:/dev/workspace/jdbc_prj/src/kr/co/sist/user/img");
			File[] temp = file.listFiles();

			StringBuilder csvFile = new StringBuilder();
			for (int i = 0; i < temp.length; i++) {
				if (!temp[i].getName().startsWith("rs_")) {
					if (i != 0) {
						csvFile.append(",");
					} // end if
					csvFile.append(temp[i].getName());
				} // end if
			} // end if

			// 5. 서버로 파일리스트 CSV Data 보내기
			dos.writeUTF(csvFile.toString()); // 문자열을 스트림에 기록
			dos.flush();// 스트림의 내용을 목적지로 분출

			// 9. 서버에서 없는 파일의 갯수를 보낸 것을 받아 그 횟수만큼 반복시킨다.
			int fileCnt = dis.readInt();
			int readCnt = 0;
			String revFileName = "";
			
			byte[] readData = new byte[512];
			int readSize = 0;
			for (int i = 0; i < fileCnt; i++) {
				dos.writeUTF("Y"); //파일을 전송받기 위한 플래그 값을 서버로 전달
				// 10.읽어들일파일의 횟수 받기
				readCnt = dis.readInt();
				// 12. 파일명 받기
				revFileName = dis.readUTF();
				// 13. 파일생성
				fos = new FileOutputStream("C:/dev/workspace/jdbc_prj/src/kr/co/sist/user/img/"+revFileName);
				dos.writeUTF("Y");// 파일 받기 전에 확인 
				while(readCnt > 0) {
					readSize = dis.read(readData);
					fos.write(readData, 0,readSize);
					readCnt--;
				}//end while
				fos.flush();
				//14.thumbnail 파일 생성
				dos.writeUTF("Y");//파일전송 확인 메세지
//				ImageResize.resizeImage("C:/dev/workspace/jdbc_prj/src/kr/co/sist/user/img/"+revFileName, 100, 80);
			} // end for
//		System.out.println(csvFile);

			// 소켓을 열어서 서버에 연결
		} finally {
			if (dis != null)
				dis.close();
			if (dos != null)
				dos.close();
			if (client != null)
				client.close();
			if (fos != null)
				fos.close();
		}
	}// sendFileList

	public static void main(String[] args) {
		RunLunchUser2 rlu = new RunLunchUser2();
		try {
			rlu.sendFileList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		new LunchUser();
	}// main

}// class
