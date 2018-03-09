package com.system.ui.unmanaged;

import java.awt.Color;

import org.joml.Vector2i;
import org.joml.Vector3f;

import com.system.TextRenderer;
import com.system.util.Utilities;

public class Dialogue {

	public static final int DIALOGUE_WIDTH = 30;
	public static final int MAX_TEXT_WIDTH = DIALOGUE_WIDTH - 2;
	
	private String text;
	
	public Dialogue(String text) {
		this.text = text;
	}
	
	public void render(TextRenderer renderer) {
		Vector2i origin = getOrigin();
		
		int row = 0;
		int column = 0;
		int spaceCount = 0;
		String[] words = text.split("\\s+");
		for(String w : words) {
			if(column + w.length() + spaceCount >= MAX_TEXT_WIDTH) {
				column = 0;
				spaceCount = 0;
				row++;
			}
			renderer.text(w, origin.x + column + spaceCount + 1, origin.y + row + 1, new Vector3f(1.0f), new Vector3f(0.0f));
			column += w.length();
			spaceCount++;
		}
		int height = calculateHeight();
		renderer.box('-', origin.x, origin.y, DIALOGUE_WIDTH, height, Utilities.convertColor(Color.RED));
		renderer.fill(' ', origin.x + 1, origin.y + 1, DIALOGUE_WIDTH - 2, height - 2, Utilities.convertColor(Color.BLACK));
	}
	
	private Vector2i getOrigin() {
		// 80x25
		int height = calculateHeight();
		int width = DIALOGUE_WIDTH;
		int x = 40 - (width / 2);
		int y = 13 - (height / 2);
		return new Vector2i(x, y);
	}
	
	public int calculateHeight() {
		String[] words = text.split("\\s+");
		int row = 0;
		int column = 0;
		int spaceCount = 0;
		for(String w : words) {
			if(column + w.length() >= MAX_TEXT_WIDTH) {
				column = 0;
				spaceCount = 0;
				row++;
			}
			column += w.length();
			spaceCount++;
		}
		return row + 2;
	}
}
