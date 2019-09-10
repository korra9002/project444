package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import userView.SignUp;

public class SignUpEvt extends MouseAdapter implements ActionListener {
	private SignUp su;
	private String id = "";
	private String gender = "";
	private String pw = "";
	private String repw = "";
	private String name = "";
	private String phone = "";
	private String phone1 = "";
//	private int phone2;
//	private int phone3;
	private String stPhone;
	private String stPhone2;
	private int loc = 0;
	private int pwHint = 0;
	private String pwAnswer = "";

	public SignUpEvt(SignUp su) {
		this.su = su;

	}// SignUpEvt

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == su.getJbtIdCheck()) {
			if (su.getJtfId().getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(su, "���̵� �Է����ּ���.");
			} else {
				id = su.getJtfId().getText().trim();
				JOptionPane.showMessageDialog(su, "�ߺ�üũ�Ϸ�");
			}
		} // end if
		if (ae.getSource() == su.getJbtRegister()) {
			// phone �޺��ڽ�
			phone1 = (String) su.getJcbPhoneNum().getSelectedItem();
			if (phone1.isEmpty()) {
				phone1 = "010";
			} // end if
				// ��й�ȣ 1
			char[] cPw = su.getJpfPw().getPassword();
			for (int i = 0; i < cPw.length; i++) {
				pw = String.valueOf(cPw);
			} // end for

			// ��й�ȣ 2
			char[] cPw2 = su.getJpfRePass().getPassword();
			for (int i = 0; i < cPw2.length; i++) {
				repw = String.valueOf(cPw2);
			} // end for
			try {
				if (su.getCbgGender().getSelectedCheckbox().getLabel().toString().equals("����")) {

					gender = "F";
				} else {
					gender = "M";
				}

			} catch (NullPointerException npe) {

			}
			if (id.isEmpty() || su.getJtfId().getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(su, "���̵� �ߺ�üũ �Ǵ� ���̵� �Է����ּ���.");
			} else if (id.isEmpty() || !su.getJtfId().getText().trim().equals(id)) {
				JOptionPane.showMessageDialog(su, "���̵� �ߺ�üũ�� ���ּ���.");
			} else {
				if (cPw.length == 0 || cPw2.length == 0) {
					JOptionPane.showMessageDialog(su, "��й�ȣ�� Ȯ�����ּ���.");
				} else {
					if (!pw.equals(repw)) {
						JOptionPane.showMessageDialog(su, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					} else {
						if (su.getJtfName().getText().isEmpty()) {
							JOptionPane.showMessageDialog(su, "�̸��� �Է����ּ���.");
						} else {
							name = su.getJtfName().getText().trim();
							if (gender.isEmpty()) {
								JOptionPane.showMessageDialog(su, "������ �������ּ���.");

							} else {
								
								DecimalFormat df = new DecimalFormat("0000");
								if (su.getJtfPhone().getText().isEmpty() || su.getJtfPhone2().getText().isEmpty()) {
									JOptionPane.showMessageDialog(su, "����ó ������ �Է����ּ���.");
								} else {
									int phone2 =0;
									int phone3 =0;
									try {
									stPhone=su.getJtfPhone().getText().trim();
									stPhone2=su.getJtfPhone2().getText().trim();
								
									phone2 = Integer.valueOf(stPhone);
									phone3 = Integer.valueOf(stPhone2);

									} catch (NumberFormatException nfe) {
										JOptionPane.showMessageDialog(su, "����ó�� �������ĸ� �����մϴ�.");
										return;
									} // end catch
									 
									if((stPhone.length()<3||stPhone.length()>4)||stPhone2.length()!=4) {
										JOptionPane.showMessageDialog(su, "����ó�� ��Ȯ�� �������ּ���.");
										return;
									}else {
										phone = phone1+"-"+stPhone+"-"+stPhone2;
										
									
									if (su.getJcbLoc().getSelectedIndex() == 0) {
										JOptionPane.showMessageDialog(su, "���������� �������ּ���.");
									} else {
										loc = su.getJcbLoc().getSelectedIndex();

										if (su.getJcbPwHint().getSelectedIndex() == 0) {
											JOptionPane.showMessageDialog(su, "�Է��Ͻ� ��й�ȣ ��Ʈ�� �������ּ���.");
										} else {
											pwHint = su.getJcbPwHint().getSelectedIndex();
											if (su.getJtfPwAnswer().getText().isEmpty()) {
												JOptionPane.showMessageDialog(su, "��й�ȣ ��Ʈ�� �亯�� �ۼ����ּ���.");
											} else {
												pwAnswer = su.getJtfPwAnswer().getText().trim();
												JOptionPane.showMessageDialog(su, "������ �Ϸ��߽��ϴ�.");
												System.out.println("���̵� : " + id + " �н����� : " + pw + " �̸� : " + name
														+ " ����  : " + gender + " ����ó : " + phone + " ���� : " + loc
														+ " ��й�ȣ ��Ʈ  : " + pwHint + "��й�ȣ �亯 : " + pwAnswer);
											} // end else
										} // end else
									} // end else
								} // end else
							} // end else
						} // end else
					} // end else
				} // end else
			} // end else
		}//end else
			
		} // end if
		
		if (ae.getSource() == su.getJbtCancle()) {
			su.dispose();
		} // end if
	}// actionPerformed
}// class
