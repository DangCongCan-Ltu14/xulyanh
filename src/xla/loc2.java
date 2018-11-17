package xla;

import java.awt.Color;
import java.awt.image.BufferedImage;

import base.rbg;

public class loc2 {
	// static int[][] ttc = { { 0, -1, 0 }, { -1, 4 , -1 }, { 0,-1, 0} };
	static int[][] ttc = { { 1, 1, 1 }, { 1, -8, 1 }, { 1, 1, 1 } };
	// static int[][] ttc = { {
	// 0,0,-1,0,0},{0,-1,-2,-1,0},{-1,-2,16,-2,-1},{0,-1,-2,-1,0},{
	// 0,0,-1,0,0}};
	static int[][] lgs = { { 2, 4, 5, 4, 2 }, { 4, 9, 12, 9, 4 }, { 5, 12, 15, 12, 5 }, { 4, 9, 12, 9, 4 },
			{ 2, 4, 5, 4, 2 } };
	static int[][] ttb = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
	static int csl;
	static int r = 30, b = 50, g = 30;
	static int color = r + b + g;
	static int plag = -1;

	static protected BufferedImage cv(BufferedImage in, int[][] H) {
		ktcsl(H);
		int x = in.getWidth();
		int y = in.getHeight();
		rbg l = new rbg(0);
		int v = H.length;
		int lv = v / 2;
		BufferedImage res = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				l.reset();
				for (int a = 0; a < v; a++)
					for (int b = 0; b < v; b++) {

						if ((a - lv + i < 0) || (b - lv + j < 0) || (a - lv + i >= x) || (b - lv + j >= y))
							l.add(in.getRGB(i, j), H[a][b]);
						else

							l.add(in.getRGB(a - lv + i, b - lv + j), H[a][b]);

					}
				res.setRGB(i, j, l.div(csl));
			}
		}
		return res;
	}

	protected static void imgiv(BufferedImage in) {
		int x = in.getWidth();
		int y = in.getHeight();
		int l = 0;

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				l = in.getRGB(i, j); // co the co loi
				l = ~l;
				// l = graycolor(l);
				in.setRGB(i, j, l);
			}
		}

	}

	protected static void imggray(BufferedImage in) {
		int x = in.getWidth();
		int y = in.getHeight();
		int l = 0;

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				l = in.getRGB(i, j); // co the co loi
				// =~l;
				l = graycolor(l);
				in.setRGB(i, j, l);
			}
		}

	}

	/**
	 * chuyen mau sang xan
	 * 
	 * @param a
	 * @return
	 */
	static int graycolor(int a) {
		int i = 0;
		// int[] k = new int[5];
		rbg l = new rbg(a);
		i = (g * l.getG() + b * l.getB() + r * l.getR()) / color;
		i = i & plag;
		i = i * 0x10101;
		a = l.getA() << 24;
		a = a | i;
		// a=a&0xff0000ff;
		return i;

	}

	static private void ktcsl(int[][] H) {
		// TODO Auto-generated method stub
		csl = 0;
		int v = H.length;
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++)
				if (H[i][j] > 0)
					csl = csl + H[i][j];
		}
		if (csl == 0)
			csl = 1;
		System.out.println(csl);
	}

	public static BufferedImage tc(BufferedImage in) {
		// TODO Auto-generated method stub

		return cv(in, ttc);
	}

	/**
	 * loc trung binh
	 * 
	 * @param in
	 * @return
	 */
	public static BufferedImage tb(BufferedImage in) {
		// TODO Auto-generated method stub

		return cv(in, ttb);
	}

	/**
	 * loc graussian
	 * 
	 * @param in
	 * @return
	 */
	public static BufferedImage tg(BufferedImage in) {
		// TODO Auto-generated method stub
		return cv(in, lgs);
	}

	public static BufferedImage edge(BufferedImage in) {
		// TODO Auto-generated method stub
		int x = in.getWidth();
		int y = in.getHeight();
		int a, b,c,d;
		int s = Color.white.getRGB();
		int p=Color.red.getRGB();
		int bl=Color.black.getRGB();
		BufferedImage eg = new BufferedImage(2 * x, 2 * y, BufferedImage.TYPE_INT_RGB);
	//	BufferedImage eg2 = new BufferedImage(2 * x, 2 * y, BufferedImage.TYPE_INT_RGB);
		x--;
		y--;
		//s=p;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				a = in.getRGB(i, j);
				b = in.getRGB(i + 1, j);
				if (a != b)
					eg.setRGB(2 * i + 1, 2 * j, s);
				b = in.getRGB(i, j + 1);
				if (a != b)
					eg.setRGB(2 * i, 2 * j + 1, s);
			}
		}
		x=2*x;
		y=2*y;
		//p=s;
	//	if(false)
		for (int i = 1; i <  x; i = i + 2) {
			for (int j = 1; j < y; j = j + 2) {
				a = eg.getRGB(i - 1, j);
				b = eg.getRGB(i + 1, j);
				c = eg.getRGB(i, j + 1);
				d = eg.getRGB(i, j - 1);
//				if (a == s && b == s) {
//					if(c==s||d==s) eg.setRGB(i, j, p);
//					else eg.setRGB(i, j, s);
//				} else {
//				if(c == s && d == s)
//				{
//					if(a==s||b==s) eg.setRGB(i, j,p);
//					else eg.setRGB(i, j, s);
//				}
	int ks=0;
	if(a==s) ks++;
	if(b==s) ks++;
	if(c==s) ks++;
	if(d==s) ks++;
	if(ks>1)eg.setRGB(i, j, p);
				
				}
			}
		
		return eg;
	}

}
