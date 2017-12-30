package com.system.state;

import com.system.TextRenderer;
import com.system.state.game.GameState;
import com.system.ui.ButtonWidget;
import com.system.ui.ColorWidget;
import com.system.ui.SelectGenderWidget;
import com.system.ui.TextWidget;
import com.system.ui.UIScreen;
import com.system.world.Player;

public class PlayerCreateState extends State {

	private UIScreen ui;
	
	private TextWidget nameField;
	private ColorWidget colorField;
	private SelectGenderWidget genderField;
	
	public PlayerCreateState() {
		ui = new UIScreen(35, 15);
		ui.addWidget(nameField = new TextWidget("NAME", 10));
		ui.addWidget(colorField = new ColorWidget("COLOR"));
		ui.addWidget(genderField = new SelectGenderWidget("SEX"));
		ui.addWidget(new ButtonWidget("START", () -> {
			StateManager.pushState(new GameState(new Player(nameField.getValue(), genderField.getSelected(), colorField.getSelected())));
		}));
	}
	
	public void render(TextRenderer renderer) {
		ui.render(renderer);
	}

	public void update() {
		ui.update();
	}
}
