package cuver;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import base.point;

public class catmull {
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	// Canvas ve=new
	// BufferedImage buf=new BufferedImage(800, 800,
	// BufferedImage.TYPE_INT_RGB);

	void paintline(Graphics g, ArrayList<point> arr) {
		// Graphics g = getGraphics();
		g.setColor(Color.GREEN);
		point p = arr.get(0);
		for (point i : arr) {
			g.drawLine(p.getx(), p.gety(), i.getx(), i.gety());
			p = i;
			// System.out.print(mcat[i][j] + " ");
		}
		g.dispose();
	}

	static int[][] mcat = { { -1, 3, -3, 1 }, { 2, -5, 4, -1 }, { -1, 0, 1, 0 }, { 0, 2, 0, 0 } };
	// ArrayList<point> arr = new ArrayList<point>();

	public static BufferedImage draw(int x, int y, ArrayList<point> arr) {
		BufferedImage res = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		Graphics g = res.getGraphics();
		int k = arr.size();
		for (int i = 0; i < k; i++)
			pcat(i, arr, g);
		g.dispose();
		return res;
	}

	private static void pcat(int l, ArrayList<point> arr, Graphics g) {
		g.setColor(Color.RED);
		int p, k = arr.size();
		int[][] cat = new int[4][2];// a,b,c,d
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				p = (l + j ) % k;
				cat[i][0] = cat[i][0] + arr.get(p).getx() * mcat[i][j];
				cat[i][1] = cat[i][1] + arr.get(p).gety() * mcat[i][j];
			}
		}
		
		double t = 0.01;
		double t3, t2;
		int x0, y0, x1, y1;
		p = (l + 1 ) % k;
		x0 = arr.get(p).getx();
		y0 = arr.get(p).gety();
		while (t <= 1) {
			t2 = t * t;
			t3 = t2 * t;
			x1 = (int) (t3 * cat[0][0] + t2 * cat[1][0] + t * cat[2][0] + cat[3][0]) / 2;
			y1 = (int) (t3 * cat[0][1] + t2 * cat[1][1] + t * cat[2][1] + cat[3][1]) / 2;
			g.drawLine(x0, y0, x1, y1);
			x0 = x1;
			y0 = y1;
			t = t + 0.01;
		}
		//System.out.println("done");

	}

}
