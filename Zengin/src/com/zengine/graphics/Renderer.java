package com.zengine.graphics;

public abstract class Renderer {

	/** Called before render() */
	public void start() {}
	/** Called after render() */
	public void end() {}
	
	public abstract void render();
}
