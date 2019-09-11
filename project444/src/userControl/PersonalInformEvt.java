package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import userDAO.UserDAO;
import userRun.RunMarketMain;
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
		    System.out.println("비밀번호 : " + pw);
		  UserDAO uDAO = UserDAO.getInstance();
		  curpw= uDAO.selectPw(pw);
		    if(curpw.isEmpty()) {
		    	JOptionPane.showMessageDialog(psi, "비밀번호가 정확하지 않습니다.");
		    }else {
		    	JOptionPane.showMessageDialog(psi, "비밀번호 확인 완료");
		    	new PwUpdate(rmm);
		    }//end else
		    
		    
		}//end if
		
	}//PersonalInformEvt
	
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
		}
	}//actionPerformed
	
	
}//class
