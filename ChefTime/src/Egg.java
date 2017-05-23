
import java.awt.Image;
import processing.core.PImage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import javax.swing.JComponent;
import javax.swing.Timer;


public class Egg extends Ingredient {
	
	  private Timer actionTimer;
	  
	
	 /** Load the sounds from the disk. 
	  * 
	  * 
	  */
	 private final Sound eggSound = new Sound("eggcrack.wav");
	 

	/**
	 * draws an ingredient object (egg)
	 * @param img image of egg
	 * @param x x-coordinate of top left corner of image
	 * @param y y-coordinate of top left corner of image
	 * @param width width of image
	 * @param height height of image
	 */
	 public Egg(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.setName("egg");

	
	 }
	 
	
	 
	  /**
	   * Egg is dropped into bowl.
	   */
	  public void released() {
			  eggSound.play();
			  actionTimer.restart();
		  
	  }

	
		
	}

