package com.system.world;

import java.awt.Color;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.system.ColorPalette;
import com.system.TextRenderer;
import com.system.util.Utilities;
import com.system.world.entity.Entity;
import com.system.world.map.Map;
import com.system.world.map.WorldMap;
import com.zengine.Input;

public class Player {

	private String name;
	private Gender gender;
	private ColorPalette palette;
	
	private int xPos, yPos;
	
	private Map map = null;
	
	private boolean looking = false;
	private int cursorX = 0;
	private int cursorY = 0;
	
	private int credits = 1000;
	
	// The entity which is underneath the player.
	private Entity under = null;

	public Player(String name, Gender gender, ColorPalette palette) {
		this.name = name;
		this.gender = gender;
		this.palette = palette;
		this.xPos = 39;
		this.yPos = 20;
	}
	
	public void render(TextRenderer renderer) {
		if(looking) {
			renderer.text('X', cursorX, cursorY, new Vector3f(1.0f, 0.0f, 0.0f), new Vector3f(0.0f));
			renderer.text(map.getNameAt(cursorX, cursorY), 0, 44, new Vector3f(1.0f), new Vector3f(0.0f));
		}
		renderer.text((char)1, xPos, yPos, palette.getPrimary(), new Vector3f(0.0f));
		
		String money = credits + "";

		renderer.text((char)224, 80 - money.length() - 1, 44, Utilities.convertColor(new Color(72, 242, 0)), new Vector3f());
		renderer.text(money, 80 - money.length(), 44, Utilities.convertColor(new Color(39, 130, 0)), new Vector3f());
	}
	
	public void update() {
		if(Input.isKeyDownR(GLFW.GLFW_KEY_L)) {
			looking = !looking;
			cursorX = xPos;
			cursorY = yPos;
		}
		if(!looking) {
			int xChange = 0;
			int yChange = 0;
			boolean moved = false; //760
			if(Input.isKeyDownR(GLFW.GLFW_KEY_DOWN)) {
				yChange++;
				moved = true;
			} else if(Input.isKeyDownR(GLFW.GLFW_KEY_UP)) {
				yChange--;
				moved = true;
			} else if(Input.isKeyDownR(GLFW.GLFW_KEY_LEFT)) {
				xChange--;
				moved = true;
			} else if(Input.isKeyDownR(GLFW.GLFW_KEY_RIGHT)) {
				xChange++;
				moved = true;
			}
			if(moved && map != null) {
				boolean solid = map.isTileSolid(xPos + xChange, yPos + yChange);
				Entity ent = map.entityAt(xPos + xChange, yPos + yChange);
				if(ent != null) {
					boolean moveable = ent.onMoveInto(this);
					if(moveable) {
						xPos += xChange;
						yPos += yChange;
						map.setCurrentCeiling(xPos, yPos);
						under = ent;
					}
				} else if(!solid) {
					xPos += xChange;
					yPos += yChange;
					map.setCurrentCeiling(xPos, yPos);
					if(under != null) {
						under.onMoveOff(this);
						under = null;
					}
				} 
			}
		} else {
			if(Input.isKeyDownR(GLFW.GLFW_KEY_DOWN)) {
				cursorY++;
			} else if(Input.isKeyDownR(GLFW.GLFW_KEY_UP)) {
				cursorY--;
			} else if(Input.isKeyDownR(GLFW.GLFW_KEY_LEFT)) {
				cursorX--;
			} else if(Input.isKeyDownR(GLFW.GLFW_KEY_RIGHT)) {
				cursorX++;
			}
		}
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public String getName() {
		return name;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public ColorPalette getPalette() {
		return palette;
	}
	
	public void addCredits(int amt) {
		credits += amt;
	}
}
