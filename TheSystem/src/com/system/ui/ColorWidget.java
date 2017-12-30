package com.system.ui;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.system.ColorPalette;
import com.system.TextRenderer;
import com.zengine.Input;

public class ColorWidget extends UIWidget {

	private String label;

	private String arrowLeft = "<";
	private String arrowRight = ">";
	private String solidLeft = new String(new char[] {223});
	
	private int currentSelection = 0;
	
	public ColorWidget(String label) {
		this.label = label;
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
		renderer.text(arrowLeft, x + 4, y, new Vector3f(1.0f), new Vector3f(0.0f));
		renderer.text(arrowRight, x + 6, y, new Vector3f(1.0f), new Vector3f(0.0f));
		renderer.text(solidLeft, x + 5, y, ColorPalette.palettes.get(currentSelection).getPrimary(), ColorPalette.palettes.get(currentSelection).getSecondary());
	}

	public ColorPalette getSelected() {
		return ColorPalette.palettes.get(currentSelection);
	}
	
	public void update() {
		if(isSelected()) {
			if (Input.isKeyDownR(GLFW.GLFW_KEY_RIGHT)) {
				if (currentSelection + 1 > ColorPalette.palettes.size() - 1) {
					currentSelection = 0;
				} else {
					currentSelection++;
				}
			}
			if (Input.isKeyDownR(GLFW.GLFW_KEY_LEFT)) {
				if (currentSelection - 1 < 0) {
					currentSelection = ColorPalette.palettes.size() - 1;
				} else {
					currentSelection--;
				}
			}
		}
	}
}
