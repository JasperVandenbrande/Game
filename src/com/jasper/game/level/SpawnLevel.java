package com.jasper.game.level;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.jasper.game.level.tile.TileFactory;
import com.jasper.game.graphics.SpriteFactory;
import com.jasper.game.graphics.SpriteSheetFactory;

public class SpawnLevel extends Level {
	private SpriteFactory SF = new SpriteFactory();
	private SpriteSheetFactory SSF = new SpriteSheetFactory();
	private TileFactory TF = new TileFactory();
	public SpawnLevel(String path) {
		super(path);
		SSF.makeSpriteSheets();
		SF.makeSprites();
		TF.makeTiles();
	}
	/**
	 * retrieve the value of the rgb colors from spawn_level.png and stores it in the tiles array.
	 */
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new double[width * height];
			for(int i = 0; i< width; i++) {
				for(int j = 0; j < height; j++) {
					 Color c =new Color(image.getRGB(i,j));
					 int red = c.getRed();
					 int green = c.getGreen();
					 int blue = c.getBlue();
					 int a = c.getAlpha();
					 tiles[i+j*height] = a * Math.pow(16,6) + red * Math.pow(16,4) + green * Math.pow(16, 2) + blue;
				}
			}
					
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file!");
		}
	}
}
