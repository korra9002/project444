package adminFileServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import fileTestAdmin.FileHelper;

public class AdminFileSend extends Thread {
	List<AdminFileSendHelper> list = new ArrayList<AdminFileSendHelper>();

	public void run() {
		// 1. ���� ���� ����
		ServerSocket server = null;
		ServerSocket server2 = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		FileInputStream fis = null;

		try {
			server = new ServerSocket(5000);
			server2 = new ServerSocket(1025);
			Socket client = null;
			Socket client2 = null;
 
			AdminFileSendHelper fh = null;
			while (true) {
				// 3. �����ڰ� ������
				client = server.accept();
				System.out.println("����"+client.getInetAddress());
				client2 = server2.accept();
				System.out.println("����"+client2.getInetAddress());
				fh = new AdminFileSendHelper(server,client,client2);
//				list.add(fh);
				fh.start();

			} // end while
		} catch (IOException ie) {

			try {
				if (server != null)
					server.close();
			} catch (IOException e) {
				e.printStackTrace();
			} // end catch
		} // end catch

	}// run

}
