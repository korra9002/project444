package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import adminDAO.AdminDAO;
import adminView.AdminMainView;
import kr.co.sist.util.cipher.DataEncrypt;
import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.LoginVO;
import userView.ForgotId;
import userView.ForgotPw;
import userView.Login;
import userView.MarketMain;
import userView.SignUp;

public class LoginEvt implements ActionListener {
	private Login lg;
	
	public LoginEvt(Login lg) {
		this.lg=lg;
		
	}//LoginEvt
	
	public void loginRun() {
		String id = lg.getJtfId().getText();
		String pw = "";
		String dePw = "";
		char[] cPw = lg.getJpfPw().getPassword(); 
		
		for(int i=0; i<cPw.length;i++) {
			pw = String.valueOf(cPw);
		}//end for
		
		//��й�ȣ ��ȣȭ
		try {
			dePw = DataEncrypt.messageDigest("MD5", pw);
		} catch (NoSuchAlgorithmException nae) {
			nae.printStackTrace();
		} // end catch
		
		if(!id.isEmpty()&&!pw.isEmpty()) {
			try {
				
				LoginVO lvo = new LoginVO(id, dePw);
				UserDAO uDAO = UserDAO.getInstance();
				
				String Login_name = uDAO.loginRun(lvo)[0];
				String suspend_flag = uDAO.loginRun(lvo)[1];
				
				//������
				if(id.equals("admin")&&pw.equals("1234")) {
					new AdminMainView();
					lg.dispose();
				}//end if
				
				if(suspend_flag.equals("N")) {
					if(!Login_name.isEmpty()&&!id.equals("admin")) {
						
						new RunMarketMain(id);
						lg.dispose();
					
					}//end if
					
				} else {
//					System.out.println(id);
//					System.out.println(Login_name);
					int cnt = 0;
					if (!Login_name.isEmpty() && (cnt=uDAO.suspendRelief(id))!=0) {
						boolean exitFlag = false;
						
						do {
							String msg = JOptionPane.showInputDialog(lg, "'�ų������� �ǰڽ��ϴ�'�� �Ȱ��� �����ּ���");
							switch(msg) {
							case "�ų������� �ǰڽ��ϴ�":
								JOptionPane.showMessageDialog(lg, "������ �����Ǿ����ϴ�.^^");
								new RunMarketMain(id);
								lg.dispose();	
								exitFlag = true;
								break;
							
							default : JOptionPane.showMessageDialog(lg, "����� �Է����ּ���.");
							
							System.out.println(exitFlag);
							}//end switch
							System.out.println(exitFlag);
						}while(!exitFlag);
						
					}else {
						JOptionPane.showMessageDialog(lg, "������ �����Դϴ�.");
						
					}//end else
					
				}//end if
				
			} catch (SQLException e) {
			
			} catch(NullPointerException npe) {
				lg.getJlLoginFail().setBounds(180, 140, 240, 25);
				lg.getJlLoginFail().setText("���̵� �Ǵ� ��й�ȣ�� Ȯ�����ּ���.");
				lg.getJtfId().setText("");
				lg.getJpfPw().setText("");
				lg.getJtfId().requestFocus();
			}//end catch
	
		}else if(id.isEmpty()){
			lg.getJlLoginFail().setBounds(270, 140, 200, 25);
			lg.getJlLoginFail().setText("���̵� �Է����ּ���.");
			lg.getJtfId().requestFocus();
		}else if(pw.isEmpty()){
			lg.getJlLoginFail().setBounds(270, 140, 200, 25);
			lg.getJlLoginFail().setText("��й�ȣ�� �Է����ּ���.");
			lg.getJpfPw().requestFocus();
		}//end if
		
	}//loginRun
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == lg.getJbtSignUp()) {
			new SignUp(lg);
		} // end if
		if (ae.getSource() == lg.getJbtForgotId()) {
			new ForgotId(lg);
		} // end if
		if (ae.getSource() == lg.getJbtForgotPw()) {
			new ForgotPw(lg);
		} // end if
		if(ae.getSource()==lg.getJbtLogin()||ae.getSource()==lg.getJtfId()||ae.getSource()==lg.getJpfPw()) {
			loginRun();
		}//end if
		
	}//actionPerformed

}//class
