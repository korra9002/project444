package userView;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {

	public static void  main(String[] args) {
		
		try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
