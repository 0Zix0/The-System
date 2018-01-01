package com.system.util;

import java.awt.Color;

import org.joml.Vector3f;

public class Utilities {

	public static Vector3f convertColor(Color color) {
		float r = color.getRed(); // 0 - 255
		float g = color.getGreen();
		float b = color.getBlue();
		return new Vector3f(r / 255, g / 255, b / 255);
	}
	
	public static Vector3f[] convertColors(Color[] colors) {
		Vector3f[] res = new Vector3f[colors.length];
		
		for(int i = 0; i < res.length; i++) {
			res[i] = convertColor(colors[i]);
		}
		
		return res;
	}
}
