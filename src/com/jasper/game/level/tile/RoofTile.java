package com.jasper.game.level.tile;

import com.jasper.game.graphics.Sprite;

public class RoofTile extends Tile {
	
	public RoofTile(Sprite sprite, double color) {
		super(sprite, color);
	}

	public RoofTile(Sprite sprite, double color, int flip, Boolean turn) {
		super(sprite, color, flip, turn);
	}
	/**
	 * Overrides the super method to indicate that a tile is solid
	 */
	public boolean solid() {
		return true;
	}
}
