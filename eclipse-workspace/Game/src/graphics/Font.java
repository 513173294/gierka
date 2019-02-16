package graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import util.Vector2f;

public class Font {
	
	private BufferedImage FONTSHEET = null;
	private BufferedImage[][] spriteArray;
	private final int TILE_SIZE = 32;
	public int w;
	public int h;
	private int wLetter;
	private int hLetter;
	
	public Font(String file) {
		w = TILE_SIZE;
		h = TILE_SIZE;
		System.out.println("Loading: " + file + "...");
		FONTSHEET = loadFont(file);
		loadSpriteArray();
	}
	
	public Font(String file, int w, int h) {
		this.w = w;
		this.h = h;
		System.out.println("Loading: " + file + "...");
		FONTSHEET = loadFont(file);
		
		wLetter = FONTSHEET.getWidth() / w;
		hLetter = FONTSHEET.getHeight() / h; 
		loadSpriteArray();
	}
	
	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	
	public void setWidth(int width) {
		w = width;
		wLetter = FONTSHEET.getWidth() / w;
	}
	
	public void setHeight(int height) {
		h = height;
		wLetter = FONTSHEET.getHeight() / h;
	}
	
	public void loadSprite() {
		
	}
	
	public void loadSpriteArray() {
		spriteArray = new BufferedImage[wLetter][hLetter]; 
		
		for(int x = 0; x < wLetter; x++) {
			for(int y = 0; y < hLetter; y++) {
				spriteArray[x][y] = getLetter(x, y); 
			}
		}
	}
	
	public int getWidth() { return w; }
	public int getHeight() { return h; } 
	
	private BufferedImage loadFont(String file) {
		BufferedImage font = null;
		try {
			font = ImageIO.read(new File("/home/nitropawel/eclipse-workspace/Game/res/" + file));
		} catch(Exception e) {
			System.out.println("ERROR: could not load file: " + file);
		}
		return font;
	}
	
	public BufferedImage getFontSheet() { return FONTSHEET; }
	public BufferedImage getLetter(int x, int y) {
		return FONTSHEET.getSubimage(x * w, y * h, w, h); // 64, 96, 16, 16
	}
	
	public BufferedImage getFont(char letter) {
		int value = letter - 65;
		System.out.println(value);
		int x = value % wLetter; //wLetter = 5
		int y = value / wLetter; //hLetter = 4
		//System.out.println("x: " +  (value % wLetter) + ", y: " + (value / hLetter));
		
		// litera Y (89) 
		// x = 24 % 5 tj 4
		// y = 24 / 4 tj 6
		return getLetter(x, y);
		}
	
	
	 

}