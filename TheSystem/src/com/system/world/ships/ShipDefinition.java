package com.system.world.ships;

import com.system.TextRenderer;
import com.system.xpreader.XPFile;

public class ShipDefinition {

	public static ShipDefinition[] ships = new ShipDefinition[128];
	static {
		registerShip(0, "cargo1");
	}
	
	public static void registerShip(int id, String ship) {
		ships[id] = ShipLoader.loadShip(ship);
		ships[id].id = id;
	}
	
	private int id;
	private XPFile shipImage;
	private boolean[] collisionMap;
	private int width, height;
	
	public ShipDefinition(XPFile image, boolean[] collisionMap) {
		this.shipImage = image;
		this.collisionMap = collisionMap;
		this.width = shipImage.layer(0).width;
		this.height = shipImage.layer(0).height;
	}
	
	public void render(TextRenderer renderer, int x, int y) {
		shipImage.render(renderer, x, y);
	}
	
	public boolean[] getCollisionMap() {
		return collisionMap;
	}
	
	public boolean isTileSolid(int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height) {
			return true;
		}
		return collisionMap[x + y * width];
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
