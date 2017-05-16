import java.awt.Image;

import processing.core.PApplet;
import processing.core.PImage;

public class Ingredient {
	PImage img;
	int x;
	int y;
	 int width;
	 int height;
	 String fileName;
	
	public Ingredient(){
		
	}
	public Ingredient( int x, int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	

	public boolean isPointInside(double x, double y){
		if(x > this.x || x < this.x + width || y > this.y || y < this.y + height){
			return true;
		} else {
			return false;
		}
	}

	public void released() {
		
	}
	

	
	public void draw(PApplet drawer) {
		drawer.loadImage(fileName);
		
	}
}
