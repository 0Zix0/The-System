package com.system.ui;

import com.system.TextRenderer;

public abstract class UIWidget {

	private boolean isSelected = false;
	
	public abstract void render(TextRenderer renderer, int x, int y);
	public abstract void update();
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setSelected(boolean s) {
		isSelected = s;
	}
	
	public boolean locked() {
		return false;
	}
}
