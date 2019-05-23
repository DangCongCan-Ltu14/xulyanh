package base;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class show {
	public static void pr(BufferedImage in) {
		JFrame f = new JFrame("test");
		f.setSize(in.getWidth() + 20, in.getHeight()+20);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);

		f.add(new Can(in));
	}
	public static void pr(BufferedImage in,String d) {
		JFrame f = new JFrame(d);
		f.setSize(in.getWidth() + 20, in.getHeight()+20);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);

		f.add(new Can(in));
	}

}

