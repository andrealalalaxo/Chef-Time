import java.awt.Image;

import processing.core.PApplet;
import processing.core.PImage;

public class Ingredient {
	private PImage img;
	private int x;
	private int y;
	private int width;
	private int height;
	 
	private PImage batterImg;
	public Ingredient(PImage img, int x, int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.img = img;
		
	}
	

	public boolean isPointInside(double x, double y){
		if(x > this.x && x < this.x + width && y > this.y && y < this.y + height){
			return true;
		} else {
			return false;
		}
	}

	public boolean isInBowl(double x, double y){
		if(x > 250 && x < 500 && y > 300 && y < 400){
			return true;
		} else {
			return false;
		}
	}
	
	public void toBatter(){
		if(this.isInBowl(this.getX(), this.getY())){
		//	PImage bowlImg =  loadImage("bowl.png");
		//	this.setImg( loadImage("batter.png"));
		} else {
		
		}
	}
	
	public void released() {
		
	}
	
	public void setX(int x1){
		this.x = x1;
	}
	
	public void setY(int y1){
		this.y = y1;
	}
	
	public void setImg(PImage img1){
		this.img = img1;
	}
	
	public int getX(){
		return this.x;
	}
	
	
	public int getY(){
		return this.y;
	}

	
	public void draw(PApplet drawer) {
		drawer.image(img, x, y, width, height);
		// Use drawer to draw img
	}
}
