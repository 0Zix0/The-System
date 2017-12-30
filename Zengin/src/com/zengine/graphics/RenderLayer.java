package com.zengine.graphics;

import com.zengine.Shader;

public class RenderLayer {

	private Renderer renderer;
	private Shader shader;
	
	public RenderLayer(Renderer renderer, Shader shader) {
		this.renderer = renderer;
		this.shader = shader;
	}
	
	public RenderLayer(Renderer renderer) {
		this(renderer, null);
	}
	
	public void start() {
		if(shader != null) shader.bind();
		renderer.start();
	}
	
	public void end() {
		renderer.end();
		if(shader != null) shader.unbind();
	}
	
	public void render() {
		renderer.render();
	}
}
