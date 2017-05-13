import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
public class Oven {
	private Timer t;
	private double bakeTime;
	
	
	public Oven(double time) {
		//Timer t = new Timer();
		
	}
	
	public void bake() {
		t.start();
	}
	
	public void stop() {
		t.stop();
	}
	
	public double getBakeTime() {
		return bakeTime;
	}
	
	
	
}
