package com.system;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.zengine.Spritesheet;
import com.zengine.graphics.Display;
import com.zengine.graphics.Renderer;

public class TextRenderer extends Renderer {
	
	private TextBatch batchRenderer;
	private Spritesheet spritesheet;
	
	private float pixelHeight, pixelWidth;
	private float charWidth, charHeight;
	private float scale;

	//private static final float SPRITE_WIDTH = 8.0f;
	//private static final float SPRITE_HEIGHT = 8.0f;
	
	private static float SPRITE_WIDTH = 8.0f;
	private static float SPRITE_HEIGHT = 8.0f;
	
	public TextRenderer(float scale) {
		this.batchRenderer = new TextBatch(spritesheet = new Spritesheet("res/sprites/cp437_8x8.png"));
		this.pixelWidth = 2.0f / Display.getWidth();
		this.pixelHeight = 2.0f / Display.getHeight();
		this.scale = scale;
		this.SPRITE_WIDTH = spritesheet.getWidth() / 16;
		this.SPRITE_HEIGHT = spritesheet.getHeight() / 16;
		this.charWidth = SPRITE_WIDTH * pixelWidth * scale;
		this.charHeight = SPRITE_HEIGHT * pixelHeight * scale;
	}
	
	public void start() {
		batchRenderer.start();
	}

	public void text(char c, int cx, int cy, Vector3f foreground, Vector3f background) {
		text(c + "", cx, cy, foreground, background);
	}
	
	public void text(String str, int cx, int cy, Vector3f foreground, Vector3f background) {
		Vector2f pos = getNDC(cx * SPRITE_WIDTH * scale, cy * SPRITE_HEIGHT * scale);
		pos.y -= charHeight;
		
		for(int i = 0; i < str.length(); i++) {
			int c = (int)str.charAt(i);
			int sheetX = c % 16;
			int sheetY = c / 16;
			sheetX *= SPRITE_WIDTH;
			sheetY *= SPRITE_HEIGHT;
			//batchRenderer.submit(new Vector3f(pos, 0), new Vector2f(charWidth, charHeight), spritesheet.getCoords((c - 32) * (int)SPRITE_WIDTH, 0, (int)SPRITE_WIDTH, (int)SPRITE_HEIGHT));
			batchRenderer.submit(new Vector3f(pos, 0), new Vector2f(charWidth, charHeight), spritesheet.getCoords(sheetX, sheetY, (int)SPRITE_WIDTH, (int)SPRITE_HEIGHT), foreground, background);
			pos.x += charWidth;
		}
	}
	
	private Vector2f getNDC(float x, float y) {
		float nx = (2.0f * x) / Display.getWidth() - 1.0f;
		float ny = 1.0f - (2.0f * y) / Display.getHeight();
		return new Vector2f(nx, ny);
	}
	
	public void render() {
		batchRenderer.render();
	}
}
