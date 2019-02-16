package entity;

import java.awt.Graphics2D;

import graphics.Sprite;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2f;

public class Player extends Entity {

	public Player(Sprite sprite, Vector2f startPosition, int size) {
		super(sprite, startPosition, size);
	}

	public void move() {
		if(up) {
			dy -= acc;
			if(dy < -speed) {
				dy = -speed;
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
			if(dx < -speed) {
				dx = -speed;
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
			if(dy > speed) {
				dy = speed;
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
			if(dx > speed) {
				dx = speed;
			}
		} else {
			if(dx > 0) {
				dx -= deacc;
				if(dx < 0) {
					dx = 0;
				}
			}
		}
		if(attack) {
			System.out.println("SHOOTING");
		} 
		
	}

	public void update() {
		super.update();
		move();
		pos.x += dx;
		pos.y += dy;
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(ani.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
	}

	public void input(MouseHandler mouse, KeyHandler key) {

		if (mouse.getButton() == 1) {
			System.out.println("Player: " + pos.x + ", " + pos.y);
		}
		if (key.up.down) {
			up = true;
		} else {
			up = false;
		}
		if (key.left.down) {
			left = true;
		} else {
			left = false;
		}
		if (key.down.down) {
			down = true;
		} else {
			down = false;
		}
		if (key.right.down) {
			right = true;
		} else {
			right = false;
		}
		if (key.attack.down) {
			attack = true;
		} else {
			attack = false;
		}
	}

}
