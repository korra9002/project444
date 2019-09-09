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
	private int phone2 = 0;
	private int phone3 = 0;
	private int loc = 0;
	private int pwHint = 0;
	private String pwAnswer = "";
	public SignUpEvt(SignUp su) {
		this.su = su;

	}// SignUpEvt


	@Override
	public void actionPerformed(ActionEvent ae) {
	try {
		if (ae.getSource() == su.getJbtIdCheck()) {
			if (!su.getJtfId().getText().isEmpty()) {
				JOptionPane.showMessageDialog(su, "�ߺ�Ȯ�� �Ϸ�");
				id = su.getJtfId().getText().trim();
			} else {
				JOptionPane.showMessageDialog(su, "���̵� �Է����ּ���.");
			}
		} // end if
		if (ae.getSource() == su.getJcbPhoneNum()) {
			phone1 = (String) su.getJcbPhoneNum().getSelectedItem();
			System.out.println(phone1);
		} // end if

		if (ae.getSource() == su.getJbtRegister()) {

			// ��й�ȣ ����
			char[] cPw = su.getJpfPw().getPassword();
			for (int i = 0; i < cPw.length; i++) {
				pw = String.valueOf(cPw);
			} // end for
			char[] cPw2 = su.getJpfRePass().getPassword();
			for (int i = 0; i < cPw2.length; i++) {
				repw = String.valueOf(cPw2);
			} // end for
				// �̸� ����
			name = su.getJtfName().getText().trim();
			// ����ó ����
				if (phone1.isEmpty()) {
					phone1 = "010";
					DecimalFormat df = new DecimalFormat("0000");
					phone2 = Integer.valueOf(su.getJtfPhone().getText().trim());
					phone3 = Integer.valueOf(su.getJtfPhone2().getText().trim());
					phone = phone1 + "-" + df.format(phone2) + "-" + df.format(phone3);
					System.out.println(phone);
				} // end if

			// ��й�ȣ ��Ʈ
			pwHint = su.getJcbPwHint().getSelectedIndex();
			pwAnswer =su.getJtfPwAnswer().getText().trim();
			// ���� ����
			loc = su.getJcbLoc().getSelectedIndex();
			// ���� ����
			gender = su.getCbgGender().getSelectedCheckbox().getLabel();
			if (id.isEmpty()) {
				JOptionPane.showMessageDialog(su, "���̵� �ߺ�üũ�� ���ּ���.");
			} else if (gender.isEmpty()) {
				JOptionPane.showMessageDialog(su, "������ üũ���ּ���.");
			} else if (pw.isEmpty()) {
				JOptionPane.showMessageDialog(su, "��й�ȣ�� �Է����ּ���.");
			} else if (!pw.equals(repw)) {
				JOptionPane.showMessageDialog(su, "��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�.");
			} else if (su.getJtfName().getText().isEmpty()) {
				JOptionPane.showMessageDialog(su, "�̸��� �Է����ּ���.");
			} else if (su.getJtfPhone().getText().isEmpty()) {
				JOptionPane.showMessageDialog(su, "����ó�� �Է����ּ���.");
			} else if (loc == 0) {
				JOptionPane.showMessageDialog(su, "������ �������ּ���.");
			} else if (pwHint == 0) {
				JOptionPane.showMessageDialog(su, "��й�ȣ ��Ʈ�� �������ּ���.");
			} else {
				JOptionPane.showMessageDialog(su, "���Լ���!");
			} // end if
			if (pw.equals(repw)) {
				System.out.println("���̵� : " + id + " ���� : " + gender + " ��й�ȣ : " + pw + "�̸�" + name + "����ó : " + phone
						+ "���� : " + loc + " ��й�ȣ ��Ʈ �ε��� : " + pwHint + "��й�ȣ ��Ʈ ��� :" + pwAnswer);
			}
		} // end if

		if (ae.getSource() == su.getJbtCancle()) {
			su.dispose();
		}//end if
	} catch (NumberFormatException nfe) {
		JOptionPane.showMessageDialog(su, "����ó�� �������ĸ� �����մϴ�.");
	} catch(NullPointerException npe) {
		JOptionPane.showMessageDialog(su, "������ ��� �Է����ּ���.");
		// end catch
	}

	}// actionPerformed
}// class
