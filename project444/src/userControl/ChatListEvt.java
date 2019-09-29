package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Compare.Descending;
import chatTest.ChattingView;
import userDAO.UserDAO;
import userRun.RunMarketMain;
import userVO.ChatListVO;
import userVO.DCodeAndIdAO;
import userView.ChatList;

public class ChatListEvt extends MouseAdapter implements ActionListener {
	private ChatList cl;
	private String flag = "buy";

	public ChatListEvt(ChatList cl) {
		this.cl = cl;
		refresh(flag);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cl.getJbBuy()) {
			flag = "buy";
			refresh(flag);
		}
		if (e.getSource() == cl.getJbSell()) {
			flag = "sell";
			refresh(flag);
		}
		if (e.getSource() == cl.getJbRefresh()) {
			refresh(flag);
		}

	}

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getClickCount() == 2) {// 더블클릭
			if (me.getSource() == cl.getJtProductList()) {
				openChat(cl.getJtProductList());
			} // end if
			if (me.getSource() == cl.getJtProductList2()) {
				openChat(cl.getJtProductList2());
			}
		} // end if
	}

	public void openChat(JTable list) {

		String dealCode = (String) list.getValueAt(list.getSelectedRow(), 5);
		String id = (String) list.getValueAt(list.getSelectedRow(), 1);
		new ChattingView(RunMarketMain.userId, new DCodeAndIdAO(dealCode, id));
		refresh(flag);

	}

	public void refresh(String flag) {
		UserDAO uDAO = UserDAO.getInstance();
		DefaultTableModel dtm = cl.getDtmProductList();
		DefaultTableModel dtm2 = cl.getDtmProductList2();
		dtm.setRowCount(0);
		dtm2.setRowCount(0);

		Object[] rowData = null;
		try {
			List<ChatListVO> list = uDAO.setChatList(flag);
			ChatListVO clVO = null;
			list.sort(new Descending());
			for (int i = 0; i < list.size(); i++) {
				clVO = list.get(i);
				rowData = new Object[6];

				rowData[0] = clVO.getProName();
				rowData[1] = clVO.getId();
				rowData[2] = clVO.getLoc();
				rowData[3] = clVO.getTime();
				if (clVO.getReadFlag().equals("N") && !clVO.getLastchat().startsWith(RunMarketMain.userId)) {
					rowData[3] += "!";
				}
				if (clVO.getLastchat().isEmpty()) {
					rowData[4] = "대화를 시작하세요.";
				} else {
					rowData[4] = clVO.getLastchat();
				}
				rowData[5] = clVO.getDealCode();
				if (clVO.getAllFlag().equals("P")) {

					dtm.addRow(rowData);
				} else {
					dtm2.addRow(rowData);
				}

			} // end for

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// refresh

	public ChatList getCl() {
		return cl;
	}

	public String getFlag() {
		return flag;
	}

}
