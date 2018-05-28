package com.jasper.game.level.tile;

import java.util.HashMap;
import java.util.Map;

import com.jasper.game.graphics.Sprite;
import com.jasper.game.input.ReadFile;

public class TileFactory {
	public static final Map<String, Tile> tileMap = new HashMap<String, Tile>();
	private ReadFile rf = new ReadFile("/inits/Tiles.txt");
	
	/**
	 * gives the order to read the file with all necessary info for creating all tiles
	 */
	public void makeTiles() {
		rf.openFile();
		rf.readFileAddTile();
		rf.closeFile();
	}
	/**
	 * creates tiles depending on parameters and puts them in a map.
	 * @param type - determines what kind of tile needs to be created
	 * @param name - name of the tile
	 * @param sprite - sprite that gets rendered
	 * @param color - color to determine which tile goes where in the level
	 */
	public static void addTile(String type, String name, Sprite sprite, double color) {
		switch (type) {
		case "dirtRoad":
			tileMap.put(name, new DirtRoadTile(sprite, color));
			break;
		case "grass":
			tileMap.put(name, new GrassTile(sprite, color));
			break;
		case "roof":
			tileMap.put(name, new RoofTile(sprite, color));
			break;
		case "wall":
			tileMap.put(name, new WallTile(sprite, color));
			break;
		case "door":
			tileMap.put(name, new DoorTile(sprite, color));
			break;
		default:
			tileMap.put(name, new VoidTile(sprite, color));
			break;
		}
	}
	/**
	 * creates tiles depending on parameters and puts them in a map.
	 * @param type - determines what kind of tile needs to be created
	 * @param name - name of the tile
	 * @param sprite - sprite that gets rendered
	 * @param color - color to determine which tile goes where in the level
	 * @param flip - flips the sprite, 0 no flip, 1 flip over x, 2 flip over y, 3 flip over x and y
	 * @param rotation - if true rotates the sprite clockwise with 90 degrees. If false nothing happens
	 */

	public static void addTileFR(String type, String name, Sprite sprite, double color, int flip, Boolean rotation) {
		switch (type) {
		case "dirt":
			tileMap.put(name, new DirtRoadTile(sprite, color, flip, rotation));
			break;
		case "grass":
			tileMap.put(name, new GrassTile(sprite, color, flip, rotation));
			break;
		case "roof":
			tileMap.put(name, new RoofTile(sprite, color, flip, rotation));
			break;
		case "wall":
			tileMap.put(name, new WallTile(sprite, color, flip, rotation));
			break;
		case "door":
			tileMap.put(name, new DoorTile(sprite, color, flip, rotation));
			break;
		default:
			tileMap.put(name, new VoidTile(sprite, color, flip, rotation));
			break;
		}
	}

}
