package states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import graphics.Sprite;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2f;
import entity.Player;

public class PlayState extends GameState {

	private Player player;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		player = new Player(new Sprite("sprite/sprite.png", 64, 64), new Vector2f(50, 350), 128);
	}

	public void update() {
		player.update();
	}

	public void input(MouseHandler mouse, KeyHandler key) {
		player.input(mouse, key);
	}

	public void render(Graphics2D g) {
		player.render(g);
	}

	@Override
	public void printInstructions(Graphics2D g) {
		// TODO Auto-generated method stub

	}

}
