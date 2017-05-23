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
	private boolean burned;
	private int bakeTime; //time the pastry was actually baked
	private boolean isOn; //timer is running if true
	private int bakingScore; //score based on how well the pastry was baked.
	private JLabel display; //displays time left
	private JButton ovenOn; //turns oven on and off
	public String counterString = "00";
	public String scoreString = "Score: ";
	
	
	/**
	 * Creates an Oven object with a specified time the pastry should be baked.
	 * @param time The set time that the pastry should be baked.
	 */
	public Oven(int time, int burnTime) {
		super();
		bakeTime = 0;
		displayTime = 0;
		burnedTime = burnTime;
		bakingScore = 0;
		burned = false;
		setTime = time;
		remainingTime = setTime;
		
		timer = new Timer(100, this);
		isOn = false;
		
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
	/**
	 * Checks if the oven is on.
	 * @return true if the oven is on
	 */
	public boolean isRunning() {
		return isOn;
	}
/**
 * Calculates the score based on how well the pastry was baked.
 * @return the baking score
 */
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
		System.out.println(scoreString);
		return bakingScore;
	}
	/**
	 * Checks to see if the food has been burned.
	 * @return true if the food has been burned
	 */
	public boolean burnedFood() {
		return burned;
	}
	
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
			burned = true;
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
