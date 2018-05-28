package com.jasper.game.entity.abilities.ranged;

import com.jasper.game.entity.Entity;
import com.jasper.game.entity.particle.Particle;
import com.jasper.game.graphics.Screen;
import com.jasper.game.graphics.Sprite;

public abstract class RangeAbility extends Entity{
	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double nx, ny;
	protected double speed, rateOfFire, range, damage;
	protected Boolean onCooldown = false;
	protected String name;
	
	public RangeAbility(int x, int y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;

	}

	public void update() {

	}

	public int getSpriteSize() {
		return sprite.SIZE;
	}

	public Sprite getSprite() {
		return sprite;
	}
	
	protected void move() {
		if (!missleCollision(x, y, nx, ny, sprite.SIZE)) {
			x += nx;
			y += ny;
		} else {
			remove();
			Particle p = new Particle((int) x, (int) y, 100, 100);
			for (int i = 0; i < p.getParticles().size(); i++) {
				p.getParticles().get(i).init(level);
			}
		}

		if (distance() > range)
			remove();

	}

	private double distance() {
		double dist = 0;
		dist = Math.sqrt((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y));
		return dist;
	}

	public boolean missleCollision(double x, double y, double xa, double ya, int size) {
		Boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = ((x + xa) + c % 2 * size - 8) / level.TILESIZE;
			double yt = ((y + ya) + c / 2 * size - 8) / level.TILESIZE;
			if (level.getTile((int) xt, (int) yt).solid())
				solid = true;
		}
		return solid;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x - sprite.SIZE / 2, (int) y - sprite.SIZE / 2, this);

	}
	
	public String getName() {
		return name;
	}
}
