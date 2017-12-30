package com.system;

import java.awt.Color;
import java.util.ArrayList;

import org.joml.Vector3f;

import com.system.util.Utilities;

public class ColorPalette {

	public static ArrayList<ColorPalette> palettes = new ArrayList<>();
	static {
		palettes.add(new ColorPalette("red", new Color(239, 40, 40), new Color(207, 0, 0)));
		palettes.add(new ColorPalette("green", new Color(113, 211, 20), new Color(92, 168, 16)));
		palettes.add(new ColorPalette("blue", new Color(52, 101, 166), new Color(32, 73, 134)));
		palettes.add(new ColorPalette("orange", new Color(247, 121, 0), new Color(207, 83, 0)));
		palettes.add(new ColorPalette("yellow", new Color(239, 215, 0), new Color(199, 162, 0)));
		palettes.add(new ColorPalette("purple", new Color(117, 81, 121), new Color(93, 52, 101)));
	}
	
	private String name;
	private Vector3f primary, secondary;
	
	public ColorPalette(String name, Color primary, Color secondary) {
		this.name = name;
		this.primary = Utilities.convertColor(primary);
		this.secondary = Utilities.convertColor(secondary);
	}
	
	public Vector3f getPrimary() {
		return primary;
	}
	
	public Vector3f getSecondary() {
		return secondary;
	}
	
	public String getName() {
		return name;
	}
}
