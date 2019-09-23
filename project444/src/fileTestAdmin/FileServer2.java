package fileTestAdmin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;



/**
 * �����ڰ� �������� ���ϸ��� ��� �޾Ƽ� �����ڿ� �������� �ʴ� ������ ã�� ������ ��.
 * 
 * @author owner
 *
 */
public class FileServer2 extends Thread {
	List<FileHelper> list = new ArrayList<FileHelper>();

	public void run() {
		// 1. ���� ���� ����
		ServerSocket server = null;
		ServerSocket server2 = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		FileInputStream fis = null;

		try {
			server = new ServerSocket(5000);
			server2 = new ServerSocket(5001);
			Socket client = null;
			Socket client2 = null;
 
			FileHelper fh = null;
			while (true) {
				// 3. �����ڰ� ������
				client = server.accept();
				System.out.println("����"+client.getInetAddress());
				client2 = server2.accept();
				System.out.println("����"+client2.getInetAddress());
				fh = new FileHelper(server,client,client2);
				list.add(fh);
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

	public static void main(String[] args) {
		// new FileServer().test();
	}// main
}// class
