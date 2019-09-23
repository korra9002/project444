package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.UUID;

import javax.swing.JOptionPane;

import kr.co.sist.util.cipher.DataEncrypt;
import userDAO.UserDAO;
import userVO.ForgotPwVO;
import userView.ForgotPw;

public class ForgotPwEvt implements ActionListener {
	private ForgotPw fp;
	public ForgotPwEvt(ForgotPw fp) {
		this.fp=fp;
	}//ForgotPwEvt
	
	public void forgotPwClose() {
		fp.dispose();
	}//ForgotPwClose
	
	public void searchPw() throws SQLException {
		String id="";
		int pwHint=0;
		String pwAnswer = "";
		String depw ="";
		
		 String uuid = UUID.randomUUID().toString().replaceAll("-", ""); 
		    	uuid = uuid.substring(0, 10); 
		   try {
			depw = DataEncrypt.messageDigest("MD5", uuid);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}//end catch
		boolean flag = false;
		DecimalFormat df = new DecimalFormat("00");
		id= fp.getJtfId().getText().trim();
		pwHint = fp.getJcbPwHint().getSelectedIndex();
		pwAnswer = fp.getJtfPwAnswer().getText().trim();
		if(id.isEmpty()) {
			JOptionPane.showMessageDialog(fp, "���̵� �Է����ּ���.");
			
		}else if(pwHint==0) {
			JOptionPane.showMessageDialog(fp, "��й�ȣ ��Ʈ�� �������ּ���.");
		}else if(pwAnswer.isEmpty()) {
			JOptionPane.showMessageDialog(fp, "��й�ȣ ��Ʈ�� ���� �������ּ���.");
		}else {
			ForgotPwVO fpVO =new ForgotPwVO(id, df.format(pwHint), pwAnswer);
			UserDAO uDAO = UserDAO.getInstance();
			flag = uDAO.updateForgotPw(fpVO, depw);
			if(flag==false) {
				JOptionPane.showMessageDialog(fp, "�Է��Ͻ� ������ �ùٸ��� �ʽ��ϴ�.");
			}else {
				JOptionPane.showMessageDialog(fp, "������ �ӽ� ��й�ȣ��["+uuid+"]�Դϴ�.");
			}//end else
		}//end else
		
		
	}//searchPw
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==fp.getJbtSearch()||ae.getSource()==fp.getJtfPwAnswer()) {
			try {
				searchPw();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}//end if
		if(ae.getSource()==fp.getJbtCancle()) {
			forgotPwClose();
		}//end if
		
	}//actionPerformed

}//class
