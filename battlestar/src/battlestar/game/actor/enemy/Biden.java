package battlestar.game.actor.enemy;

import java.awt.Image;

import battlestar.Global;
import battlestar.game.actor.enemy.*;
import battlestar.game.projectile.*;

public class Biden extends Enemy {

	private int dx = 1;
	private int dy = 1;
	private int count = 0;
	private int move_count = 1;
	private int shoot_count = 0;
	private int shoot_cooldown = 1;

	public Biden() {
		this.name = "Biden";
		this.health = 50;
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

	public void die() {
		Global.score += this.get_points();
		Global.actors.remove(this);
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
		BadBullet b = new BadBullet(0, 1);
		b.set_x(this.get_x());
		b.set_y(this.get_y());
		Global.actors.add(b);
	}

	@Override
	public void act() {
		if (this.get_health() <= 0) {
			die();
		}
		ai();
	}

	public void ai() {
		if (this.x == Global.player.get_x()) {
			if (shoot_count == shoot_cooldown) {
				shoot();
				shoot_count = 0;
			} else {
				shoot_count++;
			}
		}
		if (count == move_count) {
			this.set_x(this.get_x() + dx);
			if (this.get_x() == 0 || this.get_x() == Global.GRID_WIDTH - 1) {
				dx = -dx;
				this.set_y(this.get_y() + dy);
			}
			count = 0;
		}
		if (this.get_y() >= Global.GRID_HEIGHT) {
			Global.score = Global.score - this.get_points();
			Global.actors.remove(this);
		}
		count++;
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
