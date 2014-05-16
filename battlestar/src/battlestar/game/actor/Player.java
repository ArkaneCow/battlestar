package battlestar.game.actor;

import java.awt.Image;

import javax.swing.JOptionPane;

import battlestar.BattleStar;
import battlestar.Global;
import battlestar.game.projectile.GoodBullet;

public class Player extends Actor {

	public int player_health = 1000;
	
	public Player(String name) {
		this.x = Global.GRID_WIDTH / 2;
		this.y = Global.GRID_HEIGHT -2;
		this.name = name;
		this.sprite = Global.sprites.get("Player");
		if(name.equalsIgnoreCase("rikhil"))
			player_health = 9999999;
	}

	public void shoot() {
		GoodBullet b = new GoodBullet(0, -1);
		b.set_x(this.get_x());
		b.set_y(this.get_y());
		Global.actors.add(b);
	}
	
	@Override
	public void act() {   
		
		if (Global.player.player_health <= 0) {
			JOptionPane.showMessageDialog(null,  "YOU HAVE DIED");
			//high scores here?
			System.exit(0);
		}
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
