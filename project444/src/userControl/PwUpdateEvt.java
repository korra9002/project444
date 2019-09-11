package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import userDAO.UserDAO;
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
		if(uDAO.selectPw(curPw).isEmpty()) {
			JOptionPane.showMessageDialog(pu,"입력하신 현재 비밀번호가 올바르지 않습니다.");
		}else {
			if(!mfPw.equals(remfPw)) {
				JOptionPane.showMessageDialog(pu, "변경하실 비밀번호와 비밀번호 확인이 올바르지 않습니다.");
			}else {
				boolean flag = uDAO.updatePw(pu.getId(), mfPw);
				if(flag==false){
					JOptionPane.showMessageDialog(pu,"변경하실 비밀번호를 입력해주세요.");
					
				}else {
					JOptionPane.showMessageDialog(pu,"비밀번호 변경이 올바르게 되었습니다.");
					PwUpdateClose();
				}
			}//end else
			
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
		if(ae.getSource()==pu.getJbtOk()) {
			try {
				modifyPw();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(pu,"변경하실 비밀번호를 입력해주세요.");
			}//end catch
		}//end if
	}//actionPerformed

}//class
