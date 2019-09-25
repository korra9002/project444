package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.util.cipher.DataEncrypt;
import userDAO.UserDAO;
import userRun.RunMarketMain;
import userView.PwUpdate;

public class PwUpdateEvt implements ActionListener {
	PwUpdate pu;
	public PwUpdateEvt(PwUpdate pu) {
		this.pu=pu;
	}//PwUpdateEvt
	
	public void modifyPw() throws SQLException {
		char[] cCurPw = pu.getJpfCurrentPw().getPassword();
		char[] cMfPw = pu.getJpfUpdatePw().getPassword();
		char[] cRemfPw = pu.getJpfReUpdatePw().getPassword();
		UserDAO uDAO = UserDAO.getInstance();
		String curPw=String.valueOf(cCurPw);
		String mfPw=String.valueOf(cMfPw);
		String remfPw=String.valueOf(cRemfPw);
		String deCurPw ="";
		String deMfPw = "";
		boolean flag = false;
		//비밀번호 암호화 처리
		try {
			
			deCurPw = DataEncrypt.messageDigest("MD5", curPw);
			deMfPw = DataEncrypt.messageDigest("MD5", mfPw);
			
		} catch (NoSuchAlgorithmException nae) {
			nae.printStackTrace();
		} // end catch
		
		String curCheck= uDAO.selectPw(RunMarketMain.userId,deCurPw);
		
		if(curPw.isEmpty()) {
			JOptionPane.showMessageDialog(pu,"현재 비밀번호를 입력해주세요.");
			return;
		}//end if
		if(curCheck.isEmpty()) {
			JOptionPane.showMessageDialog(pu, "입력하신 현재 비밀번호가 올바르지 않습니다.");
			return;
		}//end if
		
		if(!mfPw.equals(remfPw)) {
			JOptionPane.showMessageDialog(pu, "변경하실 비밀번호와 비밀번호 확인이 올바르지 않습니다.");
			return;
		}//end if
		if(mfPw.equals(curPw)) {
			JOptionPane.showMessageDialog(pu, "현재 비밀번호와 입력하신 변경할 비밀번호가 일치합니다.");
			return;
		}//end if
					if(!curPw.isEmpty()&&!mfPw.isEmpty()) {
						flag = uDAO.updatePw(pu.getId(), deMfPw);
					}//end if
							if(flag==false){
								JOptionPane.showMessageDialog(pu,"변경하실 비밀번호를 입력해주세요.");
							}else {
								JOptionPane.showMessageDialog(pu,"비밀번호 변경이 올바르게 되었습니다.");
									PwUpdateClose();
							}//end else
		
	}//modifyPw
	
	public void PwUpdateClose() {
		pu.dispose();
	}//PwUpdateClose
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==pu.getJbtCancle()) {
			PwUpdateClose();
		}//end if
		if(ae.getSource()==pu.getJbtOk()||ae.getSource()==pu.getJpfReUpdatePw()) {
			try {
				modifyPw();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(pu, "입력하신 비밀번호를 확인해주세요.");
			}//end catch
		}//end if
	}//actionPerformed

}//class
