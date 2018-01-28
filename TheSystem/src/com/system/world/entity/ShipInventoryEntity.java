package com.system.world.entity;

import java.awt.Color;

import com.system.world.TileColor;

public class ShipInventoryEntity extends Entity {

	private static final TileColor COLOR = new TileColor(new Color(146, 211, 139));
	
	public char getCharacter() {
		return 'i';
	}

	public TileColor getColor() {
		return COLOR;
	}

	public String getName() {
		return "Ship Inventory";
	}
}
