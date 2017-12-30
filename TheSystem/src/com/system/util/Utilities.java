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
}
