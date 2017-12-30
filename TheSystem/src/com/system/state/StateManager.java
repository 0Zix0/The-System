package com.system.state;

import java.util.ArrayDeque;
import java.util.Deque;

import com.system.TextRenderer;

public class StateManager {

	private static Deque<State> stateQueue = new ArrayDeque<>();
	static {
		stateQueue.push(new State() {
			public void update() {
			}
			public void render(TextRenderer renderer) {
			}
		});
	}
	
	public static void popState() {
		if(stateQueue.size() != 1) {
			stateQueue.pop();
		}
	}
	
	public static void pushState(State state) {
		stateQueue.push(state);
	}
	
	public static State currentState() {
		return stateQueue.peek();
	}
}
