package com.suyin.weChat.model.menu;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable{

	private static final long serialVersionUID = -8036452737551322322L;
		
	private List<Button> buttons;

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}
}
