package adminFileServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AdminFileSend extends Thread {
	List<AdminFileSendHelper> list = new ArrayList<AdminFileSendHelper>();

	public void run() {
		ServerSocket server = null;
		ServerSocket server2 = null;

		try {
			server = new ServerSocket(5000);
			server2 = new ServerSocket(1025);
			Socket client = null;
			Socket client2 = null;

			AdminFileSendHelper fh = null;
			while (true) {
				client = server.accept();
				System.out.println("입장" + client.getInetAddress());
				client2 = server2.accept();
				System.out.println("입장" + client2.getInetAddress());
				fh = new AdminFileSendHelper(server, client, client2);
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
