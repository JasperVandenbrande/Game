package com.jasper.game.entity;

import java.util.Random;

import com.jasper.game.graphics.Screen;
import com.jasper.game.graphics.Sprite;
import com.jasper.game.level.Level;

public abstract class Entity {
	protected Sprite sprite;
	public int x,y; // pixels 
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		
	}
	/**
	 * removes the entity out of the level 
	 * and sets the value of removed true
	 */
	public void remove() {
		level.removeEntity(this);
		removed = true;
		
	}

	/**
	 * Checks if an entity is removed or still on the level.
	 * @return removed - TRUE if the entity is removed out of the level, FALSE if teh entity still exsists
	 */
	public boolean isRemoved() {
		return removed;
	}
	/**
	 * initiates an entity and adds it to the level
	 * @param level the level the entity is on 
	 */
	public void init(Level level) {
		this.level = level;
		level.add(this);
	}

	
	
}
