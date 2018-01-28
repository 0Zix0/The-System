package com.system.world.entity;

import java.awt.Color;

import com.system.world.Player;
import com.system.world.TileColor;

public class ShipPanelEntity extends Entity {

	private static final TileColor COLOR = new TileColor(new Color(162, 60, 216));
	
	public char getCharacter() {
		return 'c';
	}

	public TileColor getColor() {
		return COLOR;
	}

	public String getName() {
		return "Control Panel";
	}
	
	public boolean onMoveInto(Player player) {
		return false;
	}
}
