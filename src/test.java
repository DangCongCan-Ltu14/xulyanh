import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import base.show;
import pv3.vector;
import segment.Kmean2;
import xla.loc;

//import potrace.vector;
public class test {
	static final int length = 4096;
	static String fl = "/home/amneiht/Desktop/anh";

	// @SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			BufferedImage in = ImageIO.read(new File(fl+"/test/350.jpg"));
			BufferedImage tin=scale(in);
			show.pr(in);
			show.pr(tin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void toolest() {
		File dt = new File(fl + "/kmean");
		if (!dt.exists())
			dt.mkdirs();
		dt = new File(fl + "/vecto");
		if (!dt.exists())
			dt.mkdirs();
		dt = new File(fl + "/gauss");
		if (!dt.exists())
			dt.mkdirs();
		try {
			File kl = new File(fl + "/test");
			if (kl.isDirectory()) {
				for (File vkl : kl.listFiles()) {
					// System.out.println(vkl.getName());
					make(vkl);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void make(File vkl) throws IOException {
		// TODO Auto-generated method stub
		BufferedImage in = ImageIO.read(vkl);
		// Show.pr
		BufferedImage tb = loc.tb(in);
		String fle = fl + "/gauss/" + vkl.getName();
		ImageIO.write(tb, "jpg", new File(fle));
		tb = new Kmean2(in, 9).segment();
		fle = fl + "/kmean/" + vkl.getName();
		ImageIO.write(tb, "jpg", new File(fle));
		fle = fl + "/vecto/" + vkl.getName();
		tb = new vector(tb, 5).creat().paintimg();
		ImageIO.write(tb, "jpg", new File(fle));
	}

	private static BufferedImage scale(BufferedImage tin) {
		double h = 300.0 / Math.max(tin.getHeight(), tin.getWidth());
		int y = (int) (tin.getHeight() * h), x = (int) (tin.getWidth() * h);
		BufferedImage in = new BufferedImage(x, y, tin.getType());
		Image tmp = tin.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		Graphics g = in.getGraphics();
		g.drawImage(tmp, 0, 0, null);
		g.dispose();
		return in;
	}
}
