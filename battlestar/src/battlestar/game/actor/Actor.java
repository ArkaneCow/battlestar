package battlestar.game.actor;

import java.awt.Image;

import battlestar.Global;

public abstract class Actor {
	protected String name;
	protected Image sprite;
	protected int x = 0;
	protected int y = 0;

	abstract public String get_name();

	abstract public Image get_sprite();

	abstract public int get_x();

	abstract public int get_y();

	abstract public void set_x(int i);

	abstract public void set_y(int i);

	abstract public void act();

	public void print_location() {
		System.out.println("x: " + this.x + " y: " + this.y);
	}

	public void forward() {
		if (this.y > 0) {
			this.y--;
		}
		print_location();
	}

	public void behind() {
		if (this.y < Global.GRID_HEIGHT - 1) {
			this.y++;
		}
		print_location();
	}

	public void left() {
		if (this.x > 0) {
			this.x--;
		}
		print_location();
	}

	public void right() {
		if (this.x < Global.GRID_WIDTH - 1) {
			this.x++;
		}
		print_location();
	}

}
