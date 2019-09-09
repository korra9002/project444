package chatTest;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * 소켓을 생성하여 서버와 연결한 후, 메세지를 읽기위한 스트림과 메세지를 보내기위한 스트림을 연결하여 대화를 무한루프로 읽어 들여 T.A에
 * 출력하고, T.F에 입력된 내용이 이벤트가 발생하면 서버로 보내는 일.
 * 
 * @author owner
 *
 */
@SuppressWarnings("serial")
public class SimpleChatClient extends JFrame implements ActionListener {

	private JTextArea jtaDisplay;
	private JTextField jtfTalk;

	private Socket client;
	private DataInputStream disRead;
	private DataOutputStream dosWrite;
	private JScrollPane jsp;
	//private StringBuilder chat;
	private AdminDAO aDAO;
	private String me, you;
	

	public SimpleChatClient() {
		super("채팅 클라이언트");
		jtaDisplay = new JTextArea();
		jtfTalk = new JTextField();

		jsp = new JScrollPane(jtaDisplay);

		jsp.setBorder(new TitledBorder("대화내용"));
		jtfTalk.setBorder(new TitledBorder("대화입력"));

		jtaDisplay.setEditable(false);
		jtaDisplay.setBackground(Color.white);

		add("Center", jsp);
		add("South", jtfTalk);

		setBounds(100, 100, 500, 400);
		setVisible(true);

//		try {
//			connectToServer();
//			readMsg();
//		} catch (UnknownHostException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
	//	chat = new StringBuilder();
		aDAO = AdminDAO.getInstance();
		me = "백승규";
		you = "신수연";
		
		OrderThread ot = new OrderThread(jtaDisplay, me, you);
		ot.start();
		
		jtfTalk.addActionListener(this);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

				try {
					close();
				} catch (IOException e1) {
					e1.printStackTrace();
				} // end catch
				dispose();
			}// windowClosing

		});// addWindowListener

	}// SimpleChatServer

	/**
	 * 서버의 IP addres와 PORT를 설정하여 소켓을 생성하고, 서버에 연결된 다음 데이터를 읽거나 쓰기 위해 스트림을 연결
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 */
//	private void connectToServer() throws UnknownHostException, IOException {
//		// 소켓생성하여 : localhost, 127.0.0.1 자신 ip 를 넣으면 내 컴퓨터에 존재하는 서버에 접속
//		try {
//		client = new Socket("127.0.0.1", 55555);
////		client = new Socket("211.63.89.130", 9100);
//		// 읽기스트림 연결
//		disRead = new DataInputStream(client.getInputStream());
//		// 쓰기 스트림 연결
//		dosWrite = new DataOutputStream(client.getOutputStream());
//		jtaDisplay.setText("접속되었습니다. 즐거운 채팅 대화 나누세요.\n");
//		}catch(ConnectException ce) {
//			ce.printStackTrace();
//		}//end catch
//	}// connectToServer

	/**
	 * 읽기 스트림을 사용하여, 서버에 분출된 메세지를 무한루프로 읽어 들여 T.A에 출력 하는 일
	 * 
	 * @throws IOException
	 */
	private void readMsg() throws IOException {
		// 스트림이 객체가 생성되어있다면
		// 상대방이 보내오는 메세지를 무한루프로 읽어들여 내 창에 출력
//		if (disRead != null) {
//			while (true) {
//				jtaDisplay.append("^.~: " + disRead.readUTF() + "\n");
//				scrollPosition();
//			} // end if
//		} // end if
		
		
	}// readMsg
	
	private void scrollPosition() {
		jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
	}

	/**
	 * T.F에서 이벤트가 발생하면, T.F에 입력값을 가져와서 소켓으로 분출하는 일.
	 * 
	 * @throws IOException
	 */
	private void sendMsg() throws IOException {
		// 스트림이 연결되어 있다면
		String msg = jtfTalk.getText().trim();
		jtaDisplay.append("나 : "+msg+"\n");
		try {
			aDAO.sendChat(me, you, msg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jtfTalk.setText("");
		scrollPosition();
		
		
//		if (dosWrite != null) {
//			// T.F의 대화내용을 가져와서
//			String msg = jtfTalk.getText().trim();
//			// 내 대화창에 출력하고
//			//jtaDisplay.append("^^: " + msg + "\n");
//			// 소켓에 분출
//			dosWrite.writeUTF(msg);
//			dosWrite.flush();
//			// T.F의 내용을 초기화
//			jtfTalk.setText("");
//			scrollPosition();
//		} else {
//			JOptionPane.showMessageDialog(this, "대화상대가 없습니다.");
//		}
	}// sendMsg

	/**
	 * 소켓, 스트림 끊기
	 * 
	 * @throws IOException
	 */
	private void close() throws IOException {
		if (disRead != null)
			disRead.close();
		if (dosWrite != null)
			dosWrite.close();
		if (client != null)
			client.close();
	}// close

	public static void main(String[] args) {

	//	new SimpleChatClient();
		
		
		SimpleChatClient scc = new SimpleChatClient();
//		try {
//			scc.connectToServer();
//			scc.readMsg();
//		}catch(UnknownHostException ue) {
//			ue.printStackTrace();
//		}
//		
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			JOptionPane.showMessageDialog(scc,"대화상대가 접속을 종료하엿습니다.");
//			e.printStackTrace();
//		}//end catch
	}// main

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			sendMsg();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this,"대화상대가 접속을 종료하엿습니다.");
			e1.printStackTrace();
		} // end catch

	}// actionPerformed

	public JTextArea getJtaDisplay() {
		return jtaDisplay;
	}

	public JTextField getJtfTalk() {
		return jtfTalk;
	}

	public Socket getClient() {
		return client;
	}

	public DataInputStream getDisRead() {
		return disRead;
	}

	public DataOutputStream getDosWrite() {
		return dosWrite;
	}

	public JScrollPane getJsp() {
		return jsp;
	}



	public AdminDAO getaDAO() {
		return aDAO;
	}

}// class

