package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import userDAO.UserDAO;
import userVO.ForgotIdVO;
import userView.ForgotId;

public class ForgotIdEvt implements ActionListener {
	private ForgotId fi ;
	
	public ForgotIdEvt(ForgotId fi) {
		this.fi=fi;
	}//ForgotIdEvt
	
	public void ForgotIdClose() {
		fi.dispose();
	}//ForgotIdClose
	public void searchId() throws SQLException {
		String name="";
		String phone = "";
		String phone1 = "";
		String stPhone;
		String stPhone2;
		String id = "";
		if(fi.getJtfName().getText().isEmpty()) {
			JOptionPane.showMessageDialog(fi, "�̸��� �Է����ּ���.");
			return;
		}else {
			
			DecimalFormat df = new DecimalFormat("0000");
			if (fi.getJtfPhone().getText().isEmpty() || fi.getJtfPhone2().getText().isEmpty()) {
				JOptionPane.showMessageDialog(fi, "����ó ������ �Է����ּ���.");
				return;
			} else {
				int phone2 = 0;
				int phone3 = 0;
				try {
					stPhone = fi.getJtfPhone().getText().trim();
					stPhone2 = fi.getJtfPhone2().getText().trim();

					phone2 = Integer.valueOf(stPhone);
					phone3 = Integer.valueOf(stPhone2);

				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(fi, "����ó�� �������ĸ� �����մϴ�.");
					return;
				} // end catch

				if ((stPhone.length() < 3 || stPhone.length() > 4) || stPhone2.length() != 4) {
					JOptionPane.showMessageDialog(fi, "����ó�� ��Ȯ�� �������ּ���.");
					return;
				} else {
					phone1 = fi.getJcbPhoneNum().getSelectedItem().toString();
					phone = phone1 + "-" + stPhone + "-" + stPhone2;	
					
				}//end else
			}//end else
		 name = fi.getJtfName().getText().trim();
		ForgotIdVO fiVO = new ForgotIdVO(phone, name);
		UserDAO uDAO = UserDAO.getInstance();
		id =uDAO.selectIdCheck(fiVO);
		}//end else
		
		if(id.isEmpty()) {
			JOptionPane.showMessageDialog(fi, "�����ϴ� ���̵� �����ϴ�.");
		}else {
			JOptionPane.showMessageDialog(fi, "ȸ������ ���̵�� "+id+"�Դϴ�.");
		}
		
	}//searchId
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==fi.getJbtCancle()) {
			ForgotIdClose();
		}//end if
		if(ae.getSource()==fi.getJbtSearch()||ae.getSource()==fi.getJtfName()) {
			try {
				searchId();
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
		
		}//end if
	}//actionPerformed

}//class
