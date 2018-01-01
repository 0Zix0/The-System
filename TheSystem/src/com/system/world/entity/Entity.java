package com.system.world.entity;

import java.util.HashMap;

import org.joml.Vector2i;
import org.joml.Vector3f;

import com.system.world.TileColor;

public abstract class Entity {

	private static final HashMap<Integer, Class<? extends Entity>> entities = new HashMap<>();
	private static int currentid = 0;
	
	static {
		registerEntity(0, DoorEntity.class);
	}
	
	public static void registerEntity(int id, Class<? extends Entity> entity) {
		entities.put(id, entity);
	}
	
	public static Entity create(int id) {
		if(entities.containsKey(id)) {
			try {
				Entity ent = entities.get(id).newInstance();
				ent.id = currentid++;
				return ent;
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			throw new RuntimeException("No entity with ID '" + id + "'.");
		}
	}

	private int id = -1;
	
	protected Vector2i position;
	
	public Entity() {
		this.position = new Vector2i();
	}
	
	public Entity(Vector2i position) {
		this.position = position;
	}
	
	public Entity(int x, int y) {
		this.position = new Vector2i(x, y);
	}
	
	public abstract char getCharacter();
	public abstract TileColor getColor();
	public abstract String getName();
	
	public boolean onMoveInto() { return true; }
	public void onMoveOff() {}
	
	public Vector2i getPosition() {
		return position;
	}
	
	public void setPosition(Vector2i position) {
		this.position = position;
	}
	
	public void setPosition(int x, int y) {
		this.position.x = x;
		this.position.x = y;
	}
	
	public int getX() {
		return position.x;
	}
	
	public int getY() {
		return position.y;
	}
}
