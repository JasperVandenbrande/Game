package com.jasper.game.graphics;

import java.util.HashMap;
import java.util.Map;
import com.jasper.game.input.ReadFile;


public class SpriteFactory {
	public static final Map<String, Sprite> spriteMap = new HashMap<String, Sprite>();
	private ReadFile rf = new ReadFile("/inits/Sprites.txt");
	
	/**
	 * gives the order to read the file with all necessary info for creating all sprites
	 */
	public void makeSprites() {
	rf.openFile();
	rf.readFileAddSprite();
	rf.closeFile();
	
	}
	/**
	 * creates sprites and puts the in a map
	 * @param name - name of the spriteSheet
	 * @param size - size of the spriteSheet (must be square)
	 * @param x - x coordinate on the spriteSheet (in tile precision)
	 * @param y - y coordinate on the spriteSheet (in tile precision)
	 * @param SpriteSheet - determines on which spriteSheet the sprite can be found
	 */
	public static void addSprite(String name, int size, int x, int y, SpriteSheet sheet) {
		spriteMap.put(name, new Sprite(size, x, y, sheet));
	}
}

