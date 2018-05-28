package com.jasper.game.entity.abilities.ranged;


import com.jasper.game.graphics.SpriteFactory;

public class Fireball extends RangeAbility {
	
	public static final int COOL_DOWN = 5;

	public Fireball(int x, int y, double dir) {
		super(x, y, dir);
		range = 100;
		damage = 20;
		speed = 4;
		
		sprite = SpriteFactory.spriteMap.get("fireball");
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update() {
		move();
	}


	

}
