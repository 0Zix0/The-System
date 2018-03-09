package com.system.state.game;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import com.system.TextRenderer;
import com.system.TheSystem;
import com.system.state.State;
import com.system.ui.unmanaged.Dialogue;
import com.system.world.Player;
import com.system.world.entity.Entity;
import com.system.world.map.WorldMap;
import com.zengine.Input;
import com.system.world.map.Map;
import com.system.world.map.MapLoader;
import com.system.world.map.ShipMap;

public class GameState extends State {

	private Player player;
	private Map map;
	private TheSystem system;
	
	private Dialogue dialogue;
	
	public GameState(Player player, TheSystem system) {
		this.map = MapLoader.loadWorld("res/maps/test.png", "res/maps/ceilings.png");
		//this.map = new ShipMap();
		this.map.addEntity(39, 16, Entity.create(1));
		this.map.addEntity(40, 16, Entity.create(1));
		this.player = player;
		this.player.setMap(map);
		this.system = system;
		this.dialogue = new Dialogue("Hello this is a long string of text designed to test the dialog system.");
	}
	
	public void render(TextRenderer renderer) {
		renderer.text(system.getCurrentFPS() + " FPS", 0, 0, new Vector3f(1.0f), new Vector3f());
		renderer.text(system.getCurrentUPS() + " UPS", 0, 1, new Vector3f(1.0f), new Vector3f());
		renderer.text(player.getX() + " " + player.getY(), 0, 2, new Vector3f(1.0f), new Vector3f());
		
		//if(Input.isKeyDown(GLFW.GLFW_KEY_U)) {
			dialogue.render(renderer);
		//}
		player.render(renderer);
		map.render(renderer);
	}

	public void update() {
		player.update();
	}
}
