package com.zengine.event;

public class Event {

	enum Type {
		KEY_PRESSED,
		KEY_RELEASED
	}
	
	private Type type;
	
	protected Event(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
}
