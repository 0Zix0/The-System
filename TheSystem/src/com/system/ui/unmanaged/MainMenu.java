package com.system.ui.unmanaged;

import java.util.ArrayList;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.system.TextRenderer;
import com.system.image.ImageCache;
import com.system.ui.MenuAction;
import com.system.xpreader.XPFile;
import com.zengine.Input;

public class MainMenu {

	private int yPos;
	private ArrayList<MenuItem> items = new ArrayList<>();
	private int itemCount = 0;
	private int currentIndex = 0;

	private XPFile menuDetailL;
	private XPFile menuDetailR;

	public MainMenu(int yPos) {
		this.yPos = yPos;
		this.menuDetailL = ImageCache.get("menu_left");
		this.menuDetailR = ImageCache.get("menu_right");
	}
	
	public MainMenu addItem(String name, MenuAction action) {
		items.add(new MenuItem(name, action));
		itemCount++;
		return this;
	}

	public void update() {
		if (Input.isKeyDownR(GLFW.GLFW_KEY_DOWN)) {
			if (currentIndex + 1 > itemCount - 1) {
				currentIndex = 0;
			} else {
				currentIndex++;
			}
		}
		if (Input.isKeyDownR(GLFW.GLFW_KEY_UP)) {
			if (currentIndex - 1 < 0) {
				currentIndex = itemCount - 1;
			} else {
				currentIndex--;
			}
		}
		if(Input.isKeyDownR(GLFW.GLFW_KEY_ENTER) || Input.isKeyDownR(GLFW.GLFW_KEY_SPACE)) {
			items.get(currentIndex).action.invoke();
		}
	}

	public void render(TextRenderer renderer) {
		int curY = yPos;
		int i = 0;
		for(MenuItem item : items) {
			String s = item.name;
			renderer.text(s, 40 - (s.length() / 2), curY, new Vector3f(1.0f), new Vector3f(0.0f));
			if (i == currentIndex) {
				menuDetailL.render(renderer, 40 - (s.length() / 2) - 4, curY);
				menuDetailR.render(renderer, 40 - (s.length() / 2) + s.length(), curY);
			}
			curY += 2;
			i++;
		}
	}
	
	private class MenuItem {
		public String name;
		public MenuAction action;
		
		public MenuItem(String name, MenuAction action) {
			this.name = name;
			this.action = action;
		}
	}
}
