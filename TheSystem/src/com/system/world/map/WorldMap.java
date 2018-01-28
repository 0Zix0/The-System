package com.system.world.map;

import java.util.Random;

import org.joml.Vector3f;

import com.system.TextRenderer;
import com.system.world.Tile;
import com.system.world.entity.DoorEntity;
import com.system.world.entity.Entity;

public class WorldMap extends Map {

	private int width;
	private int height;
	private Tile[] tiles;
	private int[] colors;
	private int[] chars;
	private int[] ceilings;
	private Entity[] entities;
	
	private int currentCeiling = 0;
	
	public WorldMap(int width, int height, Tile[] tiles, int[] colors, int[] chars, int[] ceilings) {
		this.width = width;
		this.height = height;
		this.tiles = tiles;
		this.colors = colors;
		this.chars = chars;
		this.ceilings = ceilings;
		this.entities = new Entity[width * height];
	}
	
	public WorldMap() {
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
	
	public boolean isTileSolid(int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height) {
			return false;
		}
		return tiles[x + y * width].isSolid();
	}
	
	public String getNameAt(int x, int y) {
		Entity entity = entityAt(x, y);
		if(entity == null) {
			return "World";
		} else {
			return entity.getName();
		}
	}
	
	public void render(TextRenderer renderer) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				int pos = x + y * width;

				Tile t = tiles[pos];
				Entity e = entities[pos];
				
				/*
				int ceiling = ceilings[x + y * width];
				if(ceiling != 0 && ceiling != currentCeiling) {
					if(!(e instanceof DoorEntity)) {
						renderer.text('#', x, y, new Vector3f(0.1f), new Vector3f(0.0f));
						continue;
					}
				}
				*/
				
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
	
	public void setCurrentCeiling(int x, int y) {
		this.currentCeiling = ceilings[x + y * width];
	}
}
