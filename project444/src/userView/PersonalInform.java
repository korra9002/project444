package userView;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import userControl.PersonalInformEvt;
import userDAO.UserDAO;
import userRun.RunMarketMain;

public class PersonalInform extends JDialog {
		private JLabel jlId, jlPw, jlName, jlGender, jlPhone, jlLoc, jlPwHint, jlPwAnswer;
		private JTextField jtfId, jtfName, jtfPhone, jtfPwAnswer;
		private JButton jbtRegister, jbtCancle ,jbtPwUpdate;
		private ButtonGroup bgGender;
		private JRadioButton jrbWomen, jrbMan;
		private DefaultComboBoxModel<String> dcbPhoneNum,dcbLoc, dcbPwHint;
		private JComboBox<String> jcbPhoneNum,jcbLoc, jcbPwHint;
		private String[] PwHint;
		
		public PersonalInform(String id, RunMarketMain rmm) throws SQLException {
			super(rmm,"개인정보 수정");
			// JLabel
			jlId = new JLabel("아이디  ");
			jlPw = new JLabel("비밀번호  ");
			jlName = new JLabel("이름  ");
			jlGender = new JLabel("성별");
			jlPhone = new JLabel("연락처");
			jlLoc = new JLabel("지역");
			jlPwHint = new JLabel("비밀번호 힌트");
			jlPwAnswer = new JLabel("힌트 정답");
			//회원 정보 가져오기
			UserDAO uDAO = UserDAO.getInstance();
			// JTextField
			jtfId = new JTextField(id);
			jtfName = new JTextField(uDAO.selectPersonalInfom(id)[0]);
			jtfPhone = new JTextField(uDAO.selectPersonalInfom(id)[2]);
			jtfPwAnswer = new JTextField();


			// JButton
			jbtRegister = new JButton("등록");
			jbtCancle = new JButton("취소");
			jbtPwUpdate = new JButton("비밀번호 변경");

			// CheckBoxGroup
			bgGender = new ButtonGroup();
			// CheckBox
			jrbWomen = new JRadioButton("여자");
			jrbMan = new JRadioButton("남자");
			
			bgGender.add(jrbMan);
			bgGender.add(jrbWomen);
			
			// Loc ComboBox
			String[] loc = { "-지역선택-","강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구",
					"서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구" };
			dcbLoc = new DefaultComboBoxModel<String>(loc);
			// Password Hint ComboBox
			String[] PwHint = { "-비밀번호 힌트 선택-","나의 좌우명은?", "나의 보물 제 1호는?", "나의 휴대폰 번호 끝 네자리는?", "가장 기억에 남거나 좋아하는 장소는?", "가장 친한 친구 이름은?",
					"가장 받고 싶은 선물은?", "가장 좋아하는 노래 제목은?", "가장 좋아하는 연예인은?", "가장 좋아하는 음식은?", "가장 좋아하는 숫자는?", "가장 좋아하는 색깔은?" };
			dcbPwHint = new DefaultComboBoxModel<String>(PwHint);
			// PhoneNum ComboBox
			String[] phoneNum = {"010","011","012","016","017","018"};
			dcbPhoneNum = new DefaultComboBoxModel<String>(phoneNum);
			// JComboBox
			jcbLoc = new JComboBox<String>(dcbLoc);
			jcbPwHint = new JComboBox<String>(dcbPwHint);
			jcbPhoneNum = new JComboBox<String>(dcbPhoneNum);
			// 수동배치
			setLayout(null);

			jlId.setBounds(60, 100, 80, 25);
			jlPw.setBounds(60, 150, 80, 25);
			jlName.setBounds(60, 200, 50, 25);
			jlGender.setBounds(60, 260, 50, 25);
			jlPhone.setBounds(60, 310, 60, 25);
			jlLoc.setBounds(60, 360, 50, 25);
			jlPwHint.setBounds(40, 410, 100, 25);
			jlPwAnswer.setBounds(60, 470, 100, 25);

			jtfId.setBounds(140, 100, 180, 25);
			jtfName.setBounds(140, 200, 220, 25);
			jtfPhone.setBounds(205, 310, 180, 25);
			jtfPwAnswer.setBounds(140, 470, 250, 25);

			jrbMan.setBounds(160, 260, 100, 20);
			jrbWomen.setBounds(260, 260, 100, 20);

			jcbLoc.setBounds(140, 360, 150, 25);
			jcbPwHint.setBounds(140, 410, 250, 25);
			jcbPhoneNum.setBounds(140, 310, 60, 25);
			
			jbtRegister.setBounds(230, 580, 100, 30);
			jbtCancle.setBounds(360, 580, 100, 30);
			jbtPwUpdate.setBounds(140, 150, 220, 25);

			// 배치
			add(jlId);
			add(jlPw);
			add(jlName);
			add(jlGender);
			add(jlLoc);
			add(jlPhone);
			add(jlPwHint);
			add(jlPwAnswer);

			add(jtfId);
			add(jtfName);
			add(jtfPhone);
			add(jtfPwAnswer);

			add(jrbMan);
			add(jrbWomen);

			add(jcbLoc);
			add(jcbPwHint);
			add(jcbPhoneNum);
			
			add(jbtRegister);
			add(jbtCancle);
			add(jbtPwUpdate);
			//이벤트처리
			PersonalInformEvt psie = new PersonalInformEvt(this,rmm); 
			jbtPwUpdate.addActionListener(psie);
			//편집불가
			jtfId.setEditable(false);
			//배경색 변경
			jtfId.setBackground(Color.white);
			// WindowSizing
			setBounds(100, 100, 550, 700);
			// visible
			setVisible(true);
			// close
			addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosed(WindowEvent e) {
				dispose();
				}
				
			});
		}// SineUp

		public JTextField getJtfId() {
			return jtfId;
		}

		public JTextField getJtfName() {
			return jtfName;
		}

		public JTextField getJtfPhone() {
			return jtfPhone;
		}

		public JTextField getJtfPwAnswer() {
			return jtfPwAnswer;
		}

	

		public JButton getJbtRegister() {
			return jbtRegister;
		}

		public JButton getJbtCancle() {
			return jbtCancle;
		}

		public JButton getJbtPwUpdate() {
			return jbtPwUpdate;
		}

		public ButtonGroup getBgGender() {
			return bgGender;
		}

		public JRadioButton getJrbWomen() {
			return jrbWomen;
		}

		public JRadioButton getJrbMan() {
			return jrbMan;
		}

		public JComboBox<String> getJcbPhoneNum() {
			return jcbPhoneNum;
		}

		public JComboBox<String> getJcbLoc() {
			return jcbLoc;
		}

		public JComboBox<String> getJcbPwHint() {
			return jcbPwHint;
		}

		public String[] getPwHint() {
			return PwHint;
		}

		



}//class
