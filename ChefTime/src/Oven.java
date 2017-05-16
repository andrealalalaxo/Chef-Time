import java.awt.*;
import java.awt.event.*;
import java.util.Random;
//import java.util.Timer;
//mport java.util.TimerTask;
import javax.swing.*;
public class Oven implements ActionListener {
	private Timer timer;
	private int setTime; //time the pastry needs to be baked
	private int bakeTime; //time the pastry was actually baked
	
	public Oven(int time) {
		super();
		bakeTime = 0;
		setTime = time;
		timer = new Timer(1000, this);
		
	}
	/*
	public void start() {
		timer.schedule(new TimerTask() {
			public void run() {
				bake();
			}
		},bakeTime);
		
		
		
		
	}
	*/
	
	public void setTime(int i) {
		setTime = i;
	}

	public void startBaking() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	public double getSetTime() {
		return setTime;
	}

	public int getDifference() { 
		return Math.abs(setTime - bakeTime);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		bakeTime++;
		
	}
	
	
	
}
