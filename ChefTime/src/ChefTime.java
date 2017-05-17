
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;

import processing.core.PApplet;
import processing.core.PImage;
import processing.event.MouseEvent;


public class ChefTime extends PApplet {


	private Ingredient egg, flour, chocolate, foodColor, sugar, milk;
	private PImage bowlimg;




	private Ingredient currentDrag;
	private int dragOffsetX, dragOffsetY;

	public ChefTime() {
		super();
		currentDrag = null;

		//methods for catching mouse events
		runSketch(); //as soon as object is made, begin calling draw loop

	}
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		PImage flourimg =  loadImage("flour.png");
		PImage eggimg =  loadImage("eggs.png");
		PImage chocolateimg =  loadImage("chocolate.png");
		PImage milkimg =  loadImage("milk.png");
		PImage sugarimg =  loadImage("sugar.png");
		PImage foodColorimg =  loadImage("food_coloring.png");
		bowlimg =  loadImage("bowl.png");

		egg = new Ingredient (eggimg, 50, 50, 50, 50);
		flour = new Ingredient(flourimg, 50, 100, 50, 50);
		chocolate = new Ingredient (chocolateimg, 50, 150, 50, 50);
		foodColor = new Ingredient(foodColorimg, 50, 200, 50, 50);
		milk = new Ingredient (milkimg, 50, 250, 50, 50);
		sugar = new Ingredient(sugarimg, 50, 300, 50, 50);



	}

	// The statements in draw() are executed 60 times a second until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() { 
		background(255);   // Clear the screen with a white background

		//call draw on each ingredient
		egg.draw(this);
		flour.draw(this);
		sugar.draw(this);
		foodColor.draw(this);
		milk.draw(this);
		chocolate.draw(this);
		image(bowlimg, 250, 300, 250, 100);
	}



	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub


		int myX = e.getX();
		int myY = e.getY();
		if (egg.isPointInside(myX, myY)) {
			currentDrag = egg;

		} else if (flour.isPointInside(myX, myY)) {
			currentDrag = flour;
		} else if (chocolate.isPointInside(myX, myY)) {
			currentDrag = chocolate;
		} else if (sugar.isPointInside(myX, myY)) {
			currentDrag = sugar;
		} else if (milk.isPointInside(myX, myY)) {
			currentDrag = milk;
		} else if (foodColor.isPointInside(myX, myY)) {
			currentDrag = foodColor;
		}
		if (currentDrag != null) {
			dragOffsetX = myX - currentDrag.getX(); //change to sets
			dragOffsetY = myY - currentDrag.getY();
		}

		redraw();

	}

	public void mouseReleased(MouseEvent arg0) {
		currentDrag = null;
		redraw();
		egg.released();
	}

	public void mouseDragged(MouseEvent arg0) {

		if (currentDrag != null) {
			currentDrag.setX(arg0.getX()-dragOffsetX);
			currentDrag.setY(arg0.getY()-dragOffsetY);
			redraw();
		}
	}

}


