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

public class Chocolate extends Ingredient implements ActionListener {
	 private final int ACTION_TIMEOUT = 500;
	
	 private int action;
	 private JComponent surface; // for repainting when we make changes to his image
	  private Timer actionTimer;

	  private Image chocolate;
	  private Image batter;
	  private Image[] sprite;
	  
	  // The bottom left corner of Eggy.
	  private int x, y;
	  private int width, height;

	 
	 // Load the sounds from the disk. 
	 private final EasySound2 chocSound = new EasySound2("sploosh.wav");
	 

	 
	 public Chocolate(PImage img, int x, int y, int width, int height) {
			super(img, x, y, width, height);
			super.setName("chocolate");
		
		 }
	 /*
	   * Chocolate on left column with ingredients, doing nothing.
	   */
	  public void still() {
		  action = 0;
	  }
	  
	  /*
	   * Chocolate is dropped into bowl.
	   */
	  public void released() {
		  if (action == 0) {
			  action = 1;
			  chocSound.play();
			  actionTimer.restart();
		  }
	  }

	  /*
	   * Draw egg using the correct sprite.
	   */
	  public void draw(Graphics2D g2, ImageObserver io) {
		 // double xScale = (double)width / sprite[0].width;
		 // double yScale = (double)height / sprite[0].height;
		 // g2.drawImage(egg, x,(int)(y-yScale*sprite[action].height),(int)(x+xScale*sprite[action].width),y,sprite[action].x,sprite[action].y,sprite[action].x+sprite[action].width,sprite[action].y+sprite[action].height(),io);
	  }

	  public void actionPerformed(ActionEvent arg0){
		  still();
		  surface.repaint();
	  }
}