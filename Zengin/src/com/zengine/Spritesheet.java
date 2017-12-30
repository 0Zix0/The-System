package com.zengine;

import org.joml.Vector2f;
import org.joml.Vector4f;

import com.zengine.graphics.BasicTexture;

public class Spritesheet {

	private BasicTexture texture;
	
	public Spritesheet(String path) {
		texture = new BasicTexture(path);
		//if(texture.getWidth() != texture.getHeight()) throw new RuntimeException();
	}
	
	public void bind() {
		texture.bind();
	}
	
	public void unbind() {
		texture.unbind();
	}
	
	public Vector4f getCoords(int x, int y, int width, int height) {
		float unitWidth = (float)width / (float)texture.getWidth();
		float unitHeight = (float)height / (float)texture.getHeight();
		float unitX = (float)x / (float)texture.getWidth();
		float unitY = (float)y / (float)texture.getHeight();
		
		float xMin = unitX;
		float yMin = unitY;
		float xMax = unitX + unitWidth;
		float yMax = unitY + unitHeight;
		
		return new Vector4f(xMin, yMin, xMax, yMax);
	}
}
