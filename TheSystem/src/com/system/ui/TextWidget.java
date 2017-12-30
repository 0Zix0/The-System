package com.system.ui;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.system.TextRenderer;
import com.zengine.Input;
import com.zengine.graphics.Display;

public class TextWidget extends UIWidget {

	private String label;
	private int size;
	
	private boolean editing = false;
	
	private StringBuffer buffer;
	private int inputSize = 0;
	
	public TextWidget(String label, int size) {
		this.label = label;
		this.size = size;
		this.buffer = new StringBuffer(size);
		Input.addKeyListener((int key) -> {
			if(isSelected()) {
				if(Input.isKeyDownR(GLFW.GLFW_KEY_ENTER)) {
					editing = !editing;
					return;
				}
			}
			if(editing && isSelected()) {
				if(key == GLFW.GLFW_KEY_BACKSPACE) {
					if(inputSize != 0) {
						buffer.deleteCharAt(inputSize - 1);
						inputSize--;
					}
				} else if(inputSize < size) {
					buffer.append((char)key);					
					inputSize++;
				}
			}
		});
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
		if(inputSize != size) {
			renderer.text("_", x + 4 + inputSize, y, new Vector3f(1.0f), new Vector3f(0.0f));
		}
		renderer.text(buffer.toString(), x + 4, y, new Vector3f(1.0f), new Vector3f(0.0f));
		renderer.text("]", x + 4 + size, y, new Vector3f(0.2f), new Vector3f(0.0f));
		if(isSelected()) {
			renderer.text(">", (x + 3) - label.length() - 1, y, new Vector3f(1.0f), new Vector3f(0.0f));
			renderer.text("[ENTER] to edit/finish", 0, (Display.getHeight() / 16) - 1, new Vector3f(1.0f), new Vector3f(0.0f));
		}
	}

	public String getValue() {
		return buffer.toString();
	}
	
	public void update() {
	}
	
	public boolean locked() {
		return editing;
	}
}
