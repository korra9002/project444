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
	
	
	///////////////////////////////////////�׽�Ʈ�� ����
	private File file = null;
	
	
	public InsertProduct() {
//		super("��ǰ �Է�");
		
	

		jlbProductImg = new JLabel("��ǰ �̹���",JLabel.CENTER);
		jlbProductImg.setBorder(new EtchedBorder(EtchedBorder.RAISED));//�̹��� �� �׵θ� ����
	
		
		
		
		jbtSelectImg = new JButton("���� ���");
		
		String[] categoryList= {"������/����","����/���׸���","���Ƶ�/���Ƶ���","��Ȱ/������ǰ","�����Ƿ�","������ȭ",
							"��Ƽ/�̿�","�����м�/��ȭ","������/����","����/���","����/Ƽ��/����","�ݷ�������ǰ","��Ÿ �߰�ǰ"};
		
		dcbCategory = new DefaultComboBoxModel<String>(categoryList);
		jcbCategory = new JComboBox<String>(dcbCategory);
		jcbCategory.setBorder(new TitledBorder("ī�װ� ����"));
		
		jtfSubject = new JTextField("�� ����");//�̺�Ʈó��-Ŭ�� �� �ؽ�Ʈ �������
		jtfPrice = new JTextField("���� �Է�");//�̺�Ʈó��-Ŭ�� �� �ؽ�Ʈ �������
		
		jtaExplain = new JTextArea("�� ����");//�̺�Ʈó��-Ŭ�� �� �ؽ�Ʈ �������
		JScrollPane jspExplain = new JScrollPane(jtaExplain);
		
		jbtOkey = new JButton("�Ϸ�");
		jbtCancle = new JButton("���");
		
		setLayout(null);
		
//		jtp.setBounds(0, 10, 400, 30);
		
		
		
		jlbProductImg.setBounds(40, 20, 120, 100);//�̹��� ������ 120x100
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
		//////////////////// �׽�Ʈ�� ����
		jbtSelectImg.addActionListener(this);		
		jbtOkey.addActionListener(this);
		
		
		
		
	}//InsertProduct
	
	public static void main(String[] args) {
//		new InsertProduct();
	}//main

	////////////////////////// �׽�Ʈ�� �޼���////////////////////////
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
		FileDialog fdSelect = new FileDialog(this, "���ε� ���� ����", FileDialog.LOAD);
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
//					jlnotice.setText(String.format(" ����  [ %d - %d ]", 1, t.getLastIndex()));
					// openFlag = true;
					jlbProductImg.setIcon(new ImageIcon(pathAndName));
					return;
				} catch (NullPointerException ne) {

				}

			} else {

				JOptionPane.showMessageDialog(this, "�̹��� ������ �ƴմϴ�.");
				fileOpen();
			}//end else
		}//end if

	
	}// fileSend
	
	private void uploadFile() throws IOException {
		if (file != null && file.exists()) {
			switch (JOptionPane.showConfirmDialog(this, file.getName() + "�� ���ε� �Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				// 2.������ ���� ������ ����
				Socket fileClient = null;
				FileInputStream fis = null;
				DataOutputStream dos = null;
				DataInputStream dis = null;
				try {
					fileClient = new Socket("localhost", 5001);
					System.out.println("���� ����");
					// 4.������ ������ �����ϱ� ���� ��ó��(���� ������ ��� ������ �� ���� ���� ���� ���)
					int sendCnt = 0;
//					File file = new File(path + name);
					fis = new FileInputStream(file);
					byte[] readData = new byte[512];
					int len = 0;

					while ((len = fis.read(readData)) != -1) {
						sendCnt++;// �о���� �����Ͱ� �����ϸ� �����ؾ��� Ƚ���� ����
					} // end while
					System.out.println(sendCnt+"Ƚ��");
					// 5.���� Ƚ���� ����
					dis = new DataInputStream(fileClient.getInputStream());
					dos = new DataOutputStream(fileClient.getOutputStream());
					System.out.println(dis.readUTF());// ���۽��� �޼��� �ޱ�
					
					
					dos.writeInt(sendCnt);// ����Ƚ�� ����

					// 7.���ϸ� ����
					dos.writeUTF(file.getName());
					// ���� ��Ʈ�� ����(����Ƚ���� ���ϴ� ���� �����Ͱ� ������ �̵�)
					fis.close();
					// 9. ������ ����
					// ���� ��Ʈ���� �����Ǹ� �����Ͱ� ���� ������ �̵�.
					fis = new FileInputStream(file);
					System.out.println(dis.readUTF());////�������۽��� �޼��� �ޱ�
					while (sendCnt > 0) {
						len = fis.read(readData); // ���Ͽ��� �о�鿩
						dos.write(readData, 0, len);// ��½�Ʈ���� ���
						sendCnt--;

					} // end
					dos.flush();
					System.out.println(dis.readUTF());//���� ���� �޼��� �ޱ�
					JOptionPane.showMessageDialog(this, file + "�� ���� �Ǿ����ϴ�.");

				} finally {
					// ���� �Ϸ� �� ���� ����
					if (dos != null)
						dos.close();
					if (fis != null)
						fis.close();
					if (fileClient != null)
						fileClient.close();
				} // end finally

			}// end switch
		} else {
			JOptionPane.showMessageDialog(this, "�̹����� �����ϼ���");
		}//end if

	
	}//uploadFile
	
	
	
}//class
