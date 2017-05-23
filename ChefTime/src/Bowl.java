import processing.core.PApplet;
import processing.core.PImage;
/**
 * This class represents a bowl, which is where Ingredients should be
 * dragged into.
 * @author ssun681
 *
 */
public class Bowl {
	private PImage img;
	private int x, xOven;
	private int y, yOven;
	private int width;
	private int height;
	private boolean inOven;
	 
	private PImage batterImg;
	/**
	 * Creates a Bowl object with a set image, coordinate, and size
	 * @param img The image of the bowl
	 * @param x The x coordinate of the bowl
	 * @param y The y coordinate of the bowl
	 * @param width The width of the bowl
	 * @param height The height of the bowl
	 */
	public Bowl(PImage img, int x, int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.img = img;
		inOven = false;

	}
	/**
	 * Replaces the original empty Bowl image with an image of a 
	 * bowl with batter.
	 * @param img The new image of the Bowl object
	 */
	public void fillBowl(PImage img) {
		this.img = img; 
	}
	/**
	 * Replaces the original Bowl image with an image of a finished
	 * food.
	 * @param img The new image of the Bowl object
	 */
	public void cookFood(PImage img) {
		this.img = img;
	}
	/**
	 * Sets the coordinates of the Bowl to the coordinates of the Bowl
	 * in the oven.
	 * @post The x and y coordinates of the Bowl changes, as well as
	 * its width and height.
	 */
	public void moveToOven() {
		xOven = x;
		yOven = y;
		x = 245;
		y = 95;
		width = width/2;
		height = height/2;
		inOven = true;
	}
	/**
	 *Sets the coordinates of the Bowl to the original coordinates of the bowl
	 *when it's outside the oven.
	 *@post The x and y coordinates of the Bowl changes, as well as its 
	 *width and height.
	 */
	public void moveOutOven() {
		x = xOven;
		y = yOven;
		width = width*2;
		height = height*2;
		inOven = false;
	}
	/**
	 * Checks to see whether the Bowl is in the oven.
	 * @return true if the Bowl is in the oven.
	 */
	public boolean isInOven() {
		return inOven;
	}
	/**
	 * Draws an instance of a Bowl object using the image, coordinates, and width and height fields.
	 * @param drawer The marker used to draw the Bowl.
	 */
	public void draw(PApplet drawer) {
		drawer.image(img, x, y, width, height);

	}
	
}
