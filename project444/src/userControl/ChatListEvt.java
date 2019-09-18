package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
		if(me.getClickCount() == 2) {//����Ŭ��
			if(me.getSource() == cl.getJtProductList()) {
				openChat();
			}//end if	
			
		}//end if
	}
	
	public void openChat() {
		JTable jtProductList = cl.getJtProductList();
		String dealCode = (String)jtProductList.getValueAt(jtProductList.getSelectedRow(), 5);
		String id = (String)jtProductList.getValueAt(jtProductList.getSelectedRow(), 1);
		new ChattingView( RunMarketMain.userId, new DCodeAndIdAO(dealCode, id));
	}

	public void refresh(String flag) {
		UserDAO uDAO = UserDAO.getInstance();
		DefaultTableModel dtm = cl.getDtmProductList();
		dtm.setRowCount(0);

		Object[] rowData = null;
		try {
			List<ChatListVO> list = uDAO.setChatList(flag);

			ChatListVO clVO = null;
			for (int i = 0; i < list.size(); i++) {
				clVO = list.get(i);
				rowData = new Object[6];

				rowData[0] = clVO.getProName();
				rowData[1] = clVO.getId();
				rowData[2] = clVO.getLoc();
				rowData[3] = clVO.getTime();
				rowData[4] = clVO.getLastchat();
				rowData[5] = clVO.getDealCode();

				dtm.addRow(rowData);
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
