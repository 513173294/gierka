package states;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game.GamePanel;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2f;

public class GameStateManager {

	private ArrayList<GameState> states;

	public static Vector2f map;

	public static final int PLAY = 0;
	public static final int MENU = 1;
	public static final int GAMEOVER = 2;

	public GameStateManager() {
		map = new Vector2f(GamePanel.width, GamePanel.height);
		Vector2f.setWorldVar(map.x, map.y);
		states = new ArrayList<GameState>();
		states.add(new PlayState(this));
	}

	public void pop(int state) {
		states.remove(state);
	}

	public void add(int state) {
		if (state == PLAY) {
			states.add(new PlayState(this));
		}
		if (state == MENU) {
			states.add(new MenuState(this));
		}
		if (state == GAMEOVER) {
			states.add(new GameOverState(this));
		}
	}

	public void addAndPop(int state) {
		states.remove(0);
		add(state);
	}

	public void update() {
		for (int i = 0; i < states.size(); i++) {
			states.get(i).update();
		}
	}

	public void input(MouseHandler mouse, KeyHandler key) {
		for (int i = 0; i < states.size(); i++) {
			states.get(i).input(mouse, key);
		}
	}

	public void render(Graphics2D g) {
		for (int i = 0; i < states.size(); i++) {
			states.get(i).render(g);
		}
	}

}