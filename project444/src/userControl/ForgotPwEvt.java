package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.UUID;

import javax.swing.JOptionPane;

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
		
		 String uuid = UUID.randomUUID().toString().replaceAll("-", ""); 
		    	uuid = uuid.substring(0, 10); 
		boolean flag = false;
		DecimalFormat df = new DecimalFormat("00");
		id= fp.getJtfId().getText().trim();
		pwHint = fp.getJcbPwHint().getSelectedIndex();
		pwAnswer = fp.getJtfPwAnswer().getText().trim();
		if(id.isEmpty()) {
			JOptionPane.showMessageDialog(fp, "아이디를 입력해주세요.");
			
		}else if(pwHint==0) {
			JOptionPane.showMessageDialog(fp, "비밀번호 힌트를 선택해주세요.");
		}else if(pwAnswer.isEmpty()) {
			JOptionPane.showMessageDialog(fp, "비밀번호 힌트의 답을 기입해주세요.");
		}else {
			ForgotPwVO fpVO =new ForgotPwVO(id, df.format(pwHint), pwAnswer);
			UserDAO uDAO = UserDAO.getInstance();
			flag = uDAO.updateForgotPw(fpVO, uuid);
			if(flag==false) {
				JOptionPane.showMessageDialog(fp, "입력하신 정보가 올바르지 않습니다.");
			}else {
				JOptionPane.showMessageDialog(fp, "고객님의 임시 비밀번호는["+uuid+"]입니다.");
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
