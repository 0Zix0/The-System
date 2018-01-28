package com.system.world.map;

import com.system.TextRenderer;
import com.system.world.entity.Entity;

public abstract class Map {

	public abstract boolean isTileSolid(int x, int y);
	public abstract String getNameAt(int x, int y);
	public abstract void addEntity(int x, int y, Entity entity);
	public abstract Entity entityAt(int x, int y);
	public abstract void render(TextRenderer renderer);
	
	public void setCurrentCeiling(int x, int y) {
		
	}
}
