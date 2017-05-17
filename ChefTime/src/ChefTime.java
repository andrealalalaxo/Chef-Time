
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;


import processing.core.PApplet;
import processing.core.PImage;
import processing.event.MouseEvent;
import processing.event.KeyEvent;


public class ChefTime extends PApplet {
	

	private Ingredient egg, flour, chocolate, foodColor, sugar, milk;
	private Oven oven;
	
	private Ingredient currentDrag;
	private int dragOffsetX, dragOffsetY;
	
	public ChefTime() {
		super();
		

		flour = new Ingredient (50, 50, 50, 50);
		egg = new Ingredient (50, 50, 50, 50);
		chocolate = new Ingredient (50, 50, 50, 50);
		milk = new Ingredient (50, 50, 50, 50);
		sugar= new Ingredient (50, 50, 50, 50);
		foodColor= new Ingredient (50, 50, 50, 50);
		oven = new Oven(10);
		//oven.startBaking(); //begin baking timer for 10 seconds
		
		
		
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
  
		egg.img = eggimg;;
		flour.img = flourimg;
		chocolate.img = chocolateimg;
		foodColor.img = milkimg;
		sugar.img = sugarimg;
		milk.img = milkimg;
		foodColor.img = foodColorimg;
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
		System.out.println("TEST");
		
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
			dragOffsetX = myX - currentDrag.x;
			dragOffsetY = myY - currentDrag.y;
		}
		
		redraw();
		
	}
	
	public void mouseReleased(MouseEvent arg0) {
		currentDrag = null;
		redraw();
	}
	
	public void mouseDragged(MouseEvent arg0) {
		if (currentDrag != null) {
			currentDrag.x = arg0.getX()-dragOffsetX;
			currentDrag.y = arg0.getY()-dragOffsetY;
			redraw();
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if (code == 'O') { //When O is pressed, the food is taken out of the oven so it stops baking.
			//System.out.println("Key O is pressed.");
			oven.stop();
		}
			
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
	

