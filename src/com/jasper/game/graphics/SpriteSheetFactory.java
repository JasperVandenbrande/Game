package com.jasper.game.graphics;

import java.util.HashMap;
import java.util.Map;

import com.jasper.game.input.ReadFile;

public class SpriteSheetFactory {
	public static final Map<String, SpriteSheet> spriteSheetMap = new HashMap<String, SpriteSheet>();
	private ReadFile rf = new ReadFile("/inits/SpriteSheets.txt");
	
	/**
	 * gives the order to read the file with all necessary info for creating all spriteSheets
	 */
	public void makeSpriteSheets() {
		rf.openFile();
		rf.readFileAddSpriteSheet();
		rf.closeFile();
	}
	/**
	 * creates spriteSheets and puts the in a map
	 * @param name - name of the spriteSheet
	 * @param path - location of the spriteSheet
	 * @param size - size of the spriteSheet (must be square)
	 */
	public static void addSpriteSheet(String name, String path, int size) {
		spriteSheetMap.put(name, new SpriteSheet(path, size));
	}
}

	
	

