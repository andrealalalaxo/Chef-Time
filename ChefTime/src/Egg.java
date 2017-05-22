
import java.awt.Image;
import processing.core.PImage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import javax.swing.JComponent;
import javax.swing.Timer;


public class Egg extends Ingredient {
	
	  private Timer actionTimer;
	  
	
	 // Load the sounds from the disk. 
	 private final EasySound2 eggSound = new EasySound2("eggcrack.wav");
	 

	
	 public Egg(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.setName("egg");

	
	 }
	 
	 
	 /*
	   * Egg on left column with ingredients, doing nothing.
	   */
	 
	  /*
	   * Egg is dropped into bowl.
	   */
	  public void released() {
			  eggSound.play();
			  actionTimer.restart();
		  
	  }

	
		
	}

