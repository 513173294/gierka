package pawel.game;

import javax.swing.JPanel;

import states.GameStateManager;
import util.KeyHandler;
import util.MouseHandler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{
	
	public static int width;
	public static int height;
	
	private Thread thread;
	private BufferedImage image;
	private Graphics2D g;
	
	private MouseHandler mouse;
	private KeyHandler key;
	
	private boolean running = false;
	
	private GameStateManager gsm;
	
	
	private int x = 0;
	
	public GamePanel(int width, int height) {
		this.width = width;
		this.height = height;
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus();
	}
	
	public void init() {
		running = true;
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) image.getGraphics();
		
		mouse = new MouseHandler(this);
		key = new KeyHandler(this); 
		
		gsm = new GameStateManager();
	}
	
	public void run() {
		init();
		
		final double GAME_HERTZ = 60.0;
		final double TBU = 1000000000 / GAME_HERTZ; /** time_before_update*/
		
		final int MUBR = 5; /** must_update_before_render */
		
		final double TARGET_FPS = 60;
		final double TTBR = 100000000 / TARGET_FPS; /** Total_time_before_render */
		
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime;
		
		int frameCount = 0;
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
		int oldFrameCount = 0;
		
		
		
		while(running) {
			
			double now = System.nanoTime();
			int updateCount = 0;
			
			while(((now - lastUpdateTime) > TBU) && (updateCount < MUBR)) {
				update();
				input(mouse, key);
				lastUpdateTime += TBU;
				updateCount++;
			}
			
			if(now - lastUpdateTime > TBU) {
				lastUpdateTime = now = TBU;
			}
			input(mouse, key);
			render();
			draw();
			lastRenderTime = now;
			frameCount++;
			
			int thisSecond = (int) (lastUpdateTime / 1000000000);
			if(thisSecond > lastSecondTime) {
				if(frameCount != oldFrameCount) {
					//System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
					oldFrameCount = frameCount;
				}
				frameCount = 0;
				lastSecondTime = thisSecond;
			}
			
			while(((now - lastRenderTime) < TTBR) && ((now - lastUpdateTime) < TBU)) {
				Thread.yield();
				
				try {
					Thread.sleep(1);
				} catch(Exception e) {
					System.out.println("ERROR: yielding thread");
				}
				
				now = System.nanoTime();
			}
		}
	}
	 
	public void addNotify() {
		super.addNotify();
		
		if(thread == null) {
			thread = new Thread(this, "GameThread");
			thread.start();
		}
	}
	
	public void update() {
		gsm.update();
	}
	
	public void input(MouseHandler mouse, KeyHandler key) {
		gsm.input(mouse, key);
	}
	
	public void render() {
		if (g != null) {
			g.setColor(new Color(120, 200, 244));
			g.fillRect(0, 0, width, height);
			gsm.render(g);
		}
		
	}
	
	public void draw() {
		Graphics g2 = (Graphics2D) this.getGraphics();
		g2.drawImage(image, 0, 0, width, height, null);
		g2.dispose();
		
	}

}
