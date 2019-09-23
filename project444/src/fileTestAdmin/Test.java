package fileTestAdmin;

public class Test {

	public static void main(String[] args) {
		String end = "END";
		byte[] byteArray= end.getBytes();
		for (int i = 0; i < byteArray.length; i++) {
			System.out.println(byteArray[i]+",");
		}
		System.out.println(byteArray);
	}
}
