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
 * ������ �����Ͽ� ������ ������ ��, �޼����� �б����� ��Ʈ���� �޼����� ���������� ��Ʈ���� �����Ͽ� ��ȭ�� ���ѷ����� �о� �鿩 T.A��
 * ����ϰ�, T.F�� �Էµ� ������ �̺�Ʈ�� �߻��ϸ� ������ ������ ��.
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
		super("ä�� Ŭ���̾�Ʈ");
		jtaDisplay = new JTextArea();
		jtfTalk = new JTextField();

		jsp = new JScrollPane(jtaDisplay);

		jsp.setBorder(new TitledBorder("��ȭ����"));
		jtfTalk.setBorder(new TitledBorder("��ȭ�Է�"));

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
		me = "��±�";
		you = "�ż���";
		
		OrderThread ot = new OrderThread(jtaDisplay, me, you, jsp);
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
	 * ������ IP addres�� PORT�� �����Ͽ� ������ �����ϰ�, ������ ����� ���� �����͸� �аų� ���� ���� ��Ʈ���� ����
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 */
//	private void connectToServer() throws UnknownHostException, IOException {
//		// ���ϻ����Ͽ� : localhost, 127.0.0.1 �ڽ� ip �� ������ �� ��ǻ�Ϳ� �����ϴ� ������ ����
//		try {
//		client = new Socket("127.0.0.1", 55555);
////		client = new Socket("211.63.89.130", 9100);
//		// �б⽺Ʈ�� ����
//		disRead = new DataInputStream(client.getInputStream());
//		// ���� ��Ʈ�� ����
//		dosWrite = new DataOutputStream(client.getOutputStream());
//		jtaDisplay.setText("���ӵǾ����ϴ�. ��ſ� ä�� ��ȭ ��������.\n");
//		}catch(ConnectException ce) {
//			ce.printStackTrace();
//		}//end catch
//	}// connectToServer

	/**
	 * �б� ��Ʈ���� ����Ͽ�, ������ ����� �޼����� ���ѷ����� �о� �鿩 T.A�� ��� �ϴ� ��
	 * 
	 * @throws IOException
	 */
	private void readMsg() throws IOException {
		// ��Ʈ���� ��ü�� �����Ǿ��ִٸ�
		// ������ �������� �޼����� ���ѷ����� �о�鿩 �� â�� ���
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
	 * T.F���� �̺�Ʈ�� �߻��ϸ�, T.F�� �Է°��� �����ͼ� �������� �����ϴ� ��.
	 * 
	 * @throws IOException
	 */
	private void sendMsg() throws IOException {
		// ��Ʈ���� ����Ǿ� �ִٸ�
		String msg = jtfTalk.getText().trim();
		jtaDisplay.append("�� : "+msg+"\n");
		try {
			aDAO.sendChat(me, you, msg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jtfTalk.setText("");
		scrollPosition();
		
		
//		if (dosWrite != null) {
//			// T.F�� ��ȭ������ �����ͼ�
//			String msg = jtfTalk.getText().trim();
//			// �� ��ȭâ�� ����ϰ�
//			//jtaDisplay.append("^^: " + msg + "\n");
//			// ���Ͽ� ����
//			dosWrite.writeUTF(msg);
//			dosWrite.flush();
//			// T.F�� ������ �ʱ�ȭ
//			jtfTalk.setText("");
//			scrollPosition();
//		} else {
//			JOptionPane.showMessageDialog(this, "��ȭ��밡 �����ϴ�.");
//		}
	}// sendMsg

	/**
	 * ����, ��Ʈ�� ����
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
//			JOptionPane.showMessageDialog(scc,"��ȭ��밡 ������ �����Ͽ����ϴ�.");
//			e.printStackTrace();
//		}//end catch
	}// main

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			sendMsg();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this,"��ȭ��밡 ������ �����Ͽ����ϴ�.");
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

