package userView;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import userControl.PersonalInformEvt;
import userRun.RunMarketMain;

public class PersonalInform extends JDialog {
		private JLabel jlId, jlPw, jlName, jlGender, jlPhone, jlLoc, jlPwHint, jlPwAnswer;
		private JTextField jtfId, jtfPass, jtfName, jtfPhone, jtfPwAnswer;
		private JPasswordField jpfPw;
		private JButton jbtRegister, jbtCancle ,jbtPwUpdate;
		private ButtonGroup bgGender;
		private JRadioButton jrbWomen, jrbMan;
		private DefaultComboBoxModel<String> dcbPhoneNum,dcbLoc, dcbPwHint;
		private JComboBox<String> jcbPhoneNum,jcbLoc, jcbPwHint;
		private String[] PwHint;

		public PersonalInform(String id, RunMarketMain rmm) {
			super(rmm,"�������� ����");
			// JLabel
			jlId = new JLabel("���̵�  ");
			jlPw = new JLabel("��й�ȣ  ");
			jlName = new JLabel("�̸�  ");
			jlGender = new JLabel("����");
			jlPhone = new JLabel("����ó");
			jlLoc = new JLabel("����");
			jlPwHint = new JLabel("��й�ȣ ��Ʈ");
			jlPwAnswer = new JLabel("��Ʈ ����");

			// JTextField
			jtfId = new JTextField(id);
			jtfPass = new JTextField();
			jtfName = new JTextField();
			jtfPhone = new JTextField();
			jtfPwAnswer = new JTextField();

			// JPasswordField
			jpfPw = new JPasswordField();

			// JButton
			jbtRegister = new JButton("���");
			jbtCancle = new JButton("���");
			jbtPwUpdate = new JButton("��й�ȣ ����");

			// CheckBoxGroup
			bgGender = new ButtonGroup();
			// CheckBox
			jrbWomen = new JRadioButton("����");
			jrbMan = new JRadioButton("����");
			
			bgGender.add(jrbMan);
			bgGender.add(jrbWomen);
			
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
			jcbPwHint = new JComboBox<String>(dcbPwHint);
			jcbPhoneNum = new JComboBox<String>(dcbPhoneNum);
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
			jtfName.setBounds(140, 200, 220, 25);
			jtfPhone.setBounds(205, 310, 180, 25);
			jtfPwAnswer.setBounds(140, 470, 250, 25);

			jrbMan.setBounds(160, 260, 100, 20);
			jrbWomen.setBounds(260, 260, 100, 20);

			jcbLoc.setBounds(140, 360, 150, 25);
			jcbPwHint.setBounds(140, 410, 250, 25);
			jcbPhoneNum.setBounds(140, 310, 60, 25);
			
			jbtRegister.setBounds(230, 580, 100, 30);
			jbtCancle.setBounds(360, 580, 100, 30);
			jbtPwUpdate.setBounds(140, 150, 220, 25);

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
			add(jtfPhone);
			add(jtfPwAnswer);

			add(jrbMan);
			add(jrbWomen);

			add(jcbLoc);
			add(jcbPwHint);
			add(jcbPhoneNum);
			
			add(jbtRegister);
			add(jbtCancle);
			add(jbtPwUpdate);
			//�̺�Ʈó��
			PersonalInformEvt psie = new PersonalInformEvt(this,rmm); 
			jbtPwUpdate.addActionListener(psie);
			//�����Ұ�
			jtfId.setEditable(false);
			//���� ����
			jtfId.setBackground(Color.white);
			// WindowSizing
			setBounds(100, 100, 550, 700);
			// visible
			setVisible(true);
			// close
			addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosed(WindowEvent e) {
				dispose();
				}
				
			});
		}// SineUp

		public JTextField getJtfId() {
			return jtfId;
		}

		public JTextField getJtfPass() {
			return jtfPass;
		}

		public JTextField getJtfName() {
			return jtfName;
		}

		public JTextField getJtfPhone() {
			return jtfPhone;
		}

		public JTextField getJtfPwAnswer() {
			return jtfPwAnswer;
		}

		public JPasswordField getJpfPw() {
			return jpfPw;
		}

		public JButton getJbtRegister() {
			return jbtRegister;
		}

		public JButton getJbtCancle() {
			return jbtCancle;
		}

		public JButton getJbtPwUpdate() {
			return jbtPwUpdate;
		}

		public ButtonGroup getBgGender() {
			return bgGender;
		}

		public JRadioButton getJrbWomen() {
			return jrbWomen;
		}

		public JRadioButton getJrbMan() {
			return jrbMan;
		}

		public JComboBox<String> getJcbPhoneNum() {
			return jcbPhoneNum;
		}

		public JComboBox<String> getJcbLoc() {
			return jcbLoc;
		}

		public JComboBox<String> getJcbPwHint() {
			return jcbPwHint;
		}

		public String[] getPwHint() {
			return PwHint;
		}

		



}//class
