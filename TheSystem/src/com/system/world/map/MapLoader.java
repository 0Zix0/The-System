package com.system.world.map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

import com.system.world.Tile;

public class MapLoader {
	
	private static final HashMap<Integer, Integer> COLOR_LOOKUP = new HashMap<>();
	static {
		addColor(0xFF000000, 0);
		addColor(0xFFFFFFFF, 1);
		addColor(0xFFFF0000, 2);
		addColor(0xFF00FF00, 3);
	}
	private static void addColor(int color, int id) {
		COLOR_LOOKUP.put(color, id);
	}

	//TODO: remove the requirement for 80x45 maps, man up and implement scrolling
	public static Map load(String file) {
		try {
			BufferedImage image = ImageIO.read(new File(file));
			int width = image.getWidth();
			int height = image.getHeight();
			if(width != 80 || height != 45) {
				System.out.println("Map is not the right size");
				return null;
			}
			
			Tile[] tiles = new Tile[width * height];
			int[] colors = new int[width * height];
			int[] chars = new int[width * height];
			
			Random random = new Random();
			
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					int id = COLOR_LOOKUP.get(image.getRGB(x, y));
					tiles[x + y * width] = Tile.tiles[id];
					colors[x + y * width] = random.nextInt(Tile.tiles[id].getColors().length);
					chars[x + y * width] = random.nextInt(Tile.tiles[id].getCharacters().length);
				}
			}
			
			return new Map(width, height, tiles, colors, chars);
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
