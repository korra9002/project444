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
		DataInputStream dis = null;
		DataOutputStream dos = null;
		FileInputStream fis = null;

		try {
			server = new ServerSocket(5000);
			Socket client = null;

			FileHelper fh = null;
			while (true) {
				// 3. �����ڰ� ������
				client = server.accept();
				System.out.println("����"+client.getInetAddress());
				fh = new FileHelper(client);
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
