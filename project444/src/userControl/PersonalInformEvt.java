package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.sun.pisces.PiscesRenderer;

import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.PersonalInformVO;
import userVO.modifyInformVO;
import userView.PersonalInform;
import userView.PwUpdate;

public class PersonalInformEvt implements ActionListener{
	private PersonalInform psi;
	private RunMarketMain rmm;
	
	public PersonalInformEvt(PersonalInform psi, RunMarketMain rmm) {
		this.psi=psi;
		this.rmm = rmm;
	}//PersonalInformEvt
	
	public void modifyPw() throws SQLException {
		String pw = "";
		String curpw = "";
		String id = psi.getJtfId().getText().trim();
		JPanel panel = new JPanel();
		JLabel label = new JLabel("현재 비밀번호 입력:");
		JPasswordField jpfPw = new JPasswordField(10);
		panel.add(label);
		panel.add(jpfPw);
		String[] options = new String[]{"확인", "취소"};
		int option = JOptionPane.showOptionDialog(psi, panel, "현재 비밀번호 입력", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if(option == 0){
		    char[] password = jpfPw.getPassword();
		    pw= new String(password);
		  UserDAO uDAO = UserDAO.getInstance();
		  curpw= uDAO.selectPw(pw);
		    if(curpw.isEmpty()) {
		    	JOptionPane.showMessageDialog(psi, "비밀번호가 정확하지 않습니다.");
		    }else {
		    	JOptionPane.showMessageDialog(psi, "비밀번호 확인 완료");
		    	new PwUpdate(rmm,id);
		    }//end else
		    
		    
		}//end if
		
	}//PersonalInformEvt
	public void modifyRegister() throws SQLException {
		boolean flag = false;
		DecimalFormat df = new DecimalFormat("00");
		String id = psi.getJtfId().getText().trim();
		//텍스트필드의 현재(변경할) 값 가져오기
		String mfPhone1 = (String) psi.getJcbPhoneNum().getSelectedItem();
		String mfPhone2 = psi.getJtfPhone1().getText().trim();
		String mfPhone3 = psi.getJtfPhone2().getText().trim();
		String mfPhone =mfPhone1+"-"+mfPhone2+"-"+mfPhone3;
		String mfLoc = df.format(psi.getJcbLoc().getSelectedIndex());
		String mfPwHint = df.format(psi.getJcbPwHint().getSelectedIndex());
		String mfPwAnswer = (String)psi.getJtfPwAnswer().getText().trim();
		System.out.println(mfPhone + " " + mfLoc+" "+mfPwHint+" "+mfPwAnswer);
		//회원 정보 가져오기
		UserDAO uDAO = UserDAO.getInstance();
		PersonalInformVO piVO = uDAO.selectPersonalInfom(id);
		System.out.println(piVO.getPhone()+" "+piVO.getLoc()+" "+piVO.getPwHint()+" "+piVO.getPwAnswer());
		
		if(mfPhone2.isEmpty()||mfPhone3.isEmpty()) {
			JOptionPane.showMessageDialog(psi, "변경할 연락처 정보를 입력해주세요.");
			return;
		}else {
			int phone2 = 0;
			int phone3 = 0;
			try {
				phone2 = Integer.valueOf(mfPhone2);
				phone3 = Integer.valueOf(mfPhone3);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(psi, "연락처는 숫자형식만 가능합니다.");
				return;
			} // end catch
			if ((mfPhone2.length() < 3 || mfPhone2.length() > 4) || mfPhone3.length() != 4) {
				JOptionPane.showMessageDialog(psi, "연락처를 정확히 기입해주세요.");
				return;
			} else {
				mfPhone = mfPhone1 + "-" + mfPhone2 + "-" + mfPhone3;
				
				if(!mfPhone.equals(piVO.getPhone())) {
					flag = false;
					modifyInformVO miVO = new modifyInformVO(id,"phone",mfPhone);
					 if(flag==uDAO.updateThing(miVO)) {
						 JOptionPane.showMessageDialog(psi, "값 변경 실패");
							return;
					}else {
						 flag =true;
					}//end else
			 }//end if
			
		}//end else
		
		 
		}//end if
		if(!mfLoc.equals("00")&&!mfLoc.equals(piVO.getLoc())){
			flag = false;
			modifyInformVO miVO = new modifyInformVO(id, "loc_code", mfLoc);
			if(flag==uDAO.updateThing(miVO)) {
				JOptionPane.showMessageDialog(psi, "값 변경 실패");
				return;
			}else {
				flag =true;
			}//end else
		}//end if
			
		if(!mfPwHint.equals("00")&&!mfPwHint.equals(piVO.getPwHint())) {
			flag = false;
			modifyInformVO miVO = new modifyInformVO(id, "hint_code", mfPwHint);
			if(flag==uDAO.updateThing(miVO)) {
				JOptionPane.showMessageDialog(psi, "값 변경 실패");
				return;
			}else {
				flag =true;
			}//end else
		
		}//end if
		if(!mfPwAnswer.isEmpty()&&!mfPwAnswer.equals(piVO.getPwAnswer())) {
			flag = false;
			modifyInformVO miVO = new modifyInformVO(id, "answer", mfPwAnswer);
			if(flag==uDAO.updateThing(miVO)) {
				JOptionPane.showMessageDialog(psi, "값 변경 실패");
				return;
			}else {
				flag =true;
			}//end else
		}//end if
		if(flag ==true) {
			JOptionPane.showMessageDialog(psi, "정보가 변경되었습니다.");
		}else {
			JOptionPane.showMessageDialog(psi, "변경할 정보를 입력해주세요.");
		}
	}//modifyRegister
	public void PersonalInformClose() {
		psi.dispose();
	}//PersonalInform
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==psi.getJbtPwUpdate()) {
			try {
				modifyPw();
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
		}//end if
		if(ae.getSource()==psi.getJbtCancle()) {
			PersonalInformClose();
		}//end if
		if(ae.getSource()==psi.getJbtRegister()) {
			try {
				modifyRegister();
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
		}//end if
	}//actionPerformed
	
	
}//class
