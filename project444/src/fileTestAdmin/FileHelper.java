package fileTestAdmin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class FileHelper extends Thread {
	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;
	private FileInputStream fis;
	private List<String> listSendFile; // 접속자에게 보낼 리스트
	private File temp1 = null;

	public FileHelper(Socket client) throws IOException {
		this.client = client;
		// 4. 데이터를 주고 받을 스트림 연결
		
		dis = new DataInputStream(client.getInputStream());
		dos = new DataOutputStream(client.getOutputStream());

		String temp = "";
		temp1 = null;
		File[] serverFile = null;

		// 6. 클라이언트가 보내오는 파일 목록 받기

		temp = dis.readUTF(); // 파일 목록 받기
		// C:\dev\workspace\jdbc_prj\src\kr\co\sist\admin\img
		// temp에 존재하는 파일과 admin에 존재하는 파일을 비교하여 없는 파일을 찾아 출력

		temp1 = new File("c:/dev/fileTest");
		serverFile = temp1.listFiles();
		listSendFile = new ArrayList<String>();

		for (int j = 0; j < serverFile.length; j++) {
			if (!temp.contains(serverFile[j].getName()) && !serverFile[j].getName().startsWith("rs_")) {
				listSendFile.add(serverFile[j].getName());
			} // end if
		} // end for

		// 7. 전송할 파일의 갯수 보내기 (클라이언트는 이 횟수로 반복시켜 읽는다.)
		dos.writeInt(listSendFile.size());
		dos.flush();

	}// FileHelper

	public void run() {
		byte[] readData = new byte[256];// 파일에서 읽어 들인 내용을 저장
		int sendCnt = 0; // 전송할 readData갯수
		int readSize = 0; // 읽어들인 배영릐 방의 갯수.
		try {
			// 8. 전송할 파일 정보 얻기

			for (int i = 0; i < listSendFile.size(); i++) {
				sendCnt = 0;
				// System.out.println();
				fis = new FileInputStream(new File(temp1.getAbsolutePath() + "/" + listSendFile.get(i)));

				while ((readSize = fis.read(readData)) != -1) {
					sendCnt++;// 전송할 횟수
				} // end while
				fis.close();
				fis = new FileInputStream(new File(temp1.getAbsolutePath() + "/" + listSendFile.get(i)));
				System.out.println(sendCnt+"전송횟수");
				System.out.println(dis.readUTF()+"시작신호");
				// 9.전송할 횟수를 클라이언트에게 보낸다.
				dos.writeInt(sendCnt);
				dos.flush();
				// 11.전송할 파일명을 보낸다. 
				dos.writeUTF(listSendFile.get(i));
				dos.flush();
				// 12.파일에서 읽어들인 횟수만큼 클라이언트에 보낸다.
				System.out.println(dis.readUTF()+"카운트랑 파일명 받음");// 확인 받으면 파일 보낸다.
				while (sendCnt > 0) {
					readSize = fis.read(readData);
//				System.out.println(readSize+" / ");
					dos.write(readData, 0, readSize);
					sendCnt--;
				} // end while
				dos.flush();
				fis.close();
				System.out.println(dis.readUTF()+"파일전송 끝");// 파일 전송 끝 확인
				/////// 
			} // end for
		} catch (IOException ie) {
			ie.printStackTrace();
		} // end catch

	}// run
}
