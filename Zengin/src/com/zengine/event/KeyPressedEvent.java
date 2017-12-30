package com.zengine.event;

public class KeyPressedEvent extends Event {

	private int keycode;
	
	public KeyPressedEvent(int keycode) {
		super(Type.KEY_PRESSED);
		this.keycode = keycode;
	}
	
	public int getKeycode() {
		return keycode;
	}
}
