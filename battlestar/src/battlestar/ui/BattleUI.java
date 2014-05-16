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
	private final Timer timer = new Timer(100, this);
	private final Timer wave_timer = new Timer(15000, this);
	private JLabel player_label, score_label, health_label;
	
	public BattleUI() {
		this.setResizable(false);
		this.setSize(new Dimension(Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT));
		this.setLayout(null);
		player_label = new JLabel("Player: " + Global.player.get_name());
		player_label.setBounds(5, 10, 200, 15);
		score_label = new JLabel("Campaign funds: " + Global.score);
		score_label.setBounds(5, 30, 200, 15);
		health_label = new JLabel("Federal Budget Left: " + Global.player.player_health);
		health_label.setBounds(5, 50, 200, 15);
		this.add(player_label);
		this.add(score_label);
		this.add(health_label);
		canvas = new PaintCanvas();
		this.add(canvas);
		int d_x = Global.WINDOW_WIDTH - Global.CANVAS_WIDTH;
		int d_y = Global.WINDOW_HEIGHT - Global.CANVAS_HEIGHT;
		canvas.setBounds((int) d_x / 2, (int) d_y / 2, Global.CANVAS_WIDTH,
				Global.CANVAS_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		timer.start();
		wave_timer.start();
		canvas.setFocusable(true);;
		canvas.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int key_code = arg0.getKeyCode();
		if (key_code == KeyEvent.VK_W || key_code == KeyEvent.VK_UP) {
			Global.player.forward();
		} else if (key_code == KeyEvent.VK_A || key_code == KeyEvent.VK_LEFT) {
			Global.player.left();
		} else if (key_code == KeyEvent.VK_S || key_code == KeyEvent.VK_DOWN) {
			Global.player.behind();
		} else if (key_code == KeyEvent.VK_D || key_code == KeyEvent.VK_RIGHT) {
			Global.player.right();
		} else if (key_code == KeyEvent.VK_F || key_code == KeyEvent.VK_SPACE) {
			Global.player.shoot();
		} else if (key_code == KeyEvent.VK_ESCAPE) {
			System.out.println("Exiting program...");
			System.exit(0);
		} else if (key_code == KeyEvent.VK_N){
			Global.game.new_wave();
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
		
		if (e.getSource().equals(this.timer)) {
			this.score_label.setText("Campaign Funds: " + Global.score);
			this.health_label.setText("Federal Budget Left: " + Global.player.player_health);
			for (int i = 0; i < Global.actors.size(); i++) {
				Global.actors.get(i).act();
			}
			this.repaint();
			canvas.repaint();
		}
		if (e.getSource().equals(this.wave_timer)) {
			Global.game.new_wave();
		}
	}
}
