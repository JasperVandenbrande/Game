package com.jasper.game.level.tile;

import com.jasper.game.graphics.Screen;
import com.jasper.game.graphics.Sprite;

/**
 * Default tile
 */

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite, double color) {
		super(sprite, color);
	}
	
	public VoidTile(Sprite sprite, double color, int flip, Boolean turn) {
		super(sprite, color, flip, turn);
	}
}
