package com.jasper.game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path; 
	public final int SIZE;
	private int width, height;
	public int[] pixels;
	
	/**
	 * Create a square spriteSheet.
	 * @param path - location of the spriteSheet
	 * @param size - how many pixels each side is
	 */
	public SpriteSheet (String path, int size) {
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE*SIZE];
		load();
	}
	/**
	 * Create a non square spriteSheet.
	 * @param path - location of the spriteSheet
	 * @param width - how many pixels wide it is
	 * @param height - how many pixels heigh it is
	 */
	public SpriteSheet (String path, int width, int height) {
		SIZE = -1;
		this.path = path;
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		load();
	}
	/**
	 * loads the colors of every pixel of the spriteSheet in a pixel array. 
	 * to retrieve later
	 */
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0	, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
