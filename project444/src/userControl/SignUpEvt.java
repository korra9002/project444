package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import kr.co.sist.util.cipher.DataEncrypt;
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
		String gender = "";
		// 성별 저장
		try {
			if (su.getJrbWomen().isSelected()) {
				gender = "F";
			} else if (su.getJrbMan().isSelected()) {
				gender = "M";
			} // end else
				
		} catch (NullPointerException npe) {
		} // end catch
			// 회원정보 저장
		String name = su.getJtfName().getText().trim();
		String phone1 = (String) su.getJcbPhoneNum().getSelectedItem();
		String stPhone;
		String stPhone2;
		String loc = dfNum.format(su.getJcbLoc().getSelectedIndex());
		String pwHint = dfNum.format(su.getJcbPwHint().getSelectedIndex());
		String pwAnswer = su.getJtfPwAnswer().getText().trim();

		String pw = "";
		String repw = "";
		String depw ="";
		// 비밀번호 1
		char[] cPw = su.getJpfPw().getPassword();
		for (int i = 0; i < cPw.length; i++) {
			pw = String.valueOf(cPw);
		} // end for

		// 비밀번호 2
		char[] cPw2 = su.getJpfRePass().getPassword();
		for (int i = 0; i < cPw2.length; i++) {
			repw = String.valueOf(cPw2);
		} // end for

		//비밀번호 암호화 처리
		try {
			depw = DataEncrypt.messageDigest("MD5", pw);
		} catch (NoSuchAlgorithmException nae) {
			nae.printStackTrace();
		} // end catch
		
		// 아이디 체크
		if (id.isEmpty() || su.getJtfId().getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(su, "아이디 중복체크 또는 아이디를 입력해주세요.");
			return;
		} // end if
		if (id.isEmpty() || !su.getJtfId().getText().trim().equals(id)) {
			JOptionPane.showMessageDialog(su, "아이디 중복체크를 해주세요.");
			return;
		} // end if
			// 비밀번호 체크
		if (cPw.length == 0 || cPw2.length == 0) {
			JOptionPane.showMessageDialog(su, "비밀번호를 확인해주세요.");
			return;
		} else if(!(pw.length()>7&&pw.length()<17)) {
			JOptionPane.showMessageDialog(su, "비밀번호는 8 ~ 16 자입니다.");
			return;
		} else if (!pw.equals(repw)) {
			JOptionPane.showMessageDialog(su, "비밀번호가 일치하지 않습니다.");
			return;
		}// end if
		
		if (su.getJtfName().getText().isEmpty()) {
			JOptionPane.showMessageDialog(su, "이름을 입력해주세요.");
			return;
		} // end if

		// 성별 체크
		if (gender.isEmpty()) {
			JOptionPane.showMessageDialog(su, "성별을 선택해주세요.");
			return;
		} // end if
			// 연락처 체크
		if (su.getJtfPhone().getText().isEmpty() || su.getJtfPhone2().getText().isEmpty()) {
			JOptionPane.showMessageDialog(su, "연락처 형식을 입력해주세요.");
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
				JOptionPane.showMessageDialog(su, "연락처는 숫자형식만 가능합니다.");
				return;
			} // end catch

			if ((stPhone.length() < 3 || stPhone.length() > 4) || stPhone2.length() != 4) {
				JOptionPane.showMessageDialog(su, "연락처를 정확히 기입해주세요.");
				return;
			} // end if
		} // end if
			// 거주지 체크
		if (su.getJcbLoc().getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(su, "거주지역을 선택해주세요.");
			return;
		} // end if
			// 비밀번호 힌트 체크
		if (su.getJcbPwHint().getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(su, "입력하실 비밀번호 힌트를 선택해주세요.");
			return;
		} // end if
			// 비밀번호 답변 체크
		if (su.getJtfPwAnswer().getText().isEmpty()) {
			JOptionPane.showMessageDialog(su, "비밀번호 힌트의 답변을 작성해주세요.");
			return;
		} // end if
			// 연락처 저장
		String phone = phone1 + "-" + stPhone + "-" + stPhone2;
		int registerFlag = 0;
			
		SignUpVO suVO = new SignUpVO(id, depw, name, gender, phone, loc, pwHint, pwAnswer);
		UserDAO uDAO = UserDAO.getInstance();
		try {
			registerFlag = uDAO.insertLogin(suVO);
		} catch (SQLException e) {

		} // end catch
		if (registerFlag == 0) {
			JOptionPane.showMessageDialog(su, "이미 가입 되어있습니다.");
		} else {
			JOptionPane.showMessageDialog(su, "가입을 완료했습니다.");
			su.dispose();
		} // end else

	}// register

	public void idCheck() {
		String checkId = "";
		if (su.getJtfId().getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(su, "아이디를 입력해주세요.");
			su.getJtfId().requestFocus();
		} else {
			id = su.getJtfId().getText().trim();
			UserDAO uDAO = UserDAO.getInstance();
			try {
				checkId = uDAO.idCheck(id);

			} catch (SQLException e) {
				e.printStackTrace();
			} // end catch

			if (!checkId.isEmpty()) {
				JOptionPane.showMessageDialog(su, "이미 존재하는 아이디입니다.");
				su.getJtfId().setText("");
				su.getJtfId().requestFocus();
			} else {
				JOptionPane.showMessageDialog(su, "사용 가능한 아이디 입니다.");
			}

		} // end if
	}// idCheck

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
			if(JOptionPane.showConfirmDialog(su,"회원가입을 하지 않고 창을 닫으시겠습니까?","창닫기",JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE)==0) {
				signUpClose();
			}//end if
		} // end if
	}// actionPerformed
}// class
