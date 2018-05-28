package com.jasper.game.graphics;


import com.jasper.game.entity.abilities.ranged.RangeAbility;
import com.jasper.game.level.tile.Tile;


public class Screen {

	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE -1;
	public int[] tiles = new int [MAP_SIZE*MAP_SIZE];
	public int xOffset, yOffset;
	

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];		
	}

	public void Clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	/**
	 * renders a tile
	 * @param xp in pixel precision
	 * @param yp in pixel precision
	 * @param tile the tile that needs to be rendered
	 */
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y=0; y < tile.sprite.SIZE ; y++) {
			int ya = y + yp;
			int ys = y;
			if (tile.getFlip() == 2 || tile.getFlip() == 3) {
				ys = 15 - y;
			}
			for (int x=0; x < tile.sprite.SIZE ; x++) {
				int xa = x + xp;
				int xs = x;
				if (tile.getFlip() == 1 || tile.getFlip() == 3) {
					xs = 15 - x;
				}
				if(xa  < -tile.sprite.SIZE || xa >=  width|| ya < 0 || ya >= height) break;
				if(xa<0)xa=0;
				int col;
				if(tile.getTurn()) {
					col = tile.sprite.pixels[ys + xs*tile.sprite.SIZE];
					if(col != 0xffff00ff) {
						pixels[xa + ya * width] = col ;
					} 
				}
				else {
					col = tile.sprite.pixels[xs + ys*tile.sprite.SIZE];
					if(col != 0xffff00ff) {
					pixels[xa + ya * width] = col ;
					}
				}
				
			}
		}
	}
	
	/**
	 * renders a player
	 * @param xp in pixel precision
	 * @param yp in pixel precision
	 * @param sprite is the sprite of the player, this is used so that animation is possible
	 * @param flip determines if the sprite needs to get fliped, 0 no flip, 1 flip around x, 2 flip around y, 3 flip around x and y
	 */
	public void renderPlayer(int xp, int yp, Sprite sprite, int flip) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y=0; y < sprite.SIZE ; y++) {
			int ya = y + yp;
			int ys = y;
			if (flip == 2 || flip == 3) {
				ys = 15 - y;
			}
			for (int x=0; x < sprite.SIZE ; x++) {
				int xa = x + xp;
				int xs = x;
				if (flip == 1 || flip == 3) {
					xs = 15 - x;
				}
				if(xa  < -sprite.SIZE || xa >=  width|| ya < 0 || ya >= height) break;
				if(xa<0)xa=0;
				int col = sprite.pixels[xs + ys*sprite.SIZE];
				if(col != 0xffff00ff) {
					pixels[xa + ya * width] = col ;
				}
			}
		}
	
	}
	/**
	 * Sets the offset of the map so the player is always in the middle
	 * @param xOffset offset in x direction
	 * @param yOffset offset in y direction
	 */
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	/**
	 * renders a projectile
	 * @param xp in pixel precision
	 * @param yp in pixel precision
	 * @param p the projectile that needs to be rendered
	 */
	public void renderProjectile(int xp, int yp, RangeAbility p) {

		xp -= xOffset;
		yp -= yOffset;
		for (int y=0; y < p.getSpriteSize() ; y++) {
			int ya = y + yp;
			for (int x=0; x < p.getSpriteSize() ; x++) {
				int xa = x + xp;
				if(xa  < -p.getSpriteSize() || xa >=  width|| ya < 0 || ya >= height) break;
				if(xa<0) xa = 0;
				int col = p.getSprite().pixels[x + y*p.getSpriteSize()];
				if(col != 0xffff00ff) {
					pixels[xa + ya * width] = col ;
				}	
				
			}
		}		
	}
	
	/**
	 * Renders a sprite 
	 * @param xp in pixel precision
	 * @param yp in pixel precision
	 * @param sprite the sprite that gets rendered
	 * @param fixed if true the sprite will stay at a location, if false the sprite will move with the player (For UI)
	 */
	public void renderSprite(int xp, int yp, Sprite sprite, Boolean fixed) {
		if(fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for(int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for(int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa+ya*width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
	}
	
	
}
	
	
	
	
	
	
	

