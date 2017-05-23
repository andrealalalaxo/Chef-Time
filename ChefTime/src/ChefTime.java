
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
/**
 * This class represents the kitchen, which contains the oven, ingredients, and the bowl.
 * @author 
 *
 */
public class ChefTime extends PApplet {

	private Ingredient egg, flour, chocolate, foodColor, sugar, milk;
	private Oven oven;
	private Bowl bowl;
	private PImage bowlimg, ovenimg;

	private Ingredient currentDrag;
	private int dragOffsetX, dragOffsetY;

	private int rectX, rectY; // Position of oven's square button
	private int circleX, circleY; // Position of oven's circle button
	private int playX, playY;

	private int counterX, counterY; // Position of text counter String
	private int scoreX, scoreY; // Position of text score String
	private int rectSize = 13; // Diameter of rect
	private int circleSize = 15; // Diameter of circle
	private int rectColor, circleColor, baseColor;
	private int rectHighlight, circleHighlight;
	private int currentColor;
	private boolean baked; 
	private boolean rectOver = false;
	private boolean circleOver = false;
	private boolean playOver = false;
	private ArrayList<Integer> ingredients;
	private String[] recipe = {"egg","flour", "milk", "food coloring", "sugar", "chocolate"};
	private ArrayList<String> recipeIngredients;
	private ArrayList<String> addedIngredients;
	private int totalScore;
	
	private int screen; // 0 for title screen, 1 for game screen, 2 for
						// instructions

	private final Sound drop = new Sound("sploosh.wav");
	private final Sound eggCrack = new Sound("eggcrack.wav");
	//private  Sound ticking = new Sound("tick.wav");
	private final Sound ding = new Sound("ding.wav");
	private final Sound bigWin = new Sound("greatjob.wav");
	private final Sound okayWin = new Sound("okayjob.wav");
	private final Sound lose = new Sound("badjob.wav");
	/**
	 * Creates a new ChefTime object, which represents a kitchen with an oven and ingredients.
	 */
	public ChefTime() {
		super();
		screen = 0;
		baked = false;
		oven = new Oven(10, 3);
		// oven.startBaking(); //begin baking timer for 10 seconds
		totalScore = 0;
		
		currentDrag = null;
		addedIngredients = new ArrayList<>();
		recipeIngredients = new ArrayList<>();
		// methods for catching mouse events
		runSketch(); // as soon as object is made, begin calling draw loop

	}

	// The statements in the setup() function
	// execute once when the program begins
	/**
	 * Loads all the images and initializes the ingredients, as well as the coordinates for the buttons on the oven.
	 * @post The ingredients are initialized with an image, and the settings of the buttons on the oven are also initialized.
	 */
	public void setup() {
		PImage flourimg = loadImage("flour.png");
		PImage eggimg = loadImage("eggs.png");
		PImage chocolateimg = loadImage("chocolate.png");
		PImage milkimg = loadImage("milk.png");
		PImage sugarimg = loadImage("sugar.png");
		PImage foodColorimg = loadImage("food_coloring.png");

		bowlimg = loadImage("bowl.png");
		ovenimg = loadImage("oven.png");

		image(ovenimg, 200, 10, 200, 200);

		egg = new Egg(eggimg, 50, 50, 50, 50);
		flour = new Flour(flourimg, 50, 100, 50, 50);
		chocolate = new Chocolate(chocolateimg, 50, 150, 50, 50);
		foodColor = new FoodColor(foodColorimg, 50, 200, 50, 50);
		milk = new Milk(milkimg, 50, 250, 50, 50);
		sugar = new Sugar(sugarimg, 50, 300, 50, 50);
		bowl = new Bowl(bowlimg, 250, 300, 250, 100);
		// size(640, 360);
		rectColor = color(30, 255, 30);
		rectHighlight = color(80, 200, 80);
		circleColor = color(255, 30, 30);
		circleHighlight = color(200, 80, 80);
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
		playX = 200;
		playY = 250;
		ellipseMode(CENTER);

	}
	/**
	 * Finds how many ingredients were placed incorrectly compared to the recipe.
	 * @return The number of ingredients placed in the wrong order by the user.
	 */
	public int countNumberOfWrongIngredients() {
		int count = 0;
		for (int i=0; i<addedIngredients.size();i++) {
			if (addedIngredients.size() != 0 && i < recipeIngredients.size() && !addedIngredients.get(i).equals(recipeIngredients.get(i)) ) {
				count++;
			}
			if (addedIngredients.size() == 0) {
				count = recipeIngredients.size();
			}
		}
		if (addedIngredients.size() != recipeIngredients.size()) {
			count += Math.abs(recipeIngredients.size() - addedIngredients.size());
		}
		//System.out.println("Wrong number of Ingredients: " + count);
		return count;
	}
	/**
	 * Calculates the total score using the baking score and the accuracy of the ingredients added.
	 * @return Total game score
	 */
	public int calculateScore() {
		int score = oven.getBakingScore() + countNumberOfWrongIngredients()*(-10) + recipeIngredients.size()*(10);
		totalScore = score;
		return score;
	}

