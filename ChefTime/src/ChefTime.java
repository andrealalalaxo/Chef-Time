
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import processing.event.MouseEvent;


public class ChefTime extends PApplet {


	private Ingredient egg, flour, chocolate, foodColor, sugar, milk;
	private Oven oven;
	private PImage bowlimg, ovenimg;




	private Ingredient currentDrag;
	private int dragOffsetX, dragOffsetY;

	private int rectX, rectY;      // Position of square button
	private int circleX, circleY;  // Position of circle button
	private int counterX, counterY;  // Position of text counter String
	private int scoreX, scoreY;  // Position of text score String
	private int rectSize = 13;     // Diameter of rect
	private int circleSize = 15;   // Diameter of circle
	private int rectColor, circleColor, baseColor;
	private int rectHighlight, circleHighlight;
	private int currentColor;
	private boolean rectOver = false;
	private boolean circleOver = false;
	

	
	
	public ChefTime() {
		super();

	
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
		bowlimg =  loadImage("bowl.png");
		ovenimg = loadImage("oven.png");
		
		
		image(ovenimg, 200, 10, 200, 200);
		
		egg = new Ingredient (eggimg, 50, 50, 50, 50);
		flour = new Ingredient(flourimg, 50, 100, 50, 50);
		chocolate = new Ingredient (chocolateimg, 50, 150, 50, 50);
		foodColor = new Ingredient(foodColorimg, 50, 200, 50, 50);
		milk = new Ingredient (milkimg, 50, 250, 50, 50);
		sugar = new Ingredient(sugarimg, 50, 300, 50, 50);



		
		// size(640, 360);
		  rectColor = color(30,255,30);
		  rectHighlight = color(80,200,80);
		  circleColor = color(255,30,30);
		  circleHighlight = color(200,80,80);
		  baseColor = color(102);
		  currentColor = baseColor;
		  circleX = 377;
		  circleY = 170;
		  rectX = 370;
		  rectY = 140;
		  counterX = 365;
		  counterY = 100;
		  scoreX = 210;
		  scoreY = 175;
		  ellipseMode(CENTER);
		
	}

	// The statements in draw() are executed 60 times a second until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() { 
		background(255);   // Clear the screen with a white background
	  
		
		
		  update(mouseX, mouseY);
		  //background(currentColor);
		  
		  if (rectOver) {
		    fill(rectHighlight);
		  } else {
		    fill(rectColor);
		  }
		  stroke(255);
		  rect(rectX, rectY, rectSize, rectSize);
		  
		  if (circleOver) {
		    fill(circleHighlight);
		  } else {
		    fill(circleColor);
		  }
		  stroke(0);
		  ellipse(circleX, circleY, circleSize, circleSize);
		  
		  textSize(20);
//		  text("word"); 
//		  fill(0, 102, 153);
//		  text("word", 10, 60);
		  fill(0, 220, 180);
		  text(this.oven.counterString, counterX, counterY); 
		  textSize(16);
		  fill(250, 0, 0);
		  
		  text(this.oven.scoreString, scoreX, scoreY);

		//call draw on each ingredient
		egg.draw(this);
		flour.draw(this);
		sugar.draw(this);
		foodColor.draw(this);
		milk.draw(this);
		chocolate.draw(this);
		image(bowlimg, 250, 300, 250, 100);
	}



	
	
	void update(int x, int y) {
		  if ( overCircle(circleX, circleY, circleSize) ) {
		    circleOver = true;
		    rectOver = false;
		  } else if ( overRect(rectX, rectY, rectSize, rectSize) ) {
		    rectOver = true;
		    circleOver = false;
		  } else {
		    circleOver = rectOver = false;
		  }
		}


		boolean overRect(int x, int y, int width, int height)  {
		  if (mouseX >= x && mouseX <= x+width && 
		      mouseY >= y && mouseY <= y+height) {
		    return true;
		  } else {
		    return false;
		  }
		}

		boolean overCircle(int x, int y, int diameter) {
		  float disX = x - mouseX;
		  float disY = y - mouseY;
		  if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
		    return true;
		  } else {
		    return false;
		  }
		}
	
	
	public void mousePressed(MouseEvent e) {
		
		if (rectOver) {
			draw();
		    oven.startBaking();
		}
		if (circleOver) {
			oven.stopBaking();
			oven.getBakingScore();
			draw();
		}
			  
		
		
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


