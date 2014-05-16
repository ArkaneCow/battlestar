package battlestar.game.actor;

import java.awt.Image;

import battlestar.Global;
import battlestar.game.projectile.Bullet;

public class Player extends Actor {

	public int player_health = 1000;
	
	public Player(String name) {
		this.x = Global.GRID_WIDTH / 2;
		this.y = Global.GRID_HEIGHT -2;
		this.name = name;
		this.sprite = Global.sprites.get("Player");
	}

	public void shoot() {
		Bullet b = new Bullet(0, -1);
		b.set_x(this.get_x());
		b.set_y(this.get_y() -1);
		Global.actors.add(b);
	}
	
	@Override
	public void act() {

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

	@Override
	public void set_x(int i) {
		this.x = i;
	}

	@Override
	public void set_y(int i) {
		this.y = i;
	}

}
