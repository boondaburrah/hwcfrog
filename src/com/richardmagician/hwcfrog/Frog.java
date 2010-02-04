package com.richardmagician.hwcfrog;

import java.awt.Graphics;

public class Frog {
	public int lives;
	public double x;
	public double y;
	private Sprite sprite = new Sprite(ImageLoader.load("frog.bmp"));
	
	public Frog(){
		this.lives = 6;
		this.x = 144;
		this.y = 416;
	}
	
	public Frog(int lives, int x, int y){
		this.lives = lives;
		this.x = x;
		this.y = y;
	}
	
	public void tick(long elapsedTime, KeysPressed keysPressed){
		double pixelsToMove = (200*elapsedTime)/1000;
		if(keysPressed.up){
			this.y = this.y-pixelsToMove;
		}
		if(keysPressed.down){
			this.y = this.y+pixelsToMove;
		}
		if(keysPressed.left){
			this.x = this.x-pixelsToMove;
		}
		if(keysPressed.right){
			this.x = this.x+pixelsToMove;
		}
		return;
	}
	
	public void die(){
		this.x = 144;
		this.y = 416;
		this.lives--;
	}
	
	public void draw(Graphics g){
		int drawx = (((int) this.x)/2) * 2;
		int drawy = (((int) this.y)/2) * 2;
		// Draw life count
		for (int i = 0; i<this.lives; i++){
			sprite.draw(g, i*32, 448);
		}
		sprite.draw(g, drawx, drawy);
	}
}
