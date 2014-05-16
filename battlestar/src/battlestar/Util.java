package battlestar;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Util {
	public static Image resize_image(Image input, int width, int height) {
		return input.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	}
}
