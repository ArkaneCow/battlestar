package battlestar.game.actor;

import java.awt.Image;

import battlestar.Global;

public class Player extends Actor {

	public int player_health = 1000;
	
	public Player(String name) {
		this.x = Global.GRID_WIDTH / 2;
		this.y = Global.GRID_HEIGHT -2;
		this.name = name;
		this.sprite = Global.sprites.get("Player");
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
