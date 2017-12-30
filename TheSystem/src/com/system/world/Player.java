package com.system.world;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.system.ColorPalette;
import com.system.TextRenderer;
import com.zengine.Input;

public class Player {

	private String name;
	private Gender gender;
	private ColorPalette palette;
	
	private int xPos, yPos;
	
	public Player(String name, Gender gender, ColorPalette palette) {
		this.name = name;
		this.gender = gender;
		this.palette = palette;
		this.xPos = 1;
		this.yPos = 1;
	}
	
	public void render(TextRenderer renderer) {
		renderer.text((char)1, xPos, yPos, palette.getPrimary(), new Vector3f(0.0f));
	}
	
	public void update() {
		if(Input.isKeyDownR(GLFW.GLFW_KEY_DOWN)) {
			yPos++;
		}
		if(Input.isKeyDownR(GLFW.GLFW_KEY_UP)) {
			yPos--;
		}

		if(Input.isKeyDownR(GLFW.GLFW_KEY_LEFT)) {
			xPos--;
		}
		if(Input.isKeyDownR(GLFW.GLFW_KEY_RIGHT)) {
			xPos++;
		}
	}
}
