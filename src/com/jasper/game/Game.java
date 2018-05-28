package com.jasper.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
import com.jasper.game.entity.mob.Player;
import com.jasper.game.graphics.Screen;
import com.jasper.game.input.Keyboard;
import com.jasper.game.input.Mouse;
import com.jasper.game.level.Level;



public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private static int width = 300;
	private static int height = width / 16 * 9;
	private static int scale = 3;
	public static String title = "rain";

	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;

	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = Level.spawn;
		player = new Player("Wizard", 144, 140, key);
		player.init(level);
		addKeyListener(key);
		Mouse mouse = new Mouse();
		addMouseListener(mouse);;
		addMouseMotionListener(mouse);

	}
	public static int getWindowWidth() {
		return width*scale;
	}
	public static int getWindowHeight() {
		return height * scale;
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0; // conversion to nano seconds 60.0 times a second.
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;

				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	public void update() {
		key.update();
		player.update();
		level.update();
		

	}
	
	public static String errorMessage = "";
	public static String charges= "";
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.Clear();
		int xScroll = player.x - screen.width / 2; // 8 is half player sprite
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);
//		Sprite sprite = new Sprite(80, height, 0xFF0000);
//		screen.renderSprite(width-80, 0, sprite, false);
//		screen.renderSprite(50, 50, SpriteFactory.spriteMap.get("player_down"), true);
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana",0,50));
//		g.fillRect(Mouse.getX()-16, Mouse.getY()-16, 32, 32);
		g.drawString(errorMessage, 80, 80);
		g.drawString(charges, 80, 40);
		g.dispose(); // rmv graphics after useage
		bs.show(); // bufferswapping or blitting
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // remove process when closing window
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
		
	}

}
