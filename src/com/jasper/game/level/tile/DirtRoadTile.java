package com.jasper.game.level.tile;

import com.jasper.game.graphics.Screen;
import com.jasper.game.graphics.Sprite;

public class DirtRoadTile extends Tile {
	public DirtRoadTile(Sprite sprite, double color) {
		super(sprite, color);
	}

	public DirtRoadTile(Sprite sprite, double color, int flip, Boolean turn) {
		super(sprite, color, flip, turn);
	}
}
