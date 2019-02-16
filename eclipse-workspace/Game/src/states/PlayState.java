package states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import Rules.Instructions;
import entity.Bullet;
import entity.Player;
import entity.Trooper;
import graphics.Font;
import graphics.Sprite;
import levels.Level;
import tiles.TileManager;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2f;

public class PlayState extends GameState {
	
	private Font font;
	private Player player;
	private Bullet bullet;
	public Trooper trooper1, trooper2;
	private TileManager tm;
	
	public static int currLevel = 0;
	private boolean isFinished = false;
	public boolean getFinished() { return this.isFinished; }
	public void setFinished(boolean isFinished) { this.isFinished = isFinished;} 
	
	public void setLevel(int level) { this.currLevel = level; }
	public int getLevel() { return this.currLevel; }
	
	public Trooper[] enemies;
	
	public PlayState(GameStateManager gsm) {
		
		super(gsm);
		
		tm = new TileManager("/home/nitropawel/eclipse-workspace/Game/res/tiles/map1.xml");
		
		font = new Font("font/basicFont.png", 16, 16);
		player = new Player(new Sprite("sprite/sprite.png", 64, 64), new Vector2f(20, 300), 128);
		Level level1 =  new Level(currLevel, 20, "LEVEL 1");
		
		System.out.println(level1.getTotalEnemies() + "TESTTESTTESTETST");
		enemies = new Trooper[level1.getTotalEnemies()];
		enemies = level1.generateEnemies(enemies).clone();
		bullet = new Bullet(new Sprite("sprite/bulletMap.png", 32, 32), new Vector2f(50, 50), 128);
		
		
		
	}
	
	public void update() {
		
	//	if (trooper1.getPosX() < -50) {
	//		trooper1 = new Trooper(new Sprite("sprite/sprite.png", 64, 64), new Vector2f(1250, 500), 128);
	//	}
		player.update();
		bullet.update();
		for(int i = 0; i < this.enemies.length; i++) {
			System.out.println("troopers updating: " + i + " position: " + enemies[i].getPosX() + " and " + enemies[i].getPosY());
			enemies[i].update();
		}
		//trooper1.update();
		//trooper2.update();
	}
	
	public void input(MouseHandler mouse, KeyHandler key) {
		player.input(mouse, key);
	}
	
	public void render(Graphics2D g) {
		//Sprite.drawArray(g, font, Instructions.gameRule1, new Vector2f(100, 100), 32, 32, 16, 0);
		player.render(g); 
		//trooper1.render(g);
		//trooper2.render(g);
		printInstructions(g);
		bullet.render(g);
		for(int i = 0; i < this.enemies.length; i++) {
			System.out.println("troopers rendering " + enemies[i].getPosX() + " and " + enemies[i].getPosY());
			enemies[i].render(g);
		}
	}

	@Override
	public void printInstructions(Graphics2D g) {
		String[] ruleArray = Instructions.getRuleArray();
		int yOffset = 100;
		for(int i = 0; i < ruleArray.length; i++) {
			Sprite.drawArray(g, font, ruleArray[i], new Vector2f(100, yOffset), 32, 32, 16, 0);
			yOffset += 30;
		
	}
		}
	
}

