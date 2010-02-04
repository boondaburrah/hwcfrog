package com.richardmagician.hwcfrog;

import java.awt.Color;
import java.awt.Graphics;

public class Background {
	Sprite bush = new Sprite(ImageLoader.load("bush.bmp"));
	Sprite sidewalk = new Sprite(ImageLoader.load("sidewalk.bmp"));
	
	Background(){}
	
	void draw(Graphics g){
		g.setColor(Color.blue);
		g.fillRect(0, 32, 320, 192);
		for(int i=0; i<320; i=i+32){
			bush.draw(g, i, 32);
			sidewalk.draw(g, i, 224);
		}
	}
}
