package com.jasper.game.level.tile;

import com.jasper.game.graphics.Sprite;

public class GrassTile extends Tile{

	public GrassTile(Sprite sprite, double color) {
		super(sprite, color);
	}
	public GrassTile(Sprite sprite, double color, int flip, Boolean turn) {
		super(sprite, color, flip, turn);
	}
}
