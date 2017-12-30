package com.system.state;

import com.system.TextRenderer;

public abstract class State {

	public abstract void render(TextRenderer renderer);
	public abstract void update();
}
