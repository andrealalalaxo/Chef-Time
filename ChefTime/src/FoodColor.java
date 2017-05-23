import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;

import processing.core.PImage;

public class FoodColor extends Ingredient implements ActionListener {
	 private final int ACTION_TIMEOUT = 500;
	
	 private int action;
	 private JComponent surface; // for repainting when we make changes to his image
	  private Timer actionTimer;

	  private Image foodColor;
	  private Image batter;
	  private Image[] sprite;
	  
	  // The bottom left corner of Eggy.
	  private int x, y;
	  private int width, height;
	 
	 // Load the sounds from the disk. 
	 private final Sound foodColorSound = new Sound("sploosh.wav");
	 

	 /**
	  * makes an ingredient object food color
	  * @param img image of food coloring
	  * @param x x-coordinate of top left corner of image
	  * @param y y-coordinate of top left corner of image
	  * @param width width of image
	  * @param height height of image
	  */
	 public FoodColor(PImage img, int x, int y, int width, int height) {
			super(img, x, y, width, height);
			super.setName("food color");
		
		 }
	 
	 /**
	   * food coloring on left column with ingredients, doing nothing.
	   */
	  public void still() {
		  action = 0;
	  }
	  
	  /**
	   * food coloring is dropped into bowl.
	   */
	  public void released() {
		  if (action == 0) {
			  action = 1;
			  foodColorSound.play();
			  actionTimer.restart();
		  }
	  }

	 /**
	  * repaints screen
	  */
	  public void actionPerformed(ActionEvent arg0) {
		  still();
		  surface.repaint();

	  }
}