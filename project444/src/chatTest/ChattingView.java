package chatTest;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicScrollBarUI;

import adminView.ScrollBarUI;
import userVO.DCodeAndIdAO;
import userView.MarketMain;

public class ChattingView extends JFrame {
	private JLabel jlNotice, jlResult;
	private JTextArea jtaChatView;
	private JTextField jtaChatField;
	private JButton jbtProductInfo, jbtSend, jbtOk, jbtCancle;
	private JScrollPane jsp;

	private String id;

	public ChattingView(String id, DCodeAndIdAO DIAO) {
		super("채팅");
		this.id = id;

		jlResult = new JLabel();

		jlNotice = new JLabel();

		jtaChatView = new JTextArea();
		jtaChatField = new JTextField();
		jtaChatView.setLineWrap(true);
		jsp = new JScrollPane(jtaChatView);

		JPanel jp = new JPanel();

		jbtProductInfo = new JButton("거래중인 상품 정보 보기");
		jbtSend = new JButton("보내기");
		jbtOk = new JButton("예");
		jbtCancle = new JButton("아니요");

		jp.add(jlNotice);
		jp.add(jbtOk);
		jp.add(jbtCancle);
		// 수동배치
		setLayout(null);
		// TextArea
		jsp.setBounds(20, 60, 345, 380);
		jtaChatField.setBounds(20, 450, 260, 60);
		// JButton
		jbtProductInfo.setBounds(90, 5, 200, 50);
		jlResult.setBounds(90, 5, 200, 50);
		jbtSend.setBounds(285, 450, 80, 60);
		jbtOk.setBounds(240, 520, 60, 25);
		jbtCancle.setBounds(300, 520, 75, 25);
		// JLabel
		jlNotice.setBounds(35, 520, 250, 25);

		Container c = getContentPane();
		c.setBackground(new Color(0xf6f2ef));
		jbtCancle.setBackground(new Color(0xFFCC66));
		jbtOk.setBackground(new Color(0xFFCC66));
		jbtProductInfo.setBackground(new Color(0xFFCC66));
		jbtSend.setBackground(new Color(0xFFCC66));

		jsp.getVerticalScrollBar().setBackground(new Color(0xFFFFFF));
		jsp.getVerticalScrollBar().setUI(new ScrollBarUI());

		add(jbtProductInfo);
		add(jsp);
		add(jtaChatField);
		add(jbtSend);
		add(jlNotice);
		add(jbtOk);
		add(jbtCancle);
		jlNotice.setVisible(true);
		jbtOk.setVisible(false);
		jbtCancle.setVisible(false);

		setBounds(100, 100, 400, 600);
		jtaChatView.setEditable(false);

		setVisible(true);

		ChattingViewEvt cVE = new ChattingViewEvt(this, DIAO);

		jbtProductInfo.addActionListener(cVE);
		jbtSend.addActionListener(cVE);
		jtaChatField.addActionListener(cVE);
		jbtOk.addActionListener(cVE);
		jbtCancle.addActionListener(cVE);

	}// Chatting

	public JLabel getJlNotice() {
		return jlNotice;
	}

	public JLabel getJlResult() {
		return jlResult;
	}

	public JTextArea getJtaChatView() {
		return jtaChatView;
	}

	public JTextField getJtaChatField() {
		return jtaChatField;
	}

	public JButton getJbtProductInfo() {
		return jbtProductInfo;
	}

	public JButton getJbtSend() {
		return jbtSend;
	}

	public JButton getJbtOk() {
		return jbtOk;
	}

	public JButton getJbtCancle() {
		return jbtCancle;
	}

	public JScrollPane getJsp() {
		return jsp;
	}

	public String getId() {
		return id;
	}

}// class
