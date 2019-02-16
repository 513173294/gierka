package entity;

import java.awt.Graphics2D;

import graphics.Sprite;
import util.Vector2f;

public class Bullet extends Entity{

	public Bullet(Sprite sprite, Vector2f orgin, int size) {
		super(sprite, orgin, size);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(ani.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
		
	}
	
	public void update() {
		super.update();
		fly();
		pos.x += dx;
		pos.y += dy;
	}
	
	public boolean fly() {
		
		return right = true;
	}
	

}
