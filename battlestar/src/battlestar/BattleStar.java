package battlestar;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import battlestar.game.actor.Actor;
import battlestar.game.actor.Player;
import battlestar.game.actor.enemy.Biden;
import battlestar.game.actor.enemy.Chitauri;
import battlestar.game.actor.enemy.Enemy;
import battlestar.game.actor.enemy.Nixon;
import battlestar.ui.*;
import battlestar.Util;

public class BattleStar {
	private BattleUI ui;
	private int enemy_type = 0;

	public BattleStar() {
		initialize();
		game();
	}

	private void calculate_dimensions() {
		int sprite_w = Global.CANVAS_WIDTH / Global.GRID_WIDTH;
		int sprite_h = Global.CANVAS_HEIGHT / Global.GRID_HEIGHT;
		Global.sprite_width = sprite_w;
		Global.sprite_height = sprite_h;
	}

	private void load_sprites() {
		Global.sprites = new HashMap<String, Image>();
		File directory = new File("./sprites");
		for (final File file_entry : directory.listFiles()) {
			if (!file_entry.isDirectory()) {
				String filename = file_entry.getName();
				String[] split_name = filename.split("\\.");
				String extension = split_name[split_name.length - 1];
				if (extension.equals("png")) {
					System.out.println("found sprite: " + filename);
					Image i = null;
					try {
						i = ImageIO.read(file_entry);
					} catch (Exception ex) {
						System.out.println("Error loading image from file");
						ex.printStackTrace();
					}
					if (i != null) {
						i = Util.resize_image(i, Global.sprite_width,
								Global.sprite_height);
					}
					Global.sprites.put(split_name[0], i);
				}
			}
		}
	}

	private void load_background() {
		Image skybox = null;
		try {
			skybox = ImageIO.read(new File("background.png"));
		} catch (Exception ex) {
			System.out.println("Error loading image from file");
			ex.printStackTrace();
		}
		if (skybox != null) {
			skybox = Util.resize_image(skybox, Global.CANVAS_WIDTH,
					Global.CANVAS_HEIGHT);
		}
		Global.normal_background = skybox;
		Global.background = skybox;
		Image nixon = null;
		try {
			nixon = ImageIO.read(new File("nixon-background.png"));
		} catch (Exception ex) {
			System.out.println("Error loading image from file");
			ex.printStackTrace();
		}
		if (nixon != null) {
			nixon = Util.resize_image(nixon,  Global.CANVAS_WIDTH, Global.CANVAS_HEIGHT);
		}
		Global.nixon_background = nixon;
	}

	private void create_window() {
		ui = new BattleUI();
	}

	public void initialize() {
		Global.actors = new ArrayList<Actor>();
		Global.enemies = new ArrayList<Enemy>();

		calculate_dimensions();
		load_sprites();
		load_background();
		Global.enemies.add(new Chitauri());
		Global.enemies.add(new Biden());
		Global.enemies.add(new Nixon());
	}

	public void new_wave() {
		Constructor<? extends Enemy> et = null;
		try {
			if (Global.enemies.get(enemy_type).get_name().equals("Nixon")) {
				Global.background = Global.nixon_background;
			} else {
				Global.background = Global.normal_background;
			}
			et = Global.enemies.get(enemy_type)
					.getClass().getConstructor();
		} catch (NoSuchMethodException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SecurityException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		for (int i = 0; i < Global.GRID_WIDTH; i++) {
			try {
				
				Enemy new_e = et.newInstance();
				new_e.set_x(i);
				new_e.set_y(0);
				Global.actors.add(new_e);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (enemy_type == Global.enemies.size() - 1) {
			enemy_type = 0;
		} else {
			enemy_type++;
		}
	}

	public void game() {
		String player_name = (String) JOptionPane.showInputDialog(new JFrame(),
				"Enter your name: ", "Player name", JOptionPane.PLAIN_MESSAGE,
				null, null, null);
		System.out.println("Player name: " + player_name);
		Global.player = new Player(player_name);
		Global.actors.add(Global.player);
		create_window();
		new_wave();
	}
}
