package battlestar.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import battlestar.Global;
import battlestar.Util;

public class PaintCanvas extends JPanel{
	
	public PaintCanvas() {
		super();
		System.out.println("Paint canvas intialized");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Global.background, 0, 0, null);
		for (int i = 0; i < Global.actors.size(); i++) {
			Image sprite = Global.actors.get(i).get_sprite();
			int x = Global.actors.get(i).get_x() * Global.sprite_width;
			int y = Global.actors.get(i).get_y() * Global.sprite_height;
			g.drawImage(sprite, x, y, Global.sprite_width, Global.sprite_height, null);
			System.out.println("new update");
		}
	}
}
