package adminFileServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import kr.co.sist.util.img.ImageResize;

public class AdminFileRecieve extends Thread {
	private int bytesRead;
	private int current = 0;
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;
	private Socket sock = null;

	private ServerSocket servSock = null;

	public AdminFileRecieve() throws IOException {
		servSock = new ServerSocket(5001);

	}

	public void run() {
		try {
//		      servsock = new ServerSocket(SOCKET_PORT);

			while (true) {
				System.out.println("Waiting...");
				try {
					sock = servSock.accept();
					System.out.println("Accepted connection : " + sock);
					// send file
					byte[] mybytearray = new byte[6022386];
					InputStream is = sock.getInputStream();

					DataInputStream dis = new DataInputStream(is);
					String newName = dis.readUTF();
					System.out.println("받은 새로운 이름" + newName);

					fos = new FileOutputStream("c:/dev/adminRecieveFile/" + newName);
					bos = new BufferedOutputStream(fos);
					
					bytesRead =0;
					current = 0;
					do {
						bytesRead = is.read(mybytearray, current, (mybytearray.length - current));
						if (bytesRead >= 0)
							current += bytesRead;
						System.out.println(bytesRead);
					} while (bytesRead > -1);
//				} while (bytesRead > -1);

					bos.write(mybytearray, 0, current);
					bos.flush();
					System.out.println("File " + "c:/dev/adminRecieveFile/" + newName + " downloaded (" + current
							+ " bytes read)");

//					bos.close();
					is.close();
//					dis.close();
//					ImageResize.resizeImage("c:/dev/adminRecieveFile/"+newName, 100, 100);
//					ImageResize.resizeImage("c:/dev/adminRecieveFile/"+newName, 200, 200);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (fos != null)
							fos.close();
						if (bos != null)
							bos.close();
						if (sock != null)
							sock.close();

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} finally {
			try {
				if (servSock != null)
					servSock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			new AdminFileRecieve().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
