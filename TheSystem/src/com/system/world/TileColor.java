package com.system.world;

import java.awt.Color;

import org.joml.Vector3f;

import com.system.util.Utilities;

public class TileColor {

	//TODO: convert these
	public static final Color[] WALL_COLORS = new Color[] {new Color(255, 255, 85), new Color(246, 246, 76), new Color(239, 239, 59), new Color(255, 255, 85)};
	public static final Color[] FLOOR_COLORS = new Color[] {new Color(76, 76, 76)};
	public static final Color[] HOUSE_COLORS = new Color[] {new Color(91, 127, 0), new Color(102, 142, 0), new Color(80, 112, 0)};
	public static final Color[] HOUSE_FLOOR_COLORS = new Color[] {new Color(84, 46, 21)};
	
	private Vector3f foreground;
	private Vector3f background;
	
	public TileColor(Vector3f foreground, Vector3f background) {
		this.foreground = foreground;
		this.background = background;
	}
	
	public TileColor(Color foreground, Color background) {
		this.foreground = Utilities.convertColor(foreground);
		this.background = Utilities.convertColor(background);
	}
	
	public TileColor(Color foreground) {
		this.foreground = Utilities.convertColor(foreground);
		this.background = new Vector3f(0.0f);
	}

	public TileColor(Vector3f foreground) {
		this.foreground = foreground;
		this.background = new Vector3f(0.0f);
	}
	
	public Vector3f getForeground() {
		return foreground;
	}
	
	public Vector3f getBackground() {
		return background;
	}
}