	// The statements in draw() are executed 60 times a second until the
	// program is stopped. Each statement is executed in
	// sequence and after the last line is read, the first
	// line is executed again.
	/**
	 * Draws a new instance of ChefTime, drawing different screens such as the title screen, instruction screen,
	 * game screen, and score screen.
	 */
	public void draw() {
		background(255); // Clear the screen with a white background
		if (screen == 4) {
			PImage resultScreen = loadImage("resultscreen.jpg");
			image(resultScreen, -15, 0, 670, 490);
			this.textSize(50);
			this.fill(15);
			this.text("Score", 225, 50);
			this.textSize(20);
			this.text("Ingredient mixing score: ", 100, 100);
			this.text(countNumberOfWrongIngredients()*(-10) + recipeIngredients.size()*(10)+"", 400, 100);
			this.text("Baking score: ", 100, 125);
			this.text(oven.getBakingScore()+"", 400, 125);
			this.text("Total score: ", 100, 150);
			this.text(totalScore+"", 400, 150);
			
			if (totalScore >= 130) {
				
				this.text("Great job, you are an expert at mindlessly following recipes.", 10, 225);
				this.text("Don't you feel proud!", 90, 250);
			
				
			}
			else if (totalScore >= 100) {
				
				this.text("Well, at least you tried. Good show! You're still an", 20, 225);
				this.text("embarrassment to your family and the baking community.", 15, 250);
			
			} else {
				
				this.text("Congratulations, you are now qualified as a contestant on", 15, 225);
				this.text("Worst Cooks in America. Next time, try opening your eyes", 15, 250);
				this.text(" when you play, it might help. Gordon Ramsay is ashamed", 15, 275);
				this.text("to breathe the same air as you!", 110, 300);
				
			}
		}
		if (screen == 3) {
			PImage playScreen = loadImage("playbackground.jpg");
			image(playScreen, -15, 0, 670, 490);
			bowl.draw(this);
			// call draw on each ingredient
			if (egg.isHidden() == false) {
				egg.draw(this);
			}
			if (flour.isHidden() == false) {
			flour.draw(this);
			}
			if (sugar.isHidden() == false) {
			sugar.draw(this);
			}
			if (foodColor.isHidden() == false) {
			foodColor.draw(this);
			}
			if (milk.isHidden() == false) {
			milk.draw(this);
			}
			if (chocolate.isHidden() == false) {
			chocolate.draw(this);
			}
			//image(bowlimg, 250, 300, 250, 100);
			image(ovenimg, 200, 10, 200, 200);
			update(mouseX, mouseY);
			// background(currentColor);

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
			fill(0, 220, 180);
			text(this.oven.counterString, counterX, counterY);
			textSize(16);
			fill(250, 0, 0);
			
			text(this.oven.scoreString, scoreX, scoreY);
			if (baked == true && !oven.isRunning()) {
				this.textSize(15);
				this.fill(15);
				this.text("Press O to remove your masterpiece", 170, 230);
				if (!bowl.isInOven()) {
					this.textSize(15);
					this.fill(15);
					this.text("Congrats, press R to see how you did!", 165, 250);
				}
			}
			
		}
		if (screen == 0) {
			PImage titleScreen = loadImage("mainmenu.jpg");
			image(titleScreen, 0, -10, 640, 480);
			textSize(100);
			fill(0, 102, 153);
			text("Chef Time", 80, 200);
			
			textSize(30);
			text("Press P to play", 80, 300);
			text("Press I for instructions", 80, 330);

		}
		if (screen == 2) {
			PImage instructionScreen = loadImage("kitchen.jpg");
			image(instructionScreen, 0, -10, 640, 480);
			textSize(30);
			
			text("Instructions", 250, 25);
			fill(0);
			textSize(20);
			text("Memorize the recipe, which disappears after a few seconds.", 50, 75);
			text("Drag all the ingredients into the bowl in the correct order.", 50, 100);
			text("Press O to move the bowl into the oven. To start baking,", 50, 125);
			text("press the green button on the oven. Stop the oven after ", 50, 150);
			text("time is up using the red button. Then press O to remove ", 50, 175);
			text("the food. Accurately follow the recipe so your score isn't", 50, 200);
			text("trash. ", 50, 225);
			text("Press I to go back", 250, 275);
		}
		
		if (screen == 1) {
			PImage instructionScreen = loadImage("floralpaper.png");
			image(instructionScreen, 0, -10, 640, 480);
			PImage sweets = loadImage("sweets.gif");
			image(sweets, 450, 300, 120, 90);
			textSize(30);
			text("Recipe", 250, 50);
		
			for (int i = 0; i < ingredients.size(); i++) {
				text("Add: " + recipeIngredients.get(i), 50, 127+50*i);	
			}
			new java.util.Timer().schedule( 
			        new java.util.TimerTask() {
			            @Override
			            public void run() {
			                screen = 3;
			               
			            }
			        }, 
			       2000 
			);
			
		}
	}

