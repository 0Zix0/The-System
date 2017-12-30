package com.zengine.event;

public class KeyReleasedEvent extends Event {

	private int keycode;
	
	public KeyReleasedEvent(int keycode) {
		super(Type.KEY_RELEASED);
		this.keycode = keycode;
	}
	
	public int getKeycode() {
		return keycode;
	}
}
