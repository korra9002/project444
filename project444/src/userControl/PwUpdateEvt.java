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
			JOptionPane.showMessageDialog(pu,"�Է��Ͻ� ���� ��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
		}else {
			if(!mfPw.equals(remfPw)) {
				JOptionPane.showMessageDialog(pu, "�����Ͻ� ��й�ȣ�� ��й�ȣ Ȯ���� �ùٸ��� �ʽ��ϴ�.");
			}else {
				boolean flag = uDAO.updatePw(pu.getId(), mfPw);
				if(flag==false){
					JOptionPane.showMessageDialog(pu,"�����Ͻ� ��й�ȣ�� �Է����ּ���.");
					
				}else {
					JOptionPane.showMessageDialog(pu,"��й�ȣ ������ �ùٸ��� �Ǿ����ϴ�.");
					
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
				JOptionPane.showMessageDialog(pu,"�����Ͻ� ��й�ȣ�� �Է����ּ���.");
			}//end catch
		}//end if
	}//actionPerformed

}//class
