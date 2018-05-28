package com.jasper.game.level.tile;

import com.jasper.game.graphics.Screen;
import com.jasper.game.graphics.Sprite;

public class DoorTile extends Tile {
	
	public DoorTile(Sprite sprite, double color) {
		super(sprite, color);
	}

	public DoorTile(Sprite sprite, double color, int flip, Boolean turn) {
		super(sprite, color, flip, turn);
	}
}
