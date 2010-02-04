package com.richardmagician.hwcfrog;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public ImageLoader(){}
	
	public static Image load(String ref){
		BufferedImage sourceImage = null;
		BufferedImage processedImage = null;
		String classPath = System.getProperty("java.class.path");
		String fileSeparator = System.getProperty("file.separator");
		ref = classPath+fileSeparator+"com"+fileSeparator+"richardmagician"+fileSeparator+"hwcfrog"+fileSeparator+ref;

		try {
			sourceImage = ImageIO.read(new File(ref));
		} catch (IOException e) {
			fail("Couldn't load image: "+ref);
		}
		
		processedImage = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D pg = processedImage.createGraphics();
		pg.setComposite(AlphaComposite.Src);
		pg.drawImage(sourceImage, null, 0, 0);
		pg.dispose();
		processedImage = handleTransparency(processedImage);
		
		return Toolkit.getDefaultToolkit().createImage(processedImage.getSource());
	}
	
	private static BufferedImage handleTransparency(BufferedImage bimage){
		for (int yc = 0; yc<bimage.getHeight(); yc++){
			for (int xc = 0; xc<bimage.getWidth(); xc++){
				bimage.setRGB(xc, yc, handleTransparentPixel(bimage.getRGB(xc, yc)));
			}
		}
		
		return bimage;
	}
	
	private static int handleTransparentPixel(int pixel){
		if(pixel == 0xFFFF00FF){
			return 0x00FF00FF;
		}
		return pixel;
	}
	
	private static void fail(String message){
		System.err.println(message);
		System.exit(0);
	}
}
