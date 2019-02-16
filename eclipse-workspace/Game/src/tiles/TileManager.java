package tiles;

import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import graphics.Sprite;

public class TileManager {
	
	public static ArrayList<TileMap> tm;

	public TileManager() {
		tm = new ArrayList<TileMap>();
		
	}
	
	public TileManager(String path) {
		tm = new ArrayList<TileMap>();
		addTileMap(path, 100, 100);
		
	}

	private void addTileMap(String path, int blockWidth, int blockHeight) {
		String imagePath;
		
		int width = 0;
		int height = 0;
		int tileWidth;
		int tileHeight;
		int tileCount;
		int tileColumns;
		int layers = 0;
		Sprite sprite;
		
		String[] data = new String[10];
		
		try {
			
			System.out.println("TRAJ");
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
		/*	System.out.println("PATH	");
			File file1 = new File(getClass().getClassLoader().getResource(path).toURI());
			System.out.println(file1.toString());*/
			
			Document doc = builder.parse(new File(getClass().getClassLoader().getResource(path).toURI()));
			System.out.println("GOWNO1");	
			
			doc.getDocumentElement().normalize();
	
			NodeList list = doc.getElementsByTagName("tileset");
			Node node = list.item(0);
			Element eElement = (Element) node;
			
			imagePath = eElement.getAttribute("name");
			tileWidth = Integer.parseInt(eElement.getAttribute("tilewidth"));
			tileHeight = Integer.parseInt(eElement.getAttribute("tileheight"));
			tileCount = Integer.parseInt(eElement.getAttribute("tilecount"));
			tileColumns = Integer.parseInt(eElement.getAttribute("columns"));
			
			sprite = new Sprite("tiles/" + imagePath + ".png", tileWidth, tileHeight);
			
			list = doc.getElementsByTagName("layer");
			layers = list.getLength();
			
			for(int i = 0; i < layers; i++) {
				node = list.item(i);
				eElement = (Element) node;
				if(i <= 0) {
					width = Integer.parseInt(eElement.getAttribute("width"));
					height = Integer.parseInt(eElement.getAttribute("height"));
					
				}
				
				data[i] = eElement.getElementsByTagName("data").item(0).getTextContent();
				System.out.println("-----------------------\n" + data[i]);
			}
			
			
		} catch(Exception e) {
			System.out.println("ERROR - Filemanager: cannot read tile map");
		}
		 
	}
	
	public void render(Graphics2D g) {
		
	}
	
}
