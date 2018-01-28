package com.system.world.ships;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.system.image.ImageCache;
import com.system.world.map.ShipMap;
import com.system.xpreader.XPFile;

public class ShipLoader {

	public static ShipDefinition loadShip(String ship) {
		try {
			XPFile sprite = ImageCache.get("ships/" + ship);
			BufferedImage collisionImage = ImageIO.read(new File("res/sprites/xp/ships/" + ship + "_collision.png"));
			int width = sprite.layer(0).width;
			int height = sprite.layer(0).height;
			if(collisionImage.getWidth() != width || collisionImage.getHeight() != height) {
				System.out.println("Collision image is not the same size as ship sprite: " + ship);
				return null;
			}
			boolean[] collisionMap = new boolean[width * height];
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					collisionMap[x + y * width] = collisionImage.getRGB(x, y) == 0xff000000 ? true : false;
				}
			}
			return new ShipDefinition(sprite, collisionMap);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
