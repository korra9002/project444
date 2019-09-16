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
		
		String gender = "";
		String pw = "";
		String repw = "";
		String name = "";
		String phone = "";
		String phone1 = "";
		String stPhone;
		String stPhone2;
		String loc = "";
		String pwHint = "";
		String pwAnswer = "";
		DecimalFormat dfNum = new DecimalFormat("00");
		// phone 콤보박스
		phone1 = (String) su.getJcbPhoneNum().getSelectedItem();
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
			if (su.getJrbWomen().isSelected()) {
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
								} else {
									phone = phone1 + "-" + stPhone + "-" + stPhone2;

									if (su.getJcbLoc().getSelectedIndex() == 0) {
										
										JOptionPane.showMessageDialog(su, "거주지역을 선택해주세요.");
									} else {
										
										loc = dfNum.format(su.getJcbLoc().getSelectedIndex());

										if (su.getJcbPwHint().getSelectedIndex() == 0) {
											JOptionPane.showMessageDialog(su, "입력하실 비밀번호 힌트를 선택해주세요.");
										} else {
											pwHint = dfNum.format(su.getJcbPwHint().getSelectedIndex());
											if (su.getJtfPwAnswer().getText().isEmpty()) {
												JOptionPane.showMessageDialog(su, "비밀번호 힌트의 답변을 작성해주세요.");
											} else {
												int registerFlag =0;
												pwAnswer = su.getJtfPwAnswer().getText().trim();
												
												
												SignUpVO suVO = new SignUpVO(id, pw, name, gender, phone, loc, pwHint, pwAnswer);
												UserDAO uDAO = UserDAO.getInstance();
												try {
													registerFlag=uDAO.insertLogin(suVO);
												} catch (SQLException e) {
													
												}//end catch
												if(registerFlag==0) {
													JOptionPane.showMessageDialog(su, "이미 가입되어 있습니다.");
												}else {
													JOptionPane.showMessageDialog(su, "가입을 완료했습니다.");
													su.dispose();
												}//end else
												
											} // end else
										} // end else
									} // end else
								} // end else
							} // end else
						} // end else
					} // end else
				} // end else
			} // end else
		} // end else

	}// register

	public void idCheck() {
		String checkId ="";
		if (su.getJtfId().getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(su, "아이디를 입력해주세요.");
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
				JOptionPane.showMessageDialog(su, "이미 존재하는 아이디입니다.");
				su.getJtfId().setText("");
				su.getJtfId().requestFocus();
			}else {
				JOptionPane.showMessageDialog(su, "사용 가능한 아이디 입니다.");
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
