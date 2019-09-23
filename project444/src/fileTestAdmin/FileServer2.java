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
 * 접속자가 보내오는 파일명을 모두 받아서 접속자에 존재하지 않는 파일을 찾아 보내는 일.
 * 
 * @author owner
 *
 */
public class FileServer2 extends Thread {
	List<FileHelper> list = new ArrayList<FileHelper>();

	public void run() {
		// 1. 서버 소켓 열기
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
				// 3. 접속자가 들어오면
				client = server.accept();
				System.out.println("입장"+client.getInetAddress());
				client2 = server2.accept();
				System.out.println("입장"+client2.getInetAddress());
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
