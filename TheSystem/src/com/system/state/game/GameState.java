package com.system.state.game;

import com.system.TextRenderer;
import com.system.state.State;
import com.system.world.Map;
import com.system.world.Player;

public class GameState extends State {

	private Player player;
	private Map map;
	
	public GameState(Player player) {
		this.player = player;
		this.map = new Map();
	}
	
	public void render(TextRenderer renderer) {
		player.render(renderer);
		map.render(renderer);
	}

	public void update() {
		player.update();
	}
}
