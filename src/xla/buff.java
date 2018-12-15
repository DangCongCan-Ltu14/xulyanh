package xla;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import base.point;

public class buff {
	// static int csl = 0;

	BufferedImage in;

	BufferedImage paintline(ArrayList<point> arr, int col) {
		int x = in.getWidth(), y = in.getHeight();
		BufferedImage res = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		Graphics g = res.getGraphics();
		g.setColor(new Color(col));
		Polygon pl = new Polygon();
		for (point i : arr) {
			pl.addPoint(i.getx(), i.gety());
		}
		g.fillPolygon(pl);
		g.dispose();
		return res;
	}

	public static void save(BufferedImage in, String s, String extend) {
		try {
			File outputfile = new File(s+"."+extend);
			ImageIO.write(in, extend, outputfile);
			System.out.println(extend);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage get(String f) throws IOException {
		//System.out.println(f);
		BufferedImage in = ImageIO.read(new File(f));

		BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_RGB);
		// System.out.println(in.getWidth());
		Graphics2D g = newImage.createGraphics();
		g.drawImage(in, 0, 0, null);
		g.dispose();
		return newImage;
	}

}