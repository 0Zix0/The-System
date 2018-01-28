package com.system.world.entity;

import java.awt.Color;

import com.system.world.Player;
import com.system.world.TileColor;

public class BankEntity extends Entity {

	public boolean onMoveInto(Player player) {
		player.addCredits(1000);
		return false;
	}
	
	public char getCharacter() {
		return '$';
	}

	public TileColor getColor() {
		return new TileColor(new Color(54, 181, 0));
	}

	public String getName() {
		return "Bank";
	}
}
