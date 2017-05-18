import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
/**
 * This class represents an Oven object with a Swing Timer to determine how long the food should be baked, as well as 
 * how long the food had actually been baked. The Oven automatically stops once the food has exceeded the burn time.
 * @author Selina
 *
 */
public class Oven implements ActionListener {
	private Timer timer;
	private int setTime; //time the pastry needs to be baked
	private int burnedTime; //max amount of time the pastry can be cooked before burned
	private long startTime;
	private long endTime;
	private long remainingTime;
	private long displayTime;
	private int bakeTime; //time the pastry was actually baked
	private boolean isOn; //timer is running if true
	private int bakingScore; //score based on how well the pastry was baked.
	private JLabel display; //displays time left
	private JButton ovenOn; //turns oven on and off
	public String counterString = "00";
	public String scoreString = "Score: ";
	
	
	
	
	/*
	 * 	private int rectX, rectY;      // Position of square button
	private int circleX, circleY;  // Position of circle button

	private int rectSize = 13;     // Diameter of rect
	private int circleSize = 15;   // Diameter of circle
	private int rectColor, circleColor, baseColor;
	private int rectHighlight, circleHighlight;
	private int currentColor;
	private boolean rectOver = false;
	private boolean circleOver = false;


	void setup() {
	  size(640, 360);
	  rectColor = color(0);
	  rectHighlight = color(51);
	  circleColor = color(255);
	  circleHighlight = color(204);
	  baseColor = color(102);
	  currentColor = baseColor;
	  circleX = width/2+circleSize/2+10;
	  circleY = height/2;
	  rectX = width/2-rectSize-10;
	  rectY = height/2-rectSize/2;
	  ellipseMode(CENTER);
	}

	void draw() {
	  update(mouseX, mouseY);
	  background(currentColor);
	  
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

	void mousePressed() {
	  if (circleOver) {
	    currentColor = circleColor;
	  }
	  if (rectOver) {
	    currentColor = rectColor;
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
	
	*/
	
	
	/**
	 * Creates an Oven object with a specified time the pastry should be baked.
	 * @param time The set time that the pastry should be baked.
	 */
	public Oven(int time) {
		super();
		bakeTime = 0;
		displayTime = 0;
		burnedTime = 5;
		bakingScore = 0;
		
		setTime = time;
		remainingTime = setTime;
		
		timer = new Timer(100, this);
		isOn = false;
		
		//JPanel right = new JPanel(new GridLayout(1,3));
		//ovenOn = new JButton("On");
		//ovenOn.addActionListener(this);
		//right.add(ovenOn);
	}

	/**
	 * Changes the set time that the pastry should be baked.
	 * @param i The new time that the pastry should be baked.
	 */
	public void setTime(int i) {
		setTime = i;
	}
	
	/**
	 * Begins the Oven timer and starts the baking process of the pastry.
	 */
	public void startBaking() {
		timer.start();
		isOn = true;
		startTime = System.currentTimeMillis();
		endTime = startTime + remainingTime * 1000;
	}
	
	/**
	 * Begins the Oven timer and starts the baking process of the pastry.
	 */
	public void stopBaking() {
		timer.stop();
		isOn = false;
		endTime = remainingTime;
	}
	
	/**
	 * Stops the Oven timer, ending the baking process of the pastry.
	 */
	public void stop() {
		if (isOn == true) {
			System.out.println("Oven timer stopped");
			timer.stop();
			isOn = false;
		}
	}
	/**
	 * Gets the set time that the pastry should be baked at.
	 * @return The set bake time for the pastry.
	 */
	public double getSetTime() {
		return setTime;
	}
	
	public boolean isRunning() {
		return isOn;
	}

	public int getBakingScore() {
		
		int timeOff = (int)(Math.abs(remainingTime));
		if (timeOff == 0) {
			bakingScore = 100;
		} else
		if (timeOff >3) {
			bakingScore = 60;
		} else if (timeOff > 2) {
			bakingScore = 70;
		} else if (timeOff > 1) {
			bakingScore = 80;
		} else if (timeOff > 0) {
			bakingScore = 90;
		} else{
			bakingScore = 0;
		}
		scoreString = "Score: " + bakingScore;
		return bakingScore;
	}
	/*public int getDifference() { 
		return Math.abs(setTime - bakeTime);
	}
	*/
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		long currentTime;
		
		if (!isOn) {
			System.out.println("Time is not running");
			return;
		}
		
		currentTime = System.currentTimeMillis();
		remainingTime = (endTime - currentTime)/1000;
		
		if (remainingTime < 0){
			// update Display
			// time up
			// Cooked
			// Sound
			// Burned
			System.out.println("Time is up!  Please remove the food." + remainingTime);
			if (-remainingTime >= burnedTime) {
				System.out.println("Burned!");
				this.stop();
				isOn = false;
				bakingScore = 0;
			}
		}
		else {
		   // update Display Time
			if (remainingTime != displayTime && remainingTime >= 0) {
				System.out.println("Time remaining: " + remainingTime);
				counterString = String.format("%02d",remainingTime);
				displayTime = remainingTime;
			}
			

				
		}
		
		
	}
	
	
	
}
