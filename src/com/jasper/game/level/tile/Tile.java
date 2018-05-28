package com.jasper.game.level.tile;


import com.jasper.game.graphics.Screen;
import com.jasper.game.graphics.Sprite;

public class Tile{
	
	
	public Sprite sprite;
	private int flip = 0; 
	private Boolean turn = false;
	public double color;
	
	public Tile(Sprite sprite, double color) {
		this.sprite = sprite;
		flip = 0;
		this.color = color;
	}	
	public Tile(Sprite sprite, double color, int flip, Boolean turn) {
		this.sprite = sprite;	
		this.color = color;
		this.flip = flip;
		this.turn = turn;
	}
	
	/**
	 * gives the command to render a tile at tile coordinate (x, y), 
	 * changes tile coordinates to pixel coordinates
	 * @param x - x-Tile Coordinate 
	 * @param y - y-Tile Coordinate 
	 * @param screen renders tiles and entities.
	 */
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	/**
	 * Standard tile is not solid
	 * @return false 
	 */
	public boolean solid() {
		return false;
	}
	
	/**
	 * Determines if tile needs to be flipped
	 * @return flip - flips the sprite, 0 no flip, 1 flip over x, 2 flip over y, 3 flip over x and y
	 */
	public int getFlip() {
		return flip;
	}
	/**
	 * Determines if tile needs to be turned
	 * @return turn - if true rotates the sprite clockwise with 90 degrees. If false nothing happens
	 */
	public Boolean getTurn() {
		return turn;
	}
}
