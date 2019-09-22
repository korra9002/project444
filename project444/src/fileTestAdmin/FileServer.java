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
		super("���ϼ���");
		dlmFileModel = new DefaultListModel<String>();
		JList<String> listFile = new JList<String>(dlmFileModel);
		JScrollPane jspFile = new JScrollPane(listFile);

		JButton btn = new JButton("���ϼ�������");
		jspFile.setBorder(new TitledBorder("���ϸ��"));
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
		// 1. ���� ���� ����
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
				//3.������ ������ �޴´�				
				client = fileserver.accept();
				//6. ���� �޾ƾ� �� Ƚ�� �ޱ�
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
				dos.writeUTF("���۽���"); // ���۽��� �˸�
			System.out.println((revCnt= dis.readInt())+"����Ƚ�� ����");//Ŭ���̾�Ʈ�� writeInt()�� �����͸� ����
				//System.out.println(revCnt);
				//8.���ϸ� �ޱ� 
				fileName = dis.readUTF();
				System.out.println(fileName);
				
//				System.out.println(revCnt+"/"+fileName);
				//10.�����ϴ� ���� �޾Ƽ� HDD ����
				writeFile = new File("c:/dev/adminFileTest/"+fileName);
				fos = new FileOutputStream(writeFile);//������ �����Ǿ���
				dos.writeUTF("���� ���۽���");
			while(revCnt >0) {
				len =dis.read(readData);//��Ʈ������ ���� ���������� �б�
				fos.write(readData,0,len);
				revCnt--;
			}//end while
			dos.writeUTF("���� ���۳���");
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
			setTitle("���� ���� ���� ��");
		} else {
			JOptionPane.showMessageDialog(this, "������ �̹� ������ �Դϴ�.");
		} // end if
	}// actionPerformed

	public static void main(String[] args) {
		new FileServer();
	}// main
}//class
