package cuver;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

import base.point;

public class catmullbu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Canvas ve=new
	// BufferedImage buf=new BufferedImage(800, 800,
	// BufferedImage.TYPE_INT_RGB);
	catmullbu() {
		setVisible(true);
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		arr.add(new point(70, 74));
		arr.add(new point(80, 40));
		arr.add(new point(90, 90));
		arr.add(new point(200, 100));
		arr.add(new point(220, 110));
		arr.add(new point(240, 120));
		arr.add(new point(300, 50));
		arr.add(new point(400, 100));
		arr.add(new point(500, 50));
		arr.add(new point(600, 100));
		arr.add(new point(700, 580));
		arr.add(new point(100, 100));
		arr.add(new point(150, 250));
		arr.add(new point(250, 250));
		arr.add(new point(100, 100));
		arr.add(new point(110, 110));
	}

	public void paint(Graphics g) {
		super.paint(g);
		paintline();
		// g.drawImage(buf, 0, 0, null);

	}

	void paintline() {
		Graphics g = getGraphics();
		g.setColor(Color.GREEN);
		point p = arr.get(0);
		for (point i : arr) {
			g.drawLine(p.getx(), p.gety(), i.getx(), i.gety());
			p = i;
			// System.out.print(mcat[i][j] + " ");
		}
		g.dispose();
	}

	public static void main(String[] args) {
		catmullbu c = new catmullbu();
	}

	int[][] mcat = { { -1, 3, -3, 1 }, { 2, -5, 4, -1 }, { -1, 0, 1, 0 }, { 0, 2, 0, 0 } };
	ArrayList<point> arr = new ArrayList<point>();

	void in() {
		for (int i = 0; i < mcat.length; i++) {
			for (int j = 0; j < mcat[i].length; j++) {
				System.out.print(mcat[i][j] + "  ");
			}
			System.out.print("\n");
		}
	}

	private void pcat(int l,ArrayList<point> arr,Graphics g) {
		g.setColor(Color.RED);
		int[][] cat = new int[4][2];// a,b,c,d
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				cat[i][0] = cat[i][0] + arr.get(l + j).getx() * mcat[i][j];
				cat[i][1] = cat[i][1] + arr.get(l + j).gety() * mcat[i][j];
			}
		}
		double t = 0.01;
		double t3, t2;
		int x0, y0, x1, y1;
		x0 = arr.get(l + 1).getx();
		y0 = arr.get(l + 1).gety();
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
		System.out.println("done");
		g.dispose();
	}

}
