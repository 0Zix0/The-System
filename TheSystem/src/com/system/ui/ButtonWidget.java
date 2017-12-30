package com.system.ui;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.system.ColorPalette;
import com.system.TextRenderer;
import com.zengine.Input;

public class ButtonWidget extends UIWidget {

	private String label;
	private MenuAction action;
	
	public ButtonWidget(String label, MenuAction action) {
		this.label = label;
		this.action = action;
	}
	
	public void render(TextRenderer renderer, int x, int y) {
		Vector3f labelColor;
		if(isSelected()) {
			labelColor = new Vector3f(0.5f);
		} else {
			labelColor = new Vector3f(0.2f);
		}
		renderer.text("[", x - 3, y, new Vector3f(0.2f), new Vector3f(0.0f));
		renderer.text(label, x - 2, y, labelColor, new Vector3f(0.0f));
		renderer.text("]", x + 3, y, new Vector3f(0.2f), new Vector3f(0.0f));
	}

	public void update() {
		if(isSelected()) {
			if(Input.isKeyDownR(GLFW.GLFW_KEY_ENTER) || Input.isKeyDownR(GLFW.GLFW_KEY_SPACE)) {
				action.invoke();
			}
		}
	}
}
