package com.system.state;

import org.lwjgl.glfw.GLFW;

import com.system.TextRenderer;
import com.zengine.Input;

public class OptionsState extends State {

	public void render(TextRenderer renderer) {
		
	}
	
	public void update() {
		if(Input.isKeyDownR(GLFW.GLFW_KEY_ESCAPE)) {
			StateManager.popState();
		}
	}
}
