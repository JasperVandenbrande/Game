package com.jasper.game.input;

import java.io.File;
import java.util.Scanner;

import com.jasper.game.level.tile.TileFactory;
import com.jasper.game.graphics.SpriteFactory;
import com.jasper.game.graphics.SpriteSheetFactory;


public class ReadFile {
	private String path;
	private Scanner file;

	public ReadFile(String path) {
		this.path = path;
	}
	
	/**
	 * opens a new file
	 */

	public void openFile() {
		try {
			file = new Scanner(new File(ReadFile.class.getResource(path).toURI()));
		} catch (Exception e) {
			System.out.println("init File not found");
		}
	}
	/**
	 * Closes a file
	 */
	public void closeFile() {
		file.close();
	}
	
	/**
	 * Reads a file, 
	 * retrieves: name / size/ x / y / spriteSheet
	 * gives the order to create a new sprite and add it to the spriteMap 
	 * with name name, size size. You can find the sprite on the spriteSheet spriteSheet at location x, y
	 */
	public void readFileAddSprite() {
		while (file.hasNext()) {
			String name = file.next();
			if (name.charAt(0) != '*') {
				int size = file.nextInt();
				int x = file.nextInt();
				int y = file.nextInt();
				String spriteSheetName = file.next();
				SpriteFactory.addSprite(name, size, x, y, SpriteSheetFactory.spriteSheetMap.get(spriteSheetName));

			} else {
				file.nextLine();
			}
		}
	}

	
	/**
	 * Reads a file, 
	 * retrieves: name / path
	 * gives the order to create a new spriteSheet and add it to the spriteSheetMap 
	 * with name name, and path path
	 */
	public void readFileAddSpriteSheet() {
		while (file.hasNext()) {
			String name = file.next();
			if (name.charAt(0) != '*') {
				String path = file.next();
				int size = file.nextInt();
				SpriteSheetFactory.addSpriteSheet(name, path, size);
			} else {
				file.nextLine();
			}

		}
	}
	/**
	 * Reads a file, 
	 * retrieves: type / name / sprite / color (/ flip / turn)
	 * gives the order to create a new tile of type type and add it to the spriteMap 
	 * Depending on how many parameters on a line in the file, this methods chooses how to construct teh tile.
	 * with name name, sprite sprite, color color. (flip flip and turn turn)
	 */
	public void readFileAddTile() {
		while (file.hasNext()) {
			String type = file.next();
			if (type.charAt(0) != '*') {
				String name = file.next();
				String spriteName = file.next();
				Double color = stringToHex(file.next());
				if (file.hasNextInt()) {
					int flip = file.nextInt();
					Boolean turn = file.nextBoolean();

					TileFactory.addTileFR(type, name, SpriteFactory.spriteMap.get(spriteName), color, flip, turn);
				} else {

					TileFactory.addTile(type, name, SpriteFactory.spriteMap.get(spriteName), color);
				}
			} else {
				file.nextLine();
			}

		}
	}
	
	/**
	 * Changes a hexadecimal string to a double with that value
	 * @param hex a string representing a hexadecimal of length 8 consisting of the following characters {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F} 
	 * @return the value of that hex number
	 */
	public double stringToHex(String hex) {
		double col = 0;
		for(int i = 0; i<hex.length(); i++) {
			col += CharTOHex(hex.charAt(hex.length()-1-i))*Math.pow(16, i); 
		}
		return col;
	}
	
	/**
	 * Determines the value of a char in hex, if invalid char returns value 0
	 * @param c a char 
	 * @return numb - the value of that char in hex
	 */
	public int CharTOHex(char c) {
		int numb;
		switch(c) {
		case '1':
			numb = 1;
			break;
		case '2':
			numb = 2;
			break;
		case '3':
			numb = 3;
			break;
		case '4':
			numb = 4;
			break;
		case '5':
			numb = 5;
			break;
		case '6':
			numb = 6;
			break;
		case '7':
			numb = 7;
			break;
		case '8':
			numb = 8;
			break;
		case '9':
			numb = 9;
			break;
		case 'a': //fall through
		case 'A':
			numb = 10;
			break;
		case 'b': //fall through
		case 'B':
			numb = 11;
			break;
		case 'c': //fall through
		case 'C':
			numb = 12;
			break;
		case 'd': //fall through
		case 'D':
			numb = 13;
			break;
		case 'e': //fall through
		case 'E':
			numb = 14;
			break;
		case 'f': //fall through
		case 'F':
			numb = 15;
			break;
		default:
			numb =0;
			
		}
		return numb;
	}
}
