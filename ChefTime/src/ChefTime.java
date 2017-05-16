
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;

import processing.core.PApplet;
import processing.core.PImage;


public class ChefTime extends PApplet {
	

	
	public ChefTime() {
		
		runSketch(); //as soon as object is made, begin calling draw loop

	}
	// The statements in the setup() function 
		// execute once when the program begins
	public void setup() {
		  
	}
	
	// The statements in draw() are executed 60 times a second until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() { 
	  background(255);   // Clear the screen with a white background
	  /*
	  float xRatio = width/400f; //f casts to float
	  float yRatio = height/400f;
	  scale(xRatio, yRatio); 
	  */
	
	  PImage chocolate, eggs, flour, food_coloring, milk, bowl;
		chocolate = loadImage("chocolate.png");
		image(chocolate, 50, 50, 50, 50);
		eggs = loadImage("eggs.png");
		image(eggs, 50, 100, 50, 50);
		flour = loadImage("flour.png");
		image(flour, 50,150, 50, 50);
		food_coloring = loadImage("food_coloring.png");
		image(food_coloring, 50, 200, 50, 50);
		milk = loadImage("milk.png");
		image(milk, 50, 250, 50, 50);
		bowl = loadImage("bowl.png");
		image(bowl, 150, 200, 400, 200);

	}
	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx = e.getX();
		int my = e.getY();
		
		double ratioX = (double)width/800;
		double ratioY = (double)height/600;
		/*if (m.isPointInsideImage(mx/ratioX, my/ratioY) == true) {
			m.moveToLocation(500, 325);
		}
		*/
	}
	
}

   