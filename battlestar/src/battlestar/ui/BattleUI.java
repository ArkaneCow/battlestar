package battlestar.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import battlestar.Global;
import battlestar.ui.PaintCanvas;

public class BattleUI extends JFrame implements KeyListener, ActionListener {

	private PaintCanvas canvas;
	private final Timer timer = new Timer(20, this);
	private JLabel player_label;
	
	public BattleUI() {
		this.setResizable(false);
		this.setSize(new Dimension(Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT));
		this.setLayout(null);
		player_label = new JLabel("Player: " + Global.player.get_name());
		player_label.setBounds(10, 10, 200, 15);
		this.add(player_label);
		canvas = new PaintCanvas();
		this.add(canvas);
		int d_x = Global.WINDOW_WIDTH - Global.CANVAS_WIDTH;
		int d_y = Global.WINDOW_HEIGHT - Global.CANVAS_HEIGHT;
		canvas.setBounds((int) d_x / 2, (int) d_y / 2, Global.CANVAS_WIDTH,
				Global.CANVAS_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		timer.start();
		canvas.setFocusable(true);;
		canvas.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int key_code = arg0.getKeyCode();
		if (key_code == KeyEvent.VK_W) {
			Global.player.forward();
		} else if (key_code == KeyEvent.VK_A) {
			Global.player.left();
		} else if (key_code == KeyEvent.VK_S) {
			Global.player.behind();
		} else if (key_code == KeyEvent.VK_D) {
			Global.player.right();
		} else if (key_code == KeyEvent.VK_SPACE) {
			Global.player.shoot();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < Global.actors.size(); i++) {
			Global.actors.get(i).act();
		}
		this.repaint();
		canvas.repaint();
	}
}
