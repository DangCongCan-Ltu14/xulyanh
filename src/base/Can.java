package base;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Can extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 89981L;
	BufferedImage i;

	public Can(BufferedImage in) {
		// TODO Auto-generated constructor stub
		i = in;
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(i, 0, 0, null);
	}
}