package com.zengine.event;

public class EventDispatcher {

	private Event event;
	
	public EventDispatcher(Event event) {
		this.event = event;
	}
	
	public void dispatch(Event.Type type, Event event) {
		
	}
}
