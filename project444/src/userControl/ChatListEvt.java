package userControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		if(e.getSource() == cl.getJbBuy()) {
			flag = "buy";
			refresh(flag);
		}
		if(e.getSource() == cl.getJbSell()) {
			flag = "sell";
			refresh(flag);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
	}
	
	public void refresh(String flag) {
		
	}
	

}
