package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.sun.pisces.PiscesRenderer;

import kr.co.sist.util.cipher.DataEncrypt;
import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.PersonalInformVO;
import userVO.modifyInformVO;
import userView.PersonalInform;
import userView.PwUpdate;

public class PersonalInformEvt implements ActionListener{
	private PersonalInform psi;
	private RunMarketMain rmm;
	private String curpw="";
	private boolean flag = false;
	public PersonalInformEvt(PersonalInform psi, RunMarketMain rmm) {
		this.psi=psi;
		this.rmm = rmm;
	}//PersonalInformEvt
	
	public void modifyPw() throws SQLException {
		String pw = "";
	
		String dePw = "";
		String id = psi.getJtfId().getText().trim();
		JPanel panel = new JPanel();
		JLabel label = new JLabel("���� ��й�ȣ �Է�:");
		JPasswordField jpfPw = new JPasswordField(10);
		panel.add(label);
		panel.add(jpfPw);
		String[] options = new String[]{"Ȯ��", "���"};
		int option = JOptionPane.showOptionDialog(psi, panel, "���� ��й�ȣ �Է�", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if(option == 0){
		    char[] password = jpfPw.getPassword();
		    pw= new String(password);
		   
		  //��й�ȣ ��ȣȭ ó��
			try {
				dePw = DataEncrypt.messageDigest("MD5", pw);
			} catch (NoSuchAlgorithmException nae) {
				nae.printStackTrace();
			} // end catch
		    
		  UserDAO uDAO = UserDAO.getInstance();
		  curpw= uDAO.selectPw(RunMarketMain.userId,dePw);
		    if(curpw.isEmpty()) {
		    	JOptionPane.showMessageDialog(psi, "��й�ȣ�� ��Ȯ���� �ʽ��ϴ�.");
		    }else {
		    	JOptionPane.showMessageDialog(psi, "��й�ȣ Ȯ�� �Ϸ�");
		    	new PwUpdate(rmm,id);
		    }//end else
		    
		}//end if
		
	}//PersonalInformEvt
	public void modifyRegister() throws SQLException {
		
		DecimalFormat df = new DecimalFormat("00");
		String id = psi.getJtfId().getText().trim();
		//�ؽ�Ʈ�ʵ��� ����(������) �� ��������
		String mfPhone1 = (String) psi.getJcbPhoneNum().getSelectedItem();
		String mfPhone2 = psi.getJtfPhone1().getText().trim();
		String mfPhone3 = psi.getJtfPhone2().getText().trim();
		String mfPhone =mfPhone1+"-"+mfPhone2+"-"+mfPhone3;
		String mfLoc = df.format(psi.getJcbLoc().getSelectedIndex());
		String mfPwHint = df.format(psi.getJcbPwHint().getSelectedIndex());
		String mfPwAnswer = (String)psi.getJtfPwAnswer().getText().trim();
		//ȸ�� ���� ��������
		UserDAO uDAO = UserDAO.getInstance();
		PersonalInformVO piVO = uDAO.selectPersonalInfom(id);
		
		
		if(mfPhone2.isEmpty()||mfPhone3.isEmpty()) {
			JOptionPane.showMessageDialog(psi, "������ ����ó ������ �Է����ּ���.");
			return;
		}//end if
		if ((mfPhone2.length() < 3 || mfPhone2.length() > 4) || mfPhone3.length() != 4) {
			JOptionPane.showMessageDialog(psi, "����ó�� ��Ȯ�� �������ּ���.");
			return;
		}//end if
			int phone2 = 0;
			int phone3 = 0;
			try {
				phone2 = Integer.valueOf(mfPhone2);
				phone3 = Integer.valueOf(mfPhone3);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(psi, "����ó�� �������ĸ� �����մϴ�.");
				return;
			} // end catch
			mfPhone = mfPhone1 + "-" + mfPhone2 + "-" + mfPhone3;
			if(!mfPhone.equals(piVO.getPhone())) {
				flag = false;
				modifyInformVO miVO = new modifyInformVO(id,"phone",mfPhone);
			if(flag==uDAO.updateThing(miVO)) {
				JOptionPane.showMessageDialog(psi, "�� ���� ����");
					return;
			}else {
				 flag =true;
			}//end else
			 }//end if
			
		
		 
		
		if(!mfLoc.equals("00")&&!mfLoc.equals(piVO.getLoc())){
			flag = false;
			modifyInformVO miVO = new modifyInformVO(id, "loc_code", mfLoc);
			if(flag==uDAO.updateThing(miVO)) {
				JOptionPane.showMessageDialog(psi, "�� ���� ����");
				return;
			}else {
				flag =true;
			}//end else
		}//end if
			
		if(!mfPwHint.equals("00")&&!mfPwHint.equals(piVO.getPwHint())) {
			flag = false;
			modifyInformVO miVO = new modifyInformVO(id, "hint_code", mfPwHint);
			if(flag==uDAO.updateThing(miVO)) {
				JOptionPane.showMessageDialog(psi, "�� ���� ����");
				return;
			}else {
				flag =true;
			}//end else
		
		}//end if
		if(!mfPwAnswer.isEmpty()&&!mfPwAnswer.equals(piVO.getPwAnswer())) {
			flag = false;
			modifyInformVO miVO = new modifyInformVO(id, "answer", mfPwAnswer);
			if(flag==uDAO.updateThing(miVO)) {
				JOptionPane.showMessageDialog(psi, "�� ���� ����");
				return;
			}else {
				flag =true;
			}//end else
		}//end if
		if(flag ==true||PwUpdateEvt.uFlag==true) {
			JOptionPane.showMessageDialog(psi, "������ ����Ǿ����ϴ�.");
			PersonalInformClose();
		}else {
			JOptionPane.showMessageDialog(psi, "������ ������ �Է����ּ���.");
		}
	}//modifyRegister
	public void PersonalInformClose() {
		psi.dispose();
	}//PersonalInform
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==psi.getJbtPwUpdate()) {
			PwUpdateEvt.uFlag=false;
			try {
				modifyPw();
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
		}//end if
		if(ae.getSource()==psi.getJbtCancle()) {
			
				PersonalInformClose();
			
		}//end if
		if(ae.getSource()==psi.getJbtRegister()) {
			try {
				modifyRegister();
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
		}//end if
	}//actionPerformed
	
	
}//class
