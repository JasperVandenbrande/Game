package com.jasper.game.entity.mob;

import static com.jasper.game.entity.mob.Direction.EAST;
import static com.jasper.game.entity.mob.Direction.NORTH;
import static com.jasper.game.entity.mob.Direction.SOUTH;
import static com.jasper.game.entity.mob.Direction.WEST;

import java.util.ArrayList;
import java.util.List;

import com.jasper.game.entity.Entity;
import com.jasper.game.entity.abilities.ranged.Fireball;
import com.jasper.game.entity.abilities.ranged.RangeAbility;


public abstract class Mob extends Entity {
	
	protected Direction dir = SOUTH; // North 0, 1 East, 2 South and 3 West.
	protected boolean moving = false;
	protected boolean solid = true;
	protected String name;
	protected List<RangeAbility> projectiles = new ArrayList<RangeAbility>();
	
	
	/**
	 * moves a mob, seperates the move into an x movement and a y movement
	 * @param xa in pixel precision
	 * @param ya in pixel precision
	 */
	protected void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		if (xa == 0 && ya < 0)
			dir = NORTH;
		if (xa > 0 && ya == 0)
			dir = EAST;// east
		if (xa == 0 && ya > 0)
			dir = SOUTH;// south
		if (xa < 0 && ya == 0)
			dir = WEST;// west

		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}

	public void update() {
		
	}
	
	/**
	 * clears the projectiles casted by the mob that are death from the entity list
	 */
	public void clearProjectile() {
		for(int i = 0; i <projectiles.size(); i++) {
			if(projectiles.get(i).isRemoved()) {
				projectiles.remove(i);
			}
		}
	}
	/**
	 * Checks for collision on the next tile thanks to corner collision.
	 * @param xa Tile precision
	 * @param ya Tile precision
	 * @return True if the tile you are moving to is solid, False if you can move to the next tile
	 */
	private boolean collision(int xa, int ya) {
		Boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 10 - 6) / level.TILESIZE;
			int yt = ((y + ya) + c / 2 * 7) / level.TILESIZE;
			if (level.getTile(xt, yt).solid())
				solid = true;
		}
		return solid;
	}

	public void render() {
	}
	
	/**
	 * creates a new projectile and fires it
	 * @param x players pixel location
	 * @param y players pixel location
	 * @param dir angle made between player and mouse
	 */
	protected void shoot(int x, int y, double dir) {
		RangeAbility p = new Fireball(x, y, dir);
		p.init(level.getLevel());
		projectiles.add(p);
	}
	public String getName() {
		return name;
	}
}
