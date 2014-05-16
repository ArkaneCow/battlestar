package battlestar.game.projectile;

import java.awt.Image;

import battlestar.game.actor.enemy.Enemy;
import battlestar.game.projectile.*;
import battlestar.Global;

public class GoodBullet extends Projectile {

	private int dx;
	private int dy;

	public GoodBullet(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
		this.damage = 50;
		this.name = "GoodBullet";
		this.sprite = Global.sprites.get(this.name);
	}

	@Override
	public Image get_sprite() {
		return this.sprite;
	}

	@Override
	public String get_name() {
		return this.name;
	}

	@Override
	public int get_damage() {
		return this.damage;
	}

	@Override
	public int get_x() {
		return this.x;
	}

	@Override
	public int get_y() {
		return this.y;
	}

	
	@Override
	public void act() {
		if (this.x < 0 || this.x > Global.GRID_WIDTH || this.y < 0
				|| this.y > Global.GRID_HEIGHT) {
			Global.actors.remove(this);
		}
		boolean hit = false;
		for (int i = 0; i < Global.actors.size(); i++) {
			if (this.x == Global.actors.get(i).get_x()
					&& this.y == Global.actors.get(i).get_y()) {
				hit = true;
				// hitting object
				if (battlestar.game.actor.enemy.Enemy.class
						.isAssignableFrom(Global.actors.get(i).getClass())) {
					// it hit an enemy;
					Enemy e = (Enemy) Global.actors.get(i);
					e.deal_damage(this.damage);
					Global.actors.remove(this);
				}
			}
		}
		this.set_x(this.get_x() + dx);
		this.set_y(this.get_y() + dy);
	}

	@Override
	public void set_x(int i) {
		this.x = i;
	}

	@Override
	public void set_y(int i) {
		this.y = i;
	}
}
