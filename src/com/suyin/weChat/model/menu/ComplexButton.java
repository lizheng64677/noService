package com.suyin.weChat.model.menu;

import java.util.List;

public class ComplexButton extends Button {

	private static final long serialVersionUID = 80296713535662846L;

	private List<Button> sub_button;

	public List<Button> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<Button> sub_button) {
		this.sub_button = sub_button;
	}

	
}
