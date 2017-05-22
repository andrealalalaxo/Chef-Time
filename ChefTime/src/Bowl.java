import processing.core.PApplet;
import processing.core.PImage;

public class Bowl {
	private PImage img;
	private int x, xOven;
	private int y, yOven;
	private int width;
	private int height;
	private boolean inOven;
	 
	private PImage batterImg;
	public Bowl(PImage img, int x, int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.img = img;
		inOven = false;

	}
	
	public void fillBowl(PImage img) {
		this.img = img; 
	}
	public void moveToOven() {
		xOven = x;
		yOven = y;
		x = 245;
		y = 95;
		width = width/2;
		height = height/2;
		inOven = true;
	}
	
	public void moveOutOven() {
		x = xOven;
		y = yOven;
		width = width*2;
		height = height*2;
		inOven = false;
	}
	
	public boolean isInOven() {
		return inOven;
	}
	public void draw(PApplet drawer) {
		drawer.image(img, x, y, width, height);

	}
	
}
