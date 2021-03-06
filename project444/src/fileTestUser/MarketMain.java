package fileTestUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MarketMain extends JFrame implements ActionListener {

	private DefaultComboBoxModel<String> dcbm, dcbm1;
	private JComboBox<String> jcbArea, jcbCategory;
	private JTextField jtfSearch;
	private JButton jbSearch, jbRecent, jbPrice, jbRefresh;
	private JRadioButton jrbSubject, jrbId;

	private DefaultTableModel dtmProductList;
	private JTable jtProductList;

	// 수정
	public final static int FILE_SIZE = 6022386;

	public MarketMain() {
//			super("글쓰기");

		//////////////////////// 홈 화면 //////////////////////////////

//		홈, 카테고리, 글쓰기, 채팅, MyPage

		// JComboBox
		String[] dataArea = { "지역전체", "강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구",
				"동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구" };

		dcbm = new DefaultComboBoxModel<String>(dataArea);
		jcbArea = new JComboBox<String>(dcbm);

		String[] dataCategory = { "카테고리전체", "디지털/가전", "가구/인테리어", "유아동/유아도서", "생활/가공식품", "여성의류", "여성잡화", "뷰티/미용",
				"남성패션/잡화", "스포츠/레저", "게임/취미", "도서/티켓/음반", "반려동물용품", "기타 중고물품" };

		dcbm1 = new DefaultComboBoxModel<String>(dataCategory);
		jcbCategory = new JComboBox<String>(dcbm1);

		jtfSearch = new JTextField(10);
		jbSearch = new JButton("검색");

		JPanel panel1 = new JPanel();
		panel1.add(jcbArea);
		panel1.add(jtfSearch);
		panel1.add(jbSearch);

		// JRadioButton (+ 기능 추가 해야함 : 둘 중 한개만 선택할 수 있도록 -> 이거 안되면 checkboxgroup으로 묶기)
		jrbSubject = new JRadioButton("제목");
		jrbId = new JRadioButton("아이디");

		JPanel panel2 = new JPanel();
		panel2.add(jcbCategory);
		panel2.add(jrbSubject);
		panel2.add(jrbId);

		// JButton
		jbRecent = new JButton("최신순");
		jbPrice = new JButton("가격순");
		jbRefresh = new JButton("새로고침");

		JPanel panel3 = new JPanel();
		panel3.add(jbRecent);
		panel3.add(jbPrice);
		panel3.add(jbRefresh);

		// JTable

		String[] productColumn = { "이미지", "제품명", "지역", "가격", "시간" };

		dtmProductList = new DefaultTableModel(productColumn, 5);

		jtProductList = new JTable(dtmProductList);

		JScrollPane jspProductList = new JScrollPane(jtProductList);
		// 리스트 크기, 이동, 편집 불가능하게 설정

		jtProductList.setRowHeight(30);
		jtProductList.getTableHeader().setReorderingAllowed(false);

		jtProductList.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtProductList.getColumnModel().getColumn(1).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(2).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(50);

//		setResizable(false);

		// 컴포넌트 배치
		setLayout(null);

		panel1.setBounds(30, 30, 400, 40);
		panel2.setBounds(40, 70, 400, 40);
		panel3.setBounds(30, 110, 400, 40);
		jspProductList.setBounds(40, 190, 400, 200);

		add(panel1);
		add(panel2);
		add(panel3);
		add(jspProductList);

		setBounds(100, 100, 500, 600);

		setVisible(true);

//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//////////////////// 테스트용 수정
		jbRefresh.addActionListener(this);

	}// MarketMain

	public void sendFileList() throws UnknownHostException, IOException {
		int bytesRead;
		int current = 0;
		Socket sock = null;
		Socket sock2 = null;
		Socket client = null;
		Socket client2 = null;
		
		DataOutputStream dos = null;
		DataInputStream dis = null;
		//////////////////////////
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		DataOutputStream dos2 = null;
		InputStream is = null;
		


		try {
			// 2. 소켓생성 : 서버로 연결
			client = new Socket("211.63.89.159", 5000);
			client2 = new Socket("211.63.89.159",1025);
			// 4. 데이터를 주고 받을 스트림 연결
			is = client.getInputStream();
			dos2= new DataOutputStream(client.getOutputStream());
			dos = new DataOutputStream(client2.getOutputStream());
			dis = new DataInputStream(client2.getInputStream());

			File file = new File("c:\\dev\\filetest2");
			File[] temp = file.listFiles();

			StringBuilder csvFile = new StringBuilder();
			for (int i = 0; i < temp.length; i++) {
				if (i != 0) {
					csvFile.append(",");
				} // end if
				csvFile.append(temp[i].getName());
			} // end if

			dos.writeUTF(csvFile.toString()); // 111111

			int fileCnt = dis.readInt();//2222222222222222
			System.out.println(fileCnt+"앞으로 받을 파일 갯수");
			String revFileName = "";

			  // receive file
			
			for (int i = 0; i < fileCnt; i++) {
//				if(dis == null) {
//					dis = new DataInputStream(is);
//				}
		      byte [] mybytearray  = new byte [FILE_SIZE];
		      revFileName = dis.readUTF();//333333
		  	System.out.println(revFileName+"받을 파일 제목");
		  	int fileSize = dis.readInt();
		  	System.out.println(fileSize+"받을 파일 크기");
//		      dis.close();
		      File myFile = new File("c:\\dev\\filetest2\\"+revFileName);
		      fos = new FileOutputStream(myFile);
		      bos = new BufferedOutputStream(fos);
		     
		       current =0;
		       bytesRead =0;
		       
//		      bytesRead = is.read(mybytearray,0,mybytearray.length); //4444 이하 444
//		      current = bytesRead;
//		      System.out.println("첫번째 "+current);
//		      if(fileSize>65536) {
		    	  
		    	  do {
		    		  bytesRead =
		    				  is.read(mybytearray, current, (mybytearray.length-current));
		    		  if(bytesRead >= 0) current += bytesRead;
		    		  System.out.println(bytesRead+"/"+current+"/"+fileSize);
		    	  } while(current < fileSize);
//		      }
		      System.out.println("파일받기 일단 끝");
		      
		      bos.write(mybytearray, 0 , current);
		      bos.flush();
		      System.out.println("File " + "c:\\dev\\userFileTest\\"+revFileName
		          + " downloaded (" + current + " bytes read)");
		      bos.close();
//		      fos.close();
		      
		      dos.writeInt(0);
//		      dos2.writeUTF("파일 다받은 신호");///5555
//		      dos2.flush();
		  //    dos2.flush();
			} // end for
		      
//			for (int i = 0; i < fileCnt; i++) {
//				byte[] readData = new byte[512];
//				revFileName = dis.readUTF();
//				System.out.println(revFileName);
//				// 13. 파일생성
//				fos = new FileOutputStream("c:/dev/userFileTest/" + revFileName);
//				bos = new BufferedOutputStream(fos);
//
//				int len = 0;
//				while ((len = dis.read(readData)) > 0) {
//					bos.write(readData, 0, len);
//					System.out.println(len + "유저");
//				}
//				bos.close();
////					fos.flush();
//				fos.close();
//				// 14.thumbnail 파일 생성
//				// dos.writeUTF("Y");//파일전송 확인 메세지
//				System.out.println("유저 끝났다");
//				//////////////////////////
//			} // end for

			// 소켓을 열어서 서버에 연결
		} finally {
			if (dis != null)
				dis.close();
			if (dos != null)
				dos.close();
			if (client != null)
				client.close();
			if (fos != null)
				fos.close();
			if(client2 != null)
				client2.close();
		}
	}// sendFileList

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbRefresh) {
			try {
				sendFileList();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}// MarketMain
