package com.jasper.game.graphics;

public class Sprite {
	public static Sprite particle = new Sprite(3, 0xFFFF0000);
	
	public final int SIZE;
	private int x,y;
	private int width, height;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public Sprite(int size, int color) {
		this.width = size;
		this.height = size;
		this.SIZE = size;
		pixels = new int[SIZE*SIZE];
		setColor(color);
	}
	
	public Sprite(int width, int height, int color) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		setColor(color);
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.width = size;
		this.height = size;
		this.SIZE = size;
		pixels = new int[SIZE*SIZE];
		this.x = x *SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}
	/**
	 * Makes a sprite have one color
	 * @param color the value of the color(in hex)
	 */
	private void setColor(int color) {
		for(int i = 0; i<width*height; i++) {
			pixels[i] = color;
		}
	}
	/**
	 * gives the width of the sprite
	 * @return width in pixels
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * gives the height of the sprite
	 * @return height in pixels
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * loads the colors of every pixel of the Sheet out of the spriteSheet pixel array in a sprite pixel array. 
	 */
	private void load() {
		for(int y = 0; y <SIZE; y++) {
			for(int x = 0; x <SIZE; x++) {
				pixels[x+y*SIZE] = sheet.pixels[x+this.x + (y + this.y)*sheet.SIZE];
			}
		}
		
	}
}
