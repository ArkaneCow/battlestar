package battlestar.game.actor.enemy;

import battlestar.game.actor.*;

public abstract class Enemy extends Actor {
	protected int point_value;
	protected int health;

	abstract public void deal_damage(int damage);

	abstract public int get_health();

	abstract public int get_points();
}
