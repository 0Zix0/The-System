package com.system.world;

import java.awt.Color;

import org.joml.Vector3f;

import com.system.util.Utilities;

public class Tile {
	
	public static final Tile[] tiles = new Tile[128];
	
	static {
		// YELLOW WALL
		registerTile(0, new Tile().setSolid(true).setCharacters(new char[] {35, 35, 19}).setColors(TileColor.WALL_COLORS));
		
		// GRAY OUTSIDE FLOOR
		registerTile(1, new Tile().setSolid(false).setCharacters(new char[] {249}).setColors(TileColor.FLOOR_COLORS));
		
		// HOUSE WALL
		registerTile(2, new Tile().setSolid(true).setCharacters(new char[] {'#'}).setColors(TileColor.HOUSE_COLORS));
		
		// BROWN INSIDE FLOOR
		registerTile(3, new Tile().setSolid(false).setCharacters(new char[] {249}).setColors(TileColor.HOUSE_FLOOR_COLORS));
	}
	
	public static void registerTile(int id, Tile tile) {
		tiles[id] = tile;
		tiles[id].id = id;
	}
	
	private int id;
	private char[] chars = new char[] {' '};
	private Vector3f[] colors = new Vector3f[] {new Vector3f(1.0f)};
	private boolean solid = true;
	
	public Tile setCharacters(char[] chars) {
		this.chars = chars;
		return this;
	}

	public Tile setSolid(boolean solid) {
		this.solid = solid;
		return this;
	}

	public Tile setColors(Vector3f[] colors) {
		this.colors = colors;
		return this;
	}
	

	public Tile setColors(Color[] colors) {
		this.colors = Utilities.convertColors(colors);
		return this;
	}
	
	public Vector3f[] getColors() {
		return colors;
	}
	
	public char[] getCharacters() {
		return chars;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public int getID() {
		return id;
	}
}
