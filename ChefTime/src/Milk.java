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

public class Milk extends Ingredient implements ActionListener {
	 private final int ACTION_TIMEOUT = 500;
	
	 private int action;
	 private JComponent surface; // for repainting when we make changes to his image
	  private Timer actionTimer;
	  
	  private Image milk;
	  private Image batter;
	  private Image[] sprite;
	  
	  // The bottom left corner of Eggy.
	  private int x, y;
	  private int width, height;
	 
	 // Load the sounds from the disk. 
	 private final EasySound2 milkSound = new EasySound2("sploosh.wav");
	 

	 
	 public Milk(int x, int y, JComponent surface) {
		  milk = new ImageIcon("milk.png").getImage();
		  batter = new ImageIcon("batter.png").getImage();
		 
		  sprite = new Image[2]; // Coordinates of each action within the sprite sheet image
		  sprite[0] = milk;
		  sprite[1] = batter;
		  
		  
		  this.x = x;
		  this.y = y;
		  //width =  sprite[0].width;   // Default width and height is the width and height of the first image 
		 // height =  sprite[0].height;
				
		 // width *= 4;  // We scale the size of Link up x4 for visibility
		 // height *= 4;
		  
		  action = 0;
		  
		  this.surface = surface;
		  actionTimer = new Timer(ACTION_TIMEOUT,this);
		  actionTimer.setRepeats(false);
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
			  milkSound.play();
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

	  @Override
	  public void actionPerformed(ActionEvent arg0) {
		  still();
		  surface.repaint();

	  }
}