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
	private PwUpdate pu;
	public static boolean uFlag = false;
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
		//��й�ȣ ��ȣȭ ó��
		try {
			
			deCurPw = DataEncrypt.messageDigest("MD5", curPw);
			deMfPw = DataEncrypt.messageDigest("MD5", mfPw);
			
		} catch (NoSuchAlgorithmException nae) {
			nae.printStackTrace();
		} // end catch
		
		String curCheck= uDAO.selectPw(RunMarketMain.userId,deCurPw);
		
		if(curPw.isEmpty()) {
			JOptionPane.showMessageDialog(pu,"���� ��й�ȣ�� �Է����ּ���.");
			return;
		}//end if
		if(curCheck.isEmpty()) {
			JOptionPane.showMessageDialog(pu, "�Է��Ͻ� ���� ��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
			return;
		}//end if
		
		if(!mfPw.equals(remfPw)) {
			JOptionPane.showMessageDialog(pu, "�����Ͻ� ��й�ȣ�� ��й�ȣ Ȯ���� �ùٸ��� �ʽ��ϴ�.");
			return;
		}//end if
		if(mfPw.equals(curPw)) {
			JOptionPane.showMessageDialog(pu, "���� ��й�ȣ�� �Է��Ͻ� ������ ��й�ȣ�� ��ġ�մϴ�.");
			return;
		}//end if
		if(!curPw.isEmpty()&&!mfPw.isEmpty()) {
			flag = uDAO.updatePw(pu.getId(), deMfPw);
			}//end if
		if(flag==false){
			JOptionPane.showMessageDialog(pu,"�����Ͻ� ��й�ȣ�� �Է����ּ���.");
		}else {
			JOptionPane.showMessageDialog(pu,"��й�ȣ ������ �ùٸ��� �Ǿ����ϴ�.");
				uFlag =true;
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
				JOptionPane.showMessageDialog(pu, "�Է��Ͻ� ��й�ȣ�� Ȯ�����ּ���.");
			}//end catch
		}//end if
	}//actionPerformed

}//class
