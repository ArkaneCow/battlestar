package battlestar.game.projectile;

import java.awt.Image;

import battlestar.game.actor.enemy.Enemy;
import battlestar.game.projectile.*;
import battlestar.Global;

public class Bullet extends Projectile {

	private int dx;
	private int dy;

	public Bullet(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
		this.damage = 50;
		this.name = "Bullet";
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
				} else if (battlestar.game.actor.Player.class
						.isAssignableFrom(Global.actors.get(i).getClass())) {
					Global.player.player_health -= this.damage;
				}
			}
		}
		if (!hit) {
			if (dx > 0) {
				for (int i = 0; i < dx; i++) {
					this.right();
				}
			} else if (dx < 0) {
				int count = Math.abs(dx);
				for (int i = 0; i < count; i++) {
					this.left();
				}
			}
			if (dy > 0) {
				for (int i = 0; i < dy; i++) {
					this.behind();
				}
			} else if (dy < 0) {
				int count = Math.abs(dy);
				for (int i = 0; i < count; i++) {
					this.forward();
				}
			}
		}
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
