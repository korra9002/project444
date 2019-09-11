package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

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
	
	public void modifyPw() {
		String pw = "";
		JPanel panel = new JPanel();
		JLabel label = new JLabel("현재 비밀번호 입력:");
		JPasswordField jpfPw = new JPasswordField(10);
		panel.add(label);
		panel.add(jpfPw);
		String[] options = new String[]{"확인", "취소"};
		int option = JOptionPane.showOptionDialog(psi, panel, "현재 비밀번호 입력", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if(option == 0) // pressing OK button
		{
		    char[] password = jpfPw.getPassword();
		    pw= new String(password);
		    System.out.println("비밀번호 : " + pw);
		    new PwUpdate(rmm);
		    
		    
		}//end if
		
	}//PersonalInformEvt
	
	public void PersonalInformClose() {
		psi.dispose();
	}//PersonalInform
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==psi.getJbtPwUpdate()) {
			modifyPw();
		}//end if
		if(ae.getSource()==psi.getJbtCancle()) {
			PersonalInformClose();
		}
	}//actionPerformed
	
	
}//class
