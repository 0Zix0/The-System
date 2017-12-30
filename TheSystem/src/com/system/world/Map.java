package com.system.world;

import java.util.Random;

import org.joml.Vector3f;

import com.system.TextRenderer;

public class Map {

	private int width;
	private int height;
	private Tile[] tiles;
	
	public Map() {
		this.width = 80;
		this.height = 45;
		this.tiles = new Tile[width * height];
		Tile[] wallgen = {
				Tile.WALL1,
				Tile.WALL2,
				Tile.WALL3,
				Tile.WALL4,
				Tile.WALL5
		};
		Random random = new Random();
		for(int i = 0; i < tiles.length; i++) {
			tiles[i] = wallgen[random.nextInt(wallgen.length)];
		}
	}
	
	public void render(TextRenderer renderer) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				Tile t = tiles[x + y * width];
				renderer.text(t.c, x, y, t.color, new Vector3f(0.0f));
			}
		}
	}
}
