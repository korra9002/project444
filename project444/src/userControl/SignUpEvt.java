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
				JOptionPane.showMessageDialog(su, "아이디를 입력해주세요.");
			} else {
				id = su.getJtfId().getText().trim();
				JOptionPane.showMessageDialog(su, "중복체크완료");
			}
		} // end if
		if (ae.getSource() == su.getJbtRegister()) {
			// phone 콤보박스
			phone1 = (String) su.getJcbPhoneNum().getSelectedItem();
			if (phone1.isEmpty()) {
				phone1 = "010";
			} // end if
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
			try {
				if (su.getCbgGender().getSelectedCheckbox().getLabel().toString().equals("여자")) {

					gender = "F";
				} else {
					gender = "M";
				}

			} catch (NullPointerException npe) {

			}
			if (id.isEmpty() || su.getJtfId().getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(su, "아이디 중복체크 또는 아이디를 입력해주세요.");
			} else if (id.isEmpty() || !su.getJtfId().getText().trim().equals(id)) {
				JOptionPane.showMessageDialog(su, "아이디 중복체크를 해주세요.");
			} else {
				if (cPw.length == 0 || cPw2.length == 0) {
					JOptionPane.showMessageDialog(su, "비밀번호를 확인해주세요.");
				} else {
					if (!pw.equals(repw)) {
						JOptionPane.showMessageDialog(su, "비밀번호가 일치하지 않습니다.");
					} else {
						if (su.getJtfName().getText().isEmpty()) {
							JOptionPane.showMessageDialog(su, "이름을 입력해주세요.");
						} else {
							name = su.getJtfName().getText().trim();
							if (gender.isEmpty()) {
								JOptionPane.showMessageDialog(su, "성별을 선택해주세요.");

							} else {
								
								DecimalFormat df = new DecimalFormat("0000");
								if (su.getJtfPhone().getText().isEmpty() || su.getJtfPhone2().getText().isEmpty()) {
									JOptionPane.showMessageDialog(su, "연락처 형식을 입력해주세요.");
								} else {
									int phone2 =0;
									int phone3 =0;
									try {
									stPhone=su.getJtfPhone().getText().trim();
									stPhone2=su.getJtfPhone2().getText().trim();
								
									phone2 = Integer.valueOf(stPhone);
									phone3 = Integer.valueOf(stPhone2);

									} catch (NumberFormatException nfe) {
										JOptionPane.showMessageDialog(su, "연락처는 숫자형식만 가능합니다.");
										return;
									} // end catch
									 
									if((stPhone.length()<3||stPhone.length()>4)||stPhone2.length()!=4) {
										JOptionPane.showMessageDialog(su, "연락처를 정확히 기입해주세요.");
										return;
									}else {
										phone = phone1+"-"+stPhone+"-"+stPhone2;
										
									
									if (su.getJcbLoc().getSelectedIndex() == 0) {
										JOptionPane.showMessageDialog(su, "거주지역을 선택해주세요.");
									} else {
										loc = su.getJcbLoc().getSelectedIndex();

										if (su.getJcbPwHint().getSelectedIndex() == 0) {
											JOptionPane.showMessageDialog(su, "입력하실 비밀번호 힌트를 선택해주세요.");
										} else {
											pwHint = su.getJcbPwHint().getSelectedIndex();
											if (su.getJtfPwAnswer().getText().isEmpty()) {
												JOptionPane.showMessageDialog(su, "비밀번호 힌트의 답변을 작성해주세요.");
											} else {
												pwAnswer = su.getJtfPwAnswer().getText().trim();
												JOptionPane.showMessageDialog(su, "가입을 완료했습니다.");
												System.out.println("아이디 : " + id + " 패스워드 : " + pw + " 이름 : " + name
														+ " 성별  : " + gender + " 연락처 : " + phone + " 지역 : " + loc
														+ " 비밀번호 힌트  : " + pwHint + "비밀번호 답변 : " + pwAnswer);
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
