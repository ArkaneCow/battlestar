package battlestar.game.projectile;

import battlestar.game.actor.*;

public abstract class Projectile extends Actor {
	protected int damage;

	abstract public int get_damage();
}
