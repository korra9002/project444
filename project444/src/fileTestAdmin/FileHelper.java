package fileTestAdmin;

import java.io.BufferedInputStream;
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
	private List<String> listSendFile; // �����ڿ��� ���� ����Ʈ
	private File temp1 = null;
	
	////////////////
	private BufferedInputStream bis;

	public FileHelper(Socket client) throws IOException {
		this.client = client;
		// 4. �����͸� �ְ� ���� ��Ʈ�� ����
		
		dis = new DataInputStream(client.getInputStream());
		dos = new DataOutputStream(client.getOutputStream());

		String temp = "";
		temp1 = null;
		File[] serverFile = null;

		// 6. Ŭ���̾�Ʈ�� �������� ���� ��� �ޱ�

		temp = dis.readUTF(); // ���� ��� �ޱ�
		// C:\dev\workspace\jdbc_prj\src\kr\co\sist\admin\img
		// temp�� �����ϴ� ���ϰ� admin�� �����ϴ� ������ ���Ͽ� ���� ������ ã�� ���

		temp1 = new File("c:/dev/fileTest");
		serverFile = temp1.listFiles();
		listSendFile = new ArrayList<String>();

		for (int j = 0; j < serverFile.length; j++) {
			if (!temp.contains(serverFile[j].getName()) && !serverFile[j].getName().startsWith("rs_")) {
				listSendFile.add(serverFile[j].getName());
			} // end if
		} // end for

		// 7. ������ ������ ���� ������ (Ŭ���̾�Ʈ�� �� Ƚ���� �ݺ����� �д´�.)
		dos.writeInt(listSendFile.size());
		//dos.flush();

		////////////////////////////////////
		
		
		
		
		
	}// FileHelper

	public void run() {
		byte[] readData = new byte[512];// ���Ͽ��� �о� ���� ������ ����
		int sendCnt = 0; // ������ readData����
		int readSize = 0; // �о���� �迵�l ���� ����.
		try {
			// 8. ������ ���� ���� ���

			for (int i = 0; i < listSendFile.size(); i++) {
				sendCnt = 0;
				// System.out.println();
				fis = new FileInputStream(new File(temp1.getAbsolutePath() + "/" + listSendFile.get(i)));

				while ((readSize = fis.read(readData)) != -1) {
					sendCnt++;// ������ Ƚ��
				} // end while
				fis.close();
				fis = new FileInputStream(new File(temp1.getAbsolutePath() + "/" + listSendFile.get(i)));
				System.out.println(sendCnt+"����Ƚ��");
				System.out.println(dis.readUTF()+"���۽�ȣ");
				// 9.������ Ƚ���� Ŭ���̾�Ʈ���� ������.
				dos.writeInt(sendCnt);
			//	dos.flush();
				// 11.������ ���ϸ��� ������. 
				dos.writeUTF(listSendFile.get(i));
			//	dos.flush();
				// 12.���Ͽ��� �о���� Ƚ����ŭ Ŭ���̾�Ʈ�� ������.
				System.out.println(dis.readUTF()+"ī��Ʈ�� ���ϸ� ����");// Ȯ�� ������ ���� ������.
				while (sendCnt > 0) {
					readSize = fis.read(readData);
//				System.out.println(readSize+" / ");
					dos.write(readData, 0, readSize);
					sendCnt--;
				} // end while
				dos.flush();
				//////////////
			//	dos.close();
				////////////
				fis.close();
				System.out.println(dis.readUTF()+"�������� ��");// ���� ���� �� Ȯ��
				
				////////////
			//	dos = new DataOutputStream(client.getOutputStream());
				/////// 
			} // end for
		} catch (IOException ie) {
			ie.printStackTrace();
		} // end catch

	}// run
}
