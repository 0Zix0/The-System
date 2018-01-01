package com.system.state.game;

import org.joml.Vector3f;

import com.system.TextRenderer;
import com.system.TheSystem;
import com.system.state.State;
import com.system.world.Player;
import com.system.world.entity.Entity;
import com.system.world.map.Map;
import com.system.world.map.MapLoader;

public class GameState extends State {

	private Player player;
	private Map map;
	private TheSystem system;
	
	public GameState(Player player, TheSystem system) {
		this.map = MapLoader.load("res/maps/test.png");
		this.map.addEntity(24, 13, Entity.create(0));
		this.map.addEntity(21, 16, Entity.create(0));
		this.map.addEntity(27, 16, Entity.create(0));
		this.player = player;
		this.player.setMap(map);
		this.system = system;
	}
	
	public void render(TextRenderer renderer) {
		renderer.text(system.getCurrentFPS() + " FPS", 0, 0, new Vector3f(1.0f), new Vector3f());
		renderer.text(system.getCurrentUPS() + " UPS", 0, 1, new Vector3f(1.0f), new Vector3f());
		renderer.text(player.getX() + " " + player.getY(), 0, 2, new Vector3f(1.0f), new Vector3f());
		player.render(renderer);
		map.render(renderer);
	}

	public void update() {
		player.update();
	}
}
