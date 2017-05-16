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

public class Egg extends Ingredient implements ActionListener {
	 private final int ACTION_TIMEOUT = 500;
	
	 private int action;
	 private JComponent surface; // for repainting when we make changes to his image
	  private Timer actionTimer;
	  
	  private Image sprites;
	  private Rectangle[] spriteRects;
	  
	  // The bottom left corner of Eggy.
	  private int x, y;
	  private int width, height;
	 
	 // Load the sounds from the disk. 
	 private final EasySound2 eggSound = new EasySound2("eggcrack.wav");
	 

	 
	 public Egg(int x, int y, JComponent surface) {
		  sprites = new ImageIcon("eggs.png").getImage();
		  
		  spriteRects = new Rectangle[3]; // Coordinates of each action within the sprite sheet image
		  spriteRects[0] = new Rectangle(0,77,41,46);
		  spriteRects[1] = new Rectangle(468,86,61,59);
		  spriteRects[2] = new Rectangle(413,90,53,55);
		  
		  this.x = x;
		  this.y = y;
		  width =  spriteRects[0].width;   // Default width and height is the width and height of the first image loaded from the sprite sheet
		  height =  spriteRects[0].height;
				
		  width *= 4;  // We scale the size of Link up x4 for visibility
		  height *= 4;
		  
		  action = 0;
		  
		  this.surface = surface;
		  actionTimer = new Timer(ACTION_TIMEOUT,this);
		  actionTimer.setRepeats(false);
	  }
	 
	 /*
	   * Egg on left column with ingredients, doing nothing.
	   */
	  public void still() {
		  action = 0;
	  }
	  
	  /*
	   * Egg is dropped into bowl.
	   */
	  public void released() {
		  if (action == 0) {
			  action = 1;
			  eggSound.play();
			  actionTimer.restart();
		  }
	  }

	  /*
	   * Draw link using the correct sprite.
	   */
	  public void draw(Graphics2D g2, ImageObserver io) {
		  double xScale = (double)width / spriteRects[0].width;
		  double yScale = (double)height / spriteRects[0].height;
		  g2.drawImage(sprites, x,(int)(y-yScale*spriteRects[action].height),(int)(x+xScale*spriteRects[action].width),y,spriteRects[action].x,spriteRects[action].y,spriteRects[action].x+spriteRects[action].width,spriteRects[action].y+spriteRects[action].height,io);
	  }

	  @Override
	  public void actionPerformed(ActionEvent arg0) {
		  still();
		  surface.repaint();

	  }
	  /*
	private PImage eggs;

	public Egg() {

	}

	public void draw() { 
		eggs = loadImage("eggs.png");
		image(eggs, 50, 100, 50, 50);
	}
	*/
}