	void update(int x, int y) {
		if (overCircle(circleX, circleY, circleSize)) {
			circleOver = true;
			rectOver = false;
		} else if (overRect(rectX, rectY, rectSize, rectSize)) {
			rectOver = true;
			circleOver = false;
		} else {
			circleOver = rectOver = false;
		}
	}
/**
 * Checks to see if the cursor is over the square button of the oven.
 * @param x The x coordinate of the oven's square button
 * @param y The y coordinate of the oven's square button
 * @param width The width of the oven's square button
 * @param height The height of the oven's square button
 * @return true if the cursor is over the square button
 */
	boolean overRect(int x, int y, int width, int height) {
		if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
			return true;
		} else {
			return false;
		}
	}
/**
 * Checks to see if the cursor is over the circle button of the oven.
 * @param x The x coordinate of the oven's circle button
 * @param y The y coordinate of the oven's circle button
 * @param diameter The diameter of the oven's circle button
 * @return true if the cursor is over the circle button
 */
	boolean overCircle(int x, int y, int diameter) {
		float disX = x - mouseX;
		float disY = y - mouseY;
		if (sqrt(sq(disX) + sq(disY)) < diameter / 2) {
			return true;
		} else {
			return false;
		}
	}
	

	public void mousePressed(MouseEvent e) {

		if (rectOver) {
			draw();
			if (bowl.isInOven() && baked == false) {
				oven.startBaking();
				//ticking.play();
				baked = true;
				
			}
		}
		if (circleOver) {
			oven.stopBaking();
			ding.play();
			if (baked == true) {
				if (oven.burnedFood()) {
					this.textSize(35);
					this.fill(0);
					this.text("It's burning! Take it out!", 230, 300);
				}
				this.textSize(35);
				this.fill(0);
				this.text("Press O to remove your masterpiece", 230, 230);
				//countNumberOfWrongIngredients();
				oven.getBakingScore();
			}
			
			draw();
		}


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
			dragOffsetX = myX - currentDrag.getX(); // change to sets
			dragOffsetY = myY - currentDrag.getY();
		}

		redraw();

	}

	public void mouseReleased(MouseEvent e) {
		if (screen == 3){
		if (currentDrag != null && currentDrag.isInBowl(e.getX(), e.getY()) ) {

			bowl.fillBowl(loadImage("batter.png"));
			currentDrag.hideImage();
			
			addedIngredients.add(currentDrag.getName());
			if (currentDrag.getName().equals("egg")) {
				eggCrack.play();
			} else {
				drop.play();
			}
		}
		redraw();
		}
	}

	public void mouseDragged(MouseEvent arg0) {

		if (currentDrag != null) {
			currentDrag.setX(arg0.getX() - dragOffsetX);
			currentDrag.setY(arg0.getY() - dragOffsetY);
			redraw();
		}
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == 'I') {
			if (screen == 0) {
				screen = 2;
				
			}
			else if (screen == 2) {
				screen = 0;
				
			}
		}
		if (code == 'P') {
			if (screen == 1) {
				screen = 3;
			} else if (screen == 0){
				screen = 1;
				int count = 0;
				int n = (int)(Math.random()*3+4);
				ingredients = new ArrayList<>();
				while (count < n ) {
					int a = (int)(Math.random()*6);
					if (!ingredients.contains(a)) {
						ingredients.add(a);
						count++;
					}
				}
				
				for (int i = 0; i < ingredients.size(); i++) {
					recipeIngredients.add(recipe[ingredients.get(i)]);	
				}
			}
	
		}
		if (code == 'O') {
			if (!bowl.isInOven()) {
				bowl.moveToOven();
				
			} else {
				if (!oven.isRunning()) {
					bowl.moveOutOven();
					if (baked == true) {
						calculateScore();
						if (oven.getBakingScore() >= 80) {
							bowl.cookFood(loadImage("cake.gif"));
						}
					}
				}
			}
		}
		if (code == 'R') {
			if (baked && !bowl.isInOven()) {
				screen = 4;
			}
		}
		draw();
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
