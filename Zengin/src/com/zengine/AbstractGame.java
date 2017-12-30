package com.zengine;

import java.util.ArrayList;

import com.zengine.graphics.Display;
import com.zengine.graphics.RenderLayer;

public abstract class AbstractGame {
	
	private ArrayList<RenderLayer> layers = new ArrayList<>();
	
	private int currentFPS = 0;
	private int currentUPS = 0;
	
	public final void start(GameSettings settings) {
		Display.init(settings.width, settings.height, settings.title);
		initialize();
		loop();
	}
	
	private final void updateGame() {
		update();
	}
	
	private final void renderGame() {
		for(RenderLayer rl : layers) {
			rl.start();
		}
		
		render();
		
		for(RenderLayer rl : layers) {
			rl.render();
			rl.end();
		}		
	}
	
	private final void loop() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		while(!Display.shouldClose()) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			Display.clear();
			while(delta >= 1) {
				updateGame();
				updates++;
				delta--;
			}
			renderGame();
			frames++;
			Display.update();
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames + " UPS: " + updates);
				currentFPS = frames;
				currentUPS = updates;
				updates = 0;
				frames = 0;
			}
		}
	}
	
	public final void addLayer(RenderLayer layer) {
		layers.add(layer);
	}
	
	public int getCurrentFPS() {
		return currentFPS;
	}
	
	public int getCurrentUPS() {
		return currentUPS;
	}
	
	public abstract void initialize();
	public abstract void update();
	public abstract void render();
}
