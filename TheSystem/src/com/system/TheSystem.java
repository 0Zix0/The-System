package com.system;

import com.system.state.MainMenuState;
import com.system.state.StateManager;
import com.zengine.AbstractGame;
import com.zengine.GameSettings;
import com.zengine.Shader;
import com.zengine.graphics.RenderLayer;

public class TheSystem extends AbstractGame {

	private TextRenderer textRenderer;
	
	public void initialize() {
		addLayer(new RenderLayer(textRenderer = new TextRenderer(2.0f), new Shader("res/shaders/text.vert", "res/shaders/text.frag")));
		StateManager.pushState(new MainMenuState(this));
	}

	public void update() {
		StateManager.currentState().update();
	}

	//see http://www.gridsagegames.com/rexpaint/resources.html
	// for more fonts
	public void render() {
		StateManager.currentState().render(textRenderer);
		//textRenderer.text(getCurrentFPS() + " FPS", 0, 4, new Vector3f(1.0f), new Vector3f());
		//textRenderer.text(getCurrentUPS() + " UPS", 0, 5, new Vector3f(1.0f), new Vector3f());
		//for(int i = 0; i < 16 * 16; i++) {
		//	textRenderer.text(new String(new char[] {(char)i}), i % 16, i / 16, new Vector3f(0, 1, 0), new Vector3f(1, 0, 0));
		//}
	}

	public static void main(String[] args) {
		TheSystem game = new TheSystem();
		GameSettings settings = new GameSettings();
		settings.title = "The System";
		int tileWidth = 8;
		int tileHeight = 8;
		settings.width = (1280 / tileWidth) * tileWidth;
		settings.height = (720 / tileHeight) * tileHeight;
		game.start(settings);
	}
}
