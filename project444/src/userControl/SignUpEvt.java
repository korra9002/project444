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
				JOptionPane.showMessageDialog(su, "중복확인 완료");
				id = su.getJtfId().getText().trim();
			} else {
				JOptionPane.showMessageDialog(su, "아이디를 입력해주세요.");
			}
		} // end if
		if (ae.getSource() == su.getJcbPhoneNum()) {
			phone1 = (String) su.getJcbPhoneNum().getSelectedItem();
			System.out.println(phone1);
		} // end if

		if (ae.getSource() == su.getJbtRegister()) {

			// 비밀번호 저장
			char[] cPw = su.getJpfPw().getPassword();
			for (int i = 0; i < cPw.length; i++) {
				pw = String.valueOf(cPw);
			} // end for
			char[] cPw2 = su.getJpfRePass().getPassword();
			for (int i = 0; i < cPw2.length; i++) {
				repw = String.valueOf(cPw2);
			} // end for
				// 이름 저장
			name = su.getJtfName().getText().trim();
			// 연락처 저장
				if (phone1.isEmpty()) {
					phone1 = "010";
					DecimalFormat df = new DecimalFormat("0000");
					phone2 = Integer.valueOf(su.getJtfPhone().getText().trim());
					phone3 = Integer.valueOf(su.getJtfPhone2().getText().trim());
					phone = phone1 + "-" + df.format(phone2) + "-" + df.format(phone3);
					System.out.println(phone);
				} // end if

			// 비밀번호 힌트
			pwHint = su.getJcbPwHint().getSelectedIndex();
			pwAnswer =su.getJtfPwAnswer().getText().trim();
			// 지역 저장
			loc = su.getJcbLoc().getSelectedIndex();
			// 성별 저장
			gender = su.getCbgGender().getSelectedCheckbox().getLabel();
			if (id.isEmpty()) {
				JOptionPane.showMessageDialog(su, "아이디 중복체크를 해주세요.");
			} else if (gender.isEmpty()) {
				JOptionPane.showMessageDialog(su, "성별을 체크해주세요.");
			} else if (pw.isEmpty()) {
				JOptionPane.showMessageDialog(su, "비밀번호를 입력해주세요.");
			} else if (!pw.equals(repw)) {
				JOptionPane.showMessageDialog(su, "비밀번호 확인이 일치하지 않습니다.");
			} else if (su.getJtfName().getText().isEmpty()) {
				JOptionPane.showMessageDialog(su, "이름을 입력해주세요.");
			} else if (su.getJtfPhone().getText().isEmpty()) {
				JOptionPane.showMessageDialog(su, "연락처를 입력해주세요.");
			} else if (loc == 0) {
				JOptionPane.showMessageDialog(su, "지역을 선택해주세요.");
			} else if (pwHint == 0) {
				JOptionPane.showMessageDialog(su, "비밀번호 힌트를 선택해주세요.");
			} else {
				JOptionPane.showMessageDialog(su, "가입성공!");
			} // end if
			if (pw.equals(repw)) {
				System.out.println("아이디 : " + id + " 성별 : " + gender + " 비밀번호 : " + pw + "이름" + name + "연락처 : " + phone
						+ "지역 : " + loc + " 비밀번호 힌트 인덱스 : " + pwHint + "비밀번호 힌트 대답 :" + pwAnswer);
			}
		} // end if

		if (ae.getSource() == su.getJbtCancle()) {
			su.dispose();
		}//end if
	} catch (NumberFormatException nfe) {
		JOptionPane.showMessageDialog(su, "연락처는 숫자형식만 가능합니다.");
	} catch(NullPointerException npe) {
		JOptionPane.showMessageDialog(su, "형식을 모두 입력해주세요.");
		// end catch
	}

	}// actionPerformed
}// class
