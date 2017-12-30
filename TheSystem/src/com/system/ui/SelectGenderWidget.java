package com.system.ui;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.system.ColorPalette;
import com.system.TextRenderer;
import com.system.world.Gender;
import com.zengine.Input;

public class SelectGenderWidget extends UIWidget {

	private String label;
	
	private Gender gender;
	
	public SelectGenderWidget(String label) {
		this.label = label;
		this.gender = Gender.MALE;
	}
	
	public void render(TextRenderer renderer, int x, int y) {
		Vector3f labelColor;
		if(isSelected()) {
			labelColor = new Vector3f(0.5f);
		} else {
			labelColor = new Vector3f(0.2f);
		}
		renderer.text(label, (x + 3) - label.length(), y, labelColor, new Vector3f(0.0f));
		renderer.text("[", x + 3, y, new Vector3f(0.2f), new Vector3f(0.0f));
		renderer.text("]", x + 7, y, new Vector3f(0.2f), new Vector3f(0.0f));
		if(isSelected()) {
			renderer.text(">", (x + 3) - label.length() - 1, y, new Vector3f(1.0f), new Vector3f(0.0f));
		}
		renderer.text("<", x + 4, y, new Vector3f(1.0f), new Vector3f(0.0f));
		renderer.text(">", x + 6, y, new Vector3f(1.0f), new Vector3f(0.0f));
		renderer.text(gender.symbol + "", x + 5, y, new Vector3f(1.0f), new Vector3f(0.0f));
	}
	
	public Gender getSelected() {
		return gender;
	}

	public void update() {
		if(isSelected()) {
			if (Input.isKeyDownR(GLFW.GLFW_KEY_RIGHT) || Input.isKeyDownR(GLFW.GLFW_KEY_LEFT)) {
				if(gender == Gender.MALE) {
					gender = Gender.FEMALE;
				} else {
					gender = Gender.MALE;
				}
			}
		}
	}
}