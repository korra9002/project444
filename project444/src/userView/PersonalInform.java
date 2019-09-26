package userView;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import userControl.PersonalInformEvt;
import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.PersonalInformVO;

public class PersonalInform extends JDialog {
		
		private JTextField jtfId, jtfName, jtfPhone1,jtfPhone2,jtfGender, jtfPwAnswer;
		private JButton jbtRegister, jbtCancle ,jbtPwUpdate;
		private DefaultComboBoxModel<String> dcbPhoneNum,dcbLoc, dcbPwHint;
		private JComboBox<String> jcbPhoneNum,jcbLoc, jcbPwHint;
		
		public PersonalInform(String id, RunMarketMain rmm) throws SQLException {
			super(rmm,"�������� ����");
			// JLabel
			JLabel jlId = new JLabel("���̵�  ");
			JLabel jlPw = new JLabel("��й�ȣ  ");
			JLabel jlName = new JLabel("�̸�  ");
			JLabel jlGender = new JLabel("����");
			JLabel jlPhone = new JLabel("����ó");
			JLabel jlLoc = new JLabel("����");
			JLabel jlPwHint = new JLabel("��й�ȣ ��Ʈ");
			JLabel jlPwAnswer = new JLabel("��Ʈ ����");
			//ȸ�� ���� ��������
			UserDAO uDAO = UserDAO.getInstance();
			PersonalInformVO piVO = uDAO.selectPersonalInfom(id);
			 
			
			String phone= piVO.getPhone();
			String phone0 = phone.substring(0,3);
			String phone1 = phone.substring(phone.indexOf("-")+1,phone.lastIndexOf("-"));
			String phone2 = phone.substring(phone.lastIndexOf("-")+1);
			String gender = piVO.getGender();
			int locCode = Integer.valueOf(piVO.getLoc());
			if(gender.equals("M")) {
				gender="����";
			}else {
				gender ="����";
			}
			// JTextField
			jtfId = new JTextField(id);
			jtfName = new JTextField(piVO.getName());
			jtfPhone1 = new JTextField(phone1);
			jtfPhone2 = new JTextField(phone2);
			jtfGender = new JTextField(gender);
			jtfPwAnswer = new JTextField();


			// JButton
			jbtRegister = new JButton("���");
			jbtCancle = new JButton("���");
			jbtPwUpdate = new JButton("��й�ȣ ����");

		
			// Loc ComboBox
			String[] loc = { "-��������-","������", "������", "���ϱ�", "������", "���Ǳ�", "������", "���α�", "��õ��", "�����", "������", "���빮��", "���۱�", "������",
					"���빮��", "���ʱ�", "������", "���ϱ�", "���ı�", "��õ��", "��������", "��걸", "����", "���α�", "�߱�", "�߶���" };
			dcbLoc = new DefaultComboBoxModel<String>(loc);
			// Password Hint ComboBox
			String[] PwHint = { "-��й�ȣ ��Ʈ ����-","���� �¿����?", "���� ���� �� 1ȣ��?", "���� �޴��� ��ȣ �� ���ڸ���?", "���� ��￡ ���ų� �����ϴ� ��Ҵ�?", "���� ģ�� ģ�� �̸���?",
					"���� �ް� ���� ������?", "���� �����ϴ� �뷡 ������?", "���� �����ϴ� ��������?", "���� �����ϴ� ������?", "���� �����ϴ� ���ڴ�?", "���� �����ϴ� ������?" };
			dcbPwHint = new DefaultComboBoxModel<String>(PwHint);
			// PhoneNum ComboBox
			String[] phoneNum = {"010","011","012","016","017","018"};
			dcbPhoneNum = new DefaultComboBoxModel<String>(phoneNum);
			// JComboBox
			jcbLoc = new JComboBox<String>(dcbLoc);
			jcbLoc.setSelectedIndex(locCode);
			jcbPwHint = new JComboBox<String>(dcbPwHint);
			jcbPhoneNum = new JComboBox<String>(dcbPhoneNum);
			jcbPhoneNum.setSelectedItem(phone0);
			// ������ġ
			setLayout(null);

			jlId.setBounds(60, 100, 80, 25);
			jlPw.setBounds(60, 150, 80, 25);
			jlName.setBounds(60, 200, 50, 25);
			jlGender.setBounds(60, 260, 50, 25);
			jlPhone.setBounds(60, 310, 60, 25);
			jlLoc.setBounds(60, 360, 50, 25);
			jlPwHint.setBounds(40, 410, 100, 25);
			jlPwAnswer.setBounds(60, 470, 100, 25);

			jtfId.setBounds(140, 100, 180, 25);
			jtfName.setBounds(140, 200, 100, 25);
			jtfPwAnswer.setBounds(140, 470, 250, 25);

			jtfGender.setBounds(140, 260, 100, 25);

			jcbLoc.setBounds(140, 360, 150, 25);
			jcbPwHint.setBounds(140, 410, 250, 25);
			
			jbtRegister.setBounds(230, 580, 100, 30);
			jbtCancle.setBounds(360, 580, 100, 30);
			jbtPwUpdate.setBounds(140, 150, 220, 25);
			
			
			//����ó �г�
			JPanel jpPhone = new JPanel(null);
			JLabel Hyphen1 = new JLabel("-");
			JLabel Hyphen2 = new JLabel("-");
			jcbPhoneNum.setBounds(0, 0, 50, 25);
			Hyphen1.setBounds(60, 0, 5, 25);
			jtfPhone1.setBounds(75, 0, 50, 25);
			Hyphen2.setBounds(135, 0, 5, 25);
			jtfPhone2.setBounds(150, 0, 50, 25);
			jpPhone.add(jcbPhoneNum);
			jpPhone.add(Hyphen1);
			jpPhone.add(jtfPhone1);
			jpPhone.add(Hyphen2);
			jpPhone.add(jtfPhone2);
			jpPhone.setBounds(140,305,250,35);
			
			
			// ��ġ
			add(jlId);
			add(jlPw);
			add(jlName);
			add(jlGender);
			add(jlLoc);
			add(jlPhone);
			add(jlPwHint);
			add(jlPwAnswer);

			add(jtfId);
			add(jtfName);
			add(jtfPwAnswer);
			add(jtfGender);
			
			add(jcbLoc);
			add(jcbPwHint);
			
			add(jbtRegister);
			add(jbtCancle);
			add(jbtPwUpdate);
			
			add(jpPhone);
			//�̺�Ʈó��
			PersonalInformEvt psie = new PersonalInformEvt(this,rmm); 
			jbtPwUpdate.addActionListener(psie);
			jbtCancle.addActionListener(psie);
			jbtRegister.addActionListener(psie);
			
			//�����Ұ�
			jtfId.setEditable(false);
			jtfGender.setEditable(false);
			jtfName.setEditable(false);
			//���� ����
			jtfId.setBackground(new Color(0xf6f2ef));
			jtfName.setBackground(new Color(0xf6f2ef));
			jtfGender.setBackground(new Color(0xf6f2ef));
			jtfName.setBorder(new LineBorder(null, 0));
			jtfId.setBorder(new LineBorder(null, 0));
			jtfGender.setBorder(new LineBorder(null, 0));
			// �� ������
			Container c = getContentPane();
			c.setBackground(new Color(0xf6f2ef));
			jbtRegister.setBackground(new Color(0xFFCC66));
			jbtCancle.setBackground(new Color(0xFFCC66));
			jbtPwUpdate.setBackground(new Color(0xFFCC66));
			jpPhone.setBackground(new Color(0xf6f2ef));
			jcbLoc.setBackground(new Color(0xFFFFFF));
			jcbPhoneNum.setBackground(new Color(0xFFFFFF));
			jcbPwHint.setBackground(new Color(0xFFFFFF));
			
			// WindowSizing
			setBounds(100, 100, 550, 700);
			// visible
			setVisible(true);
		
		
		}// SineUp

