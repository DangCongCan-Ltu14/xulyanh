package base;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class show {
	public static void pr(BufferedImage in) {
		JFrame f = new JFrame("test");
		f.setSize(in.getWidth() + 20, in.getHeight()+20);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		f.add(new Can(in));
	}

}

class Can extends Canvas {
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
