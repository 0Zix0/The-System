package com.system.world;

import java.awt.Color;

import org.joml.Vector3f;

import com.system.util.Utilities;

public enum Tile {

	WALL1(35, new Color(255, 255, 85)),
	WALL2(35, new Color(246, 246, 76)),
	WALL3(35, new Color(239, 239, 59)),
	WALL4(19, new Color(255, 255, 85)),
	WALL5(19, new Color(239, 239, 59)),

	BUILDING1('#', new Color(61, 61, 61)),
	BUILDING2('#', new Color(79, 79, 79)),
	BUILDING3('#', new Color(40, 40, 40));
	
	public char c;
	public Vector3f color;
	Tile(int c, Color color) {
		this.c = (char)c;
		this.color = Utilities.convertColor(color);
	}
}
