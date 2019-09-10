package fileTestUser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import kr.co.sist.user.view.LunchUser;
import kr.co.sist.util.img.ImageResize;

public class RunLunchUser2 {

	public RunLunchUser2() {

	}// RunLunchUser

	public void sendFileList() throws UnknownHostException, IOException {

		Socket client = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		FileOutputStream fos = null;
		
		try {
			// 2. ���ϻ��� : ������ ����
			client = new Socket("211.63.89.159", 5000);
			// 4. �����͸� �ְ� ���� ��Ʈ�� ����
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

			// 5. ������ ���ϸ���Ʈ CSV Data ������
			dos.writeUTF(csvFile.toString()); // ���ڿ��� ��Ʈ���� ���
			dos.flush();// ��Ʈ���� ������ �������� ����

			// 9. �������� ���� ������ ������ ���� ���� �޾� �� Ƚ����ŭ �ݺ���Ų��.
			int fileCnt = dis.readInt();
			int readCnt = 0;
			String revFileName = "";
			
			byte[] readData = new byte[512];
			int readSize = 0;
			for (int i = 0; i < fileCnt; i++) {
				dos.writeUTF("Y"); //������ ���۹ޱ� ���� �÷��� ���� ������ ����
				// 10.�о���������� Ƚ�� �ޱ�
				readCnt = dis.readInt();
				// 12. ���ϸ� �ޱ�
				revFileName = dis.readUTF();
				// 13. ���ϻ���
				fos = new FileOutputStream("C:/dev/workspace/jdbc_prj/src/kr/co/sist/user/img/"+revFileName);
				dos.writeUTF("Y");// ���� �ޱ� ���� Ȯ�� 
				while(readCnt > 0) {
					readSize = dis.read(readData);
					fos.write(readData, 0,readSize);
					readCnt--;
				}//end while
				fos.flush();
				//14.thumbnail ���� ����
				dos.writeUTF("Y");//�������� Ȯ�� �޼���
				ImageResize.resizeImage("C:/dev/workspace/jdbc_prj/src/kr/co/sist/user/img/"+revFileName, 100, 80);
			} // end for
//		System.out.println(csvFile);

			// ������ ��� ������ ����
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
		new LunchUser();
	}// main

}// class
