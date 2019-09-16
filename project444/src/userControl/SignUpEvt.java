package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.ButtonModel;
import javax.swing.JOptionPane;

import userDAO.UserDAO;
import userVO.SignUpVO;
import userView.SignUp;

public class SignUpEvt extends MouseAdapter implements ActionListener {
	private SignUp su;
	private String id = "";

	public SignUpEvt(SignUp su) {
		this.su = su;

	}// SignUpEvt

	public void register() {

		DecimalFormat dfNum = new DecimalFormat("00");
		DecimalFormat df = new DecimalFormat("0000");
		String gender = "";
		// ���� ����
		try {
			if (su.getJrbWomen().isSelected()) {
				gender = "F";
			} else if (su.getJrbMan().isSelected()) {
				gender = "M";
			} // end else

		} catch (NullPointerException npe) {
		} // end catch
			// ȸ������ ����
		String name = su.getJtfName().getText().trim();
		String phone1 = (String) su.getJcbPhoneNum().getSelectedItem();
		String stPhone;
		String stPhone2;
		String loc = dfNum.format(su.getJcbLoc().getSelectedIndex());
		String pwHint = dfNum.format(su.getJcbPwHint().getSelectedIndex());
		String pwAnswer = su.getJtfPwAnswer().getText().trim();

		String pw = "";
		String repw = "";
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

		// ���̵� üũ
		if (id.isEmpty() || su.getJtfId().getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(su, "���̵� �ߺ�üũ �Ǵ� ���̵� �Է����ּ���.");
			return;
		} // end if
		if (id.isEmpty() || !su.getJtfId().getText().trim().equals(id)) {
			JOptionPane.showMessageDialog(su, "���̵� �ߺ�üũ�� ���ּ���.");
			return;
		} // end if
			// ��й�ȣ üũ
		if (cPw.length == 0 || cPw2.length == 0) {
			JOptionPane.showMessageDialog(su, "��й�ȣ�� Ȯ�����ּ���.");
			return;
		} else if (!pw.equals(repw)) {
			JOptionPane.showMessageDialog(su, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			return;
		} // end if
		if (su.getJtfName().getText().isEmpty()) {
			JOptionPane.showMessageDialog(su, "�̸��� �Է����ּ���.");
			return;
		} // end if

		// ���� üũ
		if (gender.isEmpty()) {
			JOptionPane.showMessageDialog(su, "������ �������ּ���.");
			return;
		} // end if
			// ����ó üũ
		if (su.getJtfPhone().getText().isEmpty() || su.getJtfPhone2().getText().isEmpty()) {
			JOptionPane.showMessageDialog(su, "����ó ������ �Է����ּ���.");
			return;
		} else {
			int phone2 = 0;
			int phone3 = 0;
			try {
				stPhone = su.getJtfPhone().getText().trim();
				stPhone2 = su.getJtfPhone2().getText().trim();

				phone2 = Integer.valueOf(stPhone);
				phone3 = Integer.valueOf(stPhone2);

			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(su, "����ó�� �������ĸ� �����մϴ�.");
				return;
			} // end catch

			if ((stPhone.length() < 3 || stPhone.length() > 4) || stPhone2.length() != 4) {
				JOptionPane.showMessageDialog(su, "����ó�� ��Ȯ�� �������ּ���.");
				return;
			} // end if
		} // end if
			// ������ üũ
		if (su.getJcbLoc().getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(su, "���������� �������ּ���.");
			return;
		} // end if
			// ��й�ȣ ��Ʈ üũ
		if (su.getJcbPwHint().getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(su, "�Է��Ͻ� ��й�ȣ ��Ʈ�� �������ּ���.");
			return;
		} // end if
			// ��й�ȣ �亯 üũ
		if (su.getJtfPwAnswer().getText().isEmpty()) {
			JOptionPane.showMessageDialog(su, "��й�ȣ ��Ʈ�� �亯�� �ۼ����ּ���.");
			return;
		} // end if
			// ����ó ����
		String phone = phone1 + "-" + stPhone + "-" + stPhone2;
		int registerFlag = 0;
		SignUpVO suVO = new SignUpVO(id, pw, name, gender, phone, loc, pwHint, pwAnswer);
		UserDAO uDAO = UserDAO.getInstance();
		try {
			registerFlag = uDAO.insertLogin(suVO);
		} catch (SQLException e) {

		} // end catch
		if (registerFlag == 0) {
			JOptionPane.showMessageDialog(su, "�̹� ���� �Ǿ��ֽ��ϴ�.");
		} else {
			JOptionPane.showMessageDialog(su, "������ �Ϸ��߽��ϴ�.");
			su.dispose();
		} // end else

	}// register

	public void idCheck() {
		String checkId ="";
		if (su.getJtfId().getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(su, "���̵� �Է����ּ���.");
			su.getJtfId().requestFocus();
		} else {
			id = su.getJtfId().getText().trim();
			UserDAO uDAO = UserDAO.getInstance();
			try {
				checkId=uDAO.idCheck(id);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch

			if(!checkId.isEmpty()) {
				JOptionPane.showMessageDialog(su, "�̹� �����ϴ� ���̵��Դϴ�.");
				su.getJtfId().setText("");
				su.getJtfId().requestFocus();
			}else {
				JOptionPane.showMessageDialog(su, "��� ������ ���̵� �Դϴ�.");
			}
			
		}//end if
	}//idCheck
	
	public void signUpClose() {
		su.dispose();
	}// signUpClose

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == su.getJbtIdCheck()) {
			idCheck();
		} // end if
		if (ae.getSource() == su.getJbtRegister()) {
			register();
		} // end if

		if (ae.getSource() == su.getJbtCancle()) {
			signUpClose();
		} // end if
	}// actionPerformed
}// class
