package com.richardmagician.hwcfrog;

import java.awt.Graphics;

public class Hazard {
	public final int height = 32;
	
	public Sprite startSprite; // used for double-length and variable length hazards.
	public Sprite endSprite; // used for double-length and variable length hazards.
	public Sprite sprite; // used for single-length and variable length hazards.
	int length; // how long is this hazard, in pixels?
	boolean switcher = false; // true if this sprite should switch every while.
	boolean good; // If the frog runs into this, is it okay?
	double x; // Position variables
	double y; // standard stuff.
	boolean direction; // left if true, right if false
	double switchTimer = 0; // counts the milliseconds since last switch (sinking turtles);
	public double delta = 0; // how far this hazard moved this tick.
	public double speed; // how fast this hazard moves per tick.
	
	/* UPDATE COMMENTS */
	
	/**
	 * Construct a single-length hazard.
	 * 
	 * @param sprite the sprite to use for this hazard
	 * @param good false if the frog will die by touching it
	 * @param direction the direction this hazard heads. True for left.
	 */
	public Hazard(double x, double y, Sprite sprite, boolean good, boolean direction, double speed){
		this.sprite = sprite;
		this.good = good;
		this.length = 32;
		this.direction = direction;
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	/**
	 * Constructs a double-length hazard
	 * 
	 * @param startSprite the left sprite for the hazard
	 * @param endSprite	the right sprite for the hazard
	 * @param good false if this frog will die by touching it.
	 * @param direction True if the hazard is going left.
	 */
	public Hazard(double x, double y, Sprite startSprite, Sprite endSprite, boolean good, boolean direction, double speed){
		this.startSprite = startSprite;
		this.endSprite = endSprite;
		this.good = good;
		this.length = 64;
		this.direction = direction;
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	/**
	 * Construct a variable-length (3 or more) hazard.
	 * IMPORTANT! hazards are constructed by number of sprites, not pixels
	 * determining the length! example: a sprite that is 4 long will be 4*32
	 * or 128 pixels long.
	 * 
	 * @param startSprite the leftmost sprite of the hazard
	 * @param sprite the sprite that fills in the middle by repeating
	 * @param endSprite the rightmost sprite of the hazard
	 * @param good true if this hazard doesn't kill the frog
	 * @param unitLength how many sprites long to make this hazard.
	 * @param direction direction this hazard moves in. True for left, false for right.
	 */
	public Hazard(double x, double y, Sprite startSprite, Sprite sprite, Sprite endSprite, boolean good, int unitLength, boolean direction, double speed){
		this.startSprite = startSprite;
		this.sprite = sprite;
		this.endSprite = endSprite;
		this.good = good;
		this.length = unitLength * 32;
		this.direction = direction;
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public void tick(long elapsedTime, KeysPressed keysPressed){
		if (this.switchTimer > 2000){
			this.good = !this.good;
			this.switchTimer = 0;
		}
		if (this.direction){
			this.delta = 0 - ((this.speed*elapsedTime)/1000);
		} else {
			this.delta = (this.speed*elapsedTime)/1000;
		}
		this.x = this.x + this.delta;
		this.switchTimer = this.switchTimer + elapsedTime;
	}
	
	public void draw(Graphics g){
		int drawx = (((int) this.x)/2) * 2;
		int drawy = (((int) this.y)/2) * 2;
		// Three possible cases here
		// 1: we have a single-sprite hazard.
		if(this.length == 32){
			this.sprite.draw(g, drawx, drawy);
		}
		// 2: we have a two-sprite hazard.
		else if(this.length == 64){
			this.startSprite.draw(g, drawx, drawy);
			this.endSprite.draw(g, drawx+32, drawy);
		}
		// 3: we have a variable-length hazard.
		else {
			for (int i = 0; i < (int) this.length; i = i+32){
				if(i == 0){
					this.startSprite.draw(g, drawx+i, drawy);
				} else if(i > (int) this.length - 33){
					this.endSprite.draw(g, drawx+i, drawy);
				} else {
					this.sprite.draw(g, drawx+i, drawy);
				}
			}
		}
		return;
	}
}
