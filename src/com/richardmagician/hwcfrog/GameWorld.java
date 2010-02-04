package com.richardmagician.hwcfrog;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameWorld {
	private Background background = new Background();
	private Frog frog = new Frog();
	private ArrayList rovingHazards = new ArrayList();
	private HashMap<String, Sprite> sprites = new HashMap();
	
	// Load all the sprites for the hazards.
	private Sprite turtleUp = new Sprite(ImageLoader.load("turtle-up.bmp"));
	private Sprite turtleDown = new Sprite(ImageLoader.load("turtle-down.bmp"));
	private Sprite startLog = new Sprite(ImageLoader.load("log-start.bmp"));
	private Sprite midLog = new Sprite(ImageLoader.load("log-cont.bmp"));
	private Sprite endLog = new Sprite(ImageLoader.load("log-end.bmp"));
	private Sprite startCar = new Sprite(ImageLoader.load("car-start.bmp"));
	private Sprite endCar = new Sprite(ImageLoader.load("car-end.bmp"));
	private Sprite startTruck = new Sprite(ImageLoader.load("truck-start.bmp"));
	private Sprite midTruck = new Sprite(ImageLoader.load("truck-cont.bmp"));
	private Sprite endTruck = new Sprite(ImageLoader.load("truck-end.bmp"));
	
	private Hazard testLog = new Hazard(128, 128, startLog, midLog, endLog, true, 5, true, 50);
	private Hazard testCar = new Hazard(96, 288, startCar, endCar, false, false, 50);
	private Hazard testTruck = new Hazard(160, 384, startTruck, midTruck, endTruck, false, 3, false, 50);
	private Hazard testTurtle = new Hazard(64, 160, turtleUp, true, true, 50);
	
	public GameWorld(){
		sprites.put("frog", new Sprite(ImageLoader.load("frog.bmp")));
	}
	
	public void tick(long elapsedTime, KeysPressed keysPressed){
		frog.tick(elapsedTime, keysPressed);
		testLog.tick(elapsedTime, keysPressed);
		testTurtle.tick(elapsedTime, keysPressed);
		testCar.tick(elapsedTime, keysPressed);
		testTruck.tick(elapsedTime, keysPressed);
	}
	
	public void draw(Graphics g){
		background.draw(g);
		testLog.draw(g);
		testTurtle.draw(g);
		frog.draw(g);
		testCar.draw(g);
		testTruck.draw(g);
	}
	
}
