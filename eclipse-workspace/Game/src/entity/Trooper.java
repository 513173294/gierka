package entity;

import java.awt.Graphics2D;

import graphics.Sprite;
import util.Vector2f;
import java.util.Random;

public class Trooper extends Entity {
	
	private float posX = super.pos.x;
	private float posY = super.pos.y;
	
	public Trooper(Sprite sprite, Vector2f orgin, int size) {
		super(sprite, orgin, size);
		this.randomizeSkills();
		
	}
	
	
	public Trooper(int posY) {
		super(new Sprite("sprite/sprite.png", 64, 64), new Vector2f(1250, posY), 128);
		this.randomizeSkills();
		
	}
	
	public boolean walk() {
		return left = true;
	}
	
	public void randomizeSkills() {
		Random random = new Random();
		super.maxSpeed = random.nextFloat() * 3;
	}
	


@Override
	public void render(Graphics2D g) {
		g.drawImage(ani.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
	
}

	public void move() {
	if(up) {
		dy -= acc;
		if(dy < -maxSpeed) {
			dy = -maxSpeed;
		}
	} else {
		if(dy < 0) {
			dy += deacc;
			if(dy > 0) {
				dy = 0;
			}
		}
	}
	if(left) {
		dx -= acc;
		if(dx < -maxSpeed) {
			dx = -maxSpeed;
		}
	} else {
		if(dx < 0) {
			dx += deacc;
			if(dx > 0) {
				dx = 0;
			}
		}
	}
	if(down) {
		dy += acc;
		if(dy > maxSpeed) {
			dy = maxSpeed;
		}
	} else {
		if(dy > 0) {
			dy -= deacc;
			if(dy < 0) {
				dy = 0;
			}
		}
	}
	if(right) {
		dx += acc;
		if(dx > maxSpeed) {
			dx = maxSpeed;
		}
	} else {
		if(dx > 0) {
			dx -= deacc;
			if(dx < 0) {
				dx = 0;
			}
		}
	}
	
}

	public void update() {
	super.update();
		walk();
		move();
		pos.x += dx;
		pos.y += dy;
		
}

	public float getPosX() {
		return super.pos.x;
	}

	public float getPosY() {
		return super.pos.y;
	}


}
