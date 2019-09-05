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
		  
		  // ����
		
		
		public MarketMain() {
//			super("�۾���");

			
			
			
		//////////////////////// Ȩ ȭ�� //////////////////////////////
		
//		Ȩ, ī�װ�, �۾���, ä��, MyPage
				
		
		//JComboBox
		String[] dataArea= {"������ü","������", "������", "���ϱ�", "������", "���Ǳ�", "������", "���α�", "��õ��",
				"�����", "������", "���빮��", "���۱�", "������", "���빮��", "���ʱ�", "������", "���ϱ�",
				"���ı�", "��õ��", "��������", "��걸", "����", "���α�", "�߱�", "�߶���"};
		
		dcbm = new DefaultComboBoxModel<String>(dataArea);	
		jcbArea=new JComboBox<String>(dcbm);
		
		String[] dataCategory= {"ī�װ���ü", "������/����", "����/���׸���", 	"���Ƶ�/���Ƶ���",
				"��Ȱ/������ǰ", 	"�����Ƿ�", "������ȭ", "��Ƽ/�̿�", "�����м�/��ȭ",
				"������/����", "����/���", "����/Ƽ��/����", "�ݷ�������ǰ", "��Ÿ �߰�ǰ"};
		
		dcbm1 = new DefaultComboBoxModel<String>(dataCategory);	
		jcbCategory=new JComboBox<String>(dcbm1);
		
		jtfSearch=new JTextField(10);
		jbSearch=new JButton("�˻�");
		
		JPanel panel1=new JPanel();
		panel1.add(jcbArea);
		panel1.add(jtfSearch);
		panel1.add(jbSearch);
		
		
		//JRadioButton (+ ��� �߰� �ؾ��� : �� �� �Ѱ��� ������ �� �ֵ��� -> �̰� �ȵǸ� checkboxgroup���� ����)
		jrbSubject=new JRadioButton("����");
		jrbId=new JRadioButton("���̵�");
		
		JPanel panel2=new JPanel();
		panel2.add(jcbCategory);
		panel2.add(jrbSubject);
		panel2.add(jrbId);
		
		//JButton 
		jbRecent=new JButton("�ֽż�");
		jbPrice=new JButton("���ݼ�");
		jbRefresh=new JButton("���ΰ�ħ");
		
		JPanel panel3=new JPanel();
		panel3.add(jbRecent);
		panel3.add(jbPrice);
		panel3.add(jbRefresh);
		
		
		//JTable
		
		String[] productColumn= {"�̹���", "��ǰ��", "����", "����", "�ð�" };
		
		dtmProductList=new DefaultTableModel(productColumn, 5);
		
		jtProductList=new JTable(dtmProductList);
		
		JScrollPane jspProductList=new JScrollPane(jtProductList);
		//����Ʈ ũ��, �̵�, ���� �Ұ����ϰ� ����
		
		
		jtProductList.setRowHeight(30);
		jtProductList.getTableHeader().setReorderingAllowed(false);
		
		jtProductList.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtProductList.getColumnModel().getColumn(1).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(2).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(30);
		jtProductList.getColumnModel().getColumn(3).setPreferredWidth(50);
		
//		setResizable(false);
		
		
		// ������Ʈ ��ġ 
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
		
		////////////////////�׽�Ʈ�� ���� 
		jbRefresh.addActionListener(this);
			
		}//MarketMain

		public void sendFileList() throws UnknownHostException, IOException {

			Socket client = null;
			DataOutputStream dos = null;
			DataInputStream dis = null;
			FileOutputStream fos = null;
			
			try {
				// 2. ���ϻ��� : ������ ����
				client = new Socket("211.63.89.159", 5000);
				// 4. �����͸� �ְ� ���� ��Ʈ�� ����
				dos = new DataOutputStream(client.getOutputStream());
				dis = new DataInputStream(client.getInputStream());

				File file = new File("c:/dev/fileTest2");
				File[] temp = file.listFiles();

				StringBuilder csvFile = new StringBuilder();
				for (int i = 0; i < temp.length; i++) {
					//if (!temp[i].getName().startsWith("rs_")) {
						if (i != 0) {
							csvFile.append(",");
						} // end if
						csvFile.append(temp[i].getName());
					//} // end if
				} // end if

				// 5. ������ ���ϸ���Ʈ CSV Data ������
				dos.writeUTF(csvFile.toString()); // ���ڿ��� ��Ʈ���� ���
		//		dos.flush();// ��Ʈ���� ������ �������� ����

				// 9. �������� ���� ������ ������ ���� ���� �޾� �� Ƚ����ŭ �ݺ���Ų��.
				int fileCnt = dis.readInt();
				int readCnt = 0;
				String revFileName = "";
				
				byte[] readData = new byte[512];
				int readSize = 0;
				for (int i = 0; i < fileCnt; i++) {
					dos.writeUTF("Y"); //������ ���۹ޱ� ���� �÷��� ���� ������ ����
					// 10.�о���������� Ƚ�� �ޱ�
					readCnt = dis.readInt();
					// 12. ���ϸ� �ޱ�
					revFileName = dis.readUTF();
					System.out.println(revFileName);
					// 13. ���ϻ���
					fos = new FileOutputStream("c:/dev/fileTest2/"+revFileName);
					dos.writeUTF("Y");// ���� �ޱ� ���� Ȯ�� 
					while(readCnt > 0) {
						readSize = dis.read(readData);
						fos.write(readData, 0,readSize);
						readCnt--;
					}//end while
//					fos.flush();
					fos.close();
					//14.thumbnail ���� ����
					dos.writeUTF("Y");//�������� Ȯ�� �޼���
					
					//////////////////////////
				//	dis.close();
				//	dis = new DataInputStream(client.getInputStream());
					
					//ImageResize.resizeImage("C:/dev/workspace/jdbc_prj/src/kr/co/sist/user/img/"+revFileName, 100, 80);
				} // end for
//			System.out.println(csvFile);

				// ������ ��� ������ ����
			} finally {
				if (dis != null)
					dis.close();
				if (dos != null)
					dos.close();
				if (client != null)
					client.close();
				if (fos != null)
					fos.close();
			}
		}// sendFileList
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == jbRefresh) {
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
		
		
		

	}//MarketMain


