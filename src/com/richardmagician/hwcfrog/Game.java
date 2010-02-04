package com.richardmagician.hwcfrog;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends Canvas{
	BufferStrategy strategy;						// stores the strategy we'll be using for graphics. Double buffering.
	KeysPressed keysPressed = new KeysPressed();	// Keeps track of the current state of the keyboard. Will be passed to the World.
	boolean gameRunning = true;
	GameWorld world;								// the Game World.
	
	Game(){
		// create the window for the game
		JFrame container = new JFrame("Highway Crossing Frog!");
		
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(320, 480));
		panel.setLayout(null);
		
		setBounds(0, 0, 320, 480);
		panel.add(this);
		
		// Make sure nothing paints to the window that shouldn't
		setIgnoreRepaint(true);
		
		// Get ready to, and then display, the window.
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		// Exit the program when the window closes.
		container.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		// Listen for key presses.
		addKeyListener(new KeyInputHandler());
		
		// Put this program's window on top so the game can get keyEvents, etc.
		requestFocus();
		
		// Set the window to double-buffered style.
		createBufferStrategy(2);
		this.strategy = getBufferStrategy();
	}
	
	public void gameLoop(){
		long lastLoopTime = System.currentTimeMillis();
		
		// Don't let the game start if the world hasn't been created/assigned yet.
		if (world == null){
			System.err.println("I'm afraid I can't let you do that, Dave");
			System.exit(0);
		}
		
		while (gameRunning){
			// Calculate how long (in milliseconds) it's been since the last frame.
			long elapsedTime = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			
			// Get the graphics ready, and clear the screen to black.
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0, 0, 320, 480);
			
			// Tick the game world, and have it draw to the screen.
			// The game world should know how long it's been since the last frame
			// and what the keyboard looks like.
			this.world.tick(elapsedTime, keysPressed);
			this.world.draw(g);
			
			// Clear the graphics and flip the buffer.
			g.dispose();
			strategy.show();
		}
	}
	
	private class KeyInputHandler extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				keysPressed.left = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				keysPressed.right = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_UP){
				keysPressed.up = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN){
				keysPressed.down = true;
			}
		}
		
		public void keyReleased(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				keysPressed.left = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				keysPressed.right = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_UP){
				keysPressed.up = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN){
				keysPressed.down = false;
			}
		}
		
		public void keyTyped(KeyEvent e){
			// if the use pressed escape, close the game.
			if(e.getKeyChar() == 27){
				System.exit(0);
			}
		}
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.world = new GameWorld();
		game.gameLoop();
	}
	
}
