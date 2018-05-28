package com.jasper.game.entity;

import java.util.ArrayList;
import java.util.List;

import com.jasper.game.entity.particle.Particle;

public class Spawner extends Entity{
	
	public enum Type{
		MOB, PARTICLE;
	}
	
	private Type type;
	
	public Spawner(int x, int y, Type type, int amount) {
		this.x = x;
		this.y = y;
		for(int i = 0; i< amount;i++) {
			if(type==Type.PARTICLE) {
				new Particle(x,y,50);
					level.add(new Particle(x, y, 50));
			}	
		}
	}
}
