package battlestar.game.actor.enemy;

import java.awt.Image;

import battlestar.Global;
import battlestar.game.actor.enemy.*;
import battlestar.game.projectile.*;

public class Chitauri extends Enemy {
	public Chitauri() {
		this.name = "Chitauri";
		this.health = 100;
		this.point_value = 100;
		this.sprite = Global.sprites.get(this.name);
	}

	@Override
	public void deal_damage(int damage) {
		this.health -= damage;
	}

	@Override
	public int get_health() {
		return this.health;
	}

	@Override
	public int get_points() {
		return this.point_value;
	}

	@Override
	public String get_name() {
		return this.name;
	}

	@Override
	public Image get_sprite() {
		return this.sprite;
	}

	@Override
	public int get_x() {
		return this.x;
	}

	@Override
	public int get_y() {
		return this.y;
	}

	public void shoot() {
		Bullet b = new Bullet(0, 1);
		b.set_x(this.get_x());
		b.set_y(this.get_y() + 1);
	}

	@Override
	public void act() {
		ai();
	}

	public void ai() {
		if (this.x == Global.player.get_x()) {
			shoot();
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
