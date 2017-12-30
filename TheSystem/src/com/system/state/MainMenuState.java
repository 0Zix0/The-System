package com.system.state;

import java.util.Random;

import org.joml.Vector3f;

import com.system.TextRenderer;
import com.system.image.ImageCache;
import com.system.ui.unmanaged.MainMenu;
import com.system.xpreader.XPFile;

public class MainMenuState extends State {
	
	private char[] detail1 = new char[80];
	private char[] detail2 = new char[80];
	private char[] detail3 = new char[80];
	
	private MainMenu menu;
	
	private XPFile logo;
	
	public MainMenuState() {
		this.detail1 = updateDetail(detail1);
		this.detail2 = updateDetail(detail2);
		this.detail3 = updateDetail(detail3);
		this.menu = new MainMenu(22).addItem("Start Jouney", () -> {
			StateManager.pushState(new PlayerCreateState());
		}).addItem("Options", () -> {
			StateManager.pushState(new OptionsState());
		}).addItem("Leave", () -> {
			System.out.println("leave");
		});
		this.logo = ImageCache.get("logo");
	}
	
	public void render(TextRenderer renderer) {
		String detailStr1 = new String(detail1);
		String detailStr2 = new String(detail2);
		String detailStr3 = new String(detail3);
		renderer.text(detailStr1, 0, 0, new Vector3f(0.86f), new Vector3f(0.0f));
		renderer.text(detailStr2, 0, 1, new Vector3f(0.62f), new Vector3f(0.0f));
		renderer.text(detailStr3, 0, 2, new Vector3f(0.38f), new Vector3f(0.0f));
		renderer.text(detailStr1, 0, 3, new Vector3f(0.14f), new Vector3f(0.0f));
		
		renderer.text(detailStr1, 0, 44, new Vector3f(0.86f), new Vector3f(0.0f));
		renderer.text(detailStr2, 0, 43, new Vector3f(0.62f), new Vector3f(0.0f));
		renderer.text(detailStr3, 0, 42, new Vector3f(0.38f), new Vector3f(0.0f));
		renderer.text(detailStr1, 0, 41, new Vector3f(0.14f), new Vector3f(0.0f));
		
		logo.render(renderer, 20, 6);
		menu.render(renderer);

	}
	
	Random random = new Random();
	public char randomChar() {
		char c = (char) (random.nextInt(255) + 1);
		if(c == 32) { // 32 is an empty cell, so get rid of it.
			c = randomChar();
		}
		return c;
	}
	
	public char[] updateDetail(char[] original) {
		for(int i = 0; i < original.length; i++) {
			if(random.nextFloat() > 0.8f) {
				original[i] = randomChar();
			}
		}
		return original;
	}

	private int count = 0;
	public void update() {
		if(count % 5 == 0) {
			detail1 = updateDetail(detail1);
			detail2 = updateDetail(detail2);
			detail3 = updateDetail(detail3);
		}
		count++;
		menu.update();
	}
}
