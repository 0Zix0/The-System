package com.system.world.entity;

import java.awt.Color;

import com.system.world.Player;
import com.system.world.TileColor;

public class DoorEntity extends Entity {

	private static final TileColor OPEN_COLOR = new TileColor(new Color(29, 40, 0));
	private static final TileColor CLOSED_COLOR = new TileColor(new Color(173, 242, 0));
	
	private boolean open = false;
	
	public boolean onMoveInto(Player player) {
		if(!open) {
			open = true;
			return false;
		}
		return true;
	}
	
	public void onMoveOff(Player player) {
		open = false;
	}

	public char getCharacter() {
		return open ? '|' : 'X';
	}

	public TileColor getColor() {
		return open ? OPEN_COLOR : CLOSED_COLOR;
	}

	public String getName() {
		return "Door";
	}
}
