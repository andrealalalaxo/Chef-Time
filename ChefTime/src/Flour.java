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

public class Flour extends Ingredient implements ActionListener {
	 private final int ACTION_TIMEOUT = 500;
	
	 private int action;
	 private JComponent surface; // for repainting when we make changes to his image
	  private Timer actionTimer;
	  private Image flour;
	  private Image batter;
	  private Image[] sprite;
	  
	  // The bottom left corner of Eggy.
	  private int x, y;
	  private int width, height;
	 
	 // Load the sounds from the disk. 
	 private final Sound flourSound = new Sound("sploosh.wav");
	 

	 /**
		 * Creates an Ingredient object (flour) with a set image, coordinate, and size
		 * @param img The image of the flour
		 * @param x x-coordinate of the top left corner of the image
		 * @param y y-coordinate of the top left corner of the image
		 * @param width the width of the image
		 * @param height the heigth of the image
		 * 
		 */
	 
	 public Flour(PImage img, int x, int y, int width, int height) {
			super(img, x, y, width, height);
			super.setName("flour");
		
		 }
	 
	 /**
	   * flour on left column with ingredients, doing nothing.
	   */
	  public void still() {
		  action = 0;
	  }
	  
	  /**
	   * flour is dropped into bowl.
	   */
	  public void released() {
		  if (action == 0) {
			  action = 1;
			  flourSound.play();
			  actionTimer.restart();
		  }
	  }

	  /**
	   * @post surface is repainted
	   * 
	   */
	  public void actionPerformed(ActionEvent arg0) {
		  still();
		  surface.repaint();

	  }
}