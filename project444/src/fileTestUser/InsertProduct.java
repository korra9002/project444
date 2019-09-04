package fileTestUser;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class InsertProduct extends JFrame implements ActionListener {

	private JLabel jlbProductImg;
	private JButton jbtSelectImg, jbtOkey, jbtCancle ;
	private JTextField jtfSubject, jtfPrice;
	private JTextArea jtaExplain;
	private JComboBox<String> jcbCategory;
	private DefaultComboBoxModel<String> dcbCategory; 
	
	
	///////////////////////////////////////테스트용 수정
	private File file = null;
	
	
	public InsertProduct() {
//		super("제품 입력");
		
	

		jlbProductImg = new JLabel("제품 이미지",JLabel.CENTER);
		jlbProductImg.setBorder(new EtchedBorder(EtchedBorder.RAISED));//이미지 라벨 테두리 설정
	
		
		
		
		jbtSelectImg = new JButton("사진 등록");
		
		String[] categoryList= {"디지털/가전","가구/인테리어","유아동/유아도서","생활/가공식품","여성의류","여성잡화",
							"뷰티/미용","남성패션/잡화","스포츠/레저","게임/취미","도서/티켓/음반","반려동물용품","기타 중고물품"};
		
		dcbCategory = new DefaultComboBoxModel<String>(categoryList);
		jcbCategory = new JComboBox<String>(dcbCategory);
		jcbCategory.setBorder(new TitledBorder("카테고리 종류"));
		
		jtfSubject = new JTextField("글 제목");//이벤트처리-클릭 시 텍스트 사라지게
		jtfPrice = new JTextField("가격 입력");//이벤트처리-클릭 시 텍스트 사라지게
		
		jtaExplain = new JTextArea("상세 설명");//이벤트처리-클릭 시 텍스트 사라지게
		JScrollPane jspExplain = new JScrollPane(jtaExplain);
		
		jbtOkey = new JButton("완료");
		jbtCancle = new JButton("취소");
		
		setLayout(null);
		
//		jtp.setBounds(0, 10, 400, 30);
		
		
		
		jlbProductImg.setBounds(40, 20, 120, 100);//이미지 사이즈 120x100
		jbtSelectImg.setBounds(180, 65, 100, 30);
		jcbCategory.setBounds(300, 53, 120, 50);
		jtfSubject.setBounds(40, 170, 385, 30);
		jtfPrice.setBounds(40, 210, 385, 30);
		jspExplain.setBounds(40, 250, 385, 240);
		jbtOkey.setBounds(130, 510, 80, 30);
		jbtCancle.setBounds(260, 510, 80, 30);
		
		add(jlbProductImg);
		add(jbtSelectImg);
		add(jcbCategory);
		add(jtfSubject);
		add(jtfPrice);
		add(jspExplain);
		add(jbtOkey);
		add(jbtCancle);
		

		
		setBounds(100, 100, 420, 640);
		setVisible(true);
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//////////////////// 테스트용 수정
		jbtSelectImg.addActionListener(this);		
		jbtOkey.addActionListener(this);
		
		
		
		
	}//InsertProduct
	
	public static void main(String[] args) {
//		new InsertProduct();
	}//main

	////////////////////////// 테스트용 메서드////////////////////////
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbtSelectImg) {
			try {
				fileOpen();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}//end if
		
		if(e.getSource() == jbtOkey) {
			try {
				uploadFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void fileOpen() throws UnknownHostException, IOException {
		FileDialog fdSelect = new FileDialog(this, "업로드 파일 선택", FileDialog.LOAD);
		fdSelect.setVisible(true);

		String path = fdSelect.getDirectory();
		String name = fdSelect.getFile();
		String pathAndName ="";
		if (path != null && name != null) {
			pathAndName = path + name;
			file = new File(pathAndName);
			if ((name.toLowerCase().endsWith(".jpg")||name.toLowerCase().endsWith(".png")) && file.exists()) {
				try {
//					this.setTitle(file.getName());
//					t = new Test(file);
//					jlnotice.setText(String.format(" 범위  [ %d - %d ]", 1, t.getLastIndex()));
					// openFlag = true;
					jlbProductImg.setIcon(new ImageIcon(pathAndName));
					return;
				} catch (NullPointerException ne) {

				}

			} else {

				JOptionPane.showMessageDialog(this, "이미지 파일이 아닙니다.");
				fileOpen();
			}//end else
		}//end if

	
	}// fileSend
	
	private void uploadFile() throws IOException {
		if (file != null && file.exists()) {
			switch (JOptionPane.showConfirmDialog(this, file.getName() + "을 업로드 하시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				// 2.소켓을 열어 서버에 접속
				Socket fileClient = null;
				FileInputStream fis = null;
				DataOutputStream dos = null;
				DataInputStream dis = null;
				try {
					fileClient = new Socket("211.63.89.149", 1025);
					// 4.선택한 파일을 전송하기 위해 전처리(일의 조각을 몇번 전송할 것 인지 전송 갯수 계산)
					int sendCnt = 0;
//					File file = new File(path + name);
					fis = new FileInputStream(file);
					byte[] readData = new byte[512];
					int len = 0;

					while ((len = fis.read(readData)) != -1) {
						sendCnt++;// 읽어들인 데이터가 존재하면 전송해야할 횟수를 증가
					} // end while
					System.out.println(sendCnt);
					// 5.전송 횟수를 전송
					dis = new DataInputStream(fileClient.getInputStream());
					dos = new DataOutputStream(fileClient.getOutputStream());
					System.out.println(dis.readUTF());// 전송시작 메세지 받기
					
					
					dos.write(sendCnt);// 전송횟수 보냄

					// 7.파일명 전송
					dos.writeUTF(file.getName());
					// 파일 스트림 끊기(전송횟수를 구하는 동안 포인터가 끝으로 이동)
					fis.close();
					// 9. 파일을 전송
					// 새로 스트림이 생성되면 포인터가 가장 앞으로 이동.
					fis = new FileInputStream(file);
					System.out.println(dis.readUTF());////파일전송시작 메세지 받기
					while (sendCnt > 0) {
						len = fis.read(readData); // 파일에서 읽어들여
						dos.write(readData, 0, len);// 출력스트림에 기록
						sendCnt--;

					} // end
					dos.flush();
					System.out.println(dis.readUTF());//전송 끝남 메세지 받기
					JOptionPane.showMessageDialog(this, file + "이 전송 되었습니다.");

				} finally {
					// 전송 완료 후 연결 끊기
					if (dos != null)
						dos.close();
					if (fis != null)
						fis.close();
					if (fileClient != null)
						fileClient.close();
				} // end finally

			}// end switch
		} else {
			JOptionPane.showMessageDialog(this, "이미지를 선택하세요");
		}//end if

	
	}//uploadFile
	
	
	
}//class
