package userControl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.UUID;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
			JOptionPane.showMessageDialog(fp, "아이디를 입력해주세요.");
			
		}else if(pwHint==0) {
			JOptionPane.showMessageDialog(fp, "비밀번호 힌트를 선택해주세요.");
		}else if(pwAnswer.isEmpty()) {
			JOptionPane.showMessageDialog(fp, "비밀번호 힌트의 답을 기입해주세요.");
		}else {
			ForgotPwVO fpVO =new ForgotPwVO(id, df.format(pwHint), pwAnswer);
			UserDAO uDAO = UserDAO.getInstance();
			flag = uDAO.updateForgotPw(fpVO, depw);
			if(flag==false) {
				JOptionPane.showMessageDialog(fp, "입력하신 정보가 올바르지 않습니다.");
			}else {
				
				JPanel panel = new JPanel(new GridLayout(2,2));
				JLabel label = new JLabel("고객님의 임시 비밀번호는");
				JTextField jtfPw = new JTextField(uuid);
				jtfPw.setEditable(false);
				panel.add(label);
				panel.add(new JLabel());
				panel.add(jtfPw);
				panel.add(new JLabel("입니다."));
				String[] options = new String[]{"확인"};
				int option = JOptionPane.showOptionDialog(fp, panel, "현재 비밀번호 입력", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				fp.dispose();
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
