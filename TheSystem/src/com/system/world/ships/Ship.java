package com.system.world.ships;

import java.nio.ByteBuffer;

import com.system.TextRenderer;
import com.system.world.entity.Entity;

public class Ship {

	private ShipDefinition shipType;
	private Entity[] entities;
	
	public Ship(ShipDefinition type) {
		this.shipType = type;
		this.entities = new Entity[type.getWidth() * type.getHeight()];
		this.entities[2 + 1 * type.getWidth()] = Entity.create(2);
		this.entities[3 + 1 * type.getWidth()] = Entity.create(3);
	}

	public void render(TextRenderer renderer, int x, int y) {
		for(int i = 0; i < shipType.getHeight(); i++) {
			for(int j = 0; j < shipType.getWidth(); j++) {
				Entity e = entities[j + i * shipType.getWidth()];
				if(e != null) {
					renderer.text(e.getCharacter(), x + j, y + i, e.getColor().getForeground(), e.getColor().getBackground());
				}
			} 
		}
		shipType.render(renderer, x, y);
	}
	
	public ShipDefinition getShipType() {
		return shipType;
	}
	
	public Entity entityAt(int x, int y) {
		return entities[x + y * shipType.getWidth()];
	}
}
