package com.system.world.map;

import java.util.Random;

import org.joml.Vector3f;

import com.system.TextRenderer;
import com.system.world.Tile;
import com.system.world.entity.Entity;

public class Map {

	private int width;
	private int height;
	private Tile[] tiles;
	private int[] colors;
	private int[] chars;
	private Entity[] entities;
	
	public Map(int width, int height, Tile[] tiles, int[] colors, int[] chars) {
		this.width = width;
		this.height = height;
		this.tiles = tiles;
		this.colors = colors;
		this.chars = chars;
		this.entities = new Entity[width * height];
	}
	
	public Map() {
		this.width = 80;
		this.height = 45;
		this.tiles = new Tile[width * height];
		this.colors = new int[width * height];
		this.chars = new int[width * height];
		
		int[] mapGen = {
				0, 1, 1, 1
		};
		
		Random random = new Random();
		for(int i = 0; i < tiles.length; i++) {
			int id = mapGen[random.nextInt(mapGen.length)];
			tiles[i] = Tile.tiles[id];
			colors[i] = random.nextInt(Tile.tiles[id].getColors().length);
			chars[i] = random.nextInt(Tile.tiles[id].getCharacters().length);
		}
	}
	
	public void addEntity(int x, int y, Entity entity) {
		entity.setPosition(x, y);
		entities[x + y * width] = entity;
	}
	
	public void render(TextRenderer renderer) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				int pos = x + y * width;
				Tile t = tiles[pos];
				Entity e = entities[pos];
				if(e != null) {
					renderer.text(e.getCharacter(), x, y, e.getColor().getForeground(), e.getColor().getBackground());
				} else {					
					renderer.text(t.getCharacters()[chars[pos]], x, y, t.getColors()[colors[pos]], new Vector3f(0.0f));
				}
			}
		}
	}
	
	public Entity entityAt(int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height) {
			return null;
		}
		return entities[x + y * width];
	}
	
	public Tile tileAt(int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height) {
			//TODO: perhaps return null tile?
			return null;
		}
		return tiles[x + y * width];
	}
}
