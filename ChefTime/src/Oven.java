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
	private long displayTime;
	private int bakeTime; //time the pastry was actually baked
	private boolean isRunning; //timer is running if true
	
	/**
	 * Creates an Oven object with a specified time the pastry should be baked.
	 * @param time The set time that the pastry should be baked.
	 */
	public Oven(int time) {
		super();
		bakeTime = 0;
		displayTime = 0;
		burnedTime = 5;
		startTime = System.currentTimeMillis();
		setTime = time;
		endTime = startTime + setTime * 1000;
		timer = new Timer(100, this);
		isRunning = false;
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
		isRunning = true;
	}
	/**
	 * Stops the Oven timer, ending the baking process of the pastry.
	 */
	public void stop() {
		if (isRunning == true) {
			System.out.println("Oven timer stopped");
			timer.stop();
			isRunning = false;
		}
	}
	/**
	 * Gets the set time that the pastry should be baked at.
	 * @return The set bake time for the pastry.
	 */
	public double getSetTime() {
		return setTime;
	}

	/*public int getDifference() { 
		return Math.abs(setTime - bakeTime);
	}
	*/
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		long currentTime = System.currentTimeMillis();
		long remainingTime = (endTime - currentTime)/1000;
		if (remainingTime <= 0){
			// update Display
			// time up
			// Cooked
			// Sound
			// Burned
			System.out.println("Time is up!  Please remove it!  " + remainingTime);
			if (-remainingTime >= burnedTime) {
				System.out.println("Burned! You are fired!");
				this.stop();
			}
		}
		else {
		   // update Display Time
			if (remainingTime != displayTime && remainingTime >= 0) {
				System.out.println("Time remaining: "+remainingTime);
				displayTime = remainingTime;
			}
			

				
		}
		
	}
	
	
	
}
