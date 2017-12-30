package com.system.ui;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import com.system.TextRenderer;
import com.zengine.Input;

public class UIScreen {

	private static final int GRID_SIZE_WIDTH = 4;
	private static final int GRID_SIZE_HEIGHT = 9;
	private static final int GRID_WIDTH = 20;
	private static final int GRID_HEIGHT = 5;
	
	private int currentSelection = 0;
	private ArrayList<UIWidget> widgets = new ArrayList<>();
	
	private int xPos, yPos;
	private int gap = 1;
	
	public UIScreen(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void addWidget(UIWidget widget) {
		widgets.add(widget);
		if(widgets.size() == 1) widget.setSelected(true);
	}
	
	public void update() {
		if(!widgets.get(currentSelection).locked()) {
			if (Input.isKeyDownR(GLFW.GLFW_KEY_DOWN)) {
				widgets.get(currentSelection).setSelected(false);
				if (currentSelection + 1 > widgets.size() - 1) {
					currentSelection = 0;
				} else {
					currentSelection++;
				}
				widgets.get(currentSelection).setSelected(true);
			}
			if (Input.isKeyDownR(GLFW.GLFW_KEY_UP)) {
				widgets.get(currentSelection).setSelected(false);
				if (currentSelection - 1 < 0) {
					currentSelection = widgets.size() - 1;
				} else {
					currentSelection--;
				}
				widgets.get(currentSelection).setSelected(true);
			}
		}
		
		for(UIWidget w : widgets) {
			w.update();
		}
	}
	
	public void render(TextRenderer renderer) {
		int currentY = yPos;
		int i = 0;
		for(UIWidget widget : widgets) {
			widget.render(renderer, xPos, currentY);
			currentY += gap + 1;
			i++;
		}
	}
}
