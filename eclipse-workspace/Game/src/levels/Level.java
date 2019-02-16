package levels;

import java.util.Random;

import states.PauseState;
import states.PlayState;
import entity.Trooper;
import graphics.Sprite;
import util.Vector2f;

public class Level {
	
	private int totalEnemies;
	private int level;
	private String title;
	public int getLevel() { return this.level; }
	public void setLevel(int level) { this.level = level;}
	public String getTitle() { return this.title; }
	public void setTitle(String title) { this.title = title; }
	public int getTotalEnemies() { return this.totalEnemies; }
	
	private Trooper[] enemies;
	
	public Trooper getEnemy(int index) {
		return this.enemies[index-1];
	}
	
	public Trooper[] generateEnemies(Trooper[] enemies) {
		this.enemies = enemies;
		float difficulty = 2 + (level / 5);
		
		for (int i = 0; i < enemies.length; i++) {
			Random randX = new Random();
			Random randY = new Random();
			float posX = (float) (1280 + (randX.nextFloat() * 1500));
			float posY = 50 + (randY.nextFloat() * 550);
			enemies[i] = new Trooper(new Sprite("sprite/sprite.png", 64, 64), new Vector2f(posX, posY), 128);
			enemies[i].setMaxSpeed(difficulty); // here add stats which grow with level
			System.out.println("Trooper[] generateEnemies()" + i);
			System.out.println(enemies[i].getPosX());
			}
		return enemies;
}
	public Trooper[] getEnemies() { return this.enemies; }
	
	public Level(int level, int totalEnemies, String title) {
		setLevel(level);
		this.totalEnemies = totalEnemies;
		this.title = title;
	//	this.enemies = generateEnemies();
		
	}
	
	

}
