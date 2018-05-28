import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.jasper.game.entity.abilities.Ability;

import junit.framework.Assert;

public class Anything {

	@Test
	public void test() {
		
		Ability fireball = new Ability("fireball",2, 1);
		assertTrue(fireball.tryUse());
	}
	
	@Test
	public void test2() {
		Ability GCD = new Ability("GCD", 1, 500,TimeUnit.MILLISECONDS);
		GCD.tryUse();
		assertFalse(GCD.tryUse());
	}

}
