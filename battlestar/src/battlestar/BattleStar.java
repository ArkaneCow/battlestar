package battlestar;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import battlestar.game.actor.Actor;
import battlestar.game.actor.Player;
import battlestar.ui.*;
import battlestar.Util;

public class BattleStar {
	private BattleUI ui;

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
		Global.background = skybox;
	}

	private void create_window() {
		ui = new BattleUI();
	}

	public void initialize() {
		Global.actors = new ArrayList<Actor>();
		calculate_dimensions();
		load_sprites();
		load_background();
	}

	public void game() {
		String player_name = (String) JOptionPane.showInputDialog(new JFrame(),
				"Enter your name: ", "Player name", JOptionPane.PLAIN_MESSAGE,
				null, null, null);
		System.out.println("Player name: " + player_name);
		Global.player = new Player(player_name);
		Global.actors.add(Global.player);
		
		create_window();
	}
}
