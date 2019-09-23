package fileTestAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;



public class FileServer  extends JFrame implements ActionListener, Runnable {
	private DefaultListModel<String> dlmFileModel;
	private ServerSocket fileserver;
	private Thread fileThread;

	public FileServer() {
		super("파일서버");
		dlmFileModel = new DefaultListModel<String>();
		JList<String> listFile = new JList<String>(dlmFileModel);
		JScrollPane jspFile = new JScrollPane(listFile);

		JButton btn = new JButton("파일서버시작");
		jspFile.setBorder(new TitledBorder("파일목록"));
		JPanel jpSouth = new JPanel();
		jpSouth.add(btn);

		add("Center", jspFile);
		add("South", jpSouth);

		setBounds(100, 100, 500, 300);
		setVisible(true);

		btn.addActionListener(this);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}// windowClosing

			@Override
			public void windowClosed(WindowEvent e) {

				try {
					if (fileserver != null)
						fileserver.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					System.exit(ABORT);
				}
			}// windowClosed

		});

	}// FileServer
 
	@Override
	public void run() {
		// 1. 서버 소켓 열기
		try {
			fileserver = new ServerSocket(5001);
			Socket client =null;
			DataInputStream dis = null;
			DataOutputStream dos = null;
			FileOutputStream fos = null;
			int revCnt =0;
			String fileName = "";
			File writeFile = null;
			byte[] readData = new byte[512];
			int len = 0;
			for(int i = 0; ; i++) {
				//3.접속자 소켓을 받는다				
				client = fileserver.accept();
				//6. 전송 받아야 할 횟수 받기
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
				dos.writeUTF("전송시작"); // 전송시작 알림
			System.out.println((revCnt= dis.readInt())+"전송횟수 받음");//클라이언트가 writeInt()로 데이터를 전송
				//System.out.println(revCnt);
				//8.파일명 받기 
				fileName = dis.readUTF();
				System.out.println(fileName);
				
//				System.out.println(revCnt+"/"+fileName);
				//10.전송하는 파일 받아서 HDD 생성
				writeFile = new File("c:/dev/adminFileTest/"+fileName);
				fos = new FileOutputStream(writeFile);//파일이 생성되었음
				dos.writeUTF("파일 전송시작");
			while(revCnt >0) {
				len =dis.read(readData);//스트림에서 보낸 파일정보를 읽기
				fos.write(readData,0,len);
				revCnt--;
			}//end while
			dos.writeUTF("파일 전송끝남");
			dlmFileModel.addElement(writeFile.getAbsolutePath());
			fos.close();
			client.close();
			
			}//end for
		} catch (IOException e) {
			
			e.printStackTrace();
		} // end catch
	}// run

	@Override
	public void actionPerformed(ActionEvent e) {
		if (fileThread == null) {
			fileThread = new Thread(this);
			fileThread.start();
			setTitle("파일 서버 가동 중");
		} else {
			JOptionPane.showMessageDialog(this, "서버가 이미 가동중 입니다.");
		} // end if
	}// actionPerformed

	public static void main(String[] args) {
		new FileServer();
	}// main
}//class
