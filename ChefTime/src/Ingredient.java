import java.awt.Image;

import processing.core.PApplet;

public class Ingredient {
	private Image img;
	private int x, y, width, height;
	private String fileName;
	
	public Ingredient(){
		
	}
	public Ingredient(Image img, int x, int y, int width, int height) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	
	public void draw(PApplet drawer) {
		drawer.loadImage(fileName);
		
	}
}
