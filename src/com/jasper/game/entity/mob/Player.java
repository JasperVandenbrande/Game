package com.jasper.game.entity.mob;

import static com.jasper.game.entity.mob.Direction.EAST;
import static com.jasper.game.entity.mob.Direction.NORTH;
import static com.jasper.game.entity.mob.Direction.SOUTH;
import static com.jasper.game.entity.mob.Direction.WEST;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.jasper.game.Game;
import com.jasper.game.entity.abilities.Ability;
import com.jasper.game.graphics.Screen;
import com.jasper.game.graphics.Sprite;
import com.jasper.game.graphics.SpriteFactory;
import com.jasper.game.input.Keyboard;
import com.jasper.game.input.Mouse;



public class Player extends Mob {
	
	
	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	public static final Map<String, Ability> abilities = new HashMap<String, Ability>();

	public Player(Keyboard input) {
		this.input = input;
	}

	public Player(String name, int x, int y, Keyboard input) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = SpriteFactory.spriteMap.get("player_down");
		makeAbilities();
	}

	/**
	 * Updates the player
	 * Clears projectiles
	 * updates the shooting
	 * sets the walking boolean
	 * 
	 */
	public void update() {
		Game.errorMessage="" +abilities.get("GCD").getCharges() + "  " + abilities.get("GCD").getCooldown()+"  " +abilities.get("fireball").getCharges() + "  " + abilities.get("fireball").getCooldown();
		for(String key : abilities.keySet()) {
			abilities.get(key).update();
			}
		
		int xa = 0, ya = 0;
		if (anim < 7500) {
			anim++;
		} else {
			anim = 0;
		}
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;
		clearProjectile();
		updateShooting();

		if (xa != 0 || ya != 0){
			move(xa, ya);
			walking = true;
		}
		else 
			walking = false;
	}
		
	/**
	 * test if the player can use it's ranged abilities
	 * determins the angle for the shot
	 */
	private void updateShooting() {
		if(Mouse.getButton() == 1) {
			if(abilities.get("GCD").tryUse()){
				if(abilities.get("fireball").tryUse()) {
				double dx = Mouse.getX()-Game.getWindowWidth()/2;
				double dy = Mouse.getY()-Game.getWindowHeight()/2;
				double dir = Math.atan2(dy, dx);
				shoot(x,y,dir);
					
				}
			}
		}		
	}


	/**
	 * Gives the command to render the player, if the player is walking, will choose the right sprite so the player is animated
	 */
	public void render(Screen screen) {

		int flip = 0;
		if (dir == SOUTH) {
			if (walking) {
				if (anim % 20 > 10) {
					sprite = SpriteFactory.spriteMap.get("player_down_1");
				} else {
					sprite = SpriteFactory.spriteMap.get("player_down_2");;
				}
			} else {
				sprite = SpriteFactory.spriteMap.get("player_down");;
			}

		}

		if (dir == NORTH) {
			if (walking) {
				if (anim % 20 > 10) {
					sprite = SpriteFactory.spriteMap.get("player_up_1");
				} else {
					sprite = SpriteFactory.spriteMap.get("player_up_2");
				}
			} else {
				sprite = SpriteFactory.spriteMap.get("player_up");
			}

		}

		if (dir == EAST) {
			if (walking) {
				if (anim % 20 > 10) {
					sprite = SpriteFactory.spriteMap.get("player_side_1");
				} else {
					sprite = SpriteFactory.spriteMap.get("player_side_2");
				}
			} else {
				sprite = SpriteFactory.spriteMap.get("player_side");
			}
			flip = 1;
		}

		if (dir == WEST) {
			if (walking) {
				if (anim % 20 > 10) {
					sprite = SpriteFactory.spriteMap.get("player_side_1");
				} else {
					sprite = SpriteFactory.spriteMap.get("player_side_2");
				}
			} else {
				sprite = SpriteFactory.spriteMap.get("player_side");
			}

		}
		screen.renderPlayer(x - sprite.SIZE/2, y - sprite.SIZE/2, sprite, flip);
	}
	
	/**
	 * 
	 */
	
	private void makeAbilities() {
		Ability GCD = new Ability("GCD", 1, 500, TimeUnit.MILLISECONDS);
		abilities.put(GCD.getName(), GCD);
		Ability fireball = new Ability("fireball",5, 3);
		abilities.put(fireball.getName(), fireball);
	}

}
