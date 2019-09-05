package fileTestAdmin;

import fileTestUser.MarketMain;

public class Admin {

	public static void main(String[] args) {
	FileServer2 fs2 = new FileServer2();
	fs2.start();
	new FileServer();
	}
}
