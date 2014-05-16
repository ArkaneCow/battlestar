package battlestar;

import java.awt.Image;
import java.util.*;

import battlestar.game.actor.*;
import battlestar.game.actor.enemy.Enemy;

public class Global {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;

	public static final int CANVAS_WIDTH = 500;
	public static final int CANVAS_HEIGHT = 500;

	public static final int GRID_WIDTH = 10;
	public static final int GRID_HEIGHT = 10;

	public static int sprite_width = 48;
	public static int sprite_height = 48;

	public static Map<String, Image> sprites;

	public static Image background;
	public static Image nixon_background;
	public static Image normal_background;

	public static Player player;

	public static ArrayList<Actor> actors;
	public static ArrayList<Enemy> enemies;
	
	public static int score = 0;
	
	public static BattleStar game;
}
