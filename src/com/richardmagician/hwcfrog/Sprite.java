package com.richardmagician.hwcfrog;

import java.awt.Image;
import java.awt.Graphics;

/**
 * A Sprite contains an image to be displayed on the screen.
 * Note, this only really stores the image, and doesn't where to draw
 * itself. This is so the image is only stored once for however many
 * times it needs to be drawn.
 */
public class Sprite {
	Image image; // The image to be drawn.
	
	/**
	 * Create a new sprite with an image
	 * 
	 * @param image the image to store in this sprite.
	 */
	Sprite(Image image){
		this.image = image;
	}
	
	/**
	 * Get the width of the sprite.
	 * 
	 * @return the width of the sprite in pixels
	 */
	int getWidth(){
		return image.getWidth(null);
	}
	
	/**
	 * Get the height of the sprite.
	 * 
	 * @return the height of the sprite in pixels
	 */
	int getHeight(){
		return image.getHeight(null);
	}
	
	/**
	 * Draw the sprite on the canvas
	 * 
	 * @param g The graphics context to draw on
	 * @param x the x coordinate to draw the sprite
	 * @param y the y coordinate to draw the sprite
	 */
	void draw(Graphics g, int x, int y){
		g.drawImage(image, x, y, null);
	}
}
