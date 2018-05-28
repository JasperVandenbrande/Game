package com.jasper.game.level;



import java.util.ArrayList;
import java.util.List;

import com.jasper.game.entity.Entity;
import com.jasper.game.graphics.Screen;
import com.jasper.game.level.tile.Tile;
import com.jasper.game.level.tile.TileFactory;

public class Level {

	public final int TILESIZE = 16;
	
	protected int width, height;
	protected double[] tiles;
	
	public static Level spawn = new SpawnLevel("/textures/level/spawn_level/spawn_level.png");
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new double[width * height];
	}
	/**
	 * Constructs the level
	 * @param path - path to the file where the level is drawn
	 */
	public Level(String path) {
		loadLevel(path);
	}
	
	
	protected void loadLevel(String path) {
	}
	
	/**
	 * commands to update all entities on the level
	 */
	public void update() {
		for(int i = 0; i<entities.size();i++) {
			entities.get(i).update();
		}
	}
	

	/**
	 * Manages the offset of the camera.
	 * gives the command to render the tiles and the entities on the level.
	 * @param xScroll, yScroll in pixel precision coordinates of where the tile should be. 
	 * @param screen renders tiles and entities.
	 */

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + TILESIZE) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + TILESIZE) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for(int i = 0; i<entities.size();i++) {
			entities.get(i).render(screen);
		}
	}
	
	/**
	 * adds an entity to a list of entities so level knows which entities are on it
	 * @param e can be any entity
	 */
	public void add(Entity e) {
		entities.add(e);
		
	}
	public void removeEntity(Entity e) {
		for(int i = 0; i< entities.size(); i++) {
			if(entities.get(i).equals(e)) {
				entities.remove(i);
			}
		}
	}
	/**
	 * Tiles are linked to a color, retrieves the color from the tiles array, returns the appropriated tile.
	 * If out of bounds: return voidtile. If no color matches: return voidtile, 
	 * @param x, y are in tile precision
	 * @return Tile - returns the correct tile.
	 */
	public Tile getTile(int x, int y) {
		
//		Out of bounds
		if (x < 0 || y < 0) {
			return TileFactory.tileMap.get("voidTile");
		}

		if (x >= width || y >= height) {
			return TileFactory.tileMap.get("voidTile");
		}
		
		for(String key : TileFactory.tileMap.keySet()) {
			
			if(tiles[x + y * width] == TileFactory.tileMap.get(key).color) {
				return TileFactory.tileMap.get(key);
			}
		}
		
		// default
		return TileFactory.tileMap.get("voidTile");
	}
	
	/**
	 * @return level - returns the level that is loaded.
	 */
	public Level getLevel() {
		return this;
	}
	
	protected void time() {

	}
	/**
	 * retrieve a list of all entities on the level
	 * @return entities - list of all entities on the level
	 */
	public List<Entity> getEntities() {
		return entities;
	}

}