		public JTextField getJtfId() {
			return jtfId;
		}//getJtfId

		public JTextField getJtfName() {
			return jtfName;
		}//getJtfName

		public JTextField getJtfPhone1() {
			return jtfPhone1;
		}//getJtfPhone1

		public JTextField getJtfPhone2() {
			return jtfPhone2;
		}//getJtfPhone2

		public JTextField getJtfGender() {
			return jtfGender;
		}//getJtfGender

		public JTextField getJtfPwAnswer() {
			return jtfPwAnswer;
		}//getJtfPwAnswer

		public JButton getJbtRegister() {
			return jbtRegister;
		}//getJbtRegister

		public JButton getJbtCancle() {
			return jbtCancle;
		}//getJbtCancle

		public JButton getJbtPwUpdate() {
			return jbtPwUpdate;
		}//getJbtPwUpdate

		public DefaultComboBoxModel<String> getDcbPhoneNum() {
			return dcbPhoneNum;
		}//getDcbPhoneNum

		public DefaultComboBoxModel<String> getDcbLoc() {
			return dcbLoc;
		}//getDcbLoc

		public DefaultComboBoxModel<String> getDcbPwHint() {
			return dcbPwHint;
		}//getDcbPwHint

		public JComboBox<String> getJcbPhoneNum() {
			return jcbPhoneNum;
		}//getJcbPhoneNum

		public JComboBox<String> getJcbLoc() {
			return jcbLoc;
		}//getJcbLoc

		public JComboBox<String> getJcbPwHint() {
			return jcbPwHint;
		}//getJcbPwHint

	
		



}//class
