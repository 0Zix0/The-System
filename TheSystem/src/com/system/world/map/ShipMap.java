package com.system.world.map;

import com.system.TextRenderer;
import com.system.world.entity.Entity;
import com.system.world.ships.Ship;
import com.system.world.ships.ShipDefinition;

public class ShipMap extends Map {

	private Ship ship;
	private int xPos = 40 - 3;
	private int yPos = 22 - 7;

	public ShipMap() {
		this.ship = new Ship(ShipDefinition.ships[0]);
	}

	public boolean isTileSolid(int x, int y) {
		return ship.getShipType().isTileSolid(x - xPos, y - yPos);
	}

	public String getNameAt(int x, int y) {
		Entity e = entityAt(x, y);
		if(e != null) {
			return e.getName();
		}
		return "Ship";
	}

	public void addEntity(int x, int y, Entity entity) {
		
	}

	public Entity entityAt(int x, int y) {
		return ship.entityAt(x - xPos, y - yPos);
	}

	public void render(TextRenderer renderer) {
		ship.render(renderer, xPos, yPos);
	}
}
