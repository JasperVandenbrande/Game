package com.jasper.game.entity.particle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.jasper.game.entity.Entity;
import com.jasper.game.graphics.Screen;
import com.jasper.game.graphics.Sprite;
import com.jasper.game.graphics.SpriteFactory;

public class Particle extends Entity {
	
	private List<Particle> particles = new ArrayList<Particle>();
	private long life;
	
	protected double xx, yy, xa, ya;

	public Particle(int x, int y, long life) {
		this.x = x;
		this.y = y;
		this.life = TimeUnit.MILLISECONDS.convert(life , TimeUnit.MILLISECONDS) + System.currentTimeMillis();
		this.xx = x;
		this.yy =y;
		sprite = Sprite.particle;	
		
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
	}
	
	public Particle(int x, int y, int life, int amount) {
		this(x,y,life);
		for (int i = 0; i < amount - 1; i++) {
			particles.add(new Particle(x, y, life));
		}
		particles.add(this);
		
	}
	
	/**
	 * updates the movement of particles, if they are death the are removed
	 */
	public void update() {
		this.xx += xa;
		this.yy += ya;
		if(isDeath())
			remove();
	}
	
	/**
	 * gives the command to render the particles
	 */
	public void render(Screen screen) {
		screen.renderSprite((int)xx, (int)yy, sprite, true);
	}
	
	/**
	 * Retrieves a list off all active particles
	 * @return list of all active particles
	 */
	public List<Particle> getParticles() {
		return particles;
	}
	
	/**
	 * checks if a particle is death or alive
	 * @return True is particle is death, False if particle is alive
	 */
	public boolean isDeath() {
		return(TimeUnit.MILLISECONDS.convert(life - System.currentTimeMillis(), TimeUnit.MILLISECONDS)<0);
	}
	

}
